package AmazonAutomation;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class AutomationScripts extends ReUsableMethods{


	static By byVar;
	
	/*
	 * Name of the method : testCase1
	 * Brief Description : Search and add iPhone 6 to cart
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : September 2, 2017
	 * Last Modified : September 2, 2017
	 */

	
	public static void testCase1() throws IOException, InterruptedException, AWTException
	{
		
		String filepath = "C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\AmazonAuto\\Test Data\\Test Case 1.xls";
		String sheetname = "Sheet1";
		String repopath="C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\AmazonAuto\\Object Repository.xls";
		String reposheet="Sheet1";
		
		

		String[][] exceldata = inputRead(filepath, sheetname);
		repoRead(repopath,reposheet);

		String URL = exceldata[1][1];
		String searchText = exceldata[1][2];
		
		launchURL(URL);
		
		By byVar = objDetails(1);//search text box field		
		enterText(driver.findElement(byVar), searchText, getName(1));

		byVar = objDetails(2);//search button		
		
		if(ReUsableMethods.browserName.equalsIgnoreCase("Edge"))
		clickButtonEdge(driver.findElement(byVar), getName(2));	
		else
		clickButton(driver.findElement(byVar), getName(2));
		
		
		byVar=objDetails(3); //search results
		ArrayList<WebElement> searchResults=(ArrayList<WebElement>) driver.findElements(byVar);
		
		String expectedResult=searchResults.get(0).getText();
		
		
		//click on the first result with 'Apple iPhone 6'
		if (ReUsableMethods.browserName.equalsIgnoreCase("Edge"))
			clickButtonEdge(searchResults.get(0), searchResults.get(0).getText());
		else
			clickButton(searchResults.get(0), searchResults.get(0).getText());
		
		
		byVar=objDetails(4);//add to cart button
		if(ReUsableMethods.browserName.equalsIgnoreCase("Edge"))
			clickButtonEdge(driver.findElement(byVar), getName(4));
		else
			clickButton(driver.findElement(byVar), getName(4));
	
		
		Thread.sleep(2000);
		
		
		byVar=objDetails(11);
		if(ReUsableMethods.browserName.equalsIgnoreCase("Edge"))
			clickButtonEdge(driver.findElement(byVar), getName(11));	
			else
			clickButton(driver.findElement(byVar), getName(11));
		
		byVar=objDetails(5); //cart button
		if(ReUsableMethods.browserName.equalsIgnoreCase("Edge"))
			clickButtonEdge(driver.findElement(byVar), getName(5));	
			else
			clickButton(driver.findElement(byVar), getName(5));
		
		Thread.sleep(2000);
		
		byVar=objDetails(6);//cart items
		ArrayList<WebElement> cart=(ArrayList<WebElement>) driver.findElements(byVar);
		String testResult=cart.get(0).getText();
		
		validateTestResult(expectedResult, testResult);
		
		
		bw.close();
		driver.close();
	}
	
	
	/*
	 * Name of the method : testCase2
	 * Brief Description : Mouse hover on Departments dropdown, Your amazon.com, Today's Deals
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : September 2, 2017
	 * Last Modified : September 2, 2017
	 */

	
	public static void testCase2() throws IOException, InterruptedException, AWTException
	{
		
		String filepath = "C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\AmazonAuto\\Test Data\\Test Case 2.xls";
		String sheetname = "Sheet1";
		String repopath="C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\AmazonAuto\\Object Repository.xls";
		String reposheet="Sheet1";
		
		String[][] exceldata = inputRead(filepath, sheetname);
		repoRead(repopath,reposheet);

		String URL = exceldata[1][1];
		String alloptions = exceldata[1][2];
		String[] opts=alloptions.split("%");
		
		
		
		launchURL(URL);
		
		byVar=objDetails(12);//departments drop down
		WebElement departments=driver.findElement(byVar);
		Actions action=new Actions(driver);
		action.moveToElement(departments).build().perform();
		
		Thread.sleep(3000);
		
		byVar=objDetails(13);//drop down options
		ArrayList<WebElement> options=(ArrayList<WebElement>) driver.findElements(byVar);
		Boolean val=false;
		
		for(int i=0;i<opts.length;i++)
		{
			Thread.sleep(20);
			
			if(opts[i].equals(options.get(i).getText()))
				val=true;
			else
			{
				val=false;
				break;
			}
			
			
		}
		if(val==true)
			Update_Report("Pass", "Departments dropdown","The mouse hover on departments dropdown is working.",driver);
		else
			Update_Report("Fail", "Departments dropdown","The mouse hover on departments dropdown is not working.",driver);
		
		
		byVar=objDetails(14); //your amazon.com
		WebElement your=driver.findElement(byVar);
		action.moveToElement(your).build().perform();
		action.click().build().perform();
		
		byVar=objDetails(15);//sign in header
		validateTestResult(driver.findElement(byVar).getText(), "Sign in");
		
		launchURL(URL);
		
		byVar=objDetails(16); //today's deals
		WebElement deals=driver.findElement(byVar);
		action.moveToElement(deals).build().perform();
		action.click().build().perform();
		
		byVar=objDetails(17);//today's deals header
		validateTestResult(driver.findElement(byVar).getText(), "Today's Deals");
		
		
		bw.close();
		driver.close();
	}
	
	/*
	 * Name of the method : testCase3
	 * Brief Description : Validate Departments drop down list
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : September 2, 2017
	 * Last Modified : September 2, 2017
	 */

	
	public static void testCase3() throws IOException, InterruptedException, AWTException
	{
		
		String filepath = "C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\AmazonAuto\\Test Data\\Test Case 3.xls";
		String sheetname = "Sheet1";
		String repopath="C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\AmazonAuto\\Object Repository.xls";
		String reposheet="Sheet1";
		
		String[][] exceldata = inputRead(filepath, sheetname);
		repoRead(repopath,reposheet);

		String URL = exceldata[1][1];
		
		String alloptionsfirefox = exceldata[1][2];
		String[] optsfirefox=alloptionsfirefox.split("%");
		
		String alloptionschrome = exceldata[1][3];
		String[] optschrome=alloptionschrome.split("%");
		
		
		
		launchURL(URL);
		
		byVar=objDetails(12);//departments drop down
		WebElement departments=driver.findElement(byVar);
		Actions action=new Actions(driver);
		action.moveToElement(departments).build().perform();
		
		Thread.sleep(3000);
		
		byVar=objDetails(13);//drop down options
		ArrayList<WebElement> options=(ArrayList<WebElement>) driver.findElements(byVar);
		
		
		for(int i=0;i<options.size();i++)
		{
			if(ReUsableMethods.browserName.equals("Chrome"))
			{
				
			validateTestResult(options.get(i).getText(), optschrome[i]);
			}
			if(ReUsableMethods.browserName.equals("Firefox"))
			{
				
			validateTestResult(options.get(i).getText(), optsfirefox[i]);
			}
			
		}
		
		Thread.sleep(3000);
		
		bw.close();
		driver.close();
	
	}
	
	/*
	 * Name of the method : testCase4
	 * Brief Description : Validate Departments drop down list
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : September 2, 2017
	 * Last Modified : September 2, 2017
	 */

	
	public static void testCase4() throws IOException, InterruptedException, AWTException
	{
		
		String filepath = "C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\AmazonAuto\\Test Data\\Test Case 4.xls";
		String sheetname = "Sheet1";
		String repopath="C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\AmazonAuto\\Object Repository.xls";
		String reposheet="Sheet1";
		
		String[][] exceldata = inputRead(filepath, sheetname);
		repoRead(repopath,reposheet);

		String URL=exceldata[1][1];
		String yl=exceldata[1][2];
		String[] yourlist=yl.split("%");
		String ya=exceldata[1][3];
		String[] youraccount=ya.split("%");
		
		launchURL(URL);
		
		byVar=objDetails(18);//Hello, sign in drop down
		WebElement departments=driver.findElement(byVar);
		Actions action=new Actions(driver);
		action.moveToElement(departments).build().perform();
		Thread.sleep(5000);
		
		
		byVar=objDetails(19);//Sign in button
		if(driver.findElement(byVar).isDisplayed())
			Update_Report("Pass", getName(19), "Sign in button is present", driver);
		else
			Update_Report("Fail", getName(19), "Sign in button is not present", driver);
		
		byVar=objDetails(20);//New Customer? Start here
		if(driver.findElement(byVar).isDisplayed())
			Update_Report("Pass", getName(20), "The new customer? option is present", driver);
		else
			Update_Report("Fail", getName(20), "The new customer? option is not present", driver);
		
		
		byVar=objDetails(21);//Your Lists
		if(driver.findElement(byVar).isDisplayed())
			Update_Report("Pass", getName(21), "Your Lists is present", driver);
		else
			Update_Report("Fail", getName(21), "Your Lists is not present", driver);
		
		
		byVar=objDetails(22);//your lists options
		ArrayList<WebElement> options=(ArrayList<WebElement>) driver.findElements(byVar);
		for(int i=0;i<options.size();i++)
			{
			validateTestResult(yourlist[i], options.get(i).getText());
			}
		
		byVar=objDetails(23);//your account options
		ArrayList<WebElement> accoptions=(ArrayList<WebElement>) driver.findElements(byVar);
		for(int i=0;i<accoptions.size();i++)
			{
			//System.out.print(accoptions.get(i).getText()+"%");
			validateTestResult(youraccount[i], accoptions.get(i).getText());
			}
		
		System.out.println();
		
	bw.close();
	driver.close();
	}
	
	/*
	 * Name of the method : testCase5
	 * Brief Description : Validate All drop down list
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : September 2, 2017
	 * Last Modified : September 2, 2017
	 */

	
	public static void testCase5() throws IOException, InterruptedException, AWTException
	{
		
		String filepath = "C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\AmazonAuto\\Test Data\\Test Case 5.xls";
		String sheetname = "Sheet1";
		String repopath="C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\AmazonAuto\\Object Repository.xls";
		String reposheet="Sheet1";
		
		String[][] exceldata = inputRead(filepath, sheetname);
		repoRead(repopath,reposheet);

		String URL=exceldata[1][1];
		String all=exceldata[1][2];
		String expectedResult=exceldata[1][3];
		String clothing=exceldata[1][4];
		
		launchURL(URL);
		
		byVar=objDetails(24);//all dropdown
		Select sel=new Select(driver.findElement(byVar));
		if(sel.getFirstSelectedOption().getText().equals(all))
			Update_Report("Pass", "All dropdown", "All dropdown is presenr", driver);
		else
			Update_Report("Fail", "All dropdown", "All dropdown is not present", driver);
		
		driver.findElement(byVar).click();
		
		ArrayList<WebElement> allmenu=(ArrayList<WebElement>) sel.getOptions();
		
		String testResult="";
		for(int i=0;i<allmenu.size();i++)
		{	Thread.sleep(10);
			testResult=testResult+allmenu.get(i).getText()+"/ ";
		}
		validateTestResult(expectedResult, testResult);
		
		byVar=objDetails(25);//clothing sub menu
		
		ArrayList<WebElement> submenu=(ArrayList<WebElement>) driver.findElements(byVar);
		testResult="";
		for(int i=0;i<submenu.size();i++)
		{
			Thread.sleep(10);
			testResult=testResult+submenu.get(i).getText()+"/ ";
		}
		
		validateTestResult(clothing, testResult);
		
		Thread.sleep(2000);
		bw.close();
		driver.close();
	}

	/*
	 * Name of the method : testCase6
	 * Brief Description : Search and add iPhone 6S 64GB to cart, delete it and validate empty cart
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : September 2, 2017
	 * Last Modified : September 2, 2017
	 */

	
	public static void testCase6() throws IOException, InterruptedException, AWTException
	{
		
		String filepath = "C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\AmazonAuto\\Test Data\\Test Case 6.xls";
		String sheetname = "Sheet1";
		String repopath="C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\AmazonAuto\\Object Repository.xls";
		String reposheet="Sheet1";
		
		

		String[][] exceldata = inputRead(filepath, sheetname);
		repoRead(repopath,reposheet);

		String URL = exceldata[1][1];
		String searchText = exceldata[1][2];
		String expectedResult=exceldata[1][3];
		
		launchURL(URL);
		
		By byVar = objDetails(1);//search text box field		
		enterText(driver.findElement(byVar), searchText, getName(1));

		byVar = objDetails(2);//search button		
		
		if(ReUsableMethods.browserName.equalsIgnoreCase("Edge"))
		clickButtonEdge(driver.findElement(byVar), getName(2));	
		else
		clickButton(driver.findElement(byVar), getName(2));
		
		byVar=objDetails(26); //search results
		ArrayList<WebElement> searchResults=(ArrayList<WebElement>) driver.findElements(byVar);
		
		
		//click on the first result with 'Apple iPhone 6'
		if (ReUsableMethods.browserName.equalsIgnoreCase("Edge"))
			clickButtonEdge(searchResults.get(0), searchResults.get(0).getText());
		else
			clickButton(searchResults.get(0), searchResults.get(0).getText());
		
		byVar=objDetails(30);//product title
		String producttitle=driver.findElement(byVar).getText();
		
		byVar=objDetails(4);//add to cart button
		if(ReUsableMethods.browserName.equalsIgnoreCase("Edge"))
			clickButtonEdge(driver.findElement(byVar), getName(4));
		else
			clickButton(driver.findElement(byVar), getName(4));
	
		
		Thread.sleep(2000);
		
		byVar=objDetails(27);//cart button
		if(ReUsableMethods.browserName.equalsIgnoreCase("Edge"))
			clickButtonEdge(driver.findElement(byVar), getName(27));
		else
			clickButton(driver.findElement(byVar), getName(27));
		
		byVar=objDetails(31);//products in cart
		ArrayList<WebElement> productsInCart=(ArrayList<WebElement>) driver.findElements(byVar);
		
		for(int i=0;i<productsInCart.size();i++)
		{
			if(productsInCart.get(i).getText().equals(producttitle))
			{
				byVar=objDetails(28);//delete item
				if(ReUsableMethods.browserName.equalsIgnoreCase("Edge"))
					clickButtonEdge(driver.findElement(byVar), getName(28));
				else
					clickButton(driver.findElement(byVar), getName(28));
			
			}
		}
		
		byVar=objDetails(27);//cart button
		if(ReUsableMethods.browserName.equalsIgnoreCase("Edge"))
			clickButtonEdge(driver.findElement(byVar), getName(27));
		else
			clickButton(driver.findElement(byVar), getName(27));
		
		byVar=objDetails(29);//empty cart page
		String testResult=driver.findElement(byVar).getText();
		validateTestResult(expectedResult, testResult);
		
		bw.close();
		driver.close();
	}
	
	
	/*
	 * Name of the method : testCase7
	 * Brief Description : Help page
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : September 2, 2017
	 * Last Modified : September 2, 2017
	 */

	
	public static void testCase7() throws IOException, InterruptedException, AWTException
	{
		
		String filepath = "C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\AmazonAuto\\Test Data\\Test Case 7.xls";
		String sheetname = "Sheet1";
		String repopath="C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\AmazonAuto\\Object Repository.xls";
		String reposheet="Sheet1";
		
		

		String[][] exceldata = inputRead(filepath, sheetname);
		repoRead(repopath,reposheet);

		String URL = exceldata[1][1];
		String helpheader=exceldata[1][2];
		String findmore = exceldata[1][3];
		String expectedResult=exceldata[1][4];
		
		launchURL(URL);
		
		byVar=objDetails(32);//help link
		if(ReUsableMethods.browserName.equalsIgnoreCase("Edge"))
			clickButtonEdge(driver.findElement(byVar), getName(32));
		else
			clickButton(driver.findElement(byVar), getName(32));
		
		byVar=objDetails(33);//help page header
		String testResult=driver.findElement(byVar).getText();
		validateTestResult(helpheader, testResult);
		
		byVar=objDetails(34);//help options
		ArrayList<WebElement> options= (ArrayList<WebElement>) driver.findElements(byVar);
		testResult="";
		for(int i=0;i<options.size();i++)
		{
			testResult=testResult+options.get(i).getText()+"/ ";
		}
		
		validateTestResult(expectedResult, testResult);
		
		byVar=objDetails(35);//find more solutions
		String placeholder=driver.findElement(byVar).getText();
		
		validateTestResult(findmore, placeholder);

		byVar=objDetails(36);//search icon
		if(driver.findElement(byVar).isDisplayed())
			Update_Report("Pass", "Search icon ", "Search icon present in search text field", driver);
		else
			Update_Report("Fail", "Search icon", "Search icon not present in search text field", driver);
		
		
		Thread.sleep(2000);
		bw.close();
		driver.close();
		
	}
	
	/*
	 * Name of the method : testCase8
	 * Brief Description : Quantity changes in cart 
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : September 2, 2017
	 * Last Modified : September 2, 2017
	 */

	
	public static void testCase8() throws IOException, InterruptedException, AWTException
	{
		
		String filepath = "C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\AmazonAuto\\Test Data\\Test Case 8.xls";
		String sheetname = "Sheet1";
		String repopath="C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\AmazonAuto\\Object Repository.xls";
		String reposheet="Sheet1";
		
		

		String[][] exceldata = inputRead(filepath, sheetname);
		repoRead(repopath,reposheet);

		String URL = exceldata[1][1];
		String searchText=exceldata[1][2];
		String quantity=exceldata[1][3];
		
		launchURL(URL);
		
		byVar=objDetails(24);//all dropdown
		WebElement drpdwn=driver.findElement(byVar);
		Select all=new Select(drpdwn);
	
		all.selectByIndex(11);//select books
		
		byVar=objDetails(1);//search text box
		enterText(driver.findElement(byVar), searchText, getName(1));
		
		
		byVar=objDetails(2);//search button
		if(ReUsableMethods.browserName.equalsIgnoreCase("Edge"))
			clickButtonEdge(driver.findElement(byVar), getName(2));
		else
			clickButton(driver.findElement(byVar), getName(2));
		
		
		byVar=objDetails(38);//search result
		ArrayList<WebElement> res=(ArrayList<WebElement>) driver.findElements(byVar);
		
		if(ReUsableMethods.browserName.equalsIgnoreCase("Edge"))
			clickButtonEdge(res.get(0), getName(38));
		else
			clickButton(res.get(0), getName(38));
		
		
		byVar=objDetails(40);//number in cart
		String prevqty=driver.findElement(byVar).getText();
		int prev=Integer.parseInt(prevqty);//previous quantity in cart
		Update_Report("Pass", "Previous quantity", "The previous quantity in cart is "+prevqty, driver);
		
		byVar=objDetails(39);//quantity list
		Select sel=new Select(driver.findElement(byVar));
		sel.selectByValue(quantity);
		
	
		int qtyincr=Integer.parseInt(quantity);//quantity selected
		
		if(sel.getFirstSelectedOption().getText().equalsIgnoreCase(quantity))
			Update_Report("Pass", "Select quantity 5", "Quantity 5 is selected", driver);
		else
			Update_Report("Fail", "Select quantity 5", "Quantity 5 is not selected", driver);
		
		int updtQty=prev+qtyincr; //calculate the quantity in cart
		String updatedQty=Integer.toString(updtQty);
		Thread.sleep(2000);
		
		Update_Report("Pass", "Updated quantity", ("The total calculated quantity in cart is "+updatedQty), driver);
		
		byVar=objDetails(41);//add to cart
		if(ReUsableMethods.browserName.equalsIgnoreCase("Edge"))
			clickButtonEdge(driver.findElement(byVar), getName(41));
		else
			clickButton(driver.findElement(byVar), getName(41));
		Thread.sleep(2000);
		
		byVar=objDetails(40);//number in cart
		String newqty=driver.findElement(byVar).getText(); // new quantity in cart
		
		Update_Report("Pass", "Actual quantity in cart", "The actual quantity in cart is "+newqty, driver);
		
		Thread.sleep(200);
		validateTestResult(updatedQty, newqty);
		Thread.sleep(2000);
		bw.close();
		driver.close();
		
	}
	
	/*
	 * Name of the method : testCase9
	 * Brief Description : Save for later
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : September 2, 2017
	 * Last Modified : September 2, 2017
	 */

	
	public static void testCase9() throws IOException, InterruptedException, AWTException
	{
		
		String filepath = "C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\AmazonAuto\\Test Data\\Test Case 9.xls";
		String sheetname = "Sheet1";
		String repopath="C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\AmazonAuto\\Object Repository.xls";
		String reposheet="Sheet1";
		
		

		String[][] exceldata = inputRead(filepath, sheetname);
		repoRead(repopath,reposheet);

		String URL = exceldata[1][1];
		String searchText=exceldata[1][2];
		String quantity=exceldata[1][3];
		String updquantity=exceldata[1][4];
		String expectedResult=exceldata[1][5];
		
		launchURL(URL);
		
		byVar=objDetails(24);//all dropdown
		WebElement drpdwn=driver.findElement(byVar);
		Select all=new Select(drpdwn);
	
		all.selectByIndex(11);//select books
		
		byVar=objDetails(1);//search text box
		enterText(driver.findElement(byVar), searchText, getName(1));
		
		
		byVar=objDetails(2);//search button
		if(ReUsableMethods.browserName.equalsIgnoreCase("Edge"))
			clickButtonEdge(driver.findElement(byVar), getName(2));
		else
			clickButton(driver.findElement(byVar), getName(2));
		
		
		byVar=objDetails(38);//search result
		ArrayList<WebElement> res=(ArrayList<WebElement>) driver.findElements(byVar);
		if(ReUsableMethods.browserName.equalsIgnoreCase("Edge"))
			clickButtonEdge(res.get(0), getName(38));
		else
			clickButton(res.get(0), getName(38));
		
		byVar=objDetails(40);//number in cart
		String prevqty=driver.findElement(byVar).getText();
		Update_Report("Pass", "Previous quantity", "The previous quantity in cart is "+prevqty, driver);
		
		byVar=objDetails(39);//quantity list
		Select sel=new Select(driver.findElement(byVar));
		sel.selectByValue(quantity);
		
	
		if(sel.getFirstSelectedOption().getText().equalsIgnoreCase(quantity))
			Update_Report("Pass", "Select quantity 5", "Quantity 5 is selected", driver);
		else
			Update_Report("Fail", "Select quantity 5", "Quantity 5 is not selected", driver);
		
		byVar=objDetails(41);//add to cart
		if(ReUsableMethods.browserName.equalsIgnoreCase("Edge"))
			clickButtonEdge(driver.findElement(byVar), getName(41));
		else
			clickButton(driver.findElement(byVar), getName(41));
		Thread.sleep(2000);
		
		byVar=objDetails(40);//number in cart
		String qty=driver.findElement(byVar).getText();
		Update_Report("Pass", "Quantity in cart", "The quantity in cart is "+qty, driver);
		
		byVar=objDetails(27);//cart button
		if(ReUsableMethods.browserName.equalsIgnoreCase("Edge"))
			clickButtonEdge(driver.findElement(byVar), getName(27));
		else
			clickButton(driver.findElement(byVar), getName(27));
		
		
		byVar=objDetails(42);//quantity dropdown in cart
		Select cartQty=new Select(driver.findElement(byVar));
		cartQty.selectByValue(updquantity);
		
		if(cartQty.getFirstSelectedOption().getText().equals(updquantity))
			Update_Report("Pass", "New Quantity", "New quantity selected is "+updquantity, driver);
		else
			Update_Report("Fail", "New Quantity", "Quantity "+ updquantity+" is not selected", driver);
		Thread.sleep(3000);
		
		byVar=objDetails(43);//save for later
		if(ReUsableMethods.browserName.equalsIgnoreCase("Edge"))
			clickButtonEdge(driver.findElement(byVar), getName(43));
		else
			clickButton(driver.findElement(byVar), getName(43));
		
		Thread.sleep(3000);
		
		byVar=objDetails(44);//saved later message
		String testResult=driver.findElement(byVar).getText();
		
		validateTestResult(expectedResult, testResult);
		
		bw.close();
		driver.close();
		
	}
	
	/*
	 * Name of the method : testCase10
	 * Brief Description : Search for iphone in categories
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : September 2, 2017
	 * Last Modified : September 2, 2017
	 */

	
	public static void testCase10() throws IOException, InterruptedException, AWTException
	{
		
		String filepath = "C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\AmazonAuto\\Test Data\\Test Case 10.xls";
		String sheetname = "Sheet1";
		String repopath="C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\AmazonAuto\\Object Repository.xls";
		String reposheet="Sheet1";
		
		

		String[][] exceldata = inputRead(filepath, sheetname);
		repoRead(repopath,reposheet);

		String URL = exceldata[1][1];
		String searchText=exceldata[1][2];
		
		/*
		 * in a loop
		 */
		
		launchURL(URL);
		
		byVar=objDetails(1);//search text box
		enterText(driver.findElement(byVar), searchText, getName(1));
		Thread.sleep(1000);
		
		byVar=objDetails(45);//search suggestions
		ArrayList<WebElement> ss=(ArrayList<WebElement>) driver.findElements(byVar);
		
		for(int i=0;i<3;i++)
		{
			String expectedResult=ss.get(i).getText();
			
			if(ReUsableMethods.browserName.equalsIgnoreCase("Edge"))
				clickButtonEdge(ss.get(i), ss.get(i).getText());
			else
				clickButton(ss.get(i), ss.get(i).getText());
			
			
			Thread.sleep(2000);
			
			byVar=objDetails(24);//all dropdown
			Select sel=new Select(driver.findElement(byVar));
			String testResult=sel.getFirstSelectedOption().getText();
			
			Thread.sleep(2000);
			if(expectedResult.startsWith("in "))
			{
				expectedResult=expectedResult.replace("in ", "");
				validateTestResult(expectedResult, testResult);
			}
			else
			{
				validateTestResult("All Departments", testResult);
			}
			Thread.sleep(2000);	
			
			if(i==2)
				break;
			
			launchURL(URL);
			byVar=objDetails(1);//search text box
			enterText(driver.findElement(byVar), searchText, getName(1));
			Thread.sleep(1000);
			
			byVar=objDetails(45);//search suggestions
			ss=(ArrayList<WebElement>) driver.findElements(byVar);
		}
		
		bw.close();
		driver.close();
	}
	
	/*
	 * Name of the method : launchURL
	 * Brief Description : To launch the URL 
	 * Arguments : url -> The URL to be launched 
	 * Created By : Roshini Padma Sendhil Kumar
	 * Creation Date : September 2, 2017
	 * Last Modified : September 2, 2017
	 */
	public static void launchURL(String url)
	{
			
		
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	
}
