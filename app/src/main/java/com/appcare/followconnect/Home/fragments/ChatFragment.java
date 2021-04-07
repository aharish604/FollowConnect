package com.appcare.followconnect.Home.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appcare.followconnect.Chat.Bean.CharBeanRequest;
import com.appcare.followconnect.Chat.Bean.ChatListBeanResponse;
import com.appcare.followconnect.Chat.Bean.ChatListBeanResponse1;
import com.appcare.followconnect.Chat.ChatActivity;
import com.appcare.followconnect.Chat.ChatListAdapter;
import com.appcare.followconnect.Chat.ChatPresenter;
import com.appcare.followconnect.Chat.ResponseSucessCallback;
import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.Network.APIResponse;
import com.appcare.followconnect.Profile.ProfileActivity;
import com.appcare.followconnect.R;
import com.appcare.followconnect.SignUp.CountrySpinner.Adapterpositioncallback;
import com.appcare.followconnect.SignUp.CountrySpinner.CountryBean;
import com.appcare.followconnect.UploadPost.UploadPostActivity;

import java.util.ArrayList;

public class ChatFragment extends Fragment implements APIResponse {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView chat_recyclerview = null;
    EditText et_searchchat = null;
    ChatListAdapter adapter = null;
    ProgressDialog progressDialog = null;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ChatPresenter presenter = null;

    public ChatFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ChatFragment newInstance(String param1, String param2) {
        ChatFragment fragment = new ChatFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.chat_fragment, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();

        chat_recyclerview = view.findViewById(R.id.chat_recyclerview);
        et_searchchat = view.findViewById(R.id.et_searchchat);

        InitializeObjects();

        chat_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    private void InitializeObjects() {
        presenter = new ChatPresenter(getActivity(), this);

        CharBeanRequest charBeanRequest = new CharBeanRequest();
        charBeanRequest.setUid(Constants.getUid(getActivity()));
        presenter.getChatList(charBeanRequest, new ResponseSucessCallback() {
            @Override
            public void responseSucess(Object object) {
                ChatListBeanResponse chatListBeanResponse = (ChatListBeanResponse) object;
                ArrayList<ChatListBeanResponse1> list = chatListBeanResponse.getData();

                if (list.size() != 0) {
                    chat_recyclerview.setAdapter(new ChatListAdapter(getActivity(), list, new Adapterpositioncallback() {
                        @Override
                        public void getadapterposition(Object object, int pos) {

                            ChatListBeanResponse1 bean=  (ChatListBeanResponse1) object;
                            Intent intent=new Intent(getActivity(),ChatActivity.class);
                            intent.putExtra("chatbean", bean);

                            startActivity(intent);


                        }
                    }));


                }

            }
        });
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
