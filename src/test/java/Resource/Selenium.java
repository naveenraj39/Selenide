package Resource;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Selenium {
	
	public static void main (String args[]) throws Exception {
		baseClass.webDriver();
		
		WebDriver driver = baseClass.get();
		
		driver.get("https://harmony.qa.triomics.in/");
		driver.findElement(By.xpath("//*[@id='dropdown-selectorganisation']")).click();
		driver.findElement(By.xpath("//*[@id='dropdown-selectorganisation-option-26']")).click();
		driver.findElement(By.xpath("//*[@data-testid='cta-cognito-login']")).click();
		Thread.sleep(6000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@name='username'])[2]")));
		driver.findElement(By.xpath("(//*[@name='username'])[2]")).sendKeys("Hello@123");
		driver.findElement(By.xpath("(//*[@id='signInFormPassword'])[2]")).sendKeys("Hello@123");
	}

}
