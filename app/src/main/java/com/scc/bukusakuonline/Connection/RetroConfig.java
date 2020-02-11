package com.scc.bukusakuonline.Connection;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroConfig {
    public static final String BASE_URL = "http://google.com/";

    public static Retrofit retrofit;

//    public static String getApiKey(){
//        return API_KEY;
//    }

    public static Retrofit getRetrofit(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }

    public ApiService service = RetroConfig.getRetrofit().create(ApiService.class);
}
