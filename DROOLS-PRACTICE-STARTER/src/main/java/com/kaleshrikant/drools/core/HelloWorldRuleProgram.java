package com.kaleshrikant.drools.core;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * ☕ Hello World Rule via Drools
 *
 *      🧾 Fires unconditionally using eval(true)
 *      ✅ Prints greeting to console: "Hello, Drools World from core!"
 *      🔥 Useful for testing rule engine setup
 *
 * @author Shrikant Kale
 * @Date 9/11/25
 */

public class HelloWorldRuleProgram {
	public static void main(String[] args) {
		// 1️⃣ Get KieServices and KieContainer
		KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();

		// 2️⃣ Create KieSession directly
		KieSession kieSession = kieContainer.newKieSession("ksession-rules"); // match name in kmodule.xml

		// 3️⃣ Fire all rules
		kieSession.fireAllRules();

		// 4️⃣ Dispose the session
		kieSession.dispose();

	}
}
