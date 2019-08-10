package cucumber.steps;

import cucumber.api.java.en.Given;
import org.openqa.selenium.remote.RemoteWebDriver;
import utilities.*;

import java.util.Properties;

public class BrowserStartAndNavSteps {

    /**
     * Opens threadsafe browser session and navigates to the system URL
     * Select the system from the cucumber step, environment from POM/cmd line
     * @param systemName string representing system to drive
     * @throws Exception from TestProperties if file can't be found
     */
    @Given("^I open \"(.*)\" url$")
    public void startSession( final String systemName ) throws Exception {
        // fires up browser and stores the driver reference
        new TargetBrowserMobile().startBrowser( systemName.toLowerCase() );

        Properties properties = new TestConfigProperties( System.getProperty("environment.name").toLowerCase() ).getProperties();

        // uses the reference to navigate based on configured environment from POM/cmd line
        // throws error if the property is not located in the file
        Shared.getWebDriver( systemName.toLowerCase()).get( properties.getProperty( systemName.toLowerCase() + ".url"));

        // correlation data - this ID is unique per execution
        Shared.setSharedVariable("RemoteDriver Session id", ((RemoteWebDriver)Shared.getWebDriver(systemName.toLowerCase())).getSessionId().toString());



    }

}
