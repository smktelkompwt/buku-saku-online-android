package com.scc.bukusakuonline.connection;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroConfig {
    public static final String BASE_URL = "http://34.201.130.83/api/users/admin/";

    public static Retrofit retrofit;

//    public static String getApiKey(){
//        return API_KEY;
//    }
    public ApiService services = RetroConfig.getRetrofit().create(ApiService.class);
    public static Retrofit getRetrofit(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }


}
