package com.newt.DemoWebJunit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class WebDemoTest {
	
	private static String weburl="http://localhost:8181/DemoWebApp/login/Welcome.html";
	private WebDriver driver;

	@Before
	public void setUp() {
		// Create a new instance of the html unit driver
		driver = new HtmlUnitDriver();

		//Navigate to DevOps Demo WebApp Home Page
		driver.get(weburl);
		System.out.println("Inside WebDemoTest :: setUp(), Driver get successfully ");
	}
	
	@Test
	public void verifyHomePage() 
	{
		// verify title of index page
		System.out.println("Testing for Title Verification");
		verifyTitle("Welcome");
	}
	
	@Test
	public void testcaseforLoginSuccess() 
	{
		//verify User Logins successfuly
		System.out.println("Testing for User Credentials Verification");
		verifyLoginSuccess("user1","newt123");
	}
	
	@Test
	public void testcaseforLoginFail() 
	{
		//verify User Logins un-successfuly
		System.out.println("Testing for User Credentials Verification");
		verifyLoginFail("user1","newt12345");
	}
	
	//Test Case Function : Verifies if the User is on the Correct Page
	private void verifyTitle(String expectedTitle) {
		//get the title of the page
		String actualTitle = driver.getTitle();
		System.out.println("actualTitle:"+actualTitle);
		
		// verify title
		//assertThat(actualTitle, "");
		assertEquals("tc1 :: Valid Webpage title ", actualTitle, expectedTitle);	
	}
	
	//Test Case Function : Verifies if the User is able to login Successfully with valid Credentials
	private void verifyLoginSuccess(String userName, String password) {
		String expectedMessage = "You have login successfully ";
		// find the Username Text Box
		WebElement element = driver.findElement(By.xpath("//*[@id='usrname']"));

		// set the Username in Text Box
		element.sendKeys(userName);

		// find the Password Text Box
		element = driver.findElement(By.xpath("//*[@id='pwd']"));
		
		// set the Password in input Text Box
		element.sendKeys(password);

		// find the Login Button
		element = driver.findElement(By.xpath("//*[@type='submit']"));
		
		// submit form
		element.submit();
		
		// find the Successful Message 
		element = driver.findElement(By.xpath("(//span)[1]"));
		
		// get the Successful Message		
		String actualLoginMessage = element.getText();
//		System.out.println("actualLoginMessage :: " + actualLoginMessage);
		assertEquals("enterCredentials :: valid username and password", actualLoginMessage, expectedMessage);
	}
	
	//Test Case Function : Verifies if the User is NOT able to login Successfully with Invalid Credentials
	private void verifyLoginFail(String userName, String password) {
		String expectedMessage = "Invalid username or password, please try again ";
		// find the input Username Text Box
		WebElement element = driver.findElement(By.xpath("//*[@id='usrname']"));

		// set the user name in the Text Box
		element.sendKeys(userName);

		// find the input Password Text Box
		element = driver.findElement(By.xpath("//*[@id='pwd']"));
		
		// set the Password in the Text Box
		element.sendKeys(password);

		// find the Login Button
		element = driver.findElement(By.xpath("//*[@type='submit']"));
		
		// submit form
		element.submit();
		
		// find the Successful Message
		element = driver.findElement(By.xpath("(//span)[1]"));
		
		// get the Successful Message
		String actualLoginMessage = element.getText();
		System.out.println("actualLoginMessage :: " + actualLoginMessage);
		assertEquals("enterCredentials :: valid username and password", actualLoginMessage, expectedMessage);
	}
	
	@After
	public void endTest() {
		driver.quit();//
		System.out.println("Inside WebDemoTest :: endTest(), Driver quit successfully... ");
	}
	
	


}
