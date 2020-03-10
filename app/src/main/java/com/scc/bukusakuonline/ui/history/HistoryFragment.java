package com.scc.bukusakuonline.ui.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scc.bukusakuonline.R;
import com.scc.bukusakuonline.R2;
import com.scc.bukusakuonline.adapter.AdapterAktivitasTerbaru;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryFragment extends Fragment {
    AdapterAktivitasTerbaru mAdapterAktivitasTerbaru;
    private HistoryViewModel mHistoryViewModel;
    @BindView(R2.id.rv_history)
    RecyclerView mRecyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mHistoryViewModel =
                ViewModelProviders.of(this).get(HistoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_history, container, false);
        ButterKnife.bind(this,root);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        mHistoryViewModel.loadData(getContext());
        mHistoryViewModel.getListData().observe(getActivity(), dataItems -> {
            if (dataItems != null){
                mAdapterAktivitasTerbaru = new AdapterAktivitasTerbaru(getContext(),dataItems);
                mRecyclerView.setAdapter(mAdapterAktivitasTerbaru);
                mAdapterAktivitasTerbaru.notifyDataSetChanged();
            }
        });
        return root;
    }
}