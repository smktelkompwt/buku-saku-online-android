package com.scc.bukusakuonline.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scc.bukusakuonline.R;
import com.scc.bukusakuonline.adapter.AdapterAktivitasTerbaru;
import com.scc.bukusakuonline.connection.ApiService;
import com.scc.bukusakuonline.connection.RetroConfig;
import com.scc.bukusakuonline.model.dashboard.Data;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private Data data;
    int position;
    TextView jumlahsiswa,jumlahpelanggaran;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
        jumlahsiswa = root.findViewById(R.id.JumlahSiswa);
        jumlahpelanggaran = root.findViewById(R.id.JumlahPelanggar);
        getData();

        return root;
    }

    private void getData() {

        dashboardViewModel.loadData(getContext());
        dashboardViewModel.getListData().observe(getActivity(), dataItems -> {
            try{
                jumlahpelanggaran.setText(String.valueOf(dataItems.getCountPelanggaran()));
                jumlahsiswa.setText(String.valueOf(dataItems.getCountSiswa()));
            }catch(Exception e){

            }
        });
    }
}