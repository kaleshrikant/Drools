package com.kaleshrikant.drools.controller;

import com.kaleshrikant.drools.service.HotDeploymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shrikant Kale
 * @Date 30 Sep 2025
 */


@RestController
@RequestMapping("/api/hot-deployment")
public class HotDeploymentController {
	@Autowired
	private HotDeploymentService hotDeploymentService;

	@PostMapping("/reload")
	public String reloadRules() {
		try {
			hotDeploymentService.reloadRulesManually();
			return """
					{
					  "status": "success",
					  "message": "Rules reloaded successfully via HOT deployment!",
					  "timestamp": "%s"
					}
					""".formatted(java.time.LocalDateTime.now());
		} catch(Exception e) {
			return """
					{
					  "status": "error", 
					  "message": "HOT deployment failed: %s",
					  "timestamp": "%s"
					}
					""".formatted(e.getMessage(), java.time.LocalDateTime.now());
		}
	}

	@GetMapping("/status")
	public String getStatus() {
		return hotDeploymentService.getHotDeploymentStatus();
	}

	@GetMapping("/info")
	public String getInfo() {
		return """
				HOT Deployment Features
				======================
				🔥 Dynamic Rule Reloading
				📁 External Rule Directory: hot-deployment/
				⏰ Automatic Monitoring: Every 5 seconds
				🔄 Manual Reload: POST /api/hot-deployment/reload
				📊 Status Check: GET /api/hot-deployment/status
				
				How to Use:
				1. Add DRL files to hot-deployment/ directory:
				   - hot-deployment/applicant/applicant-rules.drl
				   - hot-deployment/loan/loan-rules.drl
				
				2. Rules auto-reload within 5 seconds of file changes
				3. Or trigger manual reload via API
				
				4. Test with:
				   - POST /api/rules/evaluate-applicant
				   - POST /api/rules/evaluate-loan
				
				Note: The application must be running for HOT deployment to work.
				Rules are reloaded without restarting the application!
				""";
	}

	@GetMapping("/active")
	public String checkActive() {
		boolean isActive = hotDeploymentService.isHotDeploymentActive();
		if(isActive) {
			return """
					{
					  "status": "active",
					  "message": "HOT Deployment is active and serving rules",
					  "rulesLoaded": %d,
					  "timestamp": "%s"
					}
					""".formatted(hotDeploymentService.getHotDeploymentStatus().length(), java.time.LocalDateTime.now());
		} else {
			return """
					{
					  "status": "inactive", 
					  "message": "HOT Deployment is inactive - no rules loaded from hot-deployment directory",
					  "action": "Add DRL files to hot-deployment/applicant/ and hot-deployment/loan/",
					  "timestamp": "%s"
					}
					""".formatted(java.time.LocalDateTime.now());
		}
	}
}
