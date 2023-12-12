package jsonParser;

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

public class JsonReader {
	
	

	
public static void main(String[]args) throws IOException {
	String fileName = null;
	
	File path = new File ("./allure-results");
	
	File[] file = path.listFiles((dir, name)->name.matches(".*-result.json"));
	
	//StringBuilder sb = new StringBuilder();
	
	if(file!=null && file.length>0) {
		for(File files:file) {
			readFile(files);
			
		//	System.out.println(files.getName());
		}
	}
	
}
private static void readFile(File file) {		
	try(Scanner bf = new Scanner(new FileReader(file))){
//	StringBuilder sb =new StringBuilder();
	String line = null;
	while (bf.hasNextLine()) {
//	System.out.println(br);
	
		line = bf.nextLine();
		 
		try {
			JSONObject jb = new JSONObject(line);
			
			if(jb!=null) {
				String name = jb.getString("name");
				String status = jb.getString("status");
				
				System.out.println(name);
				
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
//	System.out.println(c1);
	
	 
	}catch(FileNotFoundException e) {
		e.printStackTrace();
	}

}}
