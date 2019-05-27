package com.portal.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.portal.base.BasePageObject;

public class StudentProfilePage extends BasePageObject<StudentProfilePage> {
	private By announcements=By.xpath("//div[@class='document-Inner']");
	private By term=By.xpath("//span[@class='term']");
	private By StudentProfileName=By.xpath("//*[@id='header']/div/div/span[3]");
	//private By menuButton=By.xpath("//*[@id=\"header\"]/div/div/a[1]/img");
	private By timeTable=By.xpath("//*[@id=\"content-wrapper\"]/div[2]/div/div/h1");
	private By mycourses=By.xpath("//*[@id=\"content-wrapper\"]/div[2]/div/h1");
	private By logOut=By.xpath("//*[@id=\"menu\"]/div/ul/li[4]/a[2]");
	protected StudentProfilePage(WebDriver driver,Logger log) {
		super(driver,log);
		// TODO Auto-generated constructor stub
	}

	public void waitForStudentProfilePageToLoad() {
		waitForVisibility(announcements);
		waitForVisibility(term,20);
		
	}
	public boolean isProfileNameCorrect(String ProfileName){
		if(getText(StudentProfileName).equals(ProfileName)) {
	//System.out.println(correctProfileName);
		return true;
		}
		else
		{return false;}
}
	
	public boolean isTimetablePageOpened(String heading){
		driver.navigate().to("https://majuonline.edu.pk/student/Student/viewTimeTable.htm");
		if(getText(timeTable).equals(heading)) {
	//System.out.println(correctProfileName);
		return true;
		}
		else
		{return false;}
		
}
	
	public boolean myCoursesPage(String coursesText){
		driver.navigate().to("https://majuonline.edu.pk/student/Student/viewMyCourses.htm");
		if(getText(mycourses).equals(coursesText)) {
	//System.out.println(correctProfileName);
		return true;
		}
		else
		{return false;}
	
	
}
	public void signedOut()
	{
		
		click(logOut);
		
	}
	
	}