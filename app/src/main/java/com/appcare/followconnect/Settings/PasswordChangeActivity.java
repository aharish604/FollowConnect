package com.appcare.followconnect.Settings;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.appcare.followconnect.Chat.ResponseSucessCallback;
import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.Common.Utility;
import com.appcare.followconnect.ForgotPassword.ForgotPasswordActivity;
import com.appcare.followconnect.ForgotPassword.ForgotPasswordBean;
import com.appcare.followconnect.ForgotPassword.ForgotPasswordPresenter;
import com.appcare.followconnect.Home.HomeActivity;
import com.appcare.followconnect.Login.LoginResponse;
import com.appcare.followconnect.Network.APIResponse;
import com.appcare.followconnect.Notifications.NotificationActivity;
import com.appcare.followconnect.R;
import com.appcare.followconnect.Settings.ChnagePassword.ChangePasswordRequestBean;
import com.appcare.followconnect.Settings.ChnagePassword.ChangePasswordResponseBean;
import com.appcare.followconnect.SignUp.SignUpActivity;

public class PasswordChangeActivity extends AppCompatActivity implements APIResponse, View.OnClickListener {

    ImageButton img_backbutton;
    EditText current_password, new_password, c_password;
    Button btn_submit;
    TextView forgetpassword_tv = null;
    ImageButton imgbtn_searchuserprofile = null;
    ProgressDialog progressDialog = null;

    SettingsPresenter presenter = null;

