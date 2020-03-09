package com.scc.bukusakuonline.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.scc.bukusakuonline.R
import com.scc.bukusakuonline.model.DetailPointItems

class AdapterDetailPoint(private  val context: Context, private val Items:List<DetailPointItems>): RecyclerView.Adapter<AdapterDetailPoint.ViewHolder>() {
    class ViewHolder (view: View): RecyclerView.ViewHolder(view) {
        private val code = view.findViewById<TextView>(R.id.code_point)
        private val desc = view.findViewById<TextView>(R.id.description_point)
        private val point = view.findViewById<TextView>(R.id.point)

        fun bindItem(items: DetailPointItems){
            point.text = items.point
            code.text = items.code
            desc.text = items.jenis_pelanggaran
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.point_items,parent,false))
    }

    override fun getItemCount(): Int  = Items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(Items[position])
    }
}