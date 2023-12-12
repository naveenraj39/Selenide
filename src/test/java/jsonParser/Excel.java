package jsonParser;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {
	

	
	public static void main (String []args) {
	

try {
	XSSFWorkbook wb = new XSSFWorkbook();
	XSSFSheet sheet = wb.createSheet("Test Result");
	XSSFRow row = sheet.createRow(0);
	XSSFCell cell = row.createCell(0);
	cell.setCellValue("Test Case");
	XSSFCell cell1 = row.createCell(1);
	cell1.setCellValue("Status");
	FileOutputStream file = new FileOutputStream("./allure-results/Test.xlsx");
	try {
		wb.write(file);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	
	
	
} catch (FileNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}
}


