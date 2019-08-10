package cucumber.steps;

import au.com.qantas.pages.web.OptionsPage;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;
import utilities.Constants;
import utilities.Shared;

public class OptionsPageSteps {


    OptionsPage optionsPage= new OptionsPage();

    @Then("user add additional {}")
    public void user_add_Baggage_to_Trip(int bagCount) throws NoSuchMethodException, InterruptedException {
        optionsPage.addBaggageToTrip(bagCount);
    }
}
