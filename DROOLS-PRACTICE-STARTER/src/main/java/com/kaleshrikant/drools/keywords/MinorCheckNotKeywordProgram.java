package com.kaleshrikant.drools.keywords;

import com.kaleshrikant.drools.keywords.model.Student;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 🧒 MinorCheckNotKeywordProgram via Drools
 *
 * 🧾 Classifies Student facts and checks guardian status:
 *    🧒 Marks students as minors if age < 18 and minor == false
 *    ⚠️ Warns if any minor lacks a guardian (using not keyword)
 *    📊 Accumulates and logs total number of minors
 *
 * 📤 Inserts Student facts into KieSession
 *    ✅ Uses update() to trigger downstream rules
 *    🧹 Session disposed after rule execution
 *
 * 🖨️ Sample Output:
 *    🧒 Aarav is marked as a minor.
 *    🧒 Isha is marked as a minor.
 *    ⚠️ Minor Aarav has no guardian assigned!
 *    ⚠️ Minor Isha has no guardian assigned!
 * 📊 Total minors identified: 3
 *
 * @author Shrikant Kale
 * @Date 11 Sep 2025
 */

public class MinorCheckNotKeywordProgram {
	private static final Logger logger = LoggerFactory.getLogger(MinorCheckNotKeywordProgram.class);
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
		kieSession.insert(new Student("Aarav", 16, false));
		kieSession.insert(new Student("Meera", 17, true));
		kieSession.insert(new Student("Rohan", 19, false));
		kieSession.insert(new Student("Isha", 15, false));

		// 3️⃣ Fire all rules
		kieSession.fireAllRules();

		// 4️⃣ Dispose the session
		kieSession.dispose();
	}
}
