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

public class Defaultdate extends QCStore {
	public static void Defaultdate(String SSN,String FileName,int Days) throws Exception
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

				//DueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
				
				DueDate=driver.findElement(By.xpath("//*[@id='ContractScheduleTable']/tbody/tr[3]/td[2]")).getText();
				 

				test.log(LogStatus.PASS, "Capture DueDate"+DueDate);
				System.out.print(DueDate);	
				driver.close();

				driver = new InternetExplorerDriver();
				driver.get(BAdminURL);
                driver.manage().window().maximize();


				DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");	
				
				driver.findElement(By.name("loginRequestBean.userId")).sendKeys("hanuman");
				test.log(LogStatus.PASS, "Username is entered: admin");			        
				driver.findElement(By.name("loginRequestBean.password")).sendKeys("C0ns0l3");
				test.log(LogStatus.PASS, "Password is entered: "+Password);					  	        			   
				driver.findElement(By.name("login")).click();
				test.log(LogStatus.PASS, "Clicked on Submit button");
				Thread.sleep(2000);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("topFrame");
				
				driver.findElement(By.xpath("//*[@id='100000']/a")).click();

				test.log(LogStatus.PASS, "Clicked on Store tab");

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@id='101000']/a")).click();

				test.log(LogStatus.PASS, "Clicked on Store Config link");

				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");
				Thread.sleep(3000);

				driver.findElement(By.xpath("//*[@id='101020']/a")).click();

				test.log(LogStatus.PASS, "Clicked on Store Edit");

				Actions action = new Actions(driver);
				action.moveByOffset(200,100).perform();
				Thread.sleep(2000);
				action.click();

				driver.switchTo().frame("main");


				driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table/tbody/tr[2]/td[1]/table/tbody/tr[1]/td/table/tbody/tr[1]/td/b/input")).sendKeys(StoreID);

				test.log(LogStatus.PASS, "Entered Store number "+StoreID);

				//Thread.sleep(1500);
				driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table/tbody/tr[2]/td[1]/table/tbody/tr[1]/td/table/tbody/tr[3]/td/input[1]")).click();

				test.log(LogStatus.PASS, "Clicked on Submit button");


				Thread.sleep(1000);


				driver.switchTo().defaultContent();

				driver.switchTo().frame("mainFrame");

				driver.switchTo().frame("main");			
				
				String business_unit=driver.findElement(By.name("locationBean.businessUnitCd")).getAttribute("value");
				
				String DDueDate[] =DueDate.split("/");

				Date DDueDateminus1 = df.parse(DueDate);

				Calendar cal = Calendar.getInstance();

				cal.setTime(DDueDateminus1);

				cal.add(Calendar.DATE, Days);

				Date DDueDate1= cal.getTime();

				DueDate =df.format(DDueDate1);

				String DueDate0[] =DueDate.split("/");

				String DueDate1 = DueDate0[0];

				String DueDate2 = DueDate0[1];

				String DueDate3 = DueDate0[2];				
			
				Thread.sleep(2000);
				driver.findElement(By.name("procDt1")).clear();
				driver.findElement(By.name("procDt1")).sendKeys(DueDate1); 
				test.log(LogStatus.PASS, "beginMonth is entered: "+DueDate1);
				driver.findElement(By.name("procDt2")).clear();
				driver.findElement(By.name("procDt2")).sendKeys(DueDate2);
				test.log(LogStatus.PASS, "beginDay is entered: "+DueDate2);
				driver.findElement(By.name("procDt3")).clear();
				driver.findElement(By.name("procDt3")).sendKeys(DueDate3);
				test.log(LogStatus.PASS, "beginYear is entered: "+DueDate3);
				
				Thread.sleep(1000);
				//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table[2]/tbody/tr/td/input[3]")).click();
				
				driver.findElement(By.name("submitButton")).click();

				test.log(LogStatus.PASS, "Clicked on Submit"); 
				Thread.sleep(2000);

				try { 
					Alert alert = driver.switchTo().alert();
					alert.accept();
					test.log(LogStatus.PASS, "Handled the alert"); 
					//if alert present, accept and move on.														

				}
				catch (NoAlertPresentException e) {
					//do what you normally would if you didn't have the alert.
				}
				try{
					if(business_unit.equalsIgnoreCase("1"))
					{
						Thread.sleep(2000);

						driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table[1]/tbody/tr[4]/td/table[1]/tbody/tr[2]/td[1]/select")).sendKeys("2");
					}
					else
					{
						Thread.sleep(2000);

						driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table[1]/tbody/tr[4]/td/table[1]/tbody/tr[2]/td[1]/select")).sendKeys("1");
					}
					test.log(LogStatus.PASS, "Changed business unit"); 
					Thread.sleep(1000);
					driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table[2]/tbody/tr/td/input[3]")).click();

					test.log(LogStatus.PASS, "Clicked on Submit"); 
				}
				catch(Exception e)
				{

				}

				test.log(LogStatus.PASS, "Store Setup is successful");
				test.log(LogStatus.PASS, "***********************************************");	

				}
		}
		
	}
}