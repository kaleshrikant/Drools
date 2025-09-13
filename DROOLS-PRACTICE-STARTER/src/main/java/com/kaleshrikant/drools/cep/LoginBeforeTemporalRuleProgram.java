package com.kaleshrikant.drools.cep;

import com.kaleshrikant.drools.cep.model.LoginEvent;
import com.kaleshrikant.drools.cep.model.PurchaseEvent;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Shrikant Kale
 * @Date 13 Sep 2025
 */

public class LoginBeforeTemporalRuleProgram {
	public static void main(String[] args) {
		// 1️⃣ Get KieServices
		KieServices kieServices = KieServices.Factory.get();

		// 2️️⃣ Get KieContainer
		KieContainer kieContainer = kieServices.getKieClasspathContainer();

		// 3️️⃣ Create KieSession directly
		KieSession kieSession = kieContainer.newKieSession("ksession-rules"); // match name in kmodule.xml

		// 4️⃣Inject logger into Drools
		List<String> logCollector = new ArrayList<>();
		kieSession.setGlobal("logCollector", logCollector);

		// 5️⃣Event Insertion
		LocalDateTime now = LocalDateTime.now();
		long loginTs = now.plusMinutes(5).toInstant(ZoneOffset.UTC).toEpochMilli();
		long purchaseTs = now.plusMinutes(10).toInstant(ZoneOffset.UTC).toEpochMilli();

		LoginEvent login = new LoginEvent("Shrikant", loginTs);
		PurchaseEvent purchase = new PurchaseEvent("Shrikant", purchaseTs);

		kieSession.insert(login);
		kieSession.insert(purchase);

		// 6️⃣Fire all rules
		kieSession.fireAllRules();

		// 7️⃣ Dispose the session
		kieSession.dispose();

		// 8️⃣ Print results
		System.out.println("Results:");
		if (logCollector.isEmpty()) {
			System.out.println("No rules fired.");
		} else {
			logCollector.forEach(System.out::println);
		}
	}
}
