package com.appcare.followconnect.Notifications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.appcare.followconnect.Chat.ResponseSucessCallback;
import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.Network.APIResponse;
import com.appcare.followconnect.Notifications.Bean.NotificationRequestBean;
import com.appcare.followconnect.Notifications.Bean.NotificationResponseBean1;
import com.appcare.followconnect.Notifications.Bean.NotificationResponsebean;
import com.appcare.followconnect.Profile.ProfileActivity;
import com.appcare.followconnect.R;
import com.appcare.followconnect.Home.HomeActivity;
import com.appcare.followconnect.UploadPost.UploadPostActivity;
import com.appcare.followconnect.UploadPost.UploadPostpresenter;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity implements APIResponse {

    RecyclerView notification_recyclerview=null;
    NotificationAdapter adapter=null;
    ImageButton imageButton=null;
    Notificationpresenter presenter=null;
    ProgressDialog progressDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }


        notification_recyclerview=findViewById(R.id.notification_recyclerview);
        notification_recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        imageButton=findViewById(R.id.imgbtn_notification);


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(NotificationActivity.this, HomeActivity.class);
                i1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i1);
                finish();
            }
        });

        InitializeObjects();

    }

    private void InitializeObjects() {
        presenter = new Notificationpresenter(NotificationActivity.this, NotificationActivity.this);

        NotificationRequestBean bean=new NotificationRequestBean();
        bean.setUid(Constants.getUid(NotificationActivity.this));

        presenter.getNotfications(bean,new ResponseSucessCallback(){


            @Override
            public void responseSucess(Object object) {
                NotificationResponsebean bean= (NotificationResponsebean)object;

                ArrayList<NotificationResponseBean1> list=bean.getData();

                setAdapter(list);

            }
        });

    }


    @Override
    public void onSuccess(Object object) {

    }

    @Override
    public void onServerError(String error) {
        dismissProgress();
        Constants.displayLongToast(NotificationActivity.this, error);
    }


    @Override
    public void showProgress() {
        progressDialog = Constants.showProgressDialog(NotificationActivity.this, "");
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
        Constants.displayLongToast(NotificationActivity.this, error.toString());
    }

    public void setAdapter(ArrayList<NotificationResponseBean1> list)
    {

        adapter=new NotificationAdapter(NotificationActivity.this,list);
        notification_recyclerview.setAdapter(adapter);


    }

}