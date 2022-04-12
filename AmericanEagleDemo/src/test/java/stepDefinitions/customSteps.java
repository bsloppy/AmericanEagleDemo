package stepDefinitions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class customSteps {
    public WebDriver driver;
    private UtilityMethods UtilityMethods;


    @Before
    public void setup() {
        this.UtilityMethods = new UtilityMethods();
    }

    //##################################GIVEN################################

    /**
     * launches browser
     */
    @Given("we launch chrome browser")
    public void launch() {
        System.setProperty("webdriver.chrome.driver", "C:\\JavaDrivers\\Chrome\\chromedriver.exe");


        driver = new ChromeDriver();

    }
    //################################## WHEN ################################

    /**
     * has the driver open a specified website
     * @param site
     */
    @When("we navigate to \"([^\"]*)\"")
    public void open_site(String site) {
        driver.get(site);
    }

    /**
     * adds an item from the shoes and accessories tab to the cart.
     * Uses shoes and accessories to avoid sizes for more reliability
     * can still have issues due to out of stock but I wasn't aware of how that works
     */
    @When("we add an item to cart")
    public void weAddAnItemToCart() throws InterruptedException {
        By shoesAccessories = By.xpath("//*[@aria-label = 'Shoes & Accessories category']");
        UtilityMethods.clickWhenVisible(shoesAccessories,driver);
        weWait();
        By hats = By.xpath("//*[@href='/us/en/c/men/accessories-socks/hats/cat2380019?pagetype=plp']");
        UtilityMethods.clickWhenVisible(hats,driver);
        weWait();
        By firstItem = By.xpath("//*[@class='_container_1514e4 __217fc']");
        UtilityMethods.clickWhenVisible(firstItem,driver);
        weWait();
        By addToBag = By.xpath("//*[@name='addToBag']");
        UtilityMethods.clickWhenVisible(addToBag,driver);
        weWait();
    }

    /**
     * selects all the text from a given element and deletes it
     * @param selector
     */
    @When("we clear \"([^\"]*)\"")
    public void clearElement(String selector) {
        By ele = By.xpath(selector);
        WebElement element = this.driver.findElement(ele);
       UtilityMethods.clickWhenVisible(ele,driver);
        element.sendKeys(Keys.chord(Keys.CONTROL,"a"));
        element.sendKeys(Keys.chord(Keys.DELETE));
        element.sendKeys(Keys.chord(Keys.TAB));


    }

    /**
     * clicks on an element
     * @param locator
     */
    @When("we click on \"([^\"]*)\"")
    public void clickOnElement(String locator) throws InterruptedException {
        By byLocator = By.xpath(locator);
       UtilityMethods.clickWhenVisible(byLocator,driver);
       weWait();
    }
    //##################################THEN################################

    /**
     * shuts down driver
     */
    @Then("we close the driver")
    public void driver_close() {
        driver.quit();
    }

    /**
     * pauses test to wait for loading
     * @throws InterruptedException
     */
    @Then("we wait")
    public void weWait() throws InterruptedException {
        Thread.sleep(5000);
    }

    /**
     * validates that a given element contains expected text
     * @param locator
     * @param text
     */
    @Then("\"([^\"]*)\" should contain the text \"([^\"]*)\"")
    public void creditcardinputShouldContainTheText(String locator, String text) {
        By byLocator = By.xpath(locator);
        UtilityMethods.verifyContainsText(byLocator, text,driver);

    }

    /**
     * sends text to a given element
     * @param input
     * @param locator
     */
    @Then("we enter \"([^\"]*)\" into \"([^\"]*)\"")
    public void weEnterIntoCreditCardInput(String input, String locator) {
        WebElement locatorElement = this.driver.findElement(By.xpath(locator));
        UtilityMethods.enterValueWhenVisible(locatorElement, input);
    }
@After
    public void teardown(){
    driver.quit();

}




}