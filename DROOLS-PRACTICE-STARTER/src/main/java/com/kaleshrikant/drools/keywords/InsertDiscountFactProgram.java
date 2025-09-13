package com.kaleshrikant.drools.keywords;

import com.kaleshrikant.drools.keywords.model.Customer;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 💰 InsertDiscountFactProgram via Drools
 *
 * 🧾 Applies festival-season discounts to Customer facts:
 *    💎 PREMIUM → 15% if purchaseAmount ≥ ₹10,000
 *    🛍️ REGULAR → 5% if purchaseAmount ≥ ₹1,000
 *    ✅ Only applies if isFestivalSeason == true
 *
 * 📤 Inserts Customer facts into KieSession
 * 📦 Creates and inserts DiscountFact with calculated discount
 * 🖨️ Logs discount insertion to console
 *
 * 🖨️ Sample Output:
 * Inserted DiscountFact for Premium Customer :: Shrikant with ₹1800.0
 * Inserted DiscountFact for Regular Customer :: Prashant with ₹750.0
 *
 * @author Shrikant Kale
 * @Date 12 Sep 2025
 */

public class InsertDiscountFactProgram {
	private static final Logger logger = LoggerFactory.getLogger(InsertDiscountFactProgram.class);
	public static void main(String[] args) {
		// 1️⃣ Get KieServices
		KieServices kieServices = KieServices.Factory.get();

		// 1️⃣ Get KieContainer
		KieContainer kieContainer = kieServices.getKieClasspathContainer();

		// 2️⃣ Create KieSession directly
		KieSession kieSession = kieContainer.newKieSession("ksession-rules"); // match name in kmodule.xml

		// Inject logger into Drools
		kieSession.setGlobal("logger", logger);

		// ✅ Set isFestivalSeason global (otherwise it’s null)
		kieSession.setGlobal("isFestivalSeason", Boolean.TRUE);

		// 2️⃣ Fact Insertion
		kieSession.insert(new Customer("Shrikant", true, 12000));
		kieSession.insert(new Customer("Prashant", false, 15000));
		kieSession.insert(new Customer("Meera", true, 8000));

		// 3️⃣ Fire all rules
		kieSession.fireAllRules();

		// 4️⃣ Dispose the session
		kieSession.dispose();
	}
}
