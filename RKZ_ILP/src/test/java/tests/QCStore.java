package tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
@Listeners(tests.TestListeners.class)
public class QCStore {

	public static WebDriverWait wait;
	public static WebDriver driver;
	public static WebDriver driver1;
	String appUrl;
	String timestamp = new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss").format(new Date());

	public static Properties prop;
	public static Properties Jprop;
	public static Properties Rprop;
	public static Properties Aprop;
	public static Properties Vprop;
	public static String loan_number;
	public static ExtentReports reports;
	public static ExtentReports Breports;
	public static ExtentReports Jreports;
	public static ExtentReports Rreports;
	public static ExtentReports Areports;
	public static ExtentTest test;
	public static String Eankey = null;
	public static String encryption_store_no = null;
	public static String encryption_transaction_nbr = null;
	public static String FileName;
	public static ExcelNew TestData;
	public static String day;

	public static String loan_nbr;
	public static String NextDueDate;
	public static String AppURL;
	public static String business_date;
	public static String No_of_Installments;
	public static String transaction_date;

	public static String ESign_CheckNbr;
	public static String Password;
	public static String ESign_CollateralType;
	public static String appdate;
	public static String Date1;
	public static String Date2;
	public static String Date3;
	public static String customer_number;

	public static String Drawer_OverShort_Amount;

	public static String Due_Date1;
	public static String Due_Date2;
	public static String Due_Date3;
	public static String passwrd;
	public static String FirstName;

	public static String AdminURL;
	public static String BAdminURL;

	public static String csr_url;
	public static String csrloginpage;

	
	// Anoop Transactions

 
@Test(priority = 1, enabled = true)
	   public void ILP_borrowregNewloan() throws Exception {
			FileName = "QC_BorrowerRegistration_NewLoan.xls";
			test = reports.startTest("QC_BorrowerRegistration_NewLoan",
					"Login-->Home Screen-->Borrower Registration-->New Loan");

			TestData = new ExcelNew(
					System.getProperty("user.dir") + Aprop.getProperty("QC_Store_NewLoan_Test_data_sheet_path") + FileName);
			String sheetName = "Start";
			int lastrow = TestData.getLastRow("Start");

			for (int row = 2; row <= lastrow; row++) {
				String RunFlag = TestData.getCellData(sheetName, "Run", row);

				if (RunFlag.equals("Y")) {
					String AppURL = TestData.getCellData(sheetName, "AppURL", row);
					String SSN = TestData.getCellData(sheetName, "SSN", row);

				    ACSRLoginLogout.login(SSN, AppURL);
					ACSRBorrowerRegistration.borrowerReg(SSN, AppURL);
					ACSRLoginLogout.logout();
					
					ACSRLoginLogout.login(SSN, AppURL);
					QCCSRNewLoan.newLoan(SSN, AppURL);
					ACSRLoginLogout.logout();
					
				}
			}
		}
		
		
		////@Test(priority = 2, enabled = true)
		public void ILP_inactiveNewloan() throws Exception {
			FileName = "QC_NewLoan_InactiveCustomer.xls";
			test = reports.startTest("QC_NewLoan_InactiveCustomer", "Login-->Home Screen-->In active customer-->New Loan");

			TestData = new ExcelNew(
					System.getProperty("user.dir") + Aprop.getProperty("QC_Store_NewLoan_Test_data_sheet_path") + FileName);
			String sheetName = "Start";
			int lastrow = TestData.getLastRow("Start");
			for (int row = 2; row <= lastrow; row++) {
				String RunFlag = TestData.getCellData(sheetName, "Run", row);
				if (RunFlag.equals("Y")) {
					String AppURL = TestData.getCellData(sheetName, "AppURL", row);
					String SSN = TestData.getCellData(sheetName, "SSN", row);

					ACSRLoginLogout.login(SSN, AppURL);
					ACSRBorrowerRegistration.borrowerReg(SSN, AppURL);
					ACSRLoginLogout.logout();
					
					ACSRLoginLogout.login(SSN, AppURL);
					QCCSRNewLoan.newLoan(SSN, AppURL);
					ACSRLoginLogout.logout();
					
					
				}
			}
		}
		
		//@Test(priority = 3, enabled = true)
		public void ILP_maxloanCount() throws Exception {
			FileName = "QC_MaxLoanCount.xls";
			test = reports.startTest("QC_MaxLoanCount", "Login-->Borrower-->Max loan count");

			TestData = new ExcelNew(
					System.getProperty("user.dir") + Aprop.getProperty("QC_Store_NewLoan_Test_data_sheet_path") + FileName);
			String sheetName = "Start";
			int lastrow = TestData.getLastRow("Start");

			for (int row = 2; row <= lastrow; row++) {
				String RunFlag = TestData.getCellData(sheetName, "Run", row);

				if (RunFlag.equals("Y")) {
					String AppURL = TestData.getCellData(sheetName, "AppURL", row);
					String SSN = TestData.getCellData(sheetName, "SSN", row);
					
					ACSRLoginLogout.login(SSN, AppURL);
					ACSRBorrowerRegistration.borrowerReg(SSN, AppURL);
					ACSRLoginLogout.logout();				

					for (int i = 2; i <= 2; i++) {

						ACSRLoginLogout.login(SSN, AppURL);
						MaxLoanCount.maxLoan(SSN, AppURL);
						ACSRLoginLogout.logout();
						
						if (!(i == 2)) {
							test.log(LogStatus.INFO, "******Customer loan Number " + i + "is approved successfully*****");
							continue;
							//break;
						} else {
							test.log(LogStatus.INFO, "******Customer loan Number " + i + "is denied*****");
							break;
						}
					}
				
			
				}

			}

		}

	//@Test(priority = 4, groups = "Brahmith1")

		public static void QC_NewLoan_Promotion_Txn() throws Exception {
			try {

				test = reports.startTest(prop.getProperty("QC_Store_ILP_NewLoan_Promotion_Txn_scenario"),
						"Login->Borrower Registratino->New Loan with Promotion");

				FileName = prop.getProperty("QC_Store_ILP_NewLoan_Promotion_Txn_file_name") + ".xls";

				TestData = new ExcelNew(System.getProperty("user.dir") + prop.getProperty("QC_Store_NewLoan_Test_data_sheet_path") + FileName);

				String sheetName = "Start";
				int lastrow = TestData.getLastRow("Start");
				System.out.println(lastrow);
				for (int row = 2; row <= lastrow; row++) {

					String RunFlag = TestData.getCellData(sheetName, "Run", row);
					// System.out.println(RunFlag);
					if (RunFlag.equals("Y")) {

						// AppURL = TestData.getCellData(sheetName, "AppURL", row);
						String SSN = TestData.getCellData(sheetName, "SSN", row);

						System.out.println(AppURL);
						

						ACSRLoginLogout.login(SSN, AppURL);
						ACSRBorrowerRegistration.borrowerReg(SSN, AppURL);
						ACSRLoginLogout.logout();
						
						ACSRLoginLogout.login(SSN, AppURL);
						QCCSRNewLoan.newLoan(SSN, AppURL);
						ACSRLoginLogout.logout();
						

					}
				}
			}

			catch (Exception e) {

				// test.log(LogStatus.ERROR, MarkupHelper.createLabel("Unable to
				// start scenario 1 " , ExtentColor.RED));
				test.log(LogStatus.ERROR, "Unable to start scenario QC_BorrowerRegistration_NewLoan_Promotion_Txn ");

			}
		}
		
		
	//@Test(priority = 5)

		public static void QC_NewLoan_Void_Txn() throws Exception {
			try {
				
				test = reports.startTest((prop.getProperty("QC_Store_NewLoan_Void_Txn_Scenario")),
						"Login->Borrower Registratino->New Loan->Void");

				FileName = prop.getProperty("QC_Store_NewLoan_Void_Txn_file_name") + ".xls";

				TestData = new ExcelNew(System.getProperty("user.dir") + prop.getProperty("QC_Store_NewLoan_Test_data_sheet_path") + FileName);

				int lastrow = TestData.getLastRow("Start");
				String sheetName = "Start";

				System.out.println(lastrow);
				for (int row = 2; row <= lastrow; row++) {

					String RunFlag = TestData.getCellData(sheetName, "Run", row);
					// System.out.println(RunFlag);
					if (RunFlag.equals("Y")) {

					
						String SSN = TestData.getCellData(sheetName, "SSN", row);

						System.out.println(AppURL);

						ACSRLoginLogout.login(SSN, AppURL);
						ACSRBorrowerRegistration.borrowerReg(SSN, AppURL);
						ACSRLoginLogout.logout();
						
						ACSRLoginLogout.login(SSN, AppURL);
						QCCSRNewLoan.newLoan(SSN, AppURL);
						ACSRLoginLogout.logout();
						
						
						QCCSRLoginLogout.login(SSN, AppURL);
						QCVoid.qcVoid(SSN, AppURL);
						QCCSRLoginLogout.logout(SSN, AppURL);
					
						/*QCCSRLoginLogout.login(SSN, AppURL);
						QCCSRHistory.history(SSN, AppURL);
						QCCSRLoginLogout.logout(SSN, AppURL);*/
					}
				}
			}

			catch (Exception e) {

				// test.log(LogStatus.ERROR, MarkupHelper.createLabel("Unable to
				// start scenario 1 " , ExtentColor.RED));
				test.log(LogStatus.ERROR, "Unable to start scenario QC_NewLoan_Void_Txn ");

			}
		}

		
//@Test(priority = 6)
		public static void QC_AgeStore_Void_Txn() throws Exception {
			
			try {
				
				test = reports.startTest((prop.getProperty("QC_Store_AgeStore_Void_Txn_Scenario")),
						"Login->Borrower Registration->New Loan->AgeStore->Void");

				FileName = prop.getProperty("QC_Store_AgeStore_Void_Txn_file_name") + ".xls";

				TestData = new ExcelNew(System.getProperty("user.dir")+ prop.getProperty("QC_Store_NewLoan_Test_data_sheet_path") + FileName);

				int lastrow = TestData.getLastRow("Start");
				String sheetName = "Start";
				
				System.out.println(lastrow);
				for (int row = 2; row <= lastrow; row++) {

					String RunFlag = TestData.getCellData(sheetName, "Run", row);
					
					if (RunFlag.equals("Y")) {

						
						String SSN = TestData.getCellData(sheetName, "SSN", row);
						System.out.println(AppURL);
						
						ACSRLoginLogout.login(SSN, AppURL);
						ACSRBorrowerRegistration.borrowerReg(SSN, AppURL);
						ACSRLoginLogout.logout();
						
						ACSRLoginLogout.login(SSN, AppURL);
						QCCSRNewLoan.newLoan(SSN, AppURL);
						ACSRLoginLogout.logout();
						
				        QCCSRLoginLogout.login(SSN, AppURL);					
						AQCAgeStoreDueDate.ageStoreDueDate(SSN, SSN);
						
						ACSRLoginLogout.login(SSN, AppURL);
						QCEncryptionDetails.readEncryptionDetails(SSN, AppURL);
						QCCSRLoginLogout.logout(SSN, AppURL);
						QCCSRLoginLogout.login(SSN, AppURL);
						
						
						QCAdminEncryption.getEncryption(driver, SSN, AppURL);					
						QCCSRLoginLogout.login(SSN, AppURL);					
						QCAgeStoreVoid.ageStoreVoid(SSN, AppURL);					
						QCCSRLoginLogout.logout(SSN, AppURL);
									
						
					}
				}
			}

			catch (Exception e) {
				System.out.println("under catch of Test");			
				test.log(LogStatus.ERROR, "Unable to start scenario QC_AgeStore_Void_Txn ");
			}
		}
	
//@Test(priority = 7, enabled = true)

		public void ILP_rescindloan() throws Exception {

			test = reports.startTest("QC_Rescind", "Loan->Rescind");
			FileName = "QC_NewLoan_Rescind.xls";

			TestData = new ExcelNew(
					System.getProperty("user.dir") + Aprop.getProperty("QC_Store_NewLoan_Test_data_sheet_path") + FileName);
			String sheetName = "Start";
			int lastrow = TestData.getLastRow("Start");
			System.out.println(lastrow);
			for (int row = 2; row <= lastrow; row++) {
				String RunFlag = TestData.getCellData(sheetName, "Run", row);

				if (RunFlag.equals("Y")) {
					String AppURL = TestData.getCellData(sheetName, "AppURL", row);
					String SSN = TestData.getCellData(sheetName, "SSN", row);

					ACSRLoginLogout.login(SSN, AppURL);
					ACSRBorrowerRegistration.borrowerReg(SSN, AppURL);
					ACSRLoginLogout.logout();
					
					ACSRLoginLogout.login(SSN, AppURL);
					QCCSRNewLoan.newLoan(SSN, AppURL);
					ACSRLoginLogout.logout();
					
					ACSRLoginLogout.login(SSN, AppURL);
					ACSRRescindLoan.rescind(SSN, AppURL);
					ACSRLoginLogout.logout();

				

				}
			}
		}

	
		
//@Test(priority = 8, enabled = true)
		
