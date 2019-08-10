package au.com.qantas.pages.web;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Constants;
import utilities.GetDateFromPicker;
import utilities.Shared;

import java.util.List;


public class HomePage extends BasePage{
    final String sessionName = Shared.getSharedVariable( Constants.currentWebDriverSession );

    WebDriver driver = Shared.getWebDriver(sessionName);

    GetDateFromPicker getDateFromPicker;

    @FindBy(xpath = "//input[@id='typeahead-input-from']")
    WebElement flightSourceObject;

    @FindBy(xpath = "//input[@id='typeahead-input-to']")
    WebElement flightToObject;

    @FindBy(xpath = "//input[@id='datepicker-input-departureDate']")
    WebElement flightDeparturePickerObject;

    @FindBy(xpath = "//*[@id=\"flight-form\"]/form[1]/div[3]/div/div[2]/div[2]/div[1]/fieldset/div/div[2]/label")
    WebElement flightOneWayTripObject;

    @FindBy(xpath = "//div[@class='qfa1-submit-button__container-right widget-form__group-container size-big']")
    WebElement flightSearchObjectbtn;

    @FindBy(xpath = "//*[@id=\"collapsibleCard0\"]/div[2]/cart-collapsible-card-footer/cart-flights-subtotal/div/div[1]/div[2]/div/cart-subtotal-row/span[1]")
    WebElement txtSubtotal;

    @FindBy(xpath = "//*[@id=\"flight-form\"]/form[1]/div[3]/div/div[5]/div/div[2]/div/div[4]/div[1]/table/tbody/tr[1]/th")
    WebElement flightDateFromMonthFirstObject;

    @FindBy(xpath = "//*[@id=\"flight-form\"]/form[1]/div[3]/div/div[5]/div/div[2]/div/div[4]/div[2]/table/tbody/tr[1]/th")
    WebElement flightDateFromMonthSecondObject;

    @FindBy(xpath = "//*[@id=\"flight-form\"]/form[1]/div[3]/div/div[5]/div/div[2]/div/div[4]/div[1]/table")
    WebElement flightDateFromMonthFirstTableObject;

    @FindBy(xpath = "//*[@id=\"flight-form\"]/form[1]/div[3]/div/div[5]/div/div[2]/div/div[4]/div[2]/table")    WebElement flightDateFromMonthSecondTableObject;


    public HomePage() throws NoSuchMethodException {
        PageFactory.initElements(driver, this);
        getDateFromPicker = new GetDateFromPicker();
    }
    /**
     * method to search the domestic flights
     * Parameters source and destination
     */

    public SearchPage searchFlight(String source,String destination) throws NoSuchMethodException, InterruptedException {

        verify_Element_displayed(flightSourceObject);
        typeText(flightSourceObject, source);

        verify_Element_displayed(flightToObject);
        Click(flightToObject);
        typeText(flightToObject, destination);
        flightToObject.sendKeys(Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(driver, 3);
        WebElement onewayRadiobtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"flight-form\"]/form[1]/div[3]/div/div[2]/div[2]/div[1]/fieldset/div/div[2]/label")));
        onewayRadiobtn.click();

        Click(flightDeparturePickerObject);

        //Code to get the current day of the system and add 15 days
        String nDate = getDateFromPicker.getSdf(15);

        String[] result = nDate.split(" ");

        //get the day of the calendar after adding 15days
        String day = result[2];

        //Select the days of the First Calendar
        if (flightDateFromMonthFirstObject.getText().toLowerCase().contains(result[1].toLowerCase())) {
            // selectDayOfCalendar(day,Model.getElementFromLocator(flightDateFromMonthFirstTableObject));
            selectDayOfCalendar(day, flightDateFromMonthFirstTableObject);
            // Select the days of the Second Calendar
        } else if (flightDateFromMonthSecondObject.getText().toLowerCase().contains(result[1].toLowerCase())) {
            selectDayOfCalendar(day, flightDateFromMonthSecondTableObject);
        }

        verify_Element_displayed(flightSearchObjectbtn);
        Click(flightSearchObjectbtn);
        verify_Element_displayed(txtSubtotal);
        Assert.assertEquals("$0", txtSubtotal.getText());
        return new SearchPage();
    }

    /**
     * method to select the date of the calendar
     * Parameters - advance of 15 days and the DatePicker Table Element Object
     */

    public void selectDayOfCalendar(String today,WebElement dateWidgetFrom){
        //This are the columns of the from date picker table
        List<WebElement> columns = dateWidgetFrom.findElements(By.tagName("td"));
        //DatePicker is a table. Thus we can navigate to each cell
        //and if a cell matches with the current date then we will click it.
          for (WebElement cell: columns) {
            //If you want to click 18th Date
            if (cell.getText().equals(today)) {
               cell.click();
               break;
           }
         }
        //Wait for 4 Seconds to see Today's date selected.
          try {
                Thread.sleep(4000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
    }
}
