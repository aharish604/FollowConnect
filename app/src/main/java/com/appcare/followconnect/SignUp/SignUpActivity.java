package com.appcare.followconnect.SignUp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
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

import com.appcare.followconnect.SignUp.Beans.RandomusernameBeanRequest;
import com.appcare.followconnect.SignUp.Beans.SignUpBeanResponse;
import com.appcare.followconnect.SignUp.Beans.SignupBeanRequest;
import com.appcare.followconnect.SignUp.Beans.SignupResponseBean1;
import com.appcare.followconnect.SignUp.CountrySpinner.Adapterpositioncallback;
import com.appcare.followconnect.Common.AppPreference;
import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.Common.Utility;
import com.appcare.followconnect.Login.LoginActivity;
import com.appcare.followconnect.Network.APIResponse;
import com.appcare.followconnect.R;
import com.appcare.followconnect.SignUp.CountrySpinner.CountryAdapter;
import com.appcare.followconnect.SignUp.CountrySpinner.CountryBean;
import com.appcare.followconnect.Home.HomeActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Calendar;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener, SignupView, APIResponse {


    ArrayList<CountryBean> countrylist = new ArrayList<>();
    SignupBeanRequest signupBeanRequest = new SignupBeanRequest();
    Spinner sp_gender = null;
    Spinner sp_counry = null;
    ImageButton imgbtn_signupback = null;
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
    TextView tv1_dob = null;
    TextView tv_termsandcondition = null;
    TextView tv_signin = null;
    Button btn_signup = null;
    Button btn_username = null;
    String gender = "";
    String country = "";
    String dob = "";
    ProgressDialog progressDialog = null;
    BottomSheetDialog bsdialog = null;
    CountryAdapter countryAdapter = null;
    private DatePickerDialog datePickerDialog;
    SignupPresenter presenter = null;
    ArrayAdapter<String> arrayAdapter = null;
    LinearLayout rl_userid = null;
    String email = "";
    ImageView eyeoff_passwod, eyevisable_password, eyeoff_cpassword, eyevisbale_cpassword;

    String fullname = "";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().hide();
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));

        initUI();
        IntializeObjetcs();

    }

    private void IntializeObjetcs() {
        presenter = new SignupPresenter(SignUpActivity.this, SignUpActivity.this, SignUpActivity.this);
        if (Constants.isNetworkAvailable(SignUpActivity.this)) {
            presenter.getCountryes();
        } else {
            Constants.displayLongToast(SignUpActivity.this, getResources().getString(R.string.check_network));
        }
    }


    private void initUI() {

        sp_gender = findViewById(R.id.sp_gender);
        sp_counry = findViewById(R.id.sp_counry);
        imgbtn_signupback = findViewById(R.id.imgbtn_signupback);
        et_fullname = findViewById(R.id.et_fullname);
        et_Emailid = findViewById(R.id.et_Emailid);

        et_userID = findViewById(R.id.et_userID);
        et_userID.setEnabled(false);

        btn_username = findViewById(R.id.btn_username);
        et_password = findViewById(R.id.et_password);
        et_cpassword = findViewById(R.id.et_cpassword);
        img_counryflag = findViewById(R.id.img_counryflag);

        et_mobilenumber = findViewById(R.id.et_mobilenumber);
        et_mobilenumber.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});

        et_countrycode = findViewById(R.id.et_countrycode);

        eyeoff_passwod = findViewById(R.id.eye_off);
        eyevisable_password = findViewById(R.id.eye_visable);
        eyevisbale_cpassword = findViewById(R.id.eye_visablecpassword);
        eyeoff_cpassword = findViewById(R.id.eye_offcpassword);




        et_countrycode.setEnabled(false);


        tv1_dob = findViewById(R.id.tv1_dob);
        img_calender = findViewById(R.id.img_calender);
        tv_termsandcondition = findViewById(R.id.tv_termsandcondition);
        btn_signup = findViewById(R.id.btn_signup);
        tv_signin = findViewById(R.id.tv_signin);
        img_spinnercountry = findViewById(R.id.img_spinnercountry);
        rl_userid = findViewById(R.id.rl_userid);


        //  sai@linkedphone.com

        btn_signup.setOnClickListener(this);
        tv_signin.setOnClickListener(this);
        imgbtn_signupback.setOnClickListener(this);
        img_spinnercountry.setOnClickListener(this);
        img_calender.setOnClickListener(this);
        tv_termsandcondition.setOnClickListener(this);

        eyeoff_passwod.setOnClickListener(this);
        eyevisable_password.setOnClickListener(this);
        eyevisbale_cpassword.setOnClickListener(this);
        eyeoff_cpassword.setOnClickListener(this);



        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(SignUpActivity.this, android.R.layout.simple_spinner_item, Constants.getGender());
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_gender.setAdapter(arrayAdapter);
        sp_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gender = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

      /*  et_Emailid.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                email = et_Emailid.getText().toString();
                fullname = et_fullname.getText().toString();

                if (fullname.isEmpty()) {
                    Constants.displayLongToast(SignUpActivity.this, getString(R.string.alert_enterfullname));
                } else if (email.isEmpty()) {
                    Constants.displayLongToast(SignUpActivity.this, getString(R.string.alert_entereamil));
                } else if (!Utility.isEmailValid(email)) {
                    Constants.displayLongToast(SignUpActivity.this, getString(R.string.alert_entervalidemail));
                } else {

                    generateRandomUsername();

                    et_userID.setEnabled(false);

                }

            }
        });

        et_fullname.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                email = et_Emailid.getText().toString();
                fullname = et_fullname.getText().toString();

                if (fullname.isEmpty()) {
                    Constants.displayLongToast(SignUpActivity.this, getString(R.string.alert_enterfullname));
                } else if (email.isEmpty()) {
                    //Constants.displayLongToast(SignUpActivity.this, getString(R.string.alert_entereamil));
                } else if (!Utility.isEmailValid(email)) {
                   // Constants.displayLongToast(SignUpActivity.this, getString(R.string.alert_entervalidemail));
                } else {

                    generateRandomUsername();

                    et_userID.setEnabled(false);

                }

            }
        });
*/


        btn_username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = et_Emailid.getText().toString();
                fullname = et_fullname.getText().toString();
                if (fullname.isEmpty()) {
                    Constants.displayLongToast(SignUpActivity.this, getString(R.string.alert_enterfullname));
                } else if (email.isEmpty()) {
                    Constants.displayLongToast(SignUpActivity.this, getString(R.string.alert_entereamil));
                } else if (!Utility.isEmailValid(email)) {
                    Constants.displayLongToast(SignUpActivity.this, getString(R.string.alert_entervalidemail));
                } else {
                    generateRandomUsername();
                }

            }
        });

        //madhusudhanroyal8142@gmail.com

    }

    private void displayBottomSheetCountrywisedata() {
        if (bsdialog == null) {
            bsdialog = new BottomSheetDialog(SignUpActivity.this, R.style.BottomSheetDialogTheme);
        }
        View sheetView = SignUpActivity.this.getLayoutInflater().inflate(R.layout.date_by_sort_bottom_sheet, null);
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
        countryAdapter = new CountryAdapter(countrylist, SignUpActivity.this, bsdialog);
        recyclerView.setAdapter(countryAdapter);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_signup:
                checkValidation();
                break;
            case R.id.tv_signin:
                Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
                break;
            case R.id.imgbtn_signupback:
                Intent i1 = new Intent(SignUpActivity.this, LoginActivity.class);
                i1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i1);
                finish();
                break;
            case R.id.img_spinnercountry:
                displayBottomSheetCountrywisedata();
                break;
            case R.id.img_calender:
                showCalender();
                break;
            case R.id.tv_termsandcondition:
                //showCalender();

                break;

            case R.id.eye_visable:
                et_password.setTransformationMethod(null);
                eyeoff_passwod.setVisibility(View.VISIBLE);
                eyevisable_password.setVisibility(View.GONE);
                break;
            case R.id.eye_off:
                et_password.setTransformationMethod(new PasswordTransformationMethod());
                eyevisable_password.setVisibility(View.VISIBLE);
                eyeoff_passwod.setVisibility(View.GONE);
                break;
            case R.id.eye_visablecpassword:
                et_cpassword.setTransformationMethod(null);
                eyeoff_cpassword.setVisibility(View.VISIBLE);
                eyevisbale_cpassword.setVisibility(View.GONE);
                break;
            case R.id.eye_offcpassword:
                et_cpassword.setTransformationMethod(new PasswordTransformationMethod());
                eyevisbale_cpassword.setVisibility(View.VISIBLE);
                eyeoff_cpassword.setVisibility(View.GONE);
                break;


        }
    }

    private void showCalender() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(SignUpActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        tv1_dob.setText(day + "-" + month + "-" + year);
                        dob = day + "-" + month + "-" + year;
                    }
                }, year, month, dayOfMonth);

        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        //datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        datePickerDialog.show();
    }


    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }


    @Override
    public void resultcountryData(ArrayList<CountryBean> list) {

        if (list.size() != 0) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    countrylist = list;

                    arrayAdapter = new ArrayAdapter<String>(SignUpActivity.this, android.R.layout.simple_spinner_item, Constants.getCountryNames(countrylist));
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sp_counry.setAdapter(arrayAdapter);

                    sp_counry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            country = parent.getItemAtPosition(position).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            });
        }


    }

    public void getdapterPosition() {
        countryAdapter.adapterPosition(new Adapterpositioncallback() {
            @Override
            public void getadapterposition(Object object, int pos) {
                CountryBean countryBean = (CountryBean) object;
                Constants.fetchSvg(SignUpActivity.this, countryBean.getFlag_url(), img_counryflag);

                et_countrycode.setText("+" + countryBean.getCallingcode());

                sp_counry.setSelection(pos);

            }
        });

    }

    private void generateRandomUsername() {

        RandomusernameBeanRequest randomuserName = new RandomusernameBeanRequest();
        randomuserName.setFullname(et_fullname.getText().toString().trim());
        randomuserName.setEmail(et_Emailid.getText().toString().trim());


        presenter.getrandomusername(randomuserName, new RandonusernameCallbacks() {
            @Override
            public void getRandomUsername(String username) {
                et_userID.setText("" + username);

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


    private void checkValidation() {
        String name = et_fullname.getText().toString().trim();
        String email = et_Emailid.getText().toString().trim();
        String mobileNo = et_mobilenumber.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        String c_password = et_cpassword.getText().toString().trim();
        String sp_gender = gender;
        String dob_date = dob;
        String sp_country = country;
        String User_id = "";

        if (name.isEmpty()) {
            Constants.displayLongToast(SignUpActivity.this, getString(R.string.alert_enterfullname));
        } else if (email.isEmpty()) {
            Constants.displayLongToast(SignUpActivity.this, getString(R.string.alert_entereamil));
        } else if (!Utility.isEmailValid(email)) {
            Constants.displayLongToast(SignUpActivity.this, getString(R.string.alert_entervalidemail));
        } else if (et_userID.getText().toString().equals("")) {
            Constants.displayLongToast(SignUpActivity.this, getString(R.string.alert_enteruserid));
        } else if (password.equalsIgnoreCase("")) {
            Constants.displayLongToast(SignUpActivity.this, getString(R.string.alert_enterpassword));
        } else if (c_password.equalsIgnoreCase("")) {
            Constants.displayLongToast(SignUpActivity.this, getString(R.string.alert_enterconfirmpassword));
        } else if (!password.equals(c_password)) {
            Constants.displayLongToast(SignUpActivity.this, getString(R.string.alert_passwordmissmatch));
        } else if (et_countrycode.getText().toString().trim().isEmpty()) {
            Constants.displayLongToast(SignUpActivity.this, getString(R.string.alert_countrycode));
        } else if (mobileNo.isEmpty()) {
            Constants.displayLongToast(SignUpActivity.this, getString(R.string.alert_entermobileno));
        } else if (mobileNo.length() < 10) {
            Constants.displayLongToast(SignUpActivity.this, getString(R.string.alert_entervalidmobileno));
        } else if (dob_date.equals("")) {
            Constants.displayLongToast(SignUpActivity.this, getString(R.string.alert_selectdob));
        } else if (sp_gender.equals("Gender")) {
            Constants.displayLongToast(SignUpActivity.this, getString(R.string.alert_selectgender));
        } else if (sp_country.equals("")) {
            Constants.displayLongToast(SignUpActivity.this, getString(R.string.alert_selectcountry));
        } else {

            signupBeanRequest.setFullname(name);
            signupBeanRequest.setEmail(email);
            signupBeanRequest.setMobile(mobileNo);
            signupBeanRequest.setPassword(c_password);
            signupBeanRequest.setGender(sp_gender);

            signupBeanRequest.setCountry(country);
            signupBeanRequest.setUsername(User_id);
            signupBeanRequest.setDob(dob_date);
            signupBeanRequest.setAddress("");
            signupBeanRequest.setLat("");

            signupBeanRequest.setLng("");
            signupBeanRequest.setCountryCode(et_countrycode.getText().toString());
            signupBeanRequest.setDeviceid("");
            signupBeanRequest.setDevicetoken("");
            signupBeanRequest.setDevicetype("Android");
            signupBeanRequest.setIp("192.168.0.1");
            signupBeanRequest.setUsername(et_userID.getText().toString().trim());


            presenter.postSignUp(signupBeanRequest);
        }
    }


    @Override
    public void onSuccess(Object object) {
        SignUpBeanResponse signUpBeanResponse = (SignUpBeanResponse) object;

        SignupResponseBean1 signupBean = signUpBeanResponse.getData();

        AppPreference.getInstance().put(Constants.Email, signupBean.getEmail());
        AppPreference.getInstance().put(Constants.UserName, signupBean.getUsername());
        AppPreference.getInstance().put(Constants.FullName, signupBean.getFullname());
        AppPreference.getInstance().put(Constants.User_ID, signupBean.getUserId());
        AppPreference.getInstance().put(Constants.password, signupBean.getPassword());

        Constants.displayLongToast(SignUpActivity.this, signUpBeanResponse.getMessage());

        openHomeActivity();
    }

    @Override
    public void onServerError(String error) {
        Constants.displayLongToast(SignUpActivity.this, error);
    }


    @Override
    public void showProgress() {
        progressDialog = Constants.showProgressDialog(SignUpActivity.this, "");
    }

    @Override
    public void dismissProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void networkError(String error) {
        Constants.displayLongToast(SignUpActivity.this, error.toString());
    }

    private void openHomeActivity() {
        AppPreference.getInstance().put(Constants.loginStatus, true);
        Intent i = new Intent(SignUpActivity.this, HomeActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }

}