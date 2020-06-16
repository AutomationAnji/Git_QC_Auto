package tests;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/*import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;*/

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class RBusinessDateCapture extends QCStore{
	//private static String NextDueDate;

	public static void businessdate(String SSN,String AppURL){
		int i;
		for(i=0;i<4;i++)
		{
			
		 
		try{
			
				int lastrow=TestData.getLastRow("NewLoan");
				String sheetName="NewLoan";

					for(int row=2;row<=lastrow;row++)
							{		
								String RegSSN = TestData.getCellData(sheetName,"SSN",row);
								if(SSN.equals(RegSSN))
								{
							
								String PIN = TestData.getCellData(sheetName,"PIN",row);
						       // System.out.println(Password);
						        String StoreId = TestData.getCellData(sheetName,"StoreID",row);
						        String ProductID = TestData.getCellData(sheetName,"ProductID",row);
						
						       String AgeStore = TestData.getCellData(sheetName,"AgeStore",row);
						      
						
									  
								        String SSN1 = SSN.substring(0, 3);
								        String SSN2 = SSN.substring(3,5);
								        String SSN3 = SSN.substring(5,9);
								
								        
						       Thread.sleep(4000);
								//test.log(LogStatus.INFO, MarkupHelper.createLabel("Age Store process is initiated", ExtentColor.BLUE));
								test.log(LogStatus.INFO, "Age Store Grace days process is initiated");
								driver.switchTo().frame("bottom");
								String Str_date=driver.findElement(By.xpath("/html/body/blink/table/tbody/tr/td[4]")).getText();
								String store_date[]=Str_date.split(":");
								business_date=store_date[1].trim();
								test.log(LogStatus.PASS, "Business date is :"+business_date);
								

								
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
					test.log(LogStatus.INFO, "Business date capture is initiated again due to Application sync issue");
					continue;


				}

	}
		if(i==3)
		{
			test.log(LogStatus.FAIL, "Business date capture is failed");
	
		}
	}
	
	public static void duedate(String SSN,String AppURL){
		int i;
		for(i=0;i<3;i++)
		{
			
		try{
			
				int lastrow=TestData.getLastRow("NewLoan");
				String sheetName="NewLoan";

					for(int row=2;row<=lastrow;row++)
							{		
								String RegSSN = TestData.getCellData(sheetName,"SSN",row);
								if(SSN.equals(RegSSN))
								{
								
						        String ProductID = TestData.getCellData(sheetName,"ProductID",row);
						       
								        String SSN1 = SSN.substring(0, 3);
								        String SSN2 = SSN.substring(3,5);
								        String SSN3 = SSN.substring(5,9);
						       Thread.sleep(4000);
								//test.log(LogStatus.INFO, MarkupHelper.createLabel("Age Store process is initiated", ExtentColor.BLUE));
								test.log(LogStatus.INFO, "************* Going to History Screen To Capture DueDate and Loan Number For AgeStore **************");
								driver.switchTo().frame("bottom");
								String Str_date=driver.findElement(By.xpath("/html/body/blink/table/tbody/tr/td[4]")).getText();
								String store_date[]=Str_date.split(":");
								String business_date=store_date[1].trim();
								test.log(LogStatus.PASS, "Business date is :"+business_date);
								driver.switchTo().defaultContent();	
								
						        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("topFrame")));
								driver.switchTo().frame("topFrame");
								wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='910000']")));
						        driver.findElement(By.cssSelector("li[id='910000']")).click();	
									
								test.log(LogStatus.PASS, "Clicked on Loan Transactions");
								Thread.sleep(1000);
								try{
								driver.switchTo().defaultContent();
								driver.switchTo().frame("mainFrame");
								driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
								driver.findElement(By.cssSelector("li[id='911101']")).click();			
								test.log(LogStatus.PASS, "Clicked on Transactions");
								}
								catch(Exception e)
								{
									driver.get(csrloginpage);
									driver.switchTo().defaultContent();	
									
							        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("topFrame")));
									driver.switchTo().frame("topFrame");
									wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='910000']")));
							        driver.findElement(By.cssSelector("li[id='910000']")).click();	
										
									Thread.sleep(1000);	
									driver.switchTo().defaultContent();
									driver.switchTo().frame("mainFrame");
									driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
									driver.findElement(By.cssSelector("li[id='911101']")).click();	
									test.log(LogStatus.PASS, "Clicked on Transactions");
								}
								driver.switchTo().frame("main");		
								driver.findElement(By.name("ssn1")).sendKeys(SSN1);
								test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
								driver.findElement(By.name("ssn2")).sendKeys(SSN2);
								test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
								driver.findElement(By.name("ssn3")).sendKeys(SSN3);
								test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
								driver.findElement(By.name("submit1")).click();
								test.log(LogStatus.PASS, "Click on submit Button");	
								
								
				//============= Taking Due Date From History ====================
								for(String winHandle : driver.getWindowHandles()){
								    driver.switchTo().window(winHandle);
									}
								    driver.switchTo().defaultContent();
								    driver.switchTo().frame("mainFrame");
								    driver.switchTo().frame("main");
								    
								    
								  // String loan_nbr= driver.findElement(locator(Rprop.getProperty("csr_loan_nbr"))).getText();
								  // test.log(LogStatus.PASS, "Loan Number is" + loan_nbr);
								    driver.findElement(By.name("button")).click();
									test.log(LogStatus.PASS, "Clicked on GO Button under search results");
									// driver.findElement(By.name("button")).click();
									
								for(String winHandle : driver.getWindowHandles()){
									    driver.switchTo().window(winHandle);
										}				    
									 driver.switchTo().defaultContent();
									    driver.switchTo().frame("mainFrame");
									    driver.switchTo().frame("main");
									   					    
									    Thread.sleep(5000);
									    
									    	 driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
											    test.log(LogStatus.PASS, "Clicked on Go button under Loans section");
									
									   //  String loan_nbr= driver.findElement(locator(Rprop.getProperty("csr_loan_nbr"))).getText();
										//   test.log(LogStatus.PASS, "Loan Number is" + loan_nbr);
										 driver.findElement(By.name("transactionList")).sendKeys("History");
										 test.log(LogStatus.PASS, "Transaction Type is selected as History");
										 driver.findElement(By.name("button")).click();
										 test.log(LogStatus.PASS, "Clicked on Go button under Transaction selection section");
										 
										
										 Thread.sleep(3000); 
										 //NextDueDate=driver.findElement(locator(Rprop.getProperty("loan_status_inf_due_date"))).getText();
										 NextDueDate=driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
										                                          
										 test.log(LogStatus.PASS, "Next due date is "+NextDueDate);
									     Thread.sleep(1000);
									     loan_number=driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[4]/table/tbody/tr[4]/td/span[2]")).getText();
									                                                                            
									     test.log(LogStatus.PASS, "Loan Number  is "+loan_number);	
								

								
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
					test.log(LogStatus.INFO, "Business date capture is initiated again due to Application sync issue");
					continue;


				}

	}
		if(i==3)
		{
			test.log(LogStatus.FAIL, "Business date capture is failed");
	
		}
	}
	
	
	
}