package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends BasePage
{   
			
	public SearchPage(WebDriver driver) {
		super(driver);
	}	
		@FindBy(xpath="//*[@id='content']/div[3]//img")
		List<WebElement> searchProducts;
				
		@FindBy(name="quantity")
		WebElement txtquantity;
		
		
		@FindBy(xpath="//button[@id='button-cart']")
		WebElement btnaddToCart;
		
		@FindBy(xpath="//div[contains(text(),'Success: You have added')]")
		WebElement cnfMsg;
		
		public boolean isProductExist(String productName)
		{
			boolean flag=false;
			for(WebElement product:searchProducts)
			{				
				if(product.getAttribute("title").equals(productName))
				{
				flag=true;
				break;
				}
			}
			
			return flag;
		
		}
		
		public void selectProduct(String productName)
		{
			for(WebElement product:searchProducts)
			{				
				if(product.getAttribute("title").equals(productName))
				{
					product.click();
				}
			}
		
		}
		
		
		public void setQuantity(String qty)
		{
			txtquantity.clear();
			txtquantity.sendKeys(qty);
		}
		
		public void addToCart() {
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    wait.until(ExpectedConditions.elementToBeClickable(btnaddToCart));
		    btnaddToCart.click();
		}

		
		public boolean checkConfMsg()
		{
			try
			{
			return cnfMsg.isDisplayed();
			}
			catch(Exception e)
			{
				return false;
			}
		}
		
		
		
		
}

