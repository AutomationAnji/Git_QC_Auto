package tests;

import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;


public class AQCAgeStoreDueDate extends QCStore{


	public static void ageStoreDueDate(String SSN,String AppURL) throws InterruptedException, MalformedURLException, ParseException{


		int lastrow=TestData.getLastRow("NewLoan");
		String sheetName="NewLoan";

		for(int row=2;row<=lastrow;row++)
		{		
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			if(SSN.equals(RegSSN))
			{
				//String ProductID = TestData.getCellData(sheetName,"ProductID",row);

				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);
				Thread.sleep(4000);

				
				test.log(LogStatus.INFO, "Age Store Due Date process is initiated");

				driver.switchTo().defaultContent();						
				driver.switchTo().frame("topFrame");
				driver.findElement(By.cssSelector("li[id='910000']")).click();									
				test.log(LogStatus.PASS, "Clicked on Loan Transactions");

				Thread.sleep(1000);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(By.cssSelector("li[id='911101']")).click();			
				test.log(LogStatus.PASS, "Clicked on Transactions");

				Thread.sleep(1500);	
				driver.switchTo().frame("main");		
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");		

				Thread.sleep(1500);
				
				
				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Clicked on GO Button under search results");
													
			

				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				test.log(LogStatus.PASS, "Clicked on Go button under Loans section");

				driver.findElement(By.name("transactionList")).sendKeys("History");
				test.log(LogStatus.PASS, "Transaction Type is selected as History");
				driver.findElement(By.name("button")).click();
				Thread.sleep(500);
				test.log(LogStatus.PASS, "Clicked on Go button under Transaction selection section");
				
				loan_nbr=driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[4]/table/tbody/tr[4]/td/span[2]")).getText();
				test.log(LogStatus.PASS, "Loan Number is" + loan_nbr);
				
				
				NextDueDate = driver.findElement(By.xpath("//*[@id='transactionHistoryTable']/tbody/tr/td[3]/table/tbody/tr[4]/td/span[2]")).getText();
				test.log(LogStatus.PASS, "Next due date is " + NextDueDate);	

				Thread.sleep(2000);
				ACSRLoginLogout.logout();
				
				QCCSRLoginLogout.adminLogin(SSN, SSN);
				AAdminStartDate.ageToDuedate();
				QCCSRLoginLogout.adminLogout(driver, SSN, SSN);
								    
			

			}}
	}

}
