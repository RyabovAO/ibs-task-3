package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductListPage extends BasePage {

    @FindBy(xpath = "//*[contains(@class, 'catalog-product__code-')]/..")
    private WebElement articleNumber;

    @FindBy(xpath = "//*[contains(@class, 'catalog-product__name ')]")
    private List<WebElement> product;

//    @FindBy(xpath = "//*[@class = 'pagination-widget__page-link']")
//    private List<WebElement> pageNumber;

    public ProductPage findProductByArticle(String article) {
        for (WebElement prod : product) {
            moveToElement(prod);
            waitUtilElementToBeVisible(articleNumber);
            String currentArticle = articleNumber.getText();
            if (currentArticle.equals(article)) {
                prod.click();
                //return true;
                return pageManager.getProductPage();
            }
        }
        //return false;
        Assertions.fail("Указанный артикул не найден");
        return pageManager.getProductPage();

    }

//    public ProductPage nextPage(String article) {
//        Iterator<WebElement> iter = product.iterator();
//        while (iter.hasNext()){
//            if(findProductByArticle(article)){
//                return pageManager.getProductPage();
//            }
//            iter.next().click();
//            }
//        return pageManager.getProductPage();
//    }

}
