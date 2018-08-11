package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class TWTLoginPage {

	WebDriver driver;
	
	public TWTLoginPage(WebDriver driver)
	{
		this.driver= driver;
	}
	
	//Using FindBy for locating elements
	@FindBy(how=How.XPATH, using="//*[@id=\"doc\"]/div/div[1]/div[1]/div[2]/div[2]/div/a[2]") WebElement LoginBtnBigger;
	@FindBy(how=How.XPATH, using="//*[@id=\"doc\"]/div/div[1]/div[1]/div[2]/div[1]/a") WebElement LoginBtnSmaller;
	//Defining all the user actions(Methods) that can be performed in the application login page
	
	public void clickOnLoginBigger()
	{
		LoginBtnBigger.click();
	}
	
	public void clickOnLoginSmaller()
	{
		LoginBtnSmaller.click();
	}
	//This method to click on Profile D
}