			public void ILP_agerescindtest() throws Exception {
				test = reports.startTest("QC_AgeStore_Rescind Loan", "Login-->Age the loan to rescind days--->Rescind loan");
				FileName = "QC_NewLoan_AgeRescind.xls";

				TestData = new ExcelNew(
						System.getProperty("user.dir") + Aprop.getProperty("QC_Store_NewLoan_Test_data_sheet_path") + FileName);
				String sheetName = "Start";
				int lastrow = TestData.getLastRow("Start");

				for (int row = 2; row <= lastrow; row++) {
					String RunFlag = TestData.getCellData(sheetName, "Run", row);

					if (RunFlag.equals("Y")) {
						String AppURL = TestData.getCellData(sheetName, "AppURL", row);
						String SSN = TestData.getCellData(sheetName, "SSN", row);


						ACSRLoginLogout.login(SSN, AppURL);
						ACSRBorrowerRegistration.borrowerReg(SSN, AppURL);
						ACSRLoginLogout.logout();
						
						ACSRLoginLogout.login(SSN, AppURL);
						QCCSRNewLoan.newLoan(SSN, AppURL);
						ACSRLoginLogout.logout();
						
						ACSRLoginLogout.login(SSN, AppURL);				
						ACSRRescindLoan.agerescind(SSN, AppURL);
						
						QCCSRLoginLogout.login(SSN, AppURL);
						ACSRRescindLoan.rescind(SSN, AppURL);
						QCCSRLoginLogout.logout(SSN, AppURL);

						/*ACSRLoginLogout.login(SSN, AppURL);
						AQCCSRHistory.history(SSN, AppURL);
						ACSRLoginLogout.logout();
						*/

					}
				}
			}
			
	//@Test(priority = 9)

			public static void QC_Installmentpayment_Txn() throws Exception {
				try {
					test = reports.startTest((prop.getProperty("QC_ILP_Installmentpayment_Txn_Scenario")),
							"Login->Borrower Registratino->New Loan->AgeStore->PartialPayment Pay Installment Amt");
					FileName = prop.getProperty("QC_ILP_Installmentpayment_Txn_file_name") + ".xls";

					TestData = new ExcelNew(System.getProperty("user.dir")
							+ prop.getProperty("QC_Store_NewLoan_Test_data_sheet_path") + FileName);

					int lastrow = TestData.getLastRow("Start");
					String sheetName = "Start";

					System.out.println(lastrow);
					for (int row = 2; row <= lastrow; row++) {

						String RunFlag = TestData.getCellData(sheetName, "Run", row);
						// System.out.println(RunFlag);
						if (RunFlag.equals("Y")) {

						String SSN = TestData.getCellData(sheetName, "SSN", row);

							System.out.println(AppURL);

					        ACSRLoginLogout.login(SSN, AppURL);
							ACSRBorrowerRegistration.borrowerReg(SSN, AppURL);
							ACSRLoginLogout.logout();
							
							ACSRLoginLogout.login(SSN, AppURL);
							QCCSRNewLoan.newLoan(SSN, AppURL);
							ACSRLoginLogout.logout();
							
							QCCSRLoginLogout.login(SSN, AppURL);
						    AQCAgeStoreDueDate.ageStoreDueDate(SSN, SSN);
							QCCSRLoginLogout.logout(SSN, AppURL);
							
							QCCSRLoginLogout.login(SSN, AppURL);
							QCPayment.payment(SSN, AppURL);
							QCCSRLoginLogout.logout(SSN, AppURL);
							
							
						}
					}
				}

				catch (Exception e) {
					System.out.println("QC_Installmentpayment_Txn");			
					test.log(LogStatus.ERROR, "Unable to start scenario QC_Installmentpayment_Txn ");

				}
			}
			
			
//@Test(priority = 10)

			public static void QC_Installmentpayment_Void_Txn() throws Exception {
				try {
					test = reports.startTest((prop.getProperty("QC_ILP_Installmentpayment_Void_Txn_Scenario")),
							"Login->Borrower Registratino->New Loan->AgeStore->PartialPayment Pay Installment Amt->Void");

					// test =
					// extent.createTest(prop.getProperty("QC_Store_AgeStore_Deposit_Menu_Txn_file_name"));

					FileName = prop.getProperty("QC_ILP_Installmentpayment_Void_Txn_file_name") + ".xls";

					TestData = new ExcelNew(System.getProperty("user.dir")
							+ prop.getProperty("QC_Store_NewLoan_Test_data_sheet_path") + FileName);

					int lastrow = TestData.getLastRow("Start");
					String sheetName = "Start";

					System.out.println(lastrow);
					for (int row = 2; row <= lastrow; row++) {

						String RunFlag = TestData.getCellData(sheetName, "Run", row);
						// System.out.println(RunFlag);
						if (RunFlag.equals("Y")) {

							// String AppURL = TestData.getCellData(sheetName, "AppURL",
							// row);
							String SSN = TestData.getCellData(sheetName, "SSN", row);

							System.out.println(AppURL);

							ACSRLoginLogout.login(SSN, AppURL);
							ACSRBorrowerRegistration.borrowerReg(SSN, AppURL);
							ACSRLoginLogout.logout();
							
							ACSRLoginLogout.login(SSN, AppURL);
							QCCSRNewLoan.newLoan(SSN, AppURL);
							ACSRLoginLogout.logout();
							
							QCCSRLoginLogout.login(SSN, AppURL);
							AQCAgeStoreDueDate.ageStoreDueDate(SSN, SSN);
							//QCCSRLoginLogout.logout(SSN, AppURL);
							
							QCCSRLoginLogout.login(SSN, AppURL);
							QCPayment.payment(SSN, AppURL);
							QCCSRLoginLogout.logout(SSN, AppURL);
							
							QCCSRLoginLogout.login(SSN, AppURL);
							QCVoid.qcVoid(SSN, AppURL);
							QCCSRLoginLogout.logout(SSN, AppURL);
							
							/*QCCSRLoginLogout.login(SSN, AppURL);
							QCCSRHistory.history(SSN, AppURL);
							QCCSRLoginLogout.logout(SSN, AppURL);*/
						}
					}
				}

				catch (Exception e) {
					System.out.println("QC_Installmentpayment_Void_Txn");
					// test.log(LogStatus.ERROR, MarkupHelper.createLabel("Unable to
					// start scenario 1 " , ExtentColor.RED));
					test.log(LogStatus.ERROR, "Unable to start scenario QC_Installmentpayment_Void_Txn ");

				}
			}
		
//@Test(priority = 11)

			public static void QC_PayAnyotherAmount_Txn() throws Exception {
				try {
					test = reports.startTest((prop.getProperty("QC_ILP_Payanyotheramount_Txn_Scenario")),
							"Login->Borrower Registratino->New Loan->AgeStore->PartialPayment Payanyotheramount");

					// test =
					// extent.createTest(prop.getProperty("QC_Store_AgeStore_Deposit_Menu_Txn_file_name"));

					FileName = prop.getProperty("QC_ILP_Payanyotheramount_Txn_file_name") + ".xls";

					TestData = new ExcelNew(System.getProperty("user.dir")
							+ prop.getProperty("QC_Store_NewLoan_Test_data_sheet_path") + FileName);

					int lastrow = TestData.getLastRow("Start");
					String sheetName = "Start";

					System.out.println(lastrow);
					for (int row = 2; row <= lastrow; row++) {

						String RunFlag = TestData.getCellData(sheetName, "Run", row);
						// System.out.println(RunFlag);
						
						if (RunFlag.equals("Y")) {

							// String AppURL = TestData.getCellData(sheetName, "AppURL",
							// row);
							String SSN = TestData.getCellData(sheetName, "SSN", row);

							System.out.println(AppURL);

							ACSRLoginLogout.login(SSN, AppURL);
							ACSRBorrowerRegistration.borrowerReg(SSN, AppURL);
							ACSRLoginLogout.logout();
							
							ACSRLoginLogout.login(SSN, AppURL);
							QCCSRNewLoan.newLoan(SSN, AppURL);
							ACSRLoginLogout.logout();
							
							QCCSRLoginLogout.login(SSN, AppURL);
							AQCAgeStoreDueDate.ageStoreDueDate(SSN, SSN);				
							QCCSRLoginLogout.login(SSN, AppURL);
							QCPayment.payment(SSN, AppURL);
							QCCSRLoginLogout.logout(SSN, AppURL);
							
						
						}
					}
				}

				catch (Exception e) {
					System.out.println("QC_Installmentpayment_Void_Txn");
					// test.log(LogStatus.ERROR, MarkupHelper.createLabel("Unable to
					// start scenario 1 " , ExtentColor.RED));
					test.log(LogStatus.ERROR, "Unable to start scenario QC_Installmentpayment_Void_Txn ");

				}
			}
			
			
//@Test(priority = 12 )

			public static void QC_PayAnyotherAmount_Void_Txn() throws Exception {
				try {
					test = reports.startTest((prop.getProperty("QC_ILP_Payanyotheramount_Void_Txn_Scenario")),
							"Login->Borrower Registratino->New Loan->AgeStore->PartialPayment Payanyotheramount-> Void");

					// test =
					// extent.createTest(prop.getProperty("QC_Store_AgeStore_Deposit_Menu_Txn_file_name"));

					FileName = prop.getProperty("QC_ILP_Payanyotheramount_Void_Txn_file_name") + ".xls";

					TestData = new ExcelNew(System.getProperty("user.dir")
							+ prop.getProperty("QC_Store_NewLoan_Test_data_sheet_path") + FileName);

					int lastrow = TestData.getLastRow("Start");
					String sheetName = "Start";

					System.out.println(lastrow);
					for (int row = 2; row <= lastrow; row++) {

						String RunFlag = TestData.getCellData(sheetName, "Run", row);
						// System.out.println(RunFlag);
						if (RunFlag.equals("Y")) {

							// AppURL = TestData.getCellData(sheetName, "AppURL", row);
							String SSN = TestData.getCellData(sheetName, "SSN", row);

							System.out.println(AppURL);

							ACSRLoginLogout.login(SSN, AppURL);
							ACSRBorrowerRegistration.borrowerReg(SSN, AppURL);
							ACSRLoginLogout.logout();
							
							ACSRLoginLogout.login(SSN, AppURL);
							QCCSRNewLoan.newLoan(SSN, AppURL);
							ACSRLoginLogout.logout();
							
							QCCSRLoginLogout.login(SSN, AppURL);
							AQCAgeStoreDueDate.ageStoreDueDate(SSN, SSN);
							
							
							QCCSRLoginLogout.login(SSN, AppURL);
							QCPayment.payment(SSN, AppURL);
							QCCSRLoginLogout.logout(SSN, AppURL);
							
							QCCSRLoginLogout.login(SSN, AppURL);
							QCVoid.qcVoid(SSN, AppURL);
							QCCSRLoginLogout.logout(SSN, AppURL);
							
							}
					}
				}

				catch (Exception e) {
					System.out.println("QC_Installmentpayment_Void_Txn");
					// test.log(LogStatus.ERROR, MarkupHelper.createLabel("Unable to
					// start scenario 1 " , ExtentColor.RED));
					test.log(LogStatus.ERROR, "Unable to start scenario QC_Installmentpayment_Void_Txn ");

				}
			}


		
//@Test(priority = 13 )

			public void QC_InstallPayment_Ref_Rescind_Txn() throws Exception {

				FileName = "QC_InstallPayment_Ref_Rescind_Txn.xls";
				test = reports.startTest("QC_InstallPayment_Ref_Rescind",
						
						"QC_InstallPayment_Ref_Rescind_Txn");

				TestData = new ExcelNew(
						System.getProperty("user.dir") + Aprop.getProperty("QC_Store_NewLoan_Test_data_sheet_path") + FileName);
				String sheetName = "Start";
				int lastrow = TestData.getLastRow("Start");

				for (int row = 2; row <= lastrow; row++) {
					String RunFlag = TestData.getCellData(sheetName, "Run", row);

					if (RunFlag.equals("Y")) {
						String AppURL = TestData.getCellData(sheetName, "AppURL", row);
						String SSN = TestData.getCellData(sheetName, "SSN", row);  
						
						
						ACSRLoginLogout.login(SSN, AppURL);
						ACSRBorrowerRegistration.borrowerReg(SSN, AppURL);
						ACSRLoginLogout.logout();
						
						
						ACSRLoginLogout.login(SSN, AppURL);						
						QCCSRNewLoan.newLoan(SSN, AppURL);
						ACSRLoginLogout.logout();
						
						ACSRLoginLogout.login(SSN, AppURL);
						AQCAgeStoreDueDate.ageStoreDueDate(SSN, AppURL);
					
						ACSRLoginLogout.login(SSN, AppURL);
					    //ILP_Payment.payment(SSN, AppURL);
						QCPayment.payment(SSN, AppURL);
						ACSRLoginLogout.logout();
					
					    ACSRLoginLogout.login(SSN, AppURL);
					    ILPRefinanceprocess.Refinance(SSN, AppURL);
						ACSRLoginLogout.logout();
						
					    ACSRLoginLogout.login(SSN, AppURL);
						ACSRRescindLoan.rescind(SSN, AppURL);
						ACSRLoginLogout.logout();
				

					
						
			

				/*		ACSRLoginLogout.login(SSN, AppURL);
						AQCCSRHistory.history(SSN, AppURL);
						ACSRLoginLogout.logout();*/

						
					}
				}
			}
			
	
	
    //@Test(priority = 14, enabled = true, groups = "Anoop11")

