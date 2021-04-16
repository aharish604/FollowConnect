package com.appcare.followconnect.SearchFriends;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.appcare.followconnect.Chat.ChatActivity;
import com.appcare.followconnect.Chat.ResponseSucessCallback;
import com.appcare.followconnect.Comments.CommentsActivity;
import com.appcare.followconnect.Common.AppPreference;
import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.Common.Utility;
import com.appcare.followconnect.Home.Adapter.MyviewAdapter;
import com.appcare.followconnect.Home.fragments.FeedLikeInputs;
import com.appcare.followconnect.MyviewPostdisplay.FeedLikeResponse;
import com.appcare.followconnect.MyviewPostdisplay.MyviewPresenter;
import com.appcare.followconnect.MyviewPostdisplay.bean.BlockResponse;
import com.appcare.followconnect.MyviewPostdisplay.bean.BlockerInputs;
import com.appcare.followconnect.MyviewPostdisplay.bean.DeleteFeedInputs;
import com.appcare.followconnect.MyviewPostdisplay.bean.DeleteResponse;
import com.appcare.followconnect.Network.APIResponse;
import com.appcare.followconnect.Profile.Bean.ProfileResponseBean1;
import com.appcare.followconnect.Profile.ProfileActivity;
import com.appcare.followconnect.R;
import com.appcare.followconnect.SearchFriends.AddasaFriend.AddfriendRequestBean;
import com.appcare.followconnect.SearchFriends.AddasaFriend.AddfrinedResponseBean;
import com.appcare.followconnect.SearchFriends.Bean.SearchDataInsertBeanResponse;
import com.appcare.followconnect.SearchFriends.Bean.SearchHistoryBeanRequest;
import com.appcare.followconnect.SearchFriends.Bean.SearchHistoryBeanResponse;
import com.appcare.followconnect.SearchFriends.Bean.SearchHistoryResponseBean1;
import com.appcare.followconnect.SearchFriends.Bean.UserFriendsInuts;
import com.appcare.followconnect.SearchFriends.followandunfollow.followResponseBean;
import com.appcare.followconnect.SearchFriends.followandunfollow.followrequestbean;
import com.appcare.followconnect.SearchFriends.unfriend.UnfriendRequestBean;
import com.appcare.followconnect.SearchFriends.unfriend.UnfriendResponseBean;
import com.appcare.followconnect.Settings.PasswordChangeActivity;
import com.appcare.followconnect.editfeed.EditFeedActivity;
import com.bumptech.glide.Glide;
import com.omega_r.libs.omegaintentbuilder.OmegaIntentBuilder;
import com.omega_r.libs.omegaintentbuilder.downloader.DownloadCallback;
import com.omega_r.libs.omegaintentbuilder.handlers.ContextIntentHandler;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

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
    List<UserFriendsFeedResponse.UserInfo> userinformation=new ArrayList<>();

    ImageView rightmark=null;
    LinearLayout ll_follow;
    String api_TAG="";
