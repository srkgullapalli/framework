/**
 *
 */
package selenium;

import managers.DriverManager;
import managers.FileReaderManager;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.ReportUtil;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Random;
import java.util.UUID;

/**
 * @author Siva Rama Krishna
 *
 */
public class SeleniumHelper extends DriverManager {

    private static int waitForSeconds = 30;
    private static int screencastCounter = 0;

    public static void waitForAngularRequestToFinish() {
        try {
            getNGDriver().waitForAngularRequestsToFinish();
        } catch (Exception e) {
        }
    }

    public static void waitForElementToBeLoaded(WebElement webElement) {
        WebDriverWait webDriverWait = new WebDriverWait(getDriver(), waitForSeconds);
        webDriverWait
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.or(
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
            JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
            jsExecutor.executeScript("arguments[0].scrollIntoView();", element);
            jsExecutor.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            takeScreenshot(getDriver(), TestResult.EXCEPTION, "failed_screencast");
        }
    }

    public static void selectOptionFromSelectBox(WebElement selectBox, String selection) {
        try {
            waitForAngularRequestToFinish();
            clickElement(selectBox);
            waitInSeconds(2000);
            WebElement option = getDriver().findElement(By.xpath("//mat-option[contains(@value,'" + selection + "')]/span"));
            clickElement(option);
            waitInSeconds(1000);
        } catch (Exception e) {
            takeScreenshot(getDriver(), TestResult.EXCEPTION, "failed_screencast");
        }
    }

    public static void selectOptionFromDropDown(WebElement parent, WebElement option) {
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

    public static void scrollDown(WebDriver driver) {
        Actions at = new Actions(driver);
        at.sendKeys(Keys.PAGE_DOWN).build().perform();
        at.sendKeys(Keys.PAGE_DOWN).build().perform();
    }

    public static void scrollTillPageEnd(WebDriver driver) {
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public static void waitForAlertAndAccept(WebDriver driver) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, waitForSeconds);
        webDriverWait.ignoring(NoAlertPresentException.class)
                .until(ExpectedConditions.alertIsPresent());

        Alert al = driver.switchTo().alert();
        al.accept();
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

    public static void fileUploadRobot(String msgPath) throws AWTException {
        StringSelection ss = new StringSelection(msgPath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

        //imitate mouse events like ENTER, CTRL+C, CTRL+V
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    public static long generateRandomNo() {
        return (long) (Math.random() * Math.pow(10, 10));
    }

    public static String generateRandomEmail() {
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(1000);
        return UUID.randomUUID().toString().substring(0, 5) + randomInt + "@gmail.com";
    }

    public static void scrollToElementActions(WebDriver driver,WebElement ele){
        waitForElementToBeLoaded(ele);
        Actions actions = new Actions(driver);
        actions.moveToElement(ele).build().perform();
    }
}
