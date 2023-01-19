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
                .findProductByArticle("5072935") //5072935  5082658
                .checkProductPage("5072935")
                .getPriceProduct(ProductsName.IPHONE)
                .chooseAdditional("Гарантия")
                .chooseGuarantee()
                .isChangePrice()
                .getGuaranteePrice()
                .clickBuyButton()
                .searchProduct("Apple AirPods Pro 2")
                .clickPresearchButton();

    }
}
