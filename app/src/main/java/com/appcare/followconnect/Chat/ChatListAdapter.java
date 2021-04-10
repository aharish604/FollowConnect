package com.appcare.followconnect.Chat;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


    public void filterList(ArrayList<ChatListBeanResponse1> filteredList, String text) {
        list = filteredList;
        this.searchText = text;
        notifyDataSetChanged();
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

        String fullname=bean.getFullname();


        if(searchText.length()>0){
            //color your text here
            int index = fullname.indexOf(searchText);
            sb = new SpannableStringBuilder(fullname);
            Pattern word = Pattern.compile(searchText.toLowerCase());
            Matcher match = word.matcher(fullname.toLowerCase());
            while(match.find()){
                ForegroundColorSpan fcs = new ForegroundColorSpan(Color.rgb(153,153,255)); //specify color here
                sb.setSpan(fcs, match.start(), match.end(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                //index = stateName.indexOf(searchText,index+1);
            }
          //  holder.country_name.setText(sb);

        }else{
         //   holder.country_name.setText(fullname);
        }


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
