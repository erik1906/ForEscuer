package com.company.erde.forescuer.API;

/**
 * Created by Erik on 22/09/2017.
 */

public interface FourSquare {
    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1")
    Call<PhotosRes> getSearch (@Query("api_key") String key, @Query("tags") String search );

    @GET("?method=flickr.photos.getInfo&format=json&nojsoncallback=1")
    Call<PhotoResponse> getPhoto (@Query("api_key") String key, @Query("photo_id") String photoId );

}
