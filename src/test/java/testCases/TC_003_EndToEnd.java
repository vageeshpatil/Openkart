package testCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_003_EndToEnd extends BaseClass{
	
	@Test
	public void Endtoend() throws InterruptedException {
		
		System.out.println("Account Regestration");
		
		SoftAssert as= new SoftAssert();
		
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickRegister();
		
		AccountRegistrationPage ap= new AccountRegistrationPage(driver);
		
		ap.setFirstName(randomeString().toUpperCase());
		ap.setLastName(randomeString().toUpperCase());
		
		String email = randomeString() + "@gmail.com";
		ap.setEmail(email);
		
		ap.setTelephone(randomeNumber());
		ap.setPassword("test123");
		ap.setConfirmPassword("test123");
		ap.setPrivacyPolicy();
		ap.clickContinue();
		Thread.sleep(3000);
		
		String confimesg = ap.getConfirmationMsg();
		
		as.assertEquals(confimesg, "Your Account Has Been Created!");
		
		
		
		
		
		
	}

}
