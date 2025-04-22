package com.intelehealth.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;

import com.google.common.collect.ImmutableMap;
import com.intelehealth.base.BaseTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class FindPatientPage extends BaseTest {
	
	// Page elements using AndroidFindBy annotation
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Find Patients']")
	private WebElement findPatients;
	
	@AndroidFindBy(accessibility = "Find Patient Screen Search Bar EditText")
	private WebElement findPatientSearchBar;
	
	@AndroidFindBy(accessibility = "Find Patient Screen Search ImageView")
	private WebElement findPatientSearchIcon;

	@AndroidFindBy(accessibility = "//android.widget.TextView[@text='Automation Rana']")
	private WebElement automationOnePatient;

	@AndroidFindBy(accessibility = "No Patient Found Placeholder Title TextView -Patient not found ")
	private WebElement patientNotFound;

	@AndroidFindBy(accessibility = "No Patient Found Placeholder Subtitle TextView-description")
	private WebElement noPatientFoundSubTitle;

	@AndroidFindBy(accessibility = "No Patient Found Placeholder 'Add New Patient' Text TextView-Add new patient")
	private WebElement addNewPatientCTAButton;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"Find Patient List Item Name TextView\"])[1]")
	private WebElement lblPatientCard;
	
//	@AndroidFindBy(accessibility = "No Patient Found Placeholder Icon ImageView")
//	private WebElement findPatient;

	// Method to click on the "Find Patient" element
	public void clickOnFindPatient() {
		click(findPatients);
	}
	public void clickOnFirstPatientCard() {
		click(lblPatientCard);
	}


// Method to enter a value in the search box and press Enter
	public void enterValueInSearchBox(String txt) throws InterruptedException {
		Thread.sleep(4000);
//	sendKeys(findPatientSearchBar,"Automation Rana");
		click(findPatientSearchBar);
		
//   Thread.sleep(2000);
//executeCommand("adb shell input keyevent 84");
		 //sendKeys(findPatientSearchBar,((PressesKey) getDriver()).pressKey(new
		// KeyEvent(AndroidKey.ENTER)));

		// click(findPatientSearchBar);
		String textToEnter = "AutomationRana";
		String adbCommand = String.format("adb shell input text %s", txt);
		executeCommand(adbCommand);
		executeCommand("adb shell input keyevent 84");
		((PressesKey) getDriver()).pressKey(new
				KeyEvent(AndroidKey.ENTER));
//	//((PressesKey) getDriver()).pressKey(new KeyEvent(AndroidKey.TAB));
//	((PressesKey) getDriver()).pressKey(new KeyEvent(AndroidKey.ENTER));

		// ((PressesKey) getDriver()).pressKey(new
		// KeyEvent().withKey(AndroidKey.SEARCH));
		// pressEnter(findPatientSearchBar);
	}

	public static void executeCommand(String command) {
		try {
			Process process = Runtime.getRuntime().exec(command);
			process.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

// Method to verify the presence of the searched patient
	public void verifySearchedPatient() {
		isDisplayed(automationOnePatient, "Patient is Displayed");
	}

// Method to enter an invalid patient name in the search box and press Enter
	public void enterInvalidPatienTName(String txt) throws InterruptedException {
		sendKeys(findPatientSearchBar, txt);

	}

//Method to verify the elements when an invalid patient is searched
	public void verifyInvalidPatientSearch() {
		isDisplayed(patientNotFound, "Patient not founf msg is displayed");
		isDisplayed(noPatientFoundSubTitle, "");
		isDisplayed(addNewPatientCTAButton, "");
	}

// Method to verify if the search box is visible
	public boolean verifySearchBoxIsVisible() throws InterruptedException {
	return	isDisplayed(findPatientSearchBar);
	}
}
