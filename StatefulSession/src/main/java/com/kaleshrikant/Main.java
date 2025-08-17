package com.kaleshrikant;

import com.kaleshrikant.model.PaymentCounter;
import com.kaleshrikant.model.PaymentOffer;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.api.runtime.rule.FactHandle;

public class Main {
	public static void main(String[] args) {
// 1. Creating : Service
		KieServices services = KieServices.Factory.get();

		// 2. Creating : Container
		KieContainer container= services.getKieClasspathContainer();

		// 3. Creating : Session
		// KieSession session = container.newKieSession("complex-ksession-rule");
		StatelessKieSession session = container.newStatelessKieSession("complex-ksession-rule");

		// 4. Creating : Facts
		PaymentOffer payTmOffer = new PaymentOffer();
		payTmOffer.setChannel("paytm");
		payTmOffer.setFestival("xmas");
		payTmOffer.setFirstTimeCustomer(true);

		PaymentCounter counter = new PaymentCounter();
		counter.setChannel("Amazon");
		counter.setAmount(10);

		// 4. Inserting : Facts
		 FactHandle factHandle;
		    session.execute(counter);

		counter = new PaymentCounter();
			counter.setChannel("XYZ");
			counter.setAmount(20);
			session.execute(counter);

		counter = new PaymentCounter();
			counter.setChannel("ABC");
			counter.setAmount(90);
			session.execute(counter);

		// 5. Firing : Rules
		// session.fireAllRules();

		/*
			Payment Counter inserted : Amazon and amount is 10
			Payment Counter channel : Amazon Amount : 10 Accumulate value :1
			Payment Counter inserted : XYZ and amount is 20
			Payment Counter channel : XYZ Amount : 20 Accumulate value :1
			Payment Counter inserted : ABC and amount is 90
			Payment Counter channel : ABC Amount : 90 Accumulate value :1
			The cashback for this payment channel VIA KIE paytm is 0
		*/

		System.out.println("The cashback for this payment channel VIA KIE "+payTmOffer.getChannel()+" is "+payTmOffer.getDiscount());

		}
	}