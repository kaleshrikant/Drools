package com.kaleshrikant.drools;

import com.kaleshrikant.drools.model.Applicant;
import com.kaleshrikant.drools.model.LoanApplication;
import com.kaleshrikant.drools.service.RuleEngineService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DroolsSpringbootExampleApplicationTests {

	@Autowired
	private RuleEngineService ruleEngineService;

	@Test
	void testApplicantApproval() {
		Applicant applicant = new Applicant("John Doe", 28, 4.5, 50000, 65000);
		Applicant result = ruleEngineService.evaluateApplicant(applicant);

		assertEquals("APPROVED", result.getStatus());
		System.out.println("Test Result: " + result);
	}

	@Test
	void testLoanApproval() {
		LoanApplication loan = new LoanApplication("Jane Smith", 25000, 36, 75000, 780);
		LoanApplication result = ruleEngineService.evaluateLoanApplication(loan);

		assertTrue(result.isApproved());
		assertEquals(5.5, result.getInterestRate());
		System.out.println("Test Result: " + result);
	}

}
