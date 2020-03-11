package com.scc.bukusakuonline.ui.peraturan

import android.content.Context
import android.util.Log.d
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.scc.bukusakuonline.connection.ApiService
import com.scc.bukusakuonline.connection.RetroConfig
import com.scc.bukusakuonline.model.peraturan.PeraturanItems
import com.scc.bukusakuonline.model.peraturan.PeraturanResponse
import retrofit2.Call
import retrofit2.Response

class PeraturanViewModel :ViewModel() {
    private  var listPoint : MutableLiveData<List<PeraturanItems>> = MutableLiveData()


    fun loadData(context: Context){
        d("viewmodel","viewmodel")
        val sharedPreferences = context.getSharedPreferences("PREF", Context.MODE_PRIVATE)
        val token ="Bearer "+ sharedPreferences.getString("TOKEN","abc")
        RetroConfig.getRetrofit().create(ApiService::class.java).getPeraturan(token).enqueue(object : retrofit2.Callback<PeraturanResponse>{
            override fun onFailure(call: Call<PeraturanResponse>, t: Throwable) {
                d("error","error")
            }

            override fun onResponse(call: Call<PeraturanResponse>, response: Response<PeraturanResponse>) {
                response.body()?.data.let {
                    listPoint.postValue(it)
                }
            }

        })
    }
    val listData: LiveData<List<PeraturanItems>> = listPoint
}