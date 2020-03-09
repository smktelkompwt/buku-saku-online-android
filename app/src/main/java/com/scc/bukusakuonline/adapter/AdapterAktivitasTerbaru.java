package com.scc.bukusakuonline.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.scc.bukusakuonline.R;

public class AdapterAktivitasTerbaru extends RecyclerView.Adapter<AdapterAktivitasTerbaru.AktivitasTerbaruViewHolder> {
    @NonNull
    @Override
    public AdapterAktivitasTerbaru.AktivitasTerbaruViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_aktivitas_new,parent,false);
        return new AktivitasTerbaruViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAktivitasTerbaru.AktivitasTerbaruViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class AktivitasTerbaruViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public AktivitasTerbaruViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {
            int p = getAdapterPosition();
        }
    }
}
