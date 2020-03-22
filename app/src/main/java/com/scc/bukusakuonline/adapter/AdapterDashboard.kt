package com.scc.bukusakuonline.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.scc.bukusakuonline.R
import com.scc.bukusakuonline.model.dashboard.DashboardViolation

class AdapterDashboard (private  val context: Context, private val Items:List<DashboardViolation>): RecyclerView.Adapter<AdapterDashboard.ViewHolder>(){
    class ViewHolder (view: View): RecyclerView.ViewHolder(view) {
        private val title = view.findViewById<TextView>(R.id.title_dashboard)
        private val desc = view.findViewById<TextView>(R.id.desc_dashboard)
        fun bindItem(items: DashboardViolation, position: Int){
            // ini set datanya
            title.text = items.title
            desc.text = items.count.toString()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterDashboard.ViewHolder {
        // ini return2 gitu layouting item , keknya blm di buat hehehe ,, ini contoh aja biar gak error
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_dashboard,parent,false))

    }

    override fun getItemCount(): Int = Items.size

    override fun onBindViewHolder(holder: AdapterDashboard.ViewHolder, position: Int) {
        holder.bindItem(Items[position], position)
    }

}