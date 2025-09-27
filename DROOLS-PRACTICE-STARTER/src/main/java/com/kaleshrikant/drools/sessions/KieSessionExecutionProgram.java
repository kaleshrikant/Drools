package com.kaleshrikant.drools.sessions;

import com.kaleshrikant.drools.sessions.model.Client;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.List;

/**
 * 🎯 Purpose: Demonstrates firing rules using a Stateful KieSession
 * 🧊 Facts: Client (name, age, city)
 * ➕ Conditions: Age-based rules
 * 🔥 Actions: Assign category and log results
 * 📂 Resources: rules/KieSessionExecution.drl
 * 📘 Learning: Shows insert, update, retract, and rule firing using Stateful KieSession
 *
 * === Expected Output ===
 * Initial Clients:
 * Client{name='Shrikant', age=34, city='Pune', category=''}
 * Client{name='Prashant', age=22, city='Mumbai', category=''}
 * Client{name='Pratibha', age=68, city='Amravati', category=''}
 *
 * After Rules Fired:
 * Client{name='Shrikant', age=34, city='Pune', category='Adult'}
 * Client{name='Prashant', age=22, city='Mumbai', category='Young Adult'}
 * Client{name='Pratibha', age=68, city='Amravati', category='Senior'}
 *
 * Rule Logs:
 * 🧑 Adult category assigned to Shrikant
 * 🧒 Young Adult category assigned to Prashant
 * 👵 Senior category assigned to Pratibha
 *
 * @author Shrikant Kale
 * @Date 27 Sep 2025
 */

public class KieSessionExecutionProgram {
	public static void main(String[] args) {
		try {
			// 🔹 Setup Drools
			KieServices ks = KieServices.Factory.get();
			KieContainer kc = ks.getKieClasspathContainer();
			KieSession ksession = kc.newKieSession("sessionKSession");

			// 🔹 Global log collector
			List<String> logCollector = new ArrayList<>();
			ksession.setGlobal("logCollector", logCollector);

			// 🔹 Insert Client facts
			Client client1 = new Client("Shrikant", 34, "Pune");
			Client client2 = new Client("Prashant", 22, "Mumbai");
			Client client3 = new Client("Pratibha", 68, "Amravati");

			ksession.insert(client1);
			ksession.insert(client2);
			ksession.insert(client3);

			System.out.println("=== Initial Clients ===");
			System.out.println(client1);
			System.out.println(client2);
			System.out.println(client3);

			// 🔹 Fire rules
			ksession.fireAllRules();

			System.out.println("\n=== Clients After Rules Fired ===");
			System.out.println(client1);
			System.out.println(client2);
			System.out.println(client3);

			System.out.println("\n=== Rule Logs ===");
			logCollector.forEach(System.out::println);

			// 🔹 Dispose session
			ksession.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
