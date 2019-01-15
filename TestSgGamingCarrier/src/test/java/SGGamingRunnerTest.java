import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
		features = "classpath:features",
		glue = "com.sg.game.steps_def",
		plugin = {"pretty",
				"html:target/testreports/testResults-html-report",
				"json:target/testreports/json/cucumber.json",
				"junit:target/testreports/xml/cucumber.xml"
		},
		tags = {}
)
public class SGGamingRunnerTest {

}
