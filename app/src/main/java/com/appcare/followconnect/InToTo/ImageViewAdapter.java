package com.appcare.followconnect.InToTo;

import android.content.Context;
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

    ArrayList<intotopublic> data=null;


    public ImageViewAdapter(FragmentActivity activity, ArrayList<intotopublic> data) {
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

        intotopublic bean=data.get(position);

        Glide.with(mContext)
                .load(bean.getImgf())
                .into(holder.ivChapter);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView ivChapter;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            ivChapter=itemView.findViewById(R.id.ivChapter);




        }
    }
}
