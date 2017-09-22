package com.company.erde.forescuer.Model;

/**
 * Created by Erik on 20/09/2017.
 */

public class Places {

    private String imageURL;
    private String name;

    public Places(String imageURL, String name) {
        this.imageURL = imageURL;
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
