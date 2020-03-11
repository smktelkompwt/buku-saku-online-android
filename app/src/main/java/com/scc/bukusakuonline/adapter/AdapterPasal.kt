package com.scc.bukusakuonline.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.scc.bukusakuonline.R
import com.scc.bukusakuonline.model.peraturan.DetailPasalItems

class AdapterPasal(private  val context: Context, private val items:List<DetailPasalItems>): RecyclerView.Adapter<AdapterPasal.ViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_detail_pasal, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position],position)
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private val idPasal = view.findViewById<TextView>(R.id.id_pasal)
        private val desc = view.findViewById<TextView>(R.id.desc_pasal)

        fun bindItem(items: DetailPasalItems, position: Int){
            Log.d("items", position.toString())
            idPasal.text = (position + 1).toString()
            desc.text = items.descPasal

        }
    }
}