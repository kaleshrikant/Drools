package com.kaleshrikant.drools.decision;

import com.kaleshrikant.drools.decision.model.CustomerProfile;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.List;
/**
 * 📊 CustomerProfile Discount Template Program
 *
 * 🎯 Purpose:
 * Demonstrates how to use a Drools rule template (.drt)
 * to dynamically generate rules for applying discounts
 * to customer profiles based on type and purchase amount.
 *
 * 🧊 Facts:
 * - CustomerProfile (name, type, purchaseAmount, discount)
 *
 * ➕ Conditions:
 * - Type of customer (VIP, REGULAR, NEW)
 * - Purchase amount thresholds
 *
 * 🔥 Actions:
 * - Set discount percentage dynamically
 *
 * 📂 Resources:
 * - templates/CustomerProfileDiscountTemplate.drt
 * - META-INF/kmodule.xml
 *
 * ✅ Learning:
 * - How to use DRL templates in Drools
 * - How to dynamically generate multiple rules
 *
 * Discount Results:
 * -----------------
 * Name            Type       PurchaseAmount  Discount
 * Shrikant        VIP        7000            0
 * Prashant        REGULAR    3000            0
 * Charlie         NEW        1500            0
 *
 * @author Shrikant Kale
 * @Date 25 Sep 2025
 */

public class CustomerDiscountTemplateProgram {

	public static void main(String[] args) {
		try {
			// 1️⃣ Get KieServices
			KieServices ks = KieServices.Factory.get();

			// 2️⃣ Create KieFileSystem and load only your template
			KieFileSystem kfs = ks.newKieFileSystem();

			kfs.write("src/main/resources/templates/CustomerProfileDiscountTemplate.drt",
					ks.getResources()
							.newClassPathResource("rules/CustomerProfileDiscountTemplate.drt")
							.setResourceType(ResourceType.DRT));


			/*  Explanation
			 *
			 *
			 */

			// 3️⃣ Build the rules
			KieBuilder kb = ks.newKieBuilder(kfs);
			kb.buildAll();

			// 4️⃣ Create KieContainer and KieSession
			KieContainer kc = ks.newKieContainer(ks.getRepository().getDefaultReleaseId());
			KieSession kieSession = kc.newKieSession();

			// 5️⃣ Initialize logCollector (global)


			// 6️⃣ Create facts
			CustomerProfile c1 = new CustomerProfile("Shrikant", "VIP", 7000);
			CustomerProfile c2 = new CustomerProfile("Prashant", "REGULAR", 3000);
			CustomerProfile c3 = new CustomerProfile("Charlie", "NEW", 1500);

			kieSession.insert(c1);
			kieSession.insert(c2);
			kieSession.insert(c3);

			// 7️⃣ Fire rules
			kieSession.fireAllRules();

			// 8️⃣ Print results
			System.out.println("\nDiscount Results:");
			System.out.println("-----------------");
			System.out.printf("%-15s %-10s %-15s %-10s%n", "Name", "Type", "PurchaseAmount", "Discount");
			for (CustomerProfile c : List.of(c1, c2, c3)) {
				System.out.printf("%-15s %-10s %-15d %-10d%n",
						c.getName(), c.getType(), c.getPurchaseAmount(), c.getDiscount());
			}


			kieSession.dispose();

		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