		public void ILP_ProcessClear() throws Exception {

			FileName = "ILP_ProcessClear.xls";
			test = reports.startTest("QC_Loan_deposit_ClearFromDropDown",
					"Loan -->Age the store upto duedate --> perform deposit--> age the store -->process Clear from drop Down");

			TestData = new ExcelNew(
					System.getProperty("user.dir") + Aprop.getProperty("QC_Store_NewLoan_Test_data_sheet_path") + FileName);
			String sheetName = "Start";
			int lastrow = TestData.getLastRow("Start");

			for (int row = 2; row <= lastrow; row++) {
				String RunFlag = TestData.getCellData(sheetName, "Run", row);

				if (RunFlag.equals("Y")) {
					String AppURL = TestData.getCellData(sheetName, "AppURL", row);
					String SSN = TestData.getCellData(sheetName, "SSN", row);

					
				    ACSRLoginLogout.login(SSN, AppURL);
					ACSRBorrowerRegistration.borrowerReg(SSN, AppURL);
					ACSRLoginLogout.logout();
				
					ACSRLoginLogout.login(SSN, AppURL);
					QCCSRNewLoan.newLoan(SSN, AppURL);
					ACSRLoginLogout.logout();
					
					ACSRLoginLogout.login(SSN, AppURL);
					AQCAgeStoreDueDate.ageStoreDueDate(SSN, AppURL);
				
					
					ACSRLoginLogout.login(SSN, AppURL);
					//AQCDepositDropdown.depositDropDown(SSN, AppURL);
					JQCDepositDropdown.depositDropDown(SSN, AppURL);				
					ACSRLoginLogout.logout();
					
					ACSRLoginLogout.login(SSN, AppURL);
					DBReturn.proc(SSN);			
					ACSRLoginLogout.logout();
			
									
					ACSRLoginLogout.login(SSN, AppURL);
					//ILPClearFromDropDown.clearDropdown(SSN, AppURL);
					ILPClearFromDropDown.clearDropdown(SSN, AppURL);
					ACSRLoginLogout.logout();

					ACSRLoginLogout.login(SSN, AppURL);
					AQCCSRHistory.history(SSN, AppURL);
					ACSRLoginLogout.logout();
					
				}
			}
		}

		
//@Test(priority = 15, enabled = true)

		public void ILP_RefinanceStepDown_void() throws Exception {
			FileName = "QC_ILP_StepDown_Void.xls";
			test = reports.startTest("QC_ILP_StepDown_Void","Login-->Age the Store_payment_Age store to Duedate_Refinance Step Down Void");

			TestData = new ExcelNew(System.getProperty("user.dir") + Aprop.getProperty("QC_Store_NewLoan_Test_data_sheet_path") + FileName);
			String sheetName = "Start";
			System.out.println(System.getProperty("user.dir")+ Aprop.getProperty("QC_Store_NewLoan_Test_data_sheet_path") + FileName);
			int lastrow = TestData.getLastRow("Start");

			for (int row = 2; row <= lastrow; row++) {
				String RunFlag = TestData.getCellData(sheetName, "Run", row);

				if (RunFlag.equals("Y")) {
					String AppURL = TestData.getCellData(sheetName, "AppURL", row);
					String SSN = TestData.getCellData(sheetName, "SSN", row);

					
					ACSRLoginLogout.login(SSN, AppURL);
					ACSRBorrowerRegistration.borrowerReg(SSN, AppURL);
					ACSRLoginLogout.logout();
					
					ACSRLoginLogout.login(SSN, AppURL);
					QCCSRNewLoan.newLoan(SSN, AppURL);
					ACSRLoginLogout.logout();
					
					ACSRLoginLogout.login(SSN, AppURL);
					ACSRRescindLoan.agerescind(SSN, AppURL);

					ACSRLoginLogout.login(SSN, AppURL);
					QCPayment.payment(SSN, AppURL);
					ACSRLoginLogout.logout();

					ACSRLoginLogout.login(SSN, AppURL);
					ILP_Refinance_StepDown.StepDown(SSN, AppURL);
					ACSRLoginLogout.logout();
					
					ACSRLoginLogout.login(SSN, AppURL);
					AQCVoid.qcVoid(SSN, AppURL);
					ACSRLoginLogout.logout();

	/*				ACSRLoginLogout.login(SSN, AppURL);
					AQCCSRHistory.history(SSN, AppURL);
					ACSRLoginLogout.logout();*/

					
				}
			}
		}

	//@Test(priority = 16, enabled = true)
	
		public void ILP_RefinanceStepDown() throws Exception {

			FileName = "QC_ILP_StepDown.xls";
			test = reports.startTest("QC_ILP_StepDown",
					"Login-->Age the Store_payment_Age store to Duedate_Refinance Step Down");

			TestData = new ExcelNew(
					System.getProperty("user.dir") + Aprop.getProperty("QC_Store_NewLoan_Test_data_sheet_path") + FileName);
			String sheetName = "Start";
			int lastrow = TestData.getLastRow("Start");

			for (int row = 2; row <= lastrow; row++) {
				String RunFlag = TestData.getCellData(sheetName, "Run", row);

				if (RunFlag.equals("Y")) {
					String AppURL = TestData.getCellData(sheetName, "AppURL", row);
					String SSN = TestData.getCellData(sheetName, "SSN", row);
					


					ACSRLoginLogout.login(SSN, AppURL);
					ACSRBorrowerRegistration.borrowerReg(SSN, AppURL);
					ACSRLoginLogout.logout();
					
					ACSRLoginLogout.login(SSN, AppURL);
					QCCSRNewLoan.newLoan(SSN, AppURL);
					ACSRLoginLogout.logout();
					
					ACSRLoginLogout.login(SSN, AppURL);
					ACSRRescindLoan.agerescind(SSN, AppURL);

					ACSRLoginLogout.login(SSN, AppURL);
					QCPayment.payment(SSN, AppURL);
					ACSRLoginLogout.logout();

					ACSRLoginLogout.login(SSN, AppURL);
					ILP_Refinance_StepDown.StepDown(SSN, AppURL);
					ACSRLoginLogout.logout();

					/*ACSRLoginLogout.login(SSN, AppURL);
					AQCCSRHistory.history(SSN, AppURL);
					ACSRLoginLogout.logout();*/

					
				}
			}
		}

		
//@Test(priority = 17, enabled = true)
		public void ILP_RefinanceStepSame_Void() throws Exception {

			FileName = "QC_ILP_StepSame_Void.xls";
			test = reports.startTest("QC_Refinance_Stepsame_Void",
					"Login-->Age the Store_payment_Age store to Duedate_Refinance Step Same Void");

			TestData = new ExcelNew(
					System.getProperty("user.dir") + Aprop.getProperty("QC_Store_NewLoan_Test_data_sheet_path") + FileName);
			String sheetName = "Start";
			int lastrow = TestData.getLastRow("Start");

			for (int row = 2; row <= lastrow; row++) {
				String RunFlag = TestData.getCellData(sheetName, "Run", row);

				if (RunFlag.equals("Y")) {
					String AppURL = TestData.getCellData(sheetName, "AppURL", row);
					String SSN = TestData.getCellData(sheetName, "SSN", row);

					
					ACSRLoginLogout.login(SSN, AppURL);
					ACSRBorrowerRegistration.borrowerReg(SSN, AppURL);
					ACSRLoginLogout.logout();
					
					ACSRLoginLogout.login(SSN, AppURL);
					QCCSRNewLoan.newLoan(SSN, AppURL);
					ACSRLoginLogout.logout();
					
					ACSRLoginLogout.login(SSN, AppURL);
					ACSRRescindLoan.agerescind(SSN, AppURL);

					ACSRLoginLogout.login(SSN, AppURL);
					QCPayment.payment(SSN, AppURL);
					ACSRLoginLogout.logout();
					
				    ACSRLoginLogout.login(SSN, AppURL);					
					AQCAgeStoreDueDate.ageStoreDueDate(SSN, AppURL);
					
					
					ACSRLoginLogout.login(SSN, AppURL);					
					ILP_Refinance_StepSame.StepSame(SSN, AppURL);
					ACSRLoginLogout.logout();

					ACSRLoginLogout.login(SSN, AppURL);
					AQCVoid.qcVoid(SSN, AppURL);
					ACSRLoginLogout.logout();

					/*ACSRLoginLogout.login(SSN, AppURL);
					AQCCSRHistory.history(SSN, AppURL);
					ACSRLoginLogout.logout();*/
					
				}
			}
		}

	
	//@Test(priority = 18, enabled = true)
		public void ILP_RefinanceStepSame() throws Exception {

			FileName = "QC_ILP_StepSame.xls";
			test = reports.startTest("QC_Refinance_Stepsame",
					"Login-->Age the Store_payment_Age store to Duedate_Refinance Step Same");

			TestData = new ExcelNew(
					System.getProperty("user.dir") + Aprop.getProperty("QC_Store_NewLoan_Test_data_sheet_path") + FileName);
			String sheetName = "Start";
			int lastrow = TestData.getLastRow("Start");

			for (int row = 2; row <= lastrow; row++) {
				String RunFlag = TestData.getCellData(sheetName, "Run", row);

				if (RunFlag.equals("Y")) {
					String AppURL = TestData.getCellData(sheetName, "AppURL", row);
					String SSN = TestData.getCellData(sheetName, "SSN", row);

				 	ACSRLoginLogout.login(SSN, AppURL);
					ACSRBorrowerRegistration.borrowerReg(SSN, AppURL);
					ACSRLoginLogout.logout();
					
					ACSRLoginLogout.login(SSN, AppURL);
					QCCSRNewLoan.newLoan(SSN, AppURL);
					ACSRLoginLogout.logout();
					
					ACSRLoginLogout.login(SSN, AppURL);
					ACSRRescindLoan.agerescind(SSN, AppURL);

					ACSRLoginLogout.login(SSN, AppURL);
					QCPayment.payment(SSN, AppURL);
					ACSRLoginLogout.logout();
					
				    ACSRLoginLogout.login(SSN, AppURL);					
					AQCAgeStoreDueDate.ageStoreDueDate(SSN, AppURL);
					
					
					ACSRLoginLogout.login(SSN, AppURL);					
					ILP_Refinance_StepSame.StepSame(SSN, AppURL);
					ACSRLoginLogout.logout();

					/*ACSRLoginLogout.login(SSN, AppURL);
					AQCCSRHistory.history(SSN, AppURL);
					ACSRLoginLogout.logout();
					*/

				}
			}
		}

		
	//@Test(priority = 19, enabled = true)
	
		public void ILP_RefinanceStepUpVoid() throws Exception {

			FileName = "QC_ILP_StepupVoid.xls";
			test = reports.startTest("QC_Loan_StepUP_Void",
					"Login-->Age the Store_payment_Age store to Duedate_Refinance Step Up void");

			TestData = new ExcelNew(
					System.getProperty("user.dir") + Aprop.getProperty("QC_Store_NewLoan_Test_data_sheet_path") + FileName);
			String sheetName = "Start";
			int lastrow = TestData.getLastRow("Start");

			for (int row = 2; row <= lastrow; row++) {
				String RunFlag = TestData.getCellData(sheetName, "Run", row);

				if (RunFlag.equals("Y")) {
					String AppURL = TestData.getCellData(sheetName, "AppURL", row);
					String SSN = TestData.getCellData(sheetName, "SSN", row);

					/*ACSRLoginLogout.login(SSN, AppURL);
					ACSRBorrowerRegistration.borrowerReg(SSN, AppURL);
					ACSRLoginLogout.logout();*/
					
					ACSRLoginLogout.login(SSN, AppURL);
					QCCSRNewLoan.newLoan(SSN, AppURL);
					ACSRLoginLogout.logout();

					ACSRLoginLogout.login(SSN, AppURL);
					ACSRRescindLoan.agerescind(SSN, AppURL);

					ACSRLoginLogout.login(SSN, AppURL);
					QCPayment.payment(SSN, AppURL);
					ACSRLoginLogout.logout();

					ACSRLoginLogout.login(SSN, AppURL);
					AQCAgeStoreDueDate.ageStoreDueDate(SSN, AppURL);

					ACSRLoginLogout.login(SSN, AppURL);
					ILP_RefinanceStepup.StepUp(SSN, AppURL);
					ACSRLoginLogout.logout();


			        ACSRLoginLogout.login(SSN, AppURL);
					AQCVoid.qcVoid(SSN, AppURL);
					ACSRLoginLogout.logout();

					
				}
			}
		}

		
		//@Test(priority = 20, enabled = true)


