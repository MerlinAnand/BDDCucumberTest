package au.com.qantas.pages.web;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Constants;
import utilities.Shared;

public class SearchPage extends BasePage {


    String strDealAmount;

    final String sessionName = Shared.getSharedVariable( Constants.currentWebDriverSession );
    WebDriver driver = Shared.getWebDriver(sessionName);

    @FindBy(xpath = "//*[@class='ng-tns-c64-8 ng-star-inserted' and text()='Add to Trip']")
    WebElement flightSearchAddTripObject;

    @FindBy(xpath = "//*[@id=\"btn-continue\"]")
    WebElement flightSearchContinueObject;

    @FindBy(xpath = "//*[@id=\"btn-accept\"]")
    WebElement flightSearchAcceptObject;

    @FindBy(xpath = "//*[@id=\"e2e-itinerary-0\"]/div/div[2]/div/upsell-itinerary-fares/upsell-fare-cell[1]/div/label/div/span[2]/div/span/span/span/text()")
    WebElement flightSubtotalDealAmountObject;

    @FindBy(xpath = "//div[@id='collapsibleCard0']/div[2]//div/div/div/div/cart-subtotal-row/span[1]")
    WebElement flightSubtotalBeforeAddBagObject;

    @FindBy(xpath = "//div[@id='collapsibleCard12']/div[2]//div/div/div/div/cart-subtotal-row/span[1]")
    WebElement flightSubtotalAfterAddBagObject;


    public SearchPage() throws NoSuchMethodException {
        PageFactory.initElements(driver, this);
    }
    /**
     * method to select the red deal
     * Parameters driver
     */
    public void addtoTrip() throws InterruptedException {
        waitForAjaxElementLoadToReachThreshold();
        //Assert for checking 0$ Subtotal
        verify_Element_displayed(flightSubtotalBeforeAddBagObject);
        Assert.assertEquals("$0", flightSubtotalBeforeAddBagObject.getText());
        Thread.sleep(3000);

        WebDriverWait wait = new WebDriverWait(driver,3);
        WebElement redDeal= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"e2e-itinerary-0\"]/div[1]/div[2]/div/upsell-itinerary-fares/upsell-fare-cell[1]/div/label/div/span[2]/div")));
        redDeal.click();
        Thread.sleep(3000);
        verify_Element_displayed(flightSearchAddTripObject);
        Click(flightSearchAddTripObject);

        WebDriverWait waitContinueFirst = new WebDriverWait(driver,10);
        WebElement btnContinueFirst= waitContinueFirst.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"btn-continue\"]")));
        btnContinueFirst.click();

        Thread.sleep(8000);
        WebDriverWait waitContinueSecond = new WebDriverWait(driver,10);
        WebElement btnContinueSecond= waitContinueSecond.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"btn-continue\"]")));
        btnContinueSecond.click();

        waitForAjaxElementLoadToReachThreshold();
        verify_Element_displayed(flightSearchAcceptObject);
        Click(flightSearchAcceptObject);
    }


}
