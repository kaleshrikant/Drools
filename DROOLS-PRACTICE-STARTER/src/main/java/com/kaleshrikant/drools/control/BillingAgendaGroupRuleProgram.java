package com.kaleshrikant.drools.control;

import com.kaleshrikant.drools.control.model.Borrower;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The billing process involves three stages:
 *
 *      Validation of invoice data
 *      Discount application based on amount
 *      Final billing confirmation
 *
 * @author Shrikant Kale
 * @Date 12 Sep 2025
 */

public class BillingAgendaGroupRuleProgram {
	private static final Logger logger = LoggerFactory.getLogger(SafeUpdateNoLoopProgram.class);
	public static void main(String[] args) {
		// 1️⃣ Get KieServices
		KieServices kieServices = KieServices.Factory.get();

		// 1️⃣ Get KieContainer
		KieContainer kieContainer = kieServices.getKieClasspathContainer();

		// 2️⃣ Create KieSession directly
		KieSession kieSession = kieContainer.newKieSession("ksession-rules"); // match name in kmodule.xml

		// Inject logger into Drools
		List<String> logCollector = new ArrayList<>();
		kieSession.setGlobal("logCollector", logCollector);


		// 2️⃣ Fact Insertion
		kieSession.insert(new Borrower("Shrikant", 12000));
		kieSession.insert(new Borrower("Meera", 8000));

		kieSession.getAgenda().getAgendaGroup("billing-validation").setFocus();
		// 3️⃣ Fire all rules
			kieSession.fireAllRules();
		logCollector.forEach(System.out::println); // or sort, export, etc.

		kieSession.getAgenda().getAgendaGroup("billing-discount").setFocus();
		// 3️⃣ Fire all rules
			kieSession.fireAllRules();
		logCollector.forEach(System.out::println); // or sort, export, etc.

		kieSession.getAgenda().getAgendaGroup("billing-confirmation").setFocus();
		// 3️⃣ Fire all rules
			kieSession.fireAllRules();
		logCollector.forEach(System.out::println); // or sort, export, etc.

		Collections.sort(logCollector); // alphabetical
		logCollector.forEach(System.out::println); // flush


		// 4️⃣ Dispose the session
		kieSession.dispose();
	}
}
