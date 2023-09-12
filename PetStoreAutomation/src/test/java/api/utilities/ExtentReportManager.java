package api.utilities;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter; //create UI for our report
	public ExtentReports extent;  // for common details
	public ExtentTest test; //create detail entries for test 
	
	String repName;
	
	public void onStart(ITestContext testContext) {
		
		/*
		 * String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new
		 * Date()); repName="API test report"+timeStamp+".html";
		 */
		 
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd.HH.mm.ss");
		String timeStamp = dtf.format(LocalDateTime.now());
		repName="API-test-report"+timeStamp+".html";
		
		String currentDir = System.getProperty("user.dir");
		System.out.println(currentDir+"\\reports\\"+ repName);
		
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+ repName); //specify location of the report
		
		
		sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject"); //Title of report
		sparkReporter.config().setReportName("petStore Api test");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent= new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "pet store user API");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User", "Ameer Salman");
		
	}
	
	public void onTestSuccess(ITestResult testResult) {
		
		test=extent.createTest(testResult.getName());
		test.assignCategory(testResult.getMethod().getGroups());
		test.createNode(testResult.getName());
		test.log(Status.PASS, "Test Passed");
		
	}
	public void onTestFailure(ITestResult testResult) {
		
		test=extent.createTest(testResult.getName());
		test.assignCategory(testResult.getMethod().getGroups());
		test.createNode(testResult.getName());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL,testResult.getThrowable().getMessage() );
		
	}
	public void onTestSkipped(ITestResult testResult) {
		
		test=extent.createTest(testResult.getName());
		test.assignCategory(testResult.getMethod().getGroups());
		test.createNode(testResult.getName());
		test.log(Status.SKIP, "Test Skipped");
		test.log(Status.SKIP,testResult.getThrowable().getMessage() );
		
	}
	
	public void onFinish(ITestContext testContext) {
		extent.flush();
		//we have to call flush method, after finishing all above method. Then only report will generate, otherwise it won't 
	}
}


