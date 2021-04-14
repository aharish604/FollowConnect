package com.appcare.followconnect.Home.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appcare.followconnect.Chat.ResponseSucessCallback;
import com.appcare.followconnect.Comments.CommentsActivity;
import com.appcare.followconnect.Home.Adapter.MyviewAdapter;
import com.appcare.followconnect.Common.AppPreference;
import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.MyviewPostdisplay.FeedLikeResponse;
import com.appcare.followconnect.MyviewPostdisplay.MyviewPresenter;
import com.appcare.followconnect.MyviewPostdisplay.bean.BlockResponse;
import com.appcare.followconnect.MyviewPostdisplay.bean.BlockerInputs;
import com.appcare.followconnect.MyviewPostdisplay.bean.DeleteFeedInputs;
import com.appcare.followconnect.MyviewPostdisplay.bean.DeleteResponse;
import com.appcare.followconnect.MyviewPostdisplay.bean.GetPostFeedBean;
import com.appcare.followconnect.MyviewPostdisplay.bean.GetPostFeedResponse;
import com.appcare.followconnect.MyviewPostdisplay.bean.GetPostRequestBean;
import com.appcare.followconnect.Network.APIResponse;
import com.appcare.followconnect.R;
import com.appcare.followconnect.UploadPost.UploadPostActivity;
import com.appcare.followconnect.View.ViewBeanRequest;
import com.appcare.followconnect.View.ViewBeanResponse;
import com.appcare.followconnect.editfeed.EditFeedActivity;

import java.util.ArrayList;
import java.util.List;

public class MyviewFragment extends Fragment implements APIResponse {

    RecyclerView myview_rv = null;
    MyviewAdapter myviewAdapter = null;
    ImageView upload_img = null;
    MyviewPresenter presenter = null;
    ProgressDialog progressDialog = null;
    List<GetPostFeedBean> myViewList = new ArrayList<>();
    String toid = "";

    private String userid = "", api_TAG = "";
    int itemPosition = 0;
    GetPostFeedResponse feedbean = new GetPostFeedResponse();

    public MyviewFragment() {
    }

