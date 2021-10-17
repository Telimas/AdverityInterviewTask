import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(
    strict = true,
    plugin = ["pretty", "json:target/cucumber.json"],
    features = ["classpath:features"],
    glue = ["automation.stepDefinitions", "automation.cucumber"],
    tags = [""],
    monochrome = true
)
class RunCucumberTests