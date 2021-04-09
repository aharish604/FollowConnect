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
import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.Network.APIResponse;
import com.appcare.followconnect.Profile.FriendsList.Bean.FollowersRequestBean;
import com.appcare.followconnect.Profile.FriendsList.Bean.FollowersResponseBean;
import com.appcare.followconnect.Profile.FriendsList.Bean.FollowersResponseBean1;
import com.appcare.followconnect.Profile.FriendsList.Bean.FriendListResponseBean1;
import com.appcare.followconnect.Profile.FriendsList.Bean.FriendsListRequestBean;
import com.appcare.followconnect.Profile.FriendsList.Bean.FriendsListResponseBean;
import com.appcare.followconnect.Profile.Profilepresenter;
import com.appcare.followconnect.R;
import com.appcare.followconnect.Settings.BlockerList.UnblockRequestBean;
import com.appcare.followconnect.Settings.BlockerList.UnblockResponseBean;
import com.appcare.followconnect.Settings.BlockerList.BlockerListAdapter;
import com.appcare.followconnect.Settings.BlockerList.BlockerListRequestBean;
import com.appcare.followconnect.Settings.BlockerList.BlockerListResponseBean;
import com.appcare.followconnect.Settings.BlockerList.BlockerListResponseBean1;
import com.appcare.followconnect.SignUp.CountrySpinner.Adapterpositioncallback;

import java.util.ArrayList;

public class CommonListActivity extends AppCompatActivity implements APIResponse {
    Profilepresenter presenter;
    RecyclerView rv_friendslist;
    String commingfrom = "";
    TextView tv_userid = null;
    ProgressDialog progressDialog = null;
    ImageButton imgbtn_searchuserprofile = null;
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

        presenter = new Profilepresenter(CommonListActivity.this, CommonListActivity.this);


        if (commingfrom.equalsIgnoreCase("FollowersList")) {
            FollowersRequestBean bean1 = new FollowersRequestBean();
            bean1.setUid(Constants.getUid(CommonListActivity.this));

            presenter.getFollowersList(bean1, new ResponseSucessCallback() {
                @Override
                public void responseSucess(Object object) {

                    FollowersResponseBean bean = (FollowersResponseBean) object;
                    ArrayList<FollowersResponseBean1> data = bean.getData();

                    rv_friendslist.setAdapter(new FollowersListAdapter(data, CommonListActivity.this));


                }
            });
        } else if (commingfrom.equalsIgnoreCase("FriendsList")) {
            FriendsListRequestBean bean = new FriendsListRequestBean();
            bean.setUid(Constants.getUid(CommonListActivity.this));

            presenter.getFriendsList(bean, new ResponseSucessCallback() {
                @Override
                public void responseSucess(Object object) {

                    FriendsListResponseBean bean = (FriendsListResponseBean) object;
                    ArrayList<FriendListResponseBean1> data = bean.getData();
                    rv_friendslist.setAdapter(new FriendsListAdapter(data, CommonListActivity.this));


                }
            });
        } else if (commingfrom.equalsIgnoreCase("BlockerList")) {


            getBlockerList();

        }


    }

    public  void  getBlockerList()
    {
        BlockerListRequestBean bean = new BlockerListRequestBean();
        bean.setUid(Constants.getUid(CommonListActivity.this));
        presenter.getBlockerList(bean, new ResponseSucessCallback() {
            @Override
            public void responseSucess(Object object) {

                BlockerListResponseBean bean = (BlockerListResponseBean) object;

                setblockerAdapter(bean);


            }
        });


    }

    private void setblockerAdapter(BlockerListResponseBean bean) {

        ArrayList<BlockerListResponseBean1> data = bean.getData();
        rv_friendslist.setAdapter(new BlockerListAdapter(data, CommonListActivity.this, new Adapterpositioncallback() {
            @Override
            public void getadapterposition(Object object, int pos) {
                BlockerListResponseBean1 bean1 = (BlockerListResponseBean1) object;
                UnblockRequestBean bean = new UnblockRequestBean();
                bean.setUserId(Constants.getUid(CommonListActivity.this));
                bean.setBlockId(bean1.getUserId());
                presenter.unBlockuser(bean, new ResponseSucessCallback() {
                    @Override
                    public void responseSucess(Object object) {
                        UnblockResponseBean unblockResponseBean = (UnblockResponseBean) object;
                        Constants.displayLongToast(CommonListActivity.this, unblockResponseBean.getMessage());
                        getBlockerList();
                    }
                });
            }
        }));
    }

    private void InitUI() {
        rv_friendslist = findViewById(R.id.rv_friendslist);
        imgbtn_searchuserprofile = findViewById(R.id.imgbtn_searchuserprofile);
        rv_friendslist.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        tv_userid = findViewById(R.id.tv_userid);
        if (commingfrom.equalsIgnoreCase("FollowersList")) {
            tv_userid.setText("FollowersList");
        } else if (commingfrom.equalsIgnoreCase("FriendsList")) {
            tv_userid.setText("FriendsList");
        } else if (commingfrom.equalsIgnoreCase("BlockerList")) {
            tv_userid.setText("BlockerList");
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
        Constants.displayLongToast(CommonListActivity.this, error);
    }


    @Override
    public void showProgress() {
        progressDialog = Constants.showProgressDialog(CommonListActivity.this, "");
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
        Constants.displayLongToast(CommonListActivity.this, error.toString());
    }
}