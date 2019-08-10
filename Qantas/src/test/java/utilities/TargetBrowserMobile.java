package utilities;


import com.google.common.base.Strings;
import cucumber.api.java.en.Given;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TargetBrowserMobile {

    public void startBrowser( final String system ) throws Exception {

        Properties properties = new TestConfigProperties("device").getProperties();

        Shared.setSharedVariable(Constants.currentWebDriverSession, system.toLowerCase() );

        WebDriver d = new FluentDriver().withCapabilities(
                getCapabilities( properties.getProperty("target.capabilities") )
        ).start();

        try {
            d.manage().window().maximize();
        } catch( WebDriverException wde){
            wde.printStackTrace();
        }

        System.out.println("===== Opening session to drive " + system +". =======");

        String timeout = System.getProperty("browser.time");
        if (Strings.isNullOrEmpty(timeout))
            timeout = "30";

        Shared.setSharedVariable("browser.timeout", timeout);

        d.manage().timeouts().implicitlyWait(Long.parseLong( timeout ) , TimeUnit.SECONDS );

        Shared.setWebDriver(system.toLowerCase(), d);

    }

    /**
     * Converts a string to a DC for use with a driver
     * @param capsString - string formated in CSV with key pairs as key:value,...
     * @return WebDriver/Appium capabilities
     */
    public DesiredCapabilities getCapabilities(String capsString){

        DesiredCapabilities dc = new DesiredCapabilities();

        //this is a slightly loose range check, if its not right, the launch code
        // will fail
        List<String> caps;
        try {
            caps = Arrays.asList(capsString.split(","));
        } catch( NullPointerException npe){
            return dc;
        }

        for( String cap : caps){
            String[] values = cap.split(":");
            Assert.assertEquals(values.length, 2, "Expected size of 2, instead got" +
                    values.length +". Check the parameter string");
            dc.setCapability(values[0],values[1]);
        }

        return dc;
    }

    /**
     * Close the session and eyes out
     * @param session
     */
    @Given("^the user closes the \"(.*)\" session$")
    public void closeWebSession(String session){

        /*EyesWrapper eyes = Shared.getSharedEyes(session);

        try {
            eyes.closeEyes();
        } catch ( com.applitools.eyes.TestFailedException tfe ){
            tfe.printStackTrace();
        }

        Shared.killWebDriver(session);
        eyes.closeAborted();*/

    }


}
