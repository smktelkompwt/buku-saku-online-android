package com.scc.bukusakuonline.ui.dashboard

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.scc.bukusakuonline.connection.ApiService
import com.scc.bukusakuonline.connection.RetroConfig
import com.scc.bukusakuonline.model.dashboard.DashboardResponse
import com.scc.bukusakuonline.model.dashboard.Data
import retrofit2.Call
import retrofit2.Response


class DashboardViewModel : ViewModel() {
    private  var listDB : MutableLiveData<Data> = MutableLiveData()
//    private val getPegawai: MutableLiveData<DashboardResponse> = MutableLiveData()

    fun loadData(context: Context){
        Log.d("viewmodel", "viewmodel")
        val sharedPreferences = context.getSharedPreferences("PREF", Context.MODE_PRIVATE)
        val token ="Bearer "+ sharedPreferences.getString("TOKEN","abc")
        RetroConfig.getRetrofit().create(ApiService::class.java).getDashboard(token).enqueue(object : retrofit2.Callback<DashboardResponse>{
            override fun onFailure(call: Call<DashboardResponse>, t: Throwable) {
                Log.d("error", "failure")
            }

            override fun onResponse(call: Call<DashboardResponse>, response: Response<DashboardResponse>) {
                if (response.code() == 200){
                    response.body()?.data.let {
                        listDB.postValue(it)
                    }
                }
            }

        })
    }
    val listData: LiveData<Data> = listDB
}