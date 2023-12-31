package com.edusoln.pom;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.edusoln.base.CommonFunctions;

public class AssignLeavepage extends CommonFunctions {

	WebDriver driver;

	@FindBy(id = "menu_dashboard_index")
	public WebElement dashboard_menu;
	@FindBy(className = "quickLaunge")
	WebElement assignleave_btn;
	@FindBy(id = "assignleave_txtEmployee_empName")
	WebElement emp_name;
	@FindBy(xpath = "//div[@class='ac_results'][2]/ul/li")
	List<WebElement> result_list;
	@FindBy(id = "assignleave_txtLeaveType")
	WebElement dropdown_leavetype;
	@FindBy(id = "assignleave_leaveBalance")
	WebElement balance;
	@FindBy(id = "assignleave_txtFromDate")
	WebElement fromDate;
	@FindBy(id = "assignleave_txtToDate")
	WebElement toDate;
	@FindBy(id = "assignleave_txtComment")
	WebElement comment_textbox;
	@FindBy(id = "assignBtn")
	WebElement assign_btn;
	@FindBy(id = "leaveBalanceConfirm")
	WebElement popup;
	@FindBy(id = "confirmOkButton")
	WebElement ok_btn;

	//Logger log = LogManager.getLogger(AssignLeavepage.class);

	public AssignLeavepage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void AssignLeave() {

		//if (getStringData(data.get("AssignLeave")).equalsIgnoreCase("Yes")) {

			Assert.assertTrue(dashboard_menu.getText().equalsIgnoreCase("Dashboard"), " text mismatch");		
			log.info("user is on Dashbord page");
			cickElement(assignleave_btn);			
			emp_name.sendKeys(getStringData(data.get("Emp_name")));
			visibilityOfListElement(result_list);
			for (WebElement result : result_list) {
				if (result.getText().equalsIgnoreCase("Orange Test")) {
					log.debug("search result includes" + result.getText());
					result.click();
					break;
				}
			}
			test.log(Status.INFO, "Employee is selected");
			dropdown_leavetype.click();
			Select select = new Select(dropdown_leavetype);
			select.selectByVisibleText(getStringData(data.get("Leave_type")));
			log.debug("dropdown selcted");			
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", fromDate);
			fromDate.clear();
			fromDate.sendKeys(getStringData(data.get("FromDate")));		
			js.executeScript("arguments[0].click();", toDate);
			toDate.clear();
			toDate.sendKeys(getStringData(data.get("ToDate")));		
			comment_textbox.sendKeys(getStringData(data.get("Comment")));
			log.debug("Comment entered is" + comment_textbox.getText());
			Thread.sleep(1000);
			js.executeScript("arguments[0].click();", assign_btn);
			visibilityOfElement(popup);
			Assert.assertTrue(popup.isDisplayed(), "Pop is not displayed");
			log.warn("Warning pop is displayed");
			ok_btn.click();
			test.log(Status.PASS, "Leave assignment is Sucessfull");

		}

	}


