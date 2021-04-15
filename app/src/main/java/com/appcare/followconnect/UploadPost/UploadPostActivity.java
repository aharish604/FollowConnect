package com.appcare.followconnect.UploadPost;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.appcare.followconnect.BuildConfig;
import com.appcare.followconnect.Common.AppPreference;
import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.Common.Utility;
import com.appcare.followconnect.Home.HomeActivity;
import com.appcare.followconnect.Network.APIResponse;
import com.appcare.followconnect.R;
import com.appcare.followconnect.spoolvid.SpoolvidUploadPostActivity;
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

import static com.appcare.followconnect.spoolvid.SpoolvidUploadPostActivity.PICK_Videos;

public class UploadPostActivity extends AppCompatActivity implements View.OnClickListener, APIResponse {

    ImageButton camera = null;
    ImageButton gallery = null;
    TextView tv_post = null;
    EditText et_comment = null;
    ImageButton imgbtn_searchuserprofile = null;
    ImageButton video = null;
    RecyclerView rv_imagepreview = null;
    File photoFile = null;
    ProgressDialog progressDialog = null;
    TextView profilename_tv = null;
    TextView tv_dispusername = null;

    String videopath = "";

    public static final int REQUEST_IMAGE_CAPTURE = 10;
    public static final int PICK_IMAGES = 2;
    public static final int STORAGE_PERMISSION = 100;
    SelectedImageAdapter selectedImageAdapter = null;
    ArrayList<String> selectedImageList = new ArrayList<>();
    ArrayList<String> selectedVideolist = new ArrayList<>();
    CircleImageView profile_image = null;

    RelativeLayout rl_privicy=null;
    Spinner sp_privacy = null;
    FileCompressor mCompressor = null;
    UploadPostpresenter presenter = null;
    String privicy = "";

