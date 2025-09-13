package com.kaleshrikant.drools.keywords;

import com.kaleshrikant.drools.keywords.model.PersonaData;
import com.kaleshrikant.drools.keywords.model.DependentFact;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 🧹 RemoveChildFactProgram via Drools
 *
 * 🧾 Removes DependentFact entries for senior PersonaData (age > 60)
 *    👤 Matches parent by name
 *    🧹 Retracts associated child facts from working memory
 *
 * 📤 Inserts parent and child facts into KieSession
 * ✅ Uses retract() to clean up dependents
 * 🧹 Session disposed after rule execution
 *
 * 🖨️ Sample Output:
 *  🧹 Removed DependentFact for child 'ChildA' of senior 'Shrikant'
 *  🧹 Removed DependentFact for child 'ChildB' of senior 'Shrikant'
 *
 * @author Shrikant Kale
 * @Date 12 Sep 2025
 */

/*
   If the parent meets certain conditions (e.g. age > 60),
   remove all associated dependents from working memory.
 */

public class RemoveChildFactProgram {
	private static final Logger logger = LoggerFactory.getLogger(RemoveChildFactProgram.class);
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
			// Insert parent facts
				kieSession.insert(new PersonaData("Shrikant", 65));
				kieSession.insert(new PersonaData("Meera", 45));

			// Insert child facts
				kieSession.insert(new DependentFact("Shrikant", "ChildA"));
				kieSession.insert(new DependentFact("Shrikant", "ChildB"));
				kieSession.insert(new DependentFact("Meera", "ChildC"));

		// 3️⃣ Fire all rules
		kieSession.fireAllRules();

		// 4️⃣ Dispose the session
		kieSession.dispose();
	}
}
