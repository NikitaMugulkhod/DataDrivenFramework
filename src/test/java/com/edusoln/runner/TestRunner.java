package com.edusoln.runner;

import java.io.IOException;
import java.util.Map;
import java.util.logging.LogManager;

import org.apache.logging.log4j.util.Constants;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.edusoln.base.CommonFunctions;
import com.edusoln.pom.LoginPage;
import com.edusoln.utilities.ExcelReader;
import com.edusoln.utilities.ExtentReportGenerator;
import com.edusoln.utilities.PropertyReader;

import freemarker.log.Logger;

public class TestRunner   extends CommonFunctions {
	public static WebDriver driver;
	public static ExtentTest test;
	ExtentReports report;
	//static Logger log=LogManager.getLogger(TestRunner.class);

	
	
	@BeforeTest
	public void beforeTest() {
		report = ExtentReportGenerator.extentReportsetup();
	}
	
  	@BeforeMethod
  	public void browserSetup() {
  		driver = CommonFunctions.browserLaunch(PropertyReader.getProperty("browser"));
  		driver.get(PropertyReader.getProperty("baseUrl"));
  	}

  	
  	

	@DataProvider(name = "dp")
	public Object[][] datasupplier() throws IOException {

		Object[][] data = ExcelReader.excelReader();

		return data;
	}
		
		
		@Test(enabled = true, dataProvider = "dp")
		public void login(Map<Object, Object> data) 
		{
		     test=report.createTest("Login verification");
			 LoginPage login=new LoginPage(driver);
			 login.login(test);
			 test.log(Status.PASS, "Login is sucessful");
			 
		}
		
		@AfterMethod
		public void teardown() {
		 driver.quit();
		}
		
		@AfterTest
		public void afterTest() {
		report.flush();	
		}
	}


