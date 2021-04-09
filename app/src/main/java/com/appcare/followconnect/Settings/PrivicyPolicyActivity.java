package com.appcare.followconnect.Settings;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.appcare.followconnect.Chat.ResponseSucessCallback;
import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.Network.APIResponse;
import com.appcare.followconnect.R;
import com.appcare.followconnect.Settings.ChnagePassword.HelpandSupportResponseBean;

import java.util.ArrayList;

public class PrivicyPolicyActivity extends AppCompatActivity implements APIResponse {

    SettingsPresenter presenter;
    ProgressDialog progressDialog=null;

    String commingfrom=null;
    TextView tv_userid=null;
    TextView tv_privacy_policy=null;
    ImageButton imgbtn_searchuserprofile=null;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privicy_policy);

        getSupportActionBar().hide();
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));


        Intent intent = getIntent();
        if (intent != null) {
            commingfrom = intent.getStringExtra("commingfrom");
        }



        tv_privacy_policy=findViewById(R.id.tv_privacy_policy);
        tv_userid=findViewById(R.id.tv_userid);
        imgbtn_searchuserprofile=findViewById(R.id.imgbtn_searchuserprofile);


        if (commingfrom.equalsIgnoreCase("PrivacyPolicy")) {
            tv_userid.setText("PrivacyPolicy");
        } else {
            tv_userid.setText("HelpandSupport");
        }


        imgbtn_searchuserprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intializeobjects();
    }


    private void Intializeobjects() {
        presenter = new SettingsPresenter(PrivicyPolicyActivity.this, PrivicyPolicyActivity.this);


        if (commingfrom.equalsIgnoreCase("PrivacyPolicy")) {

            presenter.getPrivacyPOlicy(new ResponseSucessCallback(){


                @Override
                public void responseSucess(Object object) {

                    PrivacyPolicyResponeseBean bean=(PrivacyPolicyResponeseBean) object;
                    ArrayList<PrivacyPolicyResponseBean1> data=bean.getData();
                    tv_privacy_policy.setText(""+data.get(data.size()-1).getDiscription());

                }
            });


        } else {


            presenter.getHelpandSupport(new ResponseSucessCallback(){


                @Override
                public void responseSucess(Object object) {


                    HelpandSupportResponseBean bean=(HelpandSupportResponseBean) object;
                    ArrayList<HelpandSuppordResponseBean1> data=bean.getData();
                    tv_privacy_policy.setText(""+data.get(data.size()-1).getDiscription());

                }
            });
        }






    }

    @Override
    public void onSuccess(Object object) {


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