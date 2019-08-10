package utilities;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Shared {

    private static ThreadLocal<Map<String, WebDriver>> sharedWebDrivers = new ThreadLocal<>();
    private static ThreadLocal<Map<String, String>> sharedVariables = new ThreadLocal<>();
    private static ThreadLocal<Map<String, Object>> sharedObjects = new ThreadLocal<>();

    private static void setSharedObjectsIfNull(){
        if( sharedObjects.get() == null )
            sharedObjects.set( new HashMap<>());
    }

    public static void setSharedObjects( String identifer, Object o ){
        setSharedObjectsIfNull();
        sharedObjects.get().put( identifer, o );
    }

    public static Object getSharedObjects(String identifier ){
        setSharedObjectsIfNull();
        return sharedObjects.get().get(identifier);
    }


    /**
     * stores the web driver by session name (eg vlocity etc)
     * @param session unique session name
     * @param driver driver to store
     */
    public static void setWebDriver(String session, WebDriver driver) {

        setSharedWebDriversIfNull();
        sharedWebDrivers.get().put(session, driver);
    }

    /**
     * gets the stored driver session
     * @param session session name used to store the driver
     * @return webdriver for use in steps and page drivers
     */
    public static WebDriver getWebDriver(String session) {

        setSharedWebDriversIfNull();
        return sharedWebDrivers.get().get(session);
    }

    /**
     * Gets the session list stored in the shared threadsafe store
     * @return List of sessions
     */
    @SuppressWarnings("unchecked")
    public static List<String> listDriverSessions() {

        setSharedWebDriversIfNull();
        return new ArrayList(sharedWebDrivers.get().keySet());
    }

    /**
     * closes and remove the driver from the store
     * @param session name of session to kill
     */
    public static void killWebDriver(String session) {

        WebDriver w = sharedWebDrivers.get().get(session);
        if ( w!= null ) {
            w.close();
            w.quit();
        }

        sharedWebDrivers.get().remove(session);
    }

    /**
     * sets a stored variable for the test session
     * @param key name of the retreival key
     * @param value value (string) to store
     */
    public static void setSharedVariable(String key, String value) {

        setSharedVariablesIfNull();
        sharedVariables.get().put(key, value);
    }

    /**
     * gets a stored string value based on the key
     * @param key identifier to use
     * @return the string value
     */
    public static String getSharedVariable(String key) {

        setSharedVariablesIfNull();
        return sharedVariables.get().get(key);
    }

    /**
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<String> getSharedVariableNames() {

        setSharedVariablesIfNull();
        return new ArrayList(sharedVariables.get().keySet());
    }

    /**
     * sets the variable with a new hashmap when its blank
     */
    private static void setSharedVariablesIfNull() {

        if (sharedVariables.get() == null)
            sharedVariables.set(new HashMap<>());
    }

    /**
     * sets the variable with a new hashmap when its blank
     */
    private static void setSharedWebDriversIfNull() {

        if (sharedWebDrivers.get() == null)
            sharedWebDrivers.set(new HashMap<>());
    }



}
