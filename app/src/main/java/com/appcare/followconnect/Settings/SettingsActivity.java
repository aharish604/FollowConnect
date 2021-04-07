package com.appcare.followconnect.Settings;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.Network.APIResponse;
import com.appcare.followconnect.Profile.ProfileActivity;
import com.appcare.followconnect.R;

public class SettingsActivity extends AppCompatActivity implements APIResponse, View.OnClickListener {

    SettingsPresenter presenter=null;
    ProgressDialog progressDialog = null;

    LinearLayout ll_passwordchnage=null;
    LinearLayout ll_helpandsupport=null;
    LinearLayout ll_blockerlist=null;
    LinearLayout ll_deleteaccount=null;
    LinearLayout ll_privacypolicy=null;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getSupportActionBar().hide();
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));

        InitUI();

        InitializeObjects();
    }

    private void InitUI() {

        ll_passwordchnage=findViewById(R.id.ll_passwordchnage);
        ll_helpandsupport=findViewById(R.id.ll_helpandsupport);
        ll_blockerlist=findViewById(R.id.ll_blockerlist);
        ll_deleteaccount=findViewById(R.id.ll_deleteaccount);
        ll_privacypolicy=findViewById(R.id.ll_privacypolicy);

        ll_passwordchnage.setOnClickListener(SettingsActivity.this);
        ll_helpandsupport.setOnClickListener(SettingsActivity.this);
        ll_blockerlist.setOnClickListener(SettingsActivity.this);
        ll_deleteaccount.setOnClickListener(SettingsActivity.this);
        ll_privacypolicy.setOnClickListener(SettingsActivity.this);


    }

    private void InitializeObjects() {
        presenter=new SettingsPresenter(SettingsActivity.this,SettingsActivity.this);

    }

    @Override
    public void onSuccess(Object object) {

    }

    @Override
    public void onServerError(String error) {
        dismissProgress();
        Constants.displayLongToast(SettingsActivity.this, error);
    }


    @Override
    public void showProgress() {
        progressDialog = Constants.showProgressDialog(SettingsActivity.this, "");
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
        Constants.displayLongToast(SettingsActivity.this, error.toString());
    }


    @Override
    public void onClick(View v) {

        switch(v.getId())
        {
            case R.id.ll_passwordchnage:


                startActivity(new Intent(SettingsActivity.this,PasswordChangeActivity.class));

                break;

            case R.id.ll_helpandsupport:

                break;

            case R.id.ll_blockerlist:

                break;


            case R.id.ll_deleteaccount:

                break;

            case R.id.ll_privacypolicy:

                break;

        }

    }
}