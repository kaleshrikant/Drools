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

	@PostMapping("/evaluate-applicant-all")
	public Applicant evaluateApplicantWithAllRules(@RequestBody Applicant applicant) {
		return ruleEngineService.evaluateApplicant(applicant); // Using same method for now
	}

	@GetMapping("/packages")
	public String getPackages() {
		return """
				Rules organized by packages:
				- rules.applicant: Applicant evaluation rules
				- rules.loan: Loan application rules
				- Use /evaluate-applicant for applicant rules only
				- Use /evaluate-loan for loan rules only  
				- Use /evaluate-applicant-all for all rules combined
				""";
	}

	@GetMapping("/health")
	public String health() {
		return "Drools Rule Engine with Package-Level Organization is running!";
	}

	@GetMapping("/info")
	public String info() {
		return """
				Package-Level Rule Organization Demo
				===================================
				This application demonstrates organizing Drools rules 
				into separate packages for better maintainability.
				
				Packages:
				- rules.applicant: Contains 5 rules for applicant evaluation
				- rules.loan: Contains 6 rules for loan application processing
				
				Each package is loaded in its own KIE Base and Session.
				""";
	}
}
