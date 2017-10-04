package com.company.erde.forescuer.Model.VenueDetails;

import java.io.Serializable;

/**
 * Created by Erik on 28/09/2017.
 */

public class Location implements Serializable{

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
