package com.kaleshrikant.drools.config;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Shrikant Kale
 * @Date 30 Sep 2025
 */

@Configuration
public class DroolsConfig {
	private final KieServices kieServices = KieServices.get();

	@Bean
	public KieContainer kieContainer() {
		try {
			System.out.println("=== INITIALIZING DROOLS 9.x ENGINE ===");

			KieFileSystem kieFileSystem = kieServices.newKieFileSystem();

			// Load rules from packages
			loadRulesFromPackage(kieFileSystem, "applicant");
			loadRulesFromPackage(kieFileSystem, "loan");

			KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
			kieBuilder.buildAll();

			if (kieBuilder.getResults().hasMessages(org.kie.api.builder.Message.Level.ERROR)) {
				System.err.println("Build errors:");
				kieBuilder.getResults().getMessages().forEach(msg -> {
					System.err.println("  - " + msg.getText());
				});
				throw new RuntimeException("Error building rules");
			}

			KieModule kieModule = kieBuilder.getKieModule();
			KieContainer kieContainer = kieServices.newKieContainer(kieModule.getReleaseId());

			// Test session creation
			testSessionCreation(kieContainer);

			System.out.println("✓ Drools 9.x Engine initialized successfully!");
			System.out.println("============================================");

			return kieContainer;

		} catch (Exception e) {
			System.err.println("✗ Failed to initialize Drools Engine: " + e.getMessage());
			e.printStackTrace();
			throw new RuntimeException("Failed to initialize Drools Engine", e);
		}
	}

	private void loadRulesFromPackage(KieFileSystem kieFileSystem, String packageName) {
		try {
			String resourcePath = "rules/" + packageName + "/" + packageName + "-rules.drl";
			kieFileSystem.write(ResourceFactory.newClassPathResource(resourcePath));
			System.out.println("✓ Loaded rules from: " + resourcePath);
		} catch (Exception e) {
			System.err.println("✗ Could not load rules from package: rules." + packageName + " - " + e.getMessage());
		}
	}

	private void testSessionCreation(KieContainer kieContainer) {
		try {
			// Test default session creation
			var session = kieContainer.newKieSession();
			if (session != null) {
				System.out.println("✓ Default session creation test: PASSED");
				int rulesFired = session.fireAllRules();
				System.out.println("✓ Rules fired during test: " + rulesFired);
				session.dispose();
			} else {
				System.out.println("✗ Default session creation test: FAILED");
			}

		} catch (Exception e) {
			System.err.println("✗ Session creation test failed: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
