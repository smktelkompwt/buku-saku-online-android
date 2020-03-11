package com.scc.bukusakuonline.ui.home

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.scc.bukusakuonline.connection.ApiService
import com.scc.bukusakuonline.connection.RetroConfig
import com.scc.bukusakuonline.model.Riwayat.DataItem
import com.scc.bukusakuonline.model.Riwayat.LaporanResponse

import retrofit2.Call
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private  var listPoint : MutableLiveData<List<DataItem>> = MutableLiveData()


    fun loadData(context: Context){
        Log.d("viewmodel", "viewmodel")
        val sharedPreferences = context.getSharedPreferences("PREF", Context.MODE_PRIVATE)
        val token ="Bearer "+ sharedPreferences.getString("TOKEN","abc")
        RetroConfig.getRetrofit().create(ApiService::class.java).getLapor(token).enqueue(object : retrofit2.Callback<LaporanResponse>{
            override fun onFailure(call: Call<LaporanResponse>, t: Throwable) {
                Log.d("error", "failure")
            }

            override fun onResponse(call: Call<LaporanResponse>, response: Response<LaporanResponse>) {
                if (response.code() == 200){
                    response.body()?.data.let {
                        listPoint.postValue(it)
                    }
                }
            }

        })
    }
    val listData: LiveData<List<DataItem>> = listPoint
}