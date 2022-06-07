package runner;

import commonFunctions.CutomizeTestNGCucmberRunner;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" },
        features = {"src/test/resources/features"},
        glue = {"stepDefinition","hooks"},
        tags = "@regression"
)

public class Runner extends CutomizeTestNGCucmberRunner {

}
