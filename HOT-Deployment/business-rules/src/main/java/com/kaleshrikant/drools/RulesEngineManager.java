package com.kaleshrikant.drools;

import com.kaleshrikant.drools.model.Customer;
import com.kaleshrikant.drools.model.Order;
import jakarta.annotation.PostConstruct;
import org.kie.api.KieServices;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Component;

/**
 * @author Shrikant Kale
 * @Date 28 Sep 2025
 */

@Component
public class RulesEngineManager {
	private KieContainer kieContainer;
	private KieServices kieServices;
	private String currentVersion = "1.0.0";

	public RulesEngineManager() {
		this.kieServices = KieServices.Factory.get();
	}

	@PostConstruct
	public void initialize() {
		initializeRulesContainer();
	}

	private void initializeRulesContainer() {
		try {
			// Use the current project's groupId and artifactId
			ReleaseId releaseId = kieServices.newReleaseId(
					"com.kaleshrikant", "drools-practice", currentVersion);
			this.kieContainer = kieServices.newKieContainer(releaseId);
			System.out.println("✅ Initialized rules container with version: " + currentVersion);
		} catch (Exception e) {
			System.err.println("❌ Failed to initialize rules container: " + e.getMessage());
			// Fallback: create an empty container
			this.kieContainer = kieServices.getKieClasspathContainer();
			System.out.println("✅ Using fallback classpath container");
		}
	}

	public Order processOrder(Order order, Customer customer) {
		if (kieContainer == null) {
			throw new IllegalStateException("Rules engine not initialized");
		}

		KieSession session = null;
		try {
			session = kieContainer.newKieSession("discountSession");
			session.insert(customer);
			session.insert(order);
			int rulesFired = session.fireAllRules();
			System.out.println("Fired " + rulesFired + " rules");

			System.out.println("Processed order: " + order.getOrderId() +
					" | Customer: " + customer.getLevel() +
					" | Discount: " + (order.getDiscount() * 100) + "%");
			return order;
		} catch (Exception e) {
			System.err.println("Error processing order: " + e.getMessage());
			// Return order without processing
			return order;
		} finally {
			if (session != null) {
				session.dispose();
			}
		}
	}

	public boolean hotDeployNewVersion(String newVersion) {
		System.out.println("Attempting hot deployment to version: " + newVersion);

		try {
			// 1. Create new ReleaseId
			ReleaseId newReleaseId = kieServices.newReleaseId(
					"com.kaleshrikant", "drools-practice", newVersion);

			// 2. Create and validate new container
			// Test the new container first
			KieContainer testContainer = kieServices.newKieContainer(newReleaseId);
			if (validateNewContainer(testContainer)) {
				// 3. Dispose old container
				// Close old container and switch to new one
				if (this.kieContainer != null) {
					this.kieContainer.dispose();
				}
				// 4. Switch to new container
				this.kieContainer = testContainer;
				this.currentVersion = newVersion;

				System.out.println("✅ Hot deployment successful! Now using version: " + newVersion);
				return true;
			} else {
				System.err.println("❌ Validation failed - keeping version: " + currentVersion);
				return false;
			}
		} catch (Exception e) {
			System.err.println("❌ Hot deployment failed: " + e.getMessage());
			// Try to use classpath as fallback
			this.kieContainer = kieServices.getKieClasspathContainer();
			System.out.println("✅ Using classpath container as fallback");
			return false;
		}
	}

	private boolean validateNewContainer(KieContainer container) {
		try {
			// Simple validation - try to create a session
			KieSession testSession = container.newKieSession("discountSession");
			testSession.dispose();
			return true;
		} catch (Exception e) {
			System.err.println("Container validation failed: " + e.getMessage());
			return false;
		}
	}

	public String getCurrentVersion() {
		return currentVersion;
	}
}