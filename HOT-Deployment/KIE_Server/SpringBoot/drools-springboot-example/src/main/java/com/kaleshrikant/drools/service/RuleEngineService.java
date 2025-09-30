package com.kaleshrikant.drools.service;

import com.kaleshrikant.drools.model.Applicant;
import com.kaleshrikant.drools.model.LoanApplication;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Shrikant Kale
 * @Date 30 Sep 2025
 */

@Service
public class RuleEngineService {

	private final KieContainer kieContainer;

	@Autowired
	public RuleEngineService(KieContainer kieContainer) {
		this.kieContainer = kieContainer;
		System.out.println("RuleEngineService initialized with KieContainer: " + (kieContainer != null));
	}

	public Applicant evaluateApplicant(Applicant applicant) {
		System.out.println("=== EVALUATING APPLICANT ===");
		System.out.println("Applicant: " + applicant.getName());

		KieSession session = null;
		try {
			// Create a new session from the container
			session = kieContainer.newKieSession();

			if (session == null) {
				throw new IllegalStateException("Failed to create KieSession from KieContainer");
			}

			System.out.println("KieSession created successfully");
			session.insert(applicant);
			int firedRules = session.fireAllRules();
			System.out.println("Fired " + firedRules + " rules for: " + applicant.getName());
			System.out.println("Result: " + applicant.getStatus() + " - " + applicant.getMessage());
			System.out.println("==========================");
			return applicant;
		} catch (Exception e) {
			System.err.println("Error evaluating applicant: " + e.getMessage());
			applicant.setStatus("ERROR");
			applicant.setMessage("Rule evaluation failed: " + e.getMessage());
			return applicant;
		} finally {
			if (session != null) {
				session.dispose();
				System.out.println("KieSession disposed");
			}
		}
	}

	public LoanApplication evaluateLoanApplication(LoanApplication loanApplication) {
		System.out.println("=== EVALUATING LOAN ===");
		System.out.println("Applicant: " + loanApplication.getApplicantName());

		KieSession session = null;
		try {
			// Create a new session from the container
			session = kieContainer.newKieSession();

			if (session == null) {
				throw new IllegalStateException("Failed to create KieSession from KieContainer");
			}

			System.out.println("KieSession created successfully");
			session.insert(loanApplication);
			int firedRules = session.fireAllRules();
			System.out.println("Fired " + firedRules + " rules for: " + loanApplication.getApplicantName());
			System.out.println("Result: " + (loanApplication.isApproved() ? "APPROVED" : "REJECTED"));
			if (!loanApplication.isApproved()) {
				System.out.println("Rejection Reason: " + loanApplication.getRejectionReason());
			} else {
				System.out.println("Interest Rate: " + loanApplication.getInterestRate() + "%");
			}
			System.out.println("======================================");
			return loanApplication;
		} catch (Exception e) {
			System.err.println("Error evaluating loan application: " + e.getMessage());
			loanApplication.setApproved(false);
			loanApplication.setRejectionReason("Rule evaluation failed: " + e.getMessage());
			return loanApplication;
		} finally {
			if (session != null) {
				session.dispose();
				System.out.println("KieSession disposed");
			}
		}
	}
}
