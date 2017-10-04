package com.company.erde.forescuer.Model.VenuesSearch;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Erik on 22/09/2017.
 */

public class Venue implements Serializable {

    String id;
    String name;


    @SerializedName("categories")
    Categorie[] categories;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    

    public Categorie[] getCategories() {
        return categories;
    }

    public void setCategories(Categorie[] categories) {
        this.categories = categories;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
