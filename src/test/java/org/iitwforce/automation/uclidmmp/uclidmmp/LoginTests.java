package org.iitwforce.automation.uclidmmp.uclidmmp;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginTests extends BaseClass {

	@Test(description="Login to Patient Module with valid credentails")
	public void TC_001_Login_with_valid_creds()
	{
		launchBrowser("http://162.144.124.67/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");
		String expected="Patient Portal";
		driver.findElement(By.id("username")).sendKeys("ria1");
		driver.findElement(By.id("password")).sendKeys("Ria12345");
		driver.findElement(By.name("submit")).click();
		String actual = driver.findElement(By.xpath("//h3[@class='panel-title']")).getText().trim();
		Assert.assertEquals(actual, expected); 	
	}
	
	
	@Test(description="Validate the tabs")
	public void TC_002_validate_tabs()
	{
		launchBrowser("http://162.144.124.67/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");
		String expected="Patient Portal";
		driver.findElement(By.id("username")).sendKeys("ria1");
		driver.findElement(By.id("password")).sendKeys("Ria12345");
		driver.findElement(By.name("submit")).click();
		String actual = driver.findElement(By.xpath("//h3[@class='panel-title']")).getText().trim();
		 
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actual, expected);
		
		sa.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'HOME')]")).isDisplayed());
		sa.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Profile')]")).isDisplayed());
		sa.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Schedule Appointment')]")).isDisplayed());
		sa.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Fees')]")).isDisplayed());
		sa.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Search Symptoms')]")).isDisplayed());
		sa.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Messages')]")).isDisplayed());
		sa.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Logout')]")).isDisplayed());
		sa.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Information')]")).isDisplayed());
		sa.assertAll();
	}
	
	@Test(description="Wrong username and password scenaro1")
	public void TC_003_Wrong_UserNamePassword_1()
	{
		
		launchBrowser("http://162.144.124.67/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");
		String expected = "Wrong username and password.";
		driver.findElement(By.id("username")).sendKeys("ria1");
		driver.findElement(By.id("password")).sendKeys("Ria$$");
		driver.findElement(By.name("submit")).click();

		Alert alert = driver.switchTo().alert();
		String actual = alert.getText();
		alert.accept();
		Assert.assertEquals(actual.trim(), expected.trim()); 	
	}	
	
	@Test(description="Wrong username and password scenaro1 ")
	public void TC_004_Wrong_UserNamePassword_2()
	{
		launchBrowser("http://162.144.124.67/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");
		String expected = "Wrong username and password.";
		driver.findElement(By.id("username")).sendKeys("ria1$");
		driver.findElement(By.id("password")).sendKeys("Ria12345");
		driver.findElement(By.name("submit")).click();
		Alert alert = driver.switchTo().alert();
		String actual = alert.getText();
		alert.accept();
		Assert.assertEquals(actual.trim(), expected.trim()); 	
	}	
	
	@Test(description="Wrong username and password scenaro1 ")
	public void TC_005_Wrong_UserNamePassword_3()
	{
		launchBrowser("http://162.144.124.67/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");
		String expected = "Wrong username and password.";
		driver.findElement(By.id("username")).sendKeys("ria1%");
		driver.findElement(By.id("password")).sendKeys("Ria1$$");
		driver.findElement(By.name("submit")).click();
		
		Alert alert = driver.switchTo().alert();
		String actual = alert.getText();
		alert.accept();
		Assert.assertEquals(actual.trim(), expected.trim());	
	}	
}

