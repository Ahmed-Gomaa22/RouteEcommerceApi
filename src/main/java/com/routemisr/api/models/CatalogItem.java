package com.routemisr.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CatalogItem {
    private final String id;
    private final String name;

    public CatalogItem( @JsonProperty("_id") String id,
                        @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }
    public String getId() {return id;}
    public String getName() {return name;}
}
