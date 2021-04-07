package com.appcare.followconnect.Notifications;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appcare.followconnect.Notifications.Bean.NotificationList;
import com.appcare.followconnect.Notifications.Bean.NotificationResponseBean1;
import com.appcare.followconnect.Profile.ProfileActivity;
import com.appcare.followconnect.R;
import com.appcare.followconnect.SignUp.CountrySpinner.CountryBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.viewHolder> {


    private Context mContext;
    ArrayList<NotificationResponseBean1> list;
    private String searchText="";
    private SpannableStringBuilder sb;

    public NotificationAdapter(Context activity, ArrayList<NotificationResponseBean1> list) {
        mContext=activity;
        this.list=list;
    }


    @NonNull
    @Override
    public NotificationAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.notification_item, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.viewHolder holder, int position) {

        NotificationResponseBean1 bean=list.get(position);

        Glide.with(mContext)
                .load(bean.getProfilePic())
                .placeholder(R.drawable.update_profile)
                .into(holder.profile_image);

        holder.profilename_tv.setText(""+bean.getFullname());

        NotificationList list=bean.getNotificationList();

        holder.tv_notificationdesc.setText(""+list.getNdetails());
        holder.tv_posttime.setText(""+bean.getCommenttime());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }




    public class viewHolder extends RecyclerView.ViewHolder {

        TextView tv_posttime;
        TextView profilename_tv;
        TextView tv_notificationdesc;
        CircleImageView profile_image;
        public viewHolder(@NonNull View itemView) {
            super(itemView);


            tv_posttime=itemView.findViewById(R.id.tv_TimeStamp);
            profile_image=itemView.findViewById(R.id.profile_image);
            profilename_tv=itemView.findViewById(R.id.profilename_tv);
            tv_notificationdesc=itemView.findViewById(R.id.tv_notificationdesc);

        }
    }
}
