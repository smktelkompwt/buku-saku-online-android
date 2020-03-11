package com.scc.bukusakuonline.connection;

import com.scc.bukusakuonline.model.DetailPointResponse;

import com.scc.bukusakuonline.model.kelas.KelasResponse;
import com.scc.bukusakuonline.model.riwayat.LaporanResponse;

import com.scc.bukusakuonline.model.riwayat.LaporanByIdResponse;

import com.scc.bukusakuonline.model.Login;

import com.scc.bukusakuonline.model.siswa.DetailSiswaResponse;
import com.scc.bukusakuonline.model.siswa.SiswaKelasResponse;

import com.scc.bukusakuonline.model.UploadPelanggaran;
import com.scc.bukusakuonline.model.peraturan.DetailPasalResponse;
import com.scc.bukusakuonline.model.peraturan.PeraturanById;
import com.scc.bukusakuonline.model.peraturan.PeraturanResponse;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    // point
    @GET("api/point/all")
    Call<DetailPointResponse> getDetailPoint(@Header("Authorization") String Auth);
    //login
    @FormUrlEncoded
    @POST("api/users/admin/login")
    Call<Login> login(@Field("email") String email, @Field("password") String password, @Field("secretCode") String secret);
    //kelas + siswa
    @GET("api/kelas")
    Call<SiswaKelasResponse>getSiswa(@Header("Authorization") String Auth, @Query("kelas") String kelas);

    @GET("api/kelas/all")
    Call<KelasResponse> getKelas(@Header("Authorization") String Auth, @Query("jurusan") String jurusan);

    @GET("api/users/get")
    Call<DetailSiswaResponse> getDetailSsiswa(@Header("Authorization") String Auth, @Query("id") String id);

    //lapor
    @GET("api/lapor/all")
    Call<LaporanResponse>getLapor(@Header("Authorization") String Auth);

    @GET("api/lapor")
    Call<LaporanByIdResponse>getLaporById(@Header("Authorization") String Auth, @Query("id") String id);

    @FormUrlEncoded
    @POST("api/lapor/upload")
    Call<UploadPelanggaran> uploadPelanggaran(@Header("Authorization") String auth,@Field("pelanggaran_kategori") String Pelanggaran, @Field("nis") Double Nis , @Field("image") String Image);

    //peraturan

    @GET("api/peraturan/all")
    Call<PeraturanResponse> getPeraturan(@Header("Authorization") String auth);
    @GET("api/peraturan")
    Call<PeraturanById> getPeraturanById(@Header("Authorization") String auth, @Query("id") String id);

    @GET("api/peraturan/pasal")
    Call<DetailPasalResponse> getPasal(@Header("Authorization") String auth, @Query("id") String id, @Query("idPasal") String idPasal);


}
