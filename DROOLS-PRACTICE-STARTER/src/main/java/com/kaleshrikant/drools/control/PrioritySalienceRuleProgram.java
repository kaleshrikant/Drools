package com.kaleshrikant.drools.control;

import com.kaleshrikant.drools.control.model.Patron;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Scenario
 * We have multiple rules:
 *      🥇 High-priority rule: logs premium Patron
 *      🥈 Medium-priority rule: applies discount
 *      🥉 Low-priority rule: sends notification
 * Use salience to ensure they fire in the correct order.
 *
 * @author Shrikant Kale
 * @Date 12 Sep 2025
 */

public class PrioritySalienceRuleProgram {
	private static final Logger logger = LoggerFactory.getLogger(PrioritySalienceRuleProgram.class);
	public static void main(String[] args) {
		// 1️⃣ Get KieServices
		KieServices kieServices = KieServices.Factory.get();

		// 1️⃣ Get KieContainer
		KieContainer kieContainer = kieServices.getKieClasspathContainer();

		// 2️⃣ Create KieSession directly
		KieSession kieSession = kieContainer.newKieSession("ksession-rules"); // match name in kmodule.xml

		// Inject logger into Drools
		kieSession.setGlobal("logger", logger);

		// 2️⃣ Fact Insertion
		kieSession.insert(new Patron("Shrikant", true, 12000));
		kieSession.insert(new Patron("Meera", false, 8000));

		// 3️⃣ Fire all rules
		kieSession.fireAllRules();

		// 4️⃣ Dispose the session
		kieSession.dispose();
	}
}
