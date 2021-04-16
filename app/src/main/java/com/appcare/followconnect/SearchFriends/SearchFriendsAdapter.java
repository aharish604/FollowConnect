package com.appcare.followconnect.SearchFriends;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.appcare.followconnect.ProfileUpdate.UpdateProfileActivity;
import com.appcare.followconnect.SearchFriends.Bean.SearchHistoryResponseBean1;
import com.appcare.followconnect.SignUp.CountrySpinner.Adapterpositioncallback;
import com.appcare.followconnect.SignUp.CountrySpinner.CountryBean;
import com.appcare.followconnect.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchFriendsAdapter extends RecyclerView.Adapter<SearchFriendsAdapter.viewHolder> {

    private Context mContext;
    private ArrayList<CountryBean> countryWiseModelArrayList =new ArrayList<>();
    private String searchText = "";
    private SpannableStringBuilder sb;
    Adapterpositioncallback adapterpositioncallback;
    ArrayList<SearchHistoryResponseBean1> list=null;

    public SearchFriendsAdapter(SearchFriendsActivity searchFriendsActivity, ArrayList<SearchHistoryResponseBean1> list) {
        mContext=searchFriendsActivity;
        this.list=list;
    }


    public void adapterPosition(Adapterpositioncallback adapterpositioncallback) {
        this.adapterpositioncallback=adapterpositioncallback;

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.search_friendsitem, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        SearchHistoryResponseBean1 bean=list.get(position);

        Glide.with(mContext)
                .load(bean.getProfilePic())
                .placeholder(R.drawable.ic_baseline_account_circle_24)
                .into(holder.profile_image);

        holder.tv_username.setText(""+bean.getFullname());


        holder.cd_searchhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterpositioncallback.getadapterposition(bean,position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        CardView cd_searchhistory=null;
        CircleImageView profile_image=null;
        TextView tv_username=null;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            cd_searchhistory=itemView.findViewById(R.id.cd_searchhistory);
            profile_image=itemView.findViewById(R.id.profile_image);
            tv_username=itemView.findViewById(R.id.tv_username);
        }
    }
}
