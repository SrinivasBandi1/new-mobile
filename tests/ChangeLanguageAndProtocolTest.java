package com.intelehealth.tests;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.intelehealth.base.BaseTest;
import com.intelehealth.pages.AddNewPatientPage;
import com.intelehealth.pages.AppSetupPage;
import com.intelehealth.pages.ChangeLanguageAndProtocolPage;
import com.intelehealth.pages.LoginMenuPage;
import com.intelehealth.reports.ExtentReport;
import com.intelehealth.utils.TestUtils;

public class ChangeLanguageAndProtocolTest extends BaseTest {
	TestUtils utils = new TestUtils();
	AppSetupPage appSetupPage;
	ChangeLanguageAndProtocolPage changeLanguageAndProtocolPage;
	JSONObject appData;
	AddNewPatientPage addnewPatientPage;

	@BeforeMethod
	public void beforeMethod(Method m) throws IOException, InterruptedException {
		utils.log().info("\n" + "****** starting test:" + m.getName() + "******" + "\n");
		resetApp();

		launchApp();
		System.gc();
		appSetupPage = new AppSetupPage();
		addnewPatientPage = new AddNewPatientPage();

		changeLanguageAndProtocolPage = new ChangeLanguageAndProtocolPage();
		InputStream datais = null;
		try {
			String dataFileName = "data/appData.json";
			datais = getClass().getClassLoader().getResourceAsStream(dataFileName);
			JSONTokener tokener = new JSONTokener(datais);
			appData = new JSONObject(tokener);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (datais != null) {
				datais.close();
			}
		}
		// grant all permissions
		appSetupPage.handlePermissions();
		// Perform the complete setup using the obtained username and password
		appSetupPage.completeSetup();
		Thread.sleep(4000);

	}

	/**
	 * Updated by @srinivasBandi
	 * 
	 */
	@Test(priority = 1, description = "Verify that User can select the language from the dropdown", enabled = true)
	public void IDA4_2032_verifyLanguageSelectionFromDropdown() throws InterruptedException {

		ExtentReport.startTest("IDA4_2032_verifyLanguageSelectionFromDropdown",
				"Verify that User can select the language from the dropdown");

		// verify that the user can select a language on the Change Language and
		// Protocol page
		changeLanguageAndProtocolPage.navigateToChangeLanguageScreen();
		changeLanguageAndProtocolPage.selectHindiLanguage();
		changeLanguageAndProtocolPage.getchangeLanguageConfirmationDialogueLabels();
		changeLanguageAndProtocolPage.clickOnYes();

		Assert.assertEquals(changeLanguageAndProtocolPage.getLanguageChangedSuccessFullyText(),
				expectedAssertProp.getProperty("change.language.screen.changed.successfully.message.hindi"));

	}

	/**
	 * @author @srinivasBandi
	 * 
	 */
	@Test(priority = 2, description = "Verify the sync functionality in Language selection & protocol page", enabled = true)
	public void IDA4_2039_verifyLanguageSyncFunctionality() throws InterruptedException {

		ExtentReport.startTest("IDA4_2039_verifyLanguageSyncFunctionality",
				"Verify the sync functionality in Language selection & protocol page");

		String oldSyncTime = addnewPatientPage.clickOnSync();
		changeLanguageAndProtocolPage.navigateToChangeLanguageScreen();
		changeLanguageAndProtocolPage.clickOnSync();
		getDriver().navigate().back();
		String newSyncTime = addnewPatientPage.clickOnSync();

		Assert.assertNotEquals(oldSyncTime, newSyncTime);

	}

	/**
	 * @author @srinivasBandi
	 * 
	 */
	@Test(priority = 3, description = "Verify that Reset option reset the language to English", enabled = true)
	public void IDA4_2033_verifyResetLanguageToEnglish() throws InterruptedException {
		ExtentReport.startTest("IDA4_2033_verifyResetLanguageToEnglish",
				"Verify that Reset option reset the language to English");
		changeLanguageAndProtocolPage.navigateToChangeLanguageScreen();
		changeLanguageAndProtocolPage.clickOnSync();

		Assert.assertEquals(changeLanguageAndProtocolPage.getLanguageChangedSuccessFullyText(),
				expectedAssertProp.getProperty("change.language.screen.changed.successfully.message"));

	}

	/**
	 * @author @srinivasBandi
	 * 
	 */
	@Test(priority = 4, description = "Verify that user can update using valid Server url and License key", enabled = true)
	public void IDA4_2035_VerifyUpdateWithValidServerURLAndLicenseKey() throws InterruptedException {
		ExtentReport.startTest("IDA4_2035_VerifyUpdateWithValidServerURLAndLicenseKey",
				"Verify that user can update using valid Server url and License key");
		changeLanguageAndProtocolPage.navigateToChangeLanguageScreen();
		changeLanguageAndProtocolPage.clickOnUpdateProtocolInChangeLanguage();
		/**
		 * @Need Test Data Here
		 */
		changeLanguageAndProtocolPage.enterServerURL("");
		changeLanguageAndProtocolPage.enterLicenseKey("");
		changeLanguageAndProtocolPage.clickOnUpdateProtocolButton();
		/**
		 * Will add the assertion
		 */
	}
}