		public void ILP_RefinanceStepup() throws Exception {

			FileName = "QC_ILP_StepUp.xls";
			test = reports.startTest("ILP_Refinance_stepUP",
					"Login-->Age the Store_payment_Age store to Duedate_Refinance Stepup");

			TestData = new ExcelNew(
					System.getProperty("user.dir") + Aprop.getProperty("QC_Store_NewLoan_Test_data_sheet_path") + FileName);
			String sheetName = "Start";
			int lastrow = TestData.getLastRow("Start");

			for (int row = 2; row <= lastrow; row++) {
				String RunFlag = TestData.getCellData(sheetName, "Run", row);

				if (RunFlag.equals("Y")) {
					String AppURL = TestData.getCellData(sheetName, "AppURL", row);
					String SSN = TestData.getCellData(sheetName, "SSN", row);

					ACSRLoginLogout.login(SSN, AppURL);
					ACSRBorrowerRegistration.borrowerReg(SSN, AppURL);
					ACSRLoginLogout.logout();
					
					ACSRLoginLogout.login(SSN, AppURL);
					QCCSRNewLoan.newLoan(SSN, AppURL);
					ACSRLoginLogout.logout();

					ACSRLoginLogout.login(SSN, AppURL);
					ACSRRescindLoan.agerescind(SSN, AppURL);

					ACSRLoginLogout.login(SSN, AppURL);
					QCPayment.payment(SSN, AppURL);
					ACSRLoginLogout.logout();

					ACSRLoginLogout.login(SSN, AppURL);
					AQCAgeStoreDueDate.ageStoreDueDate(SSN, AppURL);

					ACSRLoginLogout.login(SSN, AppURL);
					ILP_RefinanceStepup.StepUp(SSN, AppURL);
					ACSRLoginLogout.logout();
					
				

					
				
				}
			}
		}
		
	
//@Test(priority = 1, groups = "Ratikanta_ILP")
	public static void RQC_Bankruptcy_WORecovery_Txn_Testdata() throws Exception {
		try {
			test = reports.startTest(("QC_Bankruptcy_WORecovery"),

					"Login->Borrower Registration->New Loan->Bankruptcy in Admin Portal-->AgeStore to Due date-->WriteOff Recovery");
			String FileName = "QC_Bankruptcy_WORecovery_Txn_Testdata.xls";
			
			TestData = new ExcelNew(System.getProperty("user.dir")
					+ Rprop.getProperty("QC_Store_NewLoan_Test_data_sheet_path") + FileName);

			
			int lastrow = TestData.getLastRow("Start");
			String sheetName = "Start";
			// int lastrow=TestData.getLastRow("Borrower");
			System.out.println(lastrow);
			for (int row = 2; row <= lastrow; row++) {

				String RunFlag = TestData.getCellData(sheetName, "Run", row);
				// System.out.println(RunFlag);
				if (RunFlag.equals("Y")) {
					// driver.get(appUrl);
					// test.log(LogStatus.INFO, "Application is launched");
					// driver.manage().window().maximize();
					String AppURL = TestData.getCellData(sheetName, "AppURL", row);
					String SSN = TestData.getCellData(sheetName, "SSN", row);

					System.out.println(AppURL);

					ACSRLoginLogout.login(SSN, AppURL);
					ACSRBorrowerRegistration.borrowerReg(SSN, AppURL);
					ACSRLoginLogout.logout();
					
					ACSRLoginLogout.login(SSN, AppURL);
					QCCSRNewLoan.newLoan(SSN, AppURL);
					ACSRLoginLogout.logout();
					
					RQCCSRLoginLogout.login(SSN, AppURL);
					RQCCSRBankruptcy.bankruptcy(SSN, AppURL);
					RAdminLoginForBankruptcy.bankruptcy(driver, SSN, AppURL);
					
					RQCCSRLoginLogout.login(SSN, AppURL);
					RBusinessDateCapture.businessdate(SSN, AppURL);
					RQCCSRLoginLogout.logout(SSN, AppURL);
					
					QCCSRLoginLogout.adminLogin(SSN, SSN);						
					RAdminStoreDate.ageStoreGraceDays(SSN, AppURL);										
					QCCSRLoginLogout.adminLogout(driver, SSN, SSN);
					
					RQCCSRLoginLogout.login(SSN, AppURL);
					RQCCSRWriteOffRecovery.writeoffrecovery(SSN, AppURL);
					
					/*RQCCSRLoginLogout.login(SSN, AppURL);					
					RQCCSRHistory.history(SSN, AppURL);
					RQCCSRLoginLogout.logout(SSN, AppURL);*/
				
				}
			}
		}

		catch (Exception e) {
			System.out.println("under catch of Test" + e);
			String screenshotPath = getScreenhot(driver, "RQC_Bankruptcy_WORecovery_Txn");
			test.log(LogStatus.INFO, test.addScreenCapture(screenshotPath));
			test.log(LogStatus.ERROR, "Unable to start scenarios ");

		}
	}

 // //@Test(priority = 2, groups = "Ratikanta_ILP")


	public static void RQC_Bankruptcy_Discharge_Txn() throws Exception {
		try {
			test = reports.startTest(("QC_Bankruptcy_Discharge"),

					"Login->Borrower Registration->New Loan->Bankruptcy in Admin Portal-->AgeStore to Due date-->Discharge");
			String FileName = "QC_Bankruptcy_Discharge_Txn_Testdata.xls";
			// String FileName=
			// Rprop.getProperty("QC_DefaultPayment_Txn_Testdata_Scenario")+".xls";
			// ExcelNew TestData;
			TestData = new ExcelNew(System.getProperty("user.dir")
					+ Rprop.getProperty("QC_Store_NewLoan_Test_data_sheet_path") + FileName);

			
			int lastrow = TestData.getLastRow("Start");
			String sheetName = "Start";
			// int lastrow=TestData.getLastRow("Borrower");
			System.out.println(lastrow);
			for (int row = 2; row <= lastrow; row++) {

				String RunFlag = TestData.getCellData(sheetName, "Run", row);
				// System.out.println(RunFlag);
				if (RunFlag.equals("Y")) {
					// driver.get(appUrl);
					// test.log(LogStatus.INFO, "Application is launched");
					// driver.manage().window().maximize();
					String AppURL = TestData.getCellData(sheetName, "AppURL", row);
					String SSN = TestData.getCellData(sheetName, "SSN", row);

					System.out.println(AppURL);
					
					ACSRLoginLogout.login(SSN, AppURL);
					ACSRBorrowerRegistration.borrowerReg(SSN, AppURL);
					ACSRLoginLogout.logout();
					
					ACSRLoginLogout.login(SSN, AppURL);
					QCCSRNewLoan.newLoan(SSN, AppURL);
					ACSRLoginLogout.logout();
					
					RQCCSRLoginLogout.login(SSN, AppURL);
					RQCCSRBankruptcy.bankruptcy(SSN, AppURL);
					RAdminLoginForBankruptcy.bankruptcy(driver, SSN, AppURL);
					
					RQCCSRLoginLogout.login(SSN, AppURL);
					RBusinessDateCapture.businessdate(SSN, AppURL);
					RQCCSRLoginLogout.logout(SSN, AppURL);
					
					QCCSRLoginLogout.adminLogin(SSN, SSN);						
					RAdminStoreDate.ageStoreGraceDays(SSN, AppURL);										
					QCCSRLoginLogout.adminLogout(driver, SSN, SSN);
														
					RQCCSRLoginLogout.login(SSN, AppURL);
					RQCCSRBankruptcy.bankruptcy(SSN, AppURL);
					RAdminLoginForDischarged.discharged(driver, SSN, AppURL);
					
					
					
					
				}
			}
		}

		catch (Exception e) {
			System.out.println("under catch of Test" + e);
			String screenshotPath = getScreenhot(driver, "RQC_Bankruptcy_Discharge_Txn");
			test.log(LogStatus.INFO, test.addScreenCapture(screenshotPath));
			test.log(LogStatus.ERROR, "Unable to start scenarios ");

		}
	}

	////@Test(priority = 3, groups = "Ratikanta_ILP")

	public static void RQC_Bankruptcy_Dismiss_Txn_Testdata() throws Exception {
		try {
			test = reports.startTest(("QC_Bankruptcy_Dismiss"),

					"Login->Borrower Registration->New Loan->Bankruptcy in Admin Portal-->AgeStore to Due date-->Dismiss");
			String FileName = "QC_Bankruptcy_Dismiss_Txn_Testdata.xls";
			// String FileName=
			// Rprop.getProperty("QC_DefaultPayment_Txn_Testdata_Scenario")+".xls";
			// ExcelNew TestData;
			TestData = new ExcelNew(System.getProperty("user.dir")
					+ Rprop.getProperty("QC_Store_NewLoan_Test_data_sheet_path") + FileName);

			// test=reports.startTest("Lend Nation Smoke_testing","Lend Nation
			// Application test report");

			// String FileName= "Registration_Lend_Nation.xls";
			// Excel TestData = new
			// ExcelNew(System.getProperty("user.dir")+"/TestData/LendNation/"+FileName);
			// int lastrow=TestData.getLastRow("Start");
			int lastrow = TestData.getLastRow("Start");
			String sheetName = "Start";
			// int lastrow=TestData.getLastRow("Borrower");
			System.out.println(lastrow);
			for (int row = 2; row <= lastrow; row++) {

				String RunFlag = TestData.getCellData(sheetName, "Run", row);
				// System.out.println(RunFlag);
				if (RunFlag.equals("Y")) {
					// driver.get(appUrl);
					// test.log(LogStatus.INFO, "Application is launched");
					// driver.manage().window().maximize();
					String AppURL = TestData.getCellData(sheetName, "AppURL", row);
					String SSN = TestData.getCellData(sheetName, "SSN", row);
					
					System.out.println(AppURL);
					
				    ACSRLoginLogout.login(SSN, AppURL);
					ACSRBorrowerRegistration.borrowerReg(SSN, AppURL);
					ACSRLoginLogout.logout();
					
					ACSRLoginLogout.login(SSN, AppURL);
					QCCSRNewLoan.newLoan(SSN, AppURL);
					ACSRLoginLogout.logout();
					
					
					RQCCSRLoginLogout.login(SSN, AppURL);
					RQCCSRBankruptcy.bankruptcy(SSN, AppURL);
					RAdminLoginForBankruptcy.bankruptcy(driver, SSN, AppURL);
					
					RQCCSRLoginLogout.login(SSN, AppURL);
					RBusinessDateCapture.businessdate(SSN, AppURL);
					RQCCSRLoginLogout.logout(SSN, AppURL);
					
					QCCSRLoginLogout.adminLogin(SSN, SSN);						
					RAdminStoreDate.ageStoreGraceDays(SSN, AppURL);										
					QCCSRLoginLogout.adminLogout(driver, SSN, SSN);
					
					RQCCSRLoginLogout.login(SSN, AppURL);
					RQCCSRBankruptcy.bankruptcy(SSN, AppURL);
					RAdminLoginForDismiss.dismiss(driver, SSN, AppURL);
			
				
				}
			}
		}

		catch (Exception e) {
			System.out.println("under catch of Test" + e);
			String screenshotPath = getScreenhot(driver, "RQC_Bankruptcy_Dismiss_Txn");
			test.log(LogStatus.INFO, test.addScreenCapture(screenshotPath));
			test.log(LogStatus.ERROR, "Unable to start scenarios ");

		}
	}

	
  // //@Test(priority = 4, groups = "Ratikanta_ILP")

	public static void RQC_WriteOffRecovery_Txn_Testdata() throws Exception {
		try {
			test = reports.startTest(("QC_WriteOffRecovery"),

					"Login->Borrower Registration->New Loan->Age the Store Duedate-->WriteOff-->WriteOff Recovery");
			String FileName = "QC_WriteOffRecovery_Txn_Testdata.xls";
			// String FileName=
			// Rprop.getProperty("QC_DefaultPayment_Txn_Testdata_Scenario")+".xls";
			// ExcelNew TestData;
			TestData = new ExcelNew(System.getProperty("user.dir")
					+ Rprop.getProperty("QC_Store_NewLoan_Test_data_sheet_path") + FileName);

			// test=reports.startTest("Lend Nation Smoke_testing","Lend Nation
			// Application test report");

			// String FileName= "Registration_Lend_Nation.xls";
			// Excel TestData = new
			// ExcelNew(System.getProperty("user.dir")+"/TestData/LendNation/"+FileName);
			// int lastrow=TestData.getLastRow("Start");
			int lastrow = TestData.getLastRow("Start");
			String sheetName = "Start";
			// int lastrow=TestData.getLastRow("Borrower");
			System.out.println(lastrow);
			for (int row = 2; row <= lastrow; row++) {

				String RunFlag = TestData.getCellData(sheetName, "Run", row);
				// System.out.println(RunFlag);
				if (RunFlag.equals("Y")) {
					// driver.get(appUrl);
					// test.log(LogStatus.INFO, "Application is launched");
					// driver.manage().window().maximize();
					//String AppURL = TestData.getCellData(sheetName, "AppURL", row);
					String SSN = TestData.getCellData(sheetName, "SSN", row);

					System.out.println(AppURL);
					
					/*RQCCSRLoginLogout.login(SSN, AppURL);
					RQCBorrowerRegistration.borrowerRegistration(SSN, AppURL);
					RQCCSRLoginLogout.logout(SSN, AppURL);
					
					RQCCSRLoginLogout.login(SSN, AppURL);
					RQCCSRNewLoan.newLoan(SSN, AppURL);
					RQCCSRLoginLogout.logout(SSN, AppURL);	
					
				//===== To Due Date =======
					
					RQCCSRLoginLogout.login(SSN, AppURL);
					RBusinessDateCapture.duedate(SSN, AppURL);
					RQCCSRLoginLogout.logout(SSN, AppURL);
					

					QCCSRLoginLogout.adminLogin(SSN, SSN);						
					RAdminStoreDate.ageStoreDueDate(SSN, AppURL);									
					QCCSRLoginLogout.adminLogout(driver, SSN, SSN);
					
				 //===== 91 days grace days =======
					
					RQCCSRLoginLogout.login(SSN, AppURL);
					RBusinessDateCapture.businessdate(SSN, AppURL);
					RQCCSRLoginLogout.logout(SSN, AppURL);
									
					QCCSRLoginLogout.adminLogin(SSN, SSN);						
					RAdminStoreDate.ageStoreGraceDays(SSN, AppURL);										
					QCCSRLoginLogout.adminLogout(driver, SSN, SSN);
					
				//===== To Month's End Date =======
					
					RQCCSRLoginLogout.login(SSN, AppURL);
					RBusinessDateCapture.businessdate(SSN, AppURL);
					RQCCSRLoginLogout.logout(SSN, AppURL);
					
					QCCSRLoginLogout.adminLogin(SSN, SSN);						
					RAdminStoreDate.ageStoreToMonthEndDate(SSN, AppURL);										
					QCCSRLoginLogout.adminLogout(driver, SSN, SSN);*/
					
					RQCCSRLoginLogout.login(SSN, AppURL);
					RBusinessDateCapture.businessdate(SSN, AppURL);
					RQCCSRLoginLogout.logout(SSN, AppURL);
					
					//RWriteoffQuery.proc();
					Thread.sleep(8000);
					
					
					
					/*RQCCSRLoginLogout.login(SSN, AppURL);
					RQCCSRWORecovery.simplewriteoff_recovery(SSN, AppURL);
					
					RQCCSRLoginLogout.login(SSN, AppURL);
					RQCCSRWriteOffRecovery.writeoffrecovery(SSN, AppURL);*/
					
					/*RQCCSRLoginLogout.login(SSN, AppURL);
					RQCCSRHistory.history(SSN, AppURL);
					ACSRLoginLogout.login(SSN, AppURL);*/
				//==================	
					/*RQCCSRLoginLogout.login(SSN, AppURL);
					RQCBorrowerRegistration.borrowerRegistration(SSN, AppURL);
					ACSRLoginLogout.login(SSN, AppURL);
					RQCCSRLoginLogout.login(SSN, AppURL);
					QCCSRNewLoan.newLoan(SSN, AppURL);
					ACSRLoginLogout.login(SSN, AppURL);
					RQCCSRLoginLogout.login(SSN, AppURL);
					RQCAgeStoreDueDate.ageStoreDueDate(SSN, AppURL);
					RQCCSRLoginLogout.login(SSN, AppURL);
					RQCCSRWORecovery.simplewriteoff_recovery(SSN, AppURL);
					RQCCSRLoginLogout.login(SSN, AppURL);
					RQCCSRWriteOffRecovery.writeoffrecovery(SSN, AppURL);
					RQCCSRLoginLogout.login(SSN, AppURL);
					RQCCSRHistory.history(SSN, AppURL);
					ACSRLoginLogout.login(SSN, AppURL);*/

				}
			}
		}

		catch (Exception e) {
			System.out.println("under catch of Test" + e);
			String screenshotPath = getScreenhot(driver, "RQC_WriteOffRecovery_Txn");
			test.log(LogStatus.INFO, test.addScreenCapture(screenshotPath));
			test.log(LogStatus.ERROR, "Unable to start scenarios ");

		}
	}

////@Test(priority = 5, groups = "Ratikanta_ILP")

