package com.kaleshrikant.drools.sessions;

import com.kaleshrikant.drools.sessions.model.Item;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.List;

/**
 * 🎯 Purpose: Demonstrates creation and use of multiple KieBases and KieSessions
 * 🧊 Facts: Item (name, category, price)
 * ➕ Conditions: Apply discounts based on category
 * 🔥 Actions: Update price and log results
 * 📂 Resources: rules/KieBaseSetup.drl
 * 📘 Learning: Shows separation of rules into KieBases and execution via KieSessions
 *
 * === Expected Output ===
 * Original Items:
 * Item{name='Laptop', category='Electronics', price=1000.0}
 * Item{name='Apple', category='Groceries', price=2.5}
 *
 * After Rules Fired:
 * Item{name='Laptop', category='Electronics', price=900.0}
 * Item{name='Apple', category='Groceries', price=2.25}
 *
 * Rule Logs:
 * 💻 Electronics discount applied to Laptop
 * 🍎 Groceries discount applied to Apple
 *
 * ✅ Summary Flow
 * [Define Facts] → [Insert into KieSession] → [fireAllRules] → [Facts Updated] → [Logs Captured]
 *
 * @author Shrikant Kale
 * @Date 27 Sep 2025
 */

public class KieBaseSetupProgram {
	public static void main(String[] args) {
		try {
			// 🔹 Setup Drools
			KieServices ks = KieServices.Factory.get();
			KieContainer kc = ks.getKieClasspathContainer();

			// 🔹 Create KieSession from a specific KieBase
			KieSession session = kc.newKieSession("discountKSession");

			// 🔹 Global log collector
			List<String> logCollector = new ArrayList<>();
			session.setGlobal("logCollector", logCollector);

			// 🔹 Insert Item facts
			Item laptop = new Item("Laptop", "Electronics", 1000.0);
			Item apple = new Item("Apple", "Groceries", 2.5);

			session.insert(laptop);
			session.insert(apple);

			System.out.println("=== Original Items ===");
			System.out.println(laptop);
			System.out.println(apple);

			// 🔹 Fire rules
			session.fireAllRules();

			// 🔹 Print results
			System.out.println("\n=== Items After Rules Fired ===");
			System.out.println(laptop);
			System.out.println(apple);

			System.out.println("\n=== Rule Logs ===");
			logCollector.forEach(System.out::println);

			session.dispose();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
