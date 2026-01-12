package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
	
	public boolean isProductExist(String productName) {
		boolean flag =false;
		for (WebElement product:searchProducts) {
			if(product.getText().equals(productName))
			{
				flag=true;
				break;
			}
		}
		return flag;
	}
}

