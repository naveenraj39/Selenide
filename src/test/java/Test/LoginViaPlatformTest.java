package Test;




import Test.BaseTest;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.title;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;

import Resource.Reader;
import Resource.baseClass;


public class LoginViaPlatformTest extends BaseTest{
    @Test
 
    public void login() {

    	if(
        title().equalsIgnoreCase("Harmony")) {
  //      $(".MuiTypography-root.MuiTypography-subtitle1").shouldHave(text("Select Organization")).exists();
    	
        System.out.println("Pass");}
    	else {
    		System.out.println("Fail");
    	}
    }
  @Test
  
    
    public void TRIO_R30_login_as_platform_admin() throws InterruptedException {
        $(byXpath("//*[@id='dropdown-selectorganisation']")).click();
        
  }
  @Test 
  
     public void TRIO_R31_Select_Org() {

        $(byXpath("//*[@id='dropdown-selectorganisation-option-26']")).click();
        
  }
  @Test
  public void cognito_login() {
        $(byXpath("//*[@data-testid='cta-cognito-login']")).click();
        System.out.println("Pass");
    }

  @Test
  
  public void enterCreds() throws Exception {
	  Thread.sleep(6000);
	  $(byXpath("(//*[@name='username'])[2]")).sendKeys("siteadm.qa@mailinator.com");
	  $(byXpath("(//*[@id='signInFormPassword'])[2]")).sendKeys("Hello@123");
	  $(byXpath("(//*[@value='Sign in'])[2]")).click();
  }

  @AfterAll
  
  public void Reporting() throws InterruptedException {
	  Thread.sleep(6000);
	  Reader read = new Reader();
	  read.report();
  }
  

 /*   protected void waitUntilPagesIsLoaded() {
        $(byText("Sign in with your username and password")).should(appear, Duration.ofSeconds(2));
    }
*/
}
