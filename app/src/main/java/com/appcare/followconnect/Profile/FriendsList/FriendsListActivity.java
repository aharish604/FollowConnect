package com.appcare.followconnect.Profile.FriendsList;

import androidx.annotation.RequiresApi;
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
import android.widget.TextView;

import com.appcare.followconnect.Chat.ResponseSucessCallback;
import com.appcare.followconnect.Common.AppPreference;
import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.Network.APIResponse;
import com.appcare.followconnect.Profile.FriendsList.Bean.FollowersRequestBean;
import com.appcare.followconnect.Profile.FriendsList.Bean.FollowersResponseBean;
import com.appcare.followconnect.Profile.FriendsList.Bean.FollowersResponseBean1;
import com.appcare.followconnect.Profile.FriendsList.Bean.FriendListResponseBean1;
import com.appcare.followconnect.Profile.FriendsList.Bean.FriendsListRequestBean;
import com.appcare.followconnect.Profile.FriendsList.Bean.FriendsListResponseBean;
import com.appcare.followconnect.Profile.ProfileActivity;
import com.appcare.followconnect.Profile.Profilepresenter;
import com.appcare.followconnect.R;

import java.util.ArrayList;

public class FriendsListActivity extends AppCompatActivity implements APIResponse {

    Profilepresenter presenter;
    RecyclerView rv_friendslist;
    String commingfrom = "";
    TextView tv_userid = null;
    ProgressDialog progressDialog = null;
    ImageButton imgbtn_searchuserprofile=null;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list3);

        getSupportActionBar().hide();
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));

        Intent intent = getIntent();
        if (intent != null) {
            commingfrom = intent.getStringExtra("commingfrom");
        }


        InitUI();
        IntializeObjetcs();
    }

    private void IntializeObjetcs() {

        presenter = new Profilepresenter(FriendsListActivity.this, FriendsListActivity.this);

        if (commingfrom.equalsIgnoreCase("FollowersList")) {
            FollowersRequestBean bean1 = new FollowersRequestBean();
            bean1.setUid(Constants.getUid(FriendsListActivity.this));

            presenter.getFollowersList(bean1, new ResponseSucessCallback() {
                @Override
                public void responseSucess(Object object) {

                    FollowersResponseBean bean = (FollowersResponseBean) object;
                    ArrayList<FollowersResponseBean1> data = bean.getData();

                    rv_friendslist.setAdapter(new FollowersListAdapter(data,FriendsListActivity.this));


                }
            });
        } else {
            FriendsListRequestBean bean = new FriendsListRequestBean();
            bean.setUid(Constants.getUid(FriendsListActivity.this));

            presenter.getFriendsList(bean, new ResponseSucessCallback() {
                @Override
                public void responseSucess(Object object) {

                    FriendsListResponseBean bean = (FriendsListResponseBean) object;
                    ArrayList<FriendListResponseBean1> data = bean.getData();
                    rv_friendslist.setAdapter(new FriendsListAdapter(data,FriendsListActivity.this));


                }
            });
        }


    }

    private void InitUI() {
        rv_friendslist = findViewById(R.id.rv_friendslist);
        imgbtn_searchuserprofile = findViewById(R.id.imgbtn_searchuserprofile);
        rv_friendslist.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        tv_userid = findViewById(R.id.tv_userid);
        if (commingfrom.equalsIgnoreCase("FollowersList")) {
            tv_userid.setText("FollowersList");
        } else {
            tv_userid.setText("FriendsList");
        }
        imgbtn_searchuserprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void onSuccess(Object object) {

    }

    @Override
    public void onServerError(String error) {
        dismissProgress();
        Constants.displayLongToast(FriendsListActivity.this, error);
    }


    @Override
    public void showProgress() {
        progressDialog = Constants.showProgressDialog(FriendsListActivity.this, "");
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
        Constants.displayLongToast(FriendsListActivity.this, error.toString());
    }
}