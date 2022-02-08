/**
 * 
 */
package selenium;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import managers.DriverManager;
import managers.FileReaderManager;
import util.ReportUtil;

/**
 * @author Siva Rama Krishna
 *
 */
public class SeleniumHelper extends DriverManager {

	private static int waitForSeconds = 60;
	private static int screencastCounter = 0;

	public static void waitForAngularRequestToFinish() {
		try {
			getNGDriver().waitForAngularRequestsToFinish();
		} catch (Exception e) {
			Assert.fail("Error Waiting for Angular Request to finish ");
		}
	}

	public static void waitForElementToBeLoaded(WebElement webElement) {
		WebDriverWait webDriverWait = new WebDriverWait(getDriver(), waitForSeconds);
		webDriverWait.until(ExpectedConditions.or(
				ExpectedConditions.elementToBeClickable(webElement),
				ExpectedConditions.elementToBeSelected(webElement)
				));
	}

	public static void enterTextIntoTextBox(WebElement textBox, String textToBeEntered) {
		try {
			waitForElementToBeLoaded(textBox);
			textBox.sendKeys(textToBeEntered);
		} catch (Exception e) {
			takeScreenshot(getDriver(), TestResult.EXCEPTION, "failed_screencast");
		}
	}

	public static void clickElement(WebElement clickable) {
		try {
			waitForElementToBeLoaded(clickable);
			clickable.click();
		} catch (Exception e) {
			takeScreenshot(getDriver(), TestResult.EXCEPTION, "failed_screencast");
		}
	}

	public static void clickElement_JS(WebElement element) {
		try {
			waitForElementToBeLoaded(element);
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			takeScreenshot(getDriver(), TestResult.EXCEPTION, "failed_screencast");
		}
	}

	public static void selectOptionFromSelectBox(WebElement selectBox, String selection) {
		try {
			waitForAngularRequestToFinish();
			clickElement(selectBox);
			waitInSeconds(2000);
			WebElement option = getDriver().findElement(By.xpath("//mat-option[contains(@value,'"+selection+"')]/span"));
			clickElement(option);
			waitInSeconds(1000);
		} catch (Exception e) {
			takeScreenshot(getDriver(), TestResult.EXCEPTION, "failed_screencast");
		}
	}

	public static void selectOptionFromDropDown(WebElement parent,WebElement option) {
		try {
			waitForAngularRequestToFinish();
			clickElement_JS(parent);
			waitInSeconds(2000);
			clickElement_JS(option);
			waitInSeconds(1000);
		} catch (Exception e) {
			takeScreenshot(getDriver(), TestResult.EXCEPTION, "failed_screencast");
		}
	}

	public static void takeScreenshot(WebDriver driver, TestResult result, String screencastName) {
		try {
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String path = FileReaderManager.getInstance().getConfigReader().getScreenshotsPath() + screencastName + "_"
					+ screencastCounter + ".png";
			FileUtils.copyFile(src, new File(path));
			screencastCounter++;
			ReportUtil.addScreencastLog(path, result.name());
		} catch (Exception e) {

		}
	}

	public static void scrollDown(WebDriver driver){
		Actions at = new Actions(driver);
		at.sendKeys(Keys.PAGE_DOWN).build().perform();
		at.sendKeys(Keys.PAGE_DOWN).build().perform();
	}

	public static void waitInSeconds(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void assertTrue(boolean result, String message) {
		Assert.assertTrue(message, result);
	}

	public static void assertFalse(boolean result, String message) {
		Assert.assertTrue(message, result);
	}

	public static void selectDateByJS(WebElement ele, String dateValue) {
		JavascriptExecutor js = ((JavascriptExecutor) getDriver());
		js.executeScript("arguments[0].removeAttribute('readonly')", ele);
		waitInSeconds(5);
		waitForAngularRequestToFinish();
		WebElement newElement = getDriver().findElement(By.id("datei"));
		js.executeScript("arguments[0].setAttribute('value','02-Feb-1981')", ele);

	}

	public static void zoomOut() throws AWTException {
		Robot robot = new Robot();
		for (int i = 0; i < 4; i++) {
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_SUBTRACT);
			robot.keyRelease(KeyEvent.VK_SUBTRACT);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		}
	}



}
