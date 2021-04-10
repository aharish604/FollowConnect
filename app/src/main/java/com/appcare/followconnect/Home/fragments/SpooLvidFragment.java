package com.appcare.followconnect.Home.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appcare.followconnect.Chat.Bean.ChatHistoryBeanResponse;
import com.appcare.followconnect.Chat.ResponseSucessCallback;
import com.appcare.followconnect.Common.AppPreference;
import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.Home.Adapter.MyviewAdapter;
import com.appcare.followconnect.MyviewPostdisplay.bean.GetPostFeedBean;
import com.appcare.followconnect.MyviewPostdisplay.bean.GetPostFeedResponse;
import com.appcare.followconnect.Network.APIInterface;
import com.appcare.followconnect.Network.APIResponse;
import com.appcare.followconnect.R;
import com.appcare.followconnect.SignUp.CountrySpinner.Adapterpositioncallback;
import com.appcare.followconnect.SignUp.CountrySpinner.CountryBean;
import com.appcare.followconnect.SignUp.SignUpActivity;
import com.appcare.followconnect.spoolvid.Bean.SpoolvidRequestBean;
import com.appcare.followconnect.spoolvid.Bean.SpoolvidResponseBean;
import com.appcare.followconnect.spoolvid.Bean.SpoolvidResponseBean1;
import com.appcare.followconnect.spoolvid.SpoolvidAdapter;
import com.appcare.followconnect.spoolvid.SpoolvidUploadPostActivity;
import com.appcare.followconnect.spoolvid.SpoolvidVideoPLayingActivity;
import com.appcare.followconnect.spoolvid.spoolvidPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.callback.Callback;

public class SpooLvidFragment extends Fragment implements APIResponse {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView spoolvid_rv = null;
    ImageButton upload_img = null;
    ProgressDialog progressDialog = null;
    SpoolvidAdapter adapter = null;
    spoolvidPresenter presenter = null;

    public SpooLvidFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SpooLvidFragment newInstance(String param1, String param2) {
        SpooLvidFragment fragment = new SpooLvidFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_spoolvid, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spoolvid_rv = view.findViewById(R.id.spoolvid_rv);
        upload_img = view.findViewById(R.id.upload_img);
        spoolvid_rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        AppPreference.getInstance(getActivity());

        getSpoolVid();

        upload_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SpoolvidUploadPostActivity.class));

            }
        });


    }

    private void getSpoolVid() {


        presenter = new spoolvidPresenter(getActivity(), this);


        SpoolvidRequestBean bean = new SpoolvidRequestBean();
        bean.setUid(Constants.getUid(getActivity()));

        presenter.getspoolvid(bean, new ResponseSucessCallback() {

            @Override
            public void responseSucess(Object object) {

                dismissProgress();

                SpoolvidResponseBean spoolvidResponseBean = (SpoolvidResponseBean) object;

                ArrayList<SpoolvidResponseBean1> data=spoolvidResponseBean.getData();

                setadapter(data);

            }
        });


    }


    public void getdapterPosition() {
        adapter.adapterPosition(new Adapterpositioncallback() {
            @Override
            public void getadapterposition(Object object, int pos) {
                SpoolvidResponseBean1 bean = (SpoolvidResponseBean1) object;

                Intent intent=new Intent(getActivity(), SpoolvidVideoPLayingActivity.class);
               intent.putExtra("videourl",bean.getVf());
                intent.putExtra(Constants.ToolbarName,"SpoolVid");

                startActivity(intent);

            }
        });

    }


    private void setadapter(ArrayList<SpoolvidResponseBean1> data) {

         adapter=new SpoolvidAdapter(getActivity(),data);
        spoolvid_rv.setAdapter(adapter);
        getdapterPosition();


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
