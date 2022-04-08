package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class YandexMainPage extends BasePage {
    public YandexMainPage(WebDriver webDriver) {
        super(webDriver);
    }

    //локаторы
    private static final String marketButton = "//div[@class = 'services-new__icon services-new__icon_market']";

    public void goToMarket() {
        webDriver.findElement(By.xpath(marketButton)).click();
        for (String windowHandle : webDriver.getWindowHandles()) {
            webDriver.switchTo().window(windowHandle);
        }
    }
}