	public static void RQC_WriteOffRecovery_Void_Txn_Testdata() throws Exception {
		try {
			test = reports.startTest(("QC_WriteOffRecovery_Void"),

					"Login->Borrower Registration->New Loan->Age the Store Duedate-->WriteOff-->WriteOff Recovery-->Void");
			String FileName = ".xls";
			// String FileName=
			// Rprop.getProperty("QC_DefaultPayment_Txn_Testdata_Scenario")+".xls";
			// ExcelNew TestData;
			TestData = new ExcelNew(System.getProperty("user.dir")+ Rprop.getProperty("QC_Store_NewLoan_Test_data_sheet_path") + FileName);

			// test=reports.startTest("Lend Nation Smoke_testing","Lend Nation
			// Application test report");

			// String FileName= "Registration_Lend_Nation.xls";
			// Excel TestData = new
			// ExcelNew(System.getProperty("user.dir")+"/TestData/LendNation/"+FileName);
			// int lastrow=TestData.getLastRow("Start");
			int lastrow = TestData.getLastRow("Start");
			String sheetName = "Start";
			// int lastrow=TestData.getLastRow("Borrower");
			System.out.println(lastrow);
			for (int row = 2; row <= lastrow; row++) {

				String RunFlag = TestData.getCellData(sheetName, "Run", row);
				// System.out.println(RunFlag);
				if (RunFlag.equals("Y")) {
					// driver.get(appUrl);
					// test.log(LogStatus.INFO, "Application is launched");
					// driver.manage().window().maximize();
					String AppURL = TestData.getCellData(sheetName, "AppURL", row);
					String SSN = TestData.getCellData(sheetName, "SSN", row);

					System.out.println(AppURL);
					RQCCSRLoginLogout.login(SSN, AppURL);
					RQCBorrowerRegistration.borrowerRegistration(SSN, AppURL);
					ACSRLoginLogout.login(SSN, AppURL);
					RQCCSRLoginLogout.login(SSN, AppURL);
					QCCSRNewLoan.newLoan(SSN, AppURL);
					ACSRLoginLogout.login(SSN, AppURL);
					RQCCSRLoginLogout.login(SSN, AppURL);
					RQCAgeStoreDueDate.ageStoreDueDate(SSN, AppURL);
					RQCCSRLoginLogout.login(SSN, AppURL);
					RQCCSRWORecovery.simplewriteoff_recovery(SSN, AppURL);
					RQCCSRLoginLogout.login(SSN, AppURL);
					RQCCSRWriteOffRecovery.writeoffrecovery(SSN, AppURL);
					RQCCSRLoginLogout.login(SSN, AppURL);
					RQCCSRWOVoid.writeoffvoid(SSN, AppURL);
					RQCCSRLoginLogout.login(SSN, AppURL);
					RQCCSRHistory.history(SSN, AppURL);
					ACSRLoginLogout.login(SSN, AppURL);
				}
			}
		}

		catch (Exception e) {
			System.out.println("under catch of Test" + e);
			String screenshotPath = getScreenhot(driver, "RQC_WriteOffRecovery_Void_Txn");
			test.log(LogStatus.INFO, test.addScreenCapture(screenshotPath));
			test.log(LogStatus.ERROR, "Unable to start scenarios ");

		}
	}

//@Test(priority = 8, enabled = true, groups = "Janaki")

		public static void RKZ_ILP_ACHReturn_Txn() throws Exception {
		
				test = reports.startTest("RKZ_ILP_Return_Txn","Login->Borrower Registratino->New Loan ->Age Store->ACH Return");

				FileName = "RKZ_ILP_ACHReturn_Txn"+ ".xls";

				TestData = new ExcelNew(System.getProperty("user.dir")+ Jprop.getProperty("QC_Store_NewLoan_Test_data_sheet_path") + FileName);

				int lastrow = TestData.getLastRow("Start");
				String sheetName = "Start";
				System.out.println(lastrow);
				for (int row = 2; row <= lastrow; row++) {

					String RunFlag = TestData.getCellData(sheetName, "Run", row);
					if (RunFlag.equals("Y")) {
						String AppURL = TestData.getCellData(sheetName, "AppURL", row);
						String SSN = TestData.getCellData(sheetName, "SSN", row);
						

						ACSRLoginLogout.login(SSN, AppURL);
						ACSRBorrowerRegistration.borrowerReg(SSN, AppURL);
						ACSRLoginLogout.logout();
						
						ACSRLoginLogout.login(SSN, AppURL);
						QCCSRNewLoan.newLoan(SSN, AppURL);
						ACSRLoginLogout.logout();
						
						ACSRLoginLogout.login(SSN, AppURL);
						Age_Gracedays.Age_Gracedays(SSN, SSN, 0);
						ACSRLoginLogout.login(SSN, AppURL);
						
						JQCDepositDropdown.depositDropDown(SSN, AppURL);
						ACSRLoginLogout.logout();
					
						ACSRLoginLogout.login(SSN, AppURL);
						ACHReturn.ACHReturn(SSN, SSN, 0);
					
						QCReturnCheck.qcRturn(SSN, AppURL);
						AQCAdminLoginLogout.logout(SSN, AppURL);
						
				

					}
				}
				}

//@Test(priority = 13)

	public static void QC_LessInterest_VoidFullPayment_Txn () throws Exception {
		try
		
		{
			test = reports.startTest((prop.getProperty("QC_LessInterest_VoidFullPayment_Txn_Scenario")),
					"Login->Borrower Registration->New Loan->AgeStore to 12 days -> Less Than Interest Amount ->AgeStore to 12 days ->PartialPayment Pay Off the balance->Void");

			// test =
			// extent.createTest(prop.getProperty("QC_Store_AgeStore_Deposit_Menu_Txn_file_name"));

			FileName = prop.getProperty("QC_LessInterest_VoidFullPayment_Txn_file_name") + ".xls";

			TestData = new ExcelNew(System.getProperty("user.dir")
					+ prop.getProperty("QC_Store_NewLoan_Test_data_sheet_path") + FileName);

			int lastrow = TestData.getLastRow("Start");
			String sheetName = "Start";

			System.out.println(lastrow);
			for (int row = 2; row <= lastrow; row++) {

				String RunFlag = TestData.getCellData(sheetName, "Run", row);
				// System.out.println(RunFlag);
				if (RunFlag.equals("Y")) {

					// AppURL = TestData.getCellData(sheetName, "AppURL", row);
					String SSN = TestData.getCellData(sheetName, "SSN", row);

					ACSRLoginLogout.login(SSN, AppURL);
					ACSRBorrowerRegistration.borrowerReg(SSN, AppURL);
					ACSRLoginLogout.logout();
					
					ACSRLoginLogout.login(SSN, AppURL);
					QCCSRNewLoan.newLoan(SSN, AppURL);
					ACSRLoginLogout.logout();

				    QCCSRLoginLogout.login(SSN, AppURL);
					//QCAgeStoreGraceDays.ageStoreGraceDays(SSN, SSN);
					Age_Gracedays.Age_Gracedays(SSN, SSN, 12);					
					QCCSRLoginLogout.login(SSN, AppURL);
					
					QCPayment.payment(SSN, AppURL);
					QCCSRLoginLogout.logout(SSN, AppURL);
					
					QCCSRLoginLogout.login(SSN, AppURL);
					//QCAgeStoreGraceDays.ageStoreGraceDays(SSN, SSN);
					Age_Gracedays.Age_Gracedays(SSN, SSN, 24);
					QCCSRLoginLogout.logout(SSN, AppURL);
				
					QCCSRLoginLogout.login(SSN, AppURL);
					QCPayment.paymentTwice(SSN, SSN);
					QCCSRLoginLogout.logout(SSN, AppURL);
					
					QCCSRLoginLogout.login(SSN, AppURL);
					QCVoid.qcVoid(SSN, SSN);
					QCCSRLoginLogout.logout(SSN, AppURL);
					
					/*QCCSRLoginLogout.login(SSN, AppURL);					
					QCCSRHistory.history(SSN, AppURL);
					QCCSRLoginLogout.logout(SSN, AppURL);*/

				}
			}
		}

		catch (Exception e) {
			System.out.println("QC_LessInterest_VoidFullPayment_Txn");
			// test.log(LogStatus.ERROR, MarkupHelper.createLabel("Unable to
			// start scenario 1 " , ExtentColor.RED));
			test.log(LogStatus.ERROR, "Unable to start scenario QC_LessInterest_VoidFullPayment_Txn ");

		}
	}

	
////@Test(priority = 11, groups = "Brahmith")

//@Test(priority = 14)
	
	public static void QC_ILP_Buyback_Txn() throws Exception {
		try {
			test = reports.startTest((prop.getProperty("QC_ILP_Buyback_Txn_scenario")),
					"Login->Borrower Registratino->New Loan->AgeStore->Pay Off the balance");

			// test =
			// extent.createTest(prop.getProperty("QC_Store_AgeStore_Deposit_Menu_Txn_file_name"));

			FileName = prop.getProperty("QC_ILP_Buyback_Txn_file_name") + ".xls";

			TestData = new ExcelNew(System.getProperty("user.dir")
					+ prop.getProperty("QC_Store_NewLoan_Test_data_sheet_path") + FileName);

			int lastrow = TestData.getLastRow("Start");
			String sheetName = "Start";

			System.out.println(lastrow);
			for (int row = 2; row <= lastrow; row++) {

				String RunFlag = TestData.getCellData(sheetName, "Run", row);
				// System.out.println(RunFlag);
				if (RunFlag.equals("Y")) {

					// AppURL = TestData.getCellData(sheetName, "AppURL", row);
					String SSN = TestData.getCellData(sheetName, "SSN", row);
					System.out.println(AppURL);

					ACSRLoginLogout.login(SSN, AppURL);
					ACSRBorrowerRegistration.borrowerReg(SSN, AppURL);
					ACSRLoginLogout.logout();
					
					ACSRLoginLogout.login(SSN, AppURL);
					QCCSRNewLoan.newLoan(SSN, AppURL);
					ACSRLoginLogout.logout();
					
					QCCSRLoginLogout.login(SSN, AppURL);
					AQCAgeStoreDueDate.ageStoreDueDate(SSN, SSN);
					
					
					QCCSRLoginLogout.login(SSN, AppURL);
					QCPayment.payment(SSN, AppURL);
					QCCSRLoginLogout.logout(SSN, AppURL);
				
				}
			}
		}

		catch (Exception e) {
			System.out.println("QC_Installmentpayment_Void_Txn");
			// test.log(LogStatus.ERROR, MarkupHelper.createLabel("Unable to
			// start scenario 1 " , ExtentColor.RED));
			test.log(LogStatus.ERROR, "Unable to start scenario QC_Installmentpayment_Void_Txn ");

		}
	}

