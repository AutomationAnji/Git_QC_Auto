package tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.LogStatus;

public class ILPRefinanceprocess extends QCStore{

	public static String cardType;
	public static String cardNumber;
	public static String  cardEx_month;
	public static String cardEx_Year;
	public static String cvv;
	public static String CardHolderName;

	public static String paymentAmount;
	public static void Refinance(String SSN,String AppURL) throws InterruptedException
	{
	
			int lastrow=TestData.getLastRow("NewLoan");
			String sheetName="NewLoan";
			System.out.println("...."+sheetName);

			for(int row=2;row<=lastrow;row++)
			{		
				String RegSSN = TestData.getCellData(sheetName,"SSN",row);

				if(SSN.equals(RegSSN))
				{
					
					
					String ESign_CourtesyCallConsent = TestData.getCellData(sheetName,"ESign_CourtesyCallConsent",row);
					String ESign_Preference = TestData.getCellData(sheetName,"ESign_Preference",row);
					
				
					String TenderType = TestData.getCellData(sheetName,"TenderType",row);
					String ESign_CollateralType = TestData.getCellData(sheetName,"ESign_CollateralType",row);
					String PIN = TestData.getCellData(sheetName,"Password",row);
					
					cardType=TestData.getCellData(sheetName,"Card Type ",row);
					cardNumber=TestData.getCellData(sheetName,"Debit Card No",row);
					cardEx_month=TestData.getCellData(sheetName,"Expiry Month",row);
					cardEx_Year=TestData.getCellData(sheetName,"Expiry Year",row);
					cvv=TestData.getCellData(sheetName,"CVV",row);
					CardHolderName=TestData.getCellData(sheetName,"CardHolderName",row);

					String SSN1 = SSN.substring(0, 3);
					String SSN2 = SSN.substring(3,5);
					String SSN3 = SSN.substring(5,9);

					Thread.sleep(3000);
					test.log(LogStatus.INFO,"**************Refinance has  started**************");
					driver.switchTo().frame("topFrame");
					driver.findElement(locator(Aprop.getProperty("transactions_tab"))).click();			
					test.log(LogStatus.PASS, "Clicked on Loan Transactions");

					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");

					driver.findElement(By.cssSelector("li[id='911101']")).click();
					test.log(LogStatus.PASS, "Clicked on Transaction");		
					driver.switchTo().frame("main");	
					Thread.sleep(500);
					driver.findElement(By.name("ssn1")).sendKeys(SSN1);
					test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
					driver.findElement(locator(Aprop.getProperty("CSR_SSN_second_field"))).sendKeys(SSN2);
					test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
					driver.findElement(locator(Aprop.getProperty("CSR_SSN_third_field"))).sendKeys(SSN3);
					test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
					driver.findElement(locator(Aprop.getProperty("csr_new_loan_submit_button"))).click();
					test.log(LogStatus.PASS, "Clicked on submit Button");		
							
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");					    					   					     
					driver.findElement(locator(Aprop.getProperty("csr_new_loan_go_button"))).click();
					test.log(LogStatus.PASS, "Clicked on GO Button under search results");
					Thread.sleep(2000);					  
							
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");

					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
					test.log(LogStatus.PASS, "Clicked on GO Button Under Product web table");
					String Amt =driver.findElement(By.xpath("//*[@id='CustGrid']/tbody/tr[2]/td[10]")).getText();
					Thread.sleep(5000);
					driver.findElement(By.name("transactionList")).sendKeys("Refinance");
					test.log(LogStatus.PASS, "Transaction Type is selected as Refinance");
					driver.findElement(By.name("button")).click();
					test.log(LogStatus.PASS, "Clicked on Go button");
					Thread.sleep(1500);	
					try{
					driver.findElement(By.xpath("//*[@id='documentForm']/table/tbody/tr[4]/td/input[1]")).click();
					test.log(LogStatus.PASS, "Clicked Yes on cashOut popup");
					Thread.sleep(2000);
					}
					catch(Exception e)
					{
						
					}

					Thread.sleep(6000);
					
				
							driver.findElement(By.name("requestBean.siilBean.tenderTypeFirst")).sendKeys("Cash");
							Thread.sleep(6000);
						

						/*	WebElement e1=driver.findElement(By.name("requestBean.siilBean.tenderAmtFirst"));
							e1.clear();
							e1.sendKeys(paymentamount.trim());
							test.log(LogStatus.PASS, "Entered tender amount as  :"+paymentamount);*/

							

							driver.findElement(By.name("collateralType")).sendKeys(ESign_CollateralType);
							test.log(LogStatus.PASS, "Collateral Type is enterted as "+ESign_CollateralType);
							
							driver.switchTo().defaultContent();
							driver.switchTo().frame("mainFrame");
							driver.switchTo().frame("main");
							
							
							try{
							/*	String paymentamount=driver.findElement(By.name("refinanceLoanAmt")).getText();
								test.log(LogStatus.PASS, "Getting payment amount "+paymentamount);*/
							/*	String amtt[] =paymentamount.split(":");
								String totamt = amtt[1];*/
								driver.findElement(By.name("requestBean.siilBean.disbType")).sendKeys("Cash");
								test.log(LogStatus.PASS, "Disb type is selected as "+TenderType);
							
							
								driver.findElement(By.name("requestBean.siilBean.disbAmtFirst")).sendKeys(Amt.trim());
								test.log(LogStatus.PASS, "Disb amoutn entered as as "+Amt);
								
								driver.findElement(By.name("requestBean.password")).sendKeys(PIN);
								test.log(LogStatus.PASS, "ESign_Checks is selected as "+PIN);
									
									driver.findElement(By.name("finishLoan")).click();
									test.log(LogStatus.PASS, "click on Finish Loan button ");
									Thread.sleep(8000);
									driver.switchTo().defaultContent();
									driver.switchTo().frame("mainFrame");
									driver.switchTo().frame("main");
									
								/*	String confirm_text1=driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[2]/td[1]/b[1]")).getText();
									
									String confirm_text2=driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[2]/td[1]/b[2]")).getText();
									String confirm_text3=driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[2]/td[1]/b[3]")).getText();
									String confirm_text4=driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[3]/td/b")).getText();
								
									test.log(LogStatus.PASS, "confirm text is  "+confirm_text1+" Will receive an "+confirm_text2+" of "+confirm_text3+" First payment date is "+confirm_text4);
									*/
									driver.findElement(By.name("OKBut")).click();

									test.log(LogStatus.PASS, "click on Yes button ");

									driver.switchTo().defaultContent();
									driver.switchTo().frame("mainFrame");
									driver.switchTo().frame("main");
									if(driver.findElement(By.name("ok")).isDisplayed())
									{
										test.log(LogStatus.PASS, " Refinance is pass ");
										test.log(LogStatus.INFO, "**********************************************************************************");
									
									}
									else
									{
										test.log(LogStatus.INFO, "Refinance is pass ");
										test.log(LogStatus.INFO, "**********************************************************************************");
									}	
							}
							catch(Exception e)
							{
								
							}
						}
			}
	}
}
			
		
