package com.appcare.followconnect.SearchFriends;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.appcare.followconnect.Common.AppPreference;
import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.Login.LoginResponse;
import com.appcare.followconnect.Login.LoginResponseBean;
import com.appcare.followconnect.LoginWithGoogle.ProfileCallback;
import com.appcare.followconnect.Network.APIResponse;
import com.appcare.followconnect.SearchFriends.Bean.SearchDataInsertBeanRequest;
import com.appcare.followconnect.SearchFriends.Bean.SearchDataInsertBeanResponse;
import com.appcare.followconnect.SearchFriends.Bean.SearchHistoryBeanRequest;
import com.appcare.followconnect.SearchFriends.Bean.SearchHistoryBeanResponse;
import com.appcare.followconnect.SearchFriends.Bean.SearchHistoryResponseBean1;
import com.appcare.followconnect.SearchFriends.Bean.UserNameSearchBeanRequest;
import com.appcare.followconnect.SearchFriends.Bean.UserNameSearchBeanResponse;
import com.appcare.followconnect.SearchFriends.Bean.UserNameSearchResponseBean1;
import com.appcare.followconnect.SignUp.CountrySpinner.Adapterpositioncallback;
import com.appcare.followconnect.Home.HomeActivity;
import com.appcare.followconnect.R;

import java.util.ArrayList;

public class SearchFriendsActivity extends AppCompatActivity implements APIResponse, View.OnClickListener {
    RecyclerView recyclerView = null;
    SearchFriendsAdapter searchFriendsAdapter = null;
    ImageButton imgbtn_notification = null;
    EditText searchfriends_et = null;
    ImageButton btn_img_Searchfrnd = null;
    SearchFriendsPresenter presenter = null;
    ProgressDialog progressDialog = null;
    UserNameSearchResponseBean1 usernameSearchResponseBean = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_friends);
        getSupportActionBar().hide();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }

        initUI();
        IntializeObjetcs();
        IntializeRecyclerview();
    }

    private void IntializeRecyclerview() {
        recyclerView.setLayoutManager(new GridLayoutManager(SearchFriendsActivity.this, 2));
        //searchFriendsAdapter=new SearchFriendsAdapter(SearchFriendsActivity.this);
        //recyclerView.setAdapter(searchFriendsAdapter);

        //  getdapterPosition();

    }


    private void IntializeObjetcs() {
        presenter = new SearchFriendsPresenter(SearchFriendsActivity.this, SearchFriendsActivity.this);

        SearchHistoryBeanRequest bean = new SearchHistoryBeanRequest();

        String uid = AppPreference.getInstance().getString(Constants.User_ID);

        bean.setUid(uid);

        presenter.getSearchHistory(bean, new GetSearchHistoryCallback() {


            @Override
            public void getSearchHistory(Object object) {
                SearchHistoryBeanResponse bean = (SearchHistoryBeanResponse) object;
                ArrayList<SearchHistoryResponseBean1> list = bean.getData();

                if (list.size() != 0) {
                    searchFriendsAdapter = new SearchFriendsAdapter(SearchFriendsActivity.this, list);
                    recyclerView.setAdapter(searchFriendsAdapter);

                    getdapterPosition();
                }


            }
        });

    }


    private void initUI() {
        recyclerView = findViewById(R.id.rv_userSearch);
        imgbtn_notification = findViewById(R.id.imgbtn_notification);
        searchfriends_et = findViewById(R.id.searchfriends_et);
        btn_img_Searchfrnd = findViewById(R.id.btn_img_Searchfrnd);

        imgbtn_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(SearchFriendsActivity.this, HomeActivity.class);
                i1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i1);
                finish();
            }
        });

        btn_img_Searchfrnd.setOnClickListener(SearchFriendsActivity.this);

    }


    public void getdapterPosition() {
        searchFriendsAdapter.adapterPosition(new Adapterpositioncallback() {
            @Override
            public void getadapterposition(Object object, int pos) {

                SearchHistoryResponseBean1 bean = (SearchHistoryResponseBean1) object;

                SearchDataInsertBeanRequest searchDataInsertBeanRequest = new SearchDataInsertBeanRequest();

              /*  searchDataInsertBeanRequest.setFid(bean.getUserId());
                searchDataInsertBeanRequest.setQuery(bean.);
                searchDataInsertBeanRequest.setUid(AppPreference.getInstance().getString(Constants.User_ID));

                presenter.insertSearchData();*/

                Intent i1 = new Intent(SearchFriendsActivity.this, SearchUserProfileActivity.class);
                startActivity(i1);

            }
        });

    }

    @Override
    public void onSuccess(Object object) {
        SearchDataInsertBeanResponse searchDataInsertBeanResponse = (SearchDataInsertBeanResponse) object;
        Constants.displayLongToast(SearchFriendsActivity.this, searchDataInsertBeanResponse.getMessage());

        Intent i1 = new Intent(SearchFriendsActivity.this, SearchUserProfileActivity.class);
        startActivity(i1);


    }

    @Override
    public void onServerError(String error) {
        dismissProgress();
        Constants.displayLongToast(SearchFriendsActivity.this, error);

    }

    @Override
    public void showProgress() {
        progressDialog = Constants.showProgressDialog(SearchFriendsActivity.this, "");
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
        Constants.displayLongToast(SearchFriendsActivity.this, error);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_img_Searchfrnd:

                if(!searchfriends_et.getText().toString().equalsIgnoreCase("")) {
                    UserNameSearchBeanRequest bean = new UserNameSearchBeanRequest();
                    bean.setUid(Constants.getUid(SearchFriendsActivity.this));
                    bean.setUsername(searchfriends_et.getText().toString());

                    presenter.SearchFriend(bean, new GetSearchHistoryCallback() {
                        @Override
                        public void getSearchHistory(Object object) {
                            UserNameSearchBeanResponse userNameSearchBeanResponse = (UserNameSearchBeanResponse) object;
                            if (userNameSearchBeanResponse.getData().size() != 0) {
                                usernameSearchResponseBean = userNameSearchBeanResponse.getData().get(0);
                                SearchDataInsertBeanRequest searchDataInsertBeanRequest = new SearchDataInsertBeanRequest();
                                searchDataInsertBeanRequest.setUid(Constants.getUid(SearchFriendsActivity.this));
                                searchDataInsertBeanRequest.setQuery("" + searchfriends_et.getText().toString());
                                searchDataInsertBeanRequest.setFid(usernameSearchResponseBean.getUserId());
                                presenter.insertSearchList(searchDataInsertBeanRequest);

                            } else {
                                Constants.displayLongToast(SearchFriendsActivity.this, "User Not Found!");
                            }
                        }
                    });
                }else {
                    Constants.displayLongToast(SearchFriendsActivity.this,SearchFriendsActivity.this.getResources().getString(R.string.check_network));
                }

                break;

        }

    }
}