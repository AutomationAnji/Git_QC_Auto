package tests;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

/*import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;*/

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import junit.framework.Assert;

public class ACHReturn extends QCStore {
	
	public static void ACHReturn (String SSN,String FileName,int Days) throws Exception
	{

		////Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/PDL_Regression_Prod/"+FileName);
		//Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/PDL/"+FileName);
		//Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/PDL_Regression_Prod/"+FileName);
		int lastrow = TestData.getLastRow("NewLoan");
		String sheetName = "NewLoan";

		for (int row = 2; row <= lastrow; row++) {
			String RegSSN = TestData.getCellData(sheetName, "SSN", row);
			if (SSN.equals(RegSSN)) {


				String AgeStore = TestData.getCellData(sheetName, "AgeStore", row);

				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3, 5);
				String SSN3 = SSN.substring(5, 9);
				String ProductID=TestData.getCellData(sheetName,"ProductID",row);
				String StoreID = TestData.getCellData(sheetName,"StoreID",row);

				Thread.sleep(4000);
				
				test.log(LogStatus.INFO, "Scheduler-Store Aging");			
				
				driver.switchTo().frame("topFrame");				
				
				driver.findElement(By.linkText("Loan Transactions")).click();
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);				
				driver.findElement(By.linkText("Transactions")).click();							
				test.log(LogStatus.PASS, "Clicked on Transactions");		
				driver.switchTo().frame("main");		
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");		
				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Click on GO Button");
				for(String winHandle : driver.getWindowHandles()){
					driver.switchTo().window(winHandle);
				}				    
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				

				if(ProductID.equals("ILP"))
					
				{
					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				}
				test.log(LogStatus.PASS, "Click on GO Button");
				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(By.name("transactionList")).sendKeys("History");
				
				if(ProductID.equals("ILP"))
				{
					driver.findElement(By.id("go_Button")).click();  
				}

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				String DueDate=null;
				String LoanNo;

				DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
				test.log(LogStatus.PASS, "Capture DueDate"+DueDate);
				
				LoanNo = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[4]/table/tbody/tr[4]/td/span[2]")).getText();
				test.log(LogStatus.PASS, "Capture Loan No:"+LoanNo);
				
				
				System.out.print(DueDate);	
				driver.close();

				driver = new InternetExplorerDriver();
				driver.get(BAdminURL);
                driver.manage().window().maximize();


				DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");	
				
				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("csr26");
				test.log(LogStatus.PASS, "Username is entered: admin");			        
				driver.findElement(By.name("loginRequestBean.password")).sendKeys("1234");
				test.log(LogStatus.PASS, "Password is entered:26 ");					  	        			   
				driver.findElement(By.name("login")).click();
				test.log(LogStatus.PASS, "Clicked on Submit button");
				Thread.sleep(2000);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				
				//driver.findElement(By.xpath("//*[@id='100000']/a")).click();
				
				driver.findElement(By.linkText("Transactions")).click();

				test.log(LogStatus.PASS, "Clicked on Transactions");

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				Thread.sleep(1000);
				
				//driver.findElement(By.xpath("//*[@id='101000']/a")).click();
				
				driver.findElement(By.linkText("ACH")).click();

				test.log(LogStatus.PASS, "Clicked on ACH");

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");
				Thread.sleep(3000);

				driver.findElement(By.linkText("Installment Loan")).click();

				test.log(LogStatus.PASS, "Clicked on Installment Loan");
				
				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");
				Thread.sleep(3000);

				driver.findElement(By.linkText("INSTALLMENT ACH RETURN")).click();

				test.log(LogStatus.PASS, "Clicked on Installment Loan");				

				for( String winHandle1 : driver.getWindowHandles())
				{
					driver.switchTo().window(winHandle1);
				}			
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");


				driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[1]/td[2]/input")).sendKeys("26");
				                             

				test.log(LogStatus.PASS, "Entered Store number "+StoreID);

				//Thread.sleep(1500);
				driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table[2]/tbody/tr[2]/td[2]/table[1]/tbody/tr[3]/td[2]/input")).sendKeys(LoanNo);

				test.log(LogStatus.PASS, "Enter Loan Number");
				
				driver.findElement(By.name("submit")).click();


				Thread.sleep(1000);


				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");			
				
				
				driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table[2]/tbody/tr[4]/td[1]/input[2]")).click();
				test.log(LogStatus.PASS, "Check The Chek box");
				
				driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table[2]/tbody/tr[4]/td[8]/select")).sendKeys("R01-Insufficient Funds");
				test.log(LogStatus.PASS, "Select The the Return Reason");
				
				driver.findElement(By.xpath("//*[@id='CmdReturnPosting']")).click();
				test.log(LogStatus.PASS, "Click on ACH Return");			
				
			
				driver.close();
				}
		}
		
	}
}