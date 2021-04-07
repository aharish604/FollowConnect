package com.appcare.followconnect.spoolvid;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.appcare.followconnect.BuildConfig;
import com.appcare.followconnect.Common.AppPreference;
import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.Common.Utility;
import com.appcare.followconnect.Network.APIResponse;
import com.appcare.followconnect.R;
import com.appcare.followconnect.UploadPost.FileCompressor;
import com.appcare.followconnect.UploadPost.SelectedImageAdapter;
import com.appcare.followconnect.UploadPost.UploadPostActivity;
import com.appcare.followconnect.UploadPost.UploadPostBean;
import com.appcare.followconnect.UploadPost.UploadPostResponse;
import com.appcare.followconnect.UploadPost.UploadPostpresenter;
import com.bumptech.glide.Glide;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SpoolvidUploadPostActivity extends AppCompatActivity implements APIResponse, View.OnClickListener {


    ProgressDialog progressDialog = null;

    ImageButton camera = null;
    ImageButton gallery = null;
    TextView tv_post = null;
    EditText et_comment = null;
    ImageButton imgbtn_searchuserprofile = null;
    ImageButton video = null;
    RecyclerView rv_imagepreview = null;
    File photoFile = null;
    TextView profilename_tv = null;
    TextView tv_dispusername = null;
    String videopath="";

    public static final int REQUEST_Video_CAPTURE = 10;
    public static final int PICK_Videos = 200;
    public static final int STORAGE_PERMISSION = 100;
    SelectedImageAdapter selectedImageAdapter = null;
    ArrayList<String> selectedImageList = new ArrayList<>();
    CircleImageView profile_image = null;

    Spinner sp_privacy = null;
    FileCompressor mCompressor = null;
    spoolvidPresenter presenter = null;
    String privicy = "";
    ImageView img_thumblain=null;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spoolvid_upload_post);

        getSupportActionBar().hide();
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));

        AppPreference.getInstance(SpoolvidUploadPostActivity.this);


        mCompressor = new FileCompressor(this);

        profile_image = findViewById(R.id.profile_image);
        profilename_tv = findViewById(R.id.profilename_tv);
        tv_dispusername = findViewById(R.id.tv_dispusername);
        video = findViewById(R.id.video);
        gallery = findViewById(R.id.gallery);
        tv_post = findViewById(R.id.tv_post);
        img_thumblain = findViewById(R.id.img_thumblain);
        img_thumblain.setVisibility(View.GONE);
        imgbtn_searchuserprofile = findViewById(R.id.imgbtn_searchuserprofile);
        sp_privacy = findViewById(R.id.sp_privicy);

        String prfile_url = AppPreference.getInstance().getString(Constants.ProfilrURL);
        String fullname = AppPreference.getInstance().getString(Constants.FullName);
        String username = AppPreference.getInstance().getString(Constants.UserName);

        Glide.with(SpoolvidUploadPostActivity.this)
                .load(prfile_url)
                .placeholder(R.drawable.update_profile)
                .into(profile_image);

        profilename_tv.setText(fullname);
        tv_dispusername.setText(username);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(SpoolvidUploadPostActivity.this, android.R.layout.simple_spinner_item, Constants.getPrivacy());
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_privacy.setAdapter(arrayAdapter);
        sp_privacy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                privicy = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        imgbtn_searchuserprofile.setOnClickListener(this);
        video.setOnClickListener(this);
        gallery.setOnClickListener(this);
        tv_post.setOnClickListener(this);


    }

    @Override
    public void onSuccess(Object object) {
        UploadPostResponse signUpResponse = (UploadPostResponse) object;
        Constants.displayLongToast(SpoolvidUploadPostActivity.this, signUpResponse.getMessage());

    }

    @Override
    public void onServerError(String error) {
        dismissProgress();

        Constants.displayLongToast(SpoolvidUploadPostActivity.this, error);

    }


    @Override
    public void showProgress() {
        progressDialog = Constants.showProgressDialog(SpoolvidUploadPostActivity.this, "");
    }

    @Override
    public void dismissProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void networkError(String error) {
        dismissProgress();

        Constants.displayLongToast(SpoolvidUploadPostActivity.this, error.toString());

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.imgbtn_searchuserprofile:
                finish();
                break;
            case R.id.video:
                requestStoragePermission(true);
                break;
            case R.id.gallery:
                requestStoragePermission(false);
                break;
            case R.id.tv_post:

                validate();
                break;


        }

    }

    private void validate() {

        //long dur=Constants.checkVideoDuration(SpoolvidUploadPostActivity.this, Uri.parse(videopath));

        if(!videopath.isEmpty())
        {
            UploadPostBean bean = new UploadPostBean();
            bean.setUid(Constants.getUid(SpoolvidUploadPostActivity.this));
            bean.setLng("12.1");
            bean.setLtd("13.1");
            bean.setFeedtext("hello");
            bean.setAddress("Banglore");
            bean.setPrivicy(privicy);
            bean.setVideopath(videopath);
            bean.setIsspoolvid("Yes");

            presenter=new spoolvidPresenter(SpoolvidUploadPostActivity.this,SpoolvidUploadPostActivity.this);
            presenter.postPoolvid(bean);

        }


    }

    private void requestStoragePermission(boolean isCamera) {
        Dexter.withActivity(this).withPermissions(Constants.permissionList())
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted

                        if (report.areAllPermissionsGranted()) {
                            if (isCamera) {
                                takeVideo();
                            } else {

                                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                                intent.setType("video/*");
                                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false);
                                startActivityForResult(intent, PICK_Videos);

                            }
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).withErrorListener(error -> Toast.makeText(getApplicationContext(), "Error occurred! ", Toast.LENGTH_SHORT).show())
                .onSameThread()
                .check();
    }

    public void takeVideo() {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                ex.printStackTrace();
                // Error occurred while creating the File
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(SpoolvidUploadPostActivity.this,
                        BuildConfig.APPLICATION_ID + ".provider",
                        photoFile);

                //  mPhotoFile = photoFile;
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_Video_CAPTURE);


            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String mFileName = "MP4_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File mFile = File.createTempFile(mFileName, ".mp4", storageDir);
        return mFile;
    }


    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Need Permissions");
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.");
        builder.setPositiveButton("GOTO SETTINGS", (dialog, which) -> {
            dialog.cancel();
            openSettings();
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show();

    }


    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_Video_CAPTURE) {
                if (photoFile != null) {

                    img_thumblain.setVisibility(View.VISIBLE);
                    videopath=photoFile.toString();

                    Glide.with( SpoolvidUploadPostActivity.this )
                            .load( Uri.fromFile( new File( videopath ) ) )
                            .into( img_thumblain );

                }
            } else if (requestCode == PICK_Videos) {
                if (data.getData() != null) {

                    img_thumblain.setVisibility(View.VISIBLE);
                    Uri uri = data.getData();
                 videopath= Utility.getFilePathFromURI(SpoolvidUploadPostActivity.this, uri);

                    Glide.with( SpoolvidUploadPostActivity.this )
                            .load( Uri.fromFile( new File( videopath ) ) )
                            .into( img_thumblain );


                }
                }
            }
        }
    }
