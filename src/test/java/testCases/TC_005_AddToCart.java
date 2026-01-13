package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC_005_AddToCart extends BaseClass {
	
	@Test(groups= {"Sanity","Master"})
	public void AddingItemsToCart() throws InterruptedException {
		
			
			//doLogin();
			
		SearchPage sp = new SearchPage(driver);
		
		sp.enterProductName("iPhone");
		sp.clickSearch();
		
		if(sp.isProductExist("iPhone")) {
			
			sp.selectProduct("iPhone");
			sp.setQuantity("2");
			sp.addToCart();
		}
		
		Assert.assertEquals(sp.checkConfMsg(), true);
		
		}
		
	}


