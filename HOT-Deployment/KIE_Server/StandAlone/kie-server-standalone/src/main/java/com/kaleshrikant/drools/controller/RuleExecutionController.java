package com.kaleshrikant.drools.controller;

import com.kaleshrikant.drools.model.Applicant;
import com.kaleshrikant.drools.model.LoanApplication;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Shrikant Kale
 * @Date 01 Oct 2025
 */

@RestController
@RequestMapping("/api/rules")
public class RuleExecutionController {
	private static final Logger logger = LoggerFactory.getLogger(RuleExecutionController.class);

	@Autowired
	private KieContainer kieContainer;

	@PostMapping("/validate-applicant")
	public Applicant validateApplicant(@RequestBody Applicant applicant) {
		try {
			logger.info("=== Starting applicant validation ===");
			logger.info("Applicant: {}", applicant);
			logger.info("KieContainer: {}", kieContainer);

			// List all available sessions
			logger.info("Available KIE Bases: {}", kieContainer.getKieBaseNames());

			KieSession kieSession = null;
			try {
				logger.info("Creating KieSession 'rulesSession'...");
				kieSession = kieContainer.newKieSession("rulesSession");
				logger.info("KieSession created successfully: {}", kieSession);

				logger.info("Inserting applicant into session...");
				kieSession.insert(applicant);

				logger.info("Firing all rules...");
				int firedRules = kieSession.fireAllRules();
				logger.info("Rules fired: {}", firedRules);

				logger.info("Final applicant: {}", applicant);
				return applicant;

			} finally {
				if (kieSession != null) {
					logger.info("Disposing KieSession...");
					kieSession.dispose();
				}
			}

		} catch (Exception e) {
			logger.error("=== ERROR in validate-applicant ===", e);
			applicant.setValidationMessage("Error: " + e.getMessage());
			return applicant;
		}
	}

	@PostMapping("/process-loan")
	public LoanApplication processLoan(@RequestBody LoanApplication loanApplication) {
		try {
			logger.info("Processing loan for: {}", loanApplication.getApplicantName());

			KieSession kieSession = kieContainer.newKieSession("rulesSession"); // ← FIXED: newKieSession
			kieSession.insert(loanApplication);
			int firedRules = kieSession.fireAllRules();
			kieSession.dispose();

			logger.info("Rules fired: {}", firedRules);
			logger.info("Loan result: {}", loanApplication);

			return loanApplication;
		} catch (Exception e) {
			logger.error("Error processing loan", e);
			loanApplication.setRejectionReason("Error: " + e.getMessage());
			return loanApplication;
		}
	}

	@GetMapping("/test")
	public String test() {
		try {
			logger.info("Testing KieContainer...");
			logger.info("KieContainer: {}", kieContainer);
			logger.info("KieBase names: {}", kieContainer.getKieBaseNames());
			return "KieContainer is working. Available KIE Bases: " + kieContainer.getKieBaseNames();
		} catch (Exception e) {
			logger.error("Test failed", e);
			return "Test failed: " + e.getMessage();
		}
	}
}
