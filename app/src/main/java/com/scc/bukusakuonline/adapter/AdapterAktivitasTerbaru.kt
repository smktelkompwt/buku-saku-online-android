package com.scc.bukusakuonline.adapter

import android.content.Context
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.scc.bukusakuonline.R
import com.scc.bukusakuonline.adapter.AdapterAktivitasTerbaru.AktivitasTerbaruViewHolder
import com.scc.bukusakuonline.model.Laporan.DataItem

class AdapterAktivitasTerbaru(private  val context: Context, private val Items:List<DataItem>): RecyclerView.Adapter<AktivitasTerbaruViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AktivitasTerbaruViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_aktivitas_new, parent, false)
        return AktivitasTerbaruViewHolder(v)
    }

    override fun onBindViewHolder(holder: AktivitasTerbaruViewHolder, position: Int) {
        holder.bindItem(Items[position],position)
    }
    override fun getItemCount() : Int = Items.size

    class AktivitasTerbaruViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val name = itemView.findViewById<TextView>(R.id.name_history)
        private val date = itemView.findViewById<TextView>(R.id.date_history)
        private val desc = itemView.findViewById<TextView>(R.id.desc_history)

        fun bindItem(items: DataItem, position: Int){
            d("items",position.toString())
            name.text = items.user.nama
            date.text = items.createdDate.toString()
            desc.text = items.pelanggaran.kategori

        }
        override fun onClick(v: View) {
            val p = adapterPosition
        }
    }
}