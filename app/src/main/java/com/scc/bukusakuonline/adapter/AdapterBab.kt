package com.scc.bukusakuonline.adapter

import android.content.Context
import android.content.Intent
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.scc.bukusakuonline.R
import com.scc.bukusakuonline.model.peraturan.PasalItems
import com.scc.bukusakuonline.model.peraturan.PeraturanItems

class AdapterBab (private  val context: Context, private val Items:List<PasalItems>): RecyclerView.Adapter<AdapterBab.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val bab = view.findViewById<TextView>(R.id.bab)
        private val descBab = view.findViewById<TextView>(R.id.desc_bab)

        fun bindItem(items: PasalItems, position: Int, context: Context){
            d("items",position.toString())
            bab.text = "Bab ${(position +1)}"
            descBab.text = items.title
            itemView.setOnClickListener {
//                val intent = Intent(context, DetailPeraturanActivity::class.java)
//                intent.putExtra("id", items.id)
//                intent.putExtra("title", items.bab)
//
//                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_peraturan,parent,false))
    }

    override fun getItemCount(): Int = Items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(Items[position],position, context)
    }
}