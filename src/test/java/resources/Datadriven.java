package resources;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class Datadriven {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
       
		FileInputStream fis = new FileInputStream("C:\\Users\\Hp\\OneDrive\\Desktop\\POI_Practice");
		HSSFWorkbook wb = new HSSFWorkbook(fis);
		
//		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		int sheetcount = wb.getNumberOfSheets();
		
		for(int i=0;i<sheetcount;i++)
		{
			if(wb.getSheetName(i).equals("Testdata"))
			{
				 HSSFSheet sheet = wb.getSheetAt(i);
			}
			
		}
		

	}

}
