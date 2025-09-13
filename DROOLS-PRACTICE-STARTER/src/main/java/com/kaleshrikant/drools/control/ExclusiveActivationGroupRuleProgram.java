package com.kaleshrikant.drools.control;

import com.kaleshrikant.drools.control.model.Member;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 🏦 ExclusiveActivationGroupRuleProgram via Drools
 *
 * 🧾 Assigns loan tiers to Member facts using activation-group "loan-tier":
 *    🟢 TIER_1 → loanAmount ≥ ₹20,000
 *    🟡 TIER_2 → loanAmount ≥ ₹10,000
 *    🔴 TIER_3 → loanAmount < ₹10,000
 *
 * 📦 Uses activation-group to ensure only one tier rule fires per Member
 * 📤 Inserts Member facts into KieSession
 * 🧾 Logs tier assignment via logCollector
 * 🧹 Session disposed after rule execution
 *
 * 🖨️ Sample Output:
 *  🟢 Tier 1 loan assigned to 'Shrikant'
 *  🟡 Tier 2 loan assigned to 'Meera'
 *  🔴 Tier 3 loan assigned to 'Ravi'
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
