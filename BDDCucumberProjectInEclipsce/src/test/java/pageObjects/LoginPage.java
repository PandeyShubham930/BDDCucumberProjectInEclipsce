package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitHelper;

public class LoginPage 
{
	public WebDriver ldriver;
	WaitHelper helper;
	
	
	@FindBy(id="Email")
	@CacheLookup // annotation for same instance will be used, only use for static elements not dynamic
	WebElement txtEmail;
	
	@FindBy(id="Password")
	@CacheLookup
	WebElement txtPassword;
	
	@FindBy(xpath = "//button[@type='submit']")
	@CacheLookup
	WebElement btnLogin;
	
	@FindBy(css = "a[href='/logout']")
	@CacheLookup
	WebElement lnkLogout;
	
	public LoginPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this); //Initialising the elements
		helper = new WaitHelper(ldriver);
	}
	
	public void setUsername(String uname)
	{
		txtEmail.clear();
		txtEmail.sendKeys(uname);
	}
	
	public void setPassword(String pwd)
	{
		txtPassword.clear();
		txtPassword.sendKeys(pwd);
	}
	
	public void clickOnLogin()
	{
		btnLogin.click();
	}
	
	public void clickOnLogout()
	{
		helper.waitForElement(lnkLogout, Duration.ofSeconds(10));
		lnkLogout.click();
	}
	

}
