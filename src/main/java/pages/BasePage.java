package pages;

import constants.PropConst;
import managers.DriverManager;
import managers.PageManager;
import managers.TestPropManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected DriverManager driverManager = DriverManager.getInstance();
    protected PageManager pageManager = PageManager.getInstance();
    protected Actions action = new Actions(driverManager.getDriver());
    protected JavascriptExecutor js = (JavascriptExecutor) driverManager.getDriver();
    private final TestPropManager props = TestPropManager.getInstance();
    protected WebDriverWait wait = new WebDriverWait(driverManager.getDriver(),
            Duration.ofSeconds(Long.parseLong(props.getProperty(PropConst.EXPECTED_COND_TIMEOUT))),
            Duration.ofSeconds(Long.parseLong(props.getProperty(PropConst.EXPECTED_COND_SLEEP))));

    public BasePage() {
        PageFactory.initElements(driverManager.getDriver(), this);
    }

    protected WebElement waitUtilElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected WebElement waitUtilElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void inputField(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
//        boolean checkFlag = wait.until(ExpectedConditions.attributeContains(element, "value", value));
//        Assertions.assertTrue(checkFlag, "Поле было заполнено некорректно");
    }

    protected boolean isOpenPage(WebElement webElement) {
        return webElement.isDisplayed();
    }

    protected String getElementText(WebElement webElement) {
        return webElement.getText();
    }

    protected void moveToElement(WebElement webElement) {
        action.moveToElement(webElement).perform();
    }

    protected void scrollToElementJs(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
