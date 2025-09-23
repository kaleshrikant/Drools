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
 * WorkingHoursCalendarRuleProgram
 * Detects login events that occur outside defined working hours (9 AM – 6 PM).
 *
 * Results:
 *  ✅ Login within working hours for user: Shrikant at 1758190000000
 *  ⏰ Login outside working hours detected for user: Shrikant at 1758228000000 *
 *
 * @author Shrikant Kale
 * @date 23 Sep 2025
 */
public class WorkingHoursCalendarRuleProgram {
	public static void main(String[] args) {
		// 1️⃣ KieServices
		KieServices ks = KieServices.Factory.get();
		KieContainer kc = ks.getKieClasspathContainer();
		KieSession ksession = kc.newKieSession("ksession-cep");

		// 2️⃣ Logger
		List<String> logCollector = new ArrayList<>();
		ksession.setGlobal("logCollector", logCollector);

		// 3️⃣ Pseudo clock
		SessionPseudoClock clock = ksession.getSessionClock();

		// 4️⃣ Insert login events with different times
		ksession.insert(new LoginEvent("Shrikant", System.currentTimeMillis())); // current time
		ksession.fireAllRules();

		// Advance time 8 hours → simulate login outside working hours
		clock.advanceTime(8, TimeUnit.HOURS);
		ksession.insert(new LoginEvent("Shrikant", clock.getCurrentTime()));
		ksession.fireAllRules();

		// 5️⃣ Dispose
		ksession.dispose();

		// 6️⃣ Print results
		System.out.println("Results:");
		logCollector.forEach(System.out::println);
	}
}
