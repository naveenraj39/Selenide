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

import Resource.Reader;
import Resource.baseClass;
import pom.ImportStudy;
import pom.Login;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Map;



@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class Test1_ImportTest extends baseClass{
	

	static WebDriver d;
	static WebDriverWait wait;
	public String studyName;
	@BeforeAll
	
	
	    public static void browserTest() {
		
	        // Arranges
		System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver");
		ChromeOptions option = new ChromeOptions();
	//	option.addArguments("--headless=new");
		d = new ChromeDriver(option);
		login = new Login(d);
		IS=new ImportStudy(d);
		d.get("https://edc.devil.triomics.in/");
			
		wait = new WebDriverWait(d, Duration.ofSeconds(30));
		 	        
	    }
	    
	    
    @Test
    @Order(1)
    
    public void Select_Org_from_OrganisationList_Dropdown() throws Exception {
    	login.selectOrg();  
       
    }
    
    @Test
    @Order(2)
    public void Clickon_Cognito_Login_Button() {
    	login.cognitoLogin();
    }
    
    @Test
    @Order(3)
    public void Enter_Username_on_the_requiredFields() {
    	login.enterUserName("jane.triomics@mailinator.com");
    }
    
    @Test
    @Order(4)
    public void Enter_Password_on_the_requiredFields() {
    	login.enterPassword("Hello@123");
    }
    
    @Test
    @Order(5)
    public void Click_on_Login_Button() {
    	login.login();
    }
    
    @Test
    @Order(6)
    public void Click_onImport_Button() {
    	
    	wait.until(ExpectedConditions.elementToBeClickable
    			(By.xpath("//*[@data-testid='import-from-ext-edc']")));
    	IS.clickonImportBtn();
    }
    
    @Test
    @Order(7)
    public void Select_EDC_by_Clicking() {
    	wait.until(ExpectedConditions.elementToBeClickable
    			(By.xpath("//*[text()='Medidata Rave']")));
    	IS.selectEDC();
    	
    	
    }
    
    @Test
    @Order(8)
    public void Enter_Study_Name() throws Exception {
    	String timeStamp = new SimpleDateFormat(" yyyy-MM-dd 'at' HH:mm:ss").format(new Date());
    	String studyName = "Regression Test" + timeStamp;
    	IS.enterStudyName(studyName);
    	FileOutputStream FO = new FileOutputStream(".//target/file.txt");
  	  byte[] b = studyName.getBytes();
  	  FO.write(b);
    }
    
    @Test
    @Order(9)
    public void Clickon_GoTo_Environment_Button() {
    	IS.goToEnv();
    }
    
    
   
    
    @Test
    @Order(10)
    public void ENV03_createDevEnvironmentTest() {
    	
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='FORM_ENVIRONMENT_TYPE']")));
    	d.findElement(By.xpath("//*[@id='FORM_ENVIRONMENT_TYPE']")).click();
    	d.findElement(By.xpath("//*[text()='DEV']")).click();
    	d.findElement(By.xpath("//*[@id='FORM_ENVIRONMENT_NAME']")).sendKeys("Dev_Env");
    	d.findElement(By.xpath("//*[@data-testid='CTA_CREATE_ENVIRONMENT']")).click();
    }
    
    @Test
    @Order(11)
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
    	
    	
    	
    }
    
    @Test
    @Order(12)
    public void ENV05_createDevEnvRTteamTest() throws InterruptedException, IOException {
    	
    	
    
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
    @Order(13)
    public void ENV06_createTrainEnvRTteamTest() {
    	
    
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-testid='TStepper-study-environments']")));
	d.findElement(By.xpath("//*[@data-testid='TStepper-study-environments']")).click();
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-testid='TStepper-research-teams']")));
	d.findElement(By.xpath("//*[@data-testid='TStepper-research-teams']")).click();
	d.navigate().refresh();
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Train_Env']")));
    d.findElement(By.xpath("//*[text()='Train_Env']")).click();
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Triomics Healthcare Private Limited']")));
    d.findElement(By.xpath("//*[text()='Triomics Healthcare Private Limited']")).click();
    d.findElement(By.xpath("//*[@data-testid='CTA_EDIT_RT_CREDENTIAL']")).click();
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@placeholder='Username']")));
    d.findElement(By.xpath("//*[@placeholder='Username']")).sendKeys("ynaruka");
    d.findElement(By.xpath("//*[@type='password']")).sendKeys("Triomics####123");
    d.findElement(By.xpath("//*[@data-testid='modalGenerator-submitCTA']")).click();
   
    
    }
    
    @Test
    @Order(14)
    public void ENV07_uploadCRFTest() throws InterruptedException, IOException {
    	
    	
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-testid='logoutModalActionClick']")));
    	d.findElement(By.xpath("//*[@data-testid='logoutModalActionClick']")).click();
    	
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
    @Order(15)
    public void ENV08_selectCRFTest() throws InterruptedException, IOException {
  		
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='MuiTable-root MuiTable-stickyHeader']")));
    	
    	String crfVersion = d.findElement(By.xpath("//*[@class='MuiTable-root MuiTable-stickyHeader']//tr[1]/td[1]")).getText();
    	
    	FileOutputStream FO = new FileOutputStream(new File(".//Docs/Crf_Version.txt"));
    	byte[] b = crfVersion.getBytes();
    	FO.write(b);
    	d.findElement(By.xpath("//*[@class='MuiTable-root MuiTable-stickyHeader']//tr[1]/td[1]")).click();
        
    	
    }
    
    @Test
    @Order(16)
    public void ENV09_panelMappingTest() {
    	
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-testid='TStepper-field-mapping']")));
    	
    	
    	int row = d.findElements(By.xpath("//*[@class='MuiTable-root MuiTable-stickyHeader']/tbody/tr")).size();
    	
    	
    	for(int r=1;r<=row;r++) {
    		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='MuiTable-root MuiTable-stickyHeader']/tbody/tr["+r+"]/td[4]")));
    		String status = d.findElement(By.xpath("//*[@class='MuiTable-root MuiTable-stickyHeader']/tbody/tr["+r+"]/td[4]")).getText();
    		
    		if(status.equals("Review Needed")) {
    			
    			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='MuiTable-root MuiTable-stickyHeader']//tbody//tr["+r+"]/td[6]//child::button[1]")));
    			d.findElement(By.xpath("//*[@class='MuiTable-root MuiTable-stickyHeader']//tbody//tr["+r+"]/td[6]//child::button[1]")).click();
    			String status1 = d.findElement(By.xpath("//*[@class='MuiTable-root MuiTable-stickyHeader']/tbody/tr["+r+"]/td[4]")).getText();
    		    if (status1.equals("Saved")) {
    			d.navigate().refresh();
    			
    		    }
    		}
    		
    	}


    }
    
    @Test
    @Order(17)
    public void ENV10_linkCrfVersionTest() throws InterruptedException, IOException {
		
   	
   	d.findElement(By.xpath("//*[@data-testid='logoutModalActionClick']")).click();
    	
    	
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
    
    @Test
    @Order(18)
    public void ENV10_addParticipantTest() throws IOException, InterruptedException {
    	
    	d.findElement(By.xpath("//*[@data-testid='logoutModalActionClick']")).click();
    	BufferedReader bf = new BufferedReader(new FileReader(".//target/file.txt"));
    	String studyName = bf.readLine();
    	
    	String xpath = String.format("//*[text()='%s']//parent::div//parent::div//following-sibling::button[@type='button']", studyName);
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    	d.findElement(By.xpath(xpath)).click();
    	d.findElement(By.xpath("//*[@data-testid='sm-action_item']")).click();
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Import MRN']")));
    	d.findElement(By.xpath("//*[text()='Import MRN']")).click();
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ENTER_MRN']")));
    	d.findElement(By.xpath("//*[@id='ENTER_MRN']")).sendKeys("12519");
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-testid='fetchParticipantCTA']")));
    	d.findElement(By.xpath("//*[@data-testid='fetchParticipantCTA']")).click();
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-testid='importParticipantCTA']")));
    	d.findElement(By.xpath("//*[@data-testid='importParticipantCTA']")).click();
    	Thread.sleep(5000);
    //	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-testid='modalGenerator-cancelCTA']")));
    	d.findElement(By.xpath("//*[@data-testid='modalGenerator-cancelCTA']")).click();
    	
    	
    	
    }
    
    
    @AfterAll
    
    public static void ReportingTest() throws InterruptedException {
	  	  Thread.sleep(6000);
	  	  Reader read = new Reader();
	  	  read.report();
	    }
    
    

}
    
   

