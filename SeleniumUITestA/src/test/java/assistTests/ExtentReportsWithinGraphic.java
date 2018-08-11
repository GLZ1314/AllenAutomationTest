package assistTests;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReportsWithinGraphic {
	
	ExtentReports extent;
	ExtentTest logger;
	WebDriver driver;
	
	@BeforeTest
	public void startReport(){
		extent = new ExtentReports("reports/test-output/reportDemo.html",true);
		extent
			.addSystemInfo("Host Name", "TestMachine")
			.addSystemInfo("Environment","Automation Testing")
			.addSystemInfo("Tester", "Allen");
		extent.loadConfig(new File("extent-config.xml"));
	}
	
	
	public static String getScreenshot(WebDriver driver,String screenshotName) throws Exception{
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = "/Users/zhouwenjun/Study/Selenium JAva/workspace0/SeleniumUITestA/FailedTessScreenshots/"+screenshotName+dateName+".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
	
	@Test
	public void passTest() {
			//extent.startTest("TestCaseName", "Description")
			//TestCaseName – Name of the test
			//Description – Description of the test
			//Starting test
			logger = extent.startTest("passTest");
			Assert.assertTrue(true);
			//To generate the log when the test case is passed
			logger.log(LogStatus.PASS, "Test Case Passed is passTest");
	}
	
	@Test
	public void failTest(){
                //My intention is to fail this method
                //If this method fails, then it goes to the @AfterMethod and calls the getScreenshot method and captures a screenshot and place it in the extent reports
		logger = extent.startTest("failTest");
		 System.setProperty("webdriver.chrome.driver","src/main/java/drivers/chromedriver");
		driver = new ChromeDriver();
		driver.get("https://www.softwaretestingmaterial.com");
		String currentURL = driver.getCurrentUrl();
		Assert.assertEquals(currentURL, "NoTitle");
		logger.log(LogStatus.PASS, "Test Case (failTest) Status is passed");
	}
	
	@Test
	public void skipTest(){
		logger = extent.startTest("skipTest");
		throw new SkipException("Skipping - This is not ready for testing ");
	}
	
	@AfterMethod
	public void getResult(ITestResult result) throws Exception{
		if(result.getStatus()==ITestResult.FAILURE) {
			logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
			logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
			//To capture screenshot path and store the path 
			
			String screenshotPath = ExtentReportsWithinGraphic.getScreenshot(driver,result.getName());
			//To add it into the content
			logger.log(LogStatus.FAIL, logger.addScreenCapture(screenshotPath));
		}else if(result.getStatus() == ITestResult.SKIP){
			logger.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
		}
		// ending test
		//endTest(logger) : It ends the current test and prepares to create HTML report
		extent.endTest(logger);
			
	}
	
	@AfterTest
	public void endReport()
	{
		//writing everything to document
		//flush() - to write or update test information to your report.
		extent.flush();
		extent.close();
	}
}
