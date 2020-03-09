package com.scc.bukusakuonline.ui.pengaduan;


import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.scc.bukusakuonline.R;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class PengaduanFragment extends Fragment implements AdapterView.OnItemSelectedListener {


    @BindView(R.id.spinner_kategori)
    Spinner spinnerKategori;
    @BindView(R.id.spinner_pasal)
    Spinner spinnerPasal;
    @BindView(R.id.editText_spinner_pengaduan)
    EditText editText;

    public PengaduanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pengaduan, container, false);
        ButterKnife.bind(this, v);

        ArrayAdapter<CharSequence> adapter_pasal = ArrayAdapter.createFromResource(getContext(),
                R.array.spinner_pasal, android.R.layout.simple_spinner_item);
        adapter_pasal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinnerPasal.setAdapter(adapter_pasal);
        spinnerPasal.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.spinner_kategori, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinnerKategori.setAdapter(adapter);
        spinnerKategori.setOnItemSelectedListener(this);


        return v;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @OnClick(R.id.imageButton3)
    public void onImageButton3Clicked() {
        Toast.makeText(getContext(),"Upload Bukti",Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.button)
    public void onButtonClicked() {
        Toast.makeText(getContext(),"Laporkan",Toast.LENGTH_SHORT).show();
    }
    @OnClick(R.id.editText_spinner_pengaduan)
    public void setEditText(){
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
    }
}