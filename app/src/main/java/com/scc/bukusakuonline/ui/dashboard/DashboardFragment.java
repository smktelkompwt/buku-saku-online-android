package com.scc.bukusakuonline.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
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
import com.scc.bukusakuonline.adapter.AdapterDashboard;
import com.scc.bukusakuonline.connection.ApiService;
import com.scc.bukusakuonline.connection.RetroConfig;
import com.scc.bukusakuonline.model.dashboard.DashboardViolation;
import com.scc.bukusakuonline.model.dashboard.Data;

import java.util.List;

public class DashboardFragment extends Fragment {
    AdapterDashboard adapterDashboard;
    private DashboardViewModel dashboardViewModel;
    TextView jumlahsiswa,jumlahpelanggaran;
    RecyclerView mRecyclerView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
        jumlahsiswa = root.findViewById(R.id.JumlahSiswa);
        mRecyclerView = root.findViewById(R.id.dashboard_items);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        jumlahpelanggaran = root.findViewById(R.id.JumlahPelanggar);
        getData();

        return root;
    }

    private void getData() {

        dashboardViewModel.loadData(getContext());
        dashboardViewModel.getListData().observe(getActivity(), dataItems -> {

            try{
                if (dataItems != null) {
                    adapterDashboard = new AdapterDashboard(getContext(), dataItems.getPelanggaran());
                    mRecyclerView.setAdapter(adapterDashboard);
                    adapterDashboard.notifyDataSetChanged();
                }

                Log.d("data", String.valueOf(dataItems.getPelanggaran().get(0).getCount()));
                jumlahpelanggaran.setText(String.valueOf(dataItems.getCountPelanggaran()));
                jumlahsiswa.setText(String.valueOf(dataItems.getCountSiswa()));
            }catch(Exception e){

            }
        });
    }
}



