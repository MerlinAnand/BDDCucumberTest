package au.com.qantas.pages.web;
import org.apache.commons.lang.NullArgumentException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.util.Strings;
import utilities.Constants;
import utilities.Shared;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static java.lang.Thread.sleep;

public class BasePage {
    final String sessionName = Shared.getSharedVariable( Constants.currentWebDriverSession );

    WebDriver driver = Shared.getWebDriver(sessionName);

    public BasePage(){}

    /**
     * generic link click code - assumes we can locate the link by its visible text
     * @param value visible text to find
     */
    public void clickLink(String value){

        if( Strings.isNullOrEmpty( value ) )
            throw new NullArgumentException("Incoming value of label is set to NULL or is an empty string. Check the calling code value.");

        // make this hard coded value come from pom.xml
        new WebDriverWait( Shared.getWebDriver( Shared.getSharedVariable( Constants.currentWebDriverSession)),
                           Long.parseLong( System.getProperty("browser.explicit.wait")))
                .until(
                    ExpectedConditions.elementToBeClickable(By.linkText(value))
            ).click();

        waitForAjaxElementLoadToReachThreshold();
    }

    /**
     * waits for an ajax element count difference at 500mS intervals
     * to reach a threshold value before returning.
     */
    public void waitForAjaxElementLoadToReachThreshold(){
        int before = 0;
        int threshold = 30;
        do{
            before = elementCount();
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while( ( elementCount() - before ) > threshold );
    }

    /**
     * Gets qty of elements for determining AJAX loading is reaching a threshold
     * @return int count of all elements on current page
     */
    private int elementCount(){
        return Shared.getWebDriver( Shared.getSharedVariable( Constants.currentWebDriverSession))
                .findElements(By.xpath("//*")).size();
    }


    /**
     * Locates the element anywhere in the DOM and walks up the DOM till the attribute is not null or empty
     *
     * @param elementBy element locator without a container
     * @param attribute desired element attribute
     * @return single web element matching the attribute parameter (ie attribute exists in element)
     */
    protected WebElement locateElementAndWalkUpDomToAttribute(By elementBy, String attribute ){

        String parentPath = "./parent::node()";

        WebElement currentElement = new WebDriverWait(Shared.getWebDriver( Shared.getSharedVariable( Constants.currentWebDriverSession))
                ,Long.parseLong( System.getProperty("browser.explicit.wait")))
                .until(
                        ExpectedConditions.elementToBeClickable(elementBy)
                );

        while( Strings.isNullOrEmpty(currentElement.getAttribute(attribute ) ) ){
            currentElement = currentElement.findElement(By.xpath( parentPath ) );
        }

        return currentElement;
    }

    public void Click(By by){driver.findElement(by).click();}

    public void Click(WebElement objTest ){
        objTest.click();}


    public boolean isElementVisible(String cssLocator){
        return driver.findElement(By.cssSelector(cssLocator)).isDisplayed();
    }

    public boolean isElementPresent(WebElement element) {
        try {
            element.isDisplayed();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String get_Element_Text(By by){
        return  driver.findElement(by).getText();
    }

    public void verify_Element_Text(By by, String text){

        String strExpected = driver.findElement(by).getText().toLowerCase().trim();
        Assert.assertEquals(strExpected, text.toLowerCase().trim(), "Element text not displayed as expected .Expected: "+text.toLowerCase()+" and Actual is : "+strExpected);
    }

    public String get_Attribute_Value(By by, String strAttribute){
        String result = driver.findElement(by).getAttribute(strAttribute);
        return  result;
    }

    public List get_Element_List(By by) {
        List<WebElement> lisElement = driver.findElements(by);
        return lisElement;
    }

    public void verify_Element_displayed(WebElement objTest){
        wait_explicit_till_element_Displayed(objTest);
        boolean result =objTest.isDisplayed();
        Assert.assertEquals(result, true);
    }

    public void verify_Element_displayed(By by){
        wait_explicit_till_element_Displayed(by);
        boolean result =driver.findElement(by).isDisplayed();
        Assert.assertEquals(result, true);
    }

    public void wait_explicit_till_element_Displayed(By by){

        WebDriverWait waitnew=new WebDriverWait(driver,20);
        WebElement element = waitnew.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void wait_explicit_till_element_Displayed(WebElement objTest){
        WebDriverWait waitnew=new WebDriverWait(driver,20);
        WebElement element = waitnew.until(ExpectedConditions.visibilityOf(objTest));
    }

    public void typeText(WebElement objInput, String text) {
        objInput.clear();
        objInput.sendKeys(text);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

}
