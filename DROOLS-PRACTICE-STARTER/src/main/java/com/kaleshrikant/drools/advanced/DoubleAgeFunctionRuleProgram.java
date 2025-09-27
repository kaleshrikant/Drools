package com.kaleshrikant.drools.advanced;

import com.kaleshrikant.drools.advanced.model.UserProfile;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * 📜 DoubleAgeFunctionRuleProgram
 *
 * 🎯 Purpose:
 * Demonstrates how to use **functions in DRL** to manipulate facts.
 *
 * 🧊 Facts:
 * - UserProfile with name and age.
 *
 * ➕ Conditions:
 * - Call a DRL function to double the age of each user.
 *
 * 🔥 Actions:
 * - Update the age of the UserProfile fact using DRL function.
 *
 * 📚 Resources:
 * - DRL file with a function definition.
 *
 * 🧠 Learning:
 * - Learn how to define and call functions in DRL and modify facts.
 *
 * OUTPUT :
 * 🔹 Age doubled for Prashant → 50
 * 🔹 Age doubled for Shrikant → 60
 *
 * === UserProfiles After Doubling Age ===
 *  UserProfile{name='Prashant', age=50}
 *  UserProfile{name='Shrikant', age=60}
 *
 * @author Shrikant Kale
 * @Date 26 Sep 2025
 */

public class DoubleAgeFunctionRuleProgram {
	public static void main(String[] args) {
		KieServices ks = KieServices.Factory.get();
		KieContainer kc = ks.getKieClasspathContainer();
		KieSession kieSession = kc.newKieSession("double-age-session");

		// Insert facts
		UserProfile u1 = new UserProfile("Prashant", 25);
		UserProfile u2 = new UserProfile("Shrikant", 30);

		kieSession.insert(u1);
		kieSession.insert(u2);

		// Fire rules
		kieSession.fireAllRules();

		// Print results
		System.out.println("\n=== UserProfiles After Doubling Age ===");
		System.out.println(u1);
		System.out.println(u2);

		kieSession.dispose();
	}
}
