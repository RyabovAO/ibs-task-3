package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class ProductListPage extends BasePage {

    @FindBy(xpath = "//*[contains(@class,'catalog-products view-simple')]")
    private WebElement catalog;

    @FindBy(xpath = "//*[contains(@class, 'catalog-product__code-')]/..")
    private WebElement articleNumber;

    @FindBy(xpath = "//*[contains(@class, 'catalog-product__name ')]")
    private List<WebElement> product;

    @FindBy(xpath = "//*[@class = 'pagination-widget__page-link']")
    private List<WebElement> pageNumber;

    public ProductListPage checkProductListPage() {
        Assertions.assertTrue(isOpenPage(catalog), "Странице каталога товаров не открыта");
        return this;
    }

    public ProductPage findProductOnAllPage(String article) {
        boolean res = true;
        while (res) {
            for (WebElement pageNum : pageNumber) {
                if (findProductByArticleBoolean(article)) {
                    res = false;
                    return pageManager.getProductPage();
                } else {
                }
                pageNum.click();
            }
        }
        Assertions.fail("Указанный артикул не найден");
        return pageManager.getProductPage();
    }

    public boolean findProductByArticleBoolean(String article) {
        waitUtilElementToBeVisible(catalog);
        for (WebElement prod : product) {
            moveToElement(prod);
            waitUtilElementToBeVisible(articleNumber);
            String currentArticle = articleNumber.getText();
            if (currentArticle.equals(article)) {
                prod.click();
                return true;
            }
        }
        return false;
    }

}
