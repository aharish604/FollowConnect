package com.appcare.followconnect.InToTo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appcare.followconnect.InToTo.Bean.IntotoResponseBean1;
import com.appcare.followconnect.InToTo.Bean.intotoImageDisplayAdapter;
import com.appcare.followconnect.R;

import java.util.ArrayList;

public class IntotoAdapter extends RecyclerView.Adapter<IntotoAdapter.viewHolder> {


    private Context mContext;
    private ArrayList<IntotoResponseBean1> list;


    public IntotoAdapter(FragmentActivity activity, ArrayList<IntotoResponseBean1> data) {
        mContext = activity;
        list = data;
    }

    @NonNull
    @Override
    public IntotoAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_intoto, parent, false);
        return new IntotoAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IntotoAdapter.viewHolder holder, int position) {
        IntotoResponseBean1 bean1 = list.get(position);


        holder.rv_horzontaltoto.setAdapter(new intotoImageDisplayAdapter(mContext, bean1.get_public(),bean1.get_private()));
        holder.rv_horzontaltoto.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        holder.rv_horzontaltoto.setHasFixedSize(true);

        holder.totodate_tv.setText("" + bean1.getDate());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView totodate_tv = null;
        RecyclerView rv_horzontaltoto = null;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            totodate_tv = itemView.findViewById(R.id.totodate_tv);
            rv_horzontaltoto = itemView.findViewById(R.id.rv_horzontaltoto);
        }
    }
}
