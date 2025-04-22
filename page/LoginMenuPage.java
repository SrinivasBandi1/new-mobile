package com.intelehealth.pages;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.intelehealth.base.BaseTest;
import com.intelehealth.reports.ExtentReport;

import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginMenuPage extends BaseTest {
	// Elements on the Login Menu Page
	@AndroidFindBy(accessibility = "Login Screen Username Edittext")
	private WebElement loginScreenUserName;
	@AndroidFindBy(accessibility = "Login Screen Password Edittext")
	private WebElement loginScreenPassword;
	@AndroidFindBy(accessibility = "Login Screen Login Button")
	private WebElement loginButton;
	@AndroidFindBy(accessibility = "Login Screen Welcome Back! TextView")
	private WebElement loginScreenWelcomeBackTitle;

	@AndroidFindBy(accessibility = "Custom Toolbar Back Arrow ImageView")
	private WebElement menu;
	@AndroidFindBy(accessibility = "Navigation Header Home User Profile ImageView")
	private WebElement profile;
	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='My Achievements']")
	private WebElement myAchievements;
	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='Change language & protocol']")
	private WebElement changeLanguageAndProtocol;
	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='About us']")
	private WebElement aboutUs;
	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='Log-out']")
	private WebElement logOut;
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@resource-id='org.intelehealth.app:id/llResetAppHomeScreen']")
	private WebElement resetApp;
	@AndroidFindBy(accessibility = "Book Appointment Dialog Title TextView")
	private WebElement logOutPopup;
	@AndroidFindBy(accessibility = "Book Appointment Dialog Positive (Yes) Button")
	private WebElement yesButton;
	@AndroidFindBy(accessibility = "Navigation Header Home User Username TextView")
	private WebElement name;
	@AndroidFindBy(accessibility = "Navigation Header Home User ID TextView")
	private WebElement healthworkerID;
	@AndroidFindBy(accessibility = "Navigation Header Home 'Edit Profile' TextView")
	private WebElement editProfileLink;
	@AndroidFindBy(accessibility = "Navigation Header Home User Profile ImageView")
	private WebElement image;
	@AndroidFindBy(accessibility = "Custom Toolbar Location Name TextView")
	private WebElement locationName;
	@AndroidFindBy(accessibility = "Patient Registration Dialog Title TextView")
	private WebElement synchingApp;
	@AndroidFindBy(accessibility = "Patient Registration Dialog Positive (Yes) Button")
	private WebElement resetAppYesButton;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Sync successful']")
	private WebElement syncSuccessful;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Reset  App']")
	private WebElement resetAppPopup;
	
	
	
	JSONObject appData;
	public LoginMenuPage() throws IOException{
		InputStream datais = null;
		try {
			 // Load test data from JSON fil
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
	}
	// Methods for Page Actions

	
	// Click on the hamburger menu icon
	public void clickOnHamburgerMenu() throws InterruptedException {
		
	    click(menu);
	}



	// Click on the Log Out button
	public void clickOnLogOut() throws InterruptedException {
	    click(logOut, "Clicked on the Log Out button");
	}

	// Verify if the "Welcome Back" title is displayed on the login screen
	public void verifyWelcomeBackTitle() throws InterruptedException {
	    isDisplayed(loginScreenWelcomeBackTitle, "Welcome Back title is displayed on the login screen");
	}



	// Perform login with the provided username and password
	public void login() throws InterruptedException {
		   String originalUserName = appData.getJSONObject("validUser").getString("username");
		    String originalPassword = appData.getJSONObject("validUser").getString("password");
		    
			  System.out.println(originalPassword);
			  System.out.println(originalUserName);
			  // Encrypt the password
		        String encryptedUserName = encrypt(originalUserName);
		        System.out.println("Encrypted UserName: " + encryptedUserName);
		        String decryptedUserName = decrypt(encryptedUserName);
		        System.out.println("Decrypted Password: " + decryptedUserName);
		        String encryptedPassword = encrypt(originalPassword);
		        System.out.println("Encrypted Password: " + encryptedPassword);
		        String decryptedPassword = decrypt(encryptedPassword);
		        System.out.println("Decrypted Password: " + decryptedPassword);
	    // Enter the username into the username field
	    sendKeys(loginScreenUserName, decryptedUserName, "Entered username: " + decryptedUserName);

	    // Enter the password into the password field
	    sendKeys(loginScreenPassword, decryptedPassword, "Entered password: " +decryptedPassword);
      ExtentReport.getTest().log(Status.INFO, "Entered the username and password");
	    // Click the login button to submit the credentials
	    click(loginButton, "Clicked on the Login button");
	    
	    
	}
	public void loginWithNewPassword(String pw) {
		   String originalUserName = appData.getJSONObject("validUser").getString("username");
		   System.out.println(originalUserName);
			  // Encrypt the password
		        String encryptedUserName = encrypt(originalUserName);
		        System.out.println("Encrypted UserName: " + encryptedUserName);
		        String decryptedUserName = decrypt(encryptedUserName);
		        System.out.println("Decrypted Password: " + decryptedUserName);
		        sendKeys(loginScreenUserName, decryptedUserName, "Entered username: " + decryptedUserName);
		        sendKeys(loginScreenPassword, pw, "Entered password: " +pw);
		        click(loginButton, "Clicked on the Login button");
	}
	

	// Verify elements on the Menu Page
	public void verifyMenuPageElements() throws InterruptedException {
	    isDisplayed(profile, "Profile element is displayed on the Menu Page");
	    isDisplayed(myAchievements, "My Achievements element is displayed on the Menu Page");
	    isDisplayed(changeLanguageAndProtocol, "Change Language and Protocol element is displayed on the Menu Page");
	    isDisplayed(aboutUs, "About Us element is displayed on the Menu Page");
	    isDisplayed(resetApp, "Reset App element is displayed on the Menu Page");
	    isDisplayed(logOut, "Log Out element is displayed on the Menu Page");
	}



	// Verify user profile details on the Profile Page
	public void verifyUserProfileDetails() throws InterruptedException {
	    isDisplayed(name, "User's name is displayed on the Profile Page");
	    isDisplayed(healthworkerID, "Healthworker ID is displayed on the Profile Page");
	    isDisplayed(editProfileLink, "Edit Profile link is displayed on the Profile Page");
	    isDisplayed(image, "User's profile image is displayed on the Profile Page");
	}

	// Click on the "Yes" button
	public void clickOnYes() throws InterruptedException {
	    click(yesButton, "Clicked on the Yes button");
	}
	/**
	 * Updated by Srinivas
	 * @return
	 * @throws InterruptedException
	 */
	// Verify if the location name is displayed
	public boolean verifyLocationIsDisplayed() throws InterruptedException {
	 return   isDisplayed(locationName, "Location name is displayed");
	}
	// Verify logout functionality
	public void verifyLogout() throws InterruptedException {
	    // Click on the logout button
	    click(logOut, "Clicked on the Logout button");

	    // Click on the "Yes" button to confirm logout
	    click(yesButton, "Confirmed logout by clicking on Yes button");

	    // Introducing a brief delay for the UI to update (use of Thread.sleep should be minimized)
	    Thread.sleep(2000);

	    // Verify if the login screen's welcome back title is displayed
	    isDisplayed(loginScreenWelcomeBackTitle, "Verified the display of the login screen's welcome back title");
	}
	// Verify reset app functionality
	public void verifyResetAppFunctionality() throws InterruptedException {
	    // Click on the reset app button
	    click(resetApp, "Clicked on the Reset App button");

	    // Click on the "Yes" button to confirm app reset
	    click(resetAppYesButton, "Confirmed app reset by clicking on Yes button");

	    // Verify if the app is synchronizing
	    isDisplayed(synchingApp, "Verified that the app is synchronizing");

	   
	}
	
	
}