package com.kaleshrikant.drools.cep;

import com.kaleshrikant.drools.cep.model.TemperatureEvent;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.List;

/**
 * 📊 TemperatureSlidingLengthWindowProgram via Drools CEP
 *
 * 🧾 Detects average temperature using `window:length(3)`:
 *    ✅ Fires when average of last 3 TemperatureEvent values exceeds 30°C
 *    🧠 Uses @role(event), @timestamp(timestamp), and accumulate over length window
 *
 * 📤 Inserts TemperatureEvent facts with varying values
 * 📋 Uses Drools length-based window to track last 3 events
 * 🧹 Session disposed after rule execution
 *
 * 🖨️ Sample Output:
 *     📊 Average: 35.0°C in last 3 events
 *     🔥 Rules fired: 1
 *
 * @author Shrikant Kale
 * @Date 17 Sep 2025
 */

public class TemperatureSlidingLengthWindowProgram {
	public static void main(String[] args) throws InterruptedException {
		// 1️⃣ Get KieServices
		KieServices kieServices = KieServices.Factory.get();

		// 2️⃣ Get KieContainer
		KieContainer kieContainer = kieServices.getKieClasspathContainer();

		// 3️⃣ Create KieSession directly
		KieSession kieSession = kieContainer.newKieSession("ksession-cep"); // match name in kmodule.xml

		// 4️⃣ Inject logger into Drools
		List<String> logCollector = new ArrayList<>();
		kieSession.setGlobal("logCollector", logCollector);

		// 5️⃣ Event Insertion
		System.out.println("=== TESTING LENGTH WINDOW (3 events) ===");

		// Insert events that should trigger the rules
		insertEvent(kieSession, 30.0); // Event 1
		insertEvent(kieSession, 35.0); // Event 2 - high
		insertEvent(kieSession, 40.0); // Event 3 - high (window full)
		// Should trigger: High count rule (2 high temps) and Average rule (35 avg)

		insertEvent(kieSession, 25.0); // Event 4 (replaces event 1)
		// Window now: 35, 40, 25 - avg = 33.3 (no trigger)

		insertEvent(kieSession, 38.0); // Event 5 (replaces event 2)
		// Window now: 40, 25, 38 - avg = 34.3 (trigger average rule)

		// 7️⃣ Dispose the session
		kieSession.dispose();

		// 8️⃣ Print results
		System.out.println("\n=== FINAL RESULTS ===");
		logCollector.forEach(System.out::println);
		System.out.println("Total logs: " + logCollector.size());
	}
	private static void insertEvent(KieSession kieSession, double temperature) throws InterruptedException {
		TemperatureEvent event = new TemperatureEvent("sensor-1", temperature);
		kieSession.insert(event);
		System.out.println("Inserted: " + event);

		// 6️⃣ Fire all rules
		int fired = kieSession.fireAllRules();
		if (fired > 0) {
			System.out.println("Rules fired: " + fired);
		}
		Thread.sleep(300);
	}
}
