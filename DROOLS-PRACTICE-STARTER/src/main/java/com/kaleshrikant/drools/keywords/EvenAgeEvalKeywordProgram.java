package com.kaleshrikant.drools.keywords;
import com.kaleshrikant.drools.keywords.model.AgeCandidate;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 🔢 EvenAgeEvalKeywordProgram via Drools
 *
 * 🧾 Classifies AgeCandidate facts by even/odd age:
 *    🔢 Marks even ages and sets evenAge = true
 *    🔹 Logs odd ages without modifying the fact
 *    📊 Accumulates and logs total count of even-aged people
 *
 * 📤 Inserts candidates into KieSession
 * ✅ Uses eval() for modulus check
 * 🔁 update() triggers re-evaluation
 * 🧹 Session disposed after rule execution
 *
 * 🖨️ Sample Output:
 * 🔢 Aarav has an even age: 22
 * 🔹 Meera has an odd age: 35
 * 🔢 Rohan has an even age: 40
 * 🔹 Isha has an odd age: 27
 * 📊 Total people with even age: 2
 *
 * @author Shrikant Kale
 * @Date 11 Sep 2025
 */

public class EvenAgeEvalKeywordProgram {
	private static final Logger logger = LoggerFactory.getLogger(EvenAgeEvalKeywordProgram.class);
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
		kieSession.insert(new AgeCandidate("Aarav", 22));
		kieSession.insert(new AgeCandidate("Meera", 35));
		kieSession.insert(new AgeCandidate("Rohan", 40));
		kieSession.insert(new AgeCandidate("Isha", 27));

		// 3️⃣ Fire all rules
		kieSession.fireAllRules();

		// 4️⃣ Dispose the session
		kieSession.dispose();
	}
}
