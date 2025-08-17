package com.kaleshrikant;


import com.kaleshrikant.model.PaymentOffer;
import org.drools.compiler.compiler.DroolsParserException;
import org.drools.compiler.compiler.PackageBuilder;
import org.drools.core.RuleBase;
import org.drools.core.RuleBaseFactory;
import org.drools.core.WorkingMemory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class LegacyDroolTester {
	public static void main(String[] args) throws IOException, DroolsParserException {
		LegacyDroolTester.execteRule();
	}

	public static void execteRule() throws DroolsParserException, IOException{
		PackageBuilder builder = new PackageBuilder();

		String ruleFile = "src/main/java/resources/Offers.drl";
		InputStream resourceAsStream = LegacyDroolTester.class.getResourceAsStream(ruleFile);
		if (resourceAsStream == null) {
			System.err.println("❌ ERROR: 'Offers.drl' NOT found on classpath!");
			System.exit(1);
		} else {
			System.out.println("✅ Found 'Offers.drl' on classpath!");
		}

		Reader ruleReader = new InputStreamReader(resourceAsStream);
		builder.addPackageFromDrl(ruleReader);
		org.drools.core.rule.Package rulePackage = builder.getPackage();
		RuleBase ruleBase = RuleBaseFactory.newRuleBase();
		ruleBase.addPackage(rulePackage);

		WorkingMemory workingMemory = ruleBase.newStatefulSession();

		PaymentOffer paymentOffer = new PaymentOffer();
		paymentOffer.setChannel("PayTm");
		workingMemory.insert(paymentOffer);
		workingMemory.fireAllRules();

		System.out.println("The cashback for this payment channel "+paymentOffer.getChannel()+" is "+paymentOffer.getDiscount());

	}

}