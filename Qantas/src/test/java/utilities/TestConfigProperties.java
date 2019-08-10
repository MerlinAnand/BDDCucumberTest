package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.NoSuchFileException;
import java.util.Properties;

public class TestConfigProperties {

    String fileName = "";
    InputStream input = null;
    Properties properties = null;

    /**
     * C'Tor opens the file as input
     * and creates a new properties
     * @param name short name of the properties file
     */
    public TestConfigProperties( String name ) throws NoSuchFileException {

        this.fileName = name.toLowerCase() + ".properties";

        input = getClass().getClassLoader()
                .getResourceAsStream( fileName );

        if(input == null)
            throw new NoSuchFileException("File: "+ fileName +" was not found in the project." +
                                                  " Check the incoming value matches a properties filename");

        properties = new Properties();
    }

    /**
     * extracts the properties for use in calling class
     * @return properties object
     */
    public Properties getProperties() {

        try {
            properties.load( input );

        } catch (IOException ex) {
            ex.printStackTrace();

        } finally {
            try {
                input.close();

            } catch (IOException ex) {
                ex.printStackTrace();

            }
        }

        return properties;
    }
}
