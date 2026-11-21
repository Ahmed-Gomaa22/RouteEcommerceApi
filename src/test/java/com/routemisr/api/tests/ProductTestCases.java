package com.routemisr.api.tests;

import com.routemisr.api.models.Product;
import com.routemisr.api.services.ProductService;
import com.routemisr.api.testdata.RandomPicker;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class ProductTestCases {
    ProductService productService = new ProductService();
    private Product pickRandomProduct() {
        return RandomPicker.pickRandom(productService.extractProducts(productService.getAllProducts()));
    }
    @Test
    public void userCanRetrieveAllProducts() {
        Response response = productService.getAllProducts();
        productService.validateProductsAreReturned(response);
    }
    @Test
    public void userCanRetrieveProductById() {
        Product chosen = pickRandomProduct();
        Response response = productService.getProductById(chosen.getId());
        productService.validateProductMatchesId(response, chosen);
    }
    @Test
    public void userCanFilterProductsWithMinPrice() {
        Response response = productService.getProductsWithMinPrice(200);
        productService.validateFilteringWithMinPriceReturnsMinimumPriceProducts(response, 200);
    }

}
