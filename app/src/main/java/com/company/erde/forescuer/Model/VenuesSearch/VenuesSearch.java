package com.company.erde.forescuer.Model.VenuesSearch;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Erik on 22/09/2017.
 */

public class VenuesSearch implements Serializable{

    @SerializedName("response")
    Response reponse;

    public Response getReponse() {
        return reponse;
    }

    public void setReponse(Response reponse) {
        this.reponse = reponse;
    }
}
