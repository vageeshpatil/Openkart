package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyAccountPage extends BasePage {
	
	public MyAccountPage(WebDriver driver) {
		super(driver);
		
	}
	
	 By msgHeading = By.xpath("//h2[text()='My Account']");
	
	By Logout = By.linkText("Logout");
	
	public void clickLogout() {
		driver.findElement(Logout).click();
	
	}
	
	
	public boolean isMyAccountPageExists() {
		try {
		return (driver.findElement(msgHeading).isDisplayed());
		
	}
	catch(Exception e) {
		return false;
		
	}
	}
	
	

}
