package com.scc.bukusakuonline.user.ui.detailsiswa;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scc.bukusakuonline.user.R;
import com.scc.bukusakuonline.user.adapter.AdapterAktivitasTerbaru;
import com.scc.bukusakuonline.user.ui.detailsiswa.DetailSiswaViewModel;
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
        detailSiswaViewModel = ViewModelProviders.of(this).get(DetailSiswaViewModel.class);
        detailSiswaViewModel.loadData(this,id);
        detailSiswaViewModel.getListData().observe(this, detailSiswaItems->{
            try {
                String url = "http://" + Objects.requireNonNull(detailSiswaItems.getPelanggaran()).get(0).getImage();
                Picasso.get().load(url).into(photo);

                adapterAktivitasTerbaru = new AdapterAktivitasTerbaru(this,detailSiswaItems.getPelanggaran());
                recyclerView.setAdapter(adapterAktivitasTerbaru);
                adapterAktivitasTerbaru.notifyDataSetChanged();
            }catch (Exception e){
//                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Gagal Load Data", Toast.LENGTH_LONG).show();
            }
            nama.setText(detailSiswaItems.getName());
            kelas.setText(detailSiswaItems.getKelas());
            point.setText(Objects.requireNonNull(Objects.requireNonNull(detailSiswaItems.getPoint()).toString()));
            jumlah.setText(detailSiswaItems.getJumlah());

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
