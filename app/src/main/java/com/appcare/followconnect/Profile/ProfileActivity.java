package com.appcare.followconnect.Profile;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.appcare.followconnect.Common.AppPreference;
import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.Home.HomeActivity;
import com.appcare.followconnect.Network.APIResponse;
import com.appcare.followconnect.Profile.Bean.ProfileBeanResponse;
import com.appcare.followconnect.Profile.Bean.ProfileResponseBean1;
import com.appcare.followconnect.ProfileUpdate.UpdateProfileActivity;
import com.appcare.followconnect.R;
import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity  implements APIResponse, View.OnClickListener {
    ProgressDialog progressDialog = null;
    Profilepresenter presenter=null;
    TextView tv_fullname=null;
    TextView tv_username=null;
    TextView tv_editprofile=null;
    CircleImageView profile_img=null;
    ImageButton imgbtn_searchuserprofile=null;
    ProfileResponseBean1 profileResponseBean1 =null;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().hide();
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));

        AppPreference.getInstance(ProfileActivity.this);
        InitUI();
        IntializeObjetcs();


    }

    private void InitUI() {

        tv_fullname=findViewById(R.id.tv_fullname);
        tv_editprofile=findViewById(R.id.tv_editprofile);
        tv_username=findViewById(R.id.tv_username);
        profile_img=findViewById(R.id.profile_background);
        imgbtn_searchuserprofile=findViewById(R.id.imgbtn_searchuserprofile);


        imgbtn_searchuserprofile.setOnClickListener(ProfileActivity.this);
        tv_editprofile.setOnClickListener(ProfileActivity.this);

    }


    private void IntializeObjetcs() {

        presenter = new Profilepresenter(ProfileActivity.this, ProfileActivity.this);
        presenter.getProfileData(AppPreference.getInstance().getString(Constants.User_ID));

    }

    @Override
    public void onSuccess(Object object) {
        ProfileBeanResponse bean = (ProfileBeanResponse) object;
          profileResponseBean1 = bean.getData().get(0);

        if (!bean.getMessage().equalsIgnoreCase("")) {
            Constants.displayLongToast(ProfileActivity.this, bean.getMessage());
        }

        tv_fullname.setText(""+ profileResponseBean1.getFullname());
        tv_username.setText(""+ profileResponseBean1.getUsername());


         AppPreference.getInstance().put(Constants.ProfilrURL, profileResponseBean1.getProfile_pic());


        Glide.with(ProfileActivity.this)
                .load(profileResponseBean1.getProfile_pic())
                .placeholder(R.drawable.update_profile)
               // .centerCrop()
                .into(profile_img);



    }

    @Override
    public void onServerError(String error) {
        dismissProgress();
        Constants.displayLongToast(ProfileActivity.this, error);
    }


    @Override
    public void showProgress() {
        progressDialog = Constants.showProgressDialog(ProfileActivity.this, "");
    }

    @Override
    public void dismissProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void networkError(String error) {
        dismissProgress();
        Constants.displayLongToast(ProfileActivity.this, error.toString());
    }


    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.imgbtn_searchuserprofile:
                openHomeActivity();
                break;
                case R.id.tv_editprofile:
                openeditProfileActivity();
                break;
        }

    }

    private void openeditProfileActivity() {

        Intent i = new Intent(ProfileActivity.this, UpdateProfileActivity.class);
        i.putExtra("profilebean", profileResponseBean1);
        startActivity(i);

    }

    private void openHomeActivity() {
        Intent i = new Intent(ProfileActivity.this, HomeActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }
}