package com.intelehealth.pages;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.WebElement;

import com.intelehealth.base.BaseTest;
import com.intelehealth.base.CustomElementNotFoundException;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AppSetupPage extends BaseTest {
	// Elements on the App Setup Page
	@AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_foreground_only_button")
	// xpath = "//android.widget.Button[@text='While using the app']")
	public WebElement whileUsingAppButton;

	@AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_all_button")
	public WebElement btnAllowAll;

	@AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_button")
	// xpath = "//android.widget.Button[@text='Allow']")
	public WebElement allowButton;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='ALLOW']")
	public WebElement pDAllowButton;

	@AndroidFindBy(id = "android:id/checkbox")
	public WebElement toggle;

	@AndroidFindBy(accessibility = "Back")
	public WebElement back;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Allow Intelehealth to take pictures and record video?']")
	public WebElement takePicturesTextView;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Allow Intelehealth to access your contacts?']")
	public WebElement accessContactsTextView;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Allow Intelehealth to make and manage phone calls?']")
	public WebElement managePhoneCallsTextView;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Allow Intelehealth to access photos and media on your device?']")
	public WebElement accessPhotosTextView;

	// Language Selection Elements
	// accessibility = "Splash Screen Bottom Language Panel Save Selected Language
	// Button"
//accessibility id	Splash Screen Bottom Language Panel Save Selected Language Button
	// xpath =
	// "//android.widget.Button[@resource-id=\"org.intelehealth.app:id/btn_next_to_intro\"]"
	@AndroidFindBy(id = "org.intelehealth.app:id/btn_next_to_intro")
	private WebElement nextButton;

	@AndroidFindBy(xpath = "//android.widget.RadioButton[@text='English']")
	private WebElement english;
	// Introductory Screen Elements

	// @AndroidFindBy(accessibility = "Intro Screen First Title TextView")
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tvIntroTitle']")
	private WebElement whoWeAre;

//	@AndroidFindBy(accessibility = "Intro Screen Second Title TextView")
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.intelehealth.app:id/tvIntroTitle']")
	private WebElement takePatientVisits;

	// this needs to be changed but same as above hence negotiating

//	@AndroidFindBy(accessibility = "Intro Screen Third Title TextView")
	@AndroidFindBy(id = "org.intelehealth.app:id/tvIntroTitle")

	private WebElement providePrescriptions;

	// @AndroidFindBy(accessibility = "Intro Screen Skip Screen Button")
	@AndroidFindBy(id = "org.intelehealth.app:id/btn_skip_intro")

	private WebElement skipButton;

	// Ayu Intro Screen Elements

//	@AndroidFindBy(accessibility = "Setup Ayu Intro Screen Hello TextView")
	@AndroidFindBy(id = "org.intelehealth.app:id/tv_hello_ayu")

	private WebElement helloIamAyu;

	@AndroidFindBy(accessibility = "Setup Ayu Intro Screen Subtitle TextView")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='A digital health assistant.Let’s get started!']")

	private WebElement ayuIntroScreenSubtitle;

	// @AndroidFindBy(accessibility = "Setup Ayu Intro Screen Accept TnC And PP
	// CheckBox")
	@AndroidFindBy(id = "org.intelehealth.app:id/checkbox_privacy_policy")

	private WebElement checkBox;

//	@AndroidFindBy(accessibility = "Setup Ayu Intro Screen Setup Button")
	@AndroidFindBy(id = "org.intelehealth.app:id/btn_setup")
	private WebElement setupButton;

	// @AndroidFindBy(accessibility = "Setup Ayu Intro Screen Accept TnC And PP
	// TextView 2")
	@AndroidFindBy(id = "org.intelehealth.app:id/checkbox_privacy_policy")
	private WebElement termsAndCondition;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_privacy_notice_link_1")
	private WebElement lblTermsAndPolicy;

	@AndroidFindBy(accessibility = "Setup Ayu Intro Screen Accept TnC And PP TextView 4")
	private WebElement privacyPolicy;

	// Terms and Conditions Screen Elements
	// @AndroidFindBy(accessibility = "Terms and Conditions Title TextView")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Terms & Conditions']")
	private WebElement termsAndConditionTitle;

	@AndroidFindBy(accessibility = "Terms and Conditions Content TextView")
	private WebElement termsAndConditionDescription;

