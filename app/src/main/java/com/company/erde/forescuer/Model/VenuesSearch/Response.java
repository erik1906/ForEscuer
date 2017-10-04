package com.company.erde.forescuer.Model.VenuesSearch;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Erik on 22/09/2017.
 */

public class Response implements Serializable {

    @SerializedName("venues")
    Venue[] venues;

    public Venue[] getVenues() {
        return venues;
    }

    public void setVenues(Venue[] venues) {
        this.venues = venues;
    }
}
