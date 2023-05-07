package UIRunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/report.html",
                "json:target/cucumber-reports/cucumber.json",
                "rerun:target/rerun.txt"
        },
        features = "src/test/resources/uiFeatures",
        glue = "step_definitions",
        stepNotifications = true,
        dryRun = false
        ,tags = "@AD3"
        // you can combine tags with "and, or, and not"
)
public class CukesRunner {
}
