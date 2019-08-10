package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Gets the By locator for page objects from XL sheet OR
 * Gets the object based on the locator/driver context
 */

public class Model {

    /**
     * Gets list to work with
     * @param locator string
     * @return boolean True/false if element is present
     * @throws NoSuchMethodException
     */
    public static boolean WaitForObjectToAppear(By locator) throws NoSuchMethodException {

        try {
            return new WebDriverWait(Shared.getWebDriver( Shared.getSharedVariable( Constants.currentWebDriverSession))
                    , Long.parseLong(System.getProperty("browser.explicit.wait")))
                    .until(
                            ExpectedConditions.visibilityOfAllElementsLocatedBy(locator)
                    ).size() > 0;
        } catch ( TimeoutException toe ){
            // forces a clean return because the items are sometime not appearing (might be a marketing item)
            return false;
        }
    }

    /**
     * getElementTextList - converts list of elements into their contained text and returns the list<String></String>
     * @param elements list of webelements
     * @return List String of the contained visible text (assumes the simple case of visible text)
     */
    public static List<String> getElementTextList( List<WebElement> elements ){
        return elements.stream().map(WebElement::getText).collect(Collectors.toList());
    }


}
