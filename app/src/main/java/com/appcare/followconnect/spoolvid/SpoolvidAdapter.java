package com.appcare.followconnect.spoolvid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.appcare.followconnect.Common.Constants;
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
    SpooLvidFragment spooLvidFragment=null;

    public SpoolvidAdapter(Context activity, ArrayList<SpoolvidResponseBean1> data,SpooLvidFragment spooLvidFragment) {
        mContext = activity;
        this.data=data;
        this.spooLvidFragment=spooLvidFragment;
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

        int likeStatus = bean.getLikes();
        int dislikeStatus = bean.getDislikeCount();

        if(likeStatus!=0)
        {
            if (likeStatus == 1) {
                holder.btn_imgLike.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_like_blue));
            } else {
                holder.btn_imgLike.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_like));
            }
        }else {
            if (dislikeStatus == 1) {
                holder.btn_imgdisLike.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_dislike_color));
            } else {
                holder.btn_imgdisLike.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_dislike));
            }
        }







        Glide.with(mContext)
                .load("http://13.126.39.225/socialmedia/uploads/feed/"+list.getVfThumb())
                .into(holder.img_thumblain);
        Glide.with(mContext)
                .load(bean.getProfilePic())
                .placeholder(R.drawable.ic_baseline_account_circle_24)
                .into(holder.profile_image);


        holder.btn_imgdisLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String feedid = list.getSid();
                String postid = list.getPuid();
                int count = bean.getDislikeCount();
                int likeStatus = bean.getDislike();

                spooLvidFragment.disLikes(position, feedid, postid, count, likeStatus);

            }
        });

        holder.btn_imgLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String feedid = list.getSid();
                String postid = list.getPuid();
                int count = bean.getLikesCount();

                int likeStatus = bean.getLikes();

                spooLvidFragment.likes(position, feedid, postid, count, likeStatus);
            }
        });




        holder.imgbtn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu menu = new PopupMenu(view.getContext(), holder.imgbtn_more);
                menu.getMenuInflater().inflate(R.menu.popup_menu, menu.getMenu());
                if (Constants.getUid(mContext).equals(list.getPuid())) {
                    menu.getMenu().removeItem(R.id.block_user);
                } else {
                    menu.getMenu().removeItem(R.id.edit);
                    menu.getMenu().removeItem(R.id.delete);
                }
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            // item.setVisible(false);

                            case R.id.share_via:

                                String url="";
                                String imgurl = list.getImgf();

                                String videourl=list.getVf();

                                if(imgurl.equalsIgnoreCase(""))
                                {
                                    url="http://13.126.39.225/socialmedia/uploads/feed/"+videourl;

                                }else {
                                    url=imgurl;
                                }

                                spooLvidFragment.whatsAppShare(url,list.getSid(),list.getFeed());
                                break;
                            case R.id.block_user:

                                spooLvidFragment.blockuser(bean.getUserId());
                                break;
                            case R.id.edit:
                                spooLvidFragment.edit(list.getSid(), list.getImgf(),list.getFeed());
                                break;
                            case R.id.delete:
                                spooLvidFragment.deleteFeed(list.getSid(), position);
                                break;
                        }
                        return true;
                    }
                });
                menu.show();

            }
        });


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
        ImageButton imgbtn_more=null;
        ImageButton btn_imgdisLike=null;
        ImageButton btn_imgLike=null;

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
            imgbtn_more=itemView.findViewById(R.id.imgbtn_more);
            btn_imgLike=itemView.findViewById(R.id.btn_imgLike);
            btn_imgdisLike=itemView.findViewById(R.id.btn_imgdisLike);
        }
    }
}
