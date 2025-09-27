package com.kaleshrikant.drools.advanced;

import com.kaleshrikant.drools.advanced.model.Patient;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.List;

/**
 * 🎯 Purpose: Run Patient Diagnosis Rules
 * 🧊 Facts: Patient (name, age, temperature, cough, fatigue)
 * ➕ Conditions: Based on symptoms
 * 🔥 Actions: Assign diagnosis and log results
 * 📂 Resources: rules/patient-diagnosis.drl
 * 📘 Learning: Demonstrates simple rule evaluation with Drools
 *
 * === Patient Diagnosis Results ===
 * Patient{name='Shrikant', age=34, temperature=39.2, hasCough=true, hasFatigue=true, diagnosis='Flu'}
 * Patient{name='Prashant', age=32, temperature=37.5, hasCough=true, hasFatigue=false, diagnosis='Common Cold'}
 * Patient{name='Pratibha', age=68, temperature=36.8, hasCough=false, hasFatigue=false, diagnosis='Healthy'}
 *
 * === Rule Logs ===
 *  🩺 Flu diagnosed for Shrikant
 *  ❄️ Common Cold diagnosed for Prashant
 *  ✅ Pratibha is Healthy
 *
 * @author Shrikant Kale
 * @Date 27 Sep 2025
 */

public class PatientDiagnosisRuleUnitsProgram {
	public static void main(String[] args) {
		try {
			KieServices ks = KieServices.Factory.get();
			KieContainer kc = ks.getKieClasspathContainer();
			KieSession ksession = kc.newKieSession("advancedSession");

			// Global log collector
			List<String> logCollector = new ArrayList<>();
			ksession.setGlobal("logCollector", logCollector);

			// Insert Patient facts
			Patient patient1 = new Patient("Shrikant", 34, 39.2, true, true);
			Patient patient2 = new Patient("Prashant", 32, 37.5, true, false);
			Patient patient3 = new Patient("Pratibha", 68, 36.8, false, false);

			ksession.insert(patient1);
			ksession.insert(patient2);
			ksession.insert(patient3);

			// Fire rules
			ksession.fireAllRules();

			// Print results
			System.out.println("\n=== Patient Diagnosis Results ===");
			System.out.println(patient1);
			System.out.println(patient2);
			System.out.println(patient3);

			System.out.println("\n=== Rule Logs ===");
			logCollector.forEach(System.out::println);

			ksession.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
