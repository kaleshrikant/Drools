package com.kaleshrikant.drools.controller;

import com.kaleshrikant.drools.model.Applicant;
import com.kaleshrikant.drools.model.LoanApplication;
import com.kaleshrikant.drools.service.RuleEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Shrikant Kale
 * @Date 30 Sep 2025
 */
@RestController
@RequestMapping("/api/rules")
public class RuleEngineController {
	private final RuleEngineService ruleEngineService;

	@Autowired
	public RuleEngineController(RuleEngineService ruleEngineService) {
		this.ruleEngineService = ruleEngineService;
	}

	@PostMapping("/evaluate-applicant")
	public Applicant evaluateApplicant(@RequestBody Applicant applicant) {
		return ruleEngineService.evaluateApplicant(applicant);
	}

	@PostMapping("/evaluate-loan")
	public LoanApplication evaluateLoanApplication(@RequestBody LoanApplication loanApplication) {
		return ruleEngineService.evaluateLoanApplication(loanApplication);
	}

	@GetMapping("/packages")
	public String getPackages() {
		return """
               Rules organized by packages:
               - rules.applicant: Applicant evaluation rules
               - rules.loan: Loan application rules
               - Use /evaluate-applicant for applicant rules
               - Use /evaluate-loan for loan rules
               """;
	}

	@GetMapping("/health")
	public String health() {
		return "Drools Rule Engine with HOT Deployment is running!";
	}

	@GetMapping("/info")
	public String info() {
		return """
               HOT Deployment Rule Engine
               =========================
               Features:
               🔥 Dynamic Rule Reloading
               📁 External Rule Directory: hot-deployment/
               ⏰ Auto-monitoring every 5s
               🔄 Manual reload via API
               
               Packages:
               - rules.applicant: Applicant evaluation
               - rules.loan: Loan processing
               
               HOT Deployment Endpoints:
               - GET  /api/hot-deployment/status
               - POST /api/hot-deployment/reload
               """;
	}
}
