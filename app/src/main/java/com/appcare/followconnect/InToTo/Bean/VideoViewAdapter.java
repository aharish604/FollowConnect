package com.appcare.followconnect.InToTo.Bean;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.appcare.followconnect.InToTo.ImageViewAdapter;
import com.appcare.followconnect.R;
import com.appcare.followconnect.spoolvid.SpoolvidVideoPLayingActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class VideoViewAdapter extends RecyclerView.Adapter<VideoViewAdapter.viewHolder> {

    private Context mContext;
    //  private ArrayList<IntotoResponseBean1> list;

    ArrayList<intotopublic> data = null;


    public VideoViewAdapter(FragmentActivity activity, ArrayList<intotopublic> data) {
        mContext = activity;
        this.data = data;
    }

    @NonNull
    @Override
    public VideoViewAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_videoview, parent, false);
        return new VideoViewAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewAdapter.viewHolder holder, int position) {

        intotopublic bean = data.get(position);

        Glide.with(mContext)
                .load(bean.getVfThumb())
                .into(holder.img_thumblain);
        holder.videoplaybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, SpoolvidVideoPLayingActivity.class);
                intent.putExtra("videourl",bean.getVf());
               mContext.startActivity(intent);
            }
        });




    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView img_thumblain,videoplaybtn;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            img_thumblain = itemView.findViewById(R.id.img_thumblain);
            videoplaybtn = itemView.findViewById(R.id.videoplaybtn);


        }
    }
}
