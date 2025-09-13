package com.kaleshrikant.drools.keywords;

import com.kaleshrikant.drools.keywords.model.AdultCandidate;
import com.kaleshrikant.drools.keywords.model.PhraseInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
/**
 * 🧑‍💼 AllAdultsForallKeywordProgram via Drools
 *
 * 🧾 Collects AdultCandidate facts (age ≥ 18) for each PhraseInput
 *    📤 Inserts candidates and phrases into KieSession
 *    🔍 Rule matches adults and stores them in PhraseInput.adults
 *    🧑‍💼 Logs each matched adult with name and age
 *
 * ✅ Uses SLF4J logger for structured output
 * 🔁 update() triggers downstream rule evaluation
 * 🧹 Session disposed after rule execution
 *
 * 🖨️ Sample Output:
 * 🔍 Phrase 'Adults for Pune' matched 2 adults
 * 🧑‍💼 Aarav (age 22)
 * 🧑‍💼 Rohan (age 35)
 *
 * 🔍 Phrase 'Adults for Mumbai' matched 2 adults
 * 🧑‍💼 Aarav (age 22)
 * 🧑‍💼 Rohan (age 35)
 *
 * @author Shrikant Kale
 * @Date 11 Sep 2025
 */

public class AllAdultsForallKeywordProgram {
	private static final Logger logger = LoggerFactory.getLogger(AllAdultsForallKeywordProgram.class);

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
			// AdultCandidates
				kieSession.insert(new AdultCandidate("Aarav", 22));
				kieSession.insert(new AdultCandidate("Meera", 17));
				kieSession.insert(new AdultCandidate("Rohan", 35));
				kieSession.insert(new AdultCandidate("Isha", 15));

			// PhraseInputs
				kieSession.insert(new PhraseInput("Adults for Pune"));
				kieSession.insert(new PhraseInput("Adults for Mumbai"));

		// 3️⃣ Fire all rules
		kieSession.fireAllRules();

		// 4️⃣ Dispose the session
		kieSession.dispose();
	}
}
