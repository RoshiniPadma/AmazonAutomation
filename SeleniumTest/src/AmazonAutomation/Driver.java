package AmazonAutomation;

import java.awt.AWTException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;

public class Driver {
	
	public static int r;
	public static int c;
	public static WebDriver driver;
	public static String suitpath;
	public static String suitsheet;

	public static void main(String[] args)
			throws IOException, InterruptedException, AWTException, NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		suitpath = "C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\AmazonAuto\\Test Suit.xls";
		suitsheet = "Sheet1";

		String[][] testcases = ReUsableMethods.inputRead(suitpath, suitsheet);

		for (r = 1; r < testcases.length; r++) {

			try {
				if (testcases[r][1].equalsIgnoreCase("Y")) {
					String TC = testcases[r][2];
					if (testcases[r][3].equalsIgnoreCase("Y")) {
						//for Chrome browser
						c=3;
						
						ReUsableMethods.startReport(TC,"C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\AmazonAuto\\Html Reports\\","Chrome");
						
						String initdriver = "initChrome";
						Method dr = ReUsableMethods.class.getMethod(initdriver);
						dr.invoke(dr);
						
						Method mthd = AutomationScripts.class.getMethod(TC);
						mthd.invoke(mthd);
					}
					
					if (testcases[r][4].equalsIgnoreCase("Y")) {
						//for Firefox browser
						c=4;
						ReUsableMethods.startReport(TC,"C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\AmazonAuto\\Html Reports\\","Firefox");

						String initdriver = "initFirefox";
						Method dr = ReUsableMethods.class.getMethod(initdriver);
						dr.invoke(dr);
						
						Method mthd = AutomationScripts.class.getMethod(TC);
						mthd.invoke(mthd);
					}
					
					if (testcases[r][5].equalsIgnoreCase("Y")) {
						//for IE browser
						c=5;
						ReUsableMethods.startReport(TC,"C:\\Users\\Zara\\Documents\\Roshini\\Selenium Training\\selenium automation\\AmazonAuto\\Html Reports\\","Edge");

						String initdriver = "initEgde";
						Method dr = ReUsableMethods.class.getMethod(initdriver);
						dr.invoke(dr);
						Method mthd = AutomationScripts.class.getMethod(TC);
						mthd.invoke(mthd);
					}
					
				}
				
			} catch (Exception e) {
				System.out.println(e.getCause());
			}

		}

		

	}

}
