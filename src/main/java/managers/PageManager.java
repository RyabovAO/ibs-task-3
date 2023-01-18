package managers;

import pages.BasketPage;
import pages.ProductListPage;
import pages.ProductPage;
import pages.StartPage;

public class PageManager {

    private static PageManager pageManager = null;
    private StartPage startPage;
    private BasketPage basketPage;
    private ProductListPage productListPage;
    private ProductPage productPage;

    private PageManager() {
    }

    public static PageManager getInstance() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    public StartPage getStartPage() {
        if (startPage == null) {
            startPage = new StartPage();
        }
        return startPage;
    }

    public BasketPage getBasketPage() {
        if (basketPage == null) {
            basketPage = new BasketPage();
        }
        return basketPage;
    }

    public ProductListPage getProductListPage() {
        if (productListPage == null) {
            productListPage = new ProductListPage();
        }
        return productListPage;
    }

    public ProductPage getProductPage() {
        if (productPage == null) {
            productPage = new ProductPage();
        }
        return productPage;
    }

}
