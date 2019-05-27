package com.portal.test;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.portal.base.CsvDataProvider;
import com.portal.base.BaseTest;
import com.portal.pages.LoginPage;
import com.portal.pages.StudentProfilePage;
public class Testing extends BaseTest {
@Test(priority = 1, groups = ("Positive Test"))
public void positiveLoginTest() throws InterruptedException {
	
	String expectedTitle="MAJU : Announcements";
	String correctProfileName="SYED OVAIS HUSSAIN AKHTAR";
	LoginPage loginPage=new LoginPage(driver,log);
	String timeTableHeading="My Time Table";
	String CoursesPageHeading="My Courses";
	extentLogger=reports.createTest("Login Test");
	//getting page
	loginPage.openHomePage();
	log.info("Web page Opened");
	extentLogger.info("Web Page Opened!!");
	//filling fields
	loginPage.fillUpFields("Sp16-Bs-0029","TestingAuto123");
	log.info("Email and Password Filled");
	extentLogger.info("Username and Password Filled");
	//pushing login button
	StudentProfilePage profilePage=loginPage.pushSignIn();
	log.info("Sign In Button Pushed!!!");
	extentLogger.info("Clicked Sign In Button");
	extentLogger.pass("Login Pass");
	//navigating because i don't want anyone too see my CGPA
	driver.navigate().to("https://majuonline.edu.pk/student/Student/viewAnnouncements.htm");
	//uses thread.sleep because i want page to wait so i can logout if i want(Just creating here but i'll use at the end)
	
	Thread.sleep(5000);
	//waiting for student profile page to load
	profilePage.waitForStudentProfilePageToLoad();
	
	//Verifications
	
	//Verifying Title i.e MAJU : Announcements
	
	log.info("Verification ");
	extentLogger.info("Verification ");
	String actualTitle=profilePage.getTitle();
	Assert.assertTrue(expectedTitle.equals(actualTitle),"Page Title isn't expected.\n Expected:"+expectedTitle+
			"\nActual:"+actualTitle);
	extentLogger.info("Checking Page Title");
	//Thread.sleep(10000);
	//Verifying Correct Name of Student
	Assert.assertTrue(profilePage.isProfileNameCorrect(correctProfileName), "Student Name isn't expected");
	extentLogger.info("Checking Profile Name");
	extentLogger.info("Opening Time Table Page");
	//Checking Time Table Page
	Assert.assertTrue(profilePage.isTimetablePageOpened(timeTableHeading), "Time Table Page Not Verified");
	extentLogger.pass("Asserted Time Table Page");
	//TimeTable timeTable=new TimeTable()
	//Checking My Courses Page
	extentLogger.info("Opening My Courses Page");
	Assert.assertTrue(profilePage.myCoursesPage(CoursesPageHeading), "Courses Page Not Verified");
	extentLogger.pass("Asserted Courses Page");
	//Thread.sleep(1000000);
	extentLogger.info("Logging Out");
	profilePage.signedOut();
	extentLogger.pass("Logged Out");
	Thread.sleep(1000000);
}

@Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class, priority = 2, groups = {"Negative Test","broken"})
public void negativeLoginTest(Map<String, String> testData) {
	String expectedErrorMessage=" Invalid User/Password";
	String email=testData.get("email");
	String password=testData.get("password");
	String testNumber=testData.get("sno");
	String description=testData.get("description");
	System.out.println("Test Number:"+testNumber+" "+"for"+" "+description+"\n"+"Email:"+email+
			"\nPassword:"+password);
	
	LoginPage loginPage=new LoginPage(driver,log);
	extentLogger=reports.createTest("Negative Login Test");
	loginPage.openHomePage();
	extentLogger.info("Web Page Opened!!");
	loginPage.fillUpFields(email,password);
	log.info("Email and Password Filled: Readed from csv file");
	extentLogger.info("Username and Password Filled : Readed from csv file");
	//pushing login button
	loginPage.pushSignIn();
	extentLogger.info("Clicked Sign In Button");
	extentLogger.fail("Login Failed");
	//String expectedErrorMessage=" Invalid User/Password";
	String errorMessage=loginPage.getLoginErrorMessage();
	Assert.assertTrue(expectedErrorMessage.contains(errorMessage),"Error Message isn't expected.\n Expected:"+expectedErrorMessage+
			"\nActual:"+errorMessage);

}
	
}
