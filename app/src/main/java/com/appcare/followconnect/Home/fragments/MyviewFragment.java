package com.appcare.followconnect.Home.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appcare.followconnect.Home.Adapter.MyviewAdapter;
import com.appcare.followconnect.Common.AppPreference;
import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.MyviewPostdisplay.MyviewPresenter;
import com.appcare.followconnect.MyviewPostdisplay.bean.GetPostFeedBean;
import com.appcare.followconnect.MyviewPostdisplay.bean.GetPostFeedResponse;
import com.appcare.followconnect.MyviewPostdisplay.bean.GetPostRequestBean;
import com.appcare.followconnect.Network.APIResponse;
import com.appcare.followconnect.R;
import com.appcare.followconnect.UploadPost.UploadPostActivity;

import java.util.ArrayList;
import java.util.List;

import proj.me.bitframe.BeanImage;

public class MyviewFragment extends Fragment implements APIResponse {

    RecyclerView myview_rv = null;
    MyviewAdapter myviewAdapter = null;
    ImageView upload_img=null;
    MyviewPresenter presenter=null;
    ProgressDialog progressDialog = null;
    GetPostFeedResponse feedbean=new GetPostFeedResponse();

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
        myviewAdapter = new MyviewAdapter(getActivity(),feedList);
        myview_rv.setAdapter(myviewAdapter);
    }

    private void IntializeObjetcs() {
        presenter = new MyviewPresenter(getActivity(), this);
        String userid= AppPreference.getInstance(getActivity()).getString(Constants.User_ID);
        GetPostRequestBean bean=new GetPostRequestBean();
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

   /* public void initializeRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvClaims.setHasFixedSize(true);
        rvClaims.setLayoutManager(linearLayoutManager);
        recyclerViewAdapter = new SimpleRecyclerViewAdapter<>(getActivity(), R.layout.claims_item, this, rvClaims, no_record_found);
        rvClaims.setAdapter(recyclerViewAdapter);
    }


    @Override
    public void onItemClick(SimpleRecyclerViewAdapter var1, View var2, int var3) {

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup var1, int var2, View var3) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder var1, int var2, SimpleRecyclerViewAdapter var3) {

    }*/
}
