/*package tests;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


import com.relevantcodes.extentreports.LogStatus;

public class RAdminStoreDate extends QCStore
{
	static String startdate;

	public static void toStartdate(String SSN,String AppURL) throws InterruptedException, ParseException
	{

				String StoreID ="31";                                                       
				if(Rprop.getProperty("QC_Store_NewLoan_Test_data_sheet_path").equalsIgnoreCase("/BTestData/QCStore/ILP_BiWeekly/"))
				{
					startdate="12/07/2018";
				}
				
				
					startdate="12/07/2018";
				
				
				//String Days=TestData.getCellData(sheetName,"Days",2);	
				int GraceDaysInt=0;
				
				Thread.sleep(5000);
				driver.switchTo().defaultContent();

				driver.switchTo().frame("topFrame");

				Thread.sleep(1000);
				
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


				driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table[1]/tbody/tr[4]/td/table[1]/tbody/tr[3]/td[1]/select")).sendKeys("Open");

				test.log(LogStatus.PASS, "Store status set as Open"); 
				String business_unit=driver.findElement(By.name("locationBean.businessUnitCd")).getAttribute("value");
					
				
				
		//=======================================================================			
				DateFormat df=new SimpleDateFormat("MM/dd/yyyy");
				//String storedate[] =Demodate.split("/");
				
				Date DDueDateminus1 = df.parse(startdate);

				Calendar cal = Calendar.getInstance();

				cal.setTime(DDueDateminus1);

				cal.add(Calendar.DATE, GraceDaysInt);
			

				Date DDueDate1= cal.getTime();

				String DueDate =df.format(DDueDate1);

				String storedate1[] =DueDate.split("/");

				
				 Date1 = storedate1[0];

				 Date2 = storedate1[1];

				 Date3 = storedate1[2];
				
			WebElement	e1=driver.findElement(By.name("procDt1"));
			e1.clear();
			e1.sendKeys(Date1);
			test.log(LogStatus.PASS, "Enterd Month as"+Date1); 
			WebElement	e2=driver.findElement(By.name("procDt2"));
			e2.clear();
			e2.sendKeys(Date2);
			test.log(LogStatus.PASS, "Enterd date as"+Date2);
			WebElement	e3=driver.findElement(By.name("procDt3"));
			e3.clear();
			e3.sendKeys(Date3);
			test.log(LogStatus.PASS, "Enterd year as"+Date3);
	//=============================================================			
				Thread.sleep(1000);
				driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table[2]/tbody/tr/td/input[3]")).click();

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

*/


package tests;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
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


public class RAdminStoreDate extends QCStore{
	//private static String NextDueDate;

