package week4.day2HomeAssignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class Customer_Service_Options {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//To call WDM for driver
		WebDriverManager.chromedriver().setup();
				
		//Launch browser		
		ChromeDriver driver = new ChromeDriver();
		
		// Handling the browser Notifications
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--disable-notifications");
		
		//Load URL
		driver.get("https://login.salesforce.com");
		
		//Maximize browser
		driver.manage().window().maximize();
		
		//implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//Login Application
		driver.findElement(By.id("username")).sendKeys("ramkumar.ramaiah@testleaf.com",Keys.TAB);
		driver.findElement(By.id("password")).sendKeys("Password#123");
		driver.findElement(By.id("Login")).click();
		
		driver.findElement(By.xpath("//span[text()='Learn More']")).click();
		
		//Switch to the next window using Windowhandles.
		//String windowHandle = driver.getWindowHandle();
		
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> lstwindowHandles = new ArrayList<String>(windowHandles);
		Thread.sleep(2000);
		driver.switchTo().window(lstwindowHandles.get(1));
		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//button[text()='Confirm']")).click();
		
		Thread.sleep(1000);
		
		Shadow dom = new Shadow(driver);
		
		Thread.sleep(3000);
		
		dom.findElementByXPath("//span[text()='Products']").click();
		
		/*WebElement element = dom.findElementByXPath("//button[@class='hgf-button']");
		
		Thread.sleep(3000);
		
		Actions builder =new Actions(driver);
		
		builder.moveToElement(element).click().perform();*/
		

		dom.findElementByXPath("//span[text()='Service']").click();

		dom.findElementByXPath("//a[text()='Customer Service']").click();
		Thread.sleep(3000);
		
		List<WebElement> services = driver.findElements(By.xpath("//ul[@class='page-list page-list-level-2']/li/a"));

		System.out.println("The number of services available are" + services.size());
		
		for (WebElement webElement : services) {
			
			String text = webElement.getText();
			
			System.out.println(text);
			
		}
		
		Thread.sleep(2000);
		String titleDriver = driver.getTitle();
		
		System.out.println(titleDriver);	
		
		if(titleDriver.contentEquals("Customer Service Tools from Service Cloud - Salesforce.com")) {
			System.out.println("Title is verified correct");
		}else {
			System.out.println("Title is not verified correct");
		}
		
       driver.close();
	}

}
