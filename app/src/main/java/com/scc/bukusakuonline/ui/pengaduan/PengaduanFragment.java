package com.scc.bukusakuonline.ui.pengaduan;


import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.snackbar.Snackbar;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.scc.bukusakuonline.R;
import com.scc.bukusakuonline.R2;
import com.scc.bukusakuonline.connection.ApiService;
import com.scc.bukusakuonline.connection.RetroConfig;
import com.scc.bukusakuonline.model.UploadPelanggaran;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
    View v;
    private List<File> cameraImageFiles;
    private ArrayList permissionsToRequest;
    private ArrayList permissionsRejected = new ArrayList();
    private ArrayList permissions = new ArrayList();
    public PengaduanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_pengaduan, container, false);
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.CAMERA}, 10);

        }
        popImageChooser();

    }

    private void popImageChooser(){
        final List<Intent> cameraIntents = new ArrayList<Intent>();
        final Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        final PackageManager packageManager = getActivity().getPackageManager();
        final List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for(ResolveInfo res : listCam) {
            final String packageName = res.activityInfo.packageName;
            final Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(packageName);
            intent.putExtra(MediaStore.MEDIA_IGNORE_FILENAME, ".nomedia");

            cameraIntents.add(intent);
        }

        // Filesystem.
        final Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

        // Chooser of filesystem options.
        final Intent chooserIntent = Intent.createChooser(galleryIntent, getString(R.string.attach_images_title));

        // Add the camera options.
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents.toArray(new Parcelable[]{}));
        startActivityForResult(chooserIntent, 100);
    }

    private String createCameraImageFileName() {
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        return date+".jpg";
    }

    void galery(){
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
    //get callback from picker
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        Log.d("code", String.valueOf(requestCode));
        Log.d("code", String.valueOf(resultCode));

        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                Uri uri = null;
                Log.d("code", String.valueOf(resultCode));
                Log.d("image", String.valueOf(imageReturnedIntent));

                if (imageReturnedIntent == null) {   //since we used EXTRA_OUTPUT for camera, so it will be null
                    Log.d("image", String.valueOf(imageReturnedIntent));
                    for (int i = 0; i < cameraImageFiles.size(); i++) {
                        if (cameraImageFiles.get(i).exists()) {
                            uri = Uri.fromFile(cameraImageFiles.get(i));
                            break;
                        }
                    }
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();

                    final InputStream imageStream;
                    try {
                        int dataSize=0;
                        File f;

                        String scheme = uri.getScheme();
                        System.out.println("Scheme type " + scheme);
                        if(scheme.equals(ContentResolver.SCHEME_CONTENT))
                        {
                            try {
                                InputStream fileInputStream=getContext().getApplicationContext().getContentResolver().openInputStream(uri);
                                dataSize = fileInputStream.available();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            System.out.println("File size in bytes"+dataSize);

                        }
                        else if(scheme.equals(ContentResolver.SCHEME_FILE))
                        {
                            String path = uri.getPath();
                            try {
                                f = new File(path);
                                Log.d("f",f.toString());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        int mb = dataSize / 1000000;
                        if (mb < 3){
                            imageStream = getActivity().getContentResolver().openInputStream(uri);
                            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                            imageButton.setImageBitmap(selectedImage);
                            byte[] imageBytes = baos.toByteArray();
                            base64Image ="data:image/png;base64," + Base64.encodeToString(imageBytes, Base64.NO_WRAP);
                        }else {
                            Toast.makeText(getContext(), "Too Large", Toast.LENGTH_SHORT).show();
                        }
                     
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    Log.d("attachimage", "from camera: " + uri);
                } else {  // from gallery
                    int dataSize = 0;
                    try {
                        File f;
                        uri = imageReturnedIntent.getData();
                        String scheme = uri.getScheme();
                        System.out.println("Scheme type " + scheme);
                        if (scheme.equals(ContentResolver.SCHEME_CONTENT)) {
                            try {
                                InputStream fileInputStream = getContext().getApplicationContext().getContentResolver().openInputStream(uri);
                                dataSize = fileInputStream.available();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            System.out.println("File size in bytes" + dataSize);

                        } else if (scheme.equals(ContentResolver.SCHEME_FILE)) {
                            String path = uri.getPath();
                            try {
                                f = new File(path);
                                Log.d("f", f.toString());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        int mb = dataSize / 1000000;
                        if (mb < 3) {
                            final Uri imageUri = imageReturnedIntent.getData();
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();

                            final InputStream imageStream = getActivity().getContentResolver().openInputStream(imageUri);
                            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                            imageButton.setImageBitmap(selectedImage);
                            upload.setVisibility(View.GONE);
                            selectedImage.compress(Bitmap.CompressFormat.PNG, 100, baos);
                            byte[] imageBytes = baos.toByteArray();
                            base64Image = "data:image/png;base64," + Base64.encodeToString(imageBytes, Base64.NO_WRAP);
                            Log.d("base", base64Image);
                        } else {
                            Toast.makeText(getContext(), "Too Large", Toast.LENGTH_LONG).show();

                        }


                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }else{
            Toast.makeText(getContext(), "You haven't picked Image", Toast.LENGTH_SHORT).show();

        }
    }
    // post to server
    @OnClick(R.id.button)
    void onButtonClicked() {
        if (editText.getText().toString().equals("") && base64Image.length() ==0){
            Toast.makeText(getContext(), "Isi Datanya", Toast.LENGTH_SHORT).show();

        }else {
            Log.d("wait","wait");
            Toast.makeText(getContext(), "Please Wait", Toast.LENGTH_SHORT).show();
            SharedPreferences sharedPreferences = getContext().getSharedPreferences("PREF", Context.MODE_PRIVATE);
            String token ="Bearer "+ sharedPreferences.getString("TOKEN","abc");
            RetroConfig.getRetrofit().create(ApiService.class).uploadPelanggaran(token,category, Double.parseDouble(editText.getText().toString()) ,base64Image).enqueue(new Callback<UploadPelanggaran>() {
                @Override
                public void onResponse(Call<UploadPelanggaran> call, Response<UploadPelanggaran> response) {
                    if (response.isSuccessful()){
                        if (response.body() != null) {
                            if (response.body().getCode() == 404){
                                Log.d("yes","yes");
                                Log.d("yes",response.body().toString());
                                Toast.makeText(getContext(), "NIS Tidak di Temukan", Toast.LENGTH_SHORT).show();
                            }else {
                                Snackbar.make(v, "Success", Snackbar.LENGTH_LONG).show();
                                Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }else {
                        Log.d("no","no");
                        Snackbar.make(v,"Something went wrong",Snackbar.LENGTH_LONG).show();
                        Toast.makeText(getContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<UploadPelanggaran> call, Throwable t) {
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                    Toast.makeText(getContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();

                    Log.d("error",t.getMessage());
                }
            });
        }

    }
    @OnClick(R.id.editText_spinner_pengaduan)
    void setEditText(){
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
    }

}