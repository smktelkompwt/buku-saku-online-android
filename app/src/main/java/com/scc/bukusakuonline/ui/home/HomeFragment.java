package com.scc.bukusakuonline.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.scc.bukusakuonline.R;
import com.scc.bukusakuonline.R2;
import com.scc.bukusakuonline.adapter.AdapterAktivitasTerbaru;
import com.scc.bukusakuonline.ui.LainyaActivity;
import com.scc.bukusakuonline.ui.daftarkelas.DaftarKelas;
import com.scc.bukusakuonline.ui.peraturan.PeraturanActivity;
import com.scc.bukusakuonline.ui.detailpoint.DetailPoint;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {
    AdapterAktivitasTerbaru mAdapterAktivitasTerbaru;

    private HomeViewModel homeViewModel;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout appBarLayout;
    @BindView(R2.id.rv_aktivitas_new) RecyclerView mRecyclerView;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,root);
        SearchView searchView = root.findViewById(R.id.sercing);
        CardView menu_peraturan = root.findViewById(R.id.menu_peraturan);
        CardView menu_daftar_kelas = root.findViewById(R.id.menu_daftar_kelas);
        CardView menu_pelanggaran = root.findViewById(R.id.menu_pelanggaran);
        CardView menu_lainya = root.findViewById(R.id.menu_lainya);
        menu_peraturan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), PeraturanActivity.class));
            }
        });
        menu_daftar_kelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), DaftarKelas.class));
            }
        });
        menu_pelanggaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), DetailPoint.class));
            }
        });
        menu_lainya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), LainyaActivity.class));
            }
        });
        getData();
        return root;
    }

    private void getData() {
        try {
            homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
            homeViewModel.loadData(getContext());
            homeViewModel.getListData().observe(getActivity(), dataItems -> {
                if (dataItems != null){
                    mAdapterAktivitasTerbaru = new AdapterAktivitasTerbaru(getContext(),dataItems);
                    mRecyclerView.setAdapter(mAdapterAktivitasTerbaru);
                    mAdapterAktivitasTerbaru.notifyDataSetChanged();
                }
            });
        }catch (Exception e){

        }

    }

    //to hide toolbar if conflict please resolve
    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }
    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }
}