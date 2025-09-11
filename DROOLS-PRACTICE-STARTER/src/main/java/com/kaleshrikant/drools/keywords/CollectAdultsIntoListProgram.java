package com.kaleshrikant.drools.keywords;

import com.kaleshrikant.drools.keywords.model.Individual;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shrikant Kale
 * @Date 11 Sep 2025
 */

public class CollectAdultsIntoListProgram {
	private static final Logger logger = LoggerFactory.getLogger(CollectAdultsIntoListProgram.class);
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
		List<Individual> adultList = new ArrayList<>();
			kieSession.setGlobal("adultList", adultList);

			kieSession.insert(new Individual("Aarav", 22));
			kieSession.insert(new Individual("Meera", 17));
			kieSession.insert(new Individual("Rohan", 35));
			kieSession.insert(new Individual("Isha", 15));

		// 3️⃣ Fire all rules
		kieSession.fireAllRules();

		// 4️⃣ Dispose the session
		kieSession.dispose();

		logger.info("✅ Final list of adults:");
		for (Individual i : adultList) {
			logger.info("🧑‍💼 {} (age {})", i.getName(), i.getAge());
		}
	}
}
