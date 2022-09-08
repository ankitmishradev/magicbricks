package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = { "src/test/resources/features" }, glue = { "stepDefs" }, plugin = {
		"pretty:target/cucumber-reports/cucumber-pretty.html", "json:target/cucumber-reports/cucumber-report.json",
		"html:target/cucumber-reports/cucumber-report.html", }, monochrome = true)
public class TestRunner extends AbstractTestNGCucumberTests {
}
