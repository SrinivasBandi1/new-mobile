/**
 * Author: Shweta Naik 
 * Description: Test class for verifying App Setup functionalities.
 */
package com.intelehealth.tests;

import static org.testng.Assert.assertFalse;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.intelehealth.base.BaseTest;
import com.intelehealth.pages.AppSetupPage;
import com.intelehealth.reports.ExtentReport;
import com.intelehealth.utils.TestUtils;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("App Setup Module")
@Feature("Introductory Screens and Language  Selection")
@Owner("Srinivas Bandi")
public class AppSetupTest extends BaseTest {
	TestUtils utils = new TestUtils();
	AppSetupPage appSetupPage;
	JSONObject appData;

	@BeforeMethod
	public void beforeMethod(Method m) throws Exception {
		utils.log().info("\n" + "****** starting test:" + m.getName() + "******" + "\n");
		resetApp();
		launchApp();

		appSetupPage = new AppSetupPage();
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
		Thread.sleep(2000);
//		// grant all the permissions
		appSetupPage.handlePermissions();
////           	appSetupPage.selectLanguage();
//		// clicks on next button
		appSetupPage.clickOnNextButton();
	}

	@Test(priority = 1, description = "Verify user can select any of the language and click Next", enabled = true)
	@Description("Verifies that the user can select any language from the available options and navigate through the introductory screen.")
	@Severity(SeverityLevel.NORMAL)
	@Story("Language Selection and Navigation")
	public void IDA4_1939_verifySelectLanguage() throws InterruptedException {

		ExtentReport.startTest("IDA4_1939_verifySelectLanguage",
				"Verify user can select any of the language and click Next");

		Assert.assertTrue(appSetupPage.verifyIntroductoryScreen());

		Assert.assertTrue(appSetupPage.skipButtonIsDisplayed());

		Assert.assertEquals(appSetupPage.getIntroductoryScreenText(),

				Arrays.asList(expectedAssertProp.getProperty("introductory.screen.whoarewetext"),
						expectedAssertProp.getProperty("introductory.screen.takepatientvisitstext"),
						expectedAssertProp.getProperty("introductory.screen.provideprescriptionstext")));

	}

	@Test(priority = 2, description = " Verify clicking on Skip on the introductory screens", enabled = true)
	public void IDA4_1940_verifySkipOnTheIntroductoryScreen() throws InterruptedException {
		ExtentReport.startTest("IDA4_1939_verifySelectLanguage",
				" Verify clicking on Skip on the introductory screens");

		appSetupPage.clickOnSkipButton();

		// Verify elements on Ayu screen are displayed
		Assert.assertTrue(appSetupPage.verifyAyuScreen());

		/**
		 * remove list and make it as a signle string when we execute as there is a
		 * single xpath
		 */
		Assert.assertEquals(appSetupPage.getPolicyTexts(),
				Arrays.asList(expectedAssertProp.getProperty("ayu.screen.termsandconditionstext"),
						expectedAssertProp.getProperty("ayu.screen.privacypolicytext")));

		Assert.assertEquals(appSetupPage.getAyuScreenText(),
				Arrays.asList(expectedAssertProp.getProperty("ayu.screen.title"),
						expectedAssertProp.getProperty("ayu.screen.subtitle"),
						expectedAssertProp.getProperty("ayu.screen.setup.text")));

	}

	@Test(priority = 3, description = "Verify that user is able to navigate to setup page when the terms & conditions box is checked", enabled = true)
	public void IDA4_1943_verifyTermsAndConditionCheckBox() throws InterruptedException {

		// Click on the "Skip" button to bypass initial setup
		appSetupPage.clickOnSkipButton();
		/**
		 * Needed one attribute For status checked
		 */
		Assert.assertFalse(appSetupPage.isEnabledSetUpButton());
		// Click on the checkbox to agree to terms and conditions
		appSetupPage.clickOnCheckBox();
		Assert.assertTrue(appSetupPage.isEnabledSetUpButton());

		// Click on the "Setup" button to proceed with the application setup
		appSetupPage.clickOnSetupButton();
		Assert.assertEquals(appSetupPage.getSetUpScreenLabels(),
				Arrays.asList(expectedAssertProp.getProperty("app.setup.screen.header"),
						expectedAssertProp.getProperty("app.setup.screen.please.enter.details"),
						expectedAssertProp.getProperty("app.setup.screen.location"),
						expectedAssertProp.getProperty("app.setup.screen.username"),
						expectedAssertProp.getProperty("app.setup.screen.password")));

	}

	@Test(priority = 4, description = "Verify clicking on Terms and Conditions/Privacy Policy link on the checkbox text", enabled = true)
	public void IDA4_1941_verifyTermsAndPrivacyPolicyScreen() throws InterruptedException {
		ExtentReport.startTest("IDA4_1941_verifyTermsAndPrivacyPolicyScreen",
				"Verify clicking on Terms and Conditions/Privacy Policy link on the checkbox text");
		// Click on the "Skip" button to bypass the initial setup
		appSetupPage.clickOnSkipButton();

		// Click on the link to view the Terms and Conditions
		appSetupPage.clickOnTermsAndCondition();

		// Verify that the Terms and Conditions screen is displayed
		Assert.assertTrue(appSetupPage.verifyTermsAndConditionScreen());

		Assert.assertEquals(appSetupPage.getTermsAndConditionsScreenElementsText(),
				Arrays.asList(expectedAssertProp.getProperty("terms.and.conditions.screen.title"),
						expectedAssertProp.getProperty("terms.and.conditions.screen.description"),
						expectedAssertProp.getProperty("terms.and.conditions.screen.accept.button"),
						expectedAssertProp.getProperty("terms.and.conditions.screen.decline.button")));

		// Navigate back from the Terms and Conditions screen
		appSetupPage.clickOnTermsAndConditionsBackArrow();
		/**
		 * Needed separate element locator for terms and conditions and privacy policy
		 */
		// Click on the link to view the Privacy Policy
		appSetupPage.clickOnPrivacyPolicy();

		// Verify that the Privacy Policy screen is displayed
		Assert.assertTrue(appSetupPage.verifyPrivacyPolicyScreen());

		Assert.assertEquals(appSetupPage.getPrivacyPolicyScreenElementsText(),
				Arrays.asList(expectedAssertProp.getProperty("privacy.policy.screen.title"),
						expectedAssertProp.getProperty("privacy.policy.screen.description"),
						expectedAssertProp.getProperty("terms.and.conditions.screen.accept.button"),
						expectedAssertProp.getProperty("terms.and.conditions.screen.decline.button")));

	}

	@AfterMethod
	public void afterMethod() {
		System.gc();
		// Close the app after each test method
		closeApp();
	}

}
