package testcase;

import org.junit.jupiter.api.Test;

public class DnsTest extends BaseTests{

    @Test
    public void test() {
        pageManager.getStartPage()
                .checkStartPage()
                .searchProduct("Iphone")
                .clickPresearchButton()
                .findProductByArticle("5082658"); //5072935  5082658
    }
}
