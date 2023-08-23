package org.iitwforce.automation.uclidmmp.uclidmmp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver;
	
	@BeforeMethod
	public void launchApplication() {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();	
		driver.manage().window().maximize();
	}
	
	@AfterMethod
	public void tearDown() throws InterruptedException {		
		Thread.sleep(5000);
		driver.close();
	}
	
	public void launchBrowser(String url) {
		driver.get(url);
	 }
}