	////@Test(priority = 12, groups = "Brahmith")
	
	
	//@Test(priority = 15)
	public static void QC_ILP_Buyback_Void_Txn() throws Exception {
		try {
			test = reports.startTest((prop.getProperty("QC_ILP_Buyback_Void_Txn_scenario")),
					"Login->Borrower Registratino->New Loan->AgeStore->Payment Pay Off the balance-> Void");

			// test =
			// extent.createTest(prop.getProperty("QC_Store_AgeStore_Deposit_Menu_Txn_file_name"));

			FileName = prop.getProperty("QC_ILP_Buyback_Void_Txn_file_name") + ".xls";

			TestData = new ExcelNew(System.getProperty("user.dir")
					+ prop.getProperty("QC_Store_NewLoan_Test_data_sheet_path") + FileName);

			int lastrow = TestData.getLastRow("Start");
			String sheetName = "Start";

			System.out.println(lastrow);
			for (int row = 2; row <= lastrow; row++) {

				String RunFlag = TestData.getCellData(sheetName, "Run", row);
				// System.out.println(RunFlag);
				if (RunFlag.equals("Y")) {

					// AppURL = TestData.getCellData(sheetName, "AppURL", row);
					String SSN = TestData.getCellData(sheetName, "SSN", row);

					System.out.println(AppURL);

					ACSRLoginLogout.login(SSN, AppURL);
					ACSRBorrowerRegistration.borrowerReg(SSN, AppURL);
					ACSRLoginLogout.logout();
					
					ACSRLoginLogout.login(SSN, AppURL);
					QCCSRNewLoan.newLoan(SSN, AppURL);
					ACSRLoginLogout.logout();
					
					QCCSRLoginLogout.login(SSN, AppURL);
					AQCAgeStoreDueDate.ageStoreDueDate(SSN, SSN);
					
					QCCSRLoginLogout.login(SSN, AppURL);
					QCPayment.payment(SSN, AppURL);
					QCCSRLoginLogout.logout(SSN, AppURL);
					
					
					QCCSRLoginLogout.login(SSN, AppURL);
					QCVoid.qcVoid(SSN, AppURL);
					QCCSRLoginLogout.logout(SSN, AppURL);
					
				
				}
			}
		}

		catch (Exception e) {
			System.out.println("QC_Installmentpayment_Void_Txn");
			// test.log(LogStatus.ERROR, MarkupHelper.createLabel("Unable to
			// start scenario 1 " , ExtentColor.RED));
			test.log(LogStatus.ERROR, "Unable to start scenario QC_Installmentpayment_Void_Txn ");

		}
	}

//@Test(priority = 16, enabled = true)
	public static void RKZ_ILP_Deposit_Menu_Txn() throws Exception {
		
			test = reports.startTest("RKZ_ILP_Deposit_Menu_Txn","Login->Borrower Registratino->New Loan ->Age Store->Deposit Menu");

			FileName = "QC_ILP_Deposit_Menu_Txn" + ".xls";

			TestData = new ExcelNew(System.getProperty("user.dir")
					+ Jprop.getProperty("QC_Store_NewLoan_Test_data_sheet_path") + FileName);

			int lastrow = TestData.getLastRow("Start");
			String sheetName = "Start";
			for (int row = 2; row <= lastrow; row++) {

				String RunFlag = TestData.getCellData(sheetName, "Run", row);
				if (RunFlag.equals("Y")) {
					
					String SSN = TestData.getCellData(sheetName, "SSN", row);

					ACSRLoginLogout.login(SSN, AppURL);
					ACSRBorrowerRegistration.borrowerReg(SSN, AppURL);
					ACSRLoginLogout.logout();
					
					ACSRLoginLogout.login(SSN, AppURL);
					QCCSRNewLoan.newLoan(SSN, AppURL);
					ACSRLoginLogout.logout();
					
					ACSRLoginLogout.login(SSN, AppURL);
					Age_Gracedays.Age_Gracedays(SSN, SSN, 0);
					
					ACSRLoginLogout.login(SSN, AppURL);
					JQCDepositMenu.depositMenu(SSN, AppURL);
					ACSRLoginLogout.logout();
					
				
				}
			}
			}


//@Test(priority = 17, enabled = true)

	public static void RKZ_ILP_Deposit_dropdown_Txn() throws Exception {
	
			test = reports.startTest("RKZ_ILP_Deposit_dropdown_Txn","Login->Borrower Registratino->New Loan ->Age Store->Deposit Dropdown");

			FileName = "RKZ_ILP_Deposit_dropdown_Txn"+ ".xls";

			TestData = new ExcelNew(System.getProperty("user.dir")+ Jprop.getProperty("QC_Store_NewLoan_Test_data_sheet_path") + FileName);

			int lastrow = TestData.getLastRow("Start");
			String sheetName = "Start";
			System.out.println(lastrow);
			for (int row = 2; row <= lastrow; row++) {

				String RunFlag = TestData.getCellData(sheetName, "Run", row);
				if (RunFlag.equals("Y")) {
					String AppURL = TestData.getCellData(sheetName, "AppURL", row);
					String SSN = TestData.getCellData(sheetName, "SSN", row);
					

					ACSRLoginLogout.login(SSN, AppURL);
					ACSRBorrowerRegistration.borrowerReg(SSN, AppURL);
					ACSRLoginLogout.logout();
					
					ACSRLoginLogout.login(SSN, AppURL);
					QCCSRNewLoan.newLoan(SSN, AppURL);
					ACSRLoginLogout.logout();
					
					ACSRLoginLogout.login(SSN, AppURL);
					Age_Gracedays.Age_Gracedays(SSN, SSN, 0);
					ACSRLoginLogout.login(SSN, AppURL);
					
					JQCDepositDropdown.depositDropDown(SSN, AppURL);
					ACSRLoginLogout.logout();
				
					}
			}
			}


//@Test(priority = 18, enabled = true)

	public static void RKZ_ILP_GraceDays_Deposit_Menu_Txn() throws Exception {
	
			test = reports.startTest("RKZ_ILP_GraceDays_Deposit_Menu_Txn",
					"Login->Borrower Registratino->New Loan ->Age Store->Deposit Menu");

			FileName ="QC_ILP_GraceDays_Deposit_Menu_Txn" + ".xls";

			TestData = new ExcelNew(System.getProperty("user.dir")+ Jprop.getProperty("QC_Store_NewLoan_Test_data_sheet_path") + FileName);

			int lastrow = TestData.getLastRow("Start");
			String sheetName = "Start";
			System.out.println(lastrow);
			for (int row = 2; row <= lastrow; row++) {

				String RunFlag = TestData.getCellData(sheetName, "Run", row);
				if (RunFlag.equals("Y")) {
					String AppURL = TestData.getCellData(sheetName, "AppURL", row);
					String SSN = TestData.getCellData(sheetName, "SSN", row);
					System.out.println(AppURL);

					ACSRLoginLogout.login(SSN, AppURL);
					ACSRBorrowerRegistration.borrowerReg(SSN, AppURL);
					ACSRLoginLogout.logout();
					
					ACSRLoginLogout.login(SSN, AppURL);
					QCCSRNewLoan.newLoan(SSN, AppURL);
					ACSRLoginLogout.logout();
					
					ACSRLoginLogout.login(SSN, AppURL);
					Age_Gracedays.Age_Gracedays(SSN, SSN, 5);
					ACSRLoginLogout.login(SSN, AppURL);
					
					/*ACSRRescindLoan.agerescind(SSN, AppURL);
					ACSRLoginLogout.login(SSN, AppURL);
					*/
					
					JQCDepositMenu.depositMenu(SSN, AppURL);
					ACSRLoginLogout.logout();
					
			

				}
			}
			}


//@Test(priority = 19, enabled = true)

		public static void RKZ_ILP_GraceDays_Deposit_dropdown_Txn() throws Exception {
			
				test = reports.startTest("QC_ILP_GraceDays_Deposit_dropdown_Txn","Login->Borrower Registratino->New Loan with Promotion->Age Store->Deposit Dropdown");

				FileName ="QC_ILP_GraceDays_Deposit_dropdown_Txn" + ".xls";

				TestData = new ExcelNew(System.getProperty("user.dir")
						+ Jprop.getProperty("QC_Store_NewLoan_Test_data_sheet_path") + FileName);

				int lastrow = TestData.getLastRow("Start");
				String sheetName = "Start";
				System.out.println(lastrow);
				for (int row = 2; row <= lastrow; row++) {

					String RunFlag = TestData.getCellData(sheetName, "Run", row);
					if (RunFlag.equals("Y")) {
						String AppURL = TestData.getCellData(sheetName, "AppURL", row);
						String SSN = TestData.getCellData(sheetName, "SSN", row);
						System.out.println(AppURL);

						ACSRLoginLogout.login(SSN, AppURL);
						ACSRBorrowerRegistration.borrowerReg(SSN, AppURL);
						ACSRLoginLogout.logout();
						
						ACSRLoginLogout.login(SSN, AppURL);
						QCCSRNewLoan.newLoan(SSN, AppURL);
						ACSRLoginLogout.logout();
						
						ACSRLoginLogout.login(SSN, AppURL);
						Age_Gracedays.Age_Gracedays(SSN, SSN, 5);
						ACSRLoginLogout.login(SSN, AppURL);
						
						JQCDepositDropdown.depositDropDown(SSN, AppURL);
						ACSRLoginLogout.logout();
						
						

					}
				}
			
		}
		


//@Test(priority = 28, enabled = true)

	public static void RKZ_ILP_Deceased_Txn() throws Exception {

		test = reports.startTest("QC_ILP_Deceased_Txn","Login->Borrower Registratino->New Loan ->Deceased");

		FileName = "QC_ILP_Deceased_Txn1" + ".xls";

		TestData = new ExcelNew(System.getProperty("user.dir") + Jprop.getProperty("QC_Store_NewLoan_Test_data_sheet_path") + FileName);

		int lastrow = TestData.getLastRow("Start");
		String sheetName = "Start";
		System.out.println(lastrow);
		for (int row = 2; row <= lastrow; row++) {

			String RunFlag = TestData.getCellData(sheetName, "Run", row);
			// System.out.println(RunFlag);
			if (RunFlag.equals("Y")) {

				String AppURL = TestData.getCellData(sheetName, "AppURL", row);
				String SSN = TestData.getCellData(sheetName, "SSN", row);			

				ACSRLoginLogout.login(SSN, AppURL);
				ACSRBorrowerRegistration.borrowerReg(SSN, AppURL);
				ACSRLoginLogout.logout();
				
				ACSRLoginLogout.login(SSN, AppURL);
				QCCSRNewLoan.newLoan(SSN, AppURL);
				ACSRLoginLogout.logout();
			
				ACSRLoginLogout.login(SSN, AppURL);
				JQC_Deceased.Deceased(SSN, AppURL);
				JQCAdmin_Bankruptcy.Admin_Bankruptcy(SSN, AppURL);

			

			}
		}

	}



//@Test(priority = 29, enabled = true)

	public static void QC_ILP_Deposit_EOD_Txn() throws Exception {
	
			test = reports.startTest("QC_ILP_Deposit_EOD_Txn1","Login->Borrower Registratino->New Loan ->Age Store->Deposit EOD");

			FileName = "QC_ILP_Deposit_EOD_Txn1" + ".xls";

			TestData = new ExcelNew(System.getProperty("user.dir")
					+ Jprop.getProperty("QC_Store_NewLoan_Test_data_sheet_path") + FileName);

			int lastrow = TestData.getLastRow("Start");
			String sheetName = "Start";
			System.out.println(lastrow);
			for (int row = 2; row <= lastrow; row++) {
				String RunFlag = TestData.getCellData(sheetName, "Run", row);
				if (RunFlag.equals("Y")) {
					String AppURL = TestData.getCellData(sheetName, "AppURL", row);
					String SSN = TestData.getCellData(sheetName, "SSN", row);
					System.out.println(AppURL);

				/*	ACSRLoginLogout.login(SSN, AppURL);
					ACSRBorrowerRegistration.borrowerReg(SSN, AppURL);
					ACSRLoginLogout.logout();
					
					ACSRLoginLogout.login(SSN, AppURL);
					QCCSRNewLoan.newLoan(SSN, AppURL);
					ACSRLoginLogout.logout();
					
					ACSRLoginLogout.login(SSN, AppURL);
					Age_Gracedays.Age_Gracedays(SSN, SSN, 0);
					QCCSRLoginLogout.logout(SSN, AppURL);*/
				
					ACSRLoginLogout.login(SSN, AppURL);
					JQCEODDeposit.eodDeposit(SSN, AppURL);

					JQCAdminStoreSetup.storeSetup(SSN, AppURL);
					JQCAdminLoginLogout.logout(SSN, AppURL);
					ACSRLoginLogout.login(SSN, AppURL);
					JQCSafeAssign.safeAssign(SSN, AppURL);
					ACSRLoginLogout.logout();
					ACSRLoginLogout.login(SSN, AppURL);
					JQCDrawerAssign.drawerAssign(SSN, AppURL);
					ACSRLoginLogout.logout();
					
					
				}
			}
			}
	
//@Test(priority = 30, enabled = true)

	public static void QC_ILP_GraceDays_Deposit_EOD_Txn() throws Exception {
		try {
			test = reports.startTest(Jprop.getProperty("QC_ILP_GraceDays_Deposit_EOD_Txn"),
					"Login->Borrower Registratino->New Loan with Promotion->Age Store->GraceDays->Deposit EOD");

			FileName = Jprop.getProperty("QC_ILP_GraceDays_Deposit_EOD_Txn_file_name") + ".xls";

			TestData = new ExcelNew(System.getProperty("user.dir")
					+ Jprop.getProperty("QC_Store_NewLoan_Test_data_sheet_path") + FileName);

			int lastrow = TestData.getLastRow("Start");
			String sheetName = "Start";
			System.out.println(lastrow);
			for (int row = 2; row <= lastrow; row++) {
				String RunFlag = TestData.getCellData(sheetName, "Run", row);
				if (RunFlag.equals("Y")) {
					String AppURL = TestData.getCellData(sheetName, "AppURL", row);
					String SSN = TestData.getCellData(sheetName, "SSN", row);
					System.out.println(AppURL);

					ACSRLoginLogout.login(SSN, AppURL);
					ACSRBorrowerRegistration.borrowerReg(SSN, AppURL);
					ACSRLoginLogout.logout();
					
					ACSRLoginLogout.login(SSN, AppURL);
					QCCSRNewLoan.newLoan(SSN, AppURL);
					ACSRLoginLogout.logout();
					
					
						ACSRLoginLogout.login(SSN, AppURL);
						Age_Gracedays.Age_Gracedays(SSN, SSN, 5);
						QCCSRLoginLogout.logout(SSN, AppURL);
					
						/*ACSRLoginLogout.login(SSN, AppURL);
						JQCEODDeposit.eodDeposit(SSN, AppURL);*/

						JQCAdminStoreSetup.storeSetup(SSN, AppURL);
						JQCAdminLoginLogout.logout(SSN, AppURL);
						
						ACSRLoginLogout.login(SSN, AppURL);
						JQCSafeAssign.safeAssign(SSN, AppURL);
						ACSRLoginLogout.logout();
						
						ACSRLoginLogout.login(SSN, AppURL);
						JQCDrawerAssign.drawerAssign(SSN, AppURL);
						ACSRLoginLogout.logout();
				}
			}
		}

		catch (Exception e) {
			System.out.println("under catch of Test" + e);
			// test.log(LogStatus.ERROR, MarkupHelper.createLabel("Unable to
			// start scenario 1 " , ExtentColor.RED));
			test.log(LogStatus.ERROR, "Unable to start scenario 17 JQC_ILP_GraceDays_Deposit_EOD_Txn ");

		}
	}


//@Test(priority = 31)
	
