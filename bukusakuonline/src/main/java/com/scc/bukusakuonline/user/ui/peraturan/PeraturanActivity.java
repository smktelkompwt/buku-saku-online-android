package com.scc.bukusakuonline.user.ui.peraturan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;


import com.scc.bukusakuonline.user.R;
import com.scc.bukusakuonline.user.adapter.AdapterPeraturan;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PeraturanActivity extends AppCompatActivity {
    AdapterPeraturan adapterPeraturan;
    PeraturanViewModel peraturanViewModel;

    @BindView(R.id.rv_peraturan)
    RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peraturan);
        ButterKnife.bind(this);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.detail_peraturan));
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getData();
    }

    private void getData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        peraturanViewModel = ViewModelProviders.of(this).get(PeraturanViewModel.class);
        peraturanViewModel.loadData(this);
        peraturanViewModel.getListData().observe(this, peraturanItems -> {
            if (peraturanItems != null){
                adapterPeraturan = new AdapterPeraturan(getApplicationContext(),peraturanItems);
                mRecyclerView.setAdapter(adapterPeraturan);
                adapterPeraturan.notifyDataSetChanged();
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
