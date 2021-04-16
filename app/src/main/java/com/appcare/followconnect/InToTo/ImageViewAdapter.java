package com.appcare.followconnect.InToTo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.appcare.followconnect.InToTo.Bean.IntotoResponseBean1;
import com.appcare.followconnect.InToTo.Bean.intotopublic;
import com.appcare.followconnect.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ImageViewAdapter extends RecyclerView.Adapter<ImageViewAdapter.viewHolder>  {

    private Context mContext;
  //  private ArrayList<IntotoResponseBean1> list;

    String[] data=null;


    public ImageViewAdapter(FragmentActivity activity, String[] data) {
        mContext = activity;
        this.data = data;
    }

    @NonNull
    @Override
    public ImageViewAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_imageview, parent, false);
        return new ImageViewAdapter.viewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewAdapter.viewHolder holder, int position) {

       // intotopublic bean=data.get(position);

        Glide.with(mContext)
                .load(data[position])
                .into(holder.ivChapter);

        holder.ivChapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, IntotoImageViewActivity.class);
                Bundle args = new Bundle();
                args.putString("imageurl",data[position]);
                i.putExtra("BUNDLE", args);
                mContext.startActivity(i);


            }
        });

    }

    @Override
    public int getItemCount() {

        if (data == null)

            return 0;

        else

            return data.length;

    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView ivChapter;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            ivChapter=itemView.findViewById(R.id.ivChapter);




        }
    }
}
