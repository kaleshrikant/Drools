package com.kaleshrikant.drools.config;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ✅ Simplified KieContainer config using kmodule.xml
 * Automatically loads DRL files under "rules" package.
 *
 * @author Shrikant
 * @Date 01 Oct 2025
 */
@Configuration
public class KieServerConfig {

	private static final Logger logger = LoggerFactory.getLogger(KieServerConfig.class);

	@Bean
	public KieContainer kieContainer() {
		logger.info("=== Initializing KieContainer using kmodule.xml ===");
		KieServices kieServices = KieServices.Factory.get();
		KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
		logger.info("=== KieContainer initialized successfully ===");
		return kieClasspathContainer;
	}
}
