package com.scc.bukusakuonline.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.scc.bukusakuonline.R
import com.scc.bukusakuonline.model.peraturan.PeraturanItems
import com.scc.bukusakuonline.ui.detailperaturan.DetailPeraturanActivity

class AdapterPeraturan(private  val context: Context, private val Items:List<PeraturanItems>): RecyclerView.Adapter<AdapterPeraturan.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val bab = view.findViewById<TextView>(R.id.bab)
        private val descBab = view.findViewById<TextView>(R.id.desc_bab)

        @SuppressLint("SetTextI18n")
        fun bindItem(items: PeraturanItems, position: Int, context: Context){
            d("items",position.toString())
            bab.text = "Bab ${(position +1)}"
            descBab.text = items.bab
            itemView.setOnClickListener {
                val intent = Intent(context, DetailPeraturanActivity::class.java)
                intent.putExtra("id", items.id)
                intent.putExtra("title", items.bab)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
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