package Resource;


import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.refresh;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;



@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test4_DeployCRFTest {
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
	    public void ENV10_linkCrfVersionTest() throws InterruptedException, IOException {
	    	
	    	
	    	BufferedReader bf = new BufferedReader(new FileReader(".//target/file.txt"));
	    	String studyName = bf.readLine();
	    	
	    	String xpath = String.format("//*[text()='%s']//parent::div//parent::div//following-sibling::button[@type='button']", studyName);
	    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	    	d.findElement(By.xpath(xpath)).click();
	    	d.findElement(By.xpath("//*[@data-testid='sa-action_item']")).click();
	    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-testid='TStepper-research-teams']")));
	    	d.findElement(By.xpath("//*[@data-testid='TStepper-research-teams']")).click();
	    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='MuiTable-root MuiTable-stickyHeader']/tbody[1]")));
	    	d.findElement(By.xpath("//*[@class='MuiTable-root MuiTable-stickyHeader']/tbody[1]")).click();
	    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-testid='CTA_EDIT_RESEARCH_TEAM_FORM']")));
	    	d.findElement(By.xpath("//*[@data-testid='CTA_EDIT_RESEARCH_TEAM_FORM']")).click();
	    	d.findElement(By.xpath("//*[@data-testid='RT_FORM_CRF_VERSION']")).click();
	    	BufferedReader bf1 = new BufferedReader(new FileReader(".//Docs/Crf_Version.txt"));
	    	String crf = bf1.readLine();
	    	String crfXpath = String.format("//*[text()='%s']", crf);
	    	d.findElement(By.xpath(crfXpath)).click();
	    	d.findElement(By.xpath("//*[@data-testid='CTA_ADD_NEW_UPDATE_RT']")).click();
	    	
	    }
	    
	    @AfterAll
	    @Order(2)
	    public static void ReportingTest() throws InterruptedException {
		  	  Thread.sleep(6000);
		  	  Reader read = new Reader();
		  	  read.report();
		    }
	    
	  }
	   


