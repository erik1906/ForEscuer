package com.company.erde.forescuer.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Erik on 18/09/2017.
 */

public class APIFourSquare {
    public static final String CLIENT_ID = "1ZYUHD2OYE1N5DOLZP1VKXBPVGXRH2TMJBXAUSZ43CQHZ0Y2";
    public static final String CLIENT_SECRET = "TOOPUX43YXE4W5Q2GF2QL0IUDPMIWJJMOWB2RPLWEUS23F3L";

    public static final int REQUEST_CODE_FSQ_CONNECT = 200; //Connection code
    public static final int REQUEST_CODE_FSQ_TOKEN_EXCHANGE = 201;

    public static final String BASE_URL = "https://api.foursquare.com/v2/venues/";

    private static Retrofit retrofit = null;

    public static Retrofit getApi() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