	public static void ageStoreGraceDays(String SSN,String AppURL){
		int i;
		for(i=0;i<4;i++)
		{
			
		 
		try{
			//String FileName= Rprop.getProperty("QC_Store_NewLoan_file_name");
			
			//ExcelNew TestData = new ExcelNew(System.getProperty("user.dir")+Rprop.getProperty("QC_Store_NewLoan_Test_data_sheet_path")+FileName+".xls");  		 
			test.log(LogStatus.INFO,"*************** Admin Login To Age The Store Date *********************************");
			int lastrow=TestData.getLastRow("NewLoan");
				String sheetName="NewLoan";

					for(int row=2;row<=lastrow;row++)
							{		
								String RegSSN = TestData.getCellData(sheetName,"SSN",row);
								if(SSN.equals(RegSSN))
								{
							
								//String PIN = TestData.getCellData(sheetName,"PIN",row);
						       // System.out.println(Password);
						        String StoreID = TestData.getCellData(sheetName,"StoreID",row);
						        //String ProductID = TestData.getCellData(sheetName,"ProductID",row);
						
						       String AgeStore = TestData.getCellData(sheetName,"AgeStore",row);
						      
						
									  
								        //String SSN1 = SSN.substring(0, 3);
								        //String SSN2 = SSN.substring(3,5);
								        //String SSN3 = SSN.substring(5,9);
							   //==============================================================
						       Thread.sleep(5000);
								driver.switchTo().defaultContent();

								driver.switchTo().frame("topFrame");

								Thread.sleep(1000);
								
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

								Thread.sleep(500);
								driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table/tbody/tr[2]/td[1]/table/tbody/tr[1]/td/table/tbody/tr[3]/td/input[1]")).click();
								                             
								test.log(LogStatus.PASS, "Clicked on Submit button");


								Thread.sleep(1000);


								driver.switchTo().defaultContent();

								driver.switchTo().frame("mainFrame");

								driver.switchTo().frame("main");


								driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table[1]/tbody/tr[4]/td/table[1]/tbody/tr[3]/td[1]/select")).sendKeys("Open");

								test.log(LogStatus.PASS, "Store status set as Open"); 
								String business_unit=driver.findElement(By.name("locationBean.businessUnitCd")).getAttribute("value");
							//========================================
								int IAgeStore=Integer.parseInt(AgeStore);
						    	 
								 DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");
									Date DDueDateminus1 = df.parse(business_date);
									Calendar cal = Calendar.getInstance();
									 cal.setTime(DDueDateminus1);
									 cal.add(Calendar.DATE, IAgeStore);
									 Date DDueDate1= cal.getTime();
									 business_date =df.format(DDueDate1);
									 System.out.println(business_date);
								     
								      test.log(LogStatus.PASS, "Store Date after Aging is :"+business_date);
						//============================================
						      //================================================================
							
						       
												 driver.switchTo().defaultContent();
												 driver.switchTo().frame("mainFrame");
												 driver.switchTo().frame("main");
												String Due_Date[] =business_date.split("/");
										        String Due_Date1 = Due_Date[0];
										        String Due_Date2 = Due_Date[1];
										        String Due_Date3 = Due_Date[2];
										        /*driver.findElement(By.name("procDt1")).clear();
										        driver.findElement(By.name("procDt1")).sendKeys(Due_Date1);
										        test.log(LogStatus.PASS, "Month is entered: "+Due_Date1);
										        driver.findElement(By.name("procDt2")).clear();
												driver.findElement(By.name("procDt2")).sendKeys(Due_Date2);
												test.log(LogStatus.PASS, "Date is entered: "+Due_Date2);
												driver.findElement(By.name("procDt3")).sendKeys(Due_Date3);
												test.log(LogStatus.PASS, "Year is entered: "+Due_Date3);*/
												
												//=================
												WebElement	e1=driver.findElement(By.name("procDt1"));
												e1.clear();
												e1.sendKeys(Due_Date1);
												test.log(LogStatus.PASS, "Enterd Month as"+Date1); 
												WebElement	e2=driver.findElement(By.name("procDt2"));
												e2.clear();
												e2.sendKeys(Due_Date2);
												test.log(LogStatus.PASS, "Enterd date as"+Date2);
												WebElement	e3=driver.findElement(By.name("procDt3"));
												e3.clear();
												e3.sendKeys(Due_Date3);
												test.log(LogStatus.PASS, "Enterd year as"+Date3);
											   //================
												
												
												Thread.sleep(500);
												driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table[2]/tbody/tr/td/input[3]")).click();
												//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table[2]/tbody/tr/td/input[3]")).click();
												test.log(LogStatus.PASS, "Clicked on Submit Button");
												Thread.sleep(1000);
												 //String alert1=   driver.switchTo().alert().getText();
												 //test.log(LogStatus.PASS, "Clicked on Finish Loan: "+alert1);
												 
												 try { 
													    Alert alert = driver.switchTo().alert();
													
													    alert.accept();
													    //if alert present, accept and move on.														
														
													}
													catch (Exception e) {
													    //do what you normally would if you didn't have the alert.
													}
												 Thread.sleep(10000);
												
												 wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Submit")));
												 Thread.sleep(5000);
												 driver.findElement(By.name("Submit")).click();
												 //test.log(LogStatus.PASS, "Clicked on Scheduler Ok");
												// test.log(LogStatus.PASS, MarkupHelper.createLabel("Clicked on Scheduler Ok Successfully",ExtentColor.GREEN));
												 test.log(LogStatus.PASS,"Clicked on  Ok Successfully");
												 test.log(LogStatus.PASS,"Store Date Has been changed through Admin Portal Successfully To :" +business_date);
												 test.log(LogStatus.PASS,"************************************************");

												 Thread.sleep(5000);
												 //driver.close();
							
							break;
						 
					}
				}
			

		break; //for FOR loop		
		}
				catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					//test.log(LogStatus.FAIL, MarkupHelper.createLabel("Borrower Registration is failed", ExtentColor.RED));
					//test.log(LogStatus.FAIL, "Age store is failed");
					test.log(LogStatus.FAIL, " "+e);
					test.log(LogStatus.INFO, "Age store Grace days process is initiated again due to Application sync issue");
					continue;


				}

	}
		if(i==3)
		{
			test.log(LogStatus.FAIL, "Age store Grace days is failed");
	
		}
	}
	
	public static void ageStoreToMonthEndDate(String SSN,String AppURL){
		int i;
		for(i=0;i<4;i++)
		{
			
		 
		try{
			//String FileName= Rprop.getProperty("QC_Store_NewLoan_file_name");
			
			//ExcelNew TestData = new ExcelNew(System.getProperty("user.dir")+Rprop.getProperty("QC_Store_NewLoan_Test_data_sheet_path")+FileName+".xls");  		 
			test.log(LogStatus.INFO,"*************** Admin Login To Age The Store Date *********************************");
			int lastrow=TestData.getLastRow("NewLoan");
				String sheetName="NewLoan";

					for(int row=2;row<=lastrow;row++)
							{		
								String RegSSN = TestData.getCellData(sheetName,"SSN",row);
								if(SSN.equals(RegSSN))
								{
							
								//String PIN = TestData.getCellData(sheetName,"PIN",row);
						       // System.out.println(Password);
						        String StoreID = TestData.getCellData(sheetName,"StoreID",row);
						        //String ProductID = TestData.getCellData(sheetName,"ProductID",row);
						
						       String AgeStore = TestData.getCellData(sheetName,"AgeStore",row);
						      
						
									  
								        //String SSN1 = SSN.substring(0, 3);
								        //String SSN2 = SSN.substring(3,5);
								        //String SSN3 = SSN.substring(5,9);
							   //==============================================================
						       Thread.sleep(5000);
								driver.switchTo().defaultContent();

								driver.switchTo().frame("topFrame");

								Thread.sleep(1000);
								
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

								Thread.sleep(500);
								driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table/tbody/tr[2]/td[1]/table/tbody/tr[1]/td/table/tbody/tr[3]/td/input[1]")).click();
								                             
								test.log(LogStatus.PASS, "Clicked on Submit button");


								Thread.sleep(1000);


								driver.switchTo().defaultContent();

								driver.switchTo().frame("mainFrame");

								driver.switchTo().frame("main");


								driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table[1]/tbody/tr[4]/td/table[1]/tbody/tr[3]/td[1]/select")).sendKeys("Open");

								test.log(LogStatus.PASS, "Store status set as Open"); 
								String business_unit=driver.findElement(By.name("locationBean.businessUnitCd")).getAttribute("value");
							//========================================
							
								
								//String date = "05/13/2020";
								LocalDate convertedDate = LocalDate.parse(business_date, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
								convertedDate = convertedDate.withDayOfMonth(
								convertedDate.getMonth().length(convertedDate.isLeapYear()));
								System.out.println("Month Last Day is : " + convertedDate);				 
								String WriteoffDate = convertedDate.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));	 
								System.out.println("Writeoff Date is: " + WriteoffDate);	
						//============================================
						      //================================================================
							
						       
												 driver.switchTo().defaultContent();
												 driver.switchTo().frame("mainFrame");
												 driver.switchTo().frame("main");
												String Due_Date[] =WriteoffDate.split("-");
										        String Due_Date1 = Due_Date[0];
										        String Due_Date2 = Due_Date[1];
										        String Due_Date3 = Due_Date[2];
										      
												//=================
												WebElement	e1=driver.findElement(By.name("procDt1"));
												e1.clear();
												e1.sendKeys(Due_Date1);
												test.log(LogStatus.PASS, "Enterd Month as"+Date1); 
												WebElement	e2=driver.findElement(By.name("procDt2"));
												e2.clear();
												e2.sendKeys(Due_Date2);
												test.log(LogStatus.PASS, "Enterd date as"+Date2);
												WebElement	e3=driver.findElement(By.name("procDt3"));
												e3.clear();
												e3.sendKeys(Due_Date3);
												test.log(LogStatus.PASS, "Enterd year as"+Date3);
											   //================
												
												
												Thread.sleep(500);
												driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table[2]/tbody/tr/td/input[3]")).click();
												//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table[2]/tbody/tr/td/input[3]")).click();
												test.log(LogStatus.PASS, "Clicked on Submit Button");
												Thread.sleep(1000);
												 												 
												 try { 
													    Alert alert = driver.switchTo().alert();
													
													    alert.accept();
													    //if alert present, accept and move on.														
														
													}
													catch (Exception e) {
													    //do what you normally would if you didn't have the alert.
													}
												 Thread.sleep(10000);
												
												 wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Submit")));
												 Thread.sleep(5000);
												 driver.findElement(By.name("Submit")).click();
												 //test.log(LogStatus.PASS, "Clicked on Scheduler Ok");
												// test.log(LogStatus.PASS, MarkupHelper.createLabel("Clicked on Scheduler Ok Successfully",ExtentColor.GREEN));
												 test.log(LogStatus.PASS,"Clicked on  Ok Successfully");
												 test.log(LogStatus.PASS,"Store Date Has been changed through Admin Portal Successfully To Month End Date :" +WriteoffDate);
												 test.log(LogStatus.PASS,"************************************************");

												 Thread.sleep(5000);
												 //driver.close();
							
							break;
						 
					}
				}
			

		break; //for FOR loop		
		}
				catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					//test.log(LogStatus.FAIL, MarkupHelper.createLabel("Borrower Registration is failed", ExtentColor.RED));
					//test.log(LogStatus.FAIL, "Age store is failed");
					test.log(LogStatus.FAIL, " "+e);
					test.log(LogStatus.INFO, "Age store Grace days process is initiated again due to Application sync issue");
					continue;


				}

	}
		if(i==3)
		{
			test.log(LogStatus.FAIL, "Age store Grace days is failed");
	
		}
	}
	
	
	
	public static void ageStoreDueDate(String SSN,String AppURL){
		int i;
		for(i=0;i<4;i++)
		{
			
		 
		try{
			//String FileName= Rprop.getProperty("QC_Store_NewLoan_file_name");
			
			//ExcelNew TestData = new ExcelNew(System.getProperty("user.dir")+Rprop.getProperty("QC_Store_NewLoan_Test_data_sheet_path")+FileName+".xls");  		 
			test.log(LogStatus.INFO,"*************** Admin Login To Age The Store To DueDate *********************************");
			int lastrow=TestData.getLastRow("NewLoan");
				String sheetName="NewLoan";

					for(int row=2;row<=lastrow;row++)
							{		
								String RegSSN = TestData.getCellData(sheetName,"SSN",row);
								if(SSN.equals(RegSSN))
								{
							
								//String PIN = TestData.getCellData(sheetName,"PIN",row);
						       // System.out.println(Password);
						        String StoreID = TestData.getCellData(sheetName,"StoreID",row);
						        //String ProductID = TestData.getCellData(sheetName,"ProductID",row);
						
						       String AgeStore = TestData.getCellData(sheetName,"AgeStore",row);
						      
						
									  
								        //String SSN1 = SSN.substring(0, 3);
								        //String SSN2 = SSN.substring(3,5);
								        //String SSN3 = SSN.substring(5,9);
							   //==============================================================
						       Thread.sleep(5000);
								driver.switchTo().defaultContent();

								driver.switchTo().frame("topFrame");

								Thread.sleep(1000);
								
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

								Thread.sleep(500);
								driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table/tbody/tr[2]/td[1]/table/tbody/tr[1]/td/table/tbody/tr[3]/td/input[1]")).click();
								                             
								test.log(LogStatus.PASS, "Clicked on Submit button");


								Thread.sleep(1000);


								driver.switchTo().defaultContent();

								driver.switchTo().frame("mainFrame");

								driver.switchTo().frame("main");


								driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table[1]/tbody/tr[4]/td/table[1]/tbody/tr[3]/td[1]/select")).sendKeys("Open");

								test.log(LogStatus.PASS, "Store status set as Open"); 
								String business_unit=driver.findElement(By.name("locationBean.businessUnitCd")).getAttribute("value");
							
						      //================================================================
							
						       
												 driver.switchTo().defaultContent();
												 driver.switchTo().frame("mainFrame");
												 driver.switchTo().frame("main");
												
												 
												 String Due_Date[] =NextDueDate.split("/");
											        String Due_Date1 = Due_Date[0];
											        String Due_Date2 = Due_Date[1];
											        String Due_Date3 = Due_Date[2];
											        WebElement	e1=driver.findElement(By.name("procDt1"));
													e1.clear();
													e1.sendKeys(Due_Date1);									        
											        test.log(LogStatus.PASS, "Month is entered: "+Due_Date1);
											        
											        WebElement	e2=driver.findElement(By.name("procDt2"));
													e2.clear();
													e2.sendKeys(Due_Date2);													
													test.log(LogStatus.PASS, "Date is entered: "+Due_Date2);
													
													WebElement	e3=driver.findElement(By.name("procDt3"));
													e3.clear();
													e3.sendKeys(Due_Date3);													
													test.log(LogStatus.PASS, "Year is entered: "+Due_Date3);
												
												
												
												
												Thread.sleep(500);
												driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table[2]/tbody/tr/td/input[3]")).click();
												//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table[2]/tbody/tr/td/input[3]")).click();
												test.log(LogStatus.PASS, "Clicked on Submit Button");
												Thread.sleep(1000);
												 //String alert1=   driver.switchTo().alert().getText();
												 //test.log(LogStatus.PASS, "Clicked on Finish Loan: "+alert1);
												 
												 try { 
													    Alert alert = driver.switchTo().alert();
													
													    alert.accept();
													    //if alert present, accept and move on.														
														
													}
													catch (Exception e) {
													    //do what you normally would if you didn't have the alert.
													}
												 Thread.sleep(10000);
												
												 wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Submit")));
												 Thread.sleep(5000);
												 driver.findElement(By.name("Submit")).click();
												 //test.log(LogStatus.PASS, "Clicked on Scheduler Ok");
												// test.log(LogStatus.PASS, MarkupHelper.createLabel("Clicked on Scheduler Ok Successfully",ExtentColor.GREEN));
												 test.log(LogStatus.PASS,"Clicked on  Ok Successfully");
												 test.log(LogStatus.PASS,"Store Date Has been changed to Due Date through Admin Portal Successfully To :" +NextDueDate);
												 test.log(LogStatus.PASS,"************************************************");

												 Thread.sleep(5000);
												 //driver.close();
							
							break;
						 
					}
				}
			

		break; //for FOR loop		
		}
				catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					//test.log(LogStatus.FAIL, MarkupHelper.createLabel("Borrower Registration is failed", ExtentColor.RED));
					//test.log(LogStatus.FAIL, "Age store is failed");
					test.log(LogStatus.FAIL, " "+e);
					test.log(LogStatus.INFO, "Age store Grace days process is initiated again due to Application sync issue");
					continue;


				}

	}
		if(i==3)
		{
			test.log(LogStatus.FAIL, "Age store Grace days is failed");
	
		}
	}
	
	
	
	
	/*public static void ageStoreToDefault(String SSN,String AppURL){
		int i;
		for(i=0;i<4;i++)
		{
			
		 
		try{
			//String FileName= Rprop.getProperty("QC_Store_NewLoan_file_name");
			
			//ExcelNew TestData = new ExcelNew(System.getProperty("user.dir")+Rprop.getProperty("QC_Store_NewLoan_Test_data_sheet_path")+FileName+".xls");  		 
			test.log(LogStatus.INFO,"*************** Admin Login To Age The Store Date *********************************");
			int lastrow=TestData.getLastRow("NewLoan");
				String sheetName="NewLoan";

					for(int row=2;row<=lastrow;row++)
							{		
								String RegSSN = TestData.getCellData(sheetName,"SSN",row);
								if(SSN.equals(RegSSN))
								{
							
								//String PIN = TestData.getCellData(sheetName,"PIN",row);
						       // System.out.println(Password);
						        String StoreID = TestData.getCellData(sheetName,"StoreID",row);
						        //String ProductID = TestData.getCellData(sheetName,"ProductID",row);
						
						       //String AgeStore = TestData.getCellData(sheetName,"AgeStore",row);
						      
						
									  
								        //String SSN1 = SSN.substring(0, 3);
								        //String SSN2 = SSN.substring(3,5);
								        //String SSN3 = SSN.substring(5,9);
							   //==============================================================
						       Thread.sleep(5000);
								driver.switchTo().defaultContent();

								driver.switchTo().frame("topFrame");

								Thread.sleep(1000);
								
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

								Thread.sleep(500);
								driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form/table/tbody/tr[2]/td[1]/table/tbody/tr[1]/td/table/tbody/tr[3]/td/input[1]")).click();
								                             
								test.log(LogStatus.PASS, "Clicked on Submit button");


								Thread.sleep(1000);


								driver.switchTo().defaultContent();

								driver.switchTo().frame("mainFrame");

								driver.switchTo().frame("main");


								driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table[1]/tbody/tr[4]/td/table[1]/tbody/tr[3]/td[1]/select")).sendKeys("Open");

								test.log(LogStatus.PASS, "Store status set as Open"); 
								String business_unit=driver.findElement(By.name("locationBean.businessUnitCd")).getAttribute("value");
							//========================================
								int IAgeStore=Integer.parseInt(AgeStore);
						    	 
								 DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");
									Date DDueDateminus1 = df.parse(business_date);
									Calendar cal = Calendar.getInstance();
									 cal.setTime(DDueDateminus1);
									 cal.add(Calendar.DATE, IAgeStore);
									 Date DDueDate1= cal.getTime();
									 business_date =df.format(DDueDate1);
									 System.out.println(business_date);
								     
								      test.log(LogStatus.PASS, "Store Date after Aging is :"+business_date);
						//============================================
						      //================================================================
							
						       
												 driver.switchTo().defaultContent();
												 driver.switchTo().frame("mainFrame");
												 driver.switchTo().frame("main");
												String Due_Date[] =DefaultDate.split("/");
										        String Due_Date1 = Due_Date[0];
										        String Due_Date2 = Due_Date[1];
										        String Due_Date3 = Due_Date[2];
										        driver.findElement(By.name("procDt1")).clear();
										        driver.findElement(By.name("procDt1")).sendKeys(Due_Date1);
										        test.log(LogStatus.PASS, "Month is entered: "+Due_Date1);
										        driver.findElement(By.name("procDt2")).clear();
												driver.findElement(By.name("procDt2")).sendKeys(Due_Date2);
												test.log(LogStatus.PASS, "Date is entered: "+Due_Date2);
												driver.findElement(By.name("procDt3")).sendKeys(Due_Date3);
												test.log(LogStatus.PASS, "Year is entered: "+Due_Date3);
												
												//=================
												WebElement	e1=driver.findElement(By.name("procDt1"));
												e1.clear();
												e1.sendKeys(Due_Date1);
												test.log(LogStatus.PASS, "Enterd Month as"+Date1); 
												WebElement	e2=driver.findElement(By.name("procDt2"));
												e2.clear();
												e2.sendKeys(Due_Date2);
												test.log(LogStatus.PASS, "Enterd date as"+Date2);
												WebElement	e3=driver.findElement(By.name("procDt3"));
												e3.clear();
												e3.sendKeys(Due_Date3);
												test.log(LogStatus.PASS, "Enterd year as"+Date3);
											   //================
												
												
												Thread.sleep(500);
												driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table[2]/tbody/tr/td/input[3]")).click();
												//driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table[2]/tbody/tr/td/input[3]")).click();
												test.log(LogStatus.PASS, "Clicked on Submit Button");
												Thread.sleep(1000);
												 //String alert1=   driver.switchTo().alert().getText();
												 //test.log(LogStatus.PASS, "Clicked on Finish Loan: "+alert1);
												 
												 try { 
													    Alert alert = driver.switchTo().alert();
													
													    alert.accept();
													    //if alert present, accept and move on.														
														
													}
													catch (Exception e) {
													    //do what you normally would if you didn't have the alert.
													}
												 Thread.sleep(10000);
												
												 wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Submit")));
												 Thread.sleep(5000);
												 driver.findElement(By.name("Submit")).click();
												 //test.log(LogStatus.PASS, "Clicked on Scheduler Ok");
												// test.log(LogStatus.PASS, MarkupHelper.createLabel("Clicked on Scheduler Ok Successfully",ExtentColor.GREEN));
												 test.log(LogStatus.PASS,"Clicked on  Ok Successfully");
												 test.log(LogStatus.PASS,"Store Date Has been changed through Admin Portal Successfully To :" +business_date);
												 test.log(LogStatus.PASS,"************************************************");

												 Thread.sleep(5000);
												 //driver.close();
							
							break;
						 
					}
				}
			

		break; //for FOR loop		
		}
				catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					//test.log(LogStatus.FAIL, MarkupHelper.createLabel("Borrower Registration is failed", ExtentColor.RED));
					//test.log(LogStatus.FAIL, "Age store is failed");
					test.log(LogStatus.FAIL, " "+e);
					test.log(LogStatus.INFO, "Age store Grace days process is initiated again due to Application sync issue");
					continue;


				}

	}
		if(i==3)
		{
			test.log(LogStatus.FAIL, "Age store Grace days is failed");
	
		}
	}	
	
	*/
	
	
	
}