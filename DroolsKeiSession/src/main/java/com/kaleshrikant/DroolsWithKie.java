package com.kaleshrikant;

import com.kaleshrikant.model.PaymentOffer;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

/**
 * @author Shrikant Kale
 * @Date 8/17/25
 */

public class DroolsWithKie {
	public static void main(String[] args) {
		DroolsWithKie.executeBussinessRules();
	}

	public static void executeBussinessRules() {
		try{
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			KieSession kSession = kContainer.newKieSession("first-ksession-rule");

			PaymentOffer paymentOffer = new PaymentOffer();
			paymentOffer.setChannel("PayTm");

			FactHandle factHandler;

			factHandler = kSession.insert(paymentOffer);
			kSession.fireAllRules();

			System.out.println("The cashback for this payment channel VIA KIE "+paymentOffer.getChannel()+" is "+paymentOffer.getDiscount());
		} catch(Exception e){
			e.printStackTrace();
		}

	}
	}
