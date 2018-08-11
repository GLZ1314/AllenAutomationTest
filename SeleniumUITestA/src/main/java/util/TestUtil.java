package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/*
 * handles the functions(waits, actions, capturing screenshots, 
 * accessing excels, sending email)
 * 
 */
public class TestUtil {

	
	public  void readExcel()  {
		FileInputStream fis;
		try {
			fis = new FileInputStream("src/main/java/datatable/testDataTable.xlsx");
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0);
			Row row = sheet.getRow(1);
			Cell cell = row.getCell(1);
			System.out.println(cell);
			wb.close();
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		
		
	}
	
}
