package tests;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import pages.TWTLoginPage;

public class LoginTest {

	@Test
	public void init() throws Exception{
		TWTLoginPage loginpage = PageFactory.initElements(TestBase.driver, TWTLoginPage.class);
		loginpage.clickOnLoginBigger();
//		Assert.assertTrue(false);
	}
	
	@AfterMethod //AfterMethod annotation - This method executes after every test execution
	 public void screenShot(ITestResult result){
		//using ITestResult.FAILURE is equals to result.getStatus then it enter into if condition
		 if(ITestResult.FAILURE==result.getStatus()){
			 try{
				 // To create reference of TakesScreenshot
				 TakesScreenshot screenshot=(TakesScreenshot)TestBase.driver;
				 // Call method to capture screenshot
				 File src=screenshot.getScreenshotAs(OutputType.FILE);
				 // Copy files to specific location 
				 // result.getName() will return name of test case so that screenshot name will be same as test case name
				 FileUtils.copyFile(src, new File("/Users/zhouwenjun/Study/Selenium JAva/workspace0/SeleniumUITestA/FailedTessScreenshots/"+result.getName()+".png"));
				 System.out.println("Successfully captured a screenshot");
			 }catch (Exception e){
				 System.out.println("Exception while taking screenshot "+e.getMessage());
			 } 
		 }
		 TestBase.driver.quit();
	 }
}
