package com.scc.bukusakuonline.ui.pengaduan

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.scc.bukusakuonline.connection.ApiService
import com.scc.bukusakuonline.connection.RetroConfig
import com.scc.bukusakuonline.model.DetailPointItems
import com.scc.bukusakuonline.model.DetailPointResponse
import retrofit2.Call
import retrofit2.Response

class PengaduanViewModel : ViewModel(){
    private  var listPoint : MutableLiveData<List<DetailPointItems>> = MutableLiveData()


    fun loadData(context: Context){
        Log.d("viewmodel", "viewmodel")
        val sharedPreferences = context.getSharedPreferences("PREF", Context.MODE_PRIVATE)
        val token ="Bearer "+ sharedPreferences.getString("TOKEN","abc")
        RetroConfig.getRetrofit().create(ApiService::class.java).getDetailPoint(token).enqueue(object : retrofit2.Callback<DetailPointResponse>{
            override fun onFailure(call: Call<DetailPointResponse>, t: Throwable) {
                Log.d("error", "failure")
            }

            override fun onResponse(call: Call<DetailPointResponse>, response: Response<DetailPointResponse>) {
                if (response.code() == 200){
                    response.body()?.data.let {
                        listPoint.postValue(it)
                    }
                }
            }

        })
    }
    val listData: LiveData<List<DetailPointItems>> = listPoint
}