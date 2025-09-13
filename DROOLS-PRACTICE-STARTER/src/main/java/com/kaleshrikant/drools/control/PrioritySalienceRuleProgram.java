package com.kaleshrikant.drools.control;

import com.kaleshrikant.drools.control.model.Patron;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 🎯 PrioritySalienceRuleProgram via Drools
 *
 * 🧾 Executes Patron rules in priority order using salience:
 *    🥇 Logs premium patrons (salience 100)
 *    🥈 Applies 10% discount if purchaseAmount ≥ ₹10,000 (salience 50)
 *    🥉 Sends notification to all patrons (salience 10)
 *
 * 📤 Inserts Patron facts into KieSession
 * ✅ Salience ensures correct rule firing sequence
 * 🧹 Session disposed after rule execution
 *
 * 🖨️ Sample Output:
 *  🥇 Premium Patron detected: Shrikant
 *  🥈 Applied discount ₹1200.0 for Shrikant
 *  🥉 Notification sent to Shrikant
 *  🥉 Notification sent to Meera
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
