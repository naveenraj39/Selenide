package Test;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.triomics.framework.TmxHubrisWreckerConfigLoader;
import com.triomics.framework.common.AppConfigurations;

import Resource.baseClass;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.open;


import java.io.*;
import java.util.Properties;

@TestInstance(Lifecycle.PER_CLASS)
 @Log4j2

public class BaseTest {
   


  @BeforeAll
    public void beforeAll() throws IOException {
	 
	  WebDriverManager.chromedriver().browserVersion("120.0.6099.71").setup();
  	
 // 	configLoader.loadSelenideConfig();
  //	String baseURL =(String) TmxHubrisWreckerConfigLoader.configurations.get(AppConfigurations.SELENIDE_BASEURL);
  	open("https://harmony.qa.triomics.in/");
    }
    
   TmxHubrisWreckerConfigLoader configLoader=new TmxHubrisWreckerConfigLoader();
}
