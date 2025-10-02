package com.kaleshrikant.core.hello;

import com.kaleshrikant.core.hello.model.Person;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 👋 HelloDroolsProgram - Your First Drools Experience
 *
 * 🎯 Introduction to basic Drools rule engine:
 *    📜 Loads rules from hello-rules.drl
 *    🧠 Creates KieSession for rule execution
 *    🌐 Uses logCollector global variable for output tracking
 *    🏃 Fires all matched rules and displays results
 *
 * 🧊 Expected Facts: Add your domain objects to working memory
 * 💾 Working Memory: Holds facts for rule pattern matching
 * 📢 NOTE: This is your starting point for Drools learning!
 *
 * 💻▶ Sample Output:
 * 🔔 Welcome to Drools Rule Engine!
 * ✅ Hello rule executed successfully
 * 🎉 First rule engine experience completed
 *
 * @author Shrikant Kale
 * @since 2024
 */
public class HelloDroolsProgram {
    public static void main(String[] args) {
        // 🔧 Initialize KIE Services
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("coreHelloSession");

        // 🌐 Set up global log collector
        List<String> logCollector = new ArrayList<>();
        kieSession.setGlobal("logCollector", logCollector);

        // 🧊 Inserting Facts
         kieSession.insert(new Person("Shrikant", 30));

        // 🏃 Fire all rules
        kieSession.fireAllRules();

        // 💻▶ Print collected logs
        System.out.println("=== 📋 Log Output ===");
        for (String log : logCollector) {
            System.out.println("🔔 " + log);
        }

        // 🧹 Clean up session
        kieSession.dispose();
        System.out.println("\n🎉 HelloDroolsProgram executed successfully!");
    }
}