    public static MyviewFragment newInstance(String param1, String param2) {
        MyviewFragment fragment = new MyviewFragment();
        Bundle args = new Bundle();


        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_myview, container, false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();

        userid = AppPreference.getInstance(getActivity()).getString(Constants.User_ID);
        myview_rv = view.findViewById(R.id.myview_rv);
        upload_img = view.findViewById(R.id.upload_img);

        myview_rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        upload_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), UploadPostActivity.class));
            }
        });


        IntializeObjetcs();
    }

    private void setadapter(List<GetPostFeedBean> feedList) {
        myViewList = feedList;
        myviewAdapter = new MyviewAdapter(getActivity(), myViewList, this, userid);
        myview_rv.setAdapter(myviewAdapter);
    }

    private void IntializeObjetcs() {
        presenter = new MyviewPresenter(getActivity(), this);

        GetPostRequestBean bean = new GetPostRequestBean();
        bean.setUid(userid);
        presenter.getFeedList(bean);
    }

    @Override
    public void onSuccess(Object object) {
        GetPostFeedResponse bean = (GetPostFeedResponse) object;
        //Constants.displayLongToast(getActivity(), bean.getMessage());
        setadapter(bean.getData());
    }

    @Override
    public void onServerError(String error) {
        dismissProgress();
        Constants.displayLongToast(getActivity(), error);
    }


    @Override
    public void showProgress() {
        progressDialog = Constants.showProgressDialog(getActivity(), "");
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
        Constants.displayLongToast(getActivity(), error.toString());
    }


    public void likes(int position, String feedid, String postid, int count, int likeStatus) {
        FeedLikeInputs inputs = new FeedLikeInputs();
        inputs.setFeed_id(feedid);
        inputs.setPoster_id(postid);
        inputs.setCommenter_id(userid);
        inputs.setComment("");
        inputs.setLike("1");
        inputs.setDislike("");
        inputs.setShare("");
        inputs.setView("");
        api_TAG = "Likes";
        int countvalu;
        if (likeStatus == 0) {
            likeStatus = 1;
            countvalu = count + 1;
        } else {
            likeStatus = 0;
            countvalu = count - 1;
        }
        postLikes(inputs, position, api_TAG, countvalu, likeStatus);
    }

    private void postLikes(FeedLikeInputs inputs, int position, String likes, int countvalu, int likeStatus) {
        presenter.postLikes(inputs, new ResponseSucessCallback() {
            @Override
            public void responseSucess(Object object) {
                FeedLikeResponse feedLikeResponse = (FeedLikeResponse) object;
                Toast.makeText(getActivity(), "" + feedLikeResponse.getMessage(), Toast.LENGTH_SHORT).show();
                if (api_TAG.equals("Likes")) {

                    myViewList.get(position).setLikesCount(countvalu);
                    myViewList.get(position).setLikes(likeStatus);
                    myviewAdapter.notifyDataSetChanged();
                } else if (api_TAG.equals("DisLikes")) {
                    myViewList.get(position).setLikesCount(countvalu);
                    myViewList.get(position).setLikes(likeStatus);
                    myviewAdapter.notifyDataSetChanged();
                }
            }
        });

    }


    public void disLikes(int position, String feedid, String postid, int count, int likeStatus) {
        FeedLikeInputs inputs = new FeedLikeInputs();
        inputs.setFeed_id(feedid);
        inputs.setPoster_id(postid);
        inputs.setCommenter_id(userid);
        inputs.setComment("");
        inputs.setLike("0");
        inputs.setDislike("");
        inputs.setShare("");
        inputs.setView("");
        api_TAG = "DisLikes";
        int countvalu = count - 1;
        postLikes(inputs, position, api_TAG, countvalu, 0);
    }

    public void whatsAppShare(String fileuri, String sid, String feed) {

        String imageurl = fileuri;
        String postid = sid;
        try {
            //   Uri uri = Uri.parse(downloadImage(false));
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            String shareMessage = feed;
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            //    shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
            //  shareIntent.setType("image/jpeg");
            shareIntent.setType("text/plain");
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(Intent.createChooser(shareIntent, "Share via"));
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Something went wrong. Try again.", Toast.LENGTH_SHORT).show();

        }

    }

    public void blockuser(String blockerId) {
        BlockerInputs inputs = new BlockerInputs();

        inputs.setBlock_id(blockerId);
        inputs.setUser_id(userid);

        block(inputs);
    }

    private void block(BlockerInputs inputs) {
        presenter.block(inputs, new ResponseSucessCallback() {
            @Override
            public void responseSucess(Object object) {
                BlockResponse blockResponse = (BlockResponse) object;
                Toast.makeText(getActivity(), "" + blockResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void deleteFeed(String feedid, int position) {
        DeleteFeedInputs inputs = new DeleteFeedInputs();

        inputs.setUid(userid);
        inputs.setFeed_id(feedid);


        presenter.deleteFeed(inputs, new ResponseSucessCallback() {
            @Override
            public void responseSucess(Object object) {
                DeleteResponse deleteResponse = (DeleteResponse) object;
                Toast.makeText(getActivity(), "" + deleteResponse.getMessage(), Toast.LENGTH_SHORT).show();
                myViewList.remove(position);
                myviewAdapter.notifyDataSetChanged();
            }
        });
    }


    public void view(String feedid, int position) {
        ViewBeanRequest inputs = new ViewBeanRequest();

        inputs.setUid(userid);
        inputs.setFeedId(feedid);

        presenter.viewpost(inputs, new ResponseSucessCallback() {
            @Override
            public void responseSucess(Object object) {
                ViewBeanResponse viewBeanResponse = (ViewBeanResponse) object;

            }
        });
    }


    public void commentsClick(String feedid, String postid, int count, int position) {
        itemPosition = position;
        Intent i = new Intent(getActivity(), CommentsActivity.class);
        i.putExtra("FEEDID", feedid);
        i.putExtra("POSTID", postid);
        startActivity(i);
    }

    public void edit(String feedId,String url) {
        Intent i = new Intent(getActivity(), UploadPostActivity.class);
        i.putExtra("FEEDID", feedId);
        i.putExtra("commingfrom", "editfeed");
        i.putExtra("Imageurls",url);
        startActivity(i);
    }


}