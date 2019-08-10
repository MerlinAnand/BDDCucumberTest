#Test Application
##Functional UI Test Automation

###Purpose
The purpose of this project is to provide BDD Framework to provide solution of Web Application

The project uses Maven, Cucumber and Selenium (Java Language) to drive web interfaces

#####Installations

JDK 8 Apache Maven -version 3.6.1 https://www.mkyong.com/maven/how-to-install-maven-in-windows/ Environment Variables

To run the tests locally with Chrome, install ChromeDriver and add its location to your system PATH.
To run the tests locally with Firefox, install GeckoDriver and add its location to your system PATH.
To install all cucumber dependencies,

cucumber-core-2.4.0.jar
cucumber-java-2.4.0.jar
cucumber-junit-2.4.0.jar
cucumber-jvm-deps-1.0.6.jar
gherkin-5.0.0.jar
junit-4.12.jar
mockito-all-1.10.19.jar


Maven SetUp
To install all dependencies, run mvn clean install
Running tests To run all the tests, use the command mvn test

####Project Structure
Project Description Now that you know all about installing and running the test for this project, let’s begin the explanation on how it works by having a look at its structure.
The Features folders contains the files describing the test scenarios in Gherkin syntax, also known as feature files.
  Background:
    Given I open "qantas" url

  @personalTable
  Scenario Outline: User one way domestic trip search and Add Baggage
    When user search the domestic flights "<From>" and "<To>"
    Then user selects the red deal and add to trip
    Then user add additional "<Bags>"
    Examples:
      | From   |  To        | Bags |
      | Sydney | Brisbane   |  1   |
      | Sydney | Melbourne  |  2   |
      | Sydney | Perth      |  3   |
      | Sydney | Canberra   |  4   |
      | Sydney | Gold Coast |  5   |


The StepDefinition folders contain the contains the implementation of these test scenarios.
   @When("user search the domestic flights {string} and {string}")
    public void user_search_domestic_flight(String source,String destination) throws NoSuchMethodException, InterruptedException {
        homePage.searchFlight(driver,source,destination);
    }


JunitRunner
Act as a glue between feature file and step definitions

public class JunitParallel {

    @Test
    public void test() {


        Main.main(new String[]{
                        "-p","json:target/report/json/cucumber-parallel-report.json",
                        "-p","html:target/report/html",
                        "-g", "cucumber.steps", "src/test/resources/features",
                        "-t","@personalTable"
                }
        );

    }

}


Page Object Model:

Each Page holds the Page Objects and those are instantiated thru PageFactory

Code Snippet:
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



####Managing Secrets and Passwords and keeping the customer safe
* Use agreed secure storage
* Pass secrets, usernames and passwords from secure store to the jenkins job
* Get agreement to use embedded non-production values for debugging
* Scan the project with SonarCloud or similar for vulnerabilities



####POM Parameters
To ensure the project can be controlled via the command line (and hence CICD), there are a number of parameters to assist in test run execution

* Defaults are bold


POM.xml
      <selenium.java.version>3.5.3</selenium.java.version>
      <cucumber.java.version>4.3.1</cucumber.java.version>

       <build>
              <plugins>
                  <plugin>
                      <groupId>org.apache.maven.plugins</groupId>
                      <artifactId>maven-compiler-plugin</artifactId>
                      <version>3.8.0</version>
                      <configuration>
                          <source>8</source>
                          <target>8</target>
                      </configuration>
                  </plugin>
                  <plugin>
                      <groupId>org.apache.maven.plugins</groupId>
                      <artifactId>maven-surefire-plugin</artifactId>
                      <version>2.22.0</version>
                      <configuration>
                          <testFailureIgnore>true</testFailureIgnore>
                          <includes>
                              <include>cucumber.run.JunitParallel</include>

<table>
 <tr>
  <th>Item</th>
  <th>Purpose</th>
  <th>Expected Values</th>
 </tr>
 <tr>
  <td>environment.name</td>
  <td>Sets the environment to execute against</td>
  <td>[DEV|<b>ST</b>|SIT|PRE|PRD]</td>
 </tr>
 <tr>
  <td>image.step.switch</td>
  <td>After step image capture</td>
  <td>[<b>OFF</b>|ON]</td>
 </tr>
 <tr>
  <td>hub.url</td>
  <td>selects the external selenium grid</td>
  <td>[|valid url]</td>
 </tr>
 <tr>
  <td>fork.count</td>
  <td>number of parallel threads</td>
  <td>[<b>5</b>|valid unsigned integer]</td>
 </tr>
 <tr>
  <td>target.capabilities</td>
  <td>Uses a short name for the required grid capabilities</td>
  <td>[|||||||||||]</td>
 </tr>
 <tr>
  <td></td>
  <td></td>
  <td></td>
 </tr>
 <tr>
  <td></td>
  <td></td>
  <td></td>
 </tr>
 <tr>
  <td></td>
  <td></td>
  <td></td>
 </tr>
 <tr>
  <td></td>
  <td></td>
  <td></td>
 </tr>
 <tr>
  <td></td>
  <td></td>
  <td></td>
 </tr>
 <tr>
  <td></td>
  <td></td>
  <td></td>
 </tr>
 <tr>
  <td></td>
  <td></td>
  <td></td>
 </tr>
 <tr>
  <td></td>
  <td></td>
  <td></td>
 </tr>
 <tr>
  <td></td>
  <td></td>
  <td></td>
 </tr>
 <tr>
  <td></td>
  <td></td>
  <td></td>
 </tr>
</table>
Reports will be stored in the html:target/report/html

