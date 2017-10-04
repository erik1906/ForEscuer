package com.company.erde.forescuer.Model.VenueDetails;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Erik on 28/09/2017.
 */

public class VenueDetails implements Serializable{

    private String name;
    @SerializedName("location")
    Location location;

    @SerializedName("photos")
    Photos photos;

    @SerializedName("listed")
    Listed listed;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    public Listed getListed() {
        return listed;
    }

    public void setListed(Listed listed) {
        this.listed = listed;
    }
}
