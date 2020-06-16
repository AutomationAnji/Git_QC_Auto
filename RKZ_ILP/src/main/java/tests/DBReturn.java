package tests;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import com.relevantcodes.extentreports.LogStatus;

import oracle.jdbc.OracleTypes;

public class DBReturn extends QCStore{

	public static void proc(String SSN) throws ClassNotFoundException, SQLException, InterruptedException {
		Connection conn = null;

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
		// Object of Statement. It is used to create a Statement to execute the
		// query
		Statement stmt = null;
		Statement stmt2 = null;
		

		// Object of ResultSet => 'It maintains a cursor that points to the
		// current row in the result set'
		ResultSet resultSet = null;
		List<String> rowValues = new ArrayList();

		Class.forName("oracle.jdbc.driver.OracleDriver");
		//System.out.println("before conn");
		// Open a connection
		try {

			//conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.2.241:1521:QFUNDUAT1", prop.getProperty("db_username"),prop.getProperty("db_username"));
			
			//conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.2.241:1521:QFUNDUAT1", "QCAUTO_REL3_OCT1619","QCAUTOrel357");
			//test.log(LogStatus.PASS, "Connecting to DB ");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.2.254:1521:QFUNDUAT4", "ACSPROD_AUTOM_MAR0419","ACSProdAuto194");
			test.log(LogStatus.PASS, "Connecting to DB ");

		} catch (SQLException e1) {

			System.out.println("Connection Failed! Check output console" + e1);
			e1.printStackTrace();
		}

		// Execute a query
		stmt = conn.createStatement();
		stmt2 = conn.createStatement();
		
		//driver.switchTo().frame("bottom");
		//String Str_date=driver.findElement(By.xpath("/html/body/blink/table/tbody/tr/td[4]")).getText();
		//String appdatelist[]=Str_date.split(":");
		//appdate=appdatelist[1].trim();
		//test.log(LogStatus.PASS, "Current store date is "+Str_date);
		//NextDueDate="11/20/19";
		test.log(LogStatus.PASS, "Current store date is "+NextDueDate);
		 String Due_Date[] =NextDueDate.split("/");
	   
		 String Due_Date1 = Due_Date[0];
	     String Due_Date2 = Due_Date[1];
	     String Due_Date3 = Due_Date[2];
	     String DBdate=Due_Date2+"-"+Due_Date1+"-"+Due_Date3;
	     System.out.println(DBdate);
	     test.log(LogStatus.PASS, "Current store date format changed to DB SCHEDULE_DATE "+DBdate);
	     //loan_nbr="11036726";
		String schedule_id="116";
		try {
		
			
			stmt.executeQuery("update SCHEDULE_ATTR set IS_PROCESSED='NOP' where SCHEDULE_ID="+schedule_id);
			test.log(LogStatus.PASS, "Updating the Is_Processed to NOP");
			stmt.executeQuery("commit");
			resultSet=stmt.executeQuery("select IS_PROCESSED from schedule_attr where SCHEDULE_ID=116");
			System.out.println(resultSet);
			Thread.sleep(30000);
			System.out.println(resultSet);
			//SELECT * FROM ST_LO_APPORTIONS where APPORTION_ID='CSO' and APPORTION_AMT='124.46' and LOAN_TRAN_CODE in (SELECT LOAN_TRAN_CODE FROM ST_LO_trans where loan_code=5739144);
			
			//resultSet =stmt2.executeQuery("select loan_code,status_id,response_code,CC_info_key from REPAY_DEPOSIT_SCHEDULE where loan_code="+loan_nbr);
			//test.log(LogStatus.PASS, "Verifying where LOAN is returned or not");
			//test.log(LogStatus.PASS, "Waiting for records to be updated");
						
					
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (Exception e) {
				}
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
				}
			}
		} 
		catch (Exception e2) {

			System.out.println(" console" + e2);
			e2.printStackTrace();
			
		
		}
		Thread.sleep(4000);
		System.out.println(resultSet);
	}
		}
	}



