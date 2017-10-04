package com.company.erde.forescuer.Model.VenueDetails;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Erik on 28/09/2017.
 */

public class Photos implements Serializable{

    @SerializedName("groups")
    Groups[] groups;

    public Groups[] getGroups() {
        return groups;
    }

    public void setGroups(Groups[] groups) {
        this.groups = groups;
    }
}
