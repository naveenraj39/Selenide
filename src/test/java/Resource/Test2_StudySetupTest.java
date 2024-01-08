package Resource;


import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.refresh;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;



@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test2_StudySetupTest {
	static WebDriver d;
	static WebDriverWait wait;
	@BeforeAll
	
	
	    public static void browserTest() {
	        // Arranges
		System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver");
		
		d = new ChromeDriver();
		d.get("https://edc.devil.triomics.in/");
		wait = new WebDriverWait(d, Duration.ofSeconds(30));   
		d.findElement(By.xpath("//*[@id='dropdown-selectorganisation']")).click();
		
        
        d.findElement(By.xpath("//*[text()='DEV_Test']")).click();
        
        d.findElement(By.xpath("//*[@data-testid='cta-cognito-login']")).click();
        
        d.findElement(By.xpath("(//*[@name='username'])[2]")).sendKeys("jane.triomics@mailinator.com");
        d.findElement(By.xpath("(//*[@id='signInFormPassword'])[2]")).sendKeys("Hello@123");
        d.findElement(By.xpath("(//*[@value='Sign in'])[2]")).click();
	    }
	    
	
	    @Test
	    @Order(1)
	    public void ENV05_createDevEnvRTteamTest() throws InterruptedException, IOException {
	    	
	    	
	    	BufferedReader bf = new BufferedReader(new FileReader(".//target/file.txt"));
	    	String studyName = bf.readLine();
	    	
	    	String xpath = String.format("//*[text()='%s']//parent::div//parent::div//following-sibling::button[@type='button']", studyName);
	    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	    	d.findElement(By.xpath(xpath)).click();
	    	d.findElement(By.xpath("//*[@data-testid='sa-action_item']")).click();
	    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-testid='TStepper-research-teams']")));
	    	d.findElement(By.xpath("//*[@data-testid='TStepper-research-teams']")).click();
	    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Dev_Env']")));
	    	d.findElement(By.xpath("//*[text()='Dev_Env']")).click();
	        d.findElement(By.xpath("//*[@data-testid='CTA_ADD_NEW_RESEARCH_TEAM']")).click();
	        d.findElement(By.xpath("//*[@placeholder='Write the name of the PI']")).sendKeys("PI");
	        d.findElement(By.xpath("//*[@data-testid='CTA_ADD_MEMBER']")).click();
	        d.findElement(By.xpath("//*[@id='MEMBER_NAME']")).click();
	        d.findElement(By.xpath("//*[text()='Jane Doe']")).click();
	        d.findElement(By.xpath("//*[@id='MEMBER_ROLE']")).click();
	        d.findElement(By.xpath("//*[text()='Primary Investigator']")).click();
	        d.findElement(By.xpath("//*[@data-testid='CTA_ADD_NEW_UPDATE_RT']")).click();
	        
	    	
	    }
	    
	    @Test
	    @Order(2)
	    public void ENV06_createTrainEnvRTteamTest() throws InterruptedException {
	    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Train_Env']")));
	        d.findElement(By.xpath("//*[text()='Train_Env']")).click();
	        d.findElement(By.xpath("//*[@data-testid='TABLE_ROW_0']")).click();
	        d.findElement(By.xpath("//*[@data-testid='CTA_EDIT_RT_CREDENTIAL']")).click();
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@placeholder='Username']")));
	        d.findElement(By.xpath("//*[@placeholder='Username']")).sendKeys("ynaruka");
	        d.findElement(By.xpath("//*[@type='password']")).sendKeys("Triomics####123");
	        d.findElement(By.xpath("//*[@data-testid='modalGenerator-submitCTA']")).click();
	        
	        Thread.sleep(5000);
	        d.close();
	    	
	    }
	    

}
