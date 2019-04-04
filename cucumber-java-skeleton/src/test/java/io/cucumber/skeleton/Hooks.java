package io.cucumber.skeleton;

import java.util.Properties;

import cucumber.api.java.Before;
import gherkin.deps.net.iharder.Base64.InputStream;

public class Hooks {
	private static boolean beforeSuit = true;
	private static String executablePath;
	static Properties prop;

	@Before
	public void beforeAll() {
		if (beforeSuit) {
			System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
		}

	}
}