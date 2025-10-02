package com.kaleshrikant.core.age;

import com.kaleshrikant.core.age.model.Family;
import com.kaleshrikant.core.hello.model.Person;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 📋 AgeClassifierProgram - Age Classification and Life Stage Detection
 *
 * 🎯 Core Drools functionality demonstration:
 *    📜 Implements business logic through declarative rules
 *    🧠 Pattern matching for complex condition evaluation
 *    🌐 Global variable integration for result collection
 *    💾 Working memory management for fact handling
 *
 * 🧊 Expected Facts: Family, Person
 *
 * 💻▶ Sample Output:
 * === 📋 Log Output ===
 * 🔔 🧒 Krishna is classified as a Child.
 * 🔔 🧑 Prashant is classified as a Teenager.
 * 🔔 👨 Shrikant is classified as an Adult.
 * 🔔 👴 Pratibha is classified as a Senior.
 * 🎉 AgeClassifierProgram executed successfully!
 *
 * @author Shrikant Kale
 */
public class AgeClassifierProgram {
	public static void main(String[] args) {
		// 🔧 Initialize KIE Services
		KieServices kieServices = KieServices.Factory.get();
		KieContainer kieContainer = kieServices.getKieClasspathContainer();
		KieSession kieSession = kieContainer.newKieSession("coreAgeSession");

		// 🌐 Set up global log collector
		List<String> logCollector = new ArrayList<>();
		kieSession.setGlobal("logCollector", logCollector);

		// 🧊 Insert domain facts
		kieSession.insert(new Person("Krishna", 10));    // Child
		kieSession.insert(new Person("Prashant", 15));   // Teenager
		kieSession.insert(new Person("Shrikant", 35));   // Adult
		kieSession.insert(new Person("Pratibha", 65));   // Senior

		kieSession.insert(new Family("Kale", new Person("Shrikant", 35)));

		// 🏃 Fire all rules
		kieSession.fireAllRules();

		// 💻▶ Print collected logs
		System.out.println("=== 📋 Log Output ===");
		for (String log : logCollector) {
			System.out.println("🔔 " + log);
		}

		// 🧹 Clean up session
		kieSession.dispose();
		System.out.println("🎉 AgeClassifierProgram executed successfully!");
	}
}
