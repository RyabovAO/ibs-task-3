package pages;

import managers.ProductsName;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import products.IPhone;

public class ProductPage extends BasePage {

    @FindBy(xpath = "")
    private WebElement priceProduct;

    public void click() {

    }

//    public ProductPage getPriceProductInt(ProductsName product) {
//        waitUtilElementToBeClickable(priceProduct);
//        String text = priceProduct.getText();
//        text = text.replaceAll(" ", "");
//        text = text.replaceAll("₽", "");
//        int price = Integer.parseInt(text);
//        switch (product) {
//            case IPHONE:
//                iphone = price;
//                break;
//            case IPHONEWITHGUARANTEE:
//                iphoneWithGuarantee = price;
//                break;
//            case AIRPODS:
//                detroit = price;
//                break;
//        }
//        return pageManager.getProductPage();
//    }
}
