package com.scc.bukusakuonline.ui.detailpelanggaran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.scc.bukusakuonline.R;
import com.scc.bukusakuonline.R2;
import com.scc.bukusakuonline.ui.detailpoint.DetailPointsViewModel;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailPelanggaran extends AppCompatActivity {
    DetailPelanggaranViewModel mDetailPelanggaranViewModel;
    @BindView(R.id.photo_detail)
    ImageView imageView;
    @BindView(R.id.name_detail)
    TextView name_detail;
    @BindView(R.id.class_detail)
    TextView classDetail;
    @BindView(R.id.category_detail)
    TextView category;
    @BindView(R.id.point_detail)
    TextView point;
    @BindView(R.id.rules_detail)
    TextView rules;
    @BindView(R.id.user_detail)
    TextView user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pelanggaran);
        ButterKnife.bind(this);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.detail_pelanggaran));
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        String id = getIntent().getStringExtra("id");
        getData(id);
    }

    private void getData(String id) {
        mDetailPelanggaranViewModel = ViewModelProviders.of(this).get(DetailPelanggaranViewModel.class);
        mDetailPelanggaranViewModel.loadData(this,id);
        mDetailPelanggaranViewModel.getListData().observe(this, dataItems -> {
            Picasso.get().load(dataItems.get(0).getImage()).fit().into(imageView);
            name_detail.setText(dataItems.get(0).getUser().getNama());
            classDetail.setText(dataItems.get(0).getUser().getKelas());
            category.setText(dataItems.get(0).getPelanggaran().getKategori());
            point.setText(String.valueOf(dataItems.get(0).getPelanggaran().getPoint()));
            rules.setText(dataItems.get(0).getPelanggaran().getKategori());
            user.setText(dataItems.get(0).getPelapor().getNama());
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
