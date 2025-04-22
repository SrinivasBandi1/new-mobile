package com.intelehealth.pages;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.intelehealth.base.BaseTest;

import io.appium.java_client.pagefactory.AndroidFindBy;

public class ChangeLanguageAndProtocolPage extends BaseTest {
	
	@AndroidFindBy(accessibility = "Language Protocol Change Language Spinner TextView")
	private WebElement languageSpinner;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"Simple Dialog ListItem Title TextView\"])[2]")
	private WebElement hindi;

	@AndroidFindBy(accessibility = "Patient Registration Dialog Positive (Yes) Button")
	private WebElement btnYes;

	@AndroidFindBy(accessibility = "Patient Registration Dialog Negative (No) Button")
	private WebElement btnNo;

	@AndroidFindBy(accessibility = "Custom Toolbar Back Arrow ImageView")
	private WebElement mnuHamburger;
	
	@AndroidFindBy(accessibility = "Language Protocol Reset Language Button RelativeLayout")
	private WebElement btnReset;

	// android.widget.CheckedTextView[@text='Change language & protocol']
//
	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='Change language & protocol']")
	private WebElement changeLangProtocol;
//
	@AndroidFindBy(accessibility = "Patient Registration Dialog Title TextView")
	private WebElement changeLangTitleInDialog;

	@AndroidFindBy(accessibility = "Patient Registration Dialog Subtitle TextView")
	private WebElement changeLangSubTitleInDialog;

	@AndroidFindBy(accessibility = "Language Protocol Refresh ImageButton")
	private WebElement icnSync;

	@AndroidFindBy(accessibility = "Snackbar Content TextView")
	private WebElement toastLanguageSaved;

	@AndroidFindBy(accessibility = "Language Protocol Update Protocol RelativeLayout")
	private WebElement btnUpdateProtocolInChangeLanguage;

	@AndroidFindBy(accessibility = "Update Protocol Dialog Server Url AutoCompleteTextView")
	private WebElement txtServerURL;

	@AndroidFindBy(accessibility = "Update Protocol Dialog License Key AutoCompleteTextView")
	private WebElement txtLicenseKey;

	@AndroidFindBy(accessibility = "Update Protocol Dialog Positive (Update) Button")
	private WebElement btnUpdateProtocol;

//	@AndroidFindBy(accessibility = )
//	private WebElement languageSpinner;

	/**
	 * Updated by @SrinivasBandi
	 * @throws InterruptedException
	 */
	public void verifyUserCanSelectLanguage() throws InterruptedException {
		click(mnuHamburger, "Clicked on Hamburger Menu");
		Thread.sleep(2000);
		click(changeLangProtocol, "Clicked on Change Language & Protocol");
		click(languageSpinner);
		click(hindi);
	}

	/**
	 * @author @SrinivasBandi
	 * @throws InterruptedException
	 */
	public void navigateToChangeLanguageScreen() {
		click(mnuHamburger, "Clicked on Hamburger Menu");
		click(changeLangProtocol, "Clicked on Change Language & Protocol");
	}

	public void selectHindiLanguage() {
		click(languageSpinner);
		click(hindi);
	}

	public List<String> getchangeLanguageConfirmationDialogueLabels() {
		return getElementsText(changeLangSubTitleInDialog, changeLangTitleInDialog, btnYes, btnNo);
	}

	public void clickOnYes() {
		click(btnYes);
	}

	public void clickOnSync() {
		click(icnSync);
	}

	public void clickOnResetButton() {
		click(btnReset);
	}

	public String getLanguageChangedSuccessFullyText() {
		return toastLanguageSaved.getText();
	}

	public void clickOnUpdateProtocolInChangeLanguage() {
		click(btnUpdateProtocolInChangeLanguage);
	}

	public void enterServerURL(String serverURL) throws InterruptedException {
		sendKeys(txtLicenseKey, serverURL);
	}

	public void enterLicenseKey(String licenseKey) throws InterruptedException {
		sendKeys(txtLicenseKey, licenseKey);
	}

	public void clickOnUpdateProtocolButton() {
		click(btnUpdateProtocol);
	}

}