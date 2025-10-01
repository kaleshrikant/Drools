package com.kaleshrikant.drools.controller;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shrikant Kale
 * @Date 30 Sep 2025
 */

@RestController
@RequestMapping("/api/debug")
public class DebugController {
	@Autowired
	private KieContainer kieContainer;

	@GetMapping("/kie-info")
	public String getKieInfo() {
		StringBuilder info = new StringBuilder();
		info.append("=== KIE CONTAINER DEBUG INFO ===\n");
		info.append("KieContainer Available: ").append(kieContainer != null).append("\n");

		if(kieContainer != null) {
			try {
				KieSession session = kieContainer.newKieSession();
				info.append("✓ Session Creation: SUCCESS\n");

				int rulesFired = session.fireAllRules();
				info.append("✓ Rules Fired in Test: ").append(rulesFired).append("\n");

				session.dispose();
				info.append("✓ Session Disposal: SUCCESS\n");

			} catch(Exception e) {
				info.append("✗ Session Test Failed: ").append(e.getMessage()).append("\n");
			}

			try {
				var releaseId = kieContainer.getReleaseId();
				info.append("Release ID: ").append(releaseId.getGroupId()).append(":").append(releaseId.getArtifactId()).append(":").append(releaseId.getVersion()).append("\n");
			} catch(Exception e) {
				info.append("Release ID: Unable to retrieve\n");
			}
		} else {
			info.append("✗ KieContainer is NULL - Check configuration\n");
		}

		info.append("=================================\n");
		return info.toString();
	}

	@GetMapping("/test-rules")
	public String testRules() {
		try {
			KieSession session = kieContainer.newKieSession();
			int rulesFired = session.fireAllRules();
			session.dispose();

			return """
					{
					  "status": "success",
					  "message": "Rule engine test completed",
					  "rulesFired": %d,
					  "timestamp": "%s"
					}
					""".formatted(rulesFired, java.time.LocalDateTime.now());
		} catch(Exception e) {
			return """
					{
					  "status": "error",
					  "message": "Rule engine test failed: %s",
					  "timestamp": "%s"
					}
					""".formatted(e.getMessage(), java.time.LocalDateTime.now());
		}
	}

	@GetMapping("/system-info")
	public String getSystemInfo() {
		return """
				System Information
				=================
				Java Version: %s
				Spring Boot: 3.5.5
				Drools Version: 9.44.0.Final
				Available Processors: %d
				Max Memory: %d MB
				Free Memory: %d MB
				Total Memory: %d MB
				""".formatted(System.getProperty("java.version"), Runtime.getRuntime().availableProcessors(), Runtime.getRuntime().maxMemory() / (1024 * 1024), Runtime.getRuntime().freeMemory() / (1024 * 1024), Runtime.getRuntime().totalMemory() / (1024 * 1024));
	}
}