String fid="";
String usernmae="";
String fullname="";
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchuser_profile);

        getSupportActionBar().hide();
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        if(args!=null)
        {
            fid=args.getString("fid");
            usernmae=args.getString(Constants.UserName);
            fullname=args.getString(Constants.FullName);
        }

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
        rv_profilefeed=findViewById(R.id.rv_profilefeed);
        ll_follow=findViewById(R.id.ll_follow);
        rightmark=findViewById(R.id.rightmark);

        imgbtn_searchuserprofile.setOnClickListener(this);
        option_Menu.setOnClickListener(this);
        btnimg_chat.setOnClickListener(this);


        rv_profilefeed.setLayoutManager(new LinearLayoutManager(SearchUserProfileActivity.this));
        usersFriendFeedAdapter = new UsersFriendFeedAdapter(SearchUserProfileActivity.this,usersFeeddata,SearchUserProfileActivity.this);

        presenter = new UserFriendsPresenter(SearchUserProfileActivity.this, SearchUserProfileActivity.this);



        getuserFeed();


        ll_follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(!tv_friends.getText().toString().equalsIgnoreCase("Friends"))
                {
                    followrequestbean inputbean=new followrequestbean();
                    inputbean.setUid(Constants.getUid(  SearchUserProfileActivity.this));
                    inputbean.setFid(fid);

                    presenter.sendunfollowrequest(inputbean,new ResponseSucessCallback(){

                        @Override
                        public void responseSucess(Object object) {

                            followResponseBean bean= (followResponseBean) object;
                            Constants.displayLongToast(SearchUserProfileActivity.this,bean.getMessage());

                            getuserFeed();




                        }
                    });
                }

            }
        });

    }

    private void getuserFeed() {

        UserFriendsInuts inuts = new UserFriendsInuts();
        String uid = AppPreference.getInstance().getString(Constants.User_ID);
        inuts.setUid(uid);
        inuts.setFid(fid);
        presenter.getFriendsData(inuts, new ResponseSucessCallback() {
            @Override
            public void responseSucess(Object object) {
                UserFriendsFeedResponse bean = (UserFriendsFeedResponse) object;
                //  Toast.makeText(getApplicationContext(), ""+bean.getMessage(), Toast.LENGTH_SHORT).show();

                tvcount_2.setText(""+bean.getFollowers());
                userinformation = bean.getUserInfo();
                usersFeeddata = bean.getData();

                for (int i = 0; i < userinformation.size(); i++) {

                    profile_name.setText(userinformation.get(i).getFullname());
                    tv_username.setText(userinformation.get(i).getUsername());
                    tv_usernametoolbar.setText(userinformation.get(i).getFullname());

                    String resp = usersFeeddata.get(0).getFriendStatus();



                    Glide.with(SearchUserProfileActivity.this)
                            .load(userinformation.get(i).getProfilePic())
                            .placeholder(R.drawable.ic_baseline_account_circle_24)
                            .into(edit_profilepicture);

                    //    frienduserid = userinformation.get(i).getUserId();

                }


                if(bean.getFriendStatus().equalsIgnoreCase("noFriend")) {

                    if(bean.getConnectionStatus().equalsIgnoreCase("noFollowing")) {
                        tv_friends.setText("Follow");
                        rightmark.setVisibility(View.VISIBLE);
                    }else {
                        tv_friends.setText("Following");
                        rightmark.setVisibility(View.GONE);

                    }



                }else {

                    tv_friends.setText("Friends");
                    btnimg_chat.setVisibility(View.VISIBLE);
                    rightmark.setVisibility(View.GONE);

                }

                usersFriendFeedAdapter = new UsersFriendFeedAdapter(SearchUserProfileActivity.this,usersFeeddata,SearchUserProfileActivity.this);
                rv_profilefeed.setAdapter(usersFriendFeedAdapter);

                setData(bean);
            }
        });


    }

    private void setData(UserFriendsFeedResponse bean) {

        option_Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu menu = new PopupMenu(view.getContext(),option_Menu);
                menu.getMenuInflater().inflate(R.menu.popup_menu_searchprofile, menu.getMenu());

                if (bean.getFriendStatus().equalsIgnoreCase("noFriend")) {
                    menu.getMenu().removeItem(R.id.unfriend);
                    menu.getMenu().removeItem(R.id.blockuser);
                } else {
                    menu.getMenu().removeItem(R.id.addfriend);


                }
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {

                            // item.setVisible(false);


                            case R.id.addfriend:

                                showAlertAddfriend();

                                break;
                            case R.id.unfriend:

                                UnfriendRequestBean inputs = new UnfriendRequestBean();
                                inputs.setFid(userinformation.get(0).getUserId());
                                inputs.setUid(Constants.getUid(SearchUserProfileActivity.this));
                                MyviewPresenter   presenter = new MyviewPresenter(SearchUserProfileActivity.this, SearchUserProfileActivity.this);
                                presenter.unfriend(inputs, new ResponseSucessCallback() {
                                    @Override
                                    public void responseSucess(Object object) {
                                        UnfriendResponseBean blockResponse = (UnfriendResponseBean) object;
                                        Toast.makeText(SearchUserProfileActivity.this, "" + blockResponse.getMessage(), Toast.LENGTH_SHORT).show();

                                        getuserFeed();

                                    }

                                });


                                break;
                            case R.id.blockuser:
                                BlockerInputs inputs1 = new BlockerInputs();
                                inputs1.setBlock_id(userinformation.get(0).getUserId());
                                inputs1.setUser_id(Constants.getUid(SearchUserProfileActivity.this));

                                MyviewPresenter   presenter1 = new MyviewPresenter(SearchUserProfileActivity.this, SearchUserProfileActivity.this);
                                presenter1.block(inputs1, new ResponseSucessCallback() {
                                    @Override
                                    public void responseSucess(Object object) {
                                        BlockResponse blockResponse = (BlockResponse) object;
                                        Toast.makeText(SearchUserProfileActivity.this, "" + blockResponse.getMessage(), Toast.LENGTH_SHORT).show();

                                    }

                                });

                                break;
                        }
                        return true;
                    }
                });
                menu.show();
            }

        });


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
                LinearLayout blockuser = layout.findViewById(R.id.ll_searchfriends);
                LinearLayout unfriend = layout.findViewById(R.id.ll_notification);

                blockuser.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {




                    }
                });

                unfriend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {



                    }
                });


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

        OmegaIntentBuilder.from(SearchUserProfileActivity.this)
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
        inputs.setUser_id(Constants.getUid(SearchUserProfileActivity.this));

        block(inputs);
    }

    private void block(BlockerInputs inputs) {

        MyviewPresenter presenter=new MyviewPresenter(this,SearchUserProfileActivity.this);
        presenter.block(inputs, new ResponseSucessCallback() {
            @Override
            public void responseSucess(Object object) {
                BlockResponse blockResponse =  (BlockResponse) object;
                Constants.displayLongToast(SearchUserProfileActivity.this,blockResponse.getMessage());
            }
        });
    }

    public void deleteFeed(String feedid, int position) {
        DeleteFeedInputs inputs = new DeleteFeedInputs();

        inputs.setUid(Constants.getUid(SearchUserProfileActivity.this));
        inputs.setFeed_id(feedid);

        MyviewPresenter presenter=new MyviewPresenter(this,SearchUserProfileActivity.this);

        presenter.deleteFeed(inputs, new ResponseSucessCallback() {
            @Override
            public void responseSucess(Object object) {
                DeleteResponse deleteResponse =  (DeleteResponse) object;
                //   Toast.makeText(getActivity(), ""+deleteResponse.getMessage(), Toast.LENGTH_SHORT).show();
                Constants.displayLongToast(SearchUserProfileActivity.this,deleteResponse.getMessage());
                usersFeeddata.remove(position);
                usersFriendFeedAdapter.notifyDataSetChanged();
            }
        });
    }

    public void commentsClick(String feedid, String postid, int count, int position) {
        Intent i = new Intent(SearchUserProfileActivity.this, CommentsActivity.class);
        i.putExtra("FEEDID", feedid);
        i.putExtra("POSTID", postid);
        startActivity(i);
    }

    public void edit(String feedId) {
        Intent i = new Intent(SearchUserProfileActivity.this, EditFeedActivity.class);
        i.putExtra("FEEDID", feedId);
        startActivity(i);
    }

    public void likes(int position, String feedid, String postid, int count, int likeStatus) {
        FeedLikeInputs inputs = new FeedLikeInputs();
        inputs.setFeed_id(feedid);
        inputs.setPoster_id(postid);
        inputs.setCommenter_id(Constants.getUid(SearchUserProfileActivity.this));
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

        MyviewPresenter presenter=new MyviewPresenter(this,SearchUserProfileActivity.this);

        presenter.postLikes(inputs, new ResponseSucessCallback() {
            @Override
            public void responseSucess(Object object) {
                FeedLikeResponse feedLikeResponse =  (FeedLikeResponse) object;
                Toast.makeText(SearchUserProfileActivity.this, ""+feedLikeResponse.getMessage(), Toast.LENGTH_SHORT).show();
                if(api_TAG.equals("Likes"))
                {

                    usersFeeddata.get(position).setLikesCount(countvalu);
                    usersFeeddata.get(position).setLikes(likeStatus);
                    usersFriendFeedAdapter.notifyDataSetChanged();
                }else if(api_TAG.equals("DisLikes"))
                {
                    usersFeeddata.get(position).setLikesCount(countvalu);
                    usersFeeddata.get(position).setLikes(likeStatus);
                    usersFriendFeedAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    public void disLikes(int position, String feedid, String postid, int count, int likeStatus) {
        FeedLikeInputs inputs = new FeedLikeInputs();
        inputs.setFeed_id(feedid);
        inputs.setPoster_id(postid);
        inputs.setCommenter_id(Constants.getUid(SearchUserProfileActivity.this));
        inputs.setComment("");
        inputs.setLike("0");
        inputs.setDislike("");
        inputs.setShare("");
        inputs.setView("");
        api_TAG = "DisLikes";
        int countvalu = count-1;
        postLikes(inputs, position, api_TAG, countvalu, 0);
    }

    public void showAlertAddfriend() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View customLayout = getLayoutInflater().inflate(R.layout.layout_addfriend, null);


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

                if (Constants.isNetworkAvailable(SearchUserProfileActivity.this)) {
                    mDialog.dismiss();

                    validateemail(et_email.getText().toString().trim());

                } else {

                    Constants.displayLongToast(SearchUserProfileActivity.this, getResources().getString(R.string.check_network));

                }

            }


        });


    }

    private void validateemail(String email) {

        if (email.isEmpty()) {
            Constants.displayLongToast(SearchUserProfileActivity.this, getString(R.string.alert_entereamil));
        } else if (!Utility.isEmailValid(email)) {
            Constants.displayLongToast(SearchUserProfileActivity.this, getString(R.string.alert_entervalidemail));
        } else {
            sendFriendRequest(email);
        }


    }

    private void sendFriendRequest(String email) {
        AddfriendRequestBean bean=new AddfriendRequestBean();
        bean.setEmail(email);
        bean.setUid(Constants.getUid(SearchUserProfileActivity.this));

        presenter.sendFriendRequest(bean,new ResponseSucessCallback(){

            @Override
            public void responseSucess(Object object) {
                AddfrinedResponseBean bean=(AddfrinedResponseBean) object;

                Constants.displayLongToast(SearchUserProfileActivity.this,bean.getMessage());

            }
        });


    }


}