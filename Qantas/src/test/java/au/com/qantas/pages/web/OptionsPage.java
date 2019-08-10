package au.com.qantas.pages.web;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Constants;
import utilities.Shared;


public class OptionsPage extends BasePage {
    final String sessionName = Shared.getSharedVariable( Constants.currentWebDriverSession );
    WebDriver driver = Shared.getWebDriver(sessionName);

    @FindBy(xpath = "//button[@class='button-add-bag trigger-modal']")
    WebElement addBagLinkObject;

    @FindBy(xpath = "//button[@class='btn right']")
    WebElement addBagIncrementObject;


    public OptionsPage(){
        PageFactory.initElements(driver, this);
         }

    /**
     * method to add baggage to the trip
     * Parameters Baggage counter
     */
    public void addBaggageToTrip(Integer bagCount) throws NoSuchMethodException, InterruptedException {
        waitForAjaxElementLoadToReachThreshold();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();"
                ,  addBagLinkObject);

        Thread.sleep(3000);
        verify_Element_displayed(addBagLinkObject);
        Click(addBagLinkObject);

        // Loop to Add baggage
        for(int ctr=1; ctr<=bagCount; ctr++) {
            if (ctr>1){
                verify_Element_displayed(addBagIncrementObject);
                Click(addBagIncrementObject);
            }}

        WebDriverWait waitContinueFirst = new WebDriverWait(driver,5);
        WebElement btnContinueFirst= waitContinueFirst.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='bags-btn-continue']")));
        btnContinueFirst.click();
        Thread.sleep(3000);
        WebDriverWait acceptButton = new WebDriverWait(driver,5);
        WebElement btnAccept= acceptButton.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"bagsTermsAndConditionsModal\"]/div/div/div[2]/navigation-ribbon/navigation-ribbon-view-tac/div/nav/div[2]/button")));
        btnAccept.click();
    }

}
