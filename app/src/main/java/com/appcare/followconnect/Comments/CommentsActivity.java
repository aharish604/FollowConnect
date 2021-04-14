package com.appcare.followconnect.Comments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.appcare.followconnect.Chat.ResponseSucessCallback;
import com.appcare.followconnect.Comments.adapter.CommentsAdaper;
import com.appcare.followconnect.Comments.model.CommentsListInputs;
import com.appcare.followconnect.Comments.model.CommentsListResponse;
import com.appcare.followconnect.Comments.presenter.CommentsPresenter;
import com.appcare.followconnect.Common.AppPreference;
import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.Home.fragments.FeedLikeInputs;
import com.appcare.followconnect.MyviewPostdisplay.FeedLikeResponse;
import com.appcare.followconnect.R;

import com.appcare.followconnect.Network.APIResponse;

import java.util.ArrayList;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CommentsActivity extends AppCompatActivity implements View.OnClickListener, APIResponse {

    RecyclerView comments_rv;
    EditText et_comments;
    CommentsAdaper commentsAdaper;
    ArrayList<CommentsListResponse.CommentData> commentsArrayList;
    CommentsPresenter presenter;
    ProgressDialog progressDialog = null;
    String feedid = "", postid = "", userid= "";
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        getSupportActionBar().hide();
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));

        InitValues();

    }

    private void InitValues() {
        et_comments = findViewById(R.id.et_comments);
        comments_rv = findViewById(R.id.comments_rv);
        findViewById(R.id.imgbtn_back).setOnClickListener(this);
        findViewById(R.id.send_btn).setOnClickListener(this);
        commentsArrayList = new ArrayList<>();
        comments_rv.setLayoutManager(new LinearLayoutManager(CommentsActivity.this));

        presenter = new CommentsPresenter(CommentsActivity.this, CommentsActivity.this);

        Intent myIntent = getIntent();
         feedid = myIntent.getStringExtra("FEEDID");
         postid = myIntent.getStringExtra("POSTID");
        userid= AppPreference.getInstance(CommentsActivity.this).getString(Constants.User_ID);
        Intialize_RV();
    }

    private void Intialize_RV() {
        presenter = new CommentsPresenter(CommentsActivity.this, this);
        CommentsListInputs inputs = new CommentsListInputs();

        inputs.setFeed_id(feedid);

        presenter.getCommentsList(inputs, new ResponseSucessCallback() {
            @Override
            public void responseSucess(Object object) {
                CommentsListResponse chatHistoryBeanResponse = (CommentsListResponse) object;
                commentsArrayList = chatHistoryBeanResponse.getData();

                commentsAdaper=new CommentsAdaper(CommentsActivity.this,commentsArrayList);
                comments_rv.setAdapter(commentsAdaper);

            }
        });
    }




    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgbtn_back:
                finish();
                break;
            case R.id.send_btn:
                if(et_comments.getText().toString().equals("")){
                    Constants.displayLongToast(CommentsActivity.this, "Enter Your Comments");
                }else {
                    FeedLikeInputs inputs = new FeedLikeInputs();
                    inputs.setFeed_id(feedid);
                    inputs.setPoster_id(postid);
                    inputs.setCommenter_id(userid);
                    inputs.setComment(et_comments.getText().toString().trim());
                    inputs.setLike("");
                    inputs.setDislike("");
                    inputs.setShare("");
                    inputs.setView("");
                    commentsPost(inputs);
                }

                break;
        }
    }

    private void commentsPost(FeedLikeInputs inputs) {
        presenter.postComments(inputs, new ResponseSucessCallback() {
            @Override
            public void responseSucess(Object object) {
                FeedLikeResponse feedLikeResponse =  (FeedLikeResponse) object;
                Toast.makeText(CommentsActivity.this, ""+feedLikeResponse.getMessage(), Toast.LENGTH_SHORT).show();

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
        Constants.displayLongToast(CommentsActivity.this, error);
    }


    @Override
    public void showProgress() {
        progressDialog = Constants.showProgressDialog(CommentsActivity.this, "");
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
        Constants.displayLongToast(CommentsActivity.this, error.toString());
    }
}
