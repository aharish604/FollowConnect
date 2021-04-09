package com.appcare.followconnect.Settings.BlockerList;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.Profile.FriendsList.CommonListActivity;
import com.appcare.followconnect.R;
import com.appcare.followconnect.SignUp.CountrySpinner.Adapterpositioncallback;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class BlockerListAdapter extends RecyclerView.Adapter<BlockerListAdapter.viewHolder> {


    private Context mContext;
    ArrayList<BlockerListResponseBean1> list;
    private String searchText="";
    private SpannableStringBuilder sb;
    Adapterpositioncallback callback=null;

    public BlockerListAdapter(ArrayList<BlockerListResponseBean1> list, CommonListActivity activity, Adapterpositioncallback adapterpositioncallback) {
        mContext=activity;
        this.list=list;
        this.callback=adapterpositioncallback;
    }


    @NonNull
    @Override
    public BlockerListAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.friendslist_item, parent, false);
        return new BlockerListAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BlockerListAdapter.viewHolder holder, int position) {

        BlockerListResponseBean1 bean=list.get(position);

        Glide.with(mContext)
                .load(bean.getProfilePic())
                .placeholder(R.drawable.update_profile)
                .into(holder.profile_image);


        holder.profilename_tv.setText(""+bean.getFullname());

        holder.tv_username.setText(""+bean.getUsername());

        holder.rl_chatitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showAlertDeleteAccount(bean,position);


            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void showAlertDeleteAccount(BlockerListResponseBean1 bean, int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

       final View customLayout = LayoutInflater.from(mContext).inflate(R.layout.layout_unblockuser, null);

        AlertDialog mDialog = builder.create();
        Window window=mDialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.setView(customLayout,110, 0, 110, 0);
        mDialog.setCanceledOnTouchOutside(false);//Clicking on the screen does not disappear
        mDialog.show();
        WindowManager.LayoutParams params =   mDialog.getWindow().getAttributes();
        mDialog.getWindow().setAttributes(params);

        mDialog.show();

        TextView tv_cancel=customLayout.findViewById(R.id.tv_cancel);
        TextView tv_logout=customLayout.findViewById(R.id.tv_logout);
        TextView alertmsg_tv=customLayout.findViewById(R.id.alertmsg_tv);

        alertmsg_tv.setText("Would you like to UnBlock "+bean.getUsername());

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDialog.dismiss();
            }
        });
        tv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Constants.isNetworkAvailable(mContext))
                {
                    mDialog.dismiss();
                    callback.getadapterposition(bean,position);


                }else {

                    Constants.displayLongToast(mContext,mContext.getResources().getString(R.string.check_network));

                }

            }
        });


    }



    public class viewHolder extends RecyclerView.ViewHolder {

        TextView tv_username;
        TextView profilename_tv;
        RelativeLayout rl_chatitem=null;
        CircleImageView profile_image;
        public viewHolder(@NonNull View itemView) {
            super(itemView);


            tv_username=itemView.findViewById(R.id.tv_username);
            profile_image=itemView.findViewById(R.id.profile_image);
            profilename_tv=itemView.findViewById(R.id.profilename_tv);
            rl_chatitem=itemView.findViewById(R.id.rl_chatitem);

        }
    }
}


