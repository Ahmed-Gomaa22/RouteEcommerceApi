package com.routemisr.api.services;

import com.routemisr.api.models.CatalogItem;
import com.routemisr.config.http.endpoints.CatalogEndpoints;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class CatalogService extends BaseService {
    public Response getAllCategories() {
        return given().spec(requestSpec).when().get(CatalogEndpoints.CATEGORIES_ENDPOINT);
    }
    public Response getCategoryById(String id) {
        return given().spec(requestSpec).when().get(CatalogEndpoints.CATEGORIES_ENDPOINT + "/" +id);
    }
    public List<CatalogItem> extractCatalogItems(Response response) {
        return response.jsonPath().getList("data", CatalogItem.class);
    }
    public CatalogItem extractCatalogItem(Response response) {
        return response.jsonPath().getObject("data", CatalogItem.class);
    }
    public Response getAllBrands(){
        return given().spec(requestSpec).when().get(CatalogEndpoints.BRANDS_ENDPOINT);
    }
    public Response getBrandById(String id) {
        return given().spec(requestSpec).when().get(CatalogEndpoints.BRANDS_ENDPOINT + "/" +id);
    }


    public void validateCatalogItemsAreReturned(Response response) {
        response.then().statusCode(200);
        List<CatalogItem> items = extractCatalogItems(response);
        assertFalse(items.isEmpty(), "There is no items available");
        for (CatalogItem item : items) {
        assertNotNull(item.getName(),"Item " + item.getId() + " has no name");
        assertNotNull(item.getId(),"Item has no id");}
    }
    public void validateCatalogItemMatchesId(Response response, String expectedId) {
        response.then().statusCode(200);
        CatalogItem item = extractCatalogItem(response);
        assertEquals(item.getId(),expectedId , "Item " + item.getId() + " does not match expected id");
        assertNotNull(item.getName(),"Item " + item.getId() + " has no name");
    }
}
