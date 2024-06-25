package stepDefinations;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import pageObjects.LoginPage;

public class stepDefinations 
{
	 WebDriver driver;	
	 LoginPage lp;
	 public Logger logger;
	 
	 @Before
	 public void setup() throws IOException
	 {
		 //Reading properties
		Properties configprop = new Properties();
		FileInputStream fi = new FileInputStream("config.properties");
		configprop.load(fi);
		
		String browser = configprop.getProperty("browser");
		if(browser.equalsIgnoreCase("chrome"))
		{
			//no need for set up properties to opening an browser in latest selenium version
			driver= new ChromeDriver();
			driver.manage().window().maximize();
		}
		
		else if(browser.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		}
		
		else if(browser.equalsIgnoreCase("safari"))
		{
			driver= new SafariDriver(); 
			driver.manage().window().maximize();	
		}
		
		logger=Logger.getLogger("BDDCucumberProjectInEclipsce");//adding logger
		PropertyConfigurator.configure("log4j.properties");
	 }
	 
	@Given("user launch the chrome browser")
	public void user_lauch_the_chrome_browser() 
	{
		
		logger.info("launching browser");
		lp= new LoginPage(driver);
	}

	@Given("user opens the url {string}")
	public void user_opens_the_url(String url) 
	{
		logger.info("entering url");
		driver.get(url); 
	}

	@Given("user enters the email as {string}")
	public void user_enters_the_email_as(String username) 
	{
	    lp.setUsername(username);
	}

	@Given("user enters the password as {string}")
	public void user_enters_the_password_as(String password) 
	{
		lp.setPassword(password);    
	}

	@When("user cliks on login")
	public void user_cliks_on_login() throws InterruptedException 
	{
		logger.info("logging in");
		lp.clickOnLogin();
		Thread.sleep(3000);
	}

	@Then("page title should be {string}")
	public void page_title_should_be(String expectedTitle) 
	{
		if(driver.getPageSource().contains("Login was unsuccessful"))
		{
			driver.close();
			Assert.assertTrue(false);
		}
		else
		{
			logger.info("asserting the tittle");
			Assert.assertEquals(driver.getTitle(), expectedTitle);
		}	
	}

	@When("user clicks on logout")
	public void user_clicks_on_logout() throws InterruptedException 
	{
		logger.info("looging out");
		lp.clickOnLogout();
		Thread.sleep(3000);
	}

	@Then("user close the browser")
	public void user_close_the_browser() 
	{
		logger.info("closing the browser");
	 driver.close();    
	}

}
