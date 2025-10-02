package com.kaleshrikant.patterns.loan;

import com.kaleshrikant.patterns.loan.model.Customer;
import com.kaleshrikant.patterns.loan.model.Account;
import com.kaleshrikant.patterns.loan.model.Transaction;
import com.kaleshrikant.patterns.loan.model.Application;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 🎨 LoanApproverProgram - Intelligent Loan Approval System
 *
 * 🎯 Advanced Pattern Matching Features:
 *    📜 Complex conditional logic with multiple constraints
 *    🔍 Pattern matching with exists, not, and forall operators
 *    🧮 FUNCTIONS for reusable business calculations
 *    ➕ Accumulate patterns for data aggregation
 *    🌐 Global variables for cross-rule communication
 *
 * 🧊 Expected Facts: Customer, Account, Transaction, Application
 *
 * 💻▶ Sample Output:
 *  === 📋 Advanced Pattern Matching Results ===
 *      🔔 🔍 Complex pattern matched for: Shrikant
 *      🔔 📊 Risk score calculated: 4.8
 *      🔔 ✅ Shrikant is eligible for loan.
 *      🔔 🧠 Complex conditions satisfied for: SHRIKANT
 *  🎉 LoanApproverProgram executed successfully!
 *
 * @author Shrikant Kale
 */

public class LoanApproverProgram {
	public static void main(String[] args) {
		// 🔧 Initialize KIE Services
		KieServices kieServices = KieServices.Factory.get();
		KieContainer kieContainer = kieServices.getKieClasspathContainer();
		KieSession kieSession = kieContainer.newKieSession("patternLoanSession");

		// 🌐 Set up global log collector
		List<String> logCollector = new ArrayList<>();
		kieSession.setGlobal("logCollector", logCollector);

		// 🧊 Insert domain facts
		Customer shrikant = new Customer("Shrikant", "ACTIVE", 35, 85000, 720);
		Account account = new Account(shrikant.getId(), 15000);
		Transaction tx1 = new Transaction(shrikant.getId(), "NORMAL", 5000);
		Application app = new Application(shrikant.getId(), "HOME_LOAN");

		kieSession.insert(shrikant);
		kieSession.insert(account);
		kieSession.insert(tx1);
		kieSession.insert(app);

		// 🏃 Fire all rules
		kieSession.fireAllRules();

		// 💻▶ Print collected logs
		System.out.println("=== 📋 Advanced Pattern Matching Results ===");
		for (String log : logCollector) {
			System.out.println("🔔 " + log);
		}

		// 🧹 Clean up session
		kieSession.dispose();
		System.out.println("🎉 LoanApproverProgram executed successfully!");
	}
}
