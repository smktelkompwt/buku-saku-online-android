package com.scc.bukusakuonline.Connection;

import android.graphics.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("test")
    Call<Movie> getMovie(@Query("test")String test);
}
