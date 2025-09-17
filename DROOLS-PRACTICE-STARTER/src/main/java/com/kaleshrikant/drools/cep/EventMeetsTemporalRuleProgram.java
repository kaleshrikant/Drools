package com.kaleshrikant.drools.cep;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import com.kaleshrikant.drools.cep.model.TaskEvent ;
import com.kaleshrikant.drools.cep.model.WebinarEvent ;

import java.util.Date;

/**
 *📡 EventMeetsTemporalRuleProgram via Drools CEP
 *
 * 🧾 Detects exact temporal adjacency using `meets`:
 * ────────────────────────────────────────────────────────────
 * ✅ Fires when WebinarEvent starts exactly when TaskEvent ends
 * 🧠 Uses:
 *    • @role(event)
 *    • @timestamp(startTime)
 *    • @duration(duration)
 *    • Temporal operator: `meets`
 *
 * 📤 Inserts TaskEvent and WebinarEvent into KieSession
 * 🧹 Session disposed after rule execution
 *
 * 📋 DRL Rule:
 * rule "Task Meets Webinar Rule"
 * when
 *     $task : TaskEvent()
 *     $webinar : WebinarEvent( this meets $task )
 * then
 *     System.out.println("✅ Rule triggered: Task '" + $task.getName() +
 *                        "' ends exactly when Webinar '" + $webinar.getName() + "' starts!");
 * end
 *
 * 🖨️ Sample Output:
 * ✅ Rule triggered: Task 'DataMigration' ends exactly when Webinar 'SystemDemo' starts!
 * 🔥 Rules fired: 1
 * @author Shrikant Kale
 * @Date 17 Sep 2025
 */

public class EventMeetsTemporalRuleProgram {
	public static void main(String[] args) {
		try {
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			KieSession kSession = kContainer.newKieSession("ksession-rules");

			long now = System.currentTimeMillis();

			// Task lasts 5 seconds
			TaskEvent task = new TaskEvent("DataMigration", new Date(now), 5000);
			kSession.insert(task);

			// Webinar starts exactly when Task ends
			WebinarEvent webinar = new WebinarEvent("SystemDemo", new Date(now + 5000), 4000);
			kSession.insert(webinar);

			int fired = kSession.fireAllRules();
			System.out.println("🔥 Rules fired: " + fired);

			kSession.dispose();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
