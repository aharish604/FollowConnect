package com.appcare.followconnect.SearchFriends;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.appcare.followconnect.Chat.ChatActivity;
import com.appcare.followconnect.Chat.ResponseSucessCallback;
import com.appcare.followconnect.Common.AppPreference;
import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.Home.Adapter.MyviewAdapter;
import com.appcare.followconnect.Network.APIResponse;
import com.appcare.followconnect.Profile.Bean.ProfileResponseBean1;
import com.appcare.followconnect.R;
import com.appcare.followconnect.SearchFriends.Bean.SearchDataInsertBeanResponse;
import com.appcare.followconnect.SearchFriends.Bean.SearchHistoryBeanRequest;
import com.appcare.followconnect.SearchFriends.Bean.SearchHistoryBeanResponse;
import com.appcare.followconnect.SearchFriends.Bean.SearchHistoryResponseBean1;
import com.appcare.followconnect.SearchFriends.Bean.UserFriendsFeedResponse;
import com.appcare.followconnect.SearchFriends.Bean.UserFriendsInuts;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SearchUserProfileActivity extends AppCompatActivity implements View.OnClickListener, APIResponse {

    ImageButton imgbtn_searchuserprofile=null;
    ImageButton option_Menu=null, btnimg_chat = null;
    TextView tv_username=null;
    RecyclerView rv_profilefeed;
    CircleImageView edit_profilepicture = null;
    TextView profile_name = null;
    TextView tvcount_2 = null, tv_friends = null, tv_usernametoolbar = null;
    UsersFriendFeedAdapter usersFriendFeedAdapter;
    List<UserFriendsFeedResponse.Datum> usersFeeddata;
    ProgressDialog progressDialog = null;
    UserFriendsPresenter presenter;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchuser_profile);

        getSupportActionBar().hide();
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));


        InitUI();

    }

    private void InitUI() {
        usersFeeddata = new ArrayList<>();
        imgbtn_searchuserprofile=findViewById(R.id.imgbtn_searchuserprofile);
        option_Menu=findViewById(R.id.option_Menu);
        btnimg_chat=findViewById(R.id.btnimg_chat);
        tv_username=findViewById(R.id.tv_username);
        tv_usernametoolbar=findViewById(R.id.tv_usernametoolbar);
        profile_name=findViewById(R.id.profile_name);
        tvcount_2=findViewById(R.id.tvcount_2);
        edit_profilepicture=findViewById(R.id.edit_profilepicture);
        tv_friends=findViewById(R.id.tv_friends);
        tv_friends=findViewById(R.id.tv_friends);
        rv_profilefeed=findViewById(R.id.rv_profilefeed);

        imgbtn_searchuserprofile.setOnClickListener(this);
        option_Menu.setOnClickListener(this);
        btnimg_chat.setOnClickListener(this);


        rv_profilefeed.setLayoutManager(new LinearLayoutManager(SearchUserProfileActivity.this));
        usersFriendFeedAdapter = new UsersFriendFeedAdapter(SearchUserProfileActivity.this,usersFeeddata);

        presenter = new UserFriendsPresenter(SearchUserProfileActivity.this, SearchUserProfileActivity.this);

        UserFriendsInuts inuts = new UserFriendsInuts();
        String uid = AppPreference.getInstance().getString(Constants.User_ID);
        String fid = Constants.searchFriendsListData.getUserId();
        inuts.setUid(uid);
        inuts.setFid(fid);
        presenter.getFriendsData(inuts, new ResponseSucessCallback() {
            @Override
            public void responseSucess(Object object) {
                UserFriendsFeedResponse bean = (UserFriendsFeedResponse) object;
                //  Toast.makeText(getApplicationContext(), ""+bean.getMessage(), Toast.LENGTH_SHORT).show();

                tvcount_2.setText(""+bean.getFollowers());
                List<UserFriendsFeedResponse.UserInfo> userinformation = bean.getUserInfo();
                usersFeeddata = bean.getData();

                for (int i = 0; i < userinformation.size(); i++) {

                    profile_name.setText(userinformation.get(i).getFullname());
                    tv_username.setText(userinformation.get(i).getUsername());
                    tv_usernametoolbar.setText(userinformation.get(i).getFullname());

                    String resp = usersFeeddata.get(0).getFriendStatus();


                    if(usersFeeddata.get(0).getConnectionStatus().equalsIgnoreCase("noFollowing")) {
                        tv_friends.setText("Follow");
                    }else {
                        tv_friends.setText("UnFollow");
                        if(usersFeeddata.get(0).getFriendStatus().equalsIgnoreCase("noFriend")) {
                        }else {
                            tv_friends.setText("Friends");
                            btnimg_chat.setVisibility(View.VISIBLE);
                        }
                    }

                    Glide.with(SearchUserProfileActivity.this)
                            .load(userinformation.get(i).getProfilePic())
                            .placeholder(R.drawable.update_profile)
                            .into(edit_profilepicture);

                    //    frienduserid = userinformation.get(i).getUserId();

                }

                usersFriendFeedAdapter = new UsersFriendFeedAdapter(SearchUserProfileActivity.this,usersFeeddata);
                rv_profilefeed.setAdapter(usersFriendFeedAdapter);
            }
        });


        setData();

    }

    private void setData() {
//        profile_name.setText(Constants.searchFriendsListData.getFullname());
        tv_username.setText(Constants.searchFriendsListData.getFullname());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {

            case R.id.option_Menu:

                LayoutInflater inflater = (LayoutInflater) SearchUserProfileActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.optionmenu_usersearchprofile, null);
                final PopupWindow window = new PopupWindow(layout, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                window.setOutsideTouchable(true);
                window.showAtLocation(layout, Gravity.TOP | Gravity.END, 0, 0);
                LinearLayout ll_searchfriends = layout.findViewById(R.id.ll_searchfriends);
                LinearLayout ll_notification = layout.findViewById(R.id.ll_notification);



                break;

            case R.id.imgbtn_searchuserprofile:


                Intent i1 = new Intent(SearchUserProfileActivity.this, SearchFriendsActivity.class);
                i1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i1);
                finish();
                break;
            case R.id.btnimg_chat:
                Intent intnt = new Intent(SearchUserProfileActivity.this, ChatActivity.class);
                intnt.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intnt);
                finish();
                break;
        }
    }

    @Override
    public void onSuccess(Object object) {

        UserFriendsFeedResponse userFriendsFeedResponse = (UserFriendsFeedResponse) object;
        Constants.displayLongToast(SearchUserProfileActivity.this, userFriendsFeedResponse.getMessage());
    }

    @Override
    public void onServerError(String error) {
        dismissProgress();
        Constants.displayLongToast(SearchUserProfileActivity.this, error);
    }

    @Override
    public void showProgress() {
        progressDialog = Constants.showProgressDialog(SearchUserProfileActivity.this, "");
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
        Constants.displayLongToast(SearchUserProfileActivity.this, error);

    }

}