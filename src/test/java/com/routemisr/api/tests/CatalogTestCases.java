package com.routemisr.api.tests;

import com.routemisr.api.models.CatalogItem;
import com.routemisr.api.services.CatalogService;
import com.routemisr.api.testdata.RandomPicker;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class CatalogTestCases {

    CatalogService catalogService =  new CatalogService();
    private CatalogItem PickRandomItem(Response listResponse) {
        return RandomPicker.pickRandom(catalogService.extractCatalogItems(listResponse));
    }
    @Test
    public void userCanRetrieveAllCategories() {
    Response response = catalogService.getAllCategories();
    catalogService.validateCatalogItemsAreReturned(response);
    }
    @Test
    public void userCanRetrieveAllBrands() {
        Response response = catalogService.getAllCategories();
        catalogService.validateCatalogItemsAreReturned(response);
    }
    @Test
    public void userCanRetrieveCategoryById() {
        CatalogItem chosen = PickRandomItem(catalogService.getAllCategories());
        Response response = catalogService.getCategoryById(chosen.getId());
        catalogService.validateCatalogItemMatchesId(response,  chosen.getId());
    }
    @Test
    public void userCanRetrieveBrandById() {
        CatalogItem chosen = PickRandomItem(catalogService.getAllBrands());
        Response response = catalogService.getBrandById(chosen.getId());
        catalogService.validateCatalogItemMatchesId(response,  chosen.getId());
    }
}
