package Test;




import Test.BaseTest;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.title;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;

import Resource.Reader;


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
        $(byXpath("//*[@title='Clear']")).click();
        $(byXpath("//*[@id='dropdown-selectorganisation']")).sendKeys("Test_Org_1");
        $(byXpath("//*[@title='Close']")).click();
        $(byXpath("//*[@data-testid='cta-cognito-login']")).click();
        System.out.println("Pass");
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
