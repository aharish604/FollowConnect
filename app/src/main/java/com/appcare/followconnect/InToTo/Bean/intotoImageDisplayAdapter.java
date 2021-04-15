package com.appcare.followconnect.InToTo.Bean;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.InToTo.ImageDisplayIntotoActivity;
import com.appcare.followconnect.InToTo.IntotoAdapter;
import com.appcare.followconnect.InToTo.VideodisplayIntotoActivity;
import com.appcare.followconnect.Profile.ProfileActivity;
import com.appcare.followconnect.ProfileUpdate.UpdateProfileActivity;
import com.appcare.followconnect.R;
import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.ArrayList;

public class intotoImageDisplayAdapter extends RecyclerView.Adapter<intotoImageDisplayAdapter.viewHolder> {

    private Context mContext;
    ArrayList<intotopublic> aPublic=new ArrayList<>();
    ArrayList<intotoprivate> aPrivate=new ArrayList<>();

    String imagesarray[];
    String videosarray[];
    ArrayList<intotopublic> list1=new ArrayList<>();
    ArrayList<intotopublic> list2=new ArrayList<>();


    public intotoImageDisplayAdapter(Context mContext, ArrayList<intotopublic> aPublic, ArrayList<intotoprivate> aPrivate) {

        this.mContext = mContext;
        this.aPublic = aPublic;
        this.aPrivate = aPrivate;
    }


