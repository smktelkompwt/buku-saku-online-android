package com.scc.bukusakuonline.user.ui.detailperaturan;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scc.bukusakuonline.user.R;
import com.scc.bukusakuonline.user.adapter.AdapterBab;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailPeraturanActivity extends AppCompatActivity {
    DetailPeraturanViewModel detailPeraturanViewModel;
    AdapterBab adapterBab;
    @BindView(R.id.rv_detail_peraturan)
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_peraturan);
        ButterKnife.bind(this);
        String id = getIntent().getStringExtra("id");
        String title= getIntent().getStringExtra("title");
        Objects.requireNonNull(getSupportActionBar()).setTitle(title);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getData(id);
    }

    private void getData(String id) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        detailPeraturanViewModel = ViewModelProviders.of(this).get(DetailPeraturanViewModel.class);
        detailPeraturanViewModel.loadData(this,id);
        detailPeraturanViewModel.getListData().observe(this, pasalItems -> {
            adapterBab = new AdapterBab(this, pasalItems ,id);

            recyclerView.setAdapter(adapterBab);
            adapterBab.notifyDataSetChanged();
        });
    }
}
