package com.scc.bukusakuonline.ui.detailpasal

import android.content.Context
import android.util.Log.d
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.scc.bukusakuonline.user.connection.ApiService
import com.scc.bukusakuonline.user.connection.RetroConfig
import com.scc.bukusakuonline.user.model.peraturan.DetailPasalItems
import com.scc.bukusakuonline.user.model.peraturan.DetailPasalResponse
import retrofit2.Call
import retrofit2.Response

class DetailPasalViewModel : ViewModel() {
    private  var listPoint : MutableLiveData<List<DetailPasalItems>> = MutableLiveData()


    fun loadData(context: Context, id : String, idPasal : String){
        d("viewmodel","viewmodel")
        val sharedPreferences = context.getSharedPreferences("PREF", Context.MODE_PRIVATE)
        val token ="Bearer "+ sharedPreferences.getString("TOKEN","abc")
        RetroConfig.getRetrofit().create(ApiService::class.java).getPasal(token,id,idPasal).enqueue(object : retrofit2.Callback<DetailPasalResponse>{
            override fun onFailure(call: Call<DetailPasalResponse>, t: Throwable) {
                d("error",t.message)
            }

            override fun onResponse(call: Call<DetailPasalResponse>, response: Response<DetailPasalResponse>) {
                response.body()?.data?.desc.let {
                    listPoint.postValue(it)
                }
            }

        })
    }
    val listData: LiveData<List<DetailPasalItems>> = listPoint
}