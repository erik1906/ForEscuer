package com.company.erde.forescuer.Model.VenueDetails;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Erik on 28/09/2017.
 */

public class ResponseInfo implements Serializable{

    @SerializedName("venue")
    VenueDetails venue;

    public VenueDetails getVenue() {
        return venue;
    }

    public void setVenue(VenueDetails venue) {
        this.venue = venue;
    }
}
