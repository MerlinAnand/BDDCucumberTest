package cucumber.steps;

import au.com.qantas.pages.web.HomePage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import utilities.Constants;
import utilities.Shared;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class HomePageSteps {

    HomePage homePage = new HomePage();

    final String sessionName = Shared.getSharedVariable( Constants.currentWebDriverSession );
    final String productName = "Customer Flight Booking Site";
    final String pageName = "Home Page";

    WebDriver driver = Shared.getWebDriver(sessionName);

    public HomePageSteps() throws NoSuchMethodException {
       }

    @When("user search the domestic flights {string} and {string}")
    public void user_search_domestic_flight(String source,String destination) throws NoSuchMethodException, InterruptedException {
        homePage.searchFlight(source,destination);
    }

    }


