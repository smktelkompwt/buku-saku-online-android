package com.scc.bukusakuonline.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.scc.bukusakuonline.R;

import java.util.Objects;

public class LaguHymneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lagu_hymne);

        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.detail_5 ));
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
