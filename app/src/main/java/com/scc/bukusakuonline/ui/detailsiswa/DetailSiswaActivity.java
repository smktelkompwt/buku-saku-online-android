package com.scc.bukusakuonline.ui.detailsiswa;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scc.bukusakuonline.R;
import com.scc.bukusakuonline.adapter.AdapterAktivitasTerbaru;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailSiswaActivity extends AppCompatActivity {

    DetailSiswaViewModel detailSiswaViewModel;
    AdapterAktivitasTerbaru adapterAktivitasTerbaru;
    @BindView(R.id.rv_detail_siswa)
    RecyclerView recyclerView;
    @BindView(R.id.profile_detail_siswa)
    CircleImageView photo;
    @BindView(R.id.nama_lengkap)
    TextView nama;
    @BindView(R.id.kelas)
    TextView kelas;
    @BindView(R.id.jumlah_point)
    TextView point;
    @BindView(R.id.jumlah_pelanggara_detail)
    TextView jumlah;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_detail_siswa);
        ButterKnife.bind(this);
        String id = getIntent().getStringExtra("id");
        String name = getIntent().getStringExtra("name");
        Objects.requireNonNull(getSupportActionBar()).setTitle(name);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setElevation(0);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        init(id);

    }

    private void init(String id) {

        try {
            detailSiswaViewModel = ViewModelProviders.of(this).get(DetailSiswaViewModel.class);
            detailSiswaViewModel.loadData(this,id);
            detailSiswaViewModel.getListData().observe(this, detailSiswaItems->{
                try {
                    String url = "http://" + Objects.requireNonNull(detailSiswaItems.getPelanggaran()).get(0).getImage();
                    Picasso.get().load(url).into(photo);
                    nama.setText(detailSiswaItems.getName());
                    Log.d("cek",detailSiswaItems.getName() );
                    kelas.setText(detailSiswaItems.getKelas());
                    point.setText(Objects.requireNonNull(Objects.requireNonNull(detailSiswaItems.getPoint()).toString()));
                    jumlah.setText(detailSiswaItems.getJumlah());
                    adapterAktivitasTerbaru = new AdapterAktivitasTerbaru(this,detailSiswaItems.getPelanggaran());
                    recyclerView.setAdapter(adapterAktivitasTerbaru);
                    adapterAktivitasTerbaru.notifyDataSetChanged();
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