//*****************------------------------------------------------**************************************\\



public static void redepositDC(String SSN) throws ClassNotFoundException, SQLException {
	Connection conn = null;

	// Object of Statement. It is used to create a Statement to execute the
	// query
	Statement stmt = null;
	Statement stmt2 = null;
	

	// Object of ResultSet => 'It maintains a cursor that points to the
	// current row in the result set'
	ResultSet resultSet = null;
	List<String> rowValues = new ArrayList();

	Class.forName("oracle.jdbc.driver.OracleDriver");
	int lastrow=TestData.getLastRow("Deposit");
	String sheetName="Deposit";

	for(int row=2;row<=lastrow+1;row++)
	{		
		String RegSSN = TestData.getCellData(sheetName,"SSN",row);
		if(SSN.equals(RegSSN))
		{
			String Insta = TestData.getCellData(sheetName,"Insta",row);

			String SSN1 = SSN.substring(0, 3);
			String SSN2 = SSN.substring(3,5);
			String SSN3 = SSN.substring(5,9);		       


			test.log(LogStatus.INFO, "DC Redeposit Qurrey has initiated");
	//System.out.println("before conn");
	// Open a connection
	try {

		//conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.2.241:1521:QFUNDUAT1", prop.getProperty("db_username"),prop.getProperty("db_username"));
		
		//conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.2.241:1521:QFUNDUAT1", "QCAUTO_REL3_OCT1619","QCAUTOrel357");
		//test.log(LogStatus.PASS, "Connecting to DB ");
		
		conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.2.241:1521:QFUNDUAT1", "QC_AUTOM_NOV2619","QCautoM261");
		test.log(LogStatus.PASS, "Connecting to DB ");

	} catch (SQLException e1) {

		System.out.println("Connection Failed! Check output console" + e1);
		e1.printStackTrace();
	}

	// Execute a query
	stmt = conn.createStatement();
	stmt2 = conn.createStatement();
	//loan_nbr="11036309";
	String schedule_id="1229";
	try {
		
		resultSet=stmt.executeQuery("select cc_info_key from repay_deposit_schedule where installment_nbr=5 and loan_code="+loan_nbr);
		String key = "";
		if(resultSet.next()){	
		 key=resultSet.getString("cc_info_key");
		}
		stmt.executeQuery("update REPAY_DEPOSIT_SCHEDULE set CC_info_key ='"+key+"'where installment_nbr='"+Insta+"' and loan_code="+loan_nbr);
		test.log(LogStatus.PASS, "Executing the query with loan number"+loan_nbr);
		test.log(LogStatus.PASS, "Updating the CC_info_key to" +key);
		
		stmt.executeQuery("update REPAY_DEPOSIT_SCHEDULE set IS_REPRESENTMENT_NEXT='N' where installment_nbr='"+Insta+"' and loan_code="+loan_nbr);
		test.log(LogStatus.PASS, "Updating the IS_REPRESENTMENT_NEXT to N");
		//SELECT * FROM ST_LO_APPORTIONS where APPORTION_ID='CSO' and APPORTION_AMT='124.46' and LOAN_TRAN_CODE in (SELECT LOAN_TRAN_CODE FROM ST_LO_trans where loan_code=5739144);
		
		//resultSet =stmt2.executeQuery("select loan_code,status_id,response_code,CC_info_key from REPAY_DEPOSIT_SCHEDULE where loan_code="+loan_nbr);
		//test.log(LogStatus.PASS, "Verifying where LOAN is returned or not");
		//test.log(LogStatus.PASS, "Waiting for records to be updated");
Thread.sleep(20000);					
				
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (Exception e) {
			}
		}

		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
			}
		}
	} 
	catch (Exception e2) {

		System.out.println(" console" + e2);
		e2.printStackTrace();
		driver.close();
	}

}
	}
}
}

