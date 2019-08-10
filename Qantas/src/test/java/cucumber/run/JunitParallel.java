package cucumber.run;

import cucumber.api.cli.Main;
import org.junit.Test;


/**
 * @author Merlin
 */

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
