package pages;

import managers.ProductsName;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class ProductPage extends BasePage {

    @FindBy(xpath = "//*[contains(@class, '__code')]")
    private WebElement productCode;

    @FindBy(xpath = "//*[@class= 'product-card-top__buy']//*[@class = 'product-buy__price']")
    private WebElement priceProduct;

    @FindBy(xpath = "//*[@class = 'ui-radio__item product-warranty__item product-warranty__item_hit']//*[@class= 'product-warranty__price']")
    private WebElement guarantee;

    @FindBy(xpath = "//*[@class = 'additional-sales-tabs__titles-wrap']//*")
    private List<WebElement> additionalBlock;

    @FindBy(xpath = "//*[@class = 'ui-radio__item product-warranty__item product-warranty__item_hit']//*[@class = 'ui-radio__content']")
    private WebElement guaranteeRadioButton;

    @FindBy(xpath = "//*[@class= 'product-card-top__buy']//*[@class = 'product-buy__sub']")
    private WebElement changePrice;

    @FindBy(xpath = "//*[@class = 'product-buy product-buy_one-line']//*[@class = 'button-ui buy-btn button-ui_brand button-ui_passive']")
    private WebElement buyButtonNotActive;

    @FindBy(xpath = "//*[contains(@class,'product-card-top__buy')]//button[contains(@class,'buy-btn')]")
    private WebElement buyButton;

    @FindBy(xpath = "//*[contains(@class, '-price cart-link-counter__price')]")
    private WebElement priceBasket;

    @FindBy(xpath = "//*[@data-redirect = 'https://www.dns-shop.ru/cart/']")
    private WebElement dataRedir;


    public ProductPage checkProductPage(String article) {
        String code = productCode.getText().replaceAll("\\D", "");
        Assertions.assertEquals(article, code, "Странице продукта не открыта");
        return this;
    }

    public ProductPage getPriceProduct(ProductsName productName) {
        waitUtilElementToBeClickable(priceProduct);
        int price = getElementTextByInt(priceProduct);
        switch (productName) {
            case IPHONE:
                iPhone.setPrice(price);
                break;
            case AIRPODS:
                appleAirPods.setPrice(price);
                break;
            default:
                Assertions.fail("Цена не найдена");
        }
        return this;
    }

    public ProductPage getGuaranteePrice() {
        iPhone.setGuaranteePrice(getElementTextByInt(guarantee));
        return this;
    }

    public ProductPage chooseAdditional(String additional) {
        for (WebElement additionalItem : additionalBlock) {
            if (additionalItem.getText().contains(additional)) {
                additionalItem.click();
                return this;
            }
        }
        Assertions.fail("Меню '" + additional + "' не было найдено");
        return this;
    }

    public ProductPage chooseGuarantee() {
        waitUtilElementToBeClickable(guaranteeRadioButton);
        guaranteeRadioButton.click();
        return this;
    }

    public ProductPage isChangePrice() {
        waitUtilElementToBeVisible(changePrice);
        Assertions.assertTrue(changePrice.getText().contains("цена изменена"), "На странице не появилась надпись 'цена изменена'");
        return this;
    }

    public ProductPage clickBuyButton() {
        waitUtilElementToBeClickable(buyButton);
        buyButton.click();
        return this;
    }

    public ProductPage searchProduct(String product) {
        inputField(searchField, product);
        return this;
    }

    public ProductPage clickPresearchButton() {
        presearchButton.click();
        return this;
    }

    public ProductPage checkBasketPrice() {
        waitUtilElementToBeVisible(dataRedir);
        int basketPrice = getElementTextByInt(priceBasket);
        Assertions.assertEquals(basketPrice, iPhone.getPrice() + iPhone.getGuaranteePrice() + appleAirPods.getPrice(),
                "Сумма товаров в корзине не совпадает");
        return this;
    }

    public BasketPage clickBasketButton() {
        priceBasket.click();
        return pageManager.getBasketPage();
    }
}
