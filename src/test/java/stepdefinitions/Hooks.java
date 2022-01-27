package stepdefinitions;

import cucumber.TestContext;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import managers.DriverManager;

import static managers.DriverManager.getDriver;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;

public class Hooks {

	public static Scenario currentScenario;
	TestContext testContext;

	public Hooks(TestContext context) {
		testContext = context;
	}

	@Before
	public void setup(Scenario scenario) {
		currentScenario = scenario;
		currentScenario.write("IDAM Automation Script Execution");
		getDriver();
	}

	@After
	public void tearDown() {
		if (getDriver() != null) {
			if (currentScenario.isFailed()) {
				WebDriver augementedDriver = new Augmenter().augment(getDriver());
				currentScenario.embed(((TakesScreenshot) augementedDriver).getScreenshotAs(OutputType.BYTES),
						"image/png");
			}
		}
		DriverManager.quitDriver();
	}
}
