package com.appcare.followconnect.SignUp.CountrySpinner;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.SignUp.SignUpActivity;
import com.appcare.followconnect.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<CountryBean> countryWiseModelArrayList;
    private String searchText="";
    private SpannableStringBuilder sb;
    Adapterpositioncallback adapterpositioncallback;
    BottomSheetDialog bsdialog;


    public CountryAdapter(ArrayList<CountryBean> counrylist, Activity signUpActivity, BottomSheetDialog bsdialog) {
        this.mContext = signUpActivity;
        this.countryWiseModelArrayList = counrylist;
        this.bsdialog=bsdialog;
    }

    public void filterList(ArrayList<CountryBean> filteredList, String text) {
        countryWiseModelArrayList = filteredList;
        this.searchText = text;
        notifyDataSetChanged();
    }

    public void adapterPosition(Adapterpositioncallback adapterpositioncallback) {
this.adapterpositioncallback=adapterpositioncallback;

    }


    @NonNull
    @Override
    public CountryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_country_wise, parent, false);
        return new MyViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.MyViewHolder holder, int position) {

        //  Glide.with(mContext).load(countryWiseModelArrayList.get(position).getFlag_url()).diskCacheStrategy(DiskCacheStrategy.DATA).into(holder.img_flag);

        String countryName=countryWiseModelArrayList.get(position).getName();
        Constants.fetchSvg(mContext, countryWiseModelArrayList.get(position).getFlag_url(), holder.img_flag);
        holder.country_name.setText(" "+countryWiseModelArrayList.get(position).getName());
        holder.callcode.setText("+"+countryWiseModelArrayList.get(position).getCallingcode());
        holder.ll_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterpositioncallback.getadapterposition(countryWiseModelArrayList.get(position),position);
                bsdialog.dismiss();
            }
        });

        if(searchText.length()>0){
            //color your text here
            int index = countryName.indexOf(searchText);
            sb = new SpannableStringBuilder(countryName);
            Pattern word = Pattern.compile(searchText.toLowerCase());
            Matcher match = word.matcher(countryName.toLowerCase());
            while(match.find()){
                ForegroundColorSpan fcs = new ForegroundColorSpan(Color.rgb(153,153,255)); //specify color here
                sb.setSpan(fcs, match.start(), match.end(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                //index = stateName.indexOf(searchText,index+1);
            }
            holder.country_name.setText(sb);

        }else{
            holder.country_name.setText(countryName);
        }


    }

    @Override
    public int getItemCount() {
        return countryWiseModelArrayList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView img_flag;
        TextView country_name;
        TextView callcode;
        LinearLayout ll_root;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img_flag=itemView.findViewById(R.id.countryflag);
            country_name=itemView.findViewById(R.id.countryname);
            callcode=itemView.findViewById(R.id.callcode);
            ll_root=itemView.findViewById(R.id.ll_root);

        }
    }
}
