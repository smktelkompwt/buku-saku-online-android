package com.scc.bukusakuonline.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.scc.bukusakuonline.R
import com.scc.bukusakuonline.model.Riwayat.User

class AdapterSiswa (private  val context: Context, private val Items:List<User>): RecyclerView.Adapter<AdapterSiswa.ViewHolder>() {
    class ViewHolder (view: View): RecyclerView.ViewHolder(view)  {
        private val name = view.findViewById<TextView>(R.id.namaSiswa)
        private val nis = view.findViewById<TextView>(R.id.nisSiswa)


        fun bindItem(items: User){

            name.text = items.nama
            nis.text = items.nis.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterSiswa.ViewHolder {
        return AdapterSiswa.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_siswa, parent, false))
    }

    override fun getItemCount(): Int  = Items.size


    override fun onBindViewHolder(holder: AdapterSiswa.ViewHolder, position: Int) {
        holder.bindItem(Items[position])
    }
}