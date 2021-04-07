package com.appcare.followconnect.Home.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appcare.followconnect.Chat.ResponseSucessCallback;
import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.InToTo.IntotoAdapter;
import com.appcare.followconnect.InToTo.IntotoPresenter;
import com.appcare.followconnect.InToTo.Bean.IntotoRequestBean;
import com.appcare.followconnect.InToTo.Bean.IntotoResponse;
import com.appcare.followconnect.Network.APIResponse;
import com.appcare.followconnect.R;

public class IntotoFragment extends Fragment implements APIResponse {


     IntotoPresenter presenter=null;
     ProgressDialog progressDialog=null;
     RecyclerView recyclerview_intoto=null;
     IntotoAdapter adapter=null;


    public IntotoFragment() {
    }

    public static IntotoFragment newInstance(String param1, String param2) {
        IntotoFragment fragment = new IntotoFragment();
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



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.intoto_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        InitUI(view);
        IntializeObjects();

    }

    private void IntializeObjects() {

        presenter=new IntotoPresenter(getActivity(),this);

        IntotoRequestBean bean=new IntotoRequestBean();
        bean.setUid(Constants.getUid(getActivity()));

        presenter.getIntotoList(bean, new ResponseSucessCallback() {
            @Override
            public void responseSucess(Object object) {

                IntotoResponse bean= (IntotoResponse) object;

                Constants.displayLongToast(getActivity(),""+bean.getData().size());

                adapter=new IntotoAdapter(getActivity(),bean.getData());

                recyclerview_intoto.setAdapter(adapter);

            }
        });

    }

    private void InitUI(View view) {

        recyclerview_intoto= view.findViewById(R.id.recyclerview_intoto);
        recyclerview_intoto.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onSuccess(Object object) {

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
}
