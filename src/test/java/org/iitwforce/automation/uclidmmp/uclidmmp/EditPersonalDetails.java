package org.iitwforce.automation.uclidmmp.uclidmmp;

import java.nio.file.Path;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import net.bytebuddy.utility.RandomString;
import net.moznion.random.string.RandomStringGenerator;

public class EditPersonalDetails extends BaseClass {

	
	@Test(description="Validate the schedule appointmnet for a doctor")
	public void TC_006_ScheduleBooking_Date()
	{
		launchBrowser("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");
		driver.findElement(By.id("username")).sendKeys("ria1");
		driver.findElement(By.id("password")).sendKeys("Ria12345");
		driver.findElement(By.name("submit")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Profile')]")).click();
		driver.findElement(By.id("Ebtn")).click();
		
		//set a new value for firstname text
		
		driver.findElement(By.id("fname")).clear();
		String expectedFName= randomString("IITWFname");
		driver.findElement(By.id("fname")).sendKeys(expectedFName);				
	}
		
	public static String randomString(String s){
		
		Random rand = new Random();
		
		char sUppercase  = (char)(65+rand.nextInt(26));
		char sLowercase  = (char)(97+rand.nextInt(26));
		return s + sUppercase + sLowercase;
		
	}
}
