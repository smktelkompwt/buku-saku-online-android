package com.scc.bukusakuonline.ui.pengaduan;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.scc.bukusakuonline.R;
import com.scc.bukusakuonline.R2;
import com.scc.bukusakuonline.connection.ApiService;
import com.scc.bukusakuonline.connection.RetroConfig;
import com.scc.bukusakuonline.model.UploadPelanggaran;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class PengaduanFragment extends Fragment {
    @BindView(R.id.spinner_kategori)
    MaterialSpinner spinnerKategori;
    @BindView(R.id.editText_spinner_pengaduan)
    EditText editText;
    @BindView(R2.id.imageButton3)
    ImageButton imageButton;
    @BindView(R.id.upload_photo)
    TextView upload;
    String base64Image;
    PengaduanViewModel mPengaduanViewModel;
    String category;

    public PengaduanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pengaduan, container, false);
        ButterKnife.bind(this, v);
        mPengaduanViewModel = ViewModelProviders.of(this).get(PengaduanViewModel.class);
        mPengaduanViewModel.loadData(getContext());
        mPengaduanViewModel.getListData().observe(this, detailPointItems -> {
            if (detailPointItems != null){
                ArrayList<String> jenisPelanggaran = new ArrayList<>();
                for (int i = 0; i< detailPointItems.size(); i++){
                    jenisPelanggaran.add(detailPointItems.get(i).getJenis_pelanggaran());
                }
                spinnerKategori.setItems(jenisPelanggaran);
                spinnerKategori.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

                    @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                        category = item;
                    }
                });
            }
        });

        return v;
    }

    @OnClick(R.id.imageButton3)
    public void onImageButton3Clicked() {
        if(ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    2000);
        }
        else {
            Intent cameraIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            cameraIntent.setType("image/*");
            if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivityForResult(cameraIntent, 1000);
            }
        }
    }
    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                final InputStream imageStream = getActivity().getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                imageButton.setImageBitmap(selectedImage);
                upload.setVisibility(View.GONE);
                selectedImage.compress(Bitmap.CompressFormat.PNG, 50, baos);
                byte[] imageBytes = baos.toByteArray();
                base64Image ="data:image/png;base64," + Base64.encodeToString(imageBytes, Base64.DEFAULT);
//                base64Image = "data:image/png;base64,"+ imageBase.substring(4);
//                base64Image = imageBase;
                Log.d("base",base64Image);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(getContext(), "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }
    @OnClick(R.id.button)
    void onButtonClicked() {
        Log.d("wait","wait");
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("PREF", Context.MODE_PRIVATE);
        String token ="Bearer "+ sharedPreferences.getString("TOKEN","abc");
        RetroConfig.getRetrofit().create(ApiService.class).uploadPelanggaran(token,category, Double.parseDouble(editText.getText().toString()) ,base64Image).enqueue(new Callback<UploadPelanggaran>() {
            @Override
            public void onResponse(Call<UploadPelanggaran> call, Response<UploadPelanggaran> response) {
                if (response.isSuccessful()){
                    Log.d("response",response.toString());
                    assert response.body() != null;
                    if (response.body().getCode().equals("200")) {
                        Toast.makeText(getContext(), "Success", Toast.LENGTH_LONG).show();

                    }
                }else {
                    Log.d("no","no");
                    Log.d("response",response.toString());
                }
            }

            @Override
            public void onFailure(Call<UploadPelanggaran> call, Throwable t) {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                Log.d("error",t.getMessage());
            }
        });
    }
    @OnClick(R.id.editText_spinner_pengaduan)
    void setEditText(){
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
    }

}