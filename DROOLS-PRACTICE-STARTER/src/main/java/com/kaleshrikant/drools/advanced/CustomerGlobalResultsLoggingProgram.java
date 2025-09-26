package com.kaleshrikant.drools.advanced;

import com.kaleshrikant.drools.advanced.model.CustomerInfo;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.KieServices;
import java.util.ArrayList;
import java.util.List;

/**
 * 📜 CustomerGlobalResultsLoggingProgram
 *
 * 🎯 Purpose:
 * Demonstrates Drools **global variables** with CustomerInfo facts to log rule execution.
 *
 * 🧊 Facts:
 * - `CustomerInfo` object with name, age, and premiumMember attributes.
 *
 * ➕ Conditions:
 * - Apply discounts or logging based on premium membership or age.
 *
 * 🔥 Actions:
 * - Update premiumMember status or discount
 * - Log results in `logCollector` global list.
 *
 * 📚 Resources:
 * - DRL file with global and rules.
 *
 * 🧠 Learning:
 * - Understand how globals share objects (like logCollector) between Java and DRL.
 *
 * OUTPUT :
 *  === CustomerInfo Facts After Rules ===
 *      CustomerInfo{name='Shrikant', age=30, premiumMember=true}
 *      CustomerInfo{name='Prashant', age=20, premiumMember=false}
 *
 *  === Log Collector Output ===
 *      ✅ Premium customer: Shrikant
 *      🟡 Young customer: Prashant
 *
 * @author Shrikant Kale
 * @Date 26 Sep 2025
 */

public class CustomerGlobalResultsLoggingProgram {
	public static void main(String[] args) {
		// Step 1: Get KieServices
		KieServices ks = KieServices.Factory.get();

		// Step 2: Create container from classpath
		KieContainer kc = ks.getKieClasspathContainer();

		// Step 3: Create session
		KieSession kieSession = kc.newKieSession("global-logging-session");

		// Step 4: Create global list to collect logs
		List<String> logCollector = new ArrayList<>();
		kieSession.setGlobal("logCollector", logCollector);

		// Step 5: Insert facts
		CustomerInfo c1 = new CustomerInfo("Shrikant", 30, true);
		CustomerInfo c2 = new CustomerInfo("Prashant", 20, false);

		kieSession.insert(c1);
		kieSession.insert(c2);

		// Step 6: Fire rules
		kieSession.fireAllRules();

		// Step 7: Print results
		System.out.println("\n=== CustomerInfo Facts After Rules ===");
		System.out.println(c1);
		System.out.println(c2);

		System.out.println("\n=== Log Collector Output ===");
		logCollector.forEach(System.out::println);

		kieSession.dispose();
	}
}
