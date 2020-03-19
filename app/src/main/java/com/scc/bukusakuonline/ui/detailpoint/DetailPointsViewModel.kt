package com.scc.bukusakuonline.ui.detailpoint

import android.content.Context
import android.util.Log.d
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.scc.bukusakuonline.connection.ApiService
import com.scc.bukusakuonline.connection.RetroConfig
import com.scc.bukusakuonline.model.detailpoint.DetailPointItems
import com.scc.bukusakuonline.model.detailpoint.DetailPointResponse
import retrofit2.Call
import retrofit2.Response

class DetailPointsViewModel : ViewModel(){
    private  var listPoint : MutableLiveData<List<DetailPointItems>> = MutableLiveData()


    fun loadData(context: Context){
        d("viewmodel","viewmodel")
        val sharedPreferences = context.getSharedPreferences("PREF", Context.MODE_PRIVATE)
        val token ="Bearer "+ sharedPreferences.getString("TOKEN","abc")
        RetroConfig.getRetrofit().create(ApiService::class.java).getDetailPoint(token).enqueue(object : retrofit2.Callback<DetailPointResponse>{
            override fun onFailure(call: Call<DetailPointResponse>, t: Throwable) {
                d("error","failure")
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