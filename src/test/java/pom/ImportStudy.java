package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ImportStudy {
	
	WebDriver ldriver;
	
	public ImportStudy(WebDriver rdriver) {
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
		
	}
	
	@FindBy(xpath="//*[@data-testid='import-from-ext-edc']")WebElement importBtn;
	@FindBy(xpath="//*[text()='Medidata Rave']")WebElement selectEDC;
	@FindBy(xpath="//*[@id='IMPORT_STUDY_NAME']")WebElement studyNameField;
	@FindBy(xpath="//*[@type='button']//child::span[text()='Go to Environments']")WebElement goToEnvironment;
	
	
	
public void clickonImportBtn() {
	importBtn.click();
}

public void selectEDC() {
	selectEDC.click();
}

public void enterStudyName(String studyName) {
	studyNameField.sendKeys(studyName);
}

public void goToEnv() {
	goToEnvironment.click();
}
	
}
