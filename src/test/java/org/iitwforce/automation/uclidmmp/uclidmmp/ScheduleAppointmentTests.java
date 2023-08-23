package org.iitwforce.automation.uclidmmp.uclidmmp;

import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ScheduleAppointmentTests extends BaseClass{

	@Test(description="Validate the schedule appointmnet for a doctor")
	public void TC_006_ScheduleBooking_Date()
	{
		launchBrowser("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");
		driver.findElement(By.id("username")).sendKeys("ria1");
		driver.findElement(By.id("password")).sendKeys("Ria12345");
		driver.findElement(By.name("submit")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Schedule Appointment')]")).click();
		driver.findElement(By.cssSelector("input[value='Create new appointment']")).click();
		driver.findElement(By.xpath("//h4[contains(.,'Charlie')]/ancestor::ul/following-sibling::button")).click();
				
		driver.switchTo().frame("myframe");
		
		Calendar now = Calendar.getInstance();		
		now.add(Calendar.DATE, +14);
		String AppointmentDate =(now.get(Calendar.MONTH) + 1) + "/" + now.get(Calendar.DATE) + "/" + now.get(Calendar.YEAR);
		String AppointmentSymType = "Fever and Cough";
		String AppointmentTime ="11Am";
		String AppointmentDocName="Charlie";
		
		//String expectedDate = "08/20/2023";
		driver.findElement(By.id("datepicker")).sendKeys(AppointmentDate);
		
		WebElement timeWE = driver.findElement(By.id("time"));
		
	//	JavascriptExecutor js = (JavascriptExecutor) driver;
	//	js.executeScript("arguments[0].removeAttribute('disabled','disabled')",timeWE);
		
		Select timeselect = new Select(timeWE);
		timeselect.selectByValue(AppointmentTime);
		
		driver.findElement(By.id("ChangeHeatName")).click();
		driver.switchTo().defaultContent();
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement symWE = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sym")));
		symWE.sendKeys("Fever and Cough");
		driver.findElement(By.cssSelector("input[value='Submit']")).click();
		
		String actualDate = driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[1]")).getText();
		String Time = driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[2]")).getText();
		String SymType = driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[3]")).getText();
		String DocName = driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[4]")).getText();
		
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualDate, AppointmentDate);
		sa.assertEquals(Time, AppointmentTime);
		sa.assertEquals(SymType, AppointmentSymType);
		sa.assertEquals(DocName, AppointmentDocName);
		sa.assertAll();		
	}
}
