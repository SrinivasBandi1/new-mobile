package com.intelehealth.tests;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.intelehealth.base.BaseTest;
import com.intelehealth.pages.AppSetupPage;
import com.intelehealth.pages.FindPatientPage;
import com.intelehealth.utils.TestUtils;

public class FindPatientTest extends BaseTest {
	TestUtils utils = new TestUtils();
	AppSetupPage appSetupPage;
	FindPatientPage findPatientPage;
	JSONObject appData;

	@BeforeMethod
	public void beforeMethod(Method m) throws IOException, InterruptedException {
		utils.log().info("\n" + "****** starting test:" + m.getName() + "******" + "\n");
		resetApp();
		launchApp();
		appSetupPage = new AppSetupPage();
		findPatientPage = new FindPatientPage();
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
        appSetupPage.completeSetup();;
        Thread.sleep(6000);
     // Click on the "Find Patient" button to initiate the patient search
     		findPatientPage.clickOnFindPatient();
     		

	}

	@Test(priority = 1, description = " Verify the Search functionality by entering valid patient name in the search box", enabled = true)
	public void IDA4_2221_verifyValidPatientSearch() throws InterruptedException {
		
		Thread.sleep(1000);
		// Enter the valid patient name into the search box
		findPatientPage.enterValueInSearchBox(appData.getJSONObject("validPatient").getString("patientName"));
		// Enter valid patient name and verify the search result
		findPatientPage.verifySearchedPatient();
	}

	@Test(priority = 2, description = "Verify the Search functionality by entering invalid patient name in the search box", enabled = true)
	public void IDA4_2222_verifyInvalidPatientSearch() throws JSONException, InterruptedException {
		

		// Enter invalid patient name and verify the search result
		findPatientPage.enterValueInSearchBox(appData.getJSONObject("inValidPatient").getString("patientName"));
		// Verify that the system correctly handles an invalid patient search
		findPatientPage.verifyInvalidPatientSearch();

	}

	@Test(priority = 3, description = " Verify the Search functionality by entering invalid patient id in the search box", enabled = true)
	public void IDA4_2226_verifyInvalidPatientIdSearch() throws JSONException, InterruptedException {
		
		// Enter invalid patient id and verify the search result
		findPatientPage.enterValueInSearchBox(appData.getJSONObject("invalidPatientID").getString("Id"));
		// Verify that the system correctly handles an invalid patient search
		findPatientPage.verifyInvalidPatientSearch();
	}
	@AfterMethod
	public void afterMethod() {
		System.gc();
		// Close the app after each test method
		closeApp();
	}
}
