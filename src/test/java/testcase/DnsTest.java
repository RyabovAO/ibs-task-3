package testcase;

import managers.ProductsName;
import org.junit.jupiter.api.Test;

public class DnsTest extends BaseTests{

    @Test
    public void test() {
        pageManager.getStartPage()
                .checkStartPage()
                .searchProduct("Iphone")
                .clickPresearchButton()
                .checkProductListPage()
                .findProductOnAllPage("5082658")//5072935 - на первой стр,  5082658 - на 2й стр
                .checkProductPage("5082658")
                .getPriceProduct(ProductsName.IPHONE)
                .chooseAdditional("Гарантия")
                .chooseGuarantee()
                .isChangePrice()
                .getGuaranteePrice()
                .clickBuyButton()
                .searchProduct("Apple AirPods Pro 2")
                .clickPresearchButton()
                .getPriceProduct(ProductsName.AIRPODS)
                .clickBuyButton()
                .checkBasketPrice()
                .clickBasketButton()
                .checkIphoneGuarantee()
                .checkProductPriceAndSum()
                .deleteProduct("Apple AirPods Pro 2")
                .isProductDelete("Apple AirPods Pro 2")
                .isCorrectSum()
                .clickCountButtonPlus(3)
                .clickRestoreRemovedButton(3);
    }
}
