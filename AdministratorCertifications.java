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


public class AdministratorCertifications {

	public static void main(String[] args) throws InterruptedException {
		
		/*
		 *  1. Launch Salesforce application https://login.salesforce.com/
			2. Login with username as "ramkumar.ramaiah@testleaf.com " and password as "Password$123"
			3. Click on Learn More link in Mobile Publisher
			4. Click confirm on Confirm redirect
			5. Click Resources and mouse hover on Learning On Trailhead
			6. Clilck on Salesforce Certifications
			6. Click on Ceritification Administrator
			7. Navigate to Certification - Administrator Overview window
			8. Verify the Certifications available for Administrator Certifications should be displayed
		 */
		
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
                Thread.sleep(1000);
                
                WebElement admin = driver.findElement(By.linkText("Administrator"));
                
                Thread.sleep(1000);
                builder.scrollToElement(admin).perform();
                builder.moveToElement(admin).click().perform();
                
				WebElement element = driver.findElement(By.xpath("//div[@class='trailMix-card-content']"));		
				
				Thread.sleep(1000);
                builder.scrollToElement(element).perform();
                
                List<WebElement> certifications = driver.findElements(By.xpath("//div[@class='trailMix-card-body']/div[2]/a"));
				
                int size = certifications.size();
                
                System.out.println("Number of certifications are " + size);
                
                for (WebElement webElement : certifications) {
                	
                	String text = webElement.getText();
        			
        			System.out.println(text);
					
				}
          driver.close();
	}

}
