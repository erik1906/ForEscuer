package com.company.erde.forescuer.API;

import com.company.erde.forescuer.Model.VenueDetails.VenueInfo;
import com.company.erde.forescuer.Model.VenuesSearch.VenuesSearch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Erik on 22/09/2017.
 */

public interface FourSquare {

    @GET("search?")
    Call<VenuesSearch> getVenues (@Query("ll") String ll, @Query("oauth_token") String token, @Query("v") String v );

    @GET("{id}?")
    Call<VenueInfo> getVenueInfo (@Path("id") String id, @Query("oauth_token") String token, @Query("v") String v );


}
