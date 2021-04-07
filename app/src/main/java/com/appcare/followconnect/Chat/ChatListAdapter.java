package com.appcare.followconnect.Chat;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appcare.followconnect.Chat.Bean.ChatListBeanResponse1;
import com.appcare.followconnect.Home.fragments.ChatFragment;
import com.appcare.followconnect.Notifications.NotificationAdapter;
import com.appcare.followconnect.R;
import com.appcare.followconnect.SearchFriends.Bean.SearchHistoryResponseBean1;
import com.appcare.followconnect.SignUp.CountrySpinner.Adapterpositioncallback;
import com.appcare.followconnect.SignUp.CountrySpinner.CountryBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.viewHolder> {


    private Context mContext;
    ArrayList<ChatListBeanResponse1>   list=null;
    private String searchText="";
    private SpannableStringBuilder sb;
    Adapterpositioncallback adapterpositioncallback;

    public ChatListAdapter(Context activity, ArrayList<ChatListBeanResponse1> list, Adapterpositioncallback adapterpositioncallback) {
        mContext=activity;
        this.list=list;
        this.adapterpositioncallback=adapterpositioncallback;

    }



    @NonNull
    @Override
    public ChatListAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item, parent, false);
        return new ChatListAdapter.viewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull ChatListAdapter.viewHolder holder, int position) {


        ChatListBeanResponse1 bean=list.get(position);

        holder.profilename_tv.setText(bean.getFullname());
        holder.tv_TimeStamp.setText(bean.getCd());
        holder.tv_chatdesc.setText(bean.getDescription());
        holder.rl_chatitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterpositioncallback.getadapterposition(bean,position);
            }
        });

        Glide.with(mContext)
                .load(bean.getProfilePic())
                .placeholder(R.drawable.update_profile)
                .into(holder.profile_image);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        CircleImageView profile_image=null;
        TextView profilename_tv=null;
        TextView tv_TimeStamp=null;
        TextView tv_chatdesc=null;
        RelativeLayout rl_chatitem=null;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            profile_image=itemView.findViewById(R.id.profile_image);
            profilename_tv=itemView.findViewById(R.id.profilename_tv);
            tv_TimeStamp=itemView.findViewById(R.id.tv_TimeStamp);
            tv_chatdesc=itemView.findViewById(R.id.tv_chatdesc);
            rl_chatitem=itemView.findViewById(R.id.rl_chatitem);


        }
    }
}
