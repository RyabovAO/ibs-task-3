package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StartPage extends BasePage{

    @FindBy(xpath = "//*[@class='homepage']")
    private WebElement pageLocator;

    public StartPage searchProduct(String prod) {
        inputField(searchField, prod);
        return this;
    }

    public ProductListPage clickPresearchButton() {
        presearchButton.click();
        return pageManager.getProductListPage();
    }

    public StartPage checkStartPage() {
        Assertions.assertTrue(isOpenPage(pageLocator), "Стартовая страница DNS не открыта");
        return this;
    }
}
