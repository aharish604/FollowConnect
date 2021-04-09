package com.appcare.followconnect.Comments.adapter;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appcare.followconnect.Comments.model.CommentsListResponse;
import com.appcare.followconnect.R;
import com.appcare.followconnect.SignUp.CountrySpinner.Adapterpositioncallback;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class CommentsAdaper extends RecyclerView.Adapter<CommentsAdaper.viewHolder> {

    private Context mContext;
    ArrayList<CommentsListResponse.CommentData> list = null;
    private String searchText = "";
    private SpannableStringBuilder sb;
    Adapterpositioncallback adapterpositioncallback;

    public CommentsAdaper(Context activity, ArrayList<CommentsListResponse.CommentData> list) {
        mContext = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.comments_item, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {


        CommentsListResponse.CommentData bean = list.get(position);

        holder.profilename_tv.setText(bean.getFullname());
        holder.tv_desc.setText(bean.getUserDetails().getComment());
        holder.tv_TimeStamp.setText(bean.getCommenttime());


        Glide.with(mContext)
                .load(bean.getProfile_pic())
                .placeholder(R.drawable.update_profile)
                .into(holder.profile_image);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        CircleImageView profile_image = null;
        TextView profilename_tv = null;
        TextView tv_TimeStamp = null;
        TextView tv_desc = null;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            profile_image = itemView.findViewById(R.id.profile_image);
            profilename_tv = itemView.findViewById(R.id.profilename_tv);
            tv_TimeStamp = itemView.findViewById(R.id.tv_TimeStamp);
            tv_desc = itemView.findViewById(R.id.tv_desc);


        }
    }
}
