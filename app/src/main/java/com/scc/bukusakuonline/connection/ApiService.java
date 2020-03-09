package com.scc.bukusakuonline.connection;

import com.scc.bukusakuonline.model.DetailPointResponse;
import com.scc.bukusakuonline.model.Riwayat.LaporanResponse;
import com.scc.bukusakuonline.model.Login;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {

    @GET("api/point/all")
    Call<DetailPointResponse> getDetailPoint(@Header("Authorization") String Auth);

    @FormUrlEncoded
    @POST("api/users/admin/login")
    Call<Login> login(@Field("email") String email, @Field("password") String password, @Field("secretCode") String secret);

    @GET("lapor/all")
    Call<LaporanResponse>getSiswa(@Header("Authorization") String Auth);
}
