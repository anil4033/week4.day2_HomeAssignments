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
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		/*1) Go to https://www.nykaa.com/
			2) Mouseover on Brands and Search L'Oreal Paris
			3) Click L'Oreal Paris
			4) Check the title contains L'Oreal Paris(Hint-GetTitle)
			5) Click sort By and select customer top rated
			6) Click Category and click Hair->Click haircare->Shampoo
			7) Click->Concern->Color Protection
			8)check whether the Filter is applied with Shampoo
			9) Click on L'Oreal Paris Colour Protect Shampoo
			10) GO to the new window and select size as 175ml
			11) Print the MRP of the product
			12) Click on ADD to BAG
			13) Go to Shopping Bag 
			14) Print the Grand Total amount
			15) Click Proceed
			16) Click on Continue as Guest
			17) Check if this grand total is the same in step 14
			18) Close all windows*/
		
	    //To call WDM for driver
		WebDriverManager.chromedriver().setup();
							
		//Launch browser		
	    ChromeDriver driver = new ChromeDriver();
					
		// Handling the browser Notifications
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--disable-notifications");
					
		//Load URL
		driver.get("https://www.nykaa.com/");
					
		//Maximize browser
		driver.manage().window().maximize();
					
		//implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//Mouseover on Brands and Search L'Oreal Paris
		Actions builder = new Actions(driver);
		
		WebElement brand = driver.findElement(By.xpath("//a[text()='brands']"));
		
		builder.moveToElement(brand).perform();
		
		//Mouseover on Brands and Search L'Oreal Paris
		driver.findElement(By.id("brandSearchBox")).click();
		
		driver.findElement(By.id("brandSearchBox")).sendKeys("L'Oreal Paris",Keys.ENTER);
		
		Thread.sleep(2000);
		//Click L'Oreal Paris
		driver.findElement(By.xpath("//div[@class='css-ov2o3v']/a")).click();
		
		//Check the title contains L'Oreal Paris(Hint-GetTitle)
		String title = driver.getTitle();
		
		if(title.contains("L'Oreal Paris")) {
			System.out.println("The title contains L'Oreal Paris");
		}else {
			System.out.println("the title do not contains L'Oreal Paris");
		}
		
		//Click sort By and select customer top rated
		driver.findElement(By.xpath("//button[@class=' css-n0ptfk']")).click();
		
		driver.findElement(By.xpath("(//div[@class='control-indicator radio '])[3]")).click();
		
		//Click Category and click Hair->Click haircare->Shampoo
		driver.findElement(By.xpath("//span[text()='Category']")).click();
		
		driver.findElement(By.xpath("//span[text()='Hair']")).click();
		
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class='control-indicator checkbox ']")).click();
		
		Thread.sleep(2000);
		//Click->Concern->Color Protection
		WebElement concern = driver.findElement(By.xpath("//span[text()='Concern']"));
		
		builder.scrollToElement(concern).perform();
		
		//driver.findElement(By.xpath("(//div[@class='control-indicator checkbox '])[12]")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='control-value']/span[text()='Color Protection']/following::div")).click();
		
		Thread.sleep(2000);
		
		//check whether the Filter is applied with Shampoo
		WebElement filter = driver.findElement(By.xpath("//span[text()='Shampoo']"));
		
		String text = filter.getText();
		
		if(text.contains("Shampoo")) {
			System.out.println("The Filter is applied with Shampoo");
		}else {
			System.out.println("The Filter is not applied with Shampoo");
		}
		
		Thread.sleep(1000);
		//Click on L'Oreal Paris Colour Protect Shampoo
		driver.findElement(By.xpath("//div[@class='css-43m2vm']/img")).click();
		
		//GO to the new window and select size as 175ml
		Set<String> windowHandles = driver.getWindowHandles();
		
		List<String> winhan = new ArrayList<String>(windowHandles);
		
		driver.switchTo().window(winhan.get(1));
		
		WebElement dropdown = driver.findElement(By.xpath("//select[@class='css-2t5nwu']"));
		
		Select select = new Select(dropdown);
		
		Thread.sleep(1000);
		
		select.selectByVisibleText("175ml");
		
		//Print the MRP of the product
		WebElement mrp = driver.findElement(By.xpath("//span[@class='css-1jczs19']"));
		
		String mrpvalue = mrp.getText();
		
		mrpvalue.replace("?", "");
		
		System.out.println(mrpvalue);
		
		//Click on ADD to BAG
		driver.findElement(By.xpath("(//button[@class=' css-12z4fj0'])[1]")).click();
		
		Thread.sleep(2000);
		
		//Go to Shopping Bag
		driver.findElement(By.xpath("//button[@class='css-g4vs13']")).click();
		
		Thread.sleep(5000);
		
		//Print the Grand Total amount
		driver.switchTo().frame(0);
		
		WebElement total = driver.findElement(By.xpath("//div[@class='name medium-strong']/following::div"));
		
		String grandtotal = total.getText();
		
		//grandtotal.replace("?", "");
		
		System.out.println("The Grand Total is "+ grandtotal);
		
		// Click Proceed
		driver.findElement(By.xpath("//button[@class='btn full fill no-radius proceed ']")).click();
		
		Thread.sleep(2000);
		
		//Click on Continue as Guest
		driver.findElement(By.xpath("//button[text()='CONTINUE AS GUEST']")).click();
		
		//Check if this grand total is the same in step 14
		WebElement gtotal = driver.findElement(By.xpath("(//div[@class='value'])[2]"));
		
		String text2 = gtotal.getText();
		
		System.out.println(text2);
		
		if(text2.contentEquals(grandtotal)) {
			System.out.println("Grand total is same");
		}else
		{
			System.out.println("Grand total is not same");
		}
		//Close all windows
		driver.quit();
	}

}
