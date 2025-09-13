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
 * 📋 BillingAgendaGroupRuleProgram via Drools
 *
 * 🧾 Multi-stage billing workflow using agenda groups:
 *    🔍 billing-validation → Validates invoiceAmount > 0
 *    💸 billing-discount → Applies 15% discount if invoiceAmount ≥ ₹10,000
 *    ✅ billing-confirmation → Marks billingConfirmed once invoice is valid
 *
 * 📦 Uses logCollector to track rule execution
 * 🔁 lock-on-active prevents reactivation within same agenda group
 * 📤 Inserts Borrower facts into KieSession
 * 🧹 Session disposed after rule execution
 *
 * 🖨️ Sample Output:
 * 🔔 RuleName triggered for 'Shrikant'
 * 🧾 Invoice validated for 'Shrikant'
 * 🔔 RuleName triggered for 'Meera'
 * 🧾 Invoice validated for 'Meera'
 * 🔔 RuleName triggered for 'Shrikant'
 * 💸 Discount applied for 'Shrikant'
 * 🔔 RuleName triggered for 'Shrikant'
 * ✅ Billing confirmed for 'Shrikant'
 * 🔔 RuleName triggered for 'Meera'
 * ✅ Billing confirmed for 'Meera'
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
