package UnitTests;
import org.junit.Test;
import org.testng.Assert;
import utilities.TestConfigProperties;
import java.nio.file.NoSuchFileException;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestConfigPropertiesUnit {

    @Test
    public void newConfig() throws NoSuchFileException {
        List<String> envs = Stream.of(
                "PRD"
        ).collect(Collectors.toList());

        for( String env : envs){
            TestConfigProperties testConfigProperties = new TestConfigProperties(env.toLowerCase());
            Assert.assertNotNull(testConfigProperties);
            Properties props = testConfigProperties.getProperties();

            System.out.println("==============");
            System.out.println(env);
            for(String s : props.stringPropertyNames()){
                System.out.println(s);
                System.out.println( props.get(s));
            }
        }
    }
}
