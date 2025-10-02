package com.kaleshrikant.core.discount;

import com.kaleshrikant.core.discount.model.Customer;
import com.kaleshrikant.core.discount.model.Campaign;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 📋 DiscountEngineProgram - Dynamic Discount Calculation System
 *
 * 🎯 Core Drools functionality demonstration:
 *    📜 Implements business logic through declarative rules
 *    🧠 Pattern matching for complex condition evaluation
 *    🌐 Global variable integration for result collection
 *    💾 Working memory management for fact handling
 *
 * 🧊 Expected Facts: Customer, Campaign
 *
 * 💻▶ Sample Output:
 *  === 📋 Log Output ===
 *      🔔 💎 Shrikant gets 20% discount.
 *      🔔 🛍️ Prashant gets 10% discount.
 *      🔔 🚫 Ravi is not eligible for discount.
 *      🔔 🎁 Shrikant gets additional 5% festive bonus.
 *  🎉 DiscountEngineProgram executed successfully!
 *
 * @author Shrikant Kale
 */
public class DiscountEngineProgram {
	public static void main(String[] args) {
		// 🔧 Initialize KIE Services
		KieServices kieServices = KieServices.Factory.get();
		KieContainer kieContainer = kieServices.getKieClasspathContainer();
		KieSession kieSession = kieContainer.newKieSession("coreDiscountSession");

		// 🌐 Set up global log collector
		List<String> logCollector = new ArrayList<>();
		kieSession.setGlobal("logCollector", logCollector);

		// 🧊 Insert domain facts
		kieSession.insert(new Customer("Shrikant", "PREMIUM", 1200, 150));      // Eligible for 20% + 5% bonus
		kieSession.insert(new Customer("Prashant", "REGULAR", 700, 80));        // Eligible for 10%
		kieSession.insert(new Customer("Ravi", "REGULAR", 300, 20));            // No discount

		kieSession.insert(new Campaign("DIWALI-2025", true));                   // Active campaign

		// 🏃 Fire all rules
		kieSession.fireAllRules();

		// 💻▶ Print collected logs
		System.out.println("=== 📋 Log Output ===");
		for (String log : logCollector) {
			System.out.println("🔔 " + log);
		}

		// 🧹 Clean up session
		kieSession.dispose();
		System.out.println("🎉 DiscountEngineProgram executed successfully!");
	}
}
