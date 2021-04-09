package com.appcare.followconnect.ForgotPassword;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.appcare.followconnect.Chat.ResponseSucessCallback;
import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.Common.Utility;
import com.appcare.followconnect.Login.LoginActivity;
import com.appcare.followconnect.Login.LoginResponse;
import com.appcare.followconnect.Network.APIResponse;
import com.appcare.followconnect.R;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener, APIResponse {

    ImageButton imgbtn_forgotpassback = null;
    EditText et_email = null;
    ImageView img_clearemail = null;
    Button btn_sendlink = null;
    ForgotPasswordPresenter presenter;
    ProgressDialog progressDialog = null;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        getSupportActionBar().hide();
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));

        initUI();

        IntializeObjetcs();


    }

    private void IntializeObjetcs() {
        presenter = new ForgotPasswordPresenter(ForgotPasswordActivity.this, ForgotPasswordActivity.this);
    }

    private void initUI() {
        imgbtn_forgotpassback = findViewById(R.id.imgbtn_forgotpassback);
        et_email = findViewById(R.id.et_email);
        img_clearemail = findViewById(R.id.img_clearemail);
        btn_sendlink = findViewById(R.id.btn_sendlink);
        imgbtn_forgotpassback.setOnClickListener(ForgotPasswordActivity.this);
        btn_sendlink.setOnClickListener(ForgotPasswordActivity.this);
        img_clearemail.setOnClickListener(ForgotPasswordActivity.this);

    }

    @Override
    public void onClick(View v) {
        switch ((v.getId())) {
            case R.id.img_clearemail:
                et_email.setText("");
                break;
            case R.id.btn_sendlink:
                checkValidation();
                break;
            case R.id.imgbtn_forgotpassback:
                Intent i = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
                break;

        }
    }

    private void checkValidation() {
        String email = et_email.getText().toString().trim();
        if (email.isEmpty()) {
            Constants.displayLongToast(ForgotPasswordActivity.this, getString(R.string.alert_entereamil));
        } else if (!Utility.isEmailValid(email)) {
            Constants.displayLongToast(ForgotPasswordActivity.this, getString(R.string.alert_entervalidemail));
        } else {
            ForgotPasswordBean bean = new ForgotPasswordBean();
            bean.setEmail(email);
            presenter.sendForgetpasswordLink(bean,new ResponseSucessCallback(){
                @Override
                public void responseSucess(Object object) {
                    LoginResponse loginResponse = (LoginResponse) object;
                    Constants.displayLongToast(ForgotPasswordActivity.this, loginResponse.getMessage());

                }
            });
        }
    }

    @Override
    public void onSuccess(Object object) {

    }

    @Override
    public void onServerError(String error) {
        Constants.displayLongToast(ForgotPasswordActivity.this, error);
    }

    @Override
    public void showProgress() {
        progressDialog = Constants.showProgressDialog(ForgotPasswordActivity.this, "");
    }

    @Override
    public void dismissProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void networkError(String error) {
        Constants.displayLongToast(ForgotPasswordActivity.this, error);
    }
}