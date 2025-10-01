package com.kaleshrikant.drools.service;

import com.kaleshrikant.drools.model.Applicant;
import com.kaleshrikant.drools.model.LoanApplication;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Shrikant Kale
 * @Date 30 Sep 2025
 */

@Service
public class RuleEngineService {

	private final HotDeploymentService hotDeploymentService;

	@Autowired
	public RuleEngineService(HotDeploymentService hotDeploymentService) {
		this.hotDeploymentService = hotDeploymentService;
		System.out.println("🔥 RuleEngineService initialized with HOT Deployment");
	}

	public Applicant evaluateApplicant(Applicant applicant) {
		System.out.println("=== EVALUATING APPLICANT (HOT DEPLOYMENT) ===");
		System.out.println("Applicant: " + applicant.getName());

		KieSession session = null;
		try {
			// Try to use HOT deployment session first
			if(hotDeploymentService.isHotDeploymentActive()) {
				session = hotDeploymentService.newKieSession();
				System.out.println("🔥 Using HOT Deployment rules");
			} else {
				// Fallback to original KieContainer (you'll need to autowire it)
				throw new IllegalStateException("HOT Deployment not active - add rules to hot-deployment directory");
			}

			session.insert(applicant);
			int firedRules = session.fireAllRules();
			System.out.println("🔥 Fired " + firedRules + " HOT rules for: " + applicant.getName());
			System.out.println("Result: " + applicant.getStatus() + " - " + applicant.getMessage());
			System.out.println("==========================================");
			return applicant;
		} catch(Exception e) {
			System.err.println("Error evaluating applicant: " + e.getMessage());
			applicant.setStatus("ERROR");
			applicant.setMessage("Rule evaluation failed: " + e.getMessage());
			return applicant;
		} finally {
			if(session != null) {
				session.dispose();
			}
		}
	}

	public LoanApplication evaluateLoanApplication(LoanApplication loanApplication) {
		System.out.println("=== EVALUATING LOAN (HOT DEPLOYMENT) ===");
		System.out.println("Applicant: " + loanApplication.getApplicantName());

		KieSession session = null;
		try {
			// Try to use HOT deployment session first
			if(hotDeploymentService.isHotDeploymentActive()) {
				session = hotDeploymentService.newKieSession();
				System.out.println("🔥 Using HOT Deployment rules");
			} else {
				// Fallback to original KieContainer (you'll need to autowire it)
				throw new IllegalStateException("HOT Deployment not active - add rules to hot-deployment directory");
			}

			session.insert(loanApplication);
			int firedRules = session.fireAllRules();
			System.out.println("🔥 Fired " + firedRules + " HOT rules for: " + loanApplication.getApplicantName());
			System.out.println("Result: " + (loanApplication.isApproved() ? "APPROVED" : "REJECTED"));
			if(! loanApplication.isApproved()) {
				System.out.println("Rejection Reason: " + loanApplication.getRejectionReason());
			} else {
				System.out.println("Interest Rate: " + loanApplication.getInterestRate() + "%");
			}
			System.out.println("======================================");
			return loanApplication;
		} catch(Exception e) {
			System.err.println("Error evaluating loan application: " + e.getMessage());
			loanApplication.setApproved(false);
			loanApplication.setRejectionReason("Rule evaluation failed: " + e.getMessage());
			return loanApplication;
		} finally {
			if(session != null) {
				session.dispose();
			}
		}
	}
}
