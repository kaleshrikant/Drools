package com.kaleshrikant.drools.control;

import com.kaleshrikant.drools.control.model.Member;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * designed to demonstrate how to use the powerful Drools feature: activation-group,
 * which ensures only one rule in the group fires, even if multiple match.
 *
 * This is perfect for onboarding scenarios involving conflicting decisions, priority-based selection, or mutually exclusive actions
 * — and yes, we’ll use your preferred logCollector pattern for audit clarity.
 *
 * We have a Borrower with a loan request. We want to apply one of three loan tiers based on amount:
 * 🟢 Tier 1: ₹ ≥ 20,000
 * 🟡 Tier 2: ₹ ≥ 10,000
 * 🔴 Tier 3: ₹ < 10,000
 * Only one rule should fire, even if multiple match — so we use activation-group "loan-tier".
 *
 * @author Shrikant Kale
 * @Date 12 Sep 2025
 */

public class ExclusiveActivationGroupRuleProgram {
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
			List<Member> members = Arrays.asList(
					new Member("Shrikant", 25000),
					new Member("Meera", 15000),
					new Member("Ravi", 5000)
			);

		// 3️⃣ Fire all rules
			for (Member m : members) {
				kieSession.insert(m);
				kieSession.fireAllRules(); // 🔁 fire per fact
			}

		// 4️⃣ Dispose the session
		kieSession.dispose();

		logCollector.forEach(System.out::println);
	}
}
