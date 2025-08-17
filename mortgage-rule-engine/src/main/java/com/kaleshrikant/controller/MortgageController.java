package com.kaleshrikant.controller;

import com.kaleshrikant.model.MortgageApplication;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shrikant Kale
 * @Date 8/17/25
 */

@RestController
@RequestMapping("/api/mortgage")
public class MortgageController {

	@Autowired
	private KieContainer kieContainer;

	@PostMapping("/apply")
	public MortgageApplication apply(@RequestBody MortgageApplication application) {
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.insert(application);
		kieSession.fireAllRules();
		kieSession.dispose();
		return application;
	}
}
