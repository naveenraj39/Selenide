package Test;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.triomics.framework.TmxHubrisWreckerConfigLoader;
import com.triomics.framework.common.AppConfigurations;

import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.open;


import java.io.*;
import java.util.Properties;

@TestInstance(Lifecycle.PER_CLASS)
 @Log4j2
public class BaseTest{
   
  @BeforeAll
    public void beforeAll() throws IOException {
        Properties config = new Properties();
    	FileInputStream file = new FileInputStream (".//config/config.properties");
    	config.load(file);
    	String driver = config.getProperty("chromeDriver");
    	System.setProperty("webdriver.chrome.driver", driver);
    	
 //  	configLoader.loadSelenideConfig();
  // 	String baseURL =(String) TmxHubrisWreckerConfigLoader.configurations.get(AppConfigurations.SELENIDE_BASEURL);
    //	log.debug("Got baseURI as {}",baseURL);
    	
    	open("https://harmony.qa.triomics.in/login");
    }
    
    TmxHubrisWreckerConfigLoader configLoader=new TmxHubrisWreckerConfigLoader();
}