    ImageView eye_visablecurrentpassword = null,
            eye_visablecpassword, eye_offcurrentpassword, eye_visablenewpassword, eye_offnewpassword, eye_offcpassword;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_change);

        getSupportActionBar().hide();
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));

        InitUI();
        Intializeobjects();

    }


    private void InitUI() {
        img_backbutton = findViewById(R.id.imgbtn_searchuserprofile);
        current_password = findViewById(R.id.edt_password);
        new_password = findViewById(R.id.edt_newpassword);
        c_password = findViewById(R.id.edt_cpassword);
        forgetpassword_tv = findViewById(R.id.forgetpassword_tv);
        btn_submit = findViewById(R.id.btn_submit);
        imgbtn_searchuserprofile = findViewById(R.id.imgbtn_searchuserprofile);


        eye_visablecurrentpassword = findViewById(R.id.eye_visable);
        eye_offcurrentpassword = findViewById(R.id.eye_off);

        eye_visablenewpassword = findViewById(R.id.eye_visablenewpassword);
        eye_offnewpassword = findViewById(R.id.eye_offnewpassword);

        eye_visablecpassword = findViewById(R.id.eye_visablecpassword);
        eye_offcpassword = findViewById(R.id.eye_offcpassword);


        btn_submit.setOnClickListener(this);
        imgbtn_searchuserprofile.setOnClickListener(this);

        eye_visablecurrentpassword.setOnClickListener(this);
        eye_offcurrentpassword.setOnClickListener(this);
        eye_visablenewpassword.setOnClickListener(this);
        eye_offnewpassword.setOnClickListener(this);
        eye_visablecpassword.setOnClickListener(this);
        eye_offcpassword.setOnClickListener(this);
        forgetpassword_tv.setOnClickListener(this);
    }


    private void Intializeobjects() {
        presenter = new SettingsPresenter(PasswordChangeActivity.this, PasswordChangeActivity.this);

    }

    @Override
    public void onSuccess(Object object) {

        ChangePasswordResponseBean bean = (ChangePasswordResponseBean) object;
        Constants.displayLongToast(PasswordChangeActivity.this, bean.getMessage());


    }

    @Override
    public void onServerError(String error) {
        dismissProgress();
        Constants.displayLongToast(PasswordChangeActivity.this, error);
    }


    @Override
    public void showProgress() {
        progressDialog = Constants.showProgressDialog(PasswordChangeActivity.this, "");
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
        Constants.displayLongToast(PasswordChangeActivity.this, error.toString());
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.btn_submit:
                validate();
                break;
            case R.id.eye_visable:
                current_password.setTransformationMethod(null);
                eye_offcurrentpassword.setVisibility(View.VISIBLE);
                eye_visablecurrentpassword.setVisibility(View.GONE);
                break;
            case R.id.eye_off:
                current_password.setTransformationMethod(new PasswordTransformationMethod());
                eye_visablecurrentpassword.setVisibility(View.VISIBLE);
                eye_offcurrentpassword.setVisibility(View.GONE);
                break;
            case R.id.eye_visablenewpassword:
                new_password.setTransformationMethod(null);
                eye_offnewpassword.setVisibility(View.VISIBLE);
                eye_visablenewpassword.setVisibility(View.GONE);
                break;
            case R.id.eye_offnewpassword:
                new_password.setTransformationMethod(new PasswordTransformationMethod());
                eye_visablenewpassword.setVisibility(View.VISIBLE);
                eye_offnewpassword.setVisibility(View.GONE);
                break;
            case R.id.eye_visablecpassword:
                c_password.setTransformationMethod(null);
                eye_offcpassword.setVisibility(View.VISIBLE);
                eye_visablecpassword.setVisibility(View.GONE);
                break;
            case R.id.eye_offcpassword:
                c_password.setTransformationMethod(new PasswordTransformationMethod());
                eye_visablecpassword.setVisibility(View.VISIBLE);
                eye_offcpassword.setVisibility(View.GONE);
                break;
            case R.id.imgbtn_searchuserprofile:
                Intent i1 = new Intent(PasswordChangeActivity.this, SettingsActivity.class);
                i1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i1);
                finish();
                break;
            case R.id.forgetpassword_tv:
                showAlertForgetPassword();
                break;


        }

    }

    public void showAlertForgetPassword() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View customLayout = getLayoutInflater().inflate(R.layout.layout_forgetpassword, null);


        AlertDialog mDialog = builder.create();
        Window window = mDialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.setView(customLayout, 110, 0, 110, 0);
        mDialog.setCanceledOnTouchOutside(false);//Clicking on the screen does not disappear
        mDialog.show();
        WindowManager.LayoutParams params = mDialog.getWindow().getAttributes();
        mDialog.getWindow().setAttributes(params);

        mDialog.show();

        TextView tv_cancel = customLayout.findViewById(R.id.tv_cancel);
        TextView tv_logout = customLayout.findViewById(R.id.tv_logout);
        EditText et_email = customLayout.findViewById(R.id.et_email);

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDialog.dismiss();
            }
        });
        tv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Constants.isNetworkAvailable(PasswordChangeActivity.this)) {
                    mDialog.dismiss();

                    validateemail(et_email.getText().toString().trim());

                } else {

                    Constants.displayLongToast(PasswordChangeActivity.this, getResources().getString(R.string.check_network));

                }

            }


        });


    }

    private void validateemail(String email) {

        if (email.isEmpty()) {
            Constants.displayLongToast(PasswordChangeActivity.this, getString(R.string.alert_entereamil));
        } else if (!Utility.isEmailValid(email)) {
            Constants.displayLongToast(PasswordChangeActivity.this, getString(R.string.alert_entervalidemail));
        } else {
            passwordResetLink(email);
        }


    }

    private void passwordResetLink(String email) {
        ForgotPasswordPresenter presenter = new ForgotPasswordPresenter(PasswordChangeActivity.this, PasswordChangeActivity.this);
        ForgotPasswordBean bean = new ForgotPasswordBean();
        bean.setEmail(email);
        presenter.sendForgetpasswordLink(bean, new ResponseSucessCallback() {
            @Override
            public void responseSucess(Object object) {
                LoginResponse loginResponse = (LoginResponse) object;
                Constants.displayLongToast(PasswordChangeActivity.this, loginResponse.getMessage());
            }
        });

    }


    private void validate() {

        String currentpassword = current_password.getText().toString();
        String newpassword = new_password.getText().toString();
        String cpassword = c_password.getText().toString();

        if (currentpassword.isEmpty()) {
            Constants.displayLongToast(PasswordChangeActivity.this, getString(R.string.alert_entercurrentpassword));
        } else if (newpassword.isEmpty()) {
            Constants.displayLongToast(PasswordChangeActivity.this, getString(R.string.alert_enternewpassword));
        } else if (cpassword.isEmpty()) {
            Constants.displayLongToast(PasswordChangeActivity.this, getString(R.string.alert_entercpassword));
        } else if (!newpassword.equals(cpassword)) {
            Constants.displayLongToast(PasswordChangeActivity.this, getString(R.string.alert_passwordmissmatch));
        } else {
            ChangePasswordRequestBean bean = new ChangePasswordRequestBean();
            bean.setCpassword(cpassword);
            bean.setCurrentpassword(currentpassword);
            bean.setEmail(Constants.getEmail(PasswordChangeActivity.this));
            bean.setPassword(newpassword);
            bean.setUser_id(Constants.getUid(PasswordChangeActivity.this));
            presenter.chnagePassword(bean);
        }


    }
}
