package com.edusoln.pom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.edusoln.base.CommonFunctions;
import com.edusoln.base.LoggingDemo;

public class LoginPage   {
	
	WebDriver driver;	
	@FindBy(name="username")  WebElement userid;
	@FindBy(name="password")  WebElement password;
	@FindBy(xpath="//button[@type='submit']")WebElement login_btn;

	static Logger log=LogManager.getLogger(LoginPage.class);
	
	public LoginPage(WebDriver driver){
		this.driver=driver;		
		PageFactory.initElements(driver, this);		
	}
	
	public void login(ExtentTest test) {
		userid.sendKeys("Admin");
		test.log(Status.INFO, "user id is entered");
		password.sendKeys("admin123");
		test.log(Status.INFO, "Password is entered");
		login_btn.click();
		test.log(Status.INFO, "Login button is clicked");
	}
	
}
