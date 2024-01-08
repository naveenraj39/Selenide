package Resource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.codeborne.selenide.commands.TakeScreenshot;



public class TestWatcher {
	
WebDriver driver;
public void onTestFail() {
	
	takeScreenshot();
}

	
public byte[] takeScreenshot()  {
	
	TakesScreenshot tk = (TakesScreenshot) driver;
	return tk.getScreenshotAs(OutputType.BYTES);
	
	
	
	
	
	
}}
