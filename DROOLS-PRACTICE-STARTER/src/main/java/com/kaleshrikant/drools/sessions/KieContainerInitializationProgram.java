package com.kaleshrikant.drools.sessions;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;

/**
 * 🎯 Purpose: Demonstrates KieContainer initialization and verification
 * 🧊 Facts: N/A
 * ➕ Conditions: N/A
 * 🔥 Actions: Initializes KieContainer and prints available KieBases and KieSessions
 * 📂 Resources: META-INF/kmodule.xml
 * 📘 Learning: Shows how to programmatically create a KieContainer and verify setup
 *
 * @author Shrikant Kale
 * @Date 27 Sep 2025
 */

public class KieContainerInitializationProgram {
	public static void main(String[] args) {
		try {
			// 🔹 Initialize KieServices
			KieServices ks = KieServices.Factory.get();

			// 🔹 Create KieContainer
			KieContainer kc = ks.getKieClasspathContainer();

			// 🔹 Print available KieBases
			System.out.println("=== KieBases available in this container ===");
			kc.getKieBaseNames().forEach(System.out::println);

			System.out.println("\n✅ KieContainer successfully initialized!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
