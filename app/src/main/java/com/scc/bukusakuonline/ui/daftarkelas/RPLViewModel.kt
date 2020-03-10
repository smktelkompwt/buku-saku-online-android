package com.scc.bukusakuonline.ui.daftarkelas

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.scc.bukusakuonline.connection.ApiService
import com.scc.bukusakuonline.connection.RetroConfig
import com.scc.bukusakuonline.model.DetailPointItems
import com.scc.bukusakuonline.model.DetailPointResponse
import com.scc.bukusakuonline.model.siswa.SiswaKelasItem
import com.scc.bukusakuonline.model.siswa.SiswaKelasResponse
import retrofit2.Call
import retrofit2.Response

class RPLViewModel : ViewModel() {

    private  var listSiswa : MutableLiveData<List<SiswaKelasItem>> = MutableLiveData()


    fun loadData(context: Context){
        Log.d("viewmodel", "viewmodel")
        val sharedPreferences = context.getSharedPreferences("PREF", Context.MODE_PRIVATE)
        val token ="Bearer "+ sharedPreferences.getString("TOKEN","abc")
        RetroConfig.getRetrofit().create(ApiService::class.java).getSiswa(token,"isi apa?? kan aku wis get  spinner e isi kelas").enqueue(object : retrofit2.Callback<SiswaKelasResponse>{
            override fun onFailure(call: Call<SiswaKelasResponse>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<SiswaKelasResponse>, response: Response<SiswaKelasResponse>) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }


        })
    }
    val listData: LiveData<List<SiswaKelasItem>> = listSiswa

}