package Resource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;
import org.json.JSONObject;

public class Reader {
	
	
	static int jsonCount;
	static String read;
	static String name;
	static String status;
	
	public  void report()  {
		int rowNumber = 1;
		int testCell=0;
		int statusCell=1;
		
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sh = wb.createSheet("Test Cases");
		XSSFRow row1 = sh.createRow(0);
		XSSFCell cell2 = row1.createCell(0);
		cell2.setCellValue("Test Case Name");
		XSSFCell cell3 = row1.createCell(1);
		cell3.setCellValue("Status");
		XSSFCell cell4 = row1.createCell(1);
		cell4.setCellValue("Expected Output");
		XSSFCell cell5 = row1.createCell(1);
		cell5.setCellValue("Actual Output");
		try {
			
		File[] files =  new File("allure-results").listFiles((dir, name)->name.matches(".*-result.json"));
		
		if(files!=null && files.length>0) {
			for (File file:files) {
				
				readFile(file);
				
				JSONObject jb = new JSONObject(read);
				
				name = jb.getString("name");
				status = jb.getString("status");	
				
				XSSFRow row = sh.createRow(rowNumber);
				
				XSSFCell cell = row.createCell(testCell);
				cell.setCellValue(name);
				XSSFCell cell1 = row.createCell(statusCell);
				cell1.setCellValue(status);
				
				rowNumber++;
			
			}}}
		
		
		catch(Exception e ) {
			e.printStackTrace();
		}
		
		try {
			FileOutputStream fo = new FileOutputStream("./allure-results/Test Result.xlsx");
			
			wb.write(fo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void readFile(File file) {
		
		try {
			BufferedReader bf = new BufferedReader(new FileReader(file));
			
			read=bf.readLine();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}	
		
		
		
	}
	


	












		
		
	
