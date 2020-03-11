package com.scc.bukusakuonline.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.scc.bukusakuonline.R
import com.scc.bukusakuonline.adapter.AdapterAktivitasTerbaru.AktivitasTerbaruViewHolder
import com.scc.bukusakuonline.model.Riwayat.DataItem
import com.scc.bukusakuonline.ui.detailpelanggaran.DetailPelanggaran


class AdapterAktivitasTerbaru(private  val context: Context, private val Items:List<DataItem>): RecyclerView.Adapter<AktivitasTerbaruViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AktivitasTerbaruViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_aktivitas_new, parent, false)
        return AktivitasTerbaruViewHolder(v)
    }

    override fun onBindViewHolder(holder: AktivitasTerbaruViewHolder, position: Int) {
        holder.bindItem(Items[position],position, context)
    }
    override fun getItemCount() : Int = Items.size

    class AktivitasTerbaruViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val point = itemView.findViewById<TextView>(R.id.point_history)
        private val name = itemView.findViewById<TextView>(R.id.name_history)
        private val date = itemView.findViewById<TextView>(R.id.date_history)
        private val desc = itemView.findViewById<TextView>(R.id.desc_history)

        fun bindItem(items: DataItem, position: Int, context: Context){
            point.text = items.pelanggaran.point.toString()
            name.text = items.user.nama
            date.text = items.createdDate.toString()

            desc.text = items.pelanggaran.kategori
            itemView.setOnClickListener {
                val intent = Intent(context, DetailPelanggaran::class.java)
                intent.putExtra("id", items.id)
                context.startActivity(intent)
            }
        }


    }
}