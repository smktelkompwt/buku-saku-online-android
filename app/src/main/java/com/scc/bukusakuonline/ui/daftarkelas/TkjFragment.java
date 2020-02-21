package com.scc.bukusakuonline.ui.daftarkelas;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.scc.bukusakuonline.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TkjFragment extends Fragment implements AdapterView.OnItemSelectedListener{


    public TkjFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tkj, container, false);
    }

}
