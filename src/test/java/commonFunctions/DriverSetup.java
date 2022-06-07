package commonFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;


public class DriverSetup {

	public static WebDriver driver ;
	public static  ConfigFileReader configFileReader=new ConfigFileReader();
	@BeforeMethod
	public static void setupBrowser() {
		
		System.out.println("TestBase.setup()");
		configFileReader = new ConfigFileReader();
		String browserType=configFileReader.getBrowserType().toLowerCase().toLowerCase();
        switch(browserType) {
    	
    	case "chrome":
    		WebDriverManager.chromedriver().setup();
    	    driver= new ChromeDriver();
		break;
		
    	case "firefox":
    		WebDriverManager.firefoxdriver().setup();
    		driver=new FirefoxDriver();
    		
    	break;
    		
		default:
			break;
}
	}

	@AfterMethod
	public void closeBrowser() {
		
		driver.quit();
	}

}