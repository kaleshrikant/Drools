package com.kaleshrikant.drools.core;

import com.kaleshrikant.drools.core.model.Citizen;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
/**
 * This program uses the Drools rule engine to classify a list of Citizen objects based on their age into three categories:
 *      JUNIOR
 *      ADULT
 *      SENIOR.
 *
 * @author Shrikant Kale
 * @Date 9/11/25
 */

public class AdultCheckRuleProgram {
	public static void main(String[] args) {
		// 1️⃣ Get KieServices
		KieServices kieServices = KieServices.Factory.get();

		// 1️⃣ Get KieContainer
		KieContainer kieContainer = kieServices.getKieClasspathContainer();

		// 2️⃣ Create KieSession directly
		KieSession kieSession = kieContainer.newKieSession("ksession-rules"); // match name in kmodule.xml

		// 2️⃣ Fact Insertion
		kieSession.insert(new Citizen("Shrikant", 34));
		kieSession.insert(new Citizen("Prashant", 14));
		kieSession.insert(new Citizen("Bob", 15));
		kieSession.insert(new Citizen("Charlie", 65));

		// 3️⃣ Fire all rules
		kieSession.fireAllRules();

		// 4️⃣ Dispose the session
		kieSession.dispose();
	}
}
