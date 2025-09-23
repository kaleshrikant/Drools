package com.kaleshrikant.drools.cep;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.time.SessionPseudoClock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
/**
 * HeartbeatTimerRuleProgram
 * Demonstrates Drools timer rule that emits heartbeat every 5 seconds.
 *
 * ⏰ Starting Pseudo-clock Heartbeat Demo
 * 🔧 Using eventProcessingMode=stream, clockType=pseudo
 * ----------------------------------------
 * ⏰ Time advanced to: 5000ms (5 seconds)
 * 🔥 Rules fired: 1
 * ❤️ Heartbeat at session time: 5000ms
 * ---
 * ⏰ Time advanced to: 10000ms (10 seconds)
 * 🔥 Rules fired: 1
 * ❤️ Heartbeat at session time: 10000ms
 * ---
 * ⏰ Time advanced to: 15000ms (15 seconds)
 * 🔥 Rules fired: 1
 * ❤️ Heartbeat at session time: 15000ms
 * ---
 * ⏰ Time advanced to: 20000ms (20 seconds)
 * 🔥 Rules fired: 1
 * ❤️ Heartbeat at session time: 20000ms
 * ---
 * ----------------------------------------
 * 📊 Final Results:
 * ❤️ Heartbeat at session time: 5000ms
 * ❤️ Heartbeat at session time: 10000ms
 * ❤️ Heartbeat at session time: 15000ms
 * ❤️ Heartbeat at session time: 20000ms
 *
 * @author Shrikant Kale
 * @date 23 Sep 2025
 */
public class HeartbeatTimerRuleProgram {
	public static void main(String[] args) throws InterruptedException {
		// 1️⃣ Get KieServices & KieContainer
		KieServices kieServices = KieServices.Factory.get();
		KieContainer kieContainer = kieServices.getKieClasspathContainer();

		// 2️⃣ Create CEP session with pseudo-clock
		KieSession kieSession = kieContainer.newKieSession("ksession-cep");

		// 3️⃣ Log collector
		List<String> logCollector = new ArrayList<>();
		kieSession.setGlobal("logCollector", logCollector);

		// 4️⃣ Get pseudo-clock
		SessionPseudoClock clock = kieSession.getSessionClock();

		System.out.println("⏰ Starting Pseudo-clock Heartbeat Demo");
		System.out.println("🔧 Using eventProcessingMode=stream, clockType=pseudo");
		System.out.println("----------------------------------------");

		// 5️⃣ Manual time simulation - advance time in 5-second increments
		for (int i = 0; i < 4; i++) {
			// Advance time by 5 seconds
			clock.advanceTime(5, TimeUnit.SECONDS);
			long currentTime = clock.getCurrentTime();

			System.out.println("⏰ Time advanced to: " + currentTime + "ms (" + (i + 1) * 5 + " seconds)");

			// Fire rules to process the time advancement
			int firedRules = kieSession.fireAllRules();
			System.out.println("🔥 Rules fired: " + firedRules);

			// Print any heartbeat messages
			printLogs(logCollector);

			System.out.println("---");

			// Brief pause to see the output
			Thread.sleep(1000);
		}

		// 6️⃣ Dispose session
		kieSession.dispose();

		// 7️⃣ Print final results
		System.out.println("----------------------------------------");
		System.out.println("📊 Final Results:");
		if (logCollector.isEmpty()) {
			System.out.println("❌ No heartbeats detected - check timer configuration");
		} else {
			logCollector.forEach(System.out::println);
		}
	}

	private static void printLogs(List<String> logCollector) {
		if (!logCollector.isEmpty()) {
			logCollector.forEach(System.out::println);
			logCollector.clear();
		} else {
			System.out.println("   (no heartbeats this cycle)");
		}
	}
}