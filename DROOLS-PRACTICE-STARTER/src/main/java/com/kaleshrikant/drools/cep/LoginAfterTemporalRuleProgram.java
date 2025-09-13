package com.kaleshrikant.drools.cep;

import com.kaleshrikant.drools.cep.model.LoginEvent;
import com.kaleshrikant.drools.cep.model.PurchaseEvent;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

/**
 * ⏱️ LoginAfterTemporalRuleProgram via Drools CEP
 *
 * 🧾 Detects purchase made within 15 minutes *after* login:
 *    ✅ Fires only if PurchaseEvent timestamp > LoginEvent timestamp
 *    🧠 Uses @role(event) and @timestamp(timestamp) for time-aware reasoning
 *    🔁 Uses retract() to prevent duplicate rule firings
 *
 * 📤 Inserts LoginEvent and PurchaseEvent with future timestamps
 * 📦 Uses logCollector to track rule execution
 * 🧹 Session disposed after rule execution
 *
 * 🖨️ Sample Output:
 *  ✅ Rule fired: Shrikant made purchase AFTER login.
 *
 * @author Shrikant Kale
 * @Date 13 Sep 2025
 */

public class LoginAfterTemporalRuleProgram {
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

		// 5️⃣ Event Insertion
		LocalDateTime now = LocalDateTime.now();
		long loginTs = now.plusMinutes(5).toInstant(ZoneOffset.UTC).toEpochMilli();
		long purchaseTs = now.plusMinutes(10).toInstant(ZoneOffset.UTC).toEpochMilli();

		LoginEvent login = new LoginEvent("Shrikant", loginTs);
		PurchaseEvent purchase = new PurchaseEvent("Shrikant", purchaseTs);

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
