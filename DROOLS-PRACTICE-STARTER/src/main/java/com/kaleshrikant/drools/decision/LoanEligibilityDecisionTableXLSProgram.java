package com.kaleshrikant.drools.decision;

import com.kaleshrikant.drools.decision.model.LoanApplicant;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.List;

/**
 * LoanEligibilityDecisionTableXLSProgram
 * Evaluates loan eligibility using a Decision Table in XLS format.
 *
 * Results:
 *  🟢 Tier 1 Loan approved for Shrikant
 *  🟡 Tier 2 Loan approved for Shrikant
 *  🟠 Tier 3 Loan approved for Shrikant
 *  🟠 Tier 3 Loan approved for Prashant
 *
 * @author Shrikant Kale
 * @date 23 Sep 2025
 */
public class LoanEligibilityDecisionTableXLSProgram {
	public static void main(String[] args) {
		// 1️⃣ KieServices and KieContainer
		KieServices kieServices = KieServices.Factory.get();
		KieContainer kieContainer = kieServices.getKieClasspathContainer();
		KieSession kieSession = kieContainer.newKieSession("decisionTable-session");

		// 2️⃣ Logger
		List<String> logCollector = new ArrayList<>();
		kieSession.setGlobal("logCollector", logCollector);

		// 3️⃣ Insert sample loan applicant facts
		LoanApplicant applicant1 = new LoanApplicant("Shrikant", 30, 25000, 700);
		LoanApplicant applicant2 = new LoanApplicant("Prashant", 45, 15000, 600);
		kieSession.insert(applicant1);
		kieSession.insert(applicant2);

		// 4️⃣ Fire all rules
		kieSession.fireAllRules();

		// 5️⃣ Dispose session
		kieSession.dispose();

		// 6️⃣ Print results
		System.out.println("Results:");
		logCollector.forEach(System.out::println);
	}
}
