package com.scc.bukusakuonline.ui.detailsiswa

import android.content.Context
import android.util.Log
import android.util.Log.d
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.scc.bukusakuonline.connection.ApiService
import com.scc.bukusakuonline.connection.RetroConfig
import com.scc.bukusakuonline.model.peraturan.PasalItems
import com.scc.bukusakuonline.model.peraturan.PeraturanById
import com.scc.bukusakuonline.model.siswa.DetailSiswaItems
import com.scc.bukusakuonline.model.siswa.DetailSiswaResponse
import retrofit2.Call
import retrofit2.Response

class DetailSiswaViewModel :ViewModel(){
    private  var listPoint : MutableLiveData<DetailSiswaItems> = MutableLiveData()


    fun loadData(context: Context, id :String){
        Log.d("viewmodel", "viewmodel")
        val sharedPreferences = context.getSharedPreferences("PREF", Context.MODE_PRIVATE)
        val token ="Bearer "+ sharedPreferences.getString("TOKEN","abc")
        RetroConfig.getRetrofit().create(ApiService::class.java).getDetailSsiswa(token,id).enqueue(object : retrofit2.Callback<DetailSiswaResponse>{
            override fun onFailure(call: Call<DetailSiswaResponse>, t: Throwable) {
                d("error",t.message)
            }

            override fun onResponse(call: Call<DetailSiswaResponse>, response: Response<DetailSiswaResponse>) {
                listPoint.postValue(response.body()?.data)
            }

        })
    }
    val listData: LiveData<DetailSiswaItems> = listPoint
}