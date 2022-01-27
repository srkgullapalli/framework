/**
 * 
 */
package selenium;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import managers.DriverManager;

/**
 * @author Siva Rama Krishna
 *
 */
public class SeleniumHelper extends DriverManager {

	private static WebDriverWait wait;

	public static void waitforEleVisible(WebElement ele, int timeout) {
	//	wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));

		for (int i = 0; i <= 3; i++) {
			try {
				wait.until(ExpectedConditions.visibilityOf(ele));
			} catch (StaleElementReferenceException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void waitForAngularRequestToFinish() {
		try {
		getNGDriver().waitForAngularRequestsToFinish();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
