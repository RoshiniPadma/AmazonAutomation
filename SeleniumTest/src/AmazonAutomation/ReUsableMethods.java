package AmazonAutomation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFHyperlink;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class ReUsableMethods {

	static BufferedWriter bw = null;
	static BufferedWriter bw1 = null;
	static String htmlname;
	static String objType;
	static String objName;
	static String TestData;
	static String rootPath;
	static int report;
	

	static Date cur_dt = null;
	static String filenamer;
	static String TestReport;
	int rowcnt;
	static String exeStatus = "True";
	static int iflag = 0;
	public static WebDriver driver;

	static String fireFoxBrowser;
	static String chromeBrowser;
	static int j;
	static String result;

	static int intRowCount = 0;
	static String dataTablePath;
	static int i;
	static String browserName;
	static HSSFSheet excelsheet;
	/*
	 * Name of the method : repoRead
	 * Brief Description : To read the object repository
	 * Arguments : path -> path of the test data document, sheet -> sheet name of the test data
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : September 2, 2017
	 * Last Modified : September 2, 2017
	 */
	public static void repoRead(String path,String sheet) throws IOException
	{
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		HSSFWorkbook workbook = new HSSFWorkbook(fis);
		excelsheet = workbook.getSheet(sheet);

	}
	/*
	 * Name of the method : getName
	 * Brief Description : To fetch the name of the object
	 * Arguments : row --> row number of the object
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : September 2, 2017
	 * Last Modified : September 2, 2017
	 */
	public static String getName(int row)
	{
		String name=excelsheet.getRow(row).getCell(0).getStringCellValue();
		return name;
	}


	/*
	 * Name of the method : getLocType
	 * Brief Description : To fetch the locator type of the object
	 * Arguments : row --> row number of the object
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : September 2, 2017
	 * Last Modified : September 2, 2017
	 */
	public static String getLocType(int row)
	{
		String type=excelsheet.getRow(row).getCell(1).getStringCellValue();
		return type;
	}


	/*
	 * Name of the method : getLocValue
	 * Brief Description : To fetch the locator value of the object
	 * Arguments : row --> row number of the object
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : September 2, 2017
	 * Last Modified : September 2, 2017
	 */
	public static String getLocValue(int row)
	{
		String val=excelsheet.getRow(row).getCell(2).getStringCellValue();
		return val;
	}


	/*
	 * Name of the method : getBy
	 * Brief Description : To get the locator type phrase
	 * Arguments : type --> locator type, value --> locator value
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : September 2, 2017
	 * Last Modified : September 2, 2017
	 */

	public static By getBy(String type, String value)
	{

		switch (type)
		{
		case "id":
			return By.id(value);
		case "xpath":
			return By.xpath(value);
		case "className":
			return By.className(value);
		case "name":
			return By.name(value);
		case "linkText":
			return By.linkText(value);
		case "partialLinkText":
			return By.partialLinkText(value);
		case "cssSelector":
			return By.cssSelector(value);
		case "tagName":
			return By.tagName(value);
		default:
			System.out.println("Unknown type");
			return null;

		}
	}

	/*
	 * Name of the method : objDetails
	 * Brief Description : To get the details of the object
	 * Arguments : row --> row number of the object 
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : September 2, 2017
	 * Last Modified : September 2, 2017
	 */

	public static By objDetails(int row)
	{

		String locatortype = getLocType(row);
		String locvalue = getLocValue(row);
		By temp=getBy(locatortype,locvalue);
		return temp;

	}

	/*
	 * Name of the method : startReport
	 * Brief Description : To generate HTML Reports for test cases
	 * Arguments : scriptName -> Name of the report folder, ReportsPath -> Path of the reports folder
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : September 2, 2017
	 * Last Modified : September 2, 2017
	 */


	public static void startReport(String scriptName, String ReportsPath,String browsername) throws IOException{

		String strResultPath = null;

		browserName=browsername;
		String testScriptName =scriptName;
		j=1;

		cur_dt = new Date(); 
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String strTimeStamp = dateFormat.format(cur_dt);

		if (ReportsPath == "") { 

			ReportsPath = "C://";
		}

		if (ReportsPath.endsWith("//")) { 
			ReportsPath = ReportsPath + "//";
		}

		strResultPath = ReportsPath + "Log" + "/" +testScriptName +"/"; 
		File f = new File(strResultPath);
		f.mkdirs();
		htmlname = strResultPath  + testScriptName + "_" + strTimeStamp 
				+ ".html";



		bw = new BufferedWriter(new FileWriter(htmlname));

		bw.write("<HTML><BODY><TABLE BORDER=0 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		bw.write("<TABLE BORDER=0 BGCOLOR=BLACK CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		bw.write("<TR><TD BGCOLOR=#66699 WIDTH=27%><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>Browser Name</B></FONT></TD><TD COLSPAN=6 BGCOLOR=#66699><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>"
				+ browserName + "</B></FONT></TD></TR>");
		bw.write("<HTML><BODY><TABLE BORDER=1 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		bw.write("<TR COLS=7><TD BGCOLOR=#BDBDBD WIDTH=3%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>SL No</B></FONT></TD>"
				+ "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Step Name</B></FONT></TD>"
				+ "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Execution Time</B></FONT></TD> "
				+ "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Status</B></FONT></TD>"
				+ "<TD BGCOLOR=#BDBDBD WIDTH=47%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Detail Report</B></FONT></TD></TR>");


	}


	/*
	 * Name of the method : Update_Report
	 * Brief Description : To update the result of the test cases in the HTML report
	 * Arguments : Res_type -> Pass/Fail, Action -> Action performed , result -> Detailed description
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : September 2, 2017
	 * Last Modified : September 2, 2017
	 */
	public static void Update_Report(String Res_type,String Action, String result, WebDriver dr) throws IOException {
		String str_time;
		Date exec_time = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		str_time = dateFormat.format(exec_time);

		if (Res_type.startsWith("Pass")) {
			bw.write("<TR COLS=7><TD BGCOLOR=#EEEEEE WIDTH=3%><FONT FACE=VERDANA SIZE=2>"
					+ (j++)
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+Action
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+ str_time
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2 COLOR = GREEN>"
					+ "Passed"
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=30%><FONT FACE=VERDANA SIZE=2 COLOR = GREEN>"
					+ result + "</FONT></TD></TR>");

			writePass(Driver.r,Driver.c);
			
		} else if (Res_type.startsWith("Fail")) {
			String ss1Path= screenshot(dr);
			
			exeStatus = "Failed";
			report = 1;
			bw.write("<TR COLS=7><TD BGCOLOR=#EEEEEE WIDTH=3%><FONT FACE=VERDANA SIZE=2>"
					+ (j++)
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+Action
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+ str_time
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2 COLOR = RED>"
					+ "<a href= "
					+ ss1Path
					
					+ "  style=\"color: #FF0000\"> Failed </a>"

						+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=30%><FONT FACE=VERDANA SIZE=2 COLOR = RED>"
						+ result + "</FONT></TD></TR>");

			writeFail(Driver.r,Driver.c,ss1Path);

		} 

	}
	/*
	 * Name of the method : writeFail
	 * Brief Description : To write the failure updates in the excel file and change the cell color to red
	 * Arguments : r --> row, c --> col, screenshot --> failure screenshot path 
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : September 2, 2017
	 * Last Modified : September 2, 2017
	 */
	
	public static void writeFail(int r, int c, String screenshot) throws IOException
	{
		
		File file = new File(Driver.suitpath);
		FileInputStream xlDoc = new FileInputStream(file);
		HSSFWorkbook wb1 = new HSSFWorkbook(xlDoc);
		HSSFSheet sheet1 = wb1.getSheet(Driver.suitsheet);
		HSSFRow row = sheet1.getRow(r);
		HSSFCell cell = row.getCell(c);
		
		HSSFHyperlink hlpl=new HSSFHyperlink(HSSFHyperlink.LINK_FILE);
		cell.setCellValue("Test Case Failed. Click here to know more. ");
		
		hlpl.setAddress(screenshot);
		cell.setHyperlink(hlpl);
		HSSFCellStyle titleStyle = wb1.createCellStyle();
		HSSFFont font=wb1.createFont();
		font.setUnderline(HSSFFont.U_SINGLE);
		
		titleStyle.setFont(font);
		
	
		titleStyle.setFillForegroundColor(new HSSFColor.RED().getIndex());
		titleStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cell.setCellStyle(titleStyle);
		FileOutputStream fout1 = new FileOutputStream(Driver.suitpath);
		
		wb1.write(fout1);
		fout1.flush();
		fout1.close();
	}
	/*
	 * Name of the method : writePass
	 * Brief Description : To change the cell color to green in excel file
	 * Arguments : r --> row, c --> col
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : September 2, 2017
	 * Last Modified : September 2, 2017
	 */
	
	public static void writePass(int r, int c) throws IOException
	{
		
		File file = new File(Driver.suitpath);
		FileInputStream xlDoc = new FileInputStream(file);
		HSSFWorkbook wb1 = new HSSFWorkbook(xlDoc);
		HSSFSheet sheet1 = wb1.getSheet(Driver.suitsheet);
		HSSFRow row = sheet1.getRow(r);
		HSSFCell cell = row.getCell(c);
		
		cell.setCellValue("Test Case Passed");
		
		HSSFCellStyle titleStyle = wb1.createCellStyle();
		titleStyle.setFillForegroundColor(new HSSFColor.LIGHT_GREEN().getIndex());
		titleStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cell.setCellStyle(titleStyle);
		FileOutputStream fout1 = new FileOutputStream(Driver.suitpath);
		wb1.write(fout1);
		fout1.flush();
		fout1.close();
	}
	
	/*
	 * Name of the method : screenshot
	 * Brief Description : To get the screenshot of failures
	 * Arguments : dr -> The webdriver instance
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : September 2, 2017
	 * Last Modified : September 2, 2017
	 */
public static String screenshot(WebDriver dr) throws IOException{
		
		Date exec_time = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String str_time = dateFormat.format(exec_time);
		
		String ss1Path="C:/Users/Zara/Desktop/Screenshots/"+str_time+".png";
		
		
		File scrFile = ((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(ss1Path));
		return ss1Path;
	}


	/*
	 * Name of the method : inputRead
	 * Brief Description : To read the test data from the excel document
	 * Arguments : path -> path of the test data document, sheet -> sheet name of the test data
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : September 2, 2017
	 * Last Modified : September 2, 2017
	 */

	public static String[][] inputRead(String path, String sheet) throws IOException {

		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		HSSFWorkbook workbook = new HSSFWorkbook(fis);
		HSSFSheet excelsheet = workbook.getSheet(sheet);

		int rowcount = excelsheet.getLastRowNum() + 1;
		int colcount = excelsheet.getRow(0).getLastCellNum();

		String[][] temp = new String[rowcount][colcount];

		for (int i = 0; i < rowcount; i++) {
			for (int j = 0; j < colcount; j++) {
				temp[i][j] = excelsheet.getRow(i).getCell(j).getStringCellValue();
			}
		}


		fis.close();
		return temp;
	}

	/*
	 * Name of the method : enterText
	 * Brief Description : To enter text in the input field
	 * Arguments : elt -> WebElement being handled, input -> Data to be entered, objName -> Name of the element handled
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : September 2, 2017
	 * Last Modified : September 2, 2017
	 */

	public static void enterText(WebElement elt, String input, String objName) throws IOException
	{
		if(elt.isDisplayed())
		{
			elt.sendKeys(input);
			Update_Report("Pass", ("Enter input --> "+objName), ("Input entered in "+objName+ " --> "+input),driver);
		}
		else
		{
			Update_Report("Fail", ("Enter input --> "+objName), ("Enter input Action has failed"),driver);
		}
	}

	/*
	 * Name of the method : clickButton
	 * Brief Description : To click a button
	 * Arguments : elt ->WebElement being handled, objName -> Name of the web element being handled 
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : September 2, 2017
	 * Last Modified : September 2, 2017
	 */

	public static void clickButton(WebElement elt,  String objName) throws IOException{

		if(elt.isDisplayed()){
			elt.click();
			Update_Report("Pass", ("Click --> "+objName), (objName+" is clicked "),driver);
		}else{
			Update_Report("Fail", ("Click --> "+objName), (objName+" is not clicked "),driver);
		}

	}
	
	/*
	 * Name of the method : clickButtonEdge
	 * Brief Description : To click a button
	 * Arguments : elt ->WebElement being handled, objName -> Name of the web element being handled 
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : September 2, 2017
	 * Last Modified : September 2, 2017
	 */

	public static void clickButtonEdge(WebElement elt,  String objName) throws IOException{

		if(elt.isDisplayed()){
		
			elt.submit();
			Update_Report("Pass", ("Click --> "+objName), (objName+" is clicked "),driver);
		}else{
			Update_Report("Fail", ("Click --> "+objName), (objName+" is not clicked "),driver);
		}

	}

	/*
	 * Name of the method : validateErrMsg
	 * Brief Description : To validate the generated error message against the expected result
	 * Arguments : expectedResult -> The expected error message , testResult -> The generated error message
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : September 2, 2017
	 * Last Modified : September 2, 2017
	 */


	public static void validateErrMsg(String expectedResult, String testResult) throws IOException {
		// TODO Auto-generated method stub
		if (expectedResult.equals(testResult))

		{
			Update_Report("Pass", "Validation",( "The error message generated '"+testResult+"' matches with the expected result . Test Case Passed."),driver);
		} else
			Update_Report("Fail", "Validation",( "The error message generated '"+testResult+"' matched with the expected result. Test Case Failed."),driver);


	}



	/*
	 * Name of the method : validateTestResult
	 * Brief Description : To validate the generated test result against the expected result
	 * Arguments : expectedResult -> The expected error message , testResult -> The generated error message
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : September 2, 2017
	 * Last Modified : September 2, 2017
	 */
	public static void validateTestResult(String expectedResult, String testResult) throws IOException {
		// TODO Auto-generated method stub
		if (expectedResult.equals(testResult))

		{
			Update_Report("Pass", "Validation",( "The test result generated '"+testResult+"' matches with the expected result . Test Case Passed."),driver);
		} else
			Update_Report("Fail", "Validation",( "The test result generated '"+testResult+"' does not match with the expected result. Test Case Failed."),driver);


	}
	/*
	 * Name of the method : initChrome
	 * Brief Description : Initialize driver and launch chrome browser
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : September 2, 2017
	 * Last Modified : September 2, 2017
	 */
	
	public static void initChrome()
	{
		System.setProperty("webdriver.chrome.driver", "C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/chromedriver.exe");
		
		driver=new ChromeDriver();
	}
	
	/*
	 * Name of the method : initFirefox
	 * Brief Description : Initialize driver and launch firefox browser
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : September 2, 2017
	 * Last Modified : September 2, 2017
	 */
	public static void initFirefox()
	{
		System.setProperty("webdriver.gecko.driver", "C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/geckodriver.exe");
		
		driver=new FirefoxDriver();
	}
	
	/*
	 * Name of the method : initIE
	 * Brief Description : Initialize driver and launch IE browser
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : September 2, 2017
	 * Last Modified : September 2, 2017
	 */
	public static void initEgde()
	{
		System.setProperty("webdriver.edge.driver", "C:/Users/Zara/Documents/Roshini/Selenium Training/selenium automation/MicrosoftWebDriver.exe");
		
		driver=new EdgeDriver();
	}
	
	

}