    @NonNull
    @Override
    public intotoImageDisplayAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_imgdisplay, parent, false);
        return new intotoImageDisplayAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull intotoImageDisplayAdapter.viewHolder holder, int position) {

      //  intotopublic bean = aPublic.get(position);

        Boolean isPrivateImages = Constants.isPrivateImagesSpoolvid(aPrivate);
        Boolean isPrivateVideos = Constants.isPrivateVideosSpoolvid(aPrivate);

        if (isPrivateImages) {
            holder.img_private.setVisibility(View.VISIBLE);

        } else {
            holder.img_private.setVisibility(View.GONE);
        }

        if (isPrivateVideos) {
            holder.video_private.setVisibility(View.VISIBLE);

        } else {
            holder.video_private.setVisibility(View.GONE);


        }

        if(aPublic!=null)
        {
             list1= Constants.getImageArrayintoto(aPublic);
            list2 = Constants.getVideoArrayintoto(aPublic);

        }

        if(list1.size()>0)
        {
            holder.rl_imagedisplay.setVisibility(View.VISIBLE);

        }else {
            holder.rl_imagedisplay.setVisibility(View.GONE);

        }

        if(list2.size()>0)
        {
            holder.rl_videodisplay.setVisibility(View.VISIBLE);

        }else {
            holder.rl_videodisplay.setVisibility(View.GONE);

        }


        if(list1.size()!=0) {
            imagesarray = null;
            if (!list1.get(0).getImgf().equalsIgnoreCase("")) {
                imagesarray = list1.get(0).getImgf().split(",");
            }
        }

        if(list2.size()!=0) {
            videosarray = null;
            if (!list2.get(0).getVfThumb().equalsIgnoreCase("")) {
                videosarray = list2.get(0).getVfThumb().split(",");
            }
        }



        if(imagesarray!=null) {

            if (imagesarray.length >= 3) {
                holder.cardimg1.setVisibility(View.VISIBLE);
                holder.cardimg2.setVisibility(View.VISIBLE);
                holder.cardimg3.setVisibility(View.VISIBLE);
                Glide.with(mContext)
                        .load(imagesarray[imagesarray.length - 1])
                        .into(holder.ivChapter);

                Glide.with(mContext)
                        .load(imagesarray[imagesarray.length - 2])
                        .into(holder.ivChapter1);
                Glide.with(mContext)
                        .load(imagesarray[imagesarray.length - 3])
                        .into(holder.ivChapter2);
            } else if (imagesarray.length == 2) {
                holder.cardimg1.setVisibility(View.VISIBLE);
                holder.cardimg2.setVisibility(View.VISIBLE);
                holder.cardimg3.setVisibility(View.GONE);

                Glide.with(mContext)
                        .load(imagesarray[imagesarray.length - 1])
                        .into(holder.ivChapter);
                Glide.with(mContext)
                        .load(imagesarray[imagesarray.length - 2])
                        .into(holder.ivChapter1);
            } else if (imagesarray.length == 1) {
                holder.cardimg1.setVisibility(View.VISIBLE);
                holder.cardimg2.setVisibility(View.GONE);
                holder.cardimg3.setVisibility(View.GONE);
                Glide.with(mContext)
                        .load(imagesarray[imagesarray.length - 1])
                        .into(holder.ivChapter);
            } else {
                holder.cardimg1.setVisibility(View.GONE);
                holder.cardimg2.setVisibility(View.GONE);
                holder.cardimg3.setVisibility(View.GONE);
                holder.rl_imagedisplay.setVisibility(View.GONE);
            }

        }

        if(videosarray!=null) {

            if (videosarray.length >= 3) {
                holder.cardvid1.setVisibility(View.VISIBLE);
                holder.cardvid2.setVisibility(View.VISIBLE);
                holder.cardvid3.setVisibility(View.VISIBLE);
                Glide.with(mContext)
                        .load(videosarray[videosarray.length - 1])
                        .into(holder.img_thumblain);
                Glide.with(mContext)
                        .load(videosarray[videosarray.length - 2])
                        .into(holder.img_thumblain1);
                Glide.with(mContext)
                        .load(videosarray[videosarray.length - 3])
                        .into(holder.img_thumblain2);
            } else if (videosarray.length == 2) {
                holder.cardvid1.setVisibility(View.VISIBLE);
                holder.cardvid2.setVisibility(View.VISIBLE);
                holder.cardvid3.setVisibility(View.GONE);

                Glide.with(mContext)
                        .load(videosarray[videosarray.length - 1])
                        .into(holder.img_thumblain);
                Glide.with(mContext)
                        .load(videosarray[videosarray.length - 2])
                        .into(holder.img_thumblain1);
            } else if (videosarray.length == 1) {
                holder.cardvid1.setVisibility(View.VISIBLE);
                holder.cardvid2.setVisibility(View.GONE);
                holder.cardvid3.setVisibility(View.GONE);
                Glide.with(mContext)
                        .load(videosarray[videosarray.length - 1])
                        .into(holder.img_thumblain);
            } else {
                holder.cardvid1.setVisibility(View.GONE);
                holder.cardvid2.setVisibility(View.GONE);
                holder.cardvid3.setVisibility(View.GONE);
                holder.rl_videodisplay.setVisibility(View.GONE);
            }
        }

        holder.rl_imagedisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, ImageDisplayIntotoActivity.class);
                Bundle args = new Bundle();
                args.putStringArray("imagelistintoto",imagesarray);
              //  args.putSerializable("imagelistintoto", (Serializable) list1);
                i.putExtra("BUNDLE", args);
                mContext.startActivity(i);

            }
        });
        holder.videoplaybtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, VideodisplayIntotoActivity.class);
                Bundle args = new Bundle();
                args.putSerializable("imagelistintoto", (Serializable) list2);
                i.putExtra("BUNDLE", args);
                mContext.startActivity(i);

            }
        });

        holder.videoplaybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, VideodisplayIntotoActivity.class);
                Bundle args = new Bundle();
                args.putSerializable("imagelistintoto", (Serializable) list2);
                i.putExtra("BUNDLE", args);
                mContext.startActivity(i);

            }
        });

        holder.videoplaybtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, VideodisplayIntotoActivity.class);
                Bundle args = new Bundle();
                args.putSerializable("imagelistintoto", (Serializable) list2);
                i.putExtra("BUNDLE", args);
                mContext.startActivity(i);

            }
        });


        holder.img_private.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, ImageDisplayIntotoActivity.class);
                Bundle args = new Bundle();
                args.putStringArray("imagelistintoto",imagesarray);
                //  args.putSerializable("imagelistintoto", (Serializable) list1);
                i.putExtra("BUNDLE", args);
                mContext.startActivity(i);

            }
        });

        holder.video_private.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, VideodisplayIntotoActivity.class);
                Bundle args = new Bundle();
                args.putSerializable("imagelistintoto", (Serializable) list2);
                i.putExtra("BUNDLE", args);
                mContext.startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView ivChapter, ivChapter2, ivChapter1, img_thumblain, img_thumblain2, img_thumblain1, img_private, video_private;
        RelativeLayout img_layout, rl_imagedisplay, rl_videodisplay;
        CardView cardimg3, cardimg2, cardimg1, cardvid1, cardvid2, cardvid3;
        ImageButton videoplaybtn2, videoplaybtn1, videoplaybtn;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            ivChapter = itemView.findViewById(R.id.ivChapter);
            ivChapter2 = itemView.findViewById(R.id.ivChapter2);
            ivChapter1 = itemView.findViewById(R.id.ivChapter1);
            img_thumblain = itemView.findViewById(R.id.img_thumblain);
            img_thumblain2 = itemView.findViewById(R.id.img_thumblain2);
            img_thumblain1 = itemView.findViewById(R.id.img_thumblain1);
            img_layout = itemView.findViewById(R.id.img_layout);
            img_private = itemView.findViewById(R.id.img_private);
            video_private = itemView.findViewById(R.id.video_private);
            cardimg3 = itemView.findViewById(R.id.cardimg3);
            cardimg2 = itemView.findViewById(R.id.cardimg2);
            cardimg1 = itemView.findViewById(R.id.cardimg1);
            cardvid1 = itemView.findViewById(R.id.cardvid1);
            cardvid2 = itemView.findViewById(R.id.cardvid2);
            cardvid3 = itemView.findViewById(R.id.cardvid3);
            videoplaybtn2 = itemView.findViewById(R.id.videoplaybtn2);
            videoplaybtn1 = itemView.findViewById(R.id.videoplaybtn1);
            videoplaybtn = itemView.findViewById(R.id.videoplaybtn);


            rl_imagedisplay = itemView.findViewById(R.id.rl_imagedisplay);
            rl_videodisplay = itemView.findViewById(R.id.rl_videodisplay);
        }
    }

}
