package com.kaleshrikant.drools.control;

import com.kaleshrikant.drools.control.model.ClientProfile;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * To demonstrate how to use agenda groups and lock-on-active to control rule execution order and
 * prevent re-triggering when facts are modified.
 *
 * Stage 1: Validate client → updates status to "VALIDATED"
 *
 * Stage 2: Approve client → updates status to "APPROVED" We use agenda-group
 * to control execution order and lock-on-active to prevent rules from re-firing when facts are modified.
 *
 * This is ideal for onboarding scenarios involving staged rule flows, fact updates, and controlled rule firing.
 *
 * @author Shrikant Kale
 * @Date 12 Sep 2025
 */

public class LockActiveGroupRuleProgram {
	private static final Logger logger = LoggerFactory.getLogger(LockActiveGroupRuleProgram.class);
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
		kieSession.insert(new ClientProfile("Meera", 17, "NEW")); // underage, won't validate

			// Stage 1: Validation
					kieSession.getAgenda().getAgendaGroup("validation").setFocus();
				// 3️⃣ Fire all rules
					kieSession.fireAllRules();

			// Stage 2: Approval
					kieSession.getAgenda().getAgendaGroup("approval").setFocus();
				// 3️⃣ Fire all rules
					kieSession.fireAllRules();

		// 4️⃣ Dispose the session
		kieSession.dispose();
	}
}
