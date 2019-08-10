package cucumber.steps;

import au.com.qantas.pages.web.SearchPage;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;
import utilities.Constants;
import utilities.Shared;

public class SearchPageSteps {
    final String sessionName = Shared.getSharedVariable( Constants.currentWebDriverSession );
    WebDriver driver = Shared.getWebDriver(sessionName);

    SearchPage searchPage= new SearchPage();

    public SearchPageSteps() throws NoSuchMethodException {
    }

    @Then("^user selects the red deal and add to trip$")
    public void user_selects_reddeal_addtotrip() throws NoSuchMethodException, InterruptedException {

        searchPage.addtoTrip();
    }

}
