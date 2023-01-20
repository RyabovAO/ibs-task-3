package pages;

import constants.PropConst;
import managers.DriverManager;
import managers.PageManager;
import managers.TestPropManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import products.AppleAirPods;
import products.IPhone;
import java.time.Duration;

public class BasePage {

    protected DriverManager driverManager = DriverManager.getInstance();
    protected PageManager pageManager = PageManager.getInstance();
    protected Actions action = new Actions(driverManager.getDriver());
    private final TestPropManager props = TestPropManager.getInstance();
    protected WebDriverWait wait = new WebDriverWait(driverManager.getDriver(),
            Duration.ofSeconds(Long.parseLong(props.getProperty(PropConst.EXPECTED_COND_TIMEOUT))),
            Duration.ofSeconds(Long.parseLong(props.getProperty(PropConst.EXPECTED_COND_SLEEP))));
    protected static IPhone iPhone = new IPhone();
    protected static AppleAirPods appleAirPods = new AppleAirPods();

    @FindBy(xpath = "//*[contains(@class, 'presearch__input')]")
    protected WebElement searchField;

    @FindBy(xpath = "//*[contains(@class,'presearch__icon-search')]")
    protected WebElement presearchButton;

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
    }

    protected boolean isOpenPage(WebElement webElement) {
        return webElement.isDisplayed();
    }

    protected Integer getElementTextByInt(WebElement webElement) {
        return Integer.parseInt(webElement.getText().replaceAll("\\D", ""));
    }

    protected void moveToElement(WebElement webElement) {
        action.moveToElement(webElement).perform();
    }

}
