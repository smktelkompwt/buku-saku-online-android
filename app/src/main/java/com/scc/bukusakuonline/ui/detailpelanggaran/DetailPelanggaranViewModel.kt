package com.scc.bukusakuonline.ui.detailpelanggaran

import android.content.Context
import android.util.Log
import android.util.Log.d
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.scc.bukusakuonline.connection.ApiService
import com.scc.bukusakuonline.connection.RetroConfig
import com.scc.bukusakuonline.model.Laporan.DataItem
import com.scc.bukusakuonline.model.Laporan.LaporanByIdResponse
import com.scc.bukusakuonline.model.Laporan.LaporanResponse
import retrofit2.Call
import retrofit2.Response

class DetailPelanggaranViewModel : ViewModel() {
    private  var listPoint : MutableLiveData<DataItem> = MutableLiveData()


    fun loadData(context: Context, id :String){
        Log.d("viewmodel", "viewmodel")
        val sharedPreferences = context.getSharedPreferences("PREF", Context.MODE_PRIVATE)
        val token ="Bearer "+ sharedPreferences.getString("TOKEN","abc")
        RetroConfig.getRetrofit().create(ApiService::class.java).getLaporById(token,id).enqueue(object : retrofit2.Callback<LaporanByIdResponse>{
            override fun onFailure(call: Call<LaporanByIdResponse>, t: Throwable) {
                d("error","error")
            }

            override fun onResponse(call: Call<LaporanByIdResponse>, response: Response<LaporanByIdResponse>) {
                listPoint.postValue(response.body()?.data)
            }


        })
    }
    val listData: LiveData<DataItem> = listPoint
}