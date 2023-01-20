package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class BasketPage extends BasePage {

    @FindBy(xpath = "//*[contains(@class, 'additional-warranties-row__hit-warranty ')]" +
            "//*[contains(@class, 'base-ui-radio-button__checked additional-warranties-row__radio')]")
    private WebElement guaranteeRadioButton;

    @FindBy(xpath = "//*[@class = 'price__current']")
    private List<WebElement> listPriceProduct;

    @FindBy(xpath = "//*[contains(@class, 'product-name')]//*[contains(@class, 'base-ui-link base-ui-link')]")
    private List<WebElement> listProductName;

    @FindBy(xpath = "//*[contains(@class, 'menu-control-button remove')]")
    private List<WebElement> listDeleteButton;

    @FindBy(xpath = "//*[contains(@class, 'cart-tab-menu__')]//*[contains(@class, 'restore-last-removed')]")
    private WebElement restoreRemovedButton;

    @FindBy(xpath = "//*[@class = 'summary-header__total-items']")
    private WebElement totalProduct;

    @FindBy(xpath = "//*[contains(@class, 'count-buttons__button_plus')]")
    private WebElement countButtonPlus;

    @FindBy(xpath = "//*[contains(@class, 'cart-products-count')]")
    private WebElement cartProductCount;

    public BasketPage checkIphoneGuarantee() {
        Assertions.assertTrue(guaranteeRadioButton.isDisplayed(), "Гарантия телефона не выбрана");
        return this;
    }

    public BasketPage checkProductPriceAndSum() {
        int sum = iPhone.getPrice() + appleAirPods.getPrice() + iPhone.getGuaranteePrice();
        int count = 0;
        for (WebElement price : listPriceProduct) {
            if (getElementTextByInt(price) == iPhone.getPrice()) {
                count++;
            }
            if (getElementTextByInt(price) == appleAirPods.getPrice()) {
                count++;
            }
            if (getElementTextByInt(price) == sum) {
                count++;
            }
        }
        Assertions.assertTrue(count == 3, "Цена товара не совпала");
        return this;
    }

    public BasketPage deleteProduct(String productName) {
        for (WebElement elem : listProductName) {
            if (elem.getText().contains(productName)) {
                listDeleteButton.get(listProductName.indexOf(elem)).click();
                return this;
            }
        }
        Assertions.fail("Товар " + productName + " не был удален из корзины");
        return this;
    }

    public BasketPage isProductDelete(String productName) {
        boolean res = true;
        waitUtilElementToBeVisible(restoreRemovedButton);
        for (WebElement elem : listProductName) {
            if (!elem.getText().contains(productName)) {
                res = false;
            }
        }
        Assertions.assertFalse(res, "Продукт " + productName + " удален из корзины");
        return this;
    }

    public BasketPage isCorrectSum() {
        int currentSum = getCurrentSumInCart();
        int sum2 = iPhone.getGuaranteePrice() + iPhone.getPrice() + appleAirPods.getPrice();
        Assertions.assertEquals(sum2 - currentSum, appleAirPods.getPrice(), "Сумма не уменьшилась на сумму Airpods");
        return this;
    }

    private int getCurrentSumInCart() {
        waitUtilElementToBeVisible(totalProduct);
        return getElementTextByInt(listPriceProduct.get(listPriceProduct.size() - 1));
    }

    public BasketPage clickCountButtonPlus(int count) {
        while (true) {
            waitUtilElementToBeClickable(countButtonPlus);
            if (getElementTextByInt(cartProductCount) != 3) {
                countButtonPlus.click();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else break;
        }
        Assertions.assertEquals(iPhone.getPrice() * count + iPhone.getGuaranteePrice() * 3, getCurrentSumInCart(),
                "Сумма товаров не соответствует количеству");
        return this;
    }

    public BasketPage clickRestoreRemovedButton(int count) {
        restoreRemovedButton.click();
        waitUtilElementToBeVisible(totalProduct);
        Assertions.assertEquals(iPhone.getPrice() * count + iPhone.getGuaranteePrice() * 3 + appleAirPods.getPrice(),
                getCurrentSumInCart(), "Сумма товаров не соответствует количеству после возвращения продукта ");
        return this;
    }

}
