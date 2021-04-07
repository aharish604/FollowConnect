package com.appcare.followconnect.Chat.Bean;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appcare.followconnect.Chat.ChatActivity;
import com.appcare.followconnect.Chat.ChatListAdapter;
import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.R;
import com.appcare.followconnect.SignUp.CountrySpinner.Adapterpositioncallback;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatHistoryAdapter extends RecyclerView.Adapter<ChatHistoryAdapter.viewHolder> {

    private Context mContext;
    ArrayList<ChatHistoryBeanResponse1> list=null;
    private String searchText="";
    private String profilePic="";
    private SpannableStringBuilder sb;
    Adapterpositioncallback adapterpositioncallback;


    public ChatHistoryAdapter(Context activity, ArrayList<ChatHistoryBeanResponse1> list, String profilePic) {
        mContext=activity;
        this.list=list;
        this.profilePic=profilePic;

    }
    
    
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_chat, parent, false);
        return new ChatHistoryAdapter.viewHolder(view);       }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        ChatHistoryBeanResponse1 bean =list.get(position);

        if (Constants.getUid(mContext).equalsIgnoreCase(bean.getFromId())) {

            holder.rl_frommsg.setVisibility(View.VISIBLE);
            //  ((ChatListViewHolder) viewHolder).tv_fromtimestamp.setVisibility(View.VISIBLE);
            holder.rl_tomsg.setVisibility(View.GONE);

            holder.tv_frommsg.setText(bean.getDescription());

            Glide.with(mContext)
                    .load(bean.getProfilePic())
                    .centerCrop()
                    .into(holder.profile_imagefrom);

        } else {

            holder.rl_tomsg.setVisibility(View.VISIBLE);
            holder.rl_frommsg.setVisibility(View.GONE);
            //   ((ChatListViewHolder) viewHolder).tv_fromtimestamp.setVisibility(View.GONE);


            Glide.with(mContext)
                    .load(profilePic)
                    .centerCrop()
                    .into(holder.profile_imageto);


            holder.tv_tomsg.setText(bean.getDescription());


        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {


        RelativeLayout rl_tomsg, rl_frommsg;
        TextView tv_fromtimestamp;
        TextView tv_tomsg;
        TextView tv_frommsg;
        CircleImageView profile_imageto = null;
        CircleImageView profile_imagefrom = null;
        
        public viewHolder(@NonNull View itemView) {
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
