package com.appcare.followconnect.Settings;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.Network.APIResponse;
import com.appcare.followconnect.R;

public class PrivicyPolicyActivity extends AppCompatActivity implements APIResponse {

    SettingsPresenter presenter;
    ProgressDialog progressDialog=null;

    TextView tv_privacy_policy=null;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privicy_policy);

        getSupportActionBar().hide();
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));

        tv_privacy_policy=findViewById(R.id.tv_privacy_policy);

        Intializeobjects();
    }


    private void Intializeobjects() {
        presenter = new SettingsPresenter(PrivicyPolicyActivity.this, PrivicyPolicyActivity.this);

       // presenter.getPrivacyPOlicy();

    }

    @Override
    public void onSuccess(Object object) {

        tv_privacy_policy.setText("");

    }


    @Override
    public void onServerError(String error) {
        dismissProgress();
        Constants.displayLongToast(PrivicyPolicyActivity.this, error);
    }


    @Override
    public void showProgress() {
        progressDialog = Constants.showProgressDialog(PrivicyPolicyActivity.this, "");
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
        Constants.displayLongToast(PrivicyPolicyActivity.this, error.toString());
    }

}