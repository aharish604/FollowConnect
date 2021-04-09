package com.appcare.followconnect.Profile.FriendsList;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appcare.followconnect.Profile.FriendsList.Bean.FollowersResponseBean1;
import com.appcare.followconnect.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class FollowersListAdapter extends RecyclerView.Adapter<FollowersListAdapter.viewHolder> {


    private Context mContext;
    ArrayList<FollowersResponseBean1> list;
    private String searchText="";
    private SpannableStringBuilder sb;

    public FollowersListAdapter(ArrayList<FollowersResponseBean1> list, CommonListActivity activity) {
        mContext=activity;
        this.list=list;
    }


    @NonNull
    @Override
    public FollowersListAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.followerslist_item, parent, false);
        return new FollowersListAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FollowersListAdapter.viewHolder holder, int position) {

        FollowersResponseBean1 bean=list.get(position);

        Glide.with(mContext)
                .load(bean.getProfilePic())
                .placeholder(R.drawable.update_profile)
                .into(holder.profile_image);

        holder.profilename_tv.setText(""+bean.getFullname());

        holder.tv_username.setText(""+bean.getUsername());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }




    public class viewHolder extends RecyclerView.ViewHolder {

        TextView profilename_tv,tv_username;
        CircleImageView profile_image;
        public viewHolder(@NonNull View itemView) {
            super(itemView);


            profile_image=itemView.findViewById(R.id.profile_image);
            profilename_tv=itemView.findViewById(R.id.profilename_tv);
            tv_username=itemView.findViewById(R.id.tv_username);

        }
    }
}