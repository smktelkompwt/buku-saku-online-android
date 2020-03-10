package com.scc.bukusakuonline.connection;

import android.graphics.Movie;

import com.scc.bukusakuonline.model.DetailPointItems;
import com.scc.bukusakuonline.model.DetailPointResponse;
import com.scc.bukusakuonline.model.Laporan.LaporanResponse;
import com.scc.bukusakuonline.model.Login;
import com.scc.bukusakuonline.model.UploadPelanggaran;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @GET("api/point/all")
    Call<DetailPointResponse> getDetailPoint(@Header("Authorization") String Auth);

    @FormUrlEncoded
    @POST("api/users/admin/login")
    Call<Login> login(@Field("email") String email, @Field("password") String password, @Field("secretCode") String secret);

    @GET("api/lapor/all")
    Call<LaporanResponse>getLapor(@Header("Authorization") String Auth);

    @FormUrlEncoded
    @POST("api/lapor/upload")
    Call<UploadPelanggaran> uploadPelanggaran(@Header("Authorization") String auth,@Field("pelanggaran_kategori") String Pelanggaran, @Field("nis") Double Nis , @Field("image") String Image);
}
