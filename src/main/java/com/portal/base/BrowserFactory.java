package com.portal.base;

import org.apache.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserFactory {
public static WebDriver getDriver(String browser, Logger log) {
	
		
		WebDriver driver;
		log.info("Staring"+browser+"Driver");
		
		
		switch (browser) {
		case "IE":
		System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\src\\main\\resources\\IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		break;

		case "chrome":
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir")+"\\src\\main\\resources\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
			
			
		default:
			System.setProperty("webdriver.chrome.driver",
					("user.dir")+"\\src\\main\\resources\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
		}
		driver.manage().window().maximize();
		
		
		return driver;
}}
