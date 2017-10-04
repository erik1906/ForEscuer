package com.company.erde.forescuer.Model.VenuesSearch;

import android.graphics.drawable.Icon;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Erik on 22/09/2017.
 */

public class Categorie {

    @SerializedName("icon")
    CatIcon icon;

    public CatIcon getIcon() {
        return icon;
    }

    public void setIcon(CatIcon icon) {
        this.icon = icon;
    }
}
