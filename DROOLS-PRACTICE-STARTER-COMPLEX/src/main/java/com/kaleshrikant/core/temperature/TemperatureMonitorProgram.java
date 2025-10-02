package com.kaleshrikant.core.temperature;

import com.kaleshrikant.core.temperature.model.TemperatureReading;
import com.kaleshrikant.core.temperature.model.SensorReading;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 📋 TemperatureMonitorProgram - Temperature Monitoring and Alert System
 *
 * 🎯 Core Drools functionality demonstration:
 *    📜 Implements business logic through declarative rules
 *    🧠 Pattern matching for complex condition evaluation
 *    🌐 Global variable integration for result collection
 *    💾 Working memory management for fact handling
 *
 * 🧊 Expected Facts: TemperatureReading, SensorReading
 *
 * 💻▶ Sample Output:
 *   === 📋 Log Output ===
 *      🔔 🔥 High temperature detected: 45.0°C
 *      🔔 🌡️ Converted 45.0°C to 113.0°F
 *      🔔 🌡️ Converted 28.0°C to 82.4°F
 *      🔔 ✅ Sensor and temperature readings validated at 2025-10-02T12:58
 *  🎉 TemperatureMonitorProgram executed successfully!
 *
 * @author Shrikant Kale
 */

public class TemperatureMonitorProgram {
	public static void main(String[] args) {
		// 🔧 Initialize KIE Services
		KieServices kieServices = KieServices.Factory.get();
		KieContainer kieContainer = kieServices.getKieClasspathContainer();
		KieSession kieSession = kieContainer.newKieSession("coreTemperatureSession");

		// 🌐 Set up global log collector
		List<String> logCollector = new ArrayList<>();
		kieSession.setGlobal("logCollector", logCollector);

		// 🧊 Insert domain facts
		kieSession.insert(new TemperatureReading("C", 45.0, "2025-10-02T12:58")); // High temp
		kieSession.insert(new SensorReading(44.0, "2025-10-02T12:58"));           // Sensor match
		kieSession.insert(new TemperatureReading("C", 28.0, "2025-10-02T13:00")); // Normal temp

		// 🏃 Fire all rules
		kieSession.fireAllRules();

		// 💻▶ Print collected logs
		System.out.println("=== 📋 Log Output ===");
		for (String log : logCollector) {
			System.out.println("🔔 " + log);
		}

		// 🧹 Clean up session
		kieSession.dispose();
		System.out.println("🎉 TemperatureMonitorProgram executed successfully!");
	}
}
