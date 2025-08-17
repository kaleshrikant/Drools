package com.kaleshrikant;

import com.kaleshrikant.model.PaymentOffer;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;


public class Main {
	public static void main(String[] args) {
		// 1. Creating : Service
			KieServices services = KieServices.Factory.get();

		// 2. Creating : Container
			KieContainer container= services.getKieClasspathContainer();

		// 3. Creating : Session
			KieSession session = container.newKieSession("complex-ksession-rule");

		// 4. Creating : Facts
		PaymentOffer payTmOffer = new PaymentOffer();
			payTmOffer.setChannel("paytm");
			payTmOffer.setFestival("xmas");
			payTmOffer.setFirstTimeCustomer(true);

		// 4. Inserting : Facts
			FactHandle factHandle = session.insert(payTmOffer);

		// 5. Firing : Rules
			session.fireAllRules();

	System.out.println("The cashback for this payment channel VIA KIE "+payTmOffer.getChannel()+" is "+payTmOffer.getDiscount());

		/*
			First time customer
			The cashback for this payment channel VIA KIE paytm is 22
		*/
	}

}