package com.scc.bukusakuonline.ui.detailperaturan

import android.content.Context
import android.util.Log.d
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.scc.bukusakuonline.connection.ApiService
import com.scc.bukusakuonline.connection.RetroConfig
import com.scc.bukusakuonline.model.peraturan.PasalItems
import com.scc.bukusakuonline.model.peraturan.PeraturanById
import com.scc.bukusakuonline.model.peraturan.PeraturanItems
import com.scc.bukusakuonline.model.peraturan.PeraturanResponse
import retrofit2.Call
import retrofit2.Response

class DetailPeraturanViewModel :ViewModel() {
    private  var listPoint : MutableLiveData<List<PasalItems>> = MutableLiveData()


    fun loadData(context: Context,id :String){
        d("viewmodel","viewmodel")
        val sharedPreferences = context.getSharedPreferences("PREF", Context.MODE_PRIVATE)
        val token ="Bearer "+ sharedPreferences.getString("TOKEN","abc")
        RetroConfig.getRetrofit().create(ApiService::class.java).getPeraturanById(token,id).enqueue(object : retrofit2.Callback<PeraturanById>{
            override fun onFailure(call: Call<PeraturanById>, t: Throwable) {
                d("error",t.message)
            }

            override fun onResponse(call: Call<PeraturanById>, response: Response<PeraturanById>) {
                response.body()?.data?.pasal.let {
                    listPoint.postValue(it)
                }
            }

        })
    }
    val listData: LiveData<List<PasalItems>> = listPoint
}