package com.portal.base;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.portal.base.BrowserFactory;

public class BaseTest {

	protected WebDriver driver;
	protected Logger log;
	public ExtentReports reports;
	public ExtentTest extentLogger;

	@BeforeClass(alwaysRun = true)
	protected void setUp(ITestContext ctx) {

		String testName = ctx.getCurrentXmlTest().getName();
		log = Logger.getLogger(testName);

	}

	@Parameters({ "browser" })
	@BeforeMethod(alwaysRun=true)
	protected void settingUp(String browser) {

		ExtentHtmlReporter reporter = new ExtentHtmlReporter(
				System.getProperty("user.dir") + "\\Reports\\Extreport.html");
		reports = new ExtentReports();
		reports.attachReporter(reporter);

		log.info("Setting Up!!");

		driver = BrowserFactory.getDriver(browser,log);

	}

	@AfterMethod(alwaysRun=true)
	protected void tearingDown() {
		log.info("Tearing Down!!");
		driver.quit();
		reports.flush();

	}

}
