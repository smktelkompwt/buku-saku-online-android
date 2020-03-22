package com.scc.bukusakuonline.ui.detailperaturan;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scc.bukusakuonline.R;
import com.scc.bukusakuonline.adapter.AdapterBab;

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
        try {
            recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
            detailPeraturanViewModel = ViewModelProviders.of(this).get(DetailPeraturanViewModel.class);
            detailPeraturanViewModel.loadData(this,id);
            detailPeraturanViewModel.getListData().observe(this, pasalItems -> {
                try {


                    adapterBab = new AdapterBab(this, pasalItems, id);

                    recyclerView.setAdapter(adapterBab);
                    adapterBab.notifyDataSetChanged();
                }catch (Exception e){

                }
            });
        }catch (Exception e){

        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
