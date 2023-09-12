package api.utilities;

import java.io.*;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.*;


public class XLUtility {

	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workBook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	String path;
	
	public XLUtility(String xlPath) {
		this.path=xlPath;
	}
	
	public int getRowCount(String sheetName) throws IOException{
		
		fi= new FileInputStream(path);
		workBook = new XSSFWorkbook(fi);
		sheet = workBook.getSheet(sheetName);
		int rowCount= sheet.getLastRowNum();
		fi.close();
		workBook.close();
		return rowCount;
	}
	
	public int getCellCount(String sheetName, int rowNum) throws IOException{
		
		fi= new FileInputStream(path);
		workBook = new XSSFWorkbook(fi);
		sheet = workBook.getSheet(sheetName);
		row= sheet.getRow(rowNum);
		int cellCount= row.getLastCellNum();
		fi.close();
		workBook.close();
		return cellCount;
	}
	
	public String getCellData(String sheetName, int rowNum, int cellNum) throws IOException {
		
		fi= new FileInputStream(path);
		workBook = new XSSFWorkbook(fi);
		sheet = workBook.getSheet(sheetName);
		row= sheet.getRow(rowNum);
		cell =row.getCell(cellNum);
		String data;
		DataFormatter formate = new DataFormatter();
		
		try {
			data = formate.formatCellValue(cell); 
		}catch(Exception e) {
			e.printStackTrace();
			data="";
		}
		fi.close();
		workBook.close();
		
		return data;
	}
}

