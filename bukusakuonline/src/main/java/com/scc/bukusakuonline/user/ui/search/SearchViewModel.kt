package com.scc.bukusakuonline.user.ui.search

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.scc.bukusakuonline.user.connection.ApiService
import com.scc.bukusakuonline.user.connection.RetroConfig
import com.scc.bukusakuonline.user.model.siswa.SiswaKelasItem
import com.scc.bukusakuonline.user.model.siswa.SiswaKelasResponse
import retrofit2.Call
import retrofit2.Response

class SearchViewModel : ViewModel() {
    private var listPoint: MutableLiveData<List<SiswaKelasItem>> = MutableLiveData()


    fun loadData(context: Context, nis: String) {
        Log.d("viewmodel", "viewmodel")
        val sharedPreferences = context.getSharedPreferences("PREF", Context.MODE_PRIVATE)
        val token = "Bearer " + sharedPreferences.getString("TOKEN", "abc")
        RetroConfig.getRetrofit().create(ApiService::class.java).searchSiswa(token, nis).enqueue(object : retrofit2.Callback<SiswaKelasResponse> {
            override fun onFailure(call: Call<SiswaKelasResponse>, t: Throwable) {
                Log.d("error", t.message)
            }

            override fun onResponse(call: Call<SiswaKelasResponse>, response: Response<SiswaKelasResponse>) {
                response.body()?.data.let {
                    listPoint.postValue(it)
                }


            }
        })
    }

    val listData: LiveData<List<SiswaKelasItem>> = listPoint
}