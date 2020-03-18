package com.scc.bukusakuonline.ui.pengaduan;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.snackbar.Snackbar;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.scc.bukusakuonline.R;
import com.scc.bukusakuonline.R2;
import com.scc.bukusakuonline.connection.ApiService;
import com.scc.bukusakuonline.connection.RetroConfig;
import com.scc.bukusakuonline.model.UploadPelanggaran;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dmax.dialog.SpotsDialog;
import id.zelory.compressor.Compressor;
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
    private String base64Image;
    private String category;
    private View v;
    private AlertDialog alertDialog;
    public PengaduanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_pengaduan, container, false);
        ButterKnife.bind(this, v);
        alertDialog = new SpotsDialog.Builder().setContext(getContext()).build();
        base64Image= "";
        getData();


        return v;
    }

    private void getData() {
        PengaduanViewModel mPengaduanViewModel = ViewModelProviders.of(this).get(PengaduanViewModel.class);
        mPengaduanViewModel.loadData(Objects.requireNonNull(getContext()));
        mPengaduanViewModel.getListData().observe(this, detailPointItems -> {
            if (detailPointItems != null){
                ArrayList<String> jenisPelanggaran = new ArrayList<>();
                for (int i = 0; i< detailPointItems.size(); i++){
                    jenisPelanggaran.add(detailPointItems.get(i).getJenis_pelanggaran());
                }
                spinnerKategori.setItems(jenisPelanggaran);
                spinnerKategori.setOnItemSelectedListener((MaterialSpinner.OnItemSelectedListener<String>) (view, position, id, item) -> category = item);
            }
        });
    }

    @OnClick(R.id.imageButton3)
    void onImageButton3Clicked() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(Objects.requireNonNull(getActivity()), new String[] {Manifest.permission.CAMERA}, 10);
            popImageChooser();

        }else {
            popImageChooser();

        }

    }

    @SuppressLint("IntentReset")
    private void popImageChooser(){
        final List<Intent> cameraIntents = new ArrayList<>();
        final Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        final PackageManager packageManager = Objects.requireNonNull(getActivity()).getPackageManager();
        final List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for(ResolveInfo res : listCam) {
            final String packageName = res.activityInfo.packageName;
            final Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(packageName);
            intent.putExtra(MediaStore.MEDIA_IGNORE_FILENAME, ".nomedia");
            @SuppressLint("SdCardPath") Uri uri  = Uri.parse("file:///sdcard/photo.jpg");
            intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, uri);
            cameraIntents.add(intent);
        }

        // Filesystem.
        @SuppressLint("IntentReset") final Intent galleryIntent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryIntent.setType("image/*");

        // Chooser of filesystem options.
        final Intent chooserIntent = Intent.createChooser(galleryIntent, getString(R.string.attach_images_title));

        // Add the camera options.
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents.toArray(new Parcelable[]{}));
        startActivityForResult(chooserIntent, 100);
    }
    //get callback from picker
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        Log.d("code", String.valueOf(requestCode));
        Log.d("code", String.valueOf(resultCode));

        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                Uri uri;
                Log.d("code", String.valueOf(resultCode));
                Log.d("image", String.valueOf(imageReturnedIntent));
                try {
                    if (imageReturnedIntent == null) {   //since we used EXTRA_OUTPUT for camera, so it will be null
                        File file = new File(Environment.getExternalStorageDirectory().getPath(), "photo.jpg");
                        uri = Uri.fromFile(file);

                        Bitmap  compressedImageBitmap = new Compressor(Objects.requireNonNull(getContext())) .setMaxWidth(320)
                                .setMaxHeight(151).setQuality(1).compressToBitmap(file);
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        imageButton.setImageBitmap(compressedImageBitmap);
                        compressedImageBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                        byte[] imageBytes = baos.toByteArray();
                        base64Image ="data:image/png;base64," + Base64.encodeToString(imageBytes, Base64.DEFAULT);

                        Log.d("base64",base64Image);
                        int check = 4 * base64Image.length() / 3;
                        Log.d("size", String.valueOf(check));
                        Log.d("attachimage", "from camera: " + uri);
                    } else {  // from gallery
                        Uri selectedImageUri = imageReturnedIntent.getData();
                        String filePath = FetchPath.getPath(getContext(), selectedImageUri);
                        assert filePath != null;
                        File file = new File(filePath);
                        Bitmap  compressedImageBitmap = new Compressor(Objects.requireNonNull(getContext())) .setMaxWidth(320)
                                .setMaxHeight(151).setQuality(1).compressToBitmap(file);
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        imageButton.setImageBitmap(compressedImageBitmap);
                        compressedImageBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                        byte[] imageBytes = baos.toByteArray();
                        base64Image ="data:image/png;base64," + Base64.encodeToString(imageBytes, Base64.DEFAULT);
                        Log.d("base",base64Image);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }else{
            Toast.makeText(getContext(), "You haven't picked Image", Toast.LENGTH_SHORT).show();
        }
    }

    // post to server
    @OnClick(R.id.button)
    void onButtonClicked() {
        alertDialog.show();
        try {
            Log.d("wait","wait");
            Toast.makeText(getContext(), "Please Wait", Toast.LENGTH_SHORT).show();
            SharedPreferences sharedPreferences = Objects.requireNonNull(getContext()).getSharedPreferences("PREF", Context.MODE_PRIVATE);
            String token ="Bearer "+ sharedPreferences.getString("TOKEN","abc");
            RetroConfig.getRetrofit().create(ApiService.class).uploadPelanggaran(token,category, editText.getText().toString() ,base64Image).enqueue(new Callback<UploadPelanggaran>() {
                @Override
                public void onResponse(@NotNull Call<UploadPelanggaran> call, @NotNull Response<UploadPelanggaran> response) {
                    if (response.isSuccessful()){
                        if (response.body() != null) {
                            Log.d("yes", String.valueOf(response.body()));
                            if (response.body().getCode() != null && response.body().getCode() == 404){
                                Log.d("yes","yes");
                                Log.d("yes",response.body().toString());
                                alertDialog.hide();
                                Toast.makeText(getContext(), "NIS Tidak di Temukan", Toast.LENGTH_SHORT).show();
                            }else {
                                alertDialog.hide();

                                Snackbar.make(v, "Success", Snackbar.LENGTH_LONG).show();
                                Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }else {
                        Log.d("no","no");
                        alertDialog.hide();

                        Snackbar.make(v,"Something went wrong",Snackbar.LENGTH_LONG).show();
                        Toast.makeText(getContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(@NotNull Call<UploadPelanggaran> call, @NotNull Throwable t) {
                    alertDialog.hide();
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                    Toast.makeText(getContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();

                    Log.d("error", Objects.requireNonNull(t.getMessage()));
                }
            });
        }catch (Exception e){
            alertDialog.hide();
            Toast.makeText(getContext(), "Isi Datanya", Toast.LENGTH_LONG).show();

        }


    }
    @OnClick(R.id.editText_spinner_pengaduan)
    void setEditText(){
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
    }

}