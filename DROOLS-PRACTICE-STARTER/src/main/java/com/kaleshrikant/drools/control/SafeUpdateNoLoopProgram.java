package com.kaleshrikant.drools.control;

import com.kaleshrikant.drools.control.model.ClientProfile;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * ClientProfile fact with a status field.
 * If the status is "NEW" and age > 30, we update it to "VERIFIED" — but don’t want the rule to re-trigger after the update.
 *
 * @author Shrikant Kale
 * @Date 12 Sep 2025
 */

public class SafeUpdateNoLoopProgram {
	private static final Logger logger = LoggerFactory.getLogger(SafeUpdateNoLoopProgram.class);
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
		kieSession.insert(new ClientProfile("Shrikant", 35, "NEW"));
		kieSession.insert(new ClientProfile("Meera", 28, "NEW"));
		kieSession.insert(new ClientProfile("Ravi", 45, "VERIFIED"));

		// 3️⃣ Fire all rules
		kieSession.fireAllRules();

		// 4️⃣ Dispose the session
		kieSession.dispose();
	}
}
