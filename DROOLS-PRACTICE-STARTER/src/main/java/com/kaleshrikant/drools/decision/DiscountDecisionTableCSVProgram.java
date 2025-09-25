package com.kaleshrikant.drools.decision;

import com.kaleshrikant.drools.decision.model.Shopper;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.Arrays;
import java.util.List;

/**
 * 📊 Discount Decision Table Program
 *
 * 🎯 Purpose:
 * Demonstrates how to use a CSV Decision Table in Drools
 * to apply dynamic discounts to shoppers based on their
 * type (GOLD, SILVER, BRONZE) and purchase amount.
 *
 * 🧊 Facts:
 * - Shopper (type, purchaseAmount, discount)
 *
 * ➕ Conditions:
 * - Type of Shopper (GOLD / SILVER / BRONZE)
 * - Purchase Amount threshold
 *
 * 🔥 Actions:
 * - Set discount percentage using modify()
 *
 * 📂 Resources:
 * - decision-tables/DiscountRules.csv
 * - META-INF/kmodule.xml
 *
 * ✅ Learning:
 * - How Drools converts CSV Decision Tables into rules
 * - How to integrate them with Java programs
 *
 * Output :
 *  Shopper: GOLD, Amount: 6000, Discount: 20%
 *  Shopper: SILVER, Amount: 2500, Discount: 0%
 *  Shopper: BRONZE, Amount: 3000, Discount: 5%
 *
 * @author Shrikant Kale
 * @Date 25 Sep 2025
 */

public class DiscountDecisionTableCSVProgram {
	public static void main(String[] args) {
		try {
			// 1️⃣ Initialize KieServices and KieContainer
			KieServices kieServices = KieServices.Factory.get();
			KieContainer kieContainer = kieServices.getKieClasspathContainer();

			// 2️⃣ Load the decision table session
			KieSession kieSession = kieContainer.newKieSession("decisionTable-session");

			// 3️⃣ Create facts
			Shopper s1 = new Shopper("GOLD", 6000);
			Shopper s2 = new Shopper("SILVER", 2500);
			Shopper s3 = new Shopper("BRONZE", 3000);

			List<Shopper> shoppers = Arrays.asList(s1, s2, s3);

			// 4️⃣ Insert facts
			shoppers.forEach(kieSession::insert);

			// 5️⃣ Fire rules from decision table
			kieSession.fireAllRules();

			// 6️⃣ Print results
			shoppers.forEach(s ->
					System.out.println("Shopper: " + s.getType() +
							", Amount: " + s.getPurchaseAmount() +
							", Discount: " + s.getDiscount() + "%")
			);

			kieSession.dispose();

		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
