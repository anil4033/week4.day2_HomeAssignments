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
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class ArchitectCertifications {

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
		
		driver.switchTo().window(lstwindowHandles.get(1));
		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//button[text()='Confirm']")).click();		
		
		//driver.findElement(By.xpath("//a[text()='Resources']")).click(); 
		
		Thread.sleep(1000);
		
		Shadow dom = new Shadow(driver);
		
		Thread.sleep(3000);
						
		WebElement learn = dom.findElementByXPath("//span[text()='Learning']");
		
		learn.click();
		
		WebElement learning = dom.findElementByXPath("//span[text()='Learning on Trailhead']");					

		learning.click();
		
		WebElement certificate = dom.findElementByXPath("//a[text()='Salesforce Certification']");
		
        Actions builder = new Actions(driver);
        
        builder.scrollToElement(certificate).perform();
        builder.moveToElement(certificate).click().perform();
        
        driver.findElement(By.xpath("(//div[@class='roleMenu-item_text'])[2]")).click();
        
        String salesforceSummary = driver.findElement(By.xpath("//h1[text()='Salesforce Architect']/following-sibling::div")).getText();

        System.out.println("The summary is--" + salesforceSummary);
        
        List<WebElement> salescert = driver.findElements(By.xpath("//div[@class='credentials-card_title']"));
        
        int size = salescert.size();
        
        System.out.println("The Total Certifications available are" + size);
        
        for (WebElement sales : salescert) {
        	
        	String text = sales.getText();
			
        	System.out.println(text);
		}
        
        WebElement application = driver.findElement(By.linkText("Application Architect"));
        
        builder.scrollToElement(application).perform();
        builder.moveToElement(application).click().perform();
        
        List<WebElement> appcert = driver.findElements(By.xpath("//div[@class='credentials-card_title']"));
        
        System.out.println("Architect certifications");
		
		for(int i=0; i<4; i++)
		{
			System.out.println(appcert.get(i).getText());
		}
		
		driver.quit();
	}

}
