/**
 * 
 */
package selenium;

import java.io.File;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
		WebDriverWait webDriverWait = new WebDriverWait(getDriver(), Duration.ofSeconds(waitForSeconds));
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

	public static void selectOptionFromSelectBox(WebElement selectBox, String selection) {
		Actions builder = new Actions(getDriver());

		try {
			waitForAngularRequestToFinish();
			clickElement(selectBox);
			waitInSeconds(10);
			WebElement option = getDriver().findElement(By.xpath("//span[contains(.,'" + selection.trim() + "')]"));
			Action mouseHoverClick = builder.click(option).build();
			mouseHoverClick.perform();
			waitInSeconds(10);
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

	public static void waitInSeconds(int seconds) {
		try {
			Thread.sleep(seconds);
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
		js.executeScript("arguments[0].setAttribute('value','" + dateValue + "');", ele);

	}
}
