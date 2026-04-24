package testCases;

import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.OrderHistory;
import testBase.BaseClass;

public class TC__007_OrderHistory extends BaseClass {
	
	@Test()
	public void orderHistory() {
		
		
		HomePage hp=new HomePage(driver);
		
		hp.clickMyAccount();
					
		hp.clickLogin();
		
		LoginPage lg = new LoginPage(driver);
		lg.setEmail(p.getProperty("email"));
		lg.setPassword(p.getProperty("password"));
		
		lg.clickLogin();
		
		OrderHistory oh = new OrderHistory(driver);
		
		oh.clickOrderHistory();
		
		
		
		
	}
	
	
	

}
