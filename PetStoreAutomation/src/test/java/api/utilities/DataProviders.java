package api.utilities;


import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="Data")
	public String [][] getAllData() throws IOException {
		
		String path= System.getProperty("user.dir")+"//testData//UserData.xlsx";
		XLUtility xl= new XLUtility(path);
		int row = xl.getRowCount("Sheet1");
		int cell = xl.getCellCount("Sheet1",1);
		
		String allData[][]= new String[row][cell];
		
		for(int i=1; i<=row;i++) {
			for(int j=0;j<cell;j++) {
				allData[i-1][j]= xl.getCellData("Sheet1", i, j);
			}
		}
		System.out.println("get all data xl excecuted successfully");
		return allData;
	}
	@DataProvider(name="UserName")
	public String [] getUserName() throws IOException {
		
		String path= System.getProperty("user.dir")+"//testData//UserData.xlsx";
		XLUtility xl= new XLUtility(path);
		int row = xl.getRowCount("Sheet1");
		String []usernames= new String[row];
		
		for(int i=1; i<=row;i++) {
				usernames[i-1] = xl.getCellData("Sheet1", i, 1);
		}
		System.out.println("get User Name xl excecuted successfully");
		return usernames;
	}
}
