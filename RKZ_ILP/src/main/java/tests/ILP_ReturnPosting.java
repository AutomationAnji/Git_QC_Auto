package tests;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.relevantcodes.extentreports.LogStatus;

import tests.QCStore;

public class ILP_ReturnPosting extends QCStore

{
	public static void qcReturn (String SSN,String AppURL) throws InterruptedException
	{
	
			test.log(LogStatus.PASS, "************ Return posting Transaction started****************");
			

			int lastrow=TestData.getLastRow("Return");
			String sheetName="Return";

			for(int row=2;row<=lastrow;row++)
			{		
				String RegSSN = TestData.getCellData(sheetName,"SSN",row);
				if(SSN.equals(RegSSN))
				{
					//String  StoreID=TestData.getCellData(sheetName,"StoreID",row);
					String  StoreID="26";
					String  ReasonForReturn=TestData.getCellData(sheetName,"ReasonForReturn",row);
					Thread.sleep(1000);
					driver.switchTo().frame("topFrame");
					driver.findElement(By.partialLinkText("Transactions")).click();   
					test.log(LogStatus.PASS, "Clicked on Transactions");
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					Thread.sleep(3000);
					driver.findElement(By.linkText("ACH")).click();
					test.log(LogStatus.PASS, "Clicked on Collateral Checks link");
					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");
					Thread.sleep(2500);
					driver.findElement(By.linkText("Installment Loan")).click();

					test.log(LogStatus.PASS, "Clicked on Installment Loan");

					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					Thread.sleep(1500);

					driver.findElement(By.linkText("INSTALLMENT ACH RETURN")).click();

					test.log(LogStatus.PASS, "Clicked on Return Posting");

					Thread.sleep(2500);

						Actions action = new Actions(driver);
						action.moveByOffset(200,100).perform();
						Thread.sleep(2000);
						action.click();
						
					driver.switchTo().defaultContent();

					driver.switchTo().frame("mainFrame");

					driver.switchTo().frame("main");

					WebElement e2=driver.findElement(By.name("requestBean.homeLocNbr"));
					e2.click();
					Thread.sleep(2500);
					e2.sendKeys(StoreID);
					test.log(LogStatus.PASS, "Store ID is entered: "+StoreID);
					Thread.sleep(1000);
					
					driver.findElement(By.name("requestBean.dealNbr")).sendKeys("4364962");					
					
					driver.findElement(By.name("submit")).click();   
					test.log(LogStatus.PASS, "Clicked on Submit Button");

					Thread.sleep(1500);
					
					for( String winHandle1 : driver.getWindowHandles())
					{
					    driver.switchTo().window(winHandle1);
					}			
					 driver.switchTo().defaultContent();
					 driver.switchTo().frame("mainFrame");
					 driver.switchTo().frame("main");
				

					 int CheckBoxsize= driver.findElements(By.xpath("/html/body/table/tbody/tr[1]/td/table[2]/tbody/tr")).size();
					 
					for (int i =3; i <= CheckBoxsize;i++)
					{
											 
						String Value = driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td/table[2]/tbody/tr["+i+"]/td[1]/input[2]")).getAttribute("value");
						 
						
							 driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td/table[2]/tbody/tr["+i+"]/td[1]/input[2]")).click();
							 test.log(LogStatus.PASS, "Customer Record CheckBox Selected");
							 
							 driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td/table[2]/tbody/tr["+i+"]/td[10]/select")).sendKeys("R01-Insufficient Funds");
							 test.log(LogStatus.PASS, "Return Reason Selected as ::  R01-Insufficient Funds");		
						 
						 break;
						 
						 }
						
						
					
					 /*driver.findElement(By.name("requestBean.chkName")).click();
							test.log(LogStatus.PASS, "Customer Record CheckBox Selected");					  	        			   
							driver.findElement(By.name("rtnReasonId")).sendKeys("R01-Insufficient Funds");
							test.log(LogStatus.PASS, "Return Reason Selected as ::  R01-Insufficient Funds");*/
			
							driver.findElement(By.name("CmdReturnPosting")).click();
							test.log(LogStatus.PASS, "Clicked on ACH Return Posting button");
							
							for( String winHandle1 : driver.getWindowHandles())
							{
							    driver.switchTo().window(winHandle1);
							}			
							 driver.switchTo().defaultContent();
							 driver.switchTo().frame("mainFrame");
							 driver.switchTo().frame("main");
							 
							 test.log(LogStatus.PASS, "ACH Return Posting Done Successfull");	
					driver.close();
					
				
				}
				
			}
	}
}
					
					
				