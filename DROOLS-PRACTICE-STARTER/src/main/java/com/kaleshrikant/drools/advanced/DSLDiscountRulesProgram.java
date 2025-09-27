package com.kaleshrikant.drools.advanced;

import com.kaleshrikant.drools.advanced.model.BuyerProfile;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 🎯 Purpose: Demonstrates Drools Domain Specific Language (DSL) for discount rules.
 * 🧊 Facts: BuyerProfile (age, membershipType)
 * ➕ Conditions: Human-readable DSL phrases like "When a buyer is GOLD member"
 * 🔥 Actions: Apply discount and log results
 * 📂 Resources: rules/discount-rules.dsl, rules/discount-rules.dsl
 * 📘 Learning: Shows how DSL simplifies rule authoring for business users.
 *
 * OUTPUT :
 *  === Final Buyer Profiles ===
 *  BuyerProfile{name='Shrikant', age=30, membershipType='GOLD', discount=20%}
 *  BuyerProfile{name='Prashant', age=22, membershipType='SILVER', discount=10%}
 *
 * === Rule Logs ===
 *  🏆 GOLD discount applied to Shrikant
 *  🥈 SILVER discount applied to Prashant
 *
 * @author Shrikant Kale
 * @Date 27 Sep 2025
 */

public class DSLDiscountRulesProgram {
	public static void main(String[] args) {
		try {
			// 🔹 Setup Drools
			KieServices ks = KieServices.Factory.get();
			KieContainer kc = ks.getKieClasspathContainer();
			KieSession ksession = kc.newKieSession("dslSession");

			// 🔹 Global list for logging
			List<String> logCollector = new ArrayList<>();
			ksession.setGlobal("logCollector", logCollector);

			// 🔹 Insert facts
			BuyerProfile buyer1 = new BuyerProfile("Shrikant", 30, "GOLD");
			BuyerProfile buyer2 = new BuyerProfile("Prashant", 22, "SILVER");

			ksession.insert(buyer1);
			ksession.insert(buyer2);

			// 🔹 Fire rules
			ksession.fireAllRules();

			// 🔹 Print results
			System.out.println("\n=== Final Buyer Profiles ===");
			System.out.println(buyer1);
			System.out.println(buyer2);

			System.out.println("\n=== Rule Logs ===");
			logCollector.forEach(System.out::println);

			ksession.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