    String feedid = "";
    String imgurls = "";
    String comingfrom = "";
    String feedtext = "";
    String imagesarray[];

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_post);

        getSupportActionBar().hide();
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));


        Intent myIntent = getIntent();
        if (myIntent != null) {
            feedid = myIntent.getStringExtra("FEEDID");
            imgurls = myIntent.getStringExtra("Imageurls");
            comingfrom = myIntent.getStringExtra("commingfrom");
            feedtext = myIntent.getStringExtra("feedtext");
        }


        imagesarray = null;
        if (imgurls != null) {
            if (!imgurls.equalsIgnoreCase("")) {
                imagesarray = imgurls.split(",");
            }
        }


        mCompressor = new FileCompressor(this);
        profile_image = findViewById(R.id.profile_image);
        profilename_tv = findViewById(R.id.profilename_tv);
        tv_dispusername = findViewById(R.id.tv_dispusername);
        rl_privicy = findViewById(R.id.rl_privicy);
        rl_privicy.setVisibility(View.GONE);

        String prfile_url = AppPreference.getInstance().getString(Constants.ProfilrURL);
        String fullname = AppPreference.getInstance().getString(Constants.FullName);
        String username = AppPreference.getInstance().getString(Constants.UserName);

        Glide.with(UploadPostActivity.this)
                .load(prfile_url)
                .placeholder(R.drawable.ic_baseline_account_circle_24)
                //.centerCrop()
                .into(profile_image);
        profilename_tv.setText(fullname);
        tv_dispusername.setText(username);


        sp_privacy = findViewById(R.id.sp_privicy);
        et_comment = findViewById(R.id.et_comment);
        tv_post = findViewById(R.id.tv_post);
        video = findViewById(R.id.video);
        video.setVisibility(View.VISIBLE);
        imgbtn_searchuserprofile = findViewById(R.id.imgbtn_searchuserprofile);
        camera = findViewById(R.id.camera);
        gallery = findViewById(R.id.gallery);
        rv_imagepreview = findViewById(R.id.rv_imagepreview);
        camera.setOnClickListener(UploadPostActivity.this);
        gallery.setOnClickListener(UploadPostActivity.this);
        imgbtn_searchuserprofile.setOnClickListener(UploadPostActivity.this);
        tv_post.setOnClickListener(UploadPostActivity.this);
        video.setOnClickListener(UploadPostActivity.this);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(UploadPostActivity.this, android.R.layout.simple_spinner_item, Constants.getPrivacy());
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


        presenter = new UploadPostpresenter(UploadPostActivity.this, UploadPostActivity.this);

        if (imagesarray != null) {
            for (String filePath : imagesarray) {
                // checkImage(filePath, "image");

                try {
                    saveImag(filePath, String.valueOf(System.currentTimeMillis()));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                getFromSdcard();
            }
        }, 2000);


        et_comment.setText(feedtext);
    }

    File[] listFile;

    public void getFromSdcard() {
        File file = new File(Environment.getExternalStorageDirectory() + "/Pictures/FollowConnect");

        if (file.isDirectory()) {
            listFile = file.listFiles();

            for (int i = 0; i < listFile.length; i++) {
                checkImage(listFile[i].getAbsolutePath(), "image");

            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.camera:
                requestStoragePermission(true, "");
                break;
            case R.id.gallery:
                requestStoragePermission(false, "");
                break;
            case R.id.imgbtn_searchuserprofile:

                Constants.deleteFileFromFolder();

                openHomeActivity();
                break;

            case R.id.tv_post:
                validate();
                break;
            case R.id.video:
                requestStoragePermission(false, "video");
                break;
        }

    }


    private void validate() {

        String imagesarray[] = new String[selectedImageList.size()];
        String videosarray[] = new String[selectedVideolist.size()];

        if (selectedImageList.size() != 0 || !et_comment.getText().toString().trim().equalsIgnoreCase("") || selectedVideolist.size() != 0) {

            String comment = et_comment.getText().toString().trim();

            for (int i = 0; i < selectedImageList.size(); i++) {
                imagesarray[i] = selectedImageList.get(i);
            }

            for (int i = 0; i < selectedVideolist.size(); i++) {
                videosarray[i] = selectedVideolist.get(i);
            }

            if (comingfrom != null) {

                if (!comingfrom.equalsIgnoreCase("editfeed")) {
                    UploadPostBean bean = new UploadPostBean();
                    bean.setUid(AppPreference.getInstance().getString(Constants.User_ID));
                    bean.setLng("12.1");
                    bean.setLtd("13.1");
                    bean.setFeedtext(comment);
                    bean.setAddress("Banglore");
                    bean.setPrivicy(privicy);
                    bean.setImages(imagesarray);
                    bean.setVideos(videosarray);
                    bean.setIsspoolvid("No");
                    presenter.uploadPost(bean);
                } else {

                    UploadPostBean bean = new UploadPostBean();
                    bean.setUid(AppPreference.getInstance().getString(Constants.User_ID));
                    bean.setLng("12.1");
                    bean.setLtd("13.1");
                    bean.setFeedtext(comment);
                    bean.setAddress("Banglore");
                    bean.setPrivicy(privicy);
                    bean.setImages(imagesarray);
                    bean.setVideos(videosarray);
                    bean.setIsspoolvid("No");
                    bean.setFeed_id(feedid);
                    presenter.uploadeditPost(bean);

                }
            } else {

                UploadPostBean bean = new UploadPostBean();
                bean.setUid(AppPreference.getInstance().getString(Constants.User_ID));
                bean.setLng("12.1");
                bean.setLtd("13.1");
                bean.setFeedtext(comment);
                bean.setAddress("Banglore");
                bean.setPrivicy(privicy);
                bean.setImages(imagesarray);
                bean.setVideos(videosarray);
                bean.setIsspoolvid("No");
                presenter.uploadPost(bean);

            }


        } else {

            Constants.displayLongToast(UploadPostActivity.this, "Select Something");

        }
    }

    public String getImageFilePath(Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = UploadPostActivity.this.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                if (photoFile != null) {

                    try {
                        photoFile = mCompressor.compressToFile(photoFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    checkImage(photoFile.toString(), "image");
                }
            } else if (requestCode == PICK_IMAGES) {
                if (data.getClipData() != null) {

                    ClipData mClipData = data.getClipData();
                    for (int i = 0; i < mClipData.getItemCount(); i++) {
                        ClipData.Item item = mClipData.getItemAt(i);
                        Uri uri = item.getUri();
                        checkImage(Utility.getFilePathFromURI(UploadPostActivity.this, uri), "image");
                    }
                } else if (data.getData() != null) {
                    Uri uri = data.getData();
                    checkImage(Utility.getFilePathFromURI(UploadPostActivity.this, uri), "image");
                }

            } else if (requestCode == PICK_Videos) {

                Uri uri = data.getData();

                videopath = Utility.getFilePathFromURI(UploadPostActivity.this, uri);


                checkImage(Utility.getFilePathFromURI(UploadPostActivity.this, uri), "video");

            }
        }
    }


    public void checkImage(String filePath, String comfrom) {

        if (comfrom.equalsIgnoreCase("image")) {
            video.setVisibility(View.INVISIBLE);
            camera.setVisibility(View.VISIBLE);
            gallery.setVisibility(View.VISIBLE);
            if (selectedImageList.size() < 5) {
                rl_privicy.setVisibility(View.VISIBLE);
                selectedImageList.add(filePath);
                setSelectedImageList();
            } else {
                Constants.displayLongToast(this, "Only Five Images Are Allow to post");
            }

        } else {

            rl_privicy.setVisibility(View.VISIBLE);

            camera.setVisibility(View.GONE);
            gallery.setVisibility(View.GONE);
            video.setVisibility(View.VISIBLE);

            selectedVideolist.add(filePath);

            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
            rv_imagepreview.setLayoutManager(layoutManager);
            selectedImageAdapter = new SelectedImageAdapter(this, selectedVideolist);
            rv_imagepreview.setAdapter(selectedImageAdapter);

        }


    }


    public void setSelectedImageList() {

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        rv_imagepreview.setLayoutManager(layoutManager);
        selectedImageAdapter = new SelectedImageAdapter(this, selectedImageList);
        rv_imagepreview.setAdapter(selectedImageAdapter);
    }


    public void takePicture() {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(UploadPostActivity.this,
                        BuildConfig.APPLICATION_ID + ".provider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

            }
        }
    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String mFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File mFile = File.createTempFile(mFileName, ".jpg", storageDir);
        return mFile;
    }


    private void requestStoragePermission(boolean isCamera, String comfrom) {
        Dexter.withActivity(this).withPermissions(Constants.permissionList())
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            if (isCamera) {
                                takePicture();
                            } else {

                                if (comfrom.equalsIgnoreCase("video")) {
                                    Intent intent1 = new Intent(Intent.ACTION_GET_CONTENT);
                                    intent1.setType("video/*");
                                    intent1.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false);
                                    startActivityForResult(intent1, PICK_Videos);
                                } else {
                                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                                    intent.setType("image/*");
                                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                                    startActivityForResult(intent, PICK_IMAGES);

                                }

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

    private void openHomeActivity() {
        AppPreference.getInstance().put(Constants.loginStatus, true);
        Intent i = new Intent(UploadPostActivity.this, HomeActivity.class);
        i.putExtra("TabNumber","0");
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }

    @Override
    public void onSuccess(Object object) {
        UploadPostResponse signUpResponse = (UploadPostResponse) object;
        Constants.displayLongToast(UploadPostActivity.this, signUpResponse.getMessage());

        Constants.deleteFileFromFolder();
        openHomeActivity();

    }

    @Override
    public void onServerError(String error) {
        dismissProgress();
        Constants.displayLongToast(UploadPostActivity.this, error);
    }


    @Override
    public void showProgress() {
        progressDialog = Constants.showProgressDialog(UploadPostActivity.this, "");
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
        Constants.displayLongToast(UploadPostActivity.this, error.toString());

    }

    public void saveImag(String imageUrl, String filename) throws IOException {

        System.out.println("Image url " + imageUrl.toString());

        File direct = new File(Environment.DIRECTORY_PICTURES
                + "/FollowConnect");

        if (!direct.exists()) {
            direct.mkdirs();
        }

        try {
            DownloadManager dm = (DownloadManager) UploadPostActivity.this.getSystemService(UploadPostActivity.this.DOWNLOAD_SERVICE);
            Uri downloadUri = Uri.parse(imageUrl);
            DownloadManager.Request request = new DownloadManager.Request(downloadUri);
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                    .setAllowedOverRoaming(false)
                    .setTitle(filename)
                    .setMimeType("image/jpeg") // Your file type. You can use this code to download other file types also.
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN)
                    .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, "/FollowConnect" + File.separator + filename + ".jpg");
            dm.enqueue(request);
            Toast.makeText(UploadPostActivity.this, "Image download started.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(UploadPostActivity.this, "Image download failed.", Toast.LENGTH_SHORT).show();
        }


    }

}

