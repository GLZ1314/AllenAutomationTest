package tests;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import config.BrowserType;

/*
 * Loading the configurations from properties files
 * Initializing the WebDriver 
 * Implicit Waits
 * Extend reports
 * create the object of FileInputStream
 * 
 * 
 */
public class TestBase{

	private static final String configFilePath=
			"src/main/java/config/config.properties";
	public static WebDriver driver=null;
	
	@BeforeSuite
	private void initialize() throws IOException
	{
		Properties prop = this.readConfig();
		int browserType = Integer.valueOf(prop.getProperty("browserType"));
		String browser=null;
		switch(browserType) {
		case 1:
			browser="webdriver.chrome.driver";
			System.setProperty(browser,prop.getProperty(browser));
			driver = new ChromeDriver();
			//To maximize browser
				driver.manage().window().maximize();
			//Implicit wati
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			//To open URL
				driver.get(prop.getProperty("URL"));
				break;
//		case 2:;
//		case 3:;
		default:
			try {
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Browser Type config is unavailable");
			}
		}
	}
	
	@AfterSuite
	//Test cleanup
	public void TeardownTest()
	{
		TestBase.driver.quit();
	}
	
	
	private Properties readConfig() throws IOException
	{
		Properties prop = new Properties();
		try {
			FileReader fr =new FileReader(configFilePath);
			BufferedReader br = new BufferedReader(fr);
			String line=null;
			while((line=br.readLine())!=null)
			{
				if(line.contains("="))
				{
					String[] configStr=line.split("=");
					System.out.println(configStr[0]);
					prop.setProperty(configStr[0],configStr[1]);
				}
			}
			fr.close();
			br.close();

				System.out.println(prop);

		
			
		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
		}
		return prop;
	}
	

}
