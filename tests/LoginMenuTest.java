package com.intelehealth.tests;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import com.intelehealth.base.BaseTest;
import com.intelehealth.pages.AppSetupPage;
import com.intelehealth.pages.EditProfilePage;
import com.intelehealth.pages.LoginMenuPage;
import com.intelehealth.utils.TestUtils;

public class LoginMenuTest extends BaseTest {
	TestUtils utils = new TestUtils();
	AppSetupPage appSetupPage;
	LoginMenuPage loginMenuPage;
	EditProfilePage editProfilePage;
	JSONObject appData;

	@BeforeMethod
	public void beforeMethod(Method m) throws IOException, InterruptedException {
		utils.log().info("\n" + "****** starting test:" + m.getName() + "******" + "\n");
		resetApp();
		
		launchApp();

		
		appSetupPage = new AppSetupPage();
		loginMenuPage = new LoginMenuPage();
		editProfilePage=new EditProfilePage();
		
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
		
		 
		//grant all permissions
		appSetupPage.handlePermissions();
		// Perform the complete setup using the obtained username and password
		appSetupPage.completeSetup();
				appSetupPage.refreshUIAndWait();
		// Click on the hamburger menu in the login menu page
		loginMenuPage.clickOnHamburgerMenu();
	}
	
	
	@Test(priority = 1, description = " Verify if a user will be able to login with a valid username and valid password.", enabled = true)
	public void IDA4_1950_verifyLoginWithValidUsernameAndPassword() throws InterruptedException {

		// Click on the "Log Out" option in the login menu page
		loginMenuPage.clickOnLogOut();

		// Pause execution for 2000 milliseconds (2 seconds)
		Thread.sleep(2000);

		// Confirm the logout action by clicking on "Yes" in the login menu page
		loginMenuPage.clickOnYes();

		// Log back in using valid user credentials from appData
		loginMenuPage.login();
		Thread.sleep(5000);
		loginMenuPage.verifyLocationIsDisplayed();

	}

	@Test(priority = 2, description = "Verify if clicking on the Hamburger menu open the menu page", enabled = true)
	public void IDA4_1957_verifyHamburgerMenu() throws InterruptedException {

		// Click on the "Log Out" option in the login menu page
		loginMenuPage.clickOnLogOut();

		// Confirm the logout action by clicking on "Yes" in the login menu page
		loginMenuPage.clickOnYes();

		// Log back in using valid user credentials from appData
		loginMenuPage.login();
		loginMenuPage.clickOnHamburgerMenu();
		// Verify the elements on the menu page to ensure successful login
		loginMenuPage.verifyMenuPageElements();

	}

	@Test(priority = 3, description = "Verify that user profile is displayed on top of the menu page", enabled = true)
	public void IDA4_1959_verifyUserProfile() throws InterruptedException {

		// Click on the "Log Out" option in the login menu page
		loginMenuPage.clickOnLogOut();

		// Confirm the logout action by clicking on "Yes" in the login menu page
		loginMenuPage.clickOnYes();

		// Log back in using valid user credentials from appData
		loginMenuPage.login();
		
		loginMenuPage.clickOnHamburgerMenu();

		// Verify the user profile details on the menu page after logging back in
		loginMenuPage.verifyUserProfileDetails();

	}
//	logout and reset app 
	@Test(priority = 4, description = "Verify the User can logout from app", enabled = true)
	public void IDA4_2050_verifyLogoutFunctionality() throws InterruptedException {
		loginMenuPage.verifyLogout();
	}
	
	
	
	@Test(priority = 5, description = "Verify if user can Reset the App", enabled = true)
	public void IDA4_2052_verifyResetApp() throws InterruptedException {
		loginMenuPage.verifyResetAppFunctionality();
	}
//	@Test(priority = 6, description = "Verify if a user is able to login with a new password only after he/she has changed the password", enabled = true)
//	public void IDA4_2052_verifyUserAbleToLoginWithNewPassword() throws InterruptedException {
//		editProfilePage.verifyUserAbleToLoginWithNewPassword();
//		editProfilePage.resetThePasswordToOldPassword();
//	}
	@AfterMethod
	public void afterMethod() {
		System.gc();
		// Close the app after each test method
		closeApp();

	}
}
