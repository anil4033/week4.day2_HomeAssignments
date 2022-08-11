package week4.day2HomeAssignments;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapDeal {

	public static void main(String[] args) throws InterruptedException, IOException {
		/*
		 *  1. Launch https://www.snapdeal.com/
			2. Go to Mens Fashion
			3. Go to Sports Shoes
			4. Get the count of the sports shoes
			5. Click Training shoes
			6. Sort by Low to High
			7. Check if the items displayed are sorted correctly
			8.Select the price range (900-1200)
			9.Filter with color Navy 
			10 verify the all applied filters 
			11. Mouse Hover on first resulting Training shoes
			12. click QuickView button
			13. Print the cost and the discount percentage
			14. Take the snapshot of the shoes.
			15. Close the current window
			16. Close the main window
		 * 
		 */
		//To call WDM for driver
		WebDriverManager.chromedriver().setup();
									
		//Launch browser		
		ChromeDriver driver = new ChromeDriver();
							
		// Handling the browser Notifications
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--disable-notifications");
							
		//Load URL
		driver.get("https://www.snapdeal.com/");
						
		//Maximize browser
		driver.manage().window().maximize();
							
		//implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		Actions builder = new Actions(driver);
		
		Thread.sleep(4000);
		WebElement mensfashion = driver.findElement(By.xpath("(//a[@class='menuLinks leftCategoriesProduct '])[1]/span[2]"));
		
		Thread.sleep(1000);
		
		builder.moveToElement(mensfashion).perform();
		
		Thread.sleep(1000);
		WebElement shoes = driver.findElement(By.xpath("//span[text()='Sports Shoes']"));
		
		builder.moveToElement(shoes).click().perform();
		
		Thread.sleep(6000);
		//List<WebElement> shoelist = driver.findElements(By.xpath("//p[contains(text(),' Shoes')]"));
		//int size = shoelist.size();
		
		WebElement count = driver.findElement(By.xpath("//span[@class='category-name category-count']"));
		
		String text = count.getText();
		
		String replace = text.replace("( ", "");
		
		System.out.println(replace);
		
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//div[contains(text(),'Popularity')]")).click();
		
		Thread.sleep(1000);
		driver.findElement(By.xpath("//ul[@class='sort-value']/li[2]")).click();
		
		Thread.sleep(5000);
		
		List<WebElement> findElements = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));
		
	    List<String> str = new ArrayList<String>(); //crtl+shift+o to import
	    
		for (WebElement webElement : findElements) {
			Thread.sleep(2000);
			String text2 = webElement.getText();
			str.add(text2);
			
			}
		
		Collections.sort(str);
		System.out.println(str);
		
		WebElement price = driver.findElement(By.xpath("//div[@class='filter-type-name lfloat']"));
		builder.scrollToElement(price).perform();
		
		driver.findElement(By.xpath("//input[@name='fromVal']")).clear();
		driver.findElement(By.xpath("//input[@name='fromVal']")).sendKeys("900");
		
		driver.findElement(By.xpath("//input[@name='toVal']")).clear();
		driver.findElement(By.xpath("//input[@name='toVal']")).sendKeys("1200");
		driver.findElement(By.xpath("//div[contains(text(),'GO')]")).click();
		
		WebElement color = driver.findElement(By.xpath("(//div[@class='filter-type-name lfloat'])[3]"));
		builder.scrollToElement(color).perform();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[text()='View More ']")).click();
		
		driver.findElement(By.xpath("//label[@for='Color_s-Navy']")).click();
		
		List<WebElement> filters = driver.findElements(By.xpath("//div[@class='navFiltersPill']/a[@class='clear-filter-pill']"));
		
		System.out.println(filters.size());
		
		List<String> filter = new ArrayList<String>();
		
		System.out.println("Filters applied");
		
		for(WebElement fil : filters)
		{
			Thread.sleep(2000);
			String text3 = fil.getText();
		    filter.add(text3);
		}
		System.out.println(filter);
		
		Thread.sleep(2000);
		WebElement prodcutTitle = driver.findElement(By.xpath("(//p[@class='product-title'])[1]"));
		builder.scrollToElement(prodcutTitle).perform();
		
		WebElement img = driver.findElement(By.xpath("(//img[@class='product-image wooble'])[1]"));
		builder.moveToElement(img).perform();
		
		driver.findElement(By.xpath("//div[contains(text(),'Quick View')]")).click();
		
		String cost = driver.findElement(By.xpath("//span[@class='payBlkBig']")).getText();
		
		System.out.println("The cost is "+ cost);
		
		String percentage = driver.findElement(By.xpath("//span[@class='percent-desc ']")).getText();
		
		System.out.println("The percentage is "+ percentage);
		
		File source = driver.getScreenshotAs(OutputType.FILE);
		File dest = new File("snap1.png");
		FileUtils.copyFile(source, dest);
		
		driver.close();
   }
	
	
			
}
				
	


