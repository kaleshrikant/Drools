package com.kaleshrikant.drools.keywords;

import com.kaleshrikant.drools.keywords.model.KeywordInput;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 🏙️ City Extraction from Keyword via Drools
 *
 * 🧾 Extracts city name from KeywordInput using regex match:
 *    🏙️ BANGALORE → matches "bengaluru" or "bangalore"
 *    🏙️ MUMBAI → matches "mumbai"
 *    🏙️ PUNE → matches "pune"
 *
 * 📤 Inserts keyword phrases into KieSession
 * ✅ Sets city field in KeywordInput if matched
 * 🔁 update() triggers downstream rules
 *
 * 🖨️ Sample Output:
 * 🏙️ Extracted city: Bangalore from keyword 'Best food in Bangalore East'
 * 🏙️ Extracted city: Mumbai from keyword 'Mumbai local train timings'
 * 🏙️ Extracted city: Pune from keyword 'Weather update for Pune'
 *
 * @author Shrikant Kale
 * @Date 11 Sep 2025
 */

public class CityExtractionFromKeywordProgram {
	private static final Logger logger = LoggerFactory.getLogger(CityExtractionFromKeywordProgram.class);
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
		kieSession.insert(new KeywordInput("Best food in Bangalore East"));
		kieSession.insert(new KeywordInput("Mumbai local train timings"));
		kieSession.insert(new KeywordInput("Weather update for Pune"));

		// 3️⃣ Fire all rules
		kieSession.fireAllRules();

		// 4️⃣ Dispose the session
		kieSession.dispose();
	}
}
