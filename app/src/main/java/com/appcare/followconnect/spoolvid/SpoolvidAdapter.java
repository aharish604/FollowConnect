package com.appcare.followconnect.spoolvid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.appcare.followconnect.Home.Adapter.MyviewAdapter;
import com.appcare.followconnect.Home.fragments.SpooLvidFragment;
import com.appcare.followconnect.MyviewPostdisplay.bean.GetPostFeedBean;
import com.appcare.followconnect.R;
import com.appcare.followconnect.SignUp.CountrySpinner.Adapterpositioncallback;
import com.appcare.followconnect.spoolvid.Bean.SpoolvidResponseBean1;
import com.appcare.followconnect.spoolvid.Bean.SpoolvidResponseBeanfeedlist;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SpoolvidAdapter extends RecyclerView.Adapter<SpoolvidAdapter.viewHolder>  {


    Context mContext=null;
    Adapterpositioncallback adapterpositioncallback=null;
    ArrayList<SpoolvidResponseBean1> data=null;

    public SpoolvidAdapter(Context activity, ArrayList<SpoolvidResponseBean1> data) {
        mContext = activity;
        this.data=data;
    }

    public void adapterPosition(Adapterpositioncallback adapterpositioncallback) {
        this.adapterpositioncallback=adapterpositioncallback;

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_spoolvid ,parent, false);
        return new SpoolvidAdapter.viewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        SpoolvidResponseBean1 bean=data.get(position);

        SpoolvidResponseBeanfeedlist list=bean.getFeedList();


                Glide.with(mContext)
                .load("http://13.126.39.225/socialmedia/uploads/feed/"+list.getVfThumb())
                .into(holder.img_thumblain);
        Glide.with(mContext)
                .load(bean.getProfilePic())
                .placeholder(R.drawable.ic_baseline_account_circle_24)
                .into(holder.profile_image);




        holder.videoplaybtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        adapterpositioncallback.getadapterposition(bean,position);
                    }
                });

                holder.tv_posttime.setText(""+bean.getCd());
                holder.tv_likecount.setText(""+bean.getLikesCount());
                holder.tv_dislikecount.setText(""+bean.getDislikeCount());
                holder.tv_commentscount.setText(""+bean.getCommentsCount());
                holder.profilename_tv.setText(""+bean.getFullname());
                holder.tv_viewcounts.setText(""+bean.getViews());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView img_thumblain;
        ImageButton videoplaybtn;
        TextView tv_likecount=null;
        TextView tv_dislikecount=null;
        TextView tv_posttime=null;
        TextView tv_commentscount=null;
        TextView tv_viewcounts=null;
        TextView profilename_tv=null;

        CircleImageView profile_image=null;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            img_thumblain=itemView.findViewById(R.id.img_thumblain);
            videoplaybtn=itemView.findViewById(R.id.videoplaybtn);
            tv_likecount=itemView.findViewById(R.id.tv_likecount);
            tv_dislikecount=itemView.findViewById(R.id.tv_dislikecount);
            tv_posttime=itemView.findViewById(R.id.tv_posttime);
            tv_commentscount=itemView.findViewById(R.id.tv_commentscount);
            tv_viewcounts=itemView.findViewById(R.id.tv_viewcounts);
            profile_image=itemView.findViewById(R.id.profile_image);
            profilename_tv=itemView.findViewById(R.id.profilename_tv);
        }
    }
}