//	@AndroidFindBy(accessibility = "Terms and Conditions Back Arrow ImageView")
	@AndroidFindBy(id = "org.intelehealth.app:id/iv_back_arrow_terms")

	private WebElement termsAndConditionsBackArrow;

	@AndroidFindBy(accessibility = "Terms and Conditions Decline Button")
	private WebElement termsAndConditionDeclineButton;

	@AndroidFindBy(accessibility = "Terms and Conditions Accept Button")
	private WebElement termsAndConditionAcceptButton;

	// Privacy Policy Screen Elements
	@AndroidFindBy(accessibility = "Privacy Policy Title TextView")
	private WebElement privacyPolicyTitle;

	@AndroidFindBy(accessibility = "Privacy Policy Content TextView")
	private WebElement privacyPolicyContent;

	@AndroidFindBy(accessibility = "Privacy Policy Back Arrow ImageView")
	private WebElement privacyPolicyBackArrow;

	@AndroidFindBy(accessibility = "Privacy Policy Decline Button")
	private WebElement privacyPolicyDeclineButton;

	@AndroidFindBy(accessibility = "Privacy Policy Accept Button")
	private WebElement privacyPolicyAcceptButton;

	// @AndroidFindBy(accessibility = "Setup Screen Username Edittext")
	@AndroidFindBy(id = "org.intelehealth.app:id/et_username")

	// Setup Screen Elements
	private WebElement username;

	// @AndroidFindBy(accessibility = "Setup Screen Password Edittext")
	@AndroidFindBy(id = "org.intelehealth.app:id/et_password")

	private WebElement password;

	// @AndroidFindBy(accessibility = "Show dropdown menu")
	@AndroidFindBy(id = "org.intelehealth.app:id/autotv_select_location")

	private WebElement dropdown;

	// @AndroidFindBy(xpath = "//android.widget.RelativeLayout[@content-desc='Setup
	// Screen Parent
	// RelativeLayout']/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView[2]")

	@FindBy(xpath = "org.intelehealth.app:id/autotv_select_location")

	private WebElement location;

	// @AndroidFindBy(accessibility = "Setup Screen Setup Button")
	@AndroidFindBy(id = "org.intelehealth.app:id/btn_setup")

	private WebElement setupScreeenSetupButton;

	@AndroidFindBy(id = "org.intelehealth.app:id/tv_user_location_home")
	private WebElement locationName;

	@AndroidFindBy(id = "org.intelehealth.app:id/imageview_is_internet")
	private WebElement refreshButton;

	@AndroidFindBy(accessibility = "Intelehealth")
	private WebElement intelehealth;
	@AndroidFindBy(id = "android:id/switch_widget")
	private WebElement toggleButton;

	@AndroidFindBy(accessibility = "Navigate up")
	private WebElement navigateBack;

	// @AndroidFindBy(accessibility = "Setup Screen Enter Details TextView")
	@AndroidFindBy(id = "org.intelehealth.app:id/enterDetailsTV")
	private WebElement lblPleaseEnterDetails;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Location']")
	private WebElement lblLocation;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Password']")
	private WebElement lblPassword;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Username']")
	private WebElement lblUsername;

	// @AndroidFindBy(accessibility = "Setup Screen Setup Title TextView")
	@AndroidFindBy(id = "org.intelehealth.app:id/tv_setup")

	private WebElement lblSetUpHeader;

	JSONObject appData;

	public AppSetupPage() throws IOException {
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
	}

	// Methods for Page Actions

	// Select language
	public void selectLanguage() {
		isSelected(english);

	}

	// Handles the permission prompts during app installation
	public void handlePermissions() throws InterruptedException {

		click(whileUsingAppButton);
		for (int i = 0; i < 3; i++) { // Adjust the loop count if needed
			click(allowButton);
		}
		click(btnAllowAll);

	}

	// Click on Next button
	public void clickOnNextButton() throws InterruptedException {
		click(nextButton);
		// "Clicked on Next Button");

	}

	// Click on Skip button
	public void clickOnSkipButton() throws InterruptedException {
		click(skipButton);
	}

	// Click on CheckBox
	public void clickOnCheckBox() throws InterruptedException {
		click(checkBox);
	}

	// Click on Setup button
	public void clickOnSetupButton() throws InterruptedException {
		click(setupButton);
	}

	// Click on Terms and Conditions Back Arrow
	public void clickOnTermsAndConditionsBackArrow() throws InterruptedException {
		click(termsAndConditionsBackArrow, "Clicked on Terms and Conditions Back Arrow");
	}

	// Click on Terms and Conditions
	public void clickOnTermsAndCondition() throws InterruptedException {
		click(termsAndCondition, "Clicked on Terms and Conditions");
	}

	// Click on Privacy Policy
	public void clickOnPrivacyPolicy() throws InterruptedException {
		click(privacyPolicy, "Clicked on Privacy Policy");
	}

	// Click on Privacy Policy Back Arrow
	public void clickOnPrivacyPolicyBackArrow() throws InterruptedException {
		click(privacyPolicyBackArrow, "Clicked on Privacy Policy Back Arrow");
	}

	// Verify elements on Introductory Screen
	/**
	 * @author @SrinivasBandi
	 * 
	 */
	public boolean verifyIntroductoryScreen() throws InterruptedException {
		return isDisplayed(whoWeAre, "Who We Are is displayed on the Introductory Screen")
				&& isDisplayed(takePatientVisits, "Take Patient Visits is displayed on the Introductory Screen")
				&& isDisplayed(providePrescriptions, "Provide Prescriptions is displayed on the Introductory Screen");
	}

	public List<String> getIntroductoryScreenText() {
		return getElementsText(whoWeAre, takePatientVisits, providePrescriptions);
	}

	// Verify Skip button is displayed
	/**
	 * public void skipButtonIsDisplayed() throws InterruptedException {
	 * isDisplayed(skipButton, "Skip button is displayed"); }
	 * 
	 * @throws CustomElementNotFoundException
	 */
	public boolean skipButtonIsDisplayed() throws InterruptedException {
		if (isDisplayed2(skipButton, "Skip button is displayed")) {
			return true;
		} else {
			return false;
		}
	}

	// Verify elements on Ayu Intro Screen
	public boolean verifyAyuScreen() throws InterruptedException {
		return isDisplayed(helloIamAyu, "Hello, I am Ayu text is displayed")
				&& isDisplayed(ayuIntroScreenSubtitle, "Ayu Intro Screen Subtitle is displayed")
				&& isDisplayed(checkBox, "Checkbox is displayed")
				&& isDisplayed(setupButton, "Setup Button is displayed");
	}

	/**
	 * need to check
	 * 
	 * @return
	 */
	public List<String> getPolicyTexts() {
		return getElementsText(termsAndCondition, privacyPolicy);
	}

	public String getTermsAndPolicyText() {
		return lblTermsAndPolicy.getText();
	}

	public List<String> getAyuScreenText() {
		;
		return getElementsText(helloIamAyu, ayuIntroScreenSubtitle, setupButton);
	}

	public List<String> getTermsAndConditionsScreenElementsText() {
		return getElementsText(termsAndConditionTitle, termsAndConditionDescription, termsAndConditionAcceptButton,
				termsAndConditionDeclineButton);
	}

	public List<String> getPrivacyPolicyScreenElementsText() {
		return getElementsText(privacyPolicyTitle, privacyPolicyContent, privacyPolicyAcceptButton,
				privacyPolicyDeclineButton);
	}

	// Verify elements on Terms and Conditions Screen
	public boolean verifyTermsAndConditionScreen() throws InterruptedException {
		return isDisplayed(termsAndConditionTitle, "Terms and Condition Title is displayed")
				&& isDisplayed(termsAndConditionDescription, "Terms and Condition Description is displayed")
				&& isDisplayed(termsAndConditionDeclineButton, "Decline Button is displayed")
				&& isDisplayed(termsAndConditionsBackArrow, "Back Arrow is displayed")
				&& isDisplayed(termsAndConditionAcceptButton, "Accept Button is displayed");
	}

	// Verify elements on Privacy Policy Screen

	public boolean verifyPrivacyPolicyScreen() throws InterruptedException {

		return isDisplayed(privacyPolicyTitle, "Privacy Policy Title is displayed")
				&& isDisplayed(privacyPolicyContent, "Privacy Policy Content is displayed")
				&& isDisplayed(privacyPolicyDeclineButton, "Privacy Policy Decline Button is displayed")
				&& isDisplayed(privacyPolicyAcceptButton, "Privacy Policy Accept Button is displayed");
	}

	public boolean isEnabledSetUpButton() {
		return setupButton.isEnabled();
	}

	public List<String> getSetUpScreenLabels() {
		return getElementsText(lblSetUpHeader, lblPleaseEnterDetails, lblLocation, lblUsername, lblPassword);
	}

	// Perform login
	public void login(String un, String pw) throws InterruptedException {
		// Click on the dropdown to open the menu

		click(dropdown);
		// Click on the location element to select a location
		try {
			// click(location);
			 WebDriverWait wait = new WebDriverWait((WebDriver) driver, Duration.ofSeconds(10));
			 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@text,'Telemedicine Clinic 1')]")));
		} // Enter the username into the username field
		catch (Exception e) {
			// TODO: handle exception
		}
		sendKeys(username, un);
		// Enter the password into the password field

		sendKeys(password, pw);
		// Click on the setup screen setup button to complete the login process
		click(setupScreeenSetupButton);

		// Handling display over other apps
		try {
			if (isDisplayed(navigateBack)) {
				click(intelehealth);
				click(toggleButton);
				click(navigateBack);
				click(navigateBack);

			}
		} catch (Exception e) {
			// Properly handle the exception, e.g., log the error

		}
		// this.waitForElementToBeStable(driver,locationName);
		// Check if locationName is displayed after the actions
		isDisplayed(locationName);
	}

	public void waitForElementToBeStable(ThreadLocal<AppiumDriver> driver, WebElement locator) {
		long start = System.currentTimeMillis();
		System.out.println("⏳ Waiting for element to become clickable...");

		try {
			WebDriverWait wait = new WebDriverWait((WebDriver) driver, Duration.ofSeconds(20));
			WebElement element = (WebElement) wait.until(ExpectedConditions.elementToBeClickable(locator));
			System.out.println("✅ Element is clickable now.");

			// Extra check to simulate 'idle': Wait for element to remain in place for a few
			// milliseconds
			String oldBounds = element.getAttribute("bounds");
			Thread.sleep(500); // wait briefly to detect movement/animation
			String newBounds = element.getAttribute("bounds");

			if (!oldBounds.equals(newBounds)) {
				System.out.println("⚠️ UI is still animating or loading — element bounds changed.");
			} else {
				System.out.println("✅ Element is stable — App is likely idle.");
			}

			element.click(); // Now safe to interact
		} catch (WebDriverException | InterruptedException e) {
			System.out.println("❌ Element not ready or app not idle: " + e.getMessage());
		}

		long end = System.currentTimeMillis();
		System.out.println("⏱ Total wait time: " + (end - start) + "ms");
	}

	public void refreshUIAndWait() throws InterruptedException {
		click(refreshButton);
		// Thread.sleep(26000);
	}

	/**
	 * Updated by Srinivas
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	// Verify if the location name is displayed
	public boolean locationIsDisplayed() throws InterruptedException {
		return isDisplayed(locationName);
	}

	// Check if the setup language screen is displayed
	public boolean setupLanguageScreenIsDisplayed() throws InterruptedException {
		return isDisplayed(english, "Setup Language Screen is displayed");
	}

	// Perform a series of actions to complete the setup process
	public void completeSetup() throws InterruptedException {
		// Click the "Next" button
		clickOnNextButton();

		// Click the "Skip" button
		clickOnSkipButton();

		// Click the checkbox
		clickOnCheckBox();

		// Click the "Setup" button
		clickOnSetupButton();

		// Perform login with the provided username and password

		String originalUserName = appData.getJSONObject("validUser").getString("username");
		String originalPassword = appData.getJSONObject("validUser").getString("password");

		System.out.println(originalPassword);
		System.out.println(originalUserName);
		// Encrypt the password
		String encryptedUserName = encrypt(originalUserName);
		System.out.println("Encrypted UserName: " + encryptedUserName);
		String decryptedUserName = decrypt(encryptedUserName);
		System.out.println("Decrypted UserName: " + decryptedUserName);
		String encryptedPassword = encrypt(originalPassword);
		System.out.println("Encrypted Password: " + encryptedPassword);
		String decryptedPassword = decrypt(encryptedPassword);
		System.out.println("Decrypted Password: " + decryptedPassword);
		login(decryptedUserName, decryptedPassword);

	}

}
