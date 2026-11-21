package com.routemisr.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
    private final String id;
    private final String title;
    private final int price;
    private final CatalogItem category;

    public Product(@JsonProperty("_id") String id,
                   @JsonProperty("title") String title,
                   @JsonProperty("price") int price,
                   @JsonProperty("category") CatalogItem category){
        this.id = id;
        this.title = title;
        this.price = price;
        this.category = category;

    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public CatalogItem getCategory() {
        return category;
    }

}
