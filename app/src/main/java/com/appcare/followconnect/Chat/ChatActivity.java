package com.appcare.followconnect.Chat;

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
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.appcare.followconnect.Chat.Bean.CharBeanRequest;
import com.appcare.followconnect.Chat.Bean.ChatHistoryAdapter;
import com.appcare.followconnect.Chat.Bean.ChatHistoryBeanRequest;
import com.appcare.followconnect.Chat.Bean.ChatHistoryBeanResponse;
import com.appcare.followconnect.Chat.Bean.ChatHistoryBeanResponse1;
import com.appcare.followconnect.Chat.Bean.ChatListBeanResponse;
import com.appcare.followconnect.Chat.Bean.ChatListBeanResponse1;
import com.appcare.followconnect.Chat.Bean.MessageSendRequest;
import com.appcare.followconnect.Common.AppPreference;
import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.Home.HomeActivity;
import com.appcare.followconnect.Network.APIResponse;
import com.appcare.followconnect.Profile.Bean.ProfileResponseBean1;
import com.appcare.followconnect.Profile.ProfileActivity;
import com.appcare.followconnect.R;
import com.appcare.followconnect.SignUp.CountrySpinner.Adapterpositioncallback;
import com.appcare.followconnect.SimpleRecyclerView.AdapterInterface;
import com.appcare.followconnect.SimpleRecyclerView.SimpleRecyclerViewAdapter;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatActivity extends AppCompatActivity implements  APIResponse {
    ChatListBeanResponse1 chatListBeanResponse1;

    CircleImageView profile_image = null;
    TextView profilename_tv = null;
    TextView tv_chatstatus = null;
    TextView tv_norecordsFound = null;
    ImageButton imgbtn_chat = null;
    ProgressDialog progressDialog = null;
    RecyclerView rv_chat = null;
    ImageView img_sendmsg = null;
    EditText et_msgview = null;
    SimpleRecyclerViewAdapter recyclerViewAdapter = null;
    ChatPresenter presenter = null;
    ChatHistoryAdapter adapter=null;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        getSupportActionBar().hide();
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));

        AppPreference.getInstance(ChatActivity.this);


        Intent intent = getIntent();
        if (intent != null) {
            if (intent.getSerializableExtra("chatbean") != null) {
                chatListBeanResponse1 = (ChatListBeanResponse1) intent.getSerializableExtra("chatbean");
            }
        }
        InitUI();
        initializeRecyclerView();
        GetChatHistory("first");
    }

    private void GetChatHistory(String commingfrom) {
        presenter = new ChatPresenter(ChatActivity.this, this);
        ChatHistoryBeanRequest charBeanRequest = new ChatHistoryBeanRequest();
        charBeanRequest.setFrom_id(chatListBeanResponse1.getFromId());
        charBeanRequest.setTo_id(chatListBeanResponse1.getToId());
        presenter.getChatHistory(charBeanRequest,commingfrom, new ResponseSucessCallback() {
            @Override
            public void responseSucess(Object object) {
                ChatHistoryBeanResponse chatHistoryBeanResponse = (ChatHistoryBeanResponse) object;
                ArrayList<ChatHistoryBeanResponse1> chatlist = chatHistoryBeanResponse.getData();

                        adapter=new ChatHistoryAdapter(ChatActivity.this,chatlist,chatListBeanResponse1.getProfilePic());
                        rv_chat.setAdapter(adapter);
                        rv_chat.smoothScrollToPosition(chatlist.size());
                       // adapter.notifyDataSetChanged();



            }
        });

    }

    private void InitUI() {
        profile_image = findViewById(R.id.profile_image);
        profilename_tv = findViewById(R.id.profilename_tv);
        tv_chatstatus = findViewById(R.id.tv_chatstatus);
        imgbtn_chat = findViewById(R.id.imgbtn_chat);
        rv_chat = findViewById(R.id.rv_chat);
        img_sendmsg = findViewById(R.id.img_sendmsg);
        et_msgview = findViewById(R.id.et_msgview);
        tv_norecordsFound = findViewById(R.id.tv_norecordsFound);


        if (!chatListBeanResponse1.getProfilePic().equalsIgnoreCase("")) {
            Glide.with(ChatActivity.this)
                    .load(chatListBeanResponse1.getProfilePic())
                    .placeholder(R.drawable.update_profile)
                    .into(profile_image);
        }
        profilename_tv.setText(chatListBeanResponse1.getFullname());

        imgbtn_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        img_sendmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MessageSendRequest bean = new MessageSendRequest();
                bean.setFrom_id(Constants.getUid(ChatActivity.this));
                bean.setTo_id(chatListBeanResponse1.getFromId());
                bean.setMessage("" + et_msgview.getText().toString());
                presenter.sendmsg(bean, new ResponseSucessCallback() {
                    @Override
                    public void responseSucess(Object object) {
                        et_msgview.setText("");
                        GetChatHistory("second");

                    }

                });
            }
        });

    }


    public void initializeRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_chat.setHasFixedSize(true);
        rv_chat.setLayoutManager(linearLayoutManager);
    }


    @Override
    public void onSuccess(Object object) {

    }

    @Override
    public void onServerError(String error) {
        dismissProgress();
        Constants.displayLongToast(ChatActivity.this, error);
    }


    @Override
    public void showProgress() {
        progressDialog = Constants.showProgressDialog(ChatActivity.this, "");
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
        Constants.displayLongToast(ChatActivity.this, error.toString());
    }


    public class ChatListViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout rl_tomsg, rl_frommsg;
        TextView tv_fromtimestamp;
        TextView tv_tomsg;
        TextView tv_frommsg;
        CircleImageView profile_imageto = null;
        CircleImageView profile_imagefrom = null;

        public ChatListViewHolder(View itemView) {
            super(itemView);
            rl_tomsg = itemView.findViewById(R.id.rl_tomsg);
            rl_frommsg = itemView.findViewById(R.id.rl_frommsg);
            //  tv_fromtimestamp = itemView.findViewById(R.id.tv_fromtimestamp);
            tv_tomsg = itemView.findViewById(R.id.tv_tomsg);
            tv_frommsg = itemView.findViewById(R.id.tv_frommsg);
            profile_imageto = itemView.findViewById(R.id.profile_imageto);
            profile_imagefrom = itemView.findViewById(R.id.profile_imagefrom);

        }
    }
}