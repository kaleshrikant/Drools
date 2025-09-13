package com.kaleshrikant.drools.cep;

import com.kaleshrikant.drools.cep.model.LoginEvent2;
import com.kaleshrikant.drools.cep.model.PurchaseEvent2;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.List;

/**
 * 📡 EventDuringTemporalRuleProgram2 via Drools CEP
 *
 * 🧾 Detects PurchaseEvent2 occurring DURING LoginEvent2 session:
 *    🎯 Uses true CEP with temporal operator `during`
 *    🧠 LoginEvent2 is an interval event (@duration)
 *    🛍️ PurchaseEvent2 is a point event (@timestamp)
 *
 * 📦 Requires eventProcessingMode="stream" in kmodule.xml
 * 📤 Inserts LoginEvent2 and PurchaseEvent2 into KieSession
 * 📋 Uses logCollector to track rule execution
 * 🧹 Session disposed after rule execution
 *
 * 🖨️ Sample Output:
 *  🎯 TRUE CEP: Shrikant purchased DURING login session using Drools temporal operator
 *
 * @author Shrikant Kale
 * @Date 13 Sep 2025
 */

public class EventDuringTemporalRuleProgram2 {
	public static void main(String[] args) {
		// 1️⃣ Get KieServices
		KieServices kieServices = KieServices.Factory.get();

		// 2️⃣ Get KieContainer
		KieContainer kieContainer = kieServices.getKieClasspathContainer();

		// 3️⃣ Create KieSession directly
		KieSession kieSession = kieContainer.newKieSession("ksession-rules"); // match name in kmodule.xml

		// 4️⃣ Inject logger into Drools
		List<String> logCollector = new ArrayList<>();
		kieSession.setGlobal("logCollector", logCollector);

		long now = System.currentTimeMillis();
				// Login event with duration (interval event)
					LoginEvent2 login = new LoginEvent2("Shrikant", now, 30 * 60 * 1000);

				// Purchase event (point event)
					PurchaseEvent2 purchase = new PurchaseEvent2("Shrikant", now + 10 * 60 * 1000);

		// 5️⃣ Event Insertion
		kieSession.insert(login);
		kieSession.insert(purchase);

		// 6️⃣ Fire all rules
		kieSession.fireAllRules();

		// 7️⃣ Dispose the session
		kieSession.dispose();

		// 8️⃣ Print results
		System.out.println("Results:");
		if (logCollector.isEmpty()) {
			System.out.println("No rules fired.");
		} else {
			logCollector.forEach(System.out::println);
		}
	}
}
