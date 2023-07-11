package magento.tests;

import net.bytebuddy.utility.RandomString;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;


public class TestBase {

    private final WebDriver driver = new ChromeDriver();

    public void openApp() {
        driver.get("https://magento.softwaretestingboard.com/");
    }

    public void search(String searchString) {
        driver.findElement(By.name("q")).sendKeys(searchString);
        driver.findElement(By.xpath("//*[@id=\"search_mini_form\"]/div[2]/button")).click();
    }

    public void openItemOnPage(String itemName) {
        List<WebElement> items = driver.findElements(
                By.xpath("//*[@id=\"maincontent\"]/div[3]/div[1]/div[2]/div[2]/ol/li"));
        for (WebElement item : items) {
            if (item.getText().startsWith(itemName)) {
                item.click();
                break;
            }
        }
    }

    public void selectItemWithParams(String size, String color) {
        selectSize(size);
        selectColor(color);
    }

    public void selectSize(String size) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"product-addtocart-button\"]")));
        List<WebElement> items = driver.findElements(
                By.xpath("//*[@id=\"product-options-wrapper\"]/div/div/div[1]/div/div"));
        for (WebElement item : items) {
            if (item.getAttribute("option-label").equals(size)) {
                item.click();
                break;
            }
        }
    }

    public void selectColor(String color) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"product-addtocart-button\"]")));
        List<WebElement> items = driver.findElements(
                By.xpath("//*[@id=\"product-options-wrapper\"]/div/div/div[2]/div/div"));
        for (WebElement item : items) {
            if (item.getAttribute("option-label").equals(color)) {
                item.click();
                break;
            }
        }
    }

    public void addToCart() {
        driver.findElement(By.xpath("//*[@id=\"product-addtocart-button\"]")).click();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.textToBe(
                        By.xpath("/html/body/div[1]/header/div[2]/div[1]/a/span[2]/span[1]"),"1"));
    }

    public void openCheckout() {
        driver.findElement(By.xpath("/html/body/div[1]/header/div[2]/div[1]/a")).click();
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"top-cart-btn-checkout\"]")));
        driver.findElement(By.xpath("//*[@id=\"top-cart-btn-checkout\"]")).click();
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"shipping-method-buttons-container\"]/div/button")));
    }

    public void fillRandomShippingAddress () {
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.id("customer-email"))));
        driver.findElement(By.id("customer-email")).sendKeys(new RandomString(8).nextString() + "@gmail.com");

        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.name("firstname"))));
        driver.findElement(By.name("firstname")).sendKeys(new RandomString(8).nextString());

        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.name("lastname"))));
        driver.findElement(By.name("lastname")).sendKeys(new RandomString(8).nextString());

        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.name("street[0]"))));
        driver.findElement(By.name("street[0]")).sendKeys(new RandomString(8).nextString());

        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.name("city"))));
        driver.findElement(By.name("city")).sendKeys(new RandomString(8).nextString());

        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.name("postcode"))));
        driver.findElement(By.name("postcode")).sendKeys("12345");

        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.name("telephone"))));
        driver.findElement(By.name("telephone")).sendKeys("123456789");

        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.name("region_id"))));
        driver.findElement(By.name("region_id")).click();
        List<WebElement> selects = driver.findElements
                (By.xpath("/html/body/div[1]/main/div[2]/div/div[2]/div[4]/ol/li[1]/div[2]/form[2]/div/div[5]/div/select/option"));
        Random rand = new Random();
        int number = rand.nextInt(selects.size());
        selects.get(number).click();

        //driver.findElement(By.xpath("//*[@id=\"shipping-method-buttons-container\"]/div/button"));

    }

    public void closeApp() {
        driver.quit();
    }
}
