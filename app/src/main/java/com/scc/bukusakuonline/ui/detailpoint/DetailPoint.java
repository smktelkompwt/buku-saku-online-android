package com.scc.bukusakuonline.ui.detailpoint;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scc.bukusakuonline.R;
import com.scc.bukusakuonline.R2;
import com.scc.bukusakuonline.adapter.AdapterDetailPoint;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailPoint extends AppCompatActivity {
    AdapterDetailPoint mAdapterDetailPoint;
    DetailPointsViewModel mDetailPointsViewModel;

    @BindView(R2.id.rv_point) RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_point);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.detail_pelanggaran));
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setElevation(0);
        ButterKnife.bind(this);
        getPoint();
    }

    private void getPoint() {
        try {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
            mDetailPointsViewModel = ViewModelProviders.of(this).get(DetailPointsViewModel.class);
            mDetailPointsViewModel.loadData(this);
            mDetailPointsViewModel.getListData().observe(this, detailPointItems -> {
                if (detailPointItems != null){
                    mAdapterDetailPoint = new AdapterDetailPoint(getApplicationContext(),detailPointItems);
                    mRecyclerView.setAdapter(mAdapterDetailPoint);
                    mAdapterDetailPoint.notifyDataSetChanged();
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
