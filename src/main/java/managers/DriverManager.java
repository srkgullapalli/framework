package managers;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.paulhammant.ngwebdriver.NgWebDriver;

import enums.DriverType;
import enums.PlatformType;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {
	private static WebDriver driver;
	private static DriverType driverType;
	private static PlatformType platform;

	public static WebDriver getDriver() {
		platform = FileReaderManager.getInstance().getConfigReader().getExecutionPlatform();

		if (driver == null) {
			switch (platform) {
			case LOCAL:
				driver = createLocalDriver();
				break;
			case REMOTE:
				driver = createRemoteDriver();
				break;
			}
		}
		return driver;
	}

	public static NgWebDriver getNGDriver() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return (new NgWebDriver(js));
	}

	private static WebDriver createRemoteDriver() {
		driverType = FileReaderManager.getInstance().getConfigReader().getBrowser();

		String sauceUser ="gkrish90";
		String sauceKey = "742fa4ca-9dfa-4279-8228-6a4ef1186ed1";
		
		 String sauceUrl = "https://ondemand.us-west-1.saucelabs.com:443/wd/hub";
         MutableCapabilities sauceOptions = new MutableCapabilities();
         sauceOptions.setCapability("username", sauceUser);
         sauceOptions.setCapability("accesskey", sauceKey);
         sauceOptions.setCapability("tunnelIdentifier", "gkrish90_tunnel");

         
         sauceOptions.setCapability("seleniumVersion", "4.0.0");
         
         ChromeOptions chromOpts = new  ChromeOptions();
         chromOpts.setExperimentalOption("w3c", true);
		chromOpts.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		chromOpts.setExperimentalOption("useAutomationExtension", false);


		DesiredCapabilities caps = new DesiredCapabilities();
         caps.setCapability(ChromeOptions.CAPABILITY, chromOpts);
         caps.setCapability("sauce:options", chromOpts);
         caps.setCapability("browserName", "chrome");
         caps.setCapability("platformName", "Windows 10");
         caps.setCapability("browserVersion", "81.0");
         caps.setCapability("avoidProxy", true);
         caps.setCapability("name", "sauce");

         
            try {
				driver = new RemoteWebDriver(new URL(sauceUrl), caps);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
      
         
         return driver;
		
	}

	private static WebDriver createLocalDriver() {
		driverType = FileReaderManager.getInstance().getConfigReader().getBrowser();

		switch (driverType) {
		case FIREFOX:
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case CHROME:
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--start-maximized");
			chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			driver = new ChromeDriver(chromeOptions);

			break;
		case EDGE:
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		}
		driver.manage().window().setSize(new Dimension(1920, 1080));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		return driver;
	}

	public static void quitDriver() {
		if (driver == null) {
			return;
		}
		driver.quit();
	}
}
