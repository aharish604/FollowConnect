package com.appcare.followconnect.Home.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

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
import com.appcare.followconnect.Profile.FriendsList.CommonListActivity;
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
    ProgressDialog progressDialog = null;
    ChatListAdapter adapter = null;
    String toid="";
    ArrayList<ChatListBeanResponse1> list = new ArrayList<>();

    ImageView fab_addchatlist=null;

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
        args.putString(param1,Constants.Toid);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            toid = getArguments().getString(Constants.Toid);
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
        fab_addchatlist = view.findViewById(R.id.fab_addchatlist);
        chat_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        et_searchchat = view.findViewById(R.id.et_searchchat);

        InitializeObjects();

        fab_addchatlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CommonListActivity.class);
                intent.putExtra("commingfrom", "FriendsList");
                startActivity(intent);            }
        });

        et_searchchat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(list.size()!=0)
                {
                    Filter(s.toString());

                }
            }
        });


    }

    private void InitializeObjects() {
        presenter = new ChatPresenter(getActivity(), this);

        CharBeanRequest charBeanRequest = new CharBeanRequest();
        charBeanRequest.setUid(Constants.getUid(getActivity()));
        presenter.getChatList(charBeanRequest, new ResponseSucessCallback() {
            @Override
            public void responseSucess(Object object) {
                ChatListBeanResponse chatListBeanResponse = (ChatListBeanResponse) object;
                list.clear();
                list = chatListBeanResponse.getData();
                if (list.size() != 0) {
                    adapter = new ChatListAdapter(getActivity(), list, new Adapterpositioncallback() {
                        @Override
                        public void getadapterposition(Object object, int pos) {
                            ChatListBeanResponse1 bean = (ChatListBeanResponse1) object;
                            Intent intent = new Intent(getActivity(), ChatActivity.class);
                            intent.putExtra("chatbean", bean);
                            startActivity(intent);
                        }
                    });
                    chat_recyclerview.setAdapter(adapter);


                }

            }
        });
    }

    private void Filter(String text) {
        ArrayList<ChatListBeanResponse1> filteredList = new ArrayList<>();
        for (ChatListBeanResponse1 item : list) {
            if (item.getFullname().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList, text);
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
