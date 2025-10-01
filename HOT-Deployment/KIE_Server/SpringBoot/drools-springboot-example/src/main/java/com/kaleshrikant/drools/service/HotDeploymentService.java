package com.kaleshrikant.drools.service;


import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Shrikant Kale
 * @Date 30 Sep 2025
 */
@Service
public class HotDeploymentService {
	private final KieServices kieServices = KieServices.get();
	private KieContainer kieContainer;
	private Map<String, Long> fileLastModified = new HashMap<>();
	private final String hotDeploymentPath = "hot-deployment/";

	public HotDeploymentService() {
		createHotDeploymentDirectory();
		System.out.println("🔥 HOT Deployment Service initialized - Monitoring: " + hotDeploymentPath);
	}

	@Scheduled(fixedRate=5000)
	public void checkForRuleUpdates() {
		boolean changesDetected = false;

		try {
			changesDetected = scanForChanges();
			if(changesDetected) {
				System.out.println("🔄 HOT Deployment: Rule changes detected! Reloading rules...");
				reloadRules();
				System.out.println("✅ HOT Deployment: Rules reloaded successfully!");
			}
		} catch(Exception e) {
			System.err.println("❌ HOT Deployment Error: " + e.getMessage());
		}
	}

	private boolean scanForChanges() {
		boolean changes = false;

		try {
			File hotDeployDir = new File(hotDeploymentPath);
			if(! hotDeployDir.exists()) return false;

			File[] packageDirs = hotDeployDir.listFiles(File::isDirectory);
			if(packageDirs == null) return false;

			for(File packageDir: packageDirs) {
				File[] drlFiles = packageDir.listFiles((dir, name) -> name.endsWith(".drl"));
				if(drlFiles == null) continue;

				for(File drlFile: drlFiles) {
					String fileKey = packageDir.getName() + "/" + drlFile.getName();
					long lastModified = drlFile.lastModified();
					Long previousModified = fileLastModified.get(fileKey);

					if(previousModified == null || lastModified > previousModified) {
						fileLastModified.put(fileKey, lastModified);
						changes = true;
						System.out.println("📝 HOT Deployment: Detected change in: " + fileKey);
					}
				}
			}
		} catch(Exception e) {
			System.err.println("Error scanning for changes: " + e.getMessage());
		}

		return changes;
	}

	public synchronized void reloadRules() {
		try {
			KieFileSystem kieFileSystem = kieServices.newKieFileSystem();

			// Load rules from hot-deployment directory
			loadRulesFromHotDeployment(kieFileSystem);

			// If no hot deployment rules found, skip reload
			if(fileLastModified.isEmpty()) {
				System.out.println("ℹ️  HOT Deployment: No rules found in hot-deployment directory");
				return;
			}

			KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
			kieBuilder.buildAll();

			if(kieBuilder.getResults().hasMessages(org.kie.api.builder.Message.Level.ERROR)) {
				System.err.println("❌ HOT Deployment: Rule compilation errors:");
				kieBuilder.getResults().getMessages().forEach(msg -> {
					System.err.println("   - " + msg.getText());
				});
				throw new RuntimeException("Rule compilation failed");
			}

			KieModule kieModule = kieBuilder.getKieModule();

			// Create container directly from the module, no Maven resolution
			KieContainer newContainer = kieServices.newKieContainer(kieModule.getReleaseId());

			// Replace the old container
			if(this.kieContainer != null) {
				this.kieContainer.dispose();
			}
			this.kieContainer = newContainer;

			System.out.println("✅ HOT Deployment: Successfully loaded " + fileLastModified.size() + " rule files");

		} catch(Exception e) {
			System.err.println("❌ HOT Deployment failed: " + e.getMessage());
			// Don't throw exception - let the application continue with original rules
		}
	}

	private void loadRulesFromHotDeployment(KieFileSystem kieFileSystem) {
		try {
			File hotDeployDir = new File(hotDeploymentPath);
			if(! hotDeployDir.exists()) return;

			File[] packageDirs = hotDeployDir.listFiles(File::isDirectory);
			if(packageDirs == null) return;

			for(File packageDir: packageDirs) {
				File[] drlFiles = packageDir.listFiles((dir, name) -> name.endsWith(".drl"));
				if(drlFiles == null) continue;

				for(File drlFile: drlFiles) {
					kieFileSystem.write(ResourceFactory.newFileResource(drlFile));
					System.out.println("🔥 HOT Deployment: Loaded - " + drlFile.getPath());
				}
			}
		} catch(Exception e) {
			System.err.println("Error loading from hot deployment: " + e.getMessage());
		}
	}

	private void createHotDeploymentDirectory() {
		try {
			Path hotDeployPath = Paths.get(hotDeploymentPath);
			if(! Files.exists(hotDeployPath)) {
				Files.createDirectories(hotDeployPath);
				Files.createDirectories(Paths.get(hotDeploymentPath + "applicant"));
				Files.createDirectories(Paths.get(hotDeploymentPath + "loan"));
				System.out.println("📁 HOT Deployment: Created directory structure - " + hotDeploymentPath);
				System.out.println("💡 Please add your DRL files to: " + hotDeploymentPath + "applicant/ and " + hotDeploymentPath + "loan/");
			}
		} catch(Exception e) {
			System.err.println("Error creating hot-deployment directory: " + e.getMessage());
		}
	}

	public KieSession newKieSession() {
		if(kieContainer == null) {
			throw new IllegalStateException("HOT Deployment: KieContainer not ready - no rules loaded from hot-deployment directory");
		}
		return kieContainer.newKieSession();
	}

	public void reloadRulesManually() {
		reloadRules();
	}

	public String getHotDeploymentStatus() {
		StringBuilder status = new StringBuilder();
		status.append("=== HOT Deployment Status ===\n");
		status.append("Monitoring Directory: ").append(hotDeploymentPath).append("\n");
		status.append("Rules Loaded: ").append(fileLastModified.size()).append("\n");
		status.append("Last Check: ").append(new Date()).append("\n");

		if(! fileLastModified.isEmpty()) {
			status.append("Active Rules:\n");
			fileLastModified.keySet().forEach(rule -> {
				status.append("  - ").append(rule).append("\n");
			});
		} else {
			status.append("Status: No rules loaded from hot-deployment directory\n");
			status.append("Action: Add DRL files to ").append(hotDeploymentPath).append("applicant/ and ").append(hotDeploymentPath).append("loan/\n");
		}

		return status.toString();
	}

	public boolean isHotDeploymentActive() {
		return kieContainer != null && ! fileLastModified.isEmpty();
	}
}
