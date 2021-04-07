package com.appcare.followconnect.SearchFriends;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.appcare.followconnect.R;

public class SearchUserProfileActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton imgbtn_searchuserprofile=null;
    ImageButton option_Menu=null;
    TextView tv_username=null;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchuser_profile);

        getSupportActionBar().hide();
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));


        InitUI();

    }

    private void InitUI() {

        imgbtn_searchuserprofile=findViewById(R.id.imgbtn_searchuserprofile);
        option_Menu=findViewById(R.id.option_Menu);
        tv_username=findViewById(R.id.tv_username);

        imgbtn_searchuserprofile.setOnClickListener(this);
        option_Menu.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {

            case R.id.option_Menu:

                LayoutInflater inflater = (LayoutInflater) SearchUserProfileActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.optionmenu_usersearchprofile, null);
                final PopupWindow window = new PopupWindow(layout, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                window.setOutsideTouchable(true);
                window.showAtLocation(layout, Gravity.TOP | Gravity.END, 0, 0);
                LinearLayout ll_searchfriends = layout.findViewById(R.id.ll_searchfriends);
                LinearLayout ll_notification = layout.findViewById(R.id.ll_notification);



                break;

            case R.id.imgbtn_searchuserprofile:


                Intent i1 = new Intent(SearchUserProfileActivity.this, SearchFriendsActivity.class);
                i1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i1);
                finish();
                break;
        }
    }
}