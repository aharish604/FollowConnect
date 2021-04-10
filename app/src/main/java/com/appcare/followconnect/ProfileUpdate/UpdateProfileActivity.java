package com.appcare.followconnect.ProfileUpdate;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.appcare.followconnect.BuildConfig;
import com.appcare.followconnect.Common.AppPreference;
import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.Common.Utility;
import com.appcare.followconnect.LoginWithGoogle.ProfileCallback;
import com.appcare.followconnect.Network.APIResponse;
import com.appcare.followconnect.Profile.ProfileActivity;
import com.appcare.followconnect.Profile.Bean.ProfileResponseBean1;
import com.appcare.followconnect.R;
import com.appcare.followconnect.SignUp.CountrySpinner.Adapterpositioncallback;
import com.appcare.followconnect.SignUp.CountrySpinner.CountryAdapter;
import com.appcare.followconnect.SignUp.CountrySpinner.CountryBean;
import com.appcare.followconnect.SignUp.SignUpActivity;
import com.appcare.followconnect.SignUp.SignupView;
import com.appcare.followconnect.UploadPost.FileCompressor;
import com.appcare.followconnect.UploadPost.SelectedImageAdapter;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class UpdateProfileActivity extends AppCompatActivity implements View.OnClickListener, APIResponse {


    ArrayList<CountryBean> countrylist = new ArrayList<>();
    Spinner sp_gender = null;
    Spinner sp_counry = null;
    ImageButton imgbtn_editprofile = null;
    EditText et_fullname = null;
    EditText et_Emailid = null;
    EditText et_userID = null;
    EditText et_password = null;
    EditText et_cpassword = null;
    EditText et_countrycode = null;
    EditText et_mobilenumber = null;
    ImageView img_counryflag = null;
    ImageView img_calender = null;
    ImageView img_spinnercountry = null;
    EditText edt_dob = null;
    CircleImageView edit_profilepicture = null;
    File photoFile = null;
    String profileImage = "";
    String[] projection = {MediaStore.MediaColumns.DATA};

    public static final int REQUEST_IMAGE_CAPTURE = 10;
    public static final int PICK_IMAGES = 2;
    public static final int STORAGE_PERMISSION = 100;
    SelectedImageAdapter selectedImageAdapter = null;
    ArrayList<String> selectedImageList = new ArrayList<>();

    Spinner sp_privacy = null;
    FileCompressor mCompressor = null;

    Button btn_submitupdateprofile = null;
    String gender = "";
    String country = "";
    String dob = "";
    ProgressDialog progressDialog = null;
    BottomSheetDialog bsdialog = null;
    CountryAdapter countryAdapter = null;
    private DatePickerDialog datePickerDialog;
    ProfileUpdatePresenter presenter = null;
    ArrayAdapter<String> arrayAdapter = null;
    LinearLayout rl_userid = null;
    String email = "";
    String fullname = "";
    ProfileResponseBean1 profileResponseBean1 = null;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        Intent intent = getIntent();
        if (intent != null) {
            if (intent.getSerializableExtra("profilebean") != null) {
                profileResponseBean1 = (ProfileResponseBean1) intent.getSerializableExtra("profilebean");

            }
        }

        mCompressor = new FileCompressor(this);


        getSupportActionBar().hide();
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));


        initUI();
        IntializeObjetcs();

        if (profileResponseBean1 != null) {
            setDatatoUI();
        }


    }


    private void IntializeObjetcs() {

        presenter = new ProfileUpdatePresenter(UpdateProfileActivity.this, UpdateProfileActivity.this);
        presenter.getCountryes(new SignupView() {
            @Override
            public void resultcountryData(ArrayList<CountryBean> list) {
                if (list.size() != 0) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            countrylist = list;

                            for (int pos = 0; pos < countrylist.size(); pos++) {
                                if (countrylist.get(pos).getCountry().equalsIgnoreCase(profileResponseBean1.getCountry())) {
                                    Constants.fetchSvg(UpdateProfileActivity.this, countrylist.get(pos).getFlag_url(), img_counryflag);
                                    et_countrycode.setText("+" + countrylist.get(pos).getCallingcode());
                                    sp_counry.setSelection(pos);

                                }
                            }

                            arrayAdapter = new ArrayAdapter<String>(UpdateProfileActivity.this, android.R.layout.simple_spinner_item, Constants.getCountryNames(countrylist));
                            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            sp_counry.setAdapter(arrayAdapter);

                            if (profileResponseBean1.getCountry() != null) {
                                int spinnerPosition = arrayAdapter.getPosition(profileResponseBean1.getCountry());
                                sp_counry.setSelection(spinnerPosition);
                            }

                            sp_counry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    country = parent.getItemAtPosition(position).toString();

                                    et_countrycode.setText("+" + countrylist.get(position).getCallingcode());
                                    Constants.fetchSvg(UpdateProfileActivity.this, countrylist.get(position).getFlag_url(), img_counryflag);


                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });
                        }
                    });
                }

            }
        });

    }

    private void initUI() {


        et_fullname = findViewById(R.id.et_fullname);
        et_Emailid = findViewById(R.id.et_Emailid);
        et_Emailid.setEnabled(false);

        et_userID = findViewById(R.id.et_userID);
        et_userID.setEnabled(false);

        sp_gender = findViewById(R.id.sp_gender);
        edit_profilepicture = findViewById(R.id.edit_profilepicture);

        sp_counry = findViewById(R.id.sp_counry);

        imgbtn_editprofile = findViewById(R.id.imgbtn_editprofile);

        img_counryflag = findViewById(R.id.img_counryflag);
        et_mobilenumber = findViewById(R.id.et_mobilenumber);
        et_mobilenumber.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
        // et_mobilenumber.setEnabled(false);
        et_countrycode = findViewById(R.id.et_countrycode);
        et_countrycode.setEnabled(false);

        edt_dob = findViewById(R.id.edt_dob);
        //edt_dob.setText("dd-mm-yyyy");
        edt_dob.setEnabled(false);


        img_calender = findViewById(R.id.img_calender);
        img_spinnercountry = findViewById(R.id.img_spinnercountry);
        btn_submitupdateprofile = findViewById(R.id.btn_submitupdateprofile);



        btn_submitupdateprofile.setOnClickListener(this);
        imgbtn_editprofile.setOnClickListener(this);
        img_spinnercountry.setOnClickListener(this);
        img_calender.setOnClickListener(this);
        edit_profilepicture.setOnClickListener(this);


        // Constants.setSpinText(sp_gender,"Male");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(UpdateProfileActivity.this, android.R.layout.simple_spinner_item, Constants.getGender());
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_gender.setAdapter(arrayAdapter);

        if (profileResponseBean1 != null) {
            int spinnerPosition = arrayAdapter.getPosition(profileResponseBean1.getGender());
            sp_gender.setSelection(spinnerPosition);
        }

        sp_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gender = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }


    private void setDatatoUI() {

        et_fullname.setText(profileResponseBean1.getFullname());
        et_Emailid.setText(profileResponseBean1.getEmail());
        et_userID.setText(profileResponseBean1.getUsername());
        et_mobilenumber.setText(profileResponseBean1.getMobile());
        edt_dob.setText(profileResponseBean1.getDob());
        Glide.with(UpdateProfileActivity.this)
                .load(profileResponseBean1.getProfile_pic())
                .placeholder(R.drawable.update_profile)
                //.centerCrop()
                .into(edit_profilepicture);
        //Constants.setSpinText(sp_counry,profileBean.getCountry());


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.img_spinnercountry:
                displayBottomSheetCountrywisedata();
                break;
            case R.id.img_calender:
                showCalender();
                break;
            case R.id.btn_submitupdateprofile:
                checkValidation();
                break;
            case R.id.imgbtn_editprofile:
                Intent i = new Intent(UpdateProfileActivity.this, ProfileActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
                break;
            case R.id.edit_profilepicture:
                //  startActivityForResult(getPickImageChooserIntent(), 200);

                displayBottomSheet();
                break;


        }
    }


    private void displayBottomSheetCountrywisedata() {
        if (bsdialog == null) {
            bsdialog = new BottomSheetDialog(UpdateProfileActivity.this, R.style.BottomSheetDialogTheme);
        }
        View sheetView = UpdateProfileActivity.this.getLayoutInflater().inflate(R.layout.date_by_sort_bottom_sheet, null);
        bsdialog.setContentView(sheetView);
        bsdialog.show();
        RecyclerView recyclerView = bsdialog.findViewById(R.id.rv_country);
        EditText search_countrywise = bsdialog.findViewById(R.id.search_countrywise);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        initializeRecyclerview(recyclerView, bsdialog);
        getdapterPosition();

        search_countrywise.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                Filter(s.toString());
            }
        });

        ImageView clear = bsdialog.findViewById(R.id.img_cancel_filter);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bsdialog.dismiss();
            }
        });

    }

    private void initializeRecyclerview(RecyclerView recyclerView, BottomSheetDialog bsdialog) {
        countryAdapter = new CountryAdapter(countrylist, UpdateProfileActivity.this, bsdialog);
        recyclerView.setAdapter(countryAdapter);

    }


    public void getdapterPosition() {
        countryAdapter.adapterPosition(new Adapterpositioncallback() {
            @Override
            public void getadapterposition(Object object, int pos) {
                CountryBean countryBean = (CountryBean) object;
                Constants.fetchSvg(UpdateProfileActivity.this, countryBean.getFlag_url(), img_counryflag);
                et_countrycode.setText("+" + countryBean.getCallingcode());
                sp_counry.setSelection(pos);

            }
        });

    }

    private void Filter(String text) {
        ArrayList<CountryBean> filteredList = new ArrayList<>();
        for (CountryBean item : countrylist) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        countryAdapter.filterList(filteredList, text);
    }

    private void showCalender() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(UpdateProfileActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        edt_dob.setText(day + "-" + month + "-" + year);
                        dob = day + "-" + month + "-" + year;
                    }
                }, year, month, dayOfMonth);

        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        //datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        datePickerDialog.show();
    }


    private void checkValidation() {
        String name = et_fullname.getText().toString().trim();
        String email = et_Emailid.getText().toString().trim();
        String mobileNo = et_mobilenumber.getText().toString().trim();
        String sp_gender = gender;
        String dob_date = edt_dob.getText().toString();
        String sp_country = country;

        if (name.isEmpty()) {
            Constants.displayLongToast(UpdateProfileActivity.this, getString(R.string.alert_enterfullname));
        } else if (email.isEmpty()) {
            Constants.displayLongToast(UpdateProfileActivity.this, getString(R.string.alert_entereamil));
        } else if (!Utility.isEmailValid(email)) {
            Constants.displayLongToast(UpdateProfileActivity.this, getString(R.string.alert_entervalidemail));
        } else if (et_userID.equals("")) {
            Constants.displayLongToast(UpdateProfileActivity.this, getString(R.string.alert_enteruserid));
        } else if (et_countrycode.getText().toString().trim().isEmpty()) {
            Constants.displayLongToast(UpdateProfileActivity.this, getString(R.string.alert_countrycode));
        } else if (mobileNo.isEmpty()) {
            Constants.displayLongToast(UpdateProfileActivity.this, getString(R.string.alert_entermobileno));
        } else if (mobileNo.length() < 10) {
            Constants.displayLongToast(UpdateProfileActivity.this, getString(R.string.alert_entervalidmobileno));
        } else if (dob_date.equals("")) {
            Constants.displayLongToast(UpdateProfileActivity.this, getString(R.string.alert_selectdob));
        } else if (sp_gender.equals("Gender")) {
            Constants.displayLongToast(UpdateProfileActivity.this, getString(R.string.alert_selectgender));
        } else if (sp_country.equals("")) {
            Constants.displayLongToast(UpdateProfileActivity.this, getString(R.string.alert_selectcountry));
        } else {

            UpdateProfileBean updateProfileBean = new UpdateProfileBean();

            updateProfileBean.setFullname(name);
            updateProfileBean.setUsername(et_userID.getText().toString());
            updateProfileBean.setEmail(email);
            updateProfileBean.setMobile(mobileNo);
            updateProfileBean.setGender(gender);
            updateProfileBean.setDob(dob_date);
            updateProfileBean.setPassword(profileResponseBean1.getPassword());
            updateProfileBean.setCountry(country);
            updateProfileBean.setCountryCode(et_countrycode.getText().toString());
            updateProfileBean.setDeviceid(profileResponseBean1.getDeviceid());
            updateProfileBean.setDevicetype(profileResponseBean1.getDevicetype());
            updateProfileBean.setDevicetoken(profileResponseBean1.getDevicetoken());
            updateProfileBean.setAPIKEY("827ccb0eea8a706c4c34a16891f84e7b");
            updateProfileBean.setUser_id(profileResponseBean1.getUser_id());
            updateProfileBean.setCountryCode(et_countrycode.getText().toString());

            presenter.updateProfiledata(updateProfileBean);

        }
    }

    @Override
    public void onSuccess(Object object) {

        ProfileUpdateBeanResponse bean = (ProfileUpdateBeanResponse) object;

        if (!profileImage.equalsIgnoreCase("")) {
            String userid = AppPreference.getInstance().getString(Constants.User_ID);
            presenter.UpdateProfilePicture(profileImage, userid, new ProfileCallback() {

                @Override
                public void getProfileData(Object object) {

                    UpdateProfilePhotoResponse bean = (UpdateProfilePhotoResponse) object;

                    ArrayList<UpdateProfileResponseBean> list = bean.getData();

                    if (list.size() != 0) {
                        AppPreference.getInstance().put(Constants.User_ID,list.get(0).getUserId());
                        AppPreference.getInstance().put(Constants.ProfilrURL,list.get(0).getProfilePic());
                        AppPreference.getInstance().put(Constants.FullName,list.get(0).getFullname());
                        AppPreference.getInstance().put(Constants.UserName,list.get(0).getUsername());

                        Constants.displayLongToast(UpdateProfileActivity.this,bean.getMessage());

                        Intent i = new Intent(UpdateProfileActivity.this, ProfileActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        finish();
                    }




                }
            });


        }else {
          Constants.displayLongToast(UpdateProfileActivity.this,bean.getMessage());

        Intent i = new Intent(UpdateProfileActivity.this, ProfileActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();

        }


    }

    @Override
    public void onServerError(String error) {
        Constants.displayLongToast(UpdateProfileActivity.this, error);
    }


    @Override
    public void showProgress() {
        progressDialog = Constants.showProgressDialog(UpdateProfileActivity.this, "");
    }

    @Override
    public void dismissProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void networkError(String error) {
        Constants.displayLongToast(UpdateProfileActivity.this, error.toString());
    }


    public void getImageFilePath(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String absolutePathOfImage = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA));
                if (absolutePathOfImage != null) {
                    checkImage(absolutePathOfImage);
                } else {
                    checkImage(String.valueOf(uri));
                }
            }
        }
    }


    private void displayBottomSheet() {
        if (bsdialog == null) {
            bsdialog = new BottomSheetDialog(UpdateProfileActivity.this, R.style.BottomSheetDialogTheme);
        }
        View sheetView = UpdateProfileActivity.this.getLayoutInflater().inflate(R.layout.bottomsheet_captureimage, null);
        bsdialog.setContentView(sheetView);
        bsdialog.show();

        TextView camera = sheetView.findViewById(R.id.tv_opencamera);
        TextView gallery = sheetView.findViewById(R.id.open_gallery);
        TextView cancle = sheetView.findViewById(R.id.tv_cancle_dilog);


        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                takePicture();
            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false);
                startActivityForResult(intent, PICK_IMAGES);
            }
        });


        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bsdialog.dismiss();
            }
        });


    }


    public void takePicture() {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                ex.printStackTrace();
                // Error occurred while creating the File
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(UpdateProfileActivity.this,
                        BuildConfig.APPLICATION_ID + ".provider",
                        photoFile);

                //  mPhotoFile = photoFile;
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

    public void checkImage(String filePath) {

        bsdialog.dismiss();

        profileImage = filePath;


        Glide.with(UpdateProfileActivity.this)
                .load(filePath)
                .placeholder(R.drawable.update_profile)
                .centerCrop()
                .into(edit_profilepicture);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                if (photoFile != null) {
                    try {
                        photoFile = mCompressor.compressToFile(photoFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    checkImage(photoFile.toString());
                }
            } else if (requestCode == PICK_IMAGES) {
                if (data.getClipData() != null) {
                    ClipData mClipData = data.getClipData();
                    for (int i = 0; i < mClipData.getItemCount(); i++) {
                        ClipData.Item item = mClipData.getItemAt(i);
                        Uri uri = item.getUri();
                        checkImage(Utility.getFilePathFromURI(UpdateProfileActivity.this, uri));

                        // getImageFilePath(uri);
                    }
                } else if (data.getData() != null) {
                    Uri uri = data.getData();

                    checkImage(Utility.getFilePathFromURI(UpdateProfileActivity.this, uri));

                    //getImageFilePath(uri);
                }

            }
        }

    }


}