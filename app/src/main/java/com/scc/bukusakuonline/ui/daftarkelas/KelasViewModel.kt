package com.scc.bukusakuonline.ui.daftarkelas

import android.content.Context
import android.util.Log
import android.util.Log.d
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.scc.bukusakuonline.R2.string.kelas
import com.scc.bukusakuonline.connection.ApiService
import com.scc.bukusakuonline.connection.RetroConfig
import com.scc.bukusakuonline.model.DetailPointItems
import com.scc.bukusakuonline.model.DetailPointResponse
import com.scc.bukusakuonline.model.siswa.SiswaKelasItem
import com.scc.bukusakuonline.model.siswa.SiswaKelasResponse
import retrofit2.Call
import retrofit2.Response

class KelasViewModel : ViewModel() {

    private  var listSiswa : MutableLiveData<List<SiswaKelasItem>> = MutableLiveData()


    fun loadData(context: Context ,kelass : String){
        val sharedPreferences = context.getSharedPreferences("PREF", Context.MODE_PRIVATE)
        val token ="Bearer "+ sharedPreferences.getString("TOKEN","abc")
        RetroConfig.getRetrofit().create(ApiService::class.java).getSiswa(token,kelass).enqueue(object : retrofit2.Callback<SiswaKelasResponse>{
            override fun onFailure(call: Call<SiswaKelasResponse>, t: Throwable) {
                d("error","failure")
            }

            override fun onResponse(call: Call<SiswaKelasResponse>, response: Response<SiswaKelasResponse>) {
                if (response.code() == 200){
                    response.body()?.data.let {
                        listSiswa.postValue(it)
                    }
                }
            }


        })
    }
    val listData: LiveData<List<SiswaKelasItem>> = listSiswa

}