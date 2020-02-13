package com.scc.bukusakuonline.connection;

import android.graphics.Movie;

import com.scc.bukusakuonline.model.Login;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @GET("test")
    Call<Movie> getMovie(@Query("test")String test);

    @FormUrlEncoded
    @POST("login")
    Call<Login> login(@Field("email") String email, @Field("password") String password, @Field("secretCode") String secret);
}
