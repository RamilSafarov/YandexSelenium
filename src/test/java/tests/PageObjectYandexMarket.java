package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.YandexMarketPage;

import java.io.IOException;

public class PageObjectYandexMarket extends BaseTest{

    @Test
    @DisplayName("Лакомства для кошек")
    public void catTest() throws InterruptedException, IOException {
        YandexMarketPage yandexMarketPage = new YandexMarketPage(webDriver);
        yandexMarketPage.goToMarket();
        yandexMarketPage.getGoodiesForCat();
    }
}
