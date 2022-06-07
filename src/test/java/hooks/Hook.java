package hooks;

import java.io.IOException;

import commonFunctions.CaptureScreenshoot;
import commonFunctions.DriverSetup;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;


public class Hook extends DriverSetup {

	@BeforeStep
	public void beforeStep() {
	}

	@AfterStep
	public void afterStep() {
	}

	@Before
	public void beforeScenario() {
	}

	@After
	public void afterScenario(Scenario scenario) throws IOException {
		scenario.attach(CaptureScreenshoot.getScreenByte(driver, scenario), "image/png", scenario.getName());
	}
}
