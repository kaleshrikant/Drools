package com.kaleshrikant.drools;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;
/**
 * @author Shrikant Kale
 * @Date 28 Sep 2025
 */

@SpringBootTest
public class RulesEngineManagerTest {

	@Autowired
	private RulesEngineManager rulesEngineManager;

	@Test
	public void testRulesEngineManagerBean() {
		assertNotNull(rulesEngineManager, "RulesEngineManager bean should be created");
		assertNotNull(rulesEngineManager.getCurrentVersion(), "Should have a current version");
	}
}
