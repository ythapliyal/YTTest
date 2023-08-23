package org.iitwforce.automation.uclidmmp.uclidmmp;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DataDrivenLoginTests extends BaseClass {

	@Test(dataProvider = "loginData", dataProviderClass = ExcelDataSupplier.class)
	public void TestLogin(String userName, String password) throws Exception {
		
		launchBrowser("http://162.144.124.67/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");
		String expected="Patient Portal";
		driver.findElement(By.id("username")).sendKeys(userName);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.name("submit")).click();
		String actual = driver.findElement(By.xpath("//h3[@class='panel-title']")).getText().trim();
		Assert.assertEquals(actual, expected); 	
	}	
}

