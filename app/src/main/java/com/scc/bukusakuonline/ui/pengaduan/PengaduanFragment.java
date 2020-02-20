package com.scc.bukusakuonline.ui.pengaduan;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scc.bukusakuonline.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PengaduanFragment extends Fragment {


    public PengaduanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pengaduan, container, false);
    }

}
