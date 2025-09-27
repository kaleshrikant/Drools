package com.kaleshrikant.drools.sessions;

import com.kaleshrikant.drools.sessions.model.Client;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

import java.util.ArrayList;
import java.util.List;

/**
 * 🎯 Purpose: Demonstrates a Stateless KieSession with Client facts
 * 🧊 Facts: Client (name, age, city)
 * ➕ Conditions: Age-based rules
 * 🔥 Actions: Assign category and log results
 * 📂 Resources: rules/client-stateless-rules.drl
 * 📘 Learning: Stateless sessions do not maintain working memory across invocations
 *
 * === Expected Output ===
 * Input Clients:
 *  Client{name='Shrikant', age=34, city='Pune', category=''}
 *  Client{name='Prashant', age=22, city='Mumbai', category=''}
 *  Client{name='Pratibha', age=68, city='Amravati', category=''}
 *
 * After Rules Fired:
 *  Client{name='Shrikant', age=34, city='Pune', category='Adult'}
 *  Client{name='Prashant', age=22, city='Mumbai', category='Young Adult'}
 *  Client{name='Pratibha', age=68, city='Amravati', category='Senior'}
 *
 * Rule Logs:
 *  🧑 Adult category assigned to Shrikant
 *  🧒 Young Adult category assigned to Prashant
 *  👵 Senior category assigned to Pratibha
 *
 * @author Shrikant Kale
 * @Date 27 Sep 2025
 */

public class StatelessPersonSessionProgram {
	public static void main(String[] args) {
		try {
			// 🔹 Setup Drools
			KieServices ks = KieServices.Factory.get();
			KieContainer kc = ks.getKieClasspathContainer();
			StatelessKieSession statelessKieSession = kc.newStatelessKieSession("statelessKSession");

			// 🔹 Global log collector
			List<String> logCollector = new ArrayList<>();
			statelessKieSession.setGlobal("logCollector", logCollector);

			// 🔹 Create Person facts
			Client client1 = new Client("Shrikant", 34, "Pune");
			Client client2 = new Client("Prashant", 22, "Mumbai");
			Client client3 = new Client("Pratibha", 68, "Amravati");

			System.out.println("=== Input Persons ===");
			System.out.println(client1);
			System.out.println(client2);
			System.out.println(client3);

			// 🔹 Execute rules in one go
			List<Object> facts = new ArrayList<>();
			facts.add(client1);
			facts.add(client2);
			facts.add(client3);
			statelessKieSession.execute(facts);

			// 🔹 Print results
			System.out.println("\n=== Persons After Rules Fired ===");
			System.out.println(client1);
			System.out.println(client2);
			System.out.println(client3);

			System.out.println("\n=== Rule Logs ===");
			logCollector.forEach(System.out::println);

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
