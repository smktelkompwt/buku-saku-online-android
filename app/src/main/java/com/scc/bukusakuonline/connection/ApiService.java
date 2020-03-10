package com.scc.bukusakuonline.connection;

import com.scc.bukusakuonline.model.DetailPointResponse;
import com.scc.bukusakuonline.model.Laporan.LaporanByIdResponse;
import com.scc.bukusakuonline.model.Laporan.LaporanResponse;
import com.scc.bukusakuonline.model.Login;
import com.scc.bukusakuonline.model.UploadPelanggaran;
import com.scc.bukusakuonline.model.peraturan.PeraturanResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
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

    @GET("api/peraturan/all")
    Call<PeraturanResponse> getPeraturan(@Header("Authorization") String auth);
    @GET("api/peraturan/all")
    Call<PeraturanResponse> getPeraturanById(@Header("Authorization") String auth,@Query("id") String id);

    @GET("api/lapor")
    Call<LaporanByIdResponse>getLaporById(@Header("Authorization") String Auth, @Query("id") String id);

    @FormUrlEncoded
    @POST("api/lapor/upload")
    Call<UploadPelanggaran> uploadPelanggaran(@Header("Authorization") String auth,@Field("pelanggaran_kategori") String Pelanggaran, @Field("nis") Double Nis , @Field("image") String Image);
}
