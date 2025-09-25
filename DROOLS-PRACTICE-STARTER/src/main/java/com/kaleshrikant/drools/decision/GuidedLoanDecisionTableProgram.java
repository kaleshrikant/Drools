package com.kaleshrikant.drools.decision;

import com.kaleshrikant.drools.decision.model.LoanClient;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * 📊 Guided Loan Eligibility Decision Table Program
 *
 * 🎯 Purpose:
 * Demonstrates how to use a Guided Decision Table in Drools
 * to evaluate loan eligibility for applicants based on
 * credit score, income, and existing debt.
 *
 * 🧊 Facts:
 * - Applicant (name, creditScore, income, debt, loanEligible)
 *
 * ➕ Conditions:
 * - Credit score thresholds
 * - Income thresholds
 * - Existing debt status
 *
 * 🔥 Actions:
 * - Set loan eligibility (true/false)
 *
 * 📂 Resources:
 * - decision-tables/GuidedLoanRules.xlsx
 * - META-INF/kmodule.xml
 *
 * ✅ Learning:
 * - How to implement Guided Decision Tables in Drools
 * - How rules can be maintained by business users in Excel
 *
 * Output :
 *  LoanClient: Charlie, Loan Eligible: false
 *  LoanClient: Alice, Loan Eligible: false
 *  LoanClient: Bob, Loan Eligible: false
 *
 * @author Shrikant Kale
 * @Date 25 Sep 2025
 */

public class GuidedLoanDecisionTableProgram {
	public static void main(String[] args) {
		try {
			KieServices kieServices = KieServices.Factory.get();
			KieContainer kieContainer = kieServices.getKieClasspathContainer();

			KieSession kieSession = kieContainer.newKieSession("decisionTable-session");

			// Create facts
			LoanClient c1 = new LoanClient("Alice", 750, 80000, 10000);
			LoanClient c2 = new LoanClient("Bob", 680, 50000, 20000);
			LoanClient c3 = new LoanClient("Charlie", 600, 30000, 5000);

			List<LoanClient> clients = Arrays.asList(c1, c2, c3);

			// Set the global
			List<String> logCollector = new ArrayList<>();
			kieSession.setGlobal("logCollector", logCollector);


			// Insert facts
			clients.forEach(kieSession::insert);

			// Fire rules
			kieSession.fireAllRules();

			// Print results
			clients.forEach(c ->
					System.out.println("LoanClient: " + c.getName() +
							", Loan Eligible: " + c.isLoanEligible())
			);

			kieSession.dispose();

		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
