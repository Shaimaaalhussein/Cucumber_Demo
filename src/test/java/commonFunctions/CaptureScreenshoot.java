package commonFunctions;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.Scenario;

public class CaptureScreenshoot {
	
	public static  ConfigFileReader configFileReader=new ConfigFileReader();
	public static String getScreenshotName(String methodName) {
		Date d = new Date();
		String fileName = methodName + "_" + d.toString().replace(":", "_").replace(" ", "_") + ".png";
		return fileName;
	}
	public static String takeScreenshot(WebDriver driver, String methodName) {
		String fileName = getScreenshotName(methodName);
		
		String directory =configFileReader.getScreenShotFilePath();
		new File(directory).mkdirs();
		String path = directory + fileName;

		try {
			File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(path));
			System.out.println("*************************************");
			System.out.println("Screenshot stored at: " + path);
			System.out.println("*************************************");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(path);
		return path;
	}
	public static byte[] getScreenByte(WebDriver driver, Scenario scenario) throws IOException {
		String fileName = getScreenshotName(scenario.getName());
		String directory =configFileReader.getScreenShotFilePath();
		new File(directory).mkdirs();
		String path = directory + fileName;
			File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(path));
			byte[] file=FileUtils.readFileToByteArray(screenshot);
		
		return file;
	}
}
