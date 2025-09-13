package com.kaleshrikant.drools.keywords;

import com.kaleshrikant.drools.keywords.model.Applicant;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 👵 SeniorCountAccumulateKeywordProgram via Drools
 *
 * 🧾 Counts Applicant facts with age ≥ 60 using accumulate:
 *    📊 Fires rule only if senior count > 2
 *    🖨️ Logs total senior applicants to console
 *
 * 📤 Inserts Applicant facts into KieSession
 * ✅ Uses accumulate() with count(1)
 * 🧹 Session disposed after rule execution
 *
 * 🖨️ Sample Output:
 * Senior applicant count is high: 3
 *
 * @author Shrikant Kale
 * @Date 11 Sep 2025
 */

public class SeniorCountAccumulateKeywordProgram {
	private static final Logger logger = LoggerFactory.getLogger(SeniorCountAccumulateKeywordProgram.class);
	public static void main(String[] args) {
		// 1️⃣ Get KieServices
		KieServices kieServices = KieServices.Factory.get();

		// 1️⃣ Get KieContainer
		KieContainer kieContainer = kieServices.getKieClasspathContainer();

		// 2️⃣ Create KieSession directly
		KieSession kieSession = kieContainer.newKieSession("ksession-rules"); // match name in kmodule.xml

		// Inject logger into Drools
		kieSession.setGlobal("logger", logger);

		// 2️⃣ Fact Insertion
		kieSession.insert(new Applicant("Alice", 65));
		kieSession.insert(new Applicant("Bob", 70));
		kieSession.insert(new Applicant("Charlie", 55));
		kieSession.insert(new Applicant("David", 80));

		// 3️⃣ Fire all rules
		kieSession.fireAllRules();

		// 4️⃣ Dispose the session
		kieSession.dispose();
	}
}
