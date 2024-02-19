package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {
	
	
	WebDriver ldriver;
	public Login(WebDriver rdriver) {
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
		
		
	}
	
	@FindBy(xpath="//*[@id='dropdown-selectorganisation']")WebElement orgSelectDD;
	@FindBy(xpath="//*[text()='DEV_Test']")WebElement orgName;
	@FindBy(xpath="//*[@data-testid='cta-cognito-login']")WebElement cognitoLogin;
	@FindBy(xpath="(//*[@name='username'])[2]")WebElement username;
	@FindBy(xpath="(//*[@id='signInFormPassword'])[2]")WebElement password;
	@FindBy(xpath="(//*[@value='Sign in'])[2]")WebElement loginBtn;
	
	

	public void selectOrg() {
		orgSelectDD.click();
		orgName.click();
	}
	
	public void cognitoLogin() {
		cognitoLogin.click();
	}
	
	public void enterUserName(String user) {
		username.sendKeys(user);
	}
	
	public void enterPassword(String pass) {
		password.sendKeys(pass);
	}
	
	public void login() {
		loginBtn.click();
	}
	
}
