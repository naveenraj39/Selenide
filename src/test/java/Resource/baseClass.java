package Resource;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class baseClass {
	
	private static ThreadLocal<WebDriver> tdriver = new ThreadLocal<>();
	
    public static void webDriver() {
	 
	 if(tdriver.get()==null) {
	 WebDriverManager.chromedriver().browserVersion("120.0.6099.71").setup();
	 
	 WebDriver d = new ChromeDriver();
	 tdriver.set(d);
	 
	 }
    }
	 
	 public static WebDriver get() {
		 return tdriver.get();
	 }
	

	
	 
	 
	 
 }

	 
	
	 
	 
	


