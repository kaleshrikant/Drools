package com.kaleshrikant.drools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.kaleshrikant.drools.model.Customer;
import com.kaleshrikant.drools.model.Order;

/**
 * @author Shrikant Kale
 * @Date 28 Sep 2025
 */

@Component
public class HotDeploymentDemo implements CommandLineRunner {

	private final RulesEngineManager rulesEngine;

	// Use constructor injection instead of field injection
	@Autowired
	public HotDeploymentDemo(RulesEngineManager rulesEngine) {
		this.rulesEngine = rulesEngine;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("\n=== Starting Drools Hot Deployment Demo ===\n");

		// Create test customers
		Customer newCustomer = new Customer("Alice", "NEW", 0, false);
		Customer silverCustomer = new Customer("Bob", "SILVER", 500, false);
		Customer goldCustomer = new Customer("Charlie", "GOLD", 1500, true);

		// Test with initial rules
		System.out.println("=== Phase 1: Testing Initial Rules (v" + rulesEngine.getCurrentVersion() + ") ===");
		testScenario("New Customer - Small Order", newCustomer, 200.0);
		testScenario("Silver Customer - Medium Order", silverCustomer, 800.0);
		testScenario("Gold Customer - Large Order", goldCustomer, 1500.0);

		// Simulate rule update
		System.out.println("\n=== Phase 2: Performing Hot Deployment ===");
		boolean deploymentSuccess = rulesEngine.hotDeployNewVersion("2.0.0");

		if (deploymentSuccess) {
			// Test with new rules
			System.out.println("\n=== Phase 3: Testing Enhanced Rules (v" + rulesEngine.getCurrentVersion() + ") ===");
			testScenario("New Customer - Small Order", newCustomer, 200.0);
			testScenario("Silver Customer - Medium Order", silverCustomer, 800.0);
			testScenario("Gold Customer - Large Order", goldCustomer, 1500.0);

			// Test new customer type that only works in v2.0.0
			Customer platinumCustomer = new Customer("Diana", "PLATINUM", 3000, true);
			testScenario("Platinum VIP - Premium Order", platinumCustomer, 2500.0);
		}

		System.out.println("\n=== Demo Completed ===\n");
	}

	private void testScenario(String scenario, Customer customer, double amount) {
		System.out.println("\n--- " + scenario + " ---");
		System.out.println("Customer: " + customer);

		Order order = new Order(amount);
		System.out.println("Initial Order: " + order.getAmount());

		try {
			Order result = rulesEngine.processOrder(order, customer);
			System.out.println("Final Order: " + result.getFinalAmount() +
					" (Discount: " + (result.getDiscount() * 100) + "%)");
			System.out.println("Applied Discounts: " + result.getAppliedDiscounts());
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
}
