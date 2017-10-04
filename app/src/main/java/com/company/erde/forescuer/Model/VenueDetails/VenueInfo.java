package com.company.erde.forescuer.Model.VenueDetails;

import com.company.erde.forescuer.Model.VenuesSearch.Response;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Erik on 28/09/2017.
 */

public class VenueInfo implements Serializable{

    @SerializedName("response")
    ResponseInfo reponse;

    public ResponseInfo getReponse() {
        return reponse;
    }

    public void setReponse(ResponseInfo reponse) {
        this.reponse = reponse;
    }
}
