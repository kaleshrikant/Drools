package com.kaleshrikant.drools.control;

import com.kaleshrikant.drools.control.model.Participant;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.List;

/**
 * 🛂 VisaCheckRuleflowGroupProgram via Drools
 *
 * 🧾 Three-stage visa processing using ruleflow-group:
 *    🧪 visa-eligibility → Marks eligible if age ≥ 18
 *    📄 visa-documents → Logs document verification if eligible and submitted
 *    ✅ visa-approval → Approves visa if eligible, documents submitted, and not yet approved
 *
 * 📤 Inserts Participant facts into KieSession
 * 📦 Uses logCollector to track rule execution
 * 🎯 Activates agenda groups in sequence for controlled flow
 * 🧹 Session disposed after rule execution
 *
 * 🖨️ Sample Output:
 *      🧪 Eligibility passed for 'Shrikant'
 *      📄 Documents verified for 'Shrikant'
 *      ✅ Visa approved for 'Shrikant'
 *
 * @author Shrikant Kale
 * @Date 12 Sep 2025
 */

public class VisaCheckRuleflowGroupProgram {
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
		Participant shrikant = new Participant("Shrikant", 30, true);
		Participant meera = new Participant("Meera", 16, true); // underage

		kieSession.insert(shrikant);
		kieSession.insert(meera);

		kieSession.getAgenda().getAgendaGroup("visa-eligibility").setFocus();
		// 3️⃣ Fire all rules
			kieSession.fireAllRules();

		kieSession.getAgenda().getAgendaGroup("visa-documents").setFocus();
		// 3️⃣ Fire all rules
			kieSession.fireAllRules();

		kieSession.getAgenda().getAgendaGroup("visa-approval").setFocus();
		// 3️⃣ Fire all rules
			kieSession.fireAllRules();

		// 4️⃣ Dispose the session
		kieSession.dispose();

		logCollector.forEach(System.out::println);
	}
}
