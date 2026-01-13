package testCases;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC_004_SearchPage  extends BaseClass{
	
	@Test
	public void verify_product() {
		
		logger.info("starting search product search");
		try {	
			doLogin();
		
		SearchPage sg = new SearchPage(driver);
		 sg.enterProductName("MacBook");
		// sg.enterProductName(iphone);
		 sg.clickSearch();
		 
		// WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		 //wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productList));
		 
		 
		 sg.searchlist();
		 
		 boolean status = sg.isProductExist("MacBook");
		 Assert.assertEquals(status, true);
	
		}catch(Exception e) {
			Assert.fail();
		}
		
		}
		
		
	}