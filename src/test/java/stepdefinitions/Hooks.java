package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import model.TestContext;

import managers.DriverManager;
import selenium.TestResult;
import static managers.DriverManager.getDriver;
import static selenium.SeleniumHelper.*;

public class Hooks {

	public static Scenario currentScenario;
	TestContext testContext;

	public Hooks(TestContext context) {
		testContext = context;
	}

	@Before
	public void setup(Scenario scenario) {
		currentScenario = scenario;
	//	currentScenario.write("IDAM Automation Script Execution");
	}

	@After(order = 0)
	public void AfterSteps() {
			if (currentScenario.isFailed()) {
				takeScreenshot(getDriver(), TestResult.EXCEPTION, "failed_screencast");
			}
		DriverManager.quitDriver();
	}
}
