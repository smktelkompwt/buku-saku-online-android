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
import com.scc.bukusakuonline.ui.detailpasal.DetailPasalActivity

class AdapterBab (private  val context: Context, private val Items:List<PasalItems>, private val id_bab : String): RecyclerView.Adapter<AdapterBab.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val bab = view.findViewById<TextView>(R.id.bab)
        private val descBab = view.findViewById<TextView>(R.id.desc_bab)

        fun bindItem(items: PasalItems, position: Int, context: Context, id: String){
            d("items",position.toString())
            bab.text = "Pasal ${(position +1)}"
            descBab.text = items.title
            itemView.setOnClickListener {
                val intent = Intent(context, DetailPasalActivity::class.java)
                intent.putExtra("id_pasal", items.id)
                intent.putExtra("title", items.title)
                intent.putExtra("id",id)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_peraturan,parent,false))
    }

    override fun getItemCount(): Int = Items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(Items[position],position, context, id_bab)
    }
}