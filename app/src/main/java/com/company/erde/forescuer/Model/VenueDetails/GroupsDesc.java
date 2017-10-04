package com.company.erde.forescuer.Model.VenueDetails;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Erik on 28/09/2017.
 */

public class GroupsDesc implements Serializable{

    @SerializedName("items")
    ItemsDesc[] itemsDesc;

    public ItemsDesc[] getItemsDesc() {
        return itemsDesc;
    }

    public void setItemsDesc(ItemsDesc[] itemsDesc) {
        this.itemsDesc = itemsDesc;
    }
}
