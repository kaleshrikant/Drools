package com.kaleshrikant.drools;

import com.kaleshrikant.drools.model.Customer;
import com.kaleshrikant.drools.model.Order;

/**
 * 🚀 Starting Drools Embedded Hot Deployment Demo
 * ================================================
 * Initializing Drools rules engine...
 * 13:42:23.837 [main] WARN org.kie.maven.integration.embedder.MavenSettings -- Environment variable M2_HOME is not set
 * 13:42:25.118 [main] WARN org.drools.compiler.kie.builder.impl.KieProject -- No files found for KieBase defaultKieBase
 * ✅ Knowledge base built successfully!
 * 🔍 File watcher started - monitoring for rule changes every 2 seconds
 *
 * 🧪 TEST CASE 1: VIP Customer with Regular Order
 * Before rules: Order{id=101, customerId=1, total=500.00, discount=0%, final=500.00, reason='null', rules=[]}
 * 13:42:25.218 [main] INFO org.drools.compiler.kie.builder.impl.KieContainerImpl -- Start creation of KieBase: defaultKieBase
 * 13:42:25.298 [main] INFO org.drools.compiler.kie.builder.impl.KieContainerImpl -- End creation of KieBase: defaultKieBase
 * 📋 Inserting facts into session:
 *   - Customer{id=1, name='Alice Johnson', type='VIP', years=3}
 *   - Order{id=101, customerId=1, total=500.00, discount=0%, final=500.00, reason='null', rules=[]}
 * 🔄 Firing all rules...
 * ✅ Rules executed: 0 rules fired
 * After rules:  Order{id=101, customerId=1, total=500.00, discount=0%, final=500.00, reason='null', rules=[]}
 *
 * 🧪 TEST CASE 2: Regular Customer with Bulk Order
 * Before rules: Order{id=102, customerId=2, total=1500.00, discount=0%, final=1500.00, reason='null', rules=[]}
 * 📋 Inserting facts into session:
 *   - Customer{id=2, name='Bob Smith', type='REGULAR', years=2}
 *   - Order{id=102, customerId=2, total=1500.00, discount=0%, final=1500.00, reason='null', rules=[]}
 * 🔄 Firing all rules...
 * ✅ Rules executed: 0 rules fired
 * After rules:  Order{id=102, customerId=2, total=1500.00, discount=0%, final=1500.00, reason='null', rules=[]}
 *
 * 🧪 TEST CASE 3: Loyal Customer (5+ years)
 * Before rules: Order{id=103, customerId=3, total=300.00, discount=0%, final=300.00, reason='null', rules=[]}
 * 📋 Inserting facts into session:
 *   - Customer{id=3, name='Charlie Brown', type='REGULAR', years=7}
 *   - Order{id=103, customerId=3, total=300.00, discount=0%, final=300.00, reason='null', rules=[]}
 * 🔄 Firing all rules...
 * ✅ Rules executed: 0 rules fired
 * After rules:  Order{id=103, customerId=3, total=300.00, discount=0%, final=300.00, reason='null', rules=[]}
 *
 * ⏰ WAITING FOR RULE CHANGES...
 * 💡 TIP: Go modify the file 'src/main/resources/rules/discount-rules.drl'
 *     - Try changing discount percentages
 *     - Add new rules
 *     - Save the file and watch hot deployment in action!
 * ⏱️  Waiting 60 seconds for you to make changes...
 *
 * 🔄 TESTING AFTER POTENTIAL HOT DEPLOYMENT
 * Before rules: Order{id=104, customerId=1, total=800.00, discount=0%, final=800.00, reason='null', rules=[]}
 * 📋 Inserting facts into session:
 *   - Customer{id=1, name='Alice Johnson', type='VIP', years=3}
 *   - Order{id=104, customerId=1, total=800.00, discount=0%, final=800.00, reason='null', rules=[]}
 * 🔄 Firing all rules...
 * ✅ Rules executed: 0 rules fired
 * After rules:  Order{id=104, customerId=1, total=800.00, discount=0%, final=800.00, reason='null', rules=[]}
 *
 * 🏁 Demo completed!
 * 📴 File watcher stopped
 * 🗑️ Knowledge base disposed
 *
 * @author Shrikant Kale
 * @Date 28 Sep 2025
 */

public class EmbeddedApplication {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("🚀 Starting Drools Embedded Hot Deployment Demo");
		System.out.println("================================================");

		HotDeploymentManager ruleManager = new HotDeploymentManager();

		// Test Case 1: VIP Customer
		System.out.println("\n🧪 TEST CASE 1: VIP Customer with Regular Order");
		Customer vipCustomer = new Customer(1L, "Alice Johnson", "VIP", 3);
		Order order1 = new Order(101L, 1L, 500.0);

		System.out.println("Before rules: " + order1);
		ruleManager.executeRules(vipCustomer, order1);
		System.out.println("After rules:  " + order1);

		// Test Case 2: Regular Customer with Bulk Order
		System.out.println("\n🧪 TEST CASE 2: Regular Customer with Bulk Order");
		Customer regularCustomer = new Customer(2L, "Bob Smith", "REGULAR", 2);
		Order order2 = new Order(102L, 2L, 1500.0);

		System.out.println("Before rules: " + order2);
		ruleManager.executeRules(regularCustomer, order2);
		System.out.println("After rules:  " + order2);

		// Test Case 3: Loyal Customer
		System.out.println("\n🧪 TEST CASE 3: Loyal Customer (5+ years)");
		Customer loyalCustomer = new Customer(3L, "Charlie Brown", "REGULAR", 7);
		Order order3 = new Order(103L, 3L, 300.0);

		System.out.println("Before rules: " + order3);
		ruleManager.executeRules(loyalCustomer, order3);
		System.out.println("After rules:  " + order3);

		// Wait for manual rule changes
		System.out.println("\n⏰ WAITING FOR RULE CHANGES...");
		System.out.println("💡 TIP: Go modify the file 'src/main/resources/rules/discount-rules.drl'");
		System.out.println("    - Try changing discount percentages");
		System.out.println("    - Add new rules");
		System.out.println("    - Save the file and watch hot deployment in action!");
		System.out.println("⏱️  Waiting 60 seconds for you to make changes...");

		Thread.sleep(60000); // Wait 60 seconds

		// Test after hot deployment
		System.out.println("\n🔄 TESTING AFTER POTENTIAL HOT DEPLOYMENT");
		Order order4 = new Order(104L, 1L, 800.0); // VIP customer again
		System.out.println("Before rules: " + order4);
		ruleManager.executeRules(vipCustomer, order4);
		System.out.println("After rules:  " + order4);

		System.out.println("\n🏁 Demo completed!");
		ruleManager.shutdown();
	}
}
