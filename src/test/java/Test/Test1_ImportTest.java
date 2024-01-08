package Test;





import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import com.codeborne.selenide.WebDriverRunner;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**s
 * Simple Selenide Test with PageObjects
 */ 

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class Test1_ImportTest {
	

	static WebDriver d;
	static WebDriverWait wait;
	public String studyName;
	@BeforeAll
	
	
	    public static void browserTest() {
		
	        // Arranges
		System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver");
		
		d = new ChromeDriver();
		d.get("https://edc.devil.triomics.in/");
		
		wait = new WebDriverWait(d, Duration.ofSeconds(30));
		 
	        
	    }
	    
	    
    @Test
    @Order(1)
    
    public void ENV01_loginTest() throws Exception {
    	
    

       d.findElement(By.xpath("//*[@id='dropdown-selectorganisation']")).click();
       
       
       d.findElement(By.xpath("//*[text()='DEV_Test']")).click();
       
       d.findElement(By.xpath("//*[@data-testid='cta-cognito-login']")).click();
       
       d.findElement(By.xpath("(//*[@name='username'])[2]")).sendKeys("jane.triomics@mailinator.com");
       d.findElement(By.xpath("(//*[@id='signInFormPassword'])[2]")).sendKeys("Hello@123");
       d.findElement(By.xpath("(//*[@value='Sign in'])[2]")).click();
 	   
 	  
       
    }
    
    @Test
    @Order(2)
    public void ENV02_importStudyTest() throws IOException {
    	
    	
    	String timeStamp = new SimpleDateFormat(" yyyy-mm-dd HH:mm:ss").format(new Date());
    	
    	
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-testid='import-from-ext-edc']")));
    	
    	d.findElement(By.xpath("//*[@data-testid='import-from-ext-edc']")).click();
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Medidata Rave']")));
    	d.findElement(By.xpath("//*[text()='Medidata Rave']")).click();
    	this.studyName = "DEV_Test_Study" + timeStamp;
    	
    	d.findElement(By.xpath("//*[@id='IMPORT_STUDY_NAME']")).sendKeys(studyName);
    	
    	d.findElement(By.xpath("//*[@type='button']//child::span[text()='Go to Environments']")).click();
        
    	
   // 	$(By.xpath("//*[@data-testid='import-from-ext-edc']")).click();
    	
    	  FileOutputStream FO = new FileOutputStream(".//target/file.txt");
    	  byte[] b = studyName.getBytes();
    	  FO.write(b);
    	 
    }
    
    
    @Test
    @Order(3)
    public void ENV03_createDevEnvironmentTest() {
    	
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='FORM_ENVIRONMENT_TYPE']")));
    	d.findElement(By.xpath("//*[@id='FORM_ENVIRONMENT_TYPE']")).click();
    	d.findElement(By.xpath("//*[text()='DEV']")).click();
    	d.findElement(By.xpath("//*[@id='FORM_ENVIRONMENT_NAME']")).sendKeys("Dev_Env");
    	d.findElement(By.xpath("//*[@data-testid='CTA_CREATE_ENVIRONMENT']")).click();
    }
    
    @Test
    @Order(4)
    public void ENV04_createTrainEnvironmentTest() throws InterruptedException {
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-testid='OPEN_ENVIRONMENT_CREATE_FORM']")));
    	d.findElement(By.xpath("//*[@data-testid='OPEN_ENVIRONMENT_CREATE_FORM']")).click();
    	
    	d.findElement(By.xpath("//*[@id='FORM_ENVIRONMENT_TYPE']")).click();
    	d.findElement(By.xpath("//*[@data-testid='FORM_ENVIRONMENT_TYPE-TRAIN']")).click();
    	d.findElement(By.xpath("//*[@id='FORM_ENVIRONMENT_NAME']")).sendKeys("Train_Env");
    	
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='EXTERNAL_EDC_URL']")));
    	
    	d.findElement(By.xpath("//*[@id='EXTERNAL_EDC_URL']")).sendKeys("https://innovate.mdsol.com");
    	
    	d.findElement(By.xpath("//*[@id='EXTERNAL_EDC_USERNAME']")).sendKeys("ynaruka");
    	d.findElement(By.xpath("//*[@type='password']")).sendKeys("Triomics####123");
    	d.findElement(By.xpath("//*[@data-testid='CTA_CREATE_ENVIRONMENT']")).click();
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-testid='SELECT_REMOTE_EDCMediflex']")));
    	d.findElement(By.xpath("//*[@data-testid='SELECT_REMOTE_EDCMediflex']")).click();
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Train_Env']")));
    	d.close();
    	
    }
    
   
   
    
    }
    
   

