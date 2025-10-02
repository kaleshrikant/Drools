package com.kaleshrikant.core.grade;

import com.kaleshrikant.core.grade.model.Student;
import com.kaleshrikant.core.grade.model.ScholarshipPolicy;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 📋 GradeEvaluatorProgram - Student Grade Evaluation and Assessment
 *
 * 🎯 Core Drools functionality demonstration:
 *    📜 Implements business logic through declarative rules
 *    🧠 Pattern matching for complex condition evaluation
 *    🌐 Global variable integration for result collection
 *    💾 Working memory management for fact handling
 *
 * 🧊 Expected Facts: Domain-specific objects for rule evaluation
 * 📢 NOTE: Implement your business rules in corresponding DRL file
 * ❗ Remember to add facts to working memory before firing rules
 *
 * 💻▶ Sample Output:
 *  === 📋 Log Output ===
 *      🔔 🏅 Shrikant scored Grade A
 *      🔔 🥈 Prashant scored Grade B
 *      🔔 🥉 Pratibha scored Grade C
 *      🔔 📘 Ramesh scored Grade D
 *      🔔 ❌ Suresh scored Grade F
 *  🎉 GradeEvaluatorProgram executed successfully!
 *
 * @author Shrikant Kale
 */
public class GradeEvaluatorProgram {
	public static void main(String[] args) {
		// 🔧 Initialize KIE Services
		KieServices kieServices = KieServices.Factory.get();
		KieContainer kieContainer = kieServices.getKieClasspathContainer();
		KieSession kieSession = kieContainer.newKieSession("coreGradeSession");

		// 🌐 Set up global log collector
		List<String> logCollector = new ArrayList<>();
		kieSession.setGlobal("logCollector", logCollector);

		// 🧊 Insert domain facts
		kieSession.insert(new Student("Shrikant", 92));     // Grade A
		kieSession.insert(new Student("Prashant", 80));     // Grade B
		kieSession.insert(new Student("Pratibha", 68));     // Grade C
		kieSession.insert(new Student("Ramesh", 55));       // Grade D
		kieSession.insert(new Student("Suresh", 35));       // Grade F

		kieSession.insert(new ScholarshipPolicy("A"));          // Scholarship criteria

		// 🏃 Fire all rules
		kieSession.fireAllRules();

		// 💻▶ Print collected logs
		System.out.println("=== 📋 Log Output ===");
		for (String log : logCollector) {
			System.out.println("🔔 " + log);
		}

		// 🧹 Clean up session
		kieSession.dispose();
		System.out.println("🎉 GradeEvaluatorProgram executed successfully!");
	}
}
