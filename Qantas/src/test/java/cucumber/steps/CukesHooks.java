package cucumber.steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import org.testng.util.Strings;
import utilities.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.io.IOException;

public class CukesHooks {

    final String sessionName = Shared.getSharedVariable( Constants.currentWebDriverSession );

    WebDriver driver = Shared.getWebDriver(sessionName);

    @Before
    public void beforeScenario(Scenario scenario) throws IOException, IllegalAccessException {
        // creates a shared instance of XL for OR
        final String sessionName = Shared.getSharedVariable( Constants.currentWebDriverSession );
        WebDriver driver = Shared.getWebDriver(sessionName);
    }

    @After
    public void afterScenario(Scenario scenario) {
        System.out.println(scenario.getName());
        capturedVariables( scenario );
        if (scenario.isFailed()) {
           failedScenarioReporting( scenario );
        }
        Shared.killWebDriver(sessionName);
    }

    @AfterStep
    public void afterEachStep(Scenario scenario){

        String grab = System.getProperty("step.image.grab");
        if (Strings.isNullOrEmpty( grab ) || grab.toUpperCase().equals( "OFF") )
            return;
        //
        for (String session : Shared.listDriverSessions()) {
            WebDriver w = Shared.getWebDriver(session);
            try {
                scenario.embed(
                        ((TakesScreenshot) w)
                                .getScreenshotAs(OutputType.BYTES)
                        , "image/png");

            } catch (Exception e) {
                System.out.println("======= error getting screen from driver =========");
                e.printStackTrace();
                System.out.println("======= -------------------------------- =========");
            }
            // Browser Logs
            for( LogEntry logEntry : w.manage().logs().get(LogType.BROWSER)) {
                scenario.write(logEntry.getLevel() + " " + logEntry.getMessage());
            }
        }
    }

    public void capturedVariables( Scenario scenario){

        scenario.write("==== Captured Variables ====");

        for(String variable : Shared.getSharedVariableNames()){
            scenario.write(variable + " = " + Shared.getSharedVariable(variable));
        }

        scenario.write("==== ------------------ ====");
    }

    public void failedScenarioReporting( Scenario scenario ){

        // capture the error screens
        for (String session : Shared.listDriverSessions()) {
            WebDriver w = Shared.getWebDriver(session);
            try {
                scenario.embed(
                        ((TakesScreenshot) w)
                                .getScreenshotAs(OutputType.BYTES)
                        , "image/png");

            } catch (Exception e) {
                System.out.println("======= error getting screen from driver =========");
                e.printStackTrace();
                System.out.println("======= -------------------------------- =========");
            }

            for( LogEntry logEntry : w.manage().logs().get(LogType.BROWSER)) {
                scenario.write(logEntry.getLevel() + " " + logEntry.getMessage());
            }

        }
    }
}
