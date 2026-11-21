package com.routemisr.api.services;

import com.routemisr.api.models.Product;
import com.routemisr.config.http.endpoints.CatalogEndpoints;
import io.restassured.response.Response;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class ProductService extends BaseService {
    public Response getAllProducts() {
        return given().spec(requestSpec).when().get(CatalogEndpoints.PRODUCTS_ENDPOINT);
    }
    public Response getProductById(String id) {
        return given().spec(requestSpec).when().get(CatalogEndpoints.PRODUCTS_ENDPOINT + "/" + id);
    }
    public List<Product> extractProducts(Response response) {
        return response.jsonPath().getList("data", Product.class);
    }
    public Product extractProduct(Response response) {
        return response.jsonPath().getObject("data", Product.class);
    }

    public Response getProductsWithMinPrice(int minPrice) {
        return given().spec(requestSpec).queryParam("price[gte]", minPrice).
                when().get(CatalogEndpoints.PRODUCTS_ENDPOINT);
    }

    public void validateProductsAreReturned(Response response) {
        response.then().statusCode(200);
        List<Product> products = extractProducts(response);
        assertFalse(products.isEmpty(), "There is no products Available");
        for(Product product : products) {
            assertNotNull(product.getId(), "Product ID is null");
            assertNotNull(product.getTitle(), "Product Title is null");
            assertNotNull(product.getCategory(), "Product category is null");
            assertTrue(product.getPrice() > 0, "Product " + product.getId() + " has price " + product.getPrice());
        }
    }
    public void validateProductMatchesId(Response response, Product expected) {
        response.then().statusCode(200);
        Product product = extractProduct(response);
        assertEquals(product.getId(), expected.getId(),  "Product " + product.getId() + " does not match");
        assertEquals(product.getTitle(), expected.getTitle(),  "Product " + product.getTitle() + " does not match");}
    public void validateFilteringWithMinPriceReturnsMinimumPriceProducts(Response response, int minPrice) {
        response.then().statusCode(200);
        List<Product> products = extractProducts(response);
        assertFalse(products.isEmpty(), "There is no products Available");
        for(Product product : products) {
            assertTrue(product.getPrice() >= minPrice, "Product " + product.getId() + " price " + product.getPrice() + " does not match");
        }

    }
}
