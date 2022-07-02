package com.keyword.tests;

import org.testng.annotations.Test;

import com.keyword.engine.KeywordEngine;

public class LoginTest {
public KeywordEngine keywordEngine;
	
	@Test
	public void loginTestScenario()
	{
		keywordEngine = new KeywordEngine();
		keywordEngine.startExecution("login");
	}
}
