package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends BasePage
{   
			
	public SearchPage(WebDriver driver) {
		super (driver);
	}
	
	@FindBy(xpath="//input[@placeholder='Search']")  //For Search Product Test
	WebElement txtSearchbox;

	@FindBy(xpath="//div[@id='search']//button[@type='button']") //For Search Product Test
	WebElement btnSearch;
	
	@FindBy(xpath="//div[@class='product-thumb']//h4/a")
	List<WebElement> searchProducts;
	
	@FindBy(name="quantity")
	WebElement txtquantity;
	
	@FindBy(xpath="//button[@id='button-cart']")
	WebElement btnaddToCart;
	
	@FindBy(xpath="//div[contains(text(),'Success: You have added')]")
	WebElement cnfMsg;
	
	
	public void enterProductName(String pName) throws InterruptedException   //For Search Product Test
	{
		txtSearchbox.sendKeys(pName);
	}

	public void clickSearch()  //For Search Product Test
	{
		btnSearch.click();
	}
	
	
	
	public void searchlist() {
		for (WebElement productlist:searchProducts) {
			System.out.println(productlist.getText());
		}
	}
	
	public boolean isProductExist(String productName) 
	{
		boolean flag = false;
		for (WebElement product:searchProducts) {
			if(product.getText().equals(productName))
			{
				flag= true;
				break;
			}
		}
		return flag;
	}
	
	
	public void selectProduct(String productName)
	{
		for(WebElement product:searchProducts)
		{				
			if(product.getText().equals(productName))
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
	
	public void addToCart()
	{
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