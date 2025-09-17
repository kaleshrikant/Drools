package com.kaleshrikant.drools.cep;

import com.kaleshrikant.drools.cep.model.TemperatureEvent;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.List;

/**
 * 🌡️ TemperatureSlidingTimeWindowProgram via Drools CEP
 *
 * 🧾 Detects temperature spikes using `window:time(5s)`:
 *    ✅ Fires when multiple TemperatureEvent values > 37°C occur within a 5-second window
 *    🧠 Uses @role(event), @timestamp(timestamp), and accumulate over time window
 *
 * 📤 Inserts 4 TemperatureEvent facts spaced 1s apart
 * 📋 Uses Drools pseudo clock for controlled event timing
 * 🧹 Session disposed after rule execution
 *
 * 🖨️ Sample Output:
 *      🚨 ALERT: 3 high temperature readings in last 5s window!
 *      🔥 Rules fired: 1
 *
 * @author Shrikant Kale
 * @Date 17 Sep 2025
 */

public class TemperatureSlidingTimeWindowProgram {
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
		kieSession.insert(new TemperatureEvent("Sensor-1", 35));
		Thread.sleep(1000);

		kieSession.insert(new TemperatureEvent("Sensor-1", 38));
		Thread.sleep(1000);

		kieSession.insert(new TemperatureEvent("Sensor-1", 40));
		Thread.sleep(1000);

		kieSession.insert(new TemperatureEvent("Sensor-1", 42));

		// 6️⃣ Fire all rules
		int fired = kieSession.fireAllRules();


		// 7️⃣ Dispose the session
		kieSession.dispose();

		// 8️⃣ Print results
		System.out.println("🔥 Rules fired: " + fired);
	}
}
