package com.kaleshrikant.drools.keywords;

import com.kaleshrikant.drools.keywords.model.KeywordInput;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
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
