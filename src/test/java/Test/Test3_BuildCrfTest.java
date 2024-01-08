package Test;


import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.refresh;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;



@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test3_BuildCrfTest {
	static WebDriver d;
	static WebDriverWait wait;
	
	@BeforeAll
	
	
	    public static void browserTest() {
	        // Arranges
		System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver");
		
		d = new ChromeDriver();
		d.get("https://edc.devil.triomics.in/");
		wait = new WebDriverWait(d, Duration.ofSeconds(40));   
		d.findElement(By.xpath("//*[@id='dropdown-selectorganisation']")).click();
        
        d.findElement(By.xpath("//*[text()='DEV_Test']")).click();
        
        d.findElement(By.xpath("//*[@data-testid='cta-cognito-login']")).click();
        
        d.findElement(By.xpath("(//*[@name='username'])[2]")).sendKeys("jane.triomics@mailinator.com");
        d.findElement(By.xpath("(//*[@id='signInFormPassword'])[2]")).sendKeys("Hello@123");
        d.findElement(By.xpath("(//*[@value='Sign in'])[2]")).click();
	    }
	    
	
	    @Test
	    @Order(1)
	    public void ENV07_uploadCRFTest() throws InterruptedException, IOException {
	    	
	    	
	    	BufferedReader bf = new BufferedReader(new FileReader(".//target/file.txt"));
	    	String studyName = bf.readLine();
	    	
	    	String xpath = String.format("//*[text()='%s']//parent::div//parent::div//following-sibling::button[@type='button']", studyName);
	    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	    	d.findElement(By.xpath(xpath)).click();
	    	d.findElement(By.xpath("//*[@data-testid='sb-action_item']")).click();
	    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-testid='ADD_CRF_VERSION']")));
	    	d.findElement(By.xpath("//*[@data-testid='ADD_CRF_VERSION']")).click();
	    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Drag and drop to upload or Browse']")));
	    	File crf = new File(".//Docs/Mediflex_latest_CRF.xlsx");
	    	
	    	WebElement fileInput = d.findElement(By.xpath("//input[@type='file']"));
	    	
	    	fileInput.sendKeys(crf.getAbsolutePath());
	    	
	    	WebElement uploadBtn = d.findElement(By.xpath("//*[@data-testid='modalGenerator-submitCTA']"));
	    	if (uploadBtn.isDisplayed()) {
	    		uploadBtn.click();
	    	}
	    	
	    	
	    }
	    
	  	@Test
	    @Order(2)
        public void ENV08_selectCRFTest() throws InterruptedException, IOException {
	  		
	    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='MuiTable-root MuiTable-stickyHeader']")));
	    	
	    	String crfVersion = d.findElement(By.xpath("//*[@class='MuiTable-root MuiTable-stickyHeader']//tr[1]/td[1]")).getText();
	    	
	    	FileOutputStream FO = new FileOutputStream(new File(".//Docs/Crf_Version.txt"));
	    	byte[] b = crfVersion.getBytes();
	    	FO.write(b);
	    	d.findElement(By.xpath("//*[@class='MuiTable-root MuiTable-stickyHeader']//tr[1]/td[1]")).click();
	        
	    	
	    }
	    
	    @Test
	    @Order(3)
        public void ENV09_panelMappingTest() {
	    	
	    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-testid='TStepper-field-mapping']")));
	    	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Forms List']")));
	    	
	    	int row = d.findElements(By.xpath("//*[@class='MuiTable-root MuiTable-stickyHeader']/tbody/tr")).size();
	    	int col = d.findElements(By.xpath("//*[@class='MuiTable-root MuiTable-stickyHeader']/thead/tr/th")).size();
	    	
	    	for(int r=1;r<=row;r++) {
	    		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='MuiTable-root MuiTable-stickyHeader']/tbody/tr["+r+"]/td[4]")));
	    		String status = d.findElement(By.xpath("//*[@class='MuiTable-root MuiTable-stickyHeader']/tbody/tr["+r+"]/td[4]")).getText();
	    		System.out.println(status);
	    		if(status.equals("Review Needed")) {
	    			
	    			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='MuiTable-root MuiTable-stickyHeader']//tbody//tr["+r+"]/td[6]//child::button[1]")));
	    			d.findElement(By.xpath("//*[@class='MuiTable-root MuiTable-stickyHeader']//tbody//tr["+r+"]/td[6]//child::button[1]")).click();
	    			d.navigate().refresh();
	    			
	    			
	    		}
	    		
	    	}
	    	d.close();	
	    		
	   

}}
