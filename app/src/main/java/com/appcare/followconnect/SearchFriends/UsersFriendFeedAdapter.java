package com.appcare.followconnect.SearchFriends;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.appcare.followconnect.MyviewPostdisplay.bean.FeedList;
import com.appcare.followconnect.MyviewPostdisplay.bean.GetPostFeedBean;
import com.appcare.followconnect.R;
import com.appcare.followconnect.SearchFriends.Bean.UserFriendsFeedResponse;
import com.appcare.followconnect.spoolvid.SpoolvidVideoPLayingActivity;
import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class UsersFriendFeedAdapter extends RecyclerView.Adapter<UsersFriendFeedAdapter.viewHolder> {

    private Context mContext;
    String[] imagesarray = null;

    List<UserFriendsFeedResponse.Datum> feedList = null;

    public UsersFriendFeedAdapter(FragmentActivity activity, List<UserFriendsFeedResponse.Datum> feedList) {
        mContext = activity;
        this.feedList = feedList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.userfriends_feed_row, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        UserFriendsFeedResponse.Datum bean = feedList.get(position);
        UserFriendsFeedResponse.FeedList feedListbean = bean.getFeedList();

        holder.profilename_tv.setText("" + bean.getFullname());
        holder.tv_posttime.setText("" + bean.getCd());
        holder.post_content.setText("" + feedListbean.getFeed());

        holder.tv_commentscount.setText(""+bean.getCommentsCount());
        holder.tv_likecount.setText(""+bean.getLikesCount());
        holder.tv_dislikecount.setText(""+bean.getDislikeCount());
        holder.tv_viewcounts.setText(""+bean.getViwes());



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
                    .load(feedListbean.getVfThumb())
                    //  .placeholder(R.drawable.img3)
                    .into(holder.img_thumblain  );
        }
        else {
            holder.video_layout.setVisibility(View.GONE);
            holder.btn_imgdisLike.setVisibility(View.GONE);
            holder.tv_dislikecount.setVisibility(View.GONE);


        }

        holder.videoplaybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, SpoolvidVideoPLayingActivity.class);
                intent.putExtra("videourl",bean.getVf());
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

           /* imageView.setPadding(0, 5, 0, 0);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setLayoutParams(new FrameLayout.LayoutParams(width, height));
            frameLayout.addView(imageView);

            Glide.with(mContext)
                    .load(bean.getVfFile())
                    .placeholder(R.drawable.img3)
                    .into(imageView);*/

        }




        if (i == 1 ) {
            // imageView.setImageResource(R.drawable.img3);
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
        if (i >= 4) {
            //x=0,y=0

           /* imageView.setVisibility(View.GONE);
            imageView21.setVisibility(View.GONE);
            imageView22.setVisibility(View.GONE);


            imageView.setImageResource(android.R.color.transparent);
            imageView21.setImageResource(android.R.color.transparent);
            imageView22.setImageResource(android.R.color.transparent);
            imageView31.setImageResource(android.R.color.transparent);
            imageView32.setImageResource(android.R.color.transparent);
            imageView33.setImageResource(android.R.color.transparent);*/


            //  imageView41.setImageResource(R.drawable.img3);
            imageView41.setPadding(0, 5, 0, 0);
            imageView41.setLayoutParams(new FrameLayout.LayoutParams(width, height / 2));
            imageView41.setScaleType(ImageView.ScaleType.FIT_XY);
            //x=200,y==0

            //  imageView42.setImageResource(R.drawable.img3);
            imageView42.setY(height / 2);
            imageView42.setPadding(0, 5, 5, 0);
            imageView42.setLayoutParams(new FrameLayout.LayoutParams(width / 3, height / 2));
            imageView42.setScaleType(ImageView.ScaleType.FIT_XY);


            //  imageView43.setImageResource(R.drawable.img3);
            imageView43.setX(width / 3);
            imageView43.setY(height / 2);
            imageView43.setPadding(0, 5, 5, 0);
            imageView43.setLayoutParams(new FrameLayout.LayoutParams(width / 3, height / 2));
            imageView43.setScaleType(ImageView.ScaleType.FIT_XY);


            //  imageView44.setImageResource(R.drawable.img3);
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
        ImageButton videoplaybtn,btn_imgdisLike;
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



        }
    }
}