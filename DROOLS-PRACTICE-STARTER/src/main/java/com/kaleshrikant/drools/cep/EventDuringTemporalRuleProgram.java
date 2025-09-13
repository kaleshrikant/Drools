package com.kaleshrikant.drools.cep;

import com.kaleshrikant.drools.cep.model.SessionWindow;
import com.kaleshrikant.drools.cep.model.UserActionEvent;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.List;

/**
 * 🕒 EventDuringTemporalRuleProgram via Drools CEP
 *
 * 🧾 Detects UserActionEvent occurring within a SessionWindow:
 *    ✅ Fires if action.timestamp ∈ [startTime, endTime]
 *    🧠 Uses @role(event) and @timestamp(timestamp) for temporal matching
 *
 * 📤 Inserts SessionWindow and UserActionEvent into KieSession
 * 📦 Uses logCollector to track rule execution
 * 🧹 Session disposed after rule execution
 *
 * 🖨️ Sample Output:
 *  ✅ Valid action by Shrikant during session window.
 *
 * @author Shrikant Kale
 * @Date 13 Sep 2025
 */

public class EventDuringTemporalRuleProgram {
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
		long now = System.currentTimeMillis();
		kieSession.insert(new SessionWindow(now - 10000, now + 10000)); // 20s window
		kieSession.insert(new UserActionEvent("Shrikant", now)); // occurs during

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
