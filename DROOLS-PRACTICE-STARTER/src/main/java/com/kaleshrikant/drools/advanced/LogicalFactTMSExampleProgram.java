package com.kaleshrikant.drools.advanced;

import com.kaleshrikant.drools.advanced.model.Product;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.List;

/**
 * 🎯 Purpose: Demonstrates Drools Truth Maintenance System (TMS) with logical facts
 * 🧊 Facts: Product (name, category, discount, applied)
 * ➕ Conditions: Apply discounts based on category
 * 🔥 Actions: Insert logical discount facts using insertLogical()
 * 📂 Resources: rules/logical-facts-tms.drl
 * 📘 Learning: Shows automatic retraction of logical facts when conditions no longer hold
 *
 *  Output
 *  === Initial Products ===
 *  Product{name='Laptop', category='Electronics', discount=0%, applied=false}
 *  Product{name='TV', category='Electronics', discount=0%, applied=false}
 *  Product{name='Apple', category='Groceries', discount=0%, applied=false}
 *
 * === Products After Logical Fact Rules Fired ===
 *  Product{name='Laptop', category='Electronics', discount=0%, applied=true}
 *  Product{name='TV', category='Electronics', discount=0%, applied=true}
 *  Product{name='Apple', category='Groceries', discount=0%, applied=true}
 *
 *  === Rule Logs ===
 *  🟢 Electronics discount applied to Laptop
 *  🟢 Electronics discount applied to TV
 *  🟢 Groceries discount applied to Apple
 *
 * @author Shrikant Kale
 * @Date 27 Sep 2025
 */

public class LogicalFactTMSExampleProgram {
	public static void main(String[] args) {
		try {
			// 🔹 Setup Drools
			KieServices ks = KieServices.Factory.get();
			KieContainer kc = ks.getKieClasspathContainer();
			KieSession ksession = kc.newKieSession("advancedSession");

			// 🔹 Global log collector
			List<String> logCollector = new ArrayList<>();
			ksession.setGlobal("logCollector", logCollector);

			// 🔹 Insert initial Product facts
			Product p1 = new Product("Laptop", "Electronics");
			Product p2 = new Product("TV", "Electronics");
			Product p3 = new Product("Apple", "Groceries");

			ksession.insert(p1);
			ksession.insert(p2);
			ksession.insert(p3);

			System.out.println("=== Initial Products ===");
			System.out.println(p1);
			System.out.println(p2);
			System.out.println(p3);

			// 🔹 Fire rules
			ksession.fireAllRules();

			// 🔹 Print results
			System.out.println("\n=== Products After Logical Fact Rules Fired ===");
			System.out.println(p1);
			System.out.println(p2);
			System.out.println(p3);

			System.out.println("\n=== Rule Logs ===");
			logCollector.forEach(System.out::println);

			ksession.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