	public static void RKZ_Bankruptcy_WORecovery_Txn() throws Exception {
		try {
			test = reports.startTest(("QC_Bankruptcy_WORecovery"),

					"Login->Borrower Registration->New Loan->Bankruptcy in Admin Portal-->AgeStore to Due date-->WriteOff Recovery");
			String FileName = "QC_Bankruptcy_WORecovery_Txn_Testdata.xls";
			// String FileName=
			// Rprop.getProperty("QC_DefaultPayment_Txn_Testdata_Scenario")+".xls";
			// ExcelNew TestData;
			TestData = new ExcelNew(System.getProperty("user.dir")
					+ Rprop.getProperty("QC_Store_NewLoan_Test_data_sheet_path") + FileName);

			// test=reports.startTest("Lend Nation Smoke_testing","Lend Nation
			// Application test report");

			// String FileName= "Registration_Lend_Nation.xls";
			// Excel TestData = new
			// ExcelNew(System.getProperty("user.dir")+"/TestData/LendNation/"+FileName);
			// int lastrow=TestData.getLastRow("Start");
			int lastrow = TestData.getLastRow("Start");
			String sheetName = "Start";
			// int lastrow=TestData.getLastRow("Borrower");
			System.out.println(lastrow);
			for (int row = 2; row <= lastrow; row++) {

				String RunFlag = TestData.getCellData(sheetName, "Run", row);
				// System.out.println(RunFlag);
				if (RunFlag.equals("Y")) {
					// driver.get(appUrl);
					// test.log(LogStatus.INFO, "Application is launched");
					// driver.manage().window().maximize();
					String AppURL = TestData.getCellData(sheetName, "AppURL", row);
					String SSN = TestData.getCellData(sheetName, "SSN", row);

					System.out.println(AppURL);

					RQCCSRLoginLogout.login(SSN, AppURL);
					RQCBorrowerRegistration.borrowerRegistration(SSN, AppURL);
					RQCCSRLoginLogout.logout(SSN, AppURL);
					
					RQCCSRLoginLogout.login(SSN, AppURL);
					RQCCSRNewLoan.newLoan(SSN, AppURL);
					RQCCSRLoginLogout.logout(SSN, AppURL);
					
					RQCCSRLoginLogout.login(SSN, AppURL);
					RQCCSRBankruptcy.bankruptcy(SSN, AppURL);
					RAdminLoginForBankruptcy.bankruptcy(driver, SSN, AppURL);
					
					
					
					
					
					
					
					RQCCSRLoginLogout.login(SSN, AppURL);
					RQCAgeStoreGraceDays.ageStoreGraceDays(SSN, AppURL);
					RQCCSRLoginLogout.login(SSN, AppURL);
					RQCCSRWriteOffRecovery.writeoffrecovery(SSN, AppURL);
					RQCCSRLoginLogout.login(SSN, AppURL);
					RQCCSRHistory.history(SSN, AppURL);
					RQCCSRLoginLogout.logout(SSN, AppURL);

				}
			}
		}

		catch (Exception e) {
			System.out.println("under catch of Test" + e);
			String screenshotPath = getScreenhot(driver, "RQC_Bankruptcy_WORecovery_Txn");
			test.log(LogStatus.INFO, test.addScreenCapture(screenshotPath));
			test.log(LogStatus.ERROR, "Unable to start scenarios ");

		}
	}




//@Test(priority = 32)
	public static void QC_Bankruptcy_Discharge_Txn() throws Exception {
		try {
			test = reports.startTest(("QC_Bankruptcy_Discharge"),

					"Login->Borrower Registration->New Loan->Bankruptcy in Admin Portal-->AgeStore to Due date-->Discharge");
			String FileName = "QC_Bankruptcy_Discharge_Txn_Testdata.xls";
			// String FileName=
			// Rprop.getProperty("QC_DefaultPayment_Txn_Testdata_Scenario")+".xls";
			// ExcelNew TestData;
			TestData = new ExcelNew(System.getProperty("user.dir")
					+ Rprop.getProperty("QC_Store_NewLoan_Test_data_sheet_path") + FileName);

			// test=reports.startTest("Lend Nation Smoke_testing","Lend Nation
			// Application test report");

			// String FileName= "Registration_Lend_Nation.xls";
			// Excel TestData = new
			// ExcelNew(System.getProperty("user.dir")+"/TestData/LendNation/"+FileName);
			// int lastrow=TestData.getLastRow("Start");
			int lastrow = TestData.getLastRow("Start");
			String sheetName = "Start";
			// int lastrow=TestData.getLastRow("Borrower");
			System.out.println(lastrow);
			for (int row = 2; row <= lastrow; row++) {

				String RunFlag = TestData.getCellData(sheetName, "Run", row);
				// System.out.println(RunFlag);
				if (RunFlag.equals("Y")) {
					// driver.get(appUrl);
					// test.log(LogStatus.INFO, "Application is launched");
					// driver.manage().window().maximize();
					String AppURL = TestData.getCellData(sheetName, "AppURL", row);
					String SSN = TestData.getCellData(sheetName, "SSN", row);

					System.out.println(AppURL);
					
			        ACSRLoginLogout.login(SSN, AppURL);
					ACSRBorrowerRegistration.borrowerReg(SSN, AppURL);
					ACSRLoginLogout.logout();
					
					ACSRLoginLogout.login(SSN, AppURL);
					QCCSRNewLoan.newLoan(SSN, AppURL);
					ACSRLoginLogout.logout();
					
					ACSRLoginLogout.login(SSN, AppURL);
					RQCCSRBankruptcy.bankruptcy(SSN, AppURL);
					RAdminLoginForBankruptcy.bankruptcy(driver, SSN, AppURL);
					
					
					ACSRLoginLogout.login(SSN, AppURL);
					RQCCSRBankruptcy.bankruptcy(SSN, AppURL);
					RAdminLoginForDischarged.discharged(driver, SSN, AppURL);
					
					
					/*RQCCSRLoginLogout.login(SSN, AppURL);
					RQCCSRHistory.history(SSN, AppURL);
					ACSRLoginLogout.login(SSN, AppURL);*/
				}
			}
		}

		catch (Exception e) {
			System.out.println("under catch of Test" + e);
			String screenshotPath = getScreenhot(driver, "RQC_Bankruptcy_Discharge_Txn");
			test.log(LogStatus.INFO, test.addScreenCapture(screenshotPath));
			test.log(LogStatus.ERROR, "Unable to start scenarios ");

		}
	}


//@Test(priority = 33)

	public static void QC_Bankruptcy_Dismiss_Txn_Testdata() throws Exception {
		try {
			test = reports.startTest(("QC_Bankruptcy_Dismiss"),

					"Login->Borrower Registration->New Loan->Bankruptcy in Admin Portal-->AgeStore to Due date-->Dismiss");
			String FileName = "QC_Bankruptcy_Dismiss_Txn_Testdata.xls";
			// String FileName=
			// Rprop.getProperty("QC_DefaultPayment_Txn_Testdata_Scenario")+".xls";
			// ExcelNew TestData;
			TestData = new ExcelNew(System.getProperty("user.dir")
					+ Rprop.getProperty("QC_Store_NewLoan_Test_data_sheet_path") + FileName);

			// test=reports.startTest("Lend Nation Smoke_testing","Lend Nation
			// Application test report");

			// String FileName= "Registration_Lend_Nation.xls";
			// Excel TestData = new
			// ExcelNew(System.getProperty("user.dir")+"/TestData/LendNation/"+FileName);
			// int lastrow=TestData.getLastRow("Start");
			int lastrow = TestData.getLastRow("Start");
			String sheetName = "Start";
			// int lastrow=TestData.getLastRow("Borrower");
			System.out.println(lastrow);
			for (int row = 2; row <= lastrow; row++) {

				String RunFlag = TestData.getCellData(sheetName, "Run", row);
				// System.out.println(RunFlag);
				if (RunFlag.equals("Y")) {
					// driver.get(appUrl);
					// test.log(LogStatus.INFO, "Application is launched");
					// driver.manage().window().maximize();
					String AppURL = TestData.getCellData(sheetName, "AppURL", row);
					String SSN = TestData.getCellData(sheetName, "SSN", row);

					System.out.println(AppURL);
					    ACSRLoginLogout.login(SSN, AppURL);
						ACSRBorrowerRegistration.borrowerReg(SSN, AppURL);
						ACSRLoginLogout.logout();
						
						ACSRLoginLogout.login(SSN, AppURL);
						QCCSRNewLoan.newLoan(SSN, AppURL);
						ACSRLoginLogout.logout();
						
						ACSRLoginLogout.login(SSN, AppURL);
						RQCCSRBankruptcy.bankruptcy(SSN, AppURL);
						RAdminLoginForBankruptcy.bankruptcy(driver, SSN, AppURL);
						

						ACSRLoginLogout.login(SSN, AppURL);
						RQCCSRBankruptcy.bankruptcy(SSN, AppURL);					
					    RAdminLoginForDismiss.dismiss(driver, SSN, AppURL);
					
					
					/*RQCCSRLoginLogout.login(SSN, AppURL);
					RQCCSRHistory.history(SSN, AppURL);
					ACSRLoginLogout.login(SSN, AppURL);*/
				}
			}
		}

		catch (Exception e) {
			System.out.println("under catch of Test" + e);
			String screenshotPath = getScreenhot(driver, "RQC_Bankruptcy_Dismiss_Txn");
			test.log(LogStatus.INFO, test.addScreenCapture(screenshotPath));
			test.log(LogStatus.ERROR, "Unable to start scenarios ");

		}
	}

	
//@Test(priority = 28, groups = "Ratikanta_ILP")

	public static void QC_WriteOffRecovery_Txn_Testdata() throws Exception {
		try {
			test = reports.startTest(("QC_WriteOffRecovery"),

					"Login->Borrower Registration->New Loan->Age the Store Duedate-->WriteOff-->WriteOff Recovery");
			String FileName = "QC_WriteOffRecovery_Txn_Testdata.xls";
			// String FileName=
			// Rprop.getProperty("QC_DefaultPayment_Txn_Testdata_Scenario")+".xls";
			// ExcelNew TestData;
			TestData = new ExcelNew(System.getProperty("user.dir")
					+ Rprop.getProperty("QC_Store_NewLoan_Test_data_sheet_path") + FileName);

			// test=reports.startTest("Lend Nation Smoke_testing","Lend Nation
			// Application test report");

			// String FileName= "Registration_Lend_Nation.xls";
			// Excel TestData = new
			// ExcelNew(System.getProperty("user.dir")+"/TestData/LendNation/"+FileName);
			// int lastrow=TestData.getLastRow("Start");
			int lastrow = TestData.getLastRow("Start");
			String sheetName = "Start";
			// int lastrow=TestData.getLastRow("Borrower");
			System.out.println(lastrow);
			for (int row = 2; row <= lastrow; row++) {

				String RunFlag = TestData.getCellData(sheetName, "Run", row);
				// System.out.println(RunFlag);
				if (RunFlag.equals("Y")) {
					// driver.get(appUrl);
					// test.log(LogStatus.INFO, "Application is launched");
					// driver.manage().window().maximize();
					//String AppURL = TestData.getCellData(sheetName, "AppURL", row);
					String SSN = TestData.getCellData(sheetName, "SSN", row);

					System.out.println(AppURL);
					RQCCSRLoginLogout.login(SSN, AppURL);
					RQCBorrowerRegistration.borrowerRegistration(SSN, AppURL);
					ACSRLoginLogout.login(SSN, AppURL);
					RQCCSRLoginLogout.login(SSN, AppURL);
					QCCSRNewLoan.newLoan(SSN, AppURL);
					ACSRLoginLogout.login(SSN, AppURL);
					RQCCSRLoginLogout.login(SSN, AppURL);
					RQCAgeStoreDueDate.ageStoreDueDate(SSN, AppURL);
					RQCCSRLoginLogout.login(SSN, AppURL);
					RQCCSRWORecovery.simplewriteoff_recovery(SSN, AppURL);
					RQCCSRLoginLogout.login(SSN, AppURL);
					RQCCSRWriteOffRecovery.writeoffrecovery(SSN, AppURL);
					RQCCSRLoginLogout.login(SSN, AppURL);
					RQCCSRHistory.history(SSN, AppURL);
					ACSRLoginLogout.login(SSN, AppURL);

				}
			}
		}

		catch (Exception e) {
			System.out.println("under catch of Test" + e);
			String screenshotPath = getScreenhot(driver, "RQC_WriteOffRecovery_Txn");
			test.log(LogStatus.INFO, test.addScreenCapture(screenshotPath));
			test.log(LogStatus.ERROR, "Unable to start scenarios ");

		}
	}

//@Test(priority = 29, groups = "Ratikanta_ILP")

