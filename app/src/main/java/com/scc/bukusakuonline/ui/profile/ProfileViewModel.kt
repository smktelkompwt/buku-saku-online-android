package com.scc.bukusakuonline.ui.profile

import android.content.Context
import android.util.Log.d
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.scc.bukusakuonline.connection.ApiService
import com.scc.bukusakuonline.connection.RetroConfig
import com.scc.bukusakuonline.model.user.UserItem
import com.scc.bukusakuonline.model.user.UserResponse
import retrofit2.Call
import retrofit2.Response

class ProfileViewModel : ViewModel() {
    private  var listPoint : MutableLiveData<UserItem> = MutableLiveData()


    fun loadData(context: Context){
        d("viewmodel", "viewmodel")
        val sharedPreferences = context.getSharedPreferences("PREF", Context.MODE_PRIVATE)
        val token ="Bearer "+ sharedPreferences.getString("TOKEN","abc")
        RetroConfig.getRetrofit().create(ApiService::class.java).profile(token).enqueue(object : retrofit2.Callback<UserResponse>{
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                d("error","error")
            }

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                try {
//                    if (response.body()?.data?.size!! > 0){
                        response.body()?.data.let {
                            listPoint.postValue(it)
                        }
//                    }
                }catch (e: Exception){
                    Toast.makeText(context,"Silahkan Login Kembali",Toast.LENGTH_SHORT).show()
                }


            }

        })
    }
    val listData: LiveData<UserItem> = listPoint
}