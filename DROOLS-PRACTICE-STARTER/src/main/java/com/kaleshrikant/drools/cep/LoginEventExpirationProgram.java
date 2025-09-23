package com.kaleshrikant.drools.cep;

import com.kaleshrikant.drools.cep.model.LoginEvent;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.time.SessionPseudoClock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * === Testing @expires(10s) ===
 * ✅ Inserted event at time: 0
 *
 * 🔥 Firing rules at time 0:
 * 🔑 ACTIVE: LoginEvent for Shrikant | Age: 0ms
 * ✅ Event valid: 0ms old
 * 🔢 Events in memory: 1
 *
 * ⏰ Advanced to: 5000ms (5 seconds)
 * 🔥 Firing rules at 5 seconds:
 * 🔑 ACTIVE: LoginEvent for Shrikant | Age: 5000ms
 * ✅ Event valid: 5000ms old
 * 🔢 Events in memory: 1
 *
 * ⏰ Advanced to: 11000ms (11 seconds - PAST EXPIRATION)
 * 🔥 Firing rules at 11 seconds:
 * 🔑 ACTIVE: LoginEvent for Shrikant | Age: 11000ms
 * ❌ Event expired: 11000ms old (should be auto-removed)
 * 🔢 Events in memory: 1
 *
 * 🔥 Final rule firing:
 * 🎉 SUCCESS: @expires(10s) worked! All events removed automatically
 *
 * @author Shrikant Kale
 * @Date 18 Sep 2025
 */

public class LoginEventExpirationProgram {
	public static void main(String[] args) {
		try {
			KieServices kieServices = KieServices.Factory.get();
			KieContainer kieContainer = kieServices.getKieClasspathContainer();
			KieSession kieSession = kieContainer.newKieSession("ksession-cep");

			List<String> logCollector = new ArrayList<>();
			kieSession.setGlobal("logCollector", logCollector);

			SessionPseudoClock clock = kieSession.getSessionClock();

			System.out.println("=== Testing @expires(10s) ===");

			// 1️⃣ Insert login event at time 0
			LoginEvent loginEvent = new LoginEvent("Shrikant", clock.getCurrentTime());
			kieSession.insert(loginEvent);
			System.out.println("✅ Inserted event at time: " + clock.getCurrentTime());

			// 2️⃣ Fire rules immediately
			System.out.println("\n🔥 Firing rules at time 0:");
			kieSession.fireAllRules();
			printLogs(logCollector);

			// 3️⃣ Advance to 5 seconds (within expiration)
			clock.advanceTime(5, TimeUnit.SECONDS);
			System.out.println("\n⏰ Advanced to: " + clock.getCurrentTime() + "ms (5 seconds)");
			System.out.println("🔥 Firing rules at 5 seconds:");
			kieSession.fireAllRules();
			printLogs(logCollector);

			// 4️⃣ Advance to 11 seconds (PAST expiration)
			clock.advanceTime(6, TimeUnit.SECONDS);
			System.out.println("\n⏰ Advanced to: " + clock.getCurrentTime() + "ms (11 seconds - PAST EXPIRATION)");
			System.out.println("🔥 Firing rules at 11 seconds:");
			int fired = kieSession.fireAllRules();
			System.out.println("Rules fired: " + fired);
			printLogs(logCollector);

			// 5️⃣ Fire one more time to trigger cleanup rule
			System.out.println("\n🔥 Final rule firing:");
			kieSession.fireAllRules();
			printLogs(logCollector);

			kieSession.dispose();

		} catch (Exception e) {
			System.err.println("❌ Error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private static void printLogs(List<String> logCollector) {
		if (!logCollector.isEmpty()) {
			logCollector.forEach(System.out::println);
			logCollector.clear();
		} else {
			System.out.println("   No new logs");
		}
	}
}
