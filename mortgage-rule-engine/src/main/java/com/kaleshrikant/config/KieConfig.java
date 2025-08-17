package com.kaleshrikant.config;

import org.kie.api.KieServices;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Shrikant Kale
 * @Date 8/17/25
 */

@Configuration
public class KieConfig {

	@Bean
	public KieServices kieServices() {
		return KieServices.Factory.get();
	}

	@Bean
	public KieContainer kieContainer(KieServices kieServices) {
		return kieServices.getKieClasspathContainer();
	}

	@Bean
	public KieSession kieSession(KieContainer kieContainer) {
		return kieContainer.newKieSession("mortgage-rule-engine");
	}

}
