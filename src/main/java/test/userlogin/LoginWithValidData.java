package test.userlogin;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.LoginSignupPage;
import utilities.PropertyReader;

public class LoginWithValidData {
	WebDriver driver;
	LoginSignupPage objLoginSignupPage ;
	
	 JavascriptExecutor js;
	String driverPath = null;
	String home_URL,user_Email,user_Password;
	
	@BeforeMethod
	 public void beforeMethod() throws InterruptedException, IOException {
			

		driverPath = PropertyReader.getProperty("Chrome_Driver_Path");
		user_Email=PropertyReader.getProperty("user_Email");
		user_Password=PropertyReader.getProperty("user_Password");
		home_URL=PropertyReader.getProperty("home_URL");
			  System.setProperty("webdriver.chrome.driver", driverPath);
				driver = new ChromeDriver();
				 js = (JavascriptExecutor) driver;
				 
		  }

	@Test
	public void loginWithValidDetailsTest() throws InterruptedException {
		driver.get(home_URL);//getURL
		
		
		Thread.sleep(5000);
		objLoginSignupPage =new LoginSignupPage(driver);
		
		//click on login/signup btn
		objLoginSignupPage.clickOnLoginSignup();
		
		driver.manage().window().maximize();
		Thread.sleep(5000);
		//enter email
		objLoginSignupPage.enterUserName(user_Email);
		
		//enter password
		objLoginSignupPage.enterPasswrod(user_Password);
	   Thread.sleep(5000);
	   
	   // click on login button
	   	objLoginSignupPage.login();
		
	   	//validation of login
		Thread.sleep(5000);
		
		String welcomMsg=driver.findElement(By.xpath("/html/body/header/div/div/div/div[2]/div")).getText();
		Assert.assertTrue(welcomMsg.contains("Welcome To Car rental portal"));
		
	}
	
	@AfterMethod
	  public void afterMethod() {
		  driver.quit();
	  }
	
	
}