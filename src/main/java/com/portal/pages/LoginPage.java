package com.portal.pages;

import org.apache.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.portal.base.BasePageObject;

public class LoginPage extends BasePageObject<LoginPage>{
	private static final String URL="https://majuonline.edu.pk/student/login.htm";
	private By emailField=By.xpath("//input[@id='login__username']");
	private By passwordField=By.xpath("//input[@id='login__password']");
	private By loginButton=By.xpath("//button[@class='btn']");
	private By loginErrorMessage=By.xpath("//*[@id=\"mesg\"]/div/strong");
	public LoginPage(WebDriver driver, Logger log) {
		super(driver,log);
		// TODO Auto-generated constructor stub
	}


	public void openHomePage() {
		
		getPage(URL);
		
		
	}
	public void fillUpFields(String email,String password) {
		
		type(email, emailField);
		type(password, passwordField);
		
		
	}
	public StudentProfilePage pushSignIn() {
		click(loginButton);
		return new StudentProfilePage(driver,log);
		
	}


	public String getLoginErrorMessage() {
		waitForVisibility(loginErrorMessage,10);
		return getText(loginErrorMessage);
	}
	
}
