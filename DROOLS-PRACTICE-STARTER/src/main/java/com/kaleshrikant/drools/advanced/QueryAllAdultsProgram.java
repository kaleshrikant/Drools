package com.kaleshrikant.drools.advanced;

import com.kaleshrikant.drools.core.model.Person;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import java.util.ArrayList;
import java.util.List;

/**
 * 🔍 QueryAllAdultsProgram
 *
 * 🎯 Purpose: Demonstrate Drools `query` to fetch all adults (age >= 18).
 * 🧊 Facts: Person objects representing users.
 * ➕ Conditions: Apply a query instead of standard rules.
 * 🔥 Actions: Return all matched adults via query API.
 * 📂 Resources: DRL file with query definition.
 * 📘 Learning: How to use `query` to retrieve facts matching criteria.
 *
 * OUTPUT :
 *  ✅ Adult found: Prashant (25)
 *  ✅ Adult found: Shrikant (32)
 *
 * @author Shrikant Kale
 * @Date 27 Sep 2025
 */

public class QueryAllAdultsProgram {
	public static void main(String[] args) {
		try {
			// 🚀 Bootstrapping Drools
			KieServices ks = KieServices.Factory.get();
			KieContainer kc = ks.getKieClasspathContainer();
			KieSession kSession = kc.newKieSession("ksession-rules");

			List<String> logCollector = new ArrayList<>();
			kSession.setGlobal("logCollector", logCollector);

			// ➕ Insert Facts
			kSession.insert(new Person("Prashant", 25));
			kSession.insert(new Person("Bob", 16));
			kSession.insert(new Person("Shrikant", 32));
			kSession.insert(new Person("Daisy", 12));

			// 📌 Execute query instead of firing rules
			QueryResults results = kSession.getQueryResults("all adults");
			for (QueryResultsRow row : results) {
				Person adult = (Person) row.get("adult");
				logCollector.add("✅ Adult found: " + adult.getName() + " (" + adult.getAge() + ")");
			}

			// 📜 Print results
			logCollector.forEach(System.out::println);

			kSession.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
