package com.appcare.followconnect.Home.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.opengl.Visibility;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.appcare.followconnect.MyviewPostdisplay.bean.FeedList;
import com.appcare.followconnect.MyviewPostdisplay.bean.GetPostFeedBean;
import com.appcare.followconnect.MyviewPostdisplay.bean.GetPostFeedResponse;
import com.appcare.followconnect.R;
import com.appcare.followconnect.spoolvid.Bean.SpoolvidResponseBeanfeedlist;
import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import proj.me.bitframe.BeanImage;
import proj.me.bitframe.ViewFrame;
import proj.me.bitframe.helper.FrameType;

public class MyviewAdapter extends RecyclerView.Adapter<MyviewAdapter.viewHolder> {

    private Context mContext;
    String[] imagesarray = null;

    List<GetPostFeedBean> feedList = null;

    public MyviewAdapter(FragmentActivity activity, List<GetPostFeedBean> feedList) {
        mContext = activity;
        this.feedList = feedList;
    }

    @NonNull
    @Override
    public MyviewAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_myview, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewAdapter.viewHolder holder, int position) {
        GetPostFeedBean bean = feedList.get(position);
        FeedList feedListbean = bean.getFeedList();

        holder.profilename_tv.setText("" + bean.getFullname());
        holder.tv_posttime.setText("" + bean.getCd());
        holder.post_heading.setText("" + feedListbean.getFeed());

        if (!bean.getImgfFile().equalsIgnoreCase("")) {
            imagesarray = bean.getImgfFile().split(",");
        }

       /* SpoolvidResponseBeanfeedlist list=bean.getFeedList();


        Glide.with(mContext)
                .load("http://13.126.39.225/socialmedia/uploads/feed/"+list.getVfThumb())
                .placeholder(R.drawable.img_loading)
                .into(holder.img_thumblain);
*/

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
            i = imagesarray.length;
            System.out.println("ImageCount:-" + i);
        } else {
            holder.img_layout.setVisibility(View.GONE);

            imageView.setPadding(0, 5, 0, 0);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setLayoutParams(new FrameLayout.LayoutParams(width, height));
            frameLayout.addView(imageView);

            Glide.with(mContext)
                    .load(bean.getVfFile())
                    .placeholder(R.drawable.img3)
                    .into(imageView);

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

    public class viewHolder extends RecyclerView.ViewHolder {

        FrameLayout imgframe = null;
        ImageView img_1 = null;
        ImageView img_2 = null;
        ImageView img_3 = null;
        ImageView img_4 = null;
        CircleImageView profile_image = null;
        TextView profilename_tv = null;
        TextView tv_posttime = null;
        TextView post_heading = null;
        TextView post_content = null;
        RelativeLayout img_layout = null;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            // imgframe=itemView.findViewById(R.id.imgframe);
           /* img_1=itemView.findViewById(R.id.img_1);
            img_2=itemView.findViewById(R.id.img_2);
            img_3=itemView.findViewById(R.id.img_3);
            img_4=itemView.findViewById(R.id.img_4);
          */
            profile_image = itemView.findViewById(R.id.profile_image);
            profilename_tv = itemView.findViewById(R.id.profilename_tv);
            tv_posttime = itemView.findViewById(R.id.tv_posttime);
            post_heading = itemView.findViewById(R.id.post_heading);
            post_content = itemView.findViewById(R.id.post_content);
            img_layout = itemView.findViewById(R.id.img_layout);


        }
    }
}
