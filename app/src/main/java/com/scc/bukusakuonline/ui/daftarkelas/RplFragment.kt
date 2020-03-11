package com.scc.bukusakuonline.ui.daftarkelas

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.scc.bukusakuonline.R
import com.scc.bukusakuonline.adapter.AdapterSiswa
import com.scc.bukusakuonline.model.siswa.SiswaKelasItem
import kotlinx.android.synthetic.main.fragment_rpl.*

/**
 * A simple [Fragment] subclass.
 */
class RplFragment : Fragment(), OnItemSelectedListener {
   private lateinit var kelasViewModel: KelasViewModel
    private lateinit var siswaAdapter: AdapterSiswa
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? { // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_rpl, container, false)
        val spinner = v.findViewById<Spinner>(R.id.spinner)
        val adapter = ArrayAdapter.createFromResource(context!!,
                R.array.RPL, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this
        Log.d("cek",spinner.selectedItem.toString())
        kelasViewModel = ViewModelProviders.of(this).get(KelasViewModel::class.java)
        kelasViewModel.loadData(context!!,spinner.selectedItem.toString())
        kelasViewModel.listData.observe(this, Observer { siswa ->
            getinitUp(siswa)
        })

        return v
    }

    private fun getinitUp(list: List<SiswaKelasItem>) {
        rv_rpl.apply { layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,true)
            siswaAdapter = AdapterSiswa(context,list)
            rv_rpl.adapter = siswaAdapter
        }

    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        parent.getItemAtPosition(position)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
}