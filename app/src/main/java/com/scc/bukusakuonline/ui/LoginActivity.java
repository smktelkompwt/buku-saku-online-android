package com.scc.bukusakuonline.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.scc.bukusakuonline.R;
import com.scc.bukusakuonline.R2;
import com.scc.bukusakuonline.connection.ApiService;
import com.scc.bukusakuonline.connection.RetroConfig;
import com.scc.bukusakuonline.model.Login;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
//    @BindView(R2.id.btnLogin) MaterialButton btnLogin;
    @BindView(R2.id.txtEmail) TextInputEditText txtEmail;

    @BindView(R2.id.txtPassword) TextInputEditText txtPassword;

    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
    }
    @OnClick(R2.id.btnLogin) void login(){
        try {
            RetroConfig.getRetrofit().create(ApiService.class).
                    login(txtEmail.getText().toString(),txtPassword.getText().toString(),"001").enqueue(new Callback<Login>() {
                @Override
                public void onResponse(Call<Login> call, Response<Login> response) {
                    if (response.isSuccessful()){
                        if (response.body().getCode() == 200){
                            String token = response.body().getToken();
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("TOKEN",token);
                            editor.apply();
                            Log.d("response", token);
                        }

                    }
                }
                @Override
                public void onFailure(Call<Login> call, Throwable t) {
                    Log.d("failure",t.getMessage());

                }
            });
        }catch (Exception e){
            Log.d("error",e.getMessage());
        }
    }
}
