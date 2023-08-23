package org.iitwforce.automation.uclidmmp.uclidmmp;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.internal.junit.ArrayAsserts;

public class DemoMRS extends BaseClass{

	@Test(description="Add new service type")
	public void TC_001_Add_Service_Type() throws InterruptedException
	{
		launchBrowser("https://openmrs.org/demo/");

		driver.findElement(By.xpath("//span[contains(text(),'Enter the OpenMRS 2 Demo')]")).click();
		
		String expectedName ="MentalHealth51";
		String expectedDuration ="1000";
		
		Thread.sleep(2000);
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("password")).sendKeys("Admin123");
		Thread.sleep(2000);
		driver.findElement(By.id("Inpatient Ward")).click();
		driver.findElement(By.id("loginButton")).click();
	
		driver.findElement(By.id("appointmentschedulingui-homeAppLink-appointmentschedulingui-homeAppLink-extension")).click();
		driver.findElement(By.id("appointmentschedulingui-manageAppointmentTypes-app")).click();
		driver.findElement(By.xpath("//button[@class='confirm appointment-type-label right']")).click();
	    driver.findElement(By.id("name-field")).clear();
	    driver.findElement(By.id("name-field")).sendKeys(expectedName);
        driver.findElement(By.id("duration-field")).sendKeys(expectedDuration);
        //Thread.sleep(2000);
        driver.findElement(By.id("save-button")).click();
      
		Thread.sleep(1000);
      
		List<WebElement> namesElements = driver.findElements(By.xpath("//table[@id='appointmentTypesTable']/tbody/tr/td[1]"));
		List<String> names = new ArrayList<String>();
		
		for(WebElement namesElement : namesElements) {
			names.add(namesElement.getText());
		}
		
		String nextButton = driver.findElement(By.id("appointmentTypesTable_next")).getAttribute("class");
		
		while(!nextButton.contains("disabled"))
		{
			driver.findElement(By.id("appointmentTypesTable_next")).click();
			namesElements = driver.findElements(By.xpath("//table[@id='appointmentTypesTable']/tbody/tr/td[1]"));
			
			for(WebElement namesElement : namesElements) {
				names.add(namesElement.getText());
			}
			nextButton = driver.findElement(By.id("appointmentTypesTable_next")).getAttribute("class");					
		}
		
		String actualName ="";
		for (String name : names)
		{
			System.out.println(name);
			if(name.equals(expectedName))
			{
				actualName = expectedName;
				System.out.println(actualName);
				break;
			}
			else
			{
				actualName= "";
			}			
		}		

		System.out.println("final val " + actualName);
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualName, expectedName);
		
		//String displaycount = driver.findElement(By.id("example_info")).getText().split(" ")[5];
	}

	
	@Test(description="Remove a new service type")
	public void TC_002_Remove_Service_Type() throws InterruptedException
	{
		launchBrowser("https://openmrs.org/demo/");

		driver.findElement(By.xpath("//span[contains(text(),'Enter the OpenMRS 2 Demo')]")).click();
		
		String expectedName ="MentalHealth51";
		
		Thread.sleep(2000);
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("password")).sendKeys("Admin123");
		Thread.sleep(2000);
		driver.findElement(By.id("Inpatient Ward")).click();
		driver.findElement(By.id("loginButton")).click();
	
		driver.findElement(By.id("appointmentschedulingui-homeAppLink-appointmentschedulingui-homeAppLink-extension")).click();
		driver.findElement(By.id("appointmentschedulingui-manageAppointmentTypes-app")).click();

		List<WebElement> namesElements = driver.findElements(By.xpath("//table[@id='appointmentTypesTable']/tbody/tr/td[1]"));
		List<String> names = new ArrayList<String>();
		
		boolean bdeleted = false;
		
		for(WebElement namesElement : namesElements) {
			names.add(namesElement.getText());
		}
		
		for (String name : names)
		{
			if(name.equals(expectedName) )
			{
				driver.findElement(By.xpath("//span/i[@id='appointmentschedulingui-delete-"+name+"']")).click();
				WebElement btnDelete =  driver.findElement(By.xpath("//div[@class='simplemodal-wrap']//button[@class='confirm right'][contains(text(),'Yes')]"));
				btnDelete.click();
				bdeleted = true;
				break;
			}				
		}	
		
		String nextButton = driver.findElement(By.id("appointmentTypesTable_next")).getAttribute("class");
		
		while(!nextButton.contains("disabled"))
		{
			driver.findElement(By.id("appointmentTypesTable_next")).click();
			namesElements = driver.findElements(By.xpath("//table[@id='appointmentTypesTable']/tbody/tr/td[1]"));
			
			for(WebElement namesElement : namesElements) {
				names.add(namesElement.getText());
			}
			
			for (String name : names)
			{
				if(name.equals(expectedName)&& bdeleted==false)
				{
					System.out.println("Yaju1 " + name);
					driver.findElement(By.xpath("//span/i[@id='appointmentschedulingui-delete-"+name+"']")).click();
					Thread.sleep(1000);
					driver.switchTo().activeElement();
					WebElement btnDelete =  driver.findElement(By.xpath("//div[@class='simplemodal-wrap']//button[@class='confirm right'][contains(text(),'Yes')]"));
					btnDelete.click();
					System.out.println("found2");
					bdeleted = true;					
					break;
				}				
			}	
			nextButton = driver.findElement(By.id("appointmentTypesTable_next")).getAttribute("class");					
		}
		
		String actualName ="";
		for (String name : names)
		{
			System.out.println(name);
			if(name.equals(expectedName) && bdeleted==false)
			{
				actualName = expectedName;
				driver.findElement(By.xpath("//span/i[@id='appointmentschedulingui-delete-"+name+"']")).click();
				WebElement btnDelete =  driver.findElement(By.xpath("//div[@class='simplemodal-wrap']//button[@class='confirm right'][contains(text(),'Yes')]"));
				btnDelete.click();
				System.out.println("found3");
				break;
			}
			else
			{
				actualName= "";
			}			
		}	
		
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualName, expectedName);
		
		//String displaycount = driver.findElement(By.id("example_info")).getText().split(" ")[5];
	}
}
