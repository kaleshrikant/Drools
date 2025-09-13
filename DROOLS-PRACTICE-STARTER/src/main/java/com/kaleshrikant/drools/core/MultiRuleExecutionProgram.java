package com.kaleshrikant.drools.core;

import com.kaleshrikant.drools.core.model.Order;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 💰 Tiered Discount Evaluation via Drools
 *
 * 🧾 Applies rule-based discounts to Order facts:
 *    💎 PREMIUM → 15% if amount > 1000
 *    📦 BULK → 10% if itemCount ≥ 10
 *    🛒 REGULAR → 5% if amount > 500 and no prior discount
 *
 * ✅ Logs final discount using SLF4J
 * 🔁 Uses update() to trigger re-evaluation
 *
 * @author Shrikant Kale
 * @Date 11 Sep 2025
 */

public class MultiRuleExecutionProgram {
	private static final Logger logger = LoggerFactory.getLogger(MultiRuleExecutionProgram.class);

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
		kieSession.insert(new Order("ORD001", 1200, 5, "PREMIUM"));
		kieSession.insert(new Order("ORD002", 800, 12, "REGULAR"));
		kieSession.insert(new Order("ORD003", 600, 3, "REGULAR"));

		// 3️⃣ Fire all rules
		kieSession.fireAllRules();

		// 4️⃣ Dispose the session
		kieSession.dispose();
	}
}
