package com.scc.bukusakuonline.ui.detailpasal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.scc.bukusakuonline.R;
import com.scc.bukusakuonline.adapter.AdapterPasal;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailPasalActivity extends AppCompatActivity {

    AdapterPasal adapterPasal;
    DetailPasalViewModel detailPasalViewModel;

    @BindView(R.id.rv_pasal)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pasal);
        ButterKnife.bind(this);
        String id = getIntent().getStringExtra("id");
        String id_pasal = getIntent().getStringExtra("id_pasal");
        String title = getIntent().getStringExtra("title");
        Objects.requireNonNull(getSupportActionBar()).setTitle(title);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getData(id,id_pasal);
    }

    private void getData(String id, String id_pasal) {
        try {
            detailPasalViewModel = ViewModelProviders.of(this).get(DetailPasalViewModel.class);
            detailPasalViewModel.loadData(this,id,id_pasal);
            detailPasalViewModel.getListData().observe(this, detailPasalItems -> {
                if (detailPasalItems != null){
                    adapterPasal = new AdapterPasal(getApplicationContext(), detailPasalItems);
                    recyclerView.setAdapter(adapterPasal);
                    adapterPasal.notifyDataSetChanged();
                }
            });
        }catch (Exception e){

        }

    }
}
