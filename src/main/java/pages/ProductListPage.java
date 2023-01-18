package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductListPage extends BasePage {

//    @FindBy(xpath = "//*[contains(@data-id, 'product')]")
//    private List<WebElement> productView;

    @FindBy(xpath = "//*[contains(@class, 'catalog-product__code-')]/..")
    private WebElement articleNumber;

//    @FindBy(xpath = "//*[contains(@class, 'pagination-widget__show')]")
//    private WebElement showMoreButton;

    @FindBy(xpath = "//*[contains(@class, 'catalog-product__name ')]")
    private List<WebElement> product;

    @FindBy(xpath = "//*[@class = 'pagination-widget__page-link']")
    private List<WebElement> pageNumber;

    public BasePage findProductByArticle(String article) {
        for (WebElement prod : product) {
            moveToElement(prod);
            waitUtilElementToBeVisible(articleNumber);
            String currentArticle = articleNumber.getText();
            if (currentArticle.equals(article)) {
                prod.click();
                return pageManager.getProductPage();
            }
        }

        Assertions.fail("Указанный артикул не найден");
        return pageManager.getProductListPage();

    }

//    public ProductPage nextPage(String article) {
//        Iterator<WebElement> iter = product.iterator();
//        while (iter.hasNext()){
//            if(findProductByArticle(article)){
//                return pageManager.getProductPage();
//            } else {
//                iter.next().click();
//
//            }
//        } return pageManager.getProductPage();
//    }

}
