package com.appcare.followconnect.Chat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.Home.Adapter.MyviewAdapter;
import com.appcare.followconnect.MyviewPostdisplay.ImageSliderActivity;
import com.appcare.followconnect.MyviewPostdisplay.bean.FeedList;
import com.appcare.followconnect.MyviewPostdisplay.bean.GetPostFeedBean;
import com.appcare.followconnect.Profile.Bean.UserfeedResponseBean1;
import com.appcare.followconnect.Profile.ProfileActivity;
import com.appcare.followconnect.R;
import com.appcare.followconnect.spoolvid.SpoolvidVideoPLayingActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileUserFeedAdapter extends RecyclerView.Adapter<ProfileUserFeedAdapter.viewHolder> {

    private Context mContext;
    String[] imagesarray = null;

    ArrayList<UserfeedResponseBean1> feedList = null;
    ProfileActivity activity=null;

    public ProfileUserFeedAdapter(Context context, ArrayList<UserfeedResponseBean1> feedList, ProfileActivity activity) {
        mContext = context;
        this.feedList = feedList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ProfileUserFeedAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_userfeedprofile, parent, false);
        return new ProfileUserFeedAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileUserFeedAdapter.viewHolder holder, int position) {

        UserfeedResponseBean1 bean = feedList.get(position);
        FeedList feedListbean = bean.getFeedList();

        holder.profilename_tv.setText("" + bean.getFullname());
        holder.tv_posttime.setText("" + bean.getCd());
        holder.post_content.setText("" + feedListbean.getFeed());

        holder.tv_commentscount.setText(""+bean.getCommentsCount());
        holder.tv_likecount.setText(""+bean.getLikesCount());
        holder.tv_dislikecount.setText(""+bean.getDislikeCount());
        holder.tv_viewcounts.setText(""+bean.getViwes());



        holder.img_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(mContext, ImageSliderActivity.class);
                intent.putExtra("imageurl",bean.getImgf());
                mContext.startActivity(intent);

            }
        });



        imagesarray = null;
        if (!bean.getImgf().equalsIgnoreCase("")) {
            imagesarray = bean.getImgf().split(",");
        }


        if(!feedListbean.getVfThumb().isEmpty())
        {
            holder.video_layout.setVisibility(View.VISIBLE);
            holder.btn_imgdisLike.setVisibility(View.VISIBLE);
            holder.tv_dislikecount.setVisibility(View.VISIBLE);

            Glide.with(mContext)
                    .load("http://13.126.39.225/socialmedia/uploads/feed/"+feedListbean.getVfThumb())
                    //  .placeholder(R.drawable.img3)
                    .into(holder.img_thumblain  );
        }
        else {
            holder.video_layout.setVisibility(View.GONE);
            holder.btn_imgdisLike.setVisibility(View.GONE);
            holder.tv_dislikecount.setVisibility(View.GONE);


        }

        int likeStatus = bean.getLikes();


        if(likeStatus == 1){
            holder.btn_imgLike.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_like_blue));
        }else{
            holder.btn_imgLike.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_like));
        }


        holder.btn_imgdisLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String feedid = feedListbean.getSid();
                String postid = feedListbean.getPuid();
                int count = bean.getLikesCount();
                int likeStatus = bean.getLikes();

                 activity.disLikes(position, feedid, postid, count, likeStatus);


            }
        });

        holder.btn_imgLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String feedid = feedListbean.getSid();
                String postid = feedListbean.getPuid();
                int count = bean.getLikesCount();

                int likeStatus = bean.getLikes();

                activity.likes(position, feedid, postid, count, likeStatus);
            }
        });




        holder.imgbtn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu menu = new PopupMenu(view.getContext(), holder.imgbtn_more);
                menu.getMenuInflater().inflate(R.menu.popup_menu, menu.getMenu());
                if(Constants.getUid(mContext).equals(feedListbean.getPuid())){
                    menu.getMenu().removeItem(R.id.block_user);
                }else{
                    menu.getMenu().removeItem(R.id.edit);
                    menu.getMenu().removeItem(R.id.delete);
                }
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            // item.setVisible(false);
                            case R.id.share_via:
                               // String fileuri = imagesarray[0];
                              //  Toast.makeText(mContext, ""+fileuri, Toast.LENGTH_SHORT).show();
                                String url="";
                                String imgurl = bean.getImgf();

                                String videourl=bean.getVf();

                                if(imgurl.equalsIgnoreCase(""))
                                {
                                    url=videourl;

                                }else {
                                    url=imgurl;
                                }


                               // activity.whatsAppShare(url,feedListbean.getSid(),feedListbean.getFeed());

                                activity.whatsAppShare(url, bean.getFeedList().getSid(), bean.getFeedList().getFeed());
                                break;
                            case R.id.block_user:
                                activity.blockuser(bean.getUserId());
                                break;
                            case R.id.edit:
                                activity.edit(feedListbean.getSid());
                                break;
                            case R.id.delete:
                                activity.deleteFeed(feedListbean.getSid(), position);
                                break;
                        }
                        return true;
                    }
                });
                menu.show();
            }

        });

        holder.btn_comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String feedid = feedListbean.getSid();
                String postid = feedListbean.getPuid();
                int count = bean.getLikesCount();
                activity.commentsClick(feedid, postid, count, position);
            }
        });



        holder.videoplaybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, SpoolvidVideoPLayingActivity.class);
                intent.putExtra("videourl",bean.getVf());
                intent.putExtra(Constants.ToolbarName,"Profile");

                mContext.startActivity(intent);
            }
        });



        Glide.with(mContext)
                .load(bean.getProfilePic())
                .placeholder(R.drawable.img_loading)

                .into(holder.profile_image);

        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int wid = size.x;
        int width = wid - 70;
        int height = 700;
        int sst = width - 20;
        Log.d("Width is", String.valueOf(sst));
        Log.d("Width is", String.valueOf(width));
        FrameLayout frameLayout = holder.itemView.findViewById(R.id.imgframe);

        ImageView imageView = new ImageView(mContext);
        ImageView imageView21 = new ImageView(mContext);
        ImageView imageView22 = new ImageView(mContext);
        ImageView imageView31 = new ImageView(mContext);
        ImageView imageView32 = new ImageView(mContext);
        ImageView imageView33 = new ImageView(mContext);
        ImageView imageView41 = new ImageView(mContext);
        ImageView imageView42 = new ImageView(mContext);
        ImageView imageView43 = new ImageView(mContext);
        ImageView imageView44 = new ImageView(mContext);
        TextView textView = new TextView(mContext);


        int i = 0;
        if (imagesarray != null) {
            holder.img_layout.setVisibility(View.VISIBLE);
            holder.video_layout.setVisibility(View.GONE);
            i = imagesarray.length;
        } else {
            holder.img_layout.setVisibility(View.GONE);

        }




        if (i == 1 ) {
            imageView.setPadding(0, 5, 0, 0);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setLayoutParams(new FrameLayout.LayoutParams(width, height));
            frameLayout.addView(imageView);

            Glide.with(mContext)
                    .load(imagesarray[0])
                    .placeholder(R.drawable.img3)
                    .into(imageView);
        }
        if (i == 2) {
            // imageView21.setImageResource(R.drawable.img3);
            imageView21.setPadding(0, 5, 0, 0);
            imageView21.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView21.setLayoutParams(new FrameLayout.LayoutParams(width,
                    height / 2));

            // imageView22.setImageResource(R.drawable.img3);
            imageView22.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView22.setLayoutParams(new FrameLayout.LayoutParams(width,
                    height / 2));
            imageView22.setY(height / 2);
            imageView22.setPadding(0, 5, 0, 0);

            frameLayout.removeAllViewsInLayout();

            frameLayout.addView(imageView21);
            frameLayout.addView(imageView22);


            Glide.with(mContext)
                    .load(imagesarray[0])
                    .placeholder(R.drawable.img3)
                    .into(imageView21);

            Glide.with(mContext)
                    .load(imagesarray[1])
                    .placeholder(R.drawable.img3)
                    .into(imageView22);

        }
        if (i == 3) {
            imageView31.setPadding(0, 5, 0, 0);
            imageView31.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView31.setLayoutParams(new FrameLayout.LayoutParams(width,
                    height / 2));

            imageView32.setY(height / 2);
            imageView32.setPadding(0, 5, 5, 0);
            imageView32.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView32.setLayoutParams(new FrameLayout.LayoutParams(width / 2,
                    height / 2));


            imageView33.setX(width / 2);
            imageView33.setY(height / 2);
            imageView33.setPadding(0, 5, 5, 0);
            imageView33.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView33.setLayoutParams(new FrameLayout.LayoutParams(width / 2,
                    height / 2));

            frameLayout.removeAllViewsInLayout();

            frameLayout.addView(imageView31);
            frameLayout.addView(imageView32);
            frameLayout.addView(imageView33);

            Glide.with(mContext)
                    .load(imagesarray[0])
                    .placeholder(R.drawable.img3)
                    .into(imageView31);

            Glide.with(mContext)
                    .load(imagesarray[1])
                    .placeholder(R.drawable.img3)
                    .into(imageView32);
            Glide.with(mContext)
                    .load(imagesarray[2])
                    .placeholder(R.drawable.img3)
                    .into(imageView33);
        }
        if (i == 4) {

            imageView41.setPadding(0, 5, 0, 0);
            imageView41.setLayoutParams(new FrameLayout.LayoutParams(width, height / 2));
            imageView41.setScaleType(ImageView.ScaleType.FIT_XY);

            imageView42.setY(height / 2);
            imageView42.setPadding(0, 5, 5, 0);
            imageView42.setLayoutParams(new FrameLayout.LayoutParams(width / 3, height / 2));
            imageView42.setScaleType(ImageView.ScaleType.FIT_XY);


            imageView43.setX(width / 3);
            imageView43.setY(height / 2);
            imageView43.setPadding(0, 5, 5, 0);
            imageView43.setLayoutParams(new FrameLayout.LayoutParams(width / 3, height / 2));
            imageView43.setScaleType(ImageView.ScaleType.FIT_XY);


            int w1 = width / 3;
            int w2 = width / 3;
            imageView44.setX(w1 + w2);
            imageView44.setY(height / 2);
            imageView44.setPadding(0, 5, 0, 0);
            imageView44.setLayoutParams(new FrameLayout.LayoutParams(width / 3, height / 2));
            imageView44.setScaleType(ImageView.ScaleType.FIT_XY);


            frameLayout.removeAllViewsInLayout();

            frameLayout.addView(imageView41);
            frameLayout.addView(imageView42);
            frameLayout.addView(imageView43);
            frameLayout.addView(imageView44);


            Glide.with(mContext)
                    .load(imagesarray[0])
                    //  .placeholder(R.drawable.img3)
                    .into(imageView41);

            Glide.with(mContext)
                    .load(imagesarray[1])
                    // .placeholder(R.drawable.img3)
                    .into(imageView42);
            Glide.with(mContext)
                    .load(imagesarray[2])
                    // .placeholder(R.drawable.img3)
                    .into(imageView43);

            Glide.with(mContext)
                    .load(imagesarray[3])
                    // .placeholder(R.drawable.img3)
                    .into(imageView44);


        }

        if (i > 4) {

            imageView41.setPadding(0, 5, 0, 0);
            imageView41.setLayoutParams(new FrameLayout.LayoutParams(width, height / 2));
            imageView41.setScaleType(ImageView.ScaleType.FIT_XY);

            imageView42.setY(height / 2);
            imageView42.setPadding(0, 5, 5, 0);
            imageView42.setLayoutParams(new FrameLayout.LayoutParams(width / 3, height / 2));
            imageView42.setScaleType(ImageView.ScaleType.FIT_XY);


            imageView43.setX(width / 3);
            imageView43.setY(height / 2);
            imageView43.setPadding(0, 5, 5, 0);
            imageView43.setLayoutParams(new FrameLayout.LayoutParams(width / 3, height / 2));
            imageView43.setScaleType(ImageView.ScaleType.FIT_XY);


            int w1 = width / 3;
            int w2 = width / 3;
            imageView44.setX(w1 + w2);
            imageView44.setY(height / 2);
            imageView44.setPadding(0, 5, 0, 0);
            imageView44.setLayoutParams(new FrameLayout.LayoutParams(width / 3, height / 2));
            imageView44.setScaleType(ImageView.ScaleType.FIT_XY);


            textView.setText("+" + (imagesarray.length - 4));
            textView.setX(w1 + w2);
            textView.setTextColor(Color.parseColor("#ffffff"));
            textView.setTypeface(null, Typeface.BOLD);
            textView.setY(height / 2);
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(20);
            textView.setLayoutParams(new FrameLayout.LayoutParams(width / 3, height / 2));

            frameLayout.removeAllViewsInLayout();

            frameLayout.addView(imageView41);
            frameLayout.addView(imageView42);
            frameLayout.addView(imageView43);
            frameLayout.addView(imageView44);
            frameLayout.addView(textView);


            Glide.with(mContext)
                    .load(imagesarray[0])
                    //  .placeholder(R.drawable.img3)
                    .into(imageView41);

            Glide.with(mContext)
                    .load(imagesarray[1])
                    // .placeholder(R.drawable.img3)
                    .into(imageView42);
            Glide.with(mContext)
                    .load(imagesarray[2])
                    // .placeholder(R.drawable.img3)
                    .into(imageView43);

            Glide.with(mContext)
                    .load(imagesarray[3])
                    // .placeholder(R.drawable.img3)
                    .into(imageView44);


        }



    }

    @Override
    public int getItemCount() {
        return feedList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView img_thumblain;
        CircleImageView profile_image = null;
        TextView profilename_tv = null;
        TextView tv_posttime = null,tv_likecount,tv_dislikecount,tv_viewcounts,tv_commentscount;
        ImageButton videoplaybtn,btn_imgdisLike,imgbtn_more,btn_comments,btn_imgLike;
        TextView post_content = null;
        RelativeLayout img_layout = null,video_layout;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            profile_image = itemView.findViewById(R.id.profile_image);
            profilename_tv = itemView.findViewById(R.id.profilename_tv);
            tv_posttime = itemView.findViewById(R.id.tv_posttime);
            post_content = itemView.findViewById(R.id.post_content);
            img_layout = itemView.findViewById(R.id.img_layout);
            video_layout = itemView.findViewById(R.id.video_layout);
            img_thumblain = itemView.findViewById(R.id.img_thumblain);
            videoplaybtn = itemView.findViewById(R.id.videoplaybtn);
            tv_likecount = itemView.findViewById(R.id.tv_likecount);
            tv_dislikecount = itemView.findViewById(R.id.tv_dislikecount);
            tv_viewcounts = itemView.findViewById(R.id.tv_viewcounts);
            tv_commentscount = itemView.findViewById(R.id.tv_commentscount);
            btn_imgdisLike = itemView.findViewById(R.id.btn_imgdisLike);
            imgbtn_more = itemView.findViewById(R.id.ic_more);

            btn_comments = itemView.findViewById(R.id.btn_comments);
            btn_imgLike = itemView.findViewById(R.id.btn_imgLike);




        }
    }
}
