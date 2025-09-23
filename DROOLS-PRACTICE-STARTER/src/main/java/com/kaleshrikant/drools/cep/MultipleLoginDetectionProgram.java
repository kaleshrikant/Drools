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
 * MultipleLoginDetectionProgram
 * Detects multiple login attempts by the same user within 10s window.
 *
 * Results:
 * ⚠️ Multiple login detected for user: Shrikant within 10 seconds window!
 * @author Shrikant Kale
 * @date 23 Sep 2025
 */
public class MultipleLoginDetectionProgram {
	public static void main(String[] args) {
		// 1️⃣ KieServices
		KieServices ks = KieServices.Factory.get();
		KieContainer kc = ks.getKieClasspathContainer();
		KieSession ksession = kc.newKieSession("ksession-cep");

		// 2️⃣ Logger
		List<String> logCollector = new ArrayList<>();
		ksession.setGlobal("logCollector", logCollector);

		// 3️⃣ Use pseudo clock
		SessionPseudoClock clock = ksession.getSessionClock();

		// 4️⃣ Insert multiple login events for same user
		ksession.insert(new LoginEvent("Shrikant", clock.getCurrentTime()));
		ksession.fireAllRules();

		// Advance 5 seconds → still inside window
		clock.advanceTime(5, TimeUnit.SECONDS);
		ksession.insert(new LoginEvent("Shrikant", clock.getCurrentTime()));
		ksession.fireAllRules();

		// Advance another 6 seconds → outside window
		clock.advanceTime(6, TimeUnit.SECONDS);
		ksession.insert(new LoginEvent("Shrikant", clock.getCurrentTime()));
		ksession.fireAllRules();

		// 5️⃣ Dispose
		ksession.dispose();

		// 6️⃣ Print results
		System.out.println("Results:");
		logCollector.forEach(System.out::println);
	}
}
