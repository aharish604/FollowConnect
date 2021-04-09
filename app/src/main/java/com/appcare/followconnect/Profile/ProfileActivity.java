package com.appcare.followconnect.Profile;

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
import android.widget.ImageView;
import android.widget.TextView;

import com.appcare.followconnect.Chat.ProfileUserFeedAdapter;
import com.appcare.followconnect.Chat.ResponseSucessCallback;
import com.appcare.followconnect.Common.AppPreference;
import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.Home.HomeActivity;
import com.appcare.followconnect.Network.APIResponse;
import com.appcare.followconnect.Profile.Bean.ProfileBeanResponse;
import com.appcare.followconnect.Profile.Bean.ProfileResponseBean1;
import com.appcare.followconnect.Profile.Bean.UserFeedRequest;
import com.appcare.followconnect.Profile.Bean.UserFeedResponseBean;
import com.appcare.followconnect.Profile.Bean.UserfeedResponseBean1;
import com.appcare.followconnect.Profile.Bean.feedUserInfo;
import com.appcare.followconnect.Profile.FriendsList.CommonListActivity;
import com.appcare.followconnect.ProfileUpdate.UpdateProfileActivity;
import com.appcare.followconnect.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity implements APIResponse, View.OnClickListener {
    ProgressDialog progressDialog = null;
    Profilepresenter presenter = null;
    TextView tv_fullname = null;
    TextView tv_username = null, tvcount_1, tvcount_2, tvcount_3;
    TextView tv_editprofile = null, tv_postCount;
    CircleImageView profile_img = null;
    ImageView img1,img2;
    ImageButton imgbtn_searchuserprofile = null;
    ProfileResponseBean1 profileResponseBean1 = null;
    RecyclerView rv_userfeed = null;
    ProfileUserFeedAdapter profileUserFeedAdapter;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().hide();
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));

        AppPreference.getInstance(ProfileActivity.this);
        InitUI();
        IntializeObjetcs();


    }

    private void InitUI() {

        tv_fullname = findViewById(R.id.tv_fullname);
        tv_editprofile = findViewById(R.id.tv_editprofile);
        tv_username = findViewById(R.id.tv_username);
        profile_img = findViewById(R.id.profile_background);
        imgbtn_searchuserprofile = findViewById(R.id.imgbtn_searchuserprofile);
        tvcount_1 = findViewById(R.id.tvcount_1);
        tvcount_2 = findViewById(R.id.tvcount_2);
        tvcount_3 = findViewById(R.id.tvcount_3);
        tv_postCount = findViewById(R.id.tv_postCount);
        rv_userfeed = findViewById(R.id.rv_userfeed);

        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);


        imgbtn_searchuserprofile.setOnClickListener(ProfileActivity.this);
        tv_editprofile.setOnClickListener(ProfileActivity.this);
        img1.setOnClickListener(ProfileActivity.this);
        img2.setOnClickListener(ProfileActivity.this);

    }


    private void IntializeObjetcs() {

        presenter = new Profilepresenter(ProfileActivity.this, ProfileActivity.this);
        presenter.getProfileData(AppPreference.getInstance().getString(Constants.User_ID));

    }

    @Override
    public void onSuccess(Object object) {
        ProfileBeanResponse bean = (ProfileBeanResponse) object;
        profileResponseBean1 = bean.getData().get(0);

        tv_fullname.setText("" + profileResponseBean1.getFullname());
        tv_username.setText("" + profileResponseBean1.getUsername());

        AppPreference.getInstance().put(Constants.ProfilrURL, profileResponseBean1.getProfile_pic());

        Glide.with(ProfileActivity.this)
                .load(profileResponseBean1.getProfile_pic())
                .placeholder(R.drawable.update_profile)
                .into(profile_img);

        getUserfeeds();


    }

    private void getUserfeeds() {

        UserFeedRequest bean1 = new UserFeedRequest();
        bean1.setUid(Constants.getUid(ProfileActivity.this));


        presenter.getUserFeed(bean1, new ResponseSucessCallback() {
            @Override
            public void responseSucess(Object object) {
                UserFeedResponseBean bean = (UserFeedResponseBean) object;
                ArrayList<UserfeedResponseBean1> feedlist = bean.getData();
                ArrayList<feedUserInfo> userInfo = bean.getUserInfo();

                tvcount_1.setText("" + bean.getFriends());
                tvcount_2.setText("" + bean.getFollowers());
                tvcount_3.setText("" + bean.getPosts());
                tv_postCount.setText("" + bean.getPosts() + " Posts");


                setadapter(feedlist);


            }

        });

    }

    @Override
    public void onServerError(String error) {
        dismissProgress();
        Constants.displayLongToast(ProfileActivity.this, error);
    }


    @Override
    public void showProgress() {
        progressDialog = Constants.showProgressDialog(ProfileActivity.this, "");
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
        Constants.displayLongToast(ProfileActivity.this, error.toString());
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.imgbtn_searchuserprofile:
                openHomeActivity();
                break;
            case R.id.tv_editprofile:
                openeditProfileActivity();
                break;
                case R.id.img1:
                openFriendsListActivity();
                break;
                case R.id.img2:
                    openFollowersListActivity();
                break;
        }

    }

    private void openFriendsListActivity() {

        Intent i = new Intent(ProfileActivity.this, CommonListActivity.class);
        i.putExtra("commingfrom", "FriendsList");
        startActivity(i);

    }

    private void openFollowersListActivity() {

        Intent i = new Intent(ProfileActivity.this, CommonListActivity.class);
        i.putExtra("commingfrom", "FollowersList");
        startActivity(i);

    }

    private void openeditProfileActivity() {

        Intent i = new Intent(ProfileActivity.this, UpdateProfileActivity.class);
        i.putExtra("profilebean", profileResponseBean1);
        startActivity(i);

    }

    private void openHomeActivity() {
        Intent i = new Intent(ProfileActivity.this, HomeActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }

    private void setadapter(ArrayList<UserfeedResponseBean1> feedList) {

        rv_userfeed.setLayoutManager(new LinearLayoutManager(ProfileActivity.this));
        profileUserFeedAdapter = new ProfileUserFeedAdapter(ProfileActivity.this,feedList);
        rv_userfeed.setAdapter(profileUserFeedAdapter);
    }
}