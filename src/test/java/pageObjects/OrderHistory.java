package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderHistory extends BasePage {

    public OrderHistory(WebDriver driver) {
        super(driver);
    }

    By myAccount = By.xpath("//a[@title='My Account']");
    By orderHistory = By.xpath("//a[text()='Order History']");

    public void clickOrderHistory() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(myAccount)).click();
        wait.until(ExpectedConditions.elementToBeClickable(orderHistory)).click();
    }

    By cart = By.xpath("//div[@id ='cart']");

    public void clickcart(){
        driver.findElement(cart).click();

    }

    @FindBy(xpath="//td[@class='text-center']/button")
    WebElement clear;

    public void clearCart(){
        clear.click();
    }

}
