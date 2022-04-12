package stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UtilityMethods {





    /**
     * checks if an element exists on the current page
     *
     * @param locator
     * @param timeout
     * @return
     */
    public boolean elementtExists(By locator, int timeout,WebDriver driver) {
        WebElement element = null;
        try {
            element = new WebDriverWait(driver, timeout).until(ExpectedConditions.presenceOfElementLocated(locator));
            if (element != null)
                return true;


        } catch (Exception e) {
            System.out.println("\nElement not found" + locator.toString() + "exception:" + e);
        }

        return false;
    }

    /**
     *  enters a value into a given element
     * @param element
     * @param inputValue
     */
    public void enterValueWhenVisible(WebElement element, String inputValue) {
        try {

            element.sendKeys(inputValue);

        } catch (Exception e) {
            System.out.println("\nError:" + e);
        }

    }

    /**
     * click on an element
     *
     * @param locator
     */
    public void clickWhenVisible(By locator, WebDriver driver) {
        try {
            System.out.println(locator.toString());
            WebElement ele = driver.findElement(locator);
            ele.click();
        } catch (Exception e) {
            System.out.println("\nElement not clickable " + locator.toString() + " Exception:" + e);
        }
    }

    /**
     * validates the text within an element
     * @param byLocator
     * @param expectedText
     */
    //TODO
    public void verifyContainsText(By byLocator, String expectedText, WebDriver driver) {
        System.out.println("#############################################################################");
                System.out.println(driver.findElement(byLocator).getText());
                System.out.println(expectedText);
            Assert.assertTrue(  driver.findElement(byLocator).getText().equals(expectedText));

    }
}