	public static void QC_WriteOffRecovery_Void_Txn_Testdata() throws Exception {
		try {
			test = reports.startTest(("QC_WriteOffRecovery_Void"),

					"Login->Borrower Registration->New Loan->Age the Store Duedate-->WriteOff-->WriteOff Recovery-->Void");
			String FileName = ".xls";
			// String FileName=
			// Rprop.getProperty("QC_DefaultPayment_Txn_Testdata_Scenario")+".xls";
			// ExcelNew TestData;
			TestData = new ExcelNew(System.getProperty("user.dir")+ Rprop.getProperty("QC_Store_NewLoan_Test_data_sheet_path") + FileName);

			// test=reports.startTest("Lend Nation Smoke_testing","Lend Nation
			// Application test report");

			// String FileName= "Registration_Lend_Nation.xls";
			// Excel TestData = new
			// ExcelNew(System.getProperty("user.dir")+"/TestData/LendNation/"+FileName);
			// int lastrow=TestData.getLastRow("Start");
			int lastrow = TestData.getLastRow("Start");
			String sheetName = "Start";
			// int lastrow=TestData.getLastRow("Borrower");
			System.out.println(lastrow);
			for (int row = 2; row <= lastrow; row++) {

				String RunFlag = TestData.getCellData(sheetName, "Run", row);
				// System.out.println(RunFlag);
				if (RunFlag.equals("Y")) {
					// driver.get(appUrl);
					// test.log(LogStatus.INFO, "Application is launched");
					// driver.manage().window().maximize();
					String AppURL = TestData.getCellData(sheetName, "AppURL", row);
					String SSN = TestData.getCellData(sheetName, "SSN", row);

					System.out.println(AppURL);
					RQCCSRLoginLogout.login(SSN, AppURL);
					RQCBorrowerRegistration.borrowerRegistration(SSN, AppURL);
					ACSRLoginLogout.login(SSN, AppURL);
					RQCCSRLoginLogout.login(SSN, AppURL);
					QCCSRNewLoan.newLoan(SSN, AppURL);
					ACSRLoginLogout.login(SSN, AppURL);
					RQCCSRLoginLogout.login(SSN, AppURL);
					RQCAgeStoreDueDate.ageStoreDueDate(SSN, AppURL);
					RQCCSRLoginLogout.login(SSN, AppURL);
					RQCCSRWORecovery.simplewriteoff_recovery(SSN, AppURL);
					RQCCSRLoginLogout.login(SSN, AppURL);
					RQCCSRWriteOffRecovery.writeoffrecovery(SSN, AppURL);
					RQCCSRLoginLogout.login(SSN, AppURL);
					RQCCSRWOVoid.writeoffvoid(SSN, AppURL);
					RQCCSRLoginLogout.login(SSN, AppURL);
					RQCCSRHistory.history(SSN, AppURL);
					ACSRLoginLogout.login(SSN, AppURL);
				}
			}
		}

		catch (Exception e) {
			System.out.println("under catch of Test" + e);
			String screenshotPath = getScreenhot(driver, "RQC_WriteOffRecovery_Void_Txn");
			test.log(LogStatus.INFO, test.addScreenCapture(screenshotPath));
			test.log(LogStatus.ERROR, "Unable to start scenarios ");

		}
	}


//@Test(priority = 21, enabled = true)
	public void ILP_voidRefinance() throws Exception {

		FileName = "ILP_Scenario_NO.52.xls";
		test = reports.startTest("ILP_Refinance_void",
				"Loan->Age store for 12 days->Make Payment less than interest amount->Age Store for 10 Days->Refinance->Void Refinance");

		TestData = new ExcelNew(
				System.getProperty("user.dir") + Aprop.getProperty("QC_Store_NewLoan_Test_data_sheet_path") + FileName);
		String sheetName = "Start";
		int lastrow = TestData.getLastRow("Start");

		for (int row = 2; row <= lastrow; row++) {
			String RunFlag = TestData.getCellData(sheetName, "Run", row);

			if (RunFlag.equals("Y")) {
				String AppURL = TestData.getCellData(sheetName, "AppURL", row);
				String SSN = TestData.getCellData(sheetName, "SSN", row);

	             ACSRLoginLogout.login(SSN, AppURL);
				ACSRBorrowerRegistration.borrowerReg(SSN, AppURL);
				ACSRLoginLogout.logout();
				
				
				ACSRLoginLogout.login(SSN, AppURL);						
				QCCSRNewLoan.newLoan(SSN, AppURL);
				ACSRLoginLogout.logout();
			
				ACSRLoginLogout.login(SSN, AppURL);
				AQCAgeStoreDueDate.ageStoreDueDate(SSN, AppURL);
			
				ACSRLoginLogout.login(SSN, AppURL);
			    //ILP_Payment.payment(SSN, AppURL);
				QCPayment.payment(SSN, AppURL);
				ACSRLoginLogout.logout();
			
			    ACSRLoginLogout.login(SSN, AppURL);
			    ILPRefinanceprocess.Refinance(SSN, AppURL);
				ACSRLoginLogout.logout();
				
				ACSRLoginLogout.login(SSN, AppURL);
				AQCVoid.qcVoid(SSN, AppURL);
				ACSRLoginLogout.logout();

			/*	ACSRLoginLogout.login(SSN, AppURL);
				AQCCSRHistory.history(SSN, AppURL);
				ACSRLoginLogout.logout();
*/
				
			}
		}
	}
	

//@Test(priority = 21, enabled = true)
	public void ILP_Refinance() throws Exception {

		FileName = "ILP_Scenario_NO.52.xls";
		test = reports.startTest("ILP_Refinance",
				"Loan->Age store for 12 days->Make Payment less than interest amount->Age Store for 10 Days->Refinance->Void Refinance");

		TestData = new ExcelNew(
				System.getProperty("user.dir") + Aprop.getProperty("QC_Store_NewLoan_Test_data_sheet_path") + FileName);
		String sheetName = "Start";
		int lastrow = TestData.getLastRow("Start");

		for (int row = 2; row <= lastrow; row++) {
			String RunFlag = TestData.getCellData(sheetName, "Run", row);

			if (RunFlag.equals("Y")) {
				String AppURL = TestData.getCellData(sheetName, "AppURL", row);
				String SSN = TestData.getCellData(sheetName, "SSN", row);

	             ACSRLoginLogout.login(SSN, AppURL);
				ACSRBorrowerRegistration.borrowerReg(SSN, AppURL);
				ACSRLoginLogout.logout();
				
				
				ACSRLoginLogout.login(SSN, AppURL);						
				QCCSRNewLoan.newLoan(SSN, AppURL);
				ACSRLoginLogout.logout();
			
				ACSRLoginLogout.login(SSN, AppURL);
				AQCAgeStoreDueDate.ageStoreDueDate(SSN, AppURL);
			
				ACSRLoginLogout.login(SSN, AppURL);
			    //ILP_Payment.payment(SSN, AppURL);
				QCPayment.payment(SSN, AppURL);
				ACSRLoginLogout.logout();
			
			    ACSRLoginLogout.login(SSN, AppURL);
			    ILPRefinanceprocess.Refinance(SSN, AppURL);
				ACSRLoginLogout.logout();
				
				

			/*	ACSRLoginLogout.login(SSN, AppURL);
				AQCCSRHistory.history(SSN, AppURL);
				ACSRLoginLogout.logout();
*/
				
			}
		}
	}
	





	@BeforeClass(alwaysRun = true)
	public void setup() throws IOException {

		try {

			BufferedReader reader;
			BufferedReader Jreader;
			BufferedReader Rreader;
			BufferedReader Areader;
			BufferedReader Vreader;
			try {
				Areader = new BufferedReader(
						new FileReader("C:/QC_Auto/RKZ_ILP/src/test/java/tests/AObjects.properties"));
				Aprop = new Properties();
				Aprop.load(Areader);
				Areader.close();
				String Afilename = Aprop.getProperty("QC_Store_extent_report_file_name") + timestamp + ".html";

				reports = new ExtentReports(
						System.getProperty("user.dir") + Aprop.getProperty("QC_Store_extent_report_path") + Afilename,
						true);

			}

			catch (Exception e) {

				System.out.println("Object proprties file not found");
			}
			
			
				
			
			try {
				reader = new BufferedReader(

						new FileReader("C:/QC_Auto/RKZ_ILP/src/test/java/tests/Objects.properties"));

				prop = new Properties();
				prop.load(reader);
				reader.close();
				AppURL = prop.getProperty("CSR_URL");
				BAdminURL = prop.getProperty("ADMIN_CSR_URL");
				String Bfilename = prop.getProperty("QC_Store_extent_report_file_name") + timestamp + ".html";

				reports = new ExtentReports(
						System.getProperty("user.dir") + prop.getProperty("QC_Store_extent_report_path") + Bfilename,
						true);

			}

			catch (Exception e) {

				System.out.println("Object proprties file not found");
			}

			try {
				Jreader = new BufferedReader(
											
						new FileReader("C:/QC_Auto/RKZ_ILP/src/test/java/tests/JObjects.properties"));

				Jprop = new Properties();
				Jprop.load(Jreader);
				Jreader.close();
				String Jfilename = Jprop.getProperty("QC_Store_extent_report_file_name") + timestamp + ".html";

				reports = new ExtentReports(
						System.getProperty("user.dir") + Jprop.getProperty("QC_Store_extent_report_path") + Jfilename,
						true);

			}

			catch (Exception e) {

				System.out.println("Object proprties file not found");
			}

			try {
				Rreader = new BufferedReader(

						new FileReader("C:/QC_Auto/RKZ_ILP/src/test/java/tests/RObjects.properties"));

				Rprop = new Properties();
				Rprop.load(Rreader);
				Rreader.close();
				csr_url = Rprop.getProperty("CSR_URL");
				csrloginpage = Rprop.getProperty("Login_Page");
				AdminURL = Rprop.getProperty("ADMIN_CSR_URL");
				String Rfilename = Rprop.getProperty("QC_Store_extent_report_file_name") + timestamp + ".html";

				reports = new ExtentReports(
						System.getProperty("user.dir") + Rprop.getProperty("QC_Store_extent_report_path") + Rfilename,
						true);

			}

			catch (Exception e) {

				System.out.println("Object proprties file not found");
			}

			
			try {
				Vreader = new BufferedReader(
						new FileReader("C:/QC_Auto/RKZ_ILP/src/test/java/tests/VObjects.properties"));
				Vprop = new Properties();
				Vprop.load(Vreader);
				Vreader.close();
				//csr_url = Vprop.getProperty("CSR_URL");
				//csrloginpage = Vprop.getProperty("Login_Page");
				//AdminURL = Vprop.getProperty("ADMIN_URL");
				String Vfilename = Vprop.getProperty("QC_Store_extent_report_file_name") + timestamp + ".html";

				reports = new ExtentReports(
						System.getProperty("user.dir") + Vprop.getProperty("QC_Store_extent_report_path") + Vfilename,
						true);

			}

			catch (Exception e) {

				System.out.println("Object proprties file not found");
			}

			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/IEDriverServer.exe");

		}

		catch (Exception e) {

			test.log(LogStatus.ERROR, "Unable to setup for the QC Store ");

		}

	}

	@BeforeMethod(alwaysRun = true)

	public void killProcess() throws Exception {

		try {

			Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
			Runtime.getRuntime().exec("taskkill /IM iexplore.exe /F");

			Thread.sleep(2000); // Allow OS to kill the process
			System.out.println("killed the process ILP scenarios");
			// break;

		} catch (Exception e) {
			// break;
			// continue;
		}
	}

	public static String getScreenhot(WebDriver driver, String screenshotName) throws Exception {
		String dateName = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss").format(new Date());

		File source = ((TakesScreenshot) (driver)).getScreenshotAs(OutputType.FILE);

		String destination = System.getProperty("user.dir") + "/ExecutionReports/QCStore/ILP/FailedTestsScreenshots/"
				+ screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);

				return destination;

		/*reports.flush();
		driver.quit();*/

		
	}

	@AfterMethod(alwaysRun = true)

	public void getResult(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
			 test.log(LogStatus.FAIL, "Test Case Failed is"+result.getThrowable());

			String screenshotPath = getScreenhot(driver, result.getName());
			// To add it in the extent report
			test.log(LogStatus.FAIL, test.addScreenCapture(screenshotPath));
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {

			test.log(LogStatus.PASS, result.getName() + " Test Case is Passed");
		}
		reports.flush();
		driver.quit();

	}

	@AfterMethod(alwaysRun = true)

	public void endReport() {

		// reports.endTest(test);
		 reports.flush();

		// driver.quit();
		//extent.flush();

	}

	public static By locator(String obj) {

		String loctype = null;
		String locname = null;
		By locator = null;
		String[] locobj = obj.split("%%");
		loctype = locobj[0];
		locname = locobj[1];
		// System.out.println("startign of switch case");
		// System.out.println(obj);
		/*
		 * switch(loctype) { case "id": locator=By.id(locname); break; case
		 * "name": locator=By.name(locname); break; case "linkText":
		 * locator=By.linkText(locname); break; case "xpath":
		 * locator=By.xpath(locname); case "cssSelector":
		 * locator=By.cssSelector(locname); break; }
		 */

		if (loctype.equalsIgnoreCase("id"))
			return locator = By.id(locname);
		else if (loctype.equalsIgnoreCase("name"))
			return locator = By.name(locname);
		else if (loctype.equalsIgnoreCase("linkText"))
			return locator = By.linkText(locname);
		else if (loctype.equalsIgnoreCase("partialLinkText"))
			return locator = By.partialLinkText(locname);
		else if (loctype.equalsIgnoreCase("xpath"))
			return locator = By.xpath(locname);
		else if (loctype.equalsIgnoreCase("cssSelector"))
			return locator = By.cssSelector(locname);
		return locator;

		// System.out.println(locator);
		// return locator;

	}

}
