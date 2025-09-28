package com.kaleshrikant.drools;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.io.File;
import java.nio.file.Files;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Shrikant Kale
 * @Date 28 Sep 2025
 */

public class HotDeploymentManager {
	private KieServices kieServices;
	private KieContainer kieContainer;
	private final String rulesDirectory = "src/main/resources/rules";
	private long lastModified = 0;
	private ScheduledExecutorService scheduler;

	public HotDeploymentManager() {
		this.kieServices = KieServices.Factory.get();
		initializeRulesEngine();
		startFileWatcher();
	}

	private void initializeRulesEngine() {
		System.out.println("Initializing Drools rules engine...");
		buildKnowledgeBase();
	}

	private void buildKnowledgeBase() {
		try {
			KieFileSystem kieFileSystem = kieServices.newKieFileSystem();

			// Load all DRL files from rules directory
			File rulesDir = new File(rulesDirectory);
			if(rulesDir.exists() && rulesDir.isDirectory()) {
				File[] drlFiles = rulesDir.listFiles((dir, name) -> name.endsWith(".drl"));
				if(drlFiles != null) {
					for(File drlFile: drlFiles) {
						String content = new String(Files.readAllBytes(drlFile.toPath()));
						String resourcePath = "src/main/resources/rules/" + drlFile.getName();
						kieFileSystem.write(resourcePath, content);
						System.out.println("Loaded rule file: " + drlFile.getName());
					}
				}
			}

			KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
			kieBuilder.buildAll();

			Results results = kieBuilder.getResults();
			if(results.hasMessages(Message.Level.ERROR)) {
				System.err.println("Build errors:");
				results.getMessages().forEach(message -> System.err.println(message.getText()));
				return;
			}

			// Update container
			if(kieContainer != null) {
				kieContainer.dispose();
			}
			kieContainer = kieServices.newKieContainer(kieBuilder.getKieModule().getReleaseId());

			System.out.println("✅ Knowledge base built successfully!");

		} catch(Exception e) {
			System.err.println("Error building knowledge base: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private void startFileWatcher() {
		scheduler = Executors.newScheduledThreadPool(1);
		scheduler.scheduleWithFixedDelay(this::checkForRuleChanges, 5, 2, TimeUnit.SECONDS);
		System.out.println("🔍 File watcher started - monitoring for rule changes every 2 seconds");
	}

	private void checkForRuleChanges() {
		try {
			File rulesDir = new File(rulesDirectory);
			if(! rulesDir.exists()) return;

			long currentLastModified = getLastModifiedTime(rulesDir);

			if(currentLastModified > lastModified) {
				System.out.println("🔥 RULE CHANGES DETECTED! Performing HOT DEPLOYMENT...");
				buildKnowledgeBase();
				lastModified = currentLastModified;
				System.out.println("🚀 HOT DEPLOYMENT COMPLETED!");
			}
		} catch(Exception e) {
			System.err.println("Error checking for rule changes: " + e.getMessage());
		}
	}

	private long getLastModifiedTime(File directory) {
		long lastMod = 0;
		File[] files = directory.listFiles();
		if(files != null) {
			for(File file: files) {
				if(file.getName().endsWith(".drl")) {
					lastMod = Math.max(lastMod, file.lastModified());
				}
			}
		}
		return lastMod;
	}

	public void executeRules(Object... facts) {
		if(kieContainer == null) {
			System.err.println("❌ Knowledge base not initialized!");
			return;
		}

		KieSession kieSession = kieContainer.newKieSession();

		try {
			System.out.println("📋 Inserting facts into session:");
			for(Object fact: facts) {
				kieSession.insert(fact);
				System.out.println("  - " + fact);
			}

			System.out.println("🔄 Firing all rules...");
			int rulesFired = kieSession.fireAllRules();
			System.out.println("✅ Rules executed: " + rulesFired + " rules fired");

		} finally {
			kieSession.dispose();
		}
	}

	public void manualHotDeploy() {
		System.out.println("🔧 Manual HOT DEPLOYMENT triggered...");
		buildKnowledgeBase();
		lastModified = getLastModifiedTime(new File(rulesDirectory));
		System.out.println("🎯 Manual HOT DEPLOYMENT completed!");
	}

	public void shutdown() {
		if(scheduler != null) {
			scheduler.shutdown();
			System.out.println("📴 File watcher stopped");
		}
		if(kieContainer != null) {
			kieContainer.dispose();
			System.out.println("🗑️ Knowledge base disposed");
		}
	}
}
