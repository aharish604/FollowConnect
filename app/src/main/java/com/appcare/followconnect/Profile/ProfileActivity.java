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
import android.widget.Toast;

import com.appcare.followconnect.Chat.ProfileUserFeedAdapter;
import com.appcare.followconnect.Chat.ResponseSucessCallback;
import com.appcare.followconnect.Comments.CommentsActivity;
import com.appcare.followconnect.Common.AppPreference;
import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.Home.HomeActivity;
import com.appcare.followconnect.Home.fragments.FeedLikeInputs;
import com.appcare.followconnect.MyviewPostdisplay.FeedLikeResponse;
import com.appcare.followconnect.MyviewPostdisplay.MyviewPresenter;
import com.appcare.followconnect.MyviewPostdisplay.bean.BlockResponse;
import com.appcare.followconnect.MyviewPostdisplay.bean.BlockerInputs;
import com.appcare.followconnect.MyviewPostdisplay.bean.DeleteFeedInputs;
import com.appcare.followconnect.MyviewPostdisplay.bean.DeleteResponse;
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
import com.appcare.followconnect.editfeed.EditFeedActivity;
import com.bumptech.glide.Glide;
import com.omega_r.libs.omegaintentbuilder.OmegaIntentBuilder;
import com.omega_r.libs.omegaintentbuilder.downloader.DownloadCallback;
import com.omega_r.libs.omegaintentbuilder.handlers.ContextIntentHandler;

import org.jetbrains.annotations.NotNull;

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
    String api_TAG="";
    ArrayList<UserfeedResponseBean1> feedlist=new ArrayList<>();

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
                .placeholder(R.drawable.ic_baseline_account_circle_24)
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
                 feedlist = bean.getData();
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
        profileUserFeedAdapter = new ProfileUserFeedAdapter(ProfileActivity.this,feedList,ProfileActivity.this);
        rv_userfeed.setAdapter(profileUserFeedAdapter);
    }



    public void whatsAppShare(String fileuri, String sid, String feed) {

        showProgress();

        String[] imagesarray=null;
        ArrayList<String> list=new ArrayList<>();
        list.clear();
        if (!fileuri.equalsIgnoreCase("")) {
            imagesarray = fileuri.split(",");
        }

        for(String ch:imagesarray)
        {

            list.add(ch);
        }

        OmegaIntentBuilder.from(ProfileActivity.this)
                .share()
                //.emailTo("your_email_here@gmail.com")
                //.subject("Great library")
                .filesUrls()
                .filesUrls(list)
                // .fileUrlWithMimeType("https://avatars1.githubusercontent.com/u/28600571?s=200&v=4", MimeTypes.IMAGE_PNG)
                .download(new DownloadCallback() {
                    @Override
                    public void onDownloaded(boolean success, @NotNull ContextIntentHandler contextIntentHandler) {

                        dismissProgress();
                        contextIntentHandler.startActivity();
                    }
                });

    }


    public void blockuser(String blockerId) {
        BlockerInputs inputs = new BlockerInputs();

        inputs.setBlock_id(blockerId);
        inputs.setUser_id(Constants.getUid(ProfileActivity.this));

        block(inputs);
    }

    private void block(BlockerInputs inputs) {

        MyviewPresenter presenter=new MyviewPresenter(this,ProfileActivity.this);
        presenter.block(inputs, new ResponseSucessCallback() {
            @Override
            public void responseSucess(Object object) {
                BlockResponse blockResponse =  (BlockResponse) object;
                Constants.displayLongToast(ProfileActivity.this,blockResponse.getMessage());
            }
        });
    }


    public void deleteFeed(String feedid, int position) {
        DeleteFeedInputs inputs = new DeleteFeedInputs();

        inputs.setUid(Constants.getUid(ProfileActivity.this));
        inputs.setFeed_id(feedid);

        MyviewPresenter presenter=new MyviewPresenter(this,ProfileActivity.this);

        presenter.deleteFeed(inputs, new ResponseSucessCallback() {
            @Override
            public void responseSucess(Object object) {
                DeleteResponse deleteResponse =  (DeleteResponse) object;
             //   Toast.makeText(getActivity(), ""+deleteResponse.getMessage(), Toast.LENGTH_SHORT).show();
                Constants.displayLongToast(ProfileActivity.this,deleteResponse.getMessage());
                feedlist.remove(position);
                profileUserFeedAdapter.notifyDataSetChanged();
            }
        });
    }

    public void commentsClick(String feedid, String postid, int count, int position) {
        Intent i = new Intent(ProfileActivity.this, CommentsActivity.class);
        i.putExtra("FEEDID", feedid);
        i.putExtra("POSTID", postid);
        startActivity(i);
    }

    public void edit(String feedId) {
        Intent i = new Intent(ProfileActivity.this, EditFeedActivity.class);
        i.putExtra("FEEDID", feedId);
        startActivity(i);
    }

    public void likes(int position, String feedid, String postid, int count, int likeStatus) {
        FeedLikeInputs inputs = new FeedLikeInputs();
        inputs.setFeed_id(feedid);
        inputs.setPoster_id(postid);
        inputs.setCommenter_id(Constants.getUid(ProfileActivity.this));
        inputs.setComment("");
        inputs.setLike("1");
        inputs.setDislike("");
        inputs.setShare("");
        inputs.setView("");
        api_TAG = "Likes";
        int countvalu;
        if(likeStatus == 0){
            likeStatus = 1;
            countvalu = count+1;
        }else{
            likeStatus = 0;
            countvalu = count- 1;
        }
        postLikes(inputs, position, api_TAG, countvalu, likeStatus);
    }

    private void postLikes(FeedLikeInputs inputs, int position, String likes, int countvalu, int likeStatus) {

        MyviewPresenter presenter=new MyviewPresenter(this,ProfileActivity.this);

        presenter.postLikes(inputs, new ResponseSucessCallback() {
            @Override
            public void responseSucess(Object object) {
                FeedLikeResponse feedLikeResponse =  (FeedLikeResponse) object;
                Toast.makeText(ProfileActivity.this, ""+feedLikeResponse.getMessage(), Toast.LENGTH_SHORT).show();
                if(api_TAG.equals("Likes"))
                {

                    feedlist.get(position).setLikesCount(countvalu);
                    feedlist.get(position).setLikes(likeStatus);
                    profileUserFeedAdapter.notifyDataSetChanged();
                }else if(api_TAG.equals("DisLikes"))
                {
                    feedlist.get(position).setLikesCount(countvalu);
                    feedlist.get(position).setLikes(likeStatus);
                    profileUserFeedAdapter.notifyDataSetChanged();
                }
            }
        });

    }


    public void disLikes(int position, String feedid, String postid, int count, int likeStatus) {
        FeedLikeInputs inputs = new FeedLikeInputs();
        inputs.setFeed_id(feedid);
        inputs.setPoster_id(postid);
        inputs.setCommenter_id(Constants.getUid(ProfileActivity.this));
        inputs.setComment("");
        inputs.setLike("0");
        inputs.setDislike("");
        inputs.setShare("");
        inputs.setView("");
        api_TAG = "DisLikes";
        int countvalu = count-1;
        postLikes(inputs, position, api_TAG, countvalu, 0);
    }


}