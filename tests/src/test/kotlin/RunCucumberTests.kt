import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(
    strict = true,
    plugin = ["pretty", "json:target/cucumber.json", "io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm"],
    features = ["classpath:features"],
    glue = ["automation.stepDefinitions", "automation.cucumber"],
    tags = ["@Task1"],
    monochrome = true
)
class RunCucumberTests