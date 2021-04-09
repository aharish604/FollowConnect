package com.appcare.followconnect.Settings;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appcare.followconnect.Chat.ResponseSucessCallback;
import com.appcare.followconnect.Common.AppPreference;
import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.Login.LoginActivity;
import com.appcare.followconnect.Network.APIResponse;
import com.appcare.followconnect.Profile.FriendsList.CommonListActivity;
import com.appcare.followconnect.R;
import com.appcare.followconnect.Settings.DeleteAccount.AccountDeleteRequestBean;
import com.appcare.followconnect.Settings.DeleteAccount.AccountDeleteResponseBean;
import com.appcare.followconnect.Settings.PrivacyPolicy.PrivicyPolicyActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class SettingsActivity extends AppCompatActivity implements APIResponse, View.OnClickListener {

    SettingsPresenter presenter = null;
    ProgressDialog progressDialog = null;

    LinearLayout ll_passwordchnage = null;
    LinearLayout ll_helpandsupport = null;
    LinearLayout ll_blockerlist = null;
    LinearLayout ll_deleteaccount = null;
    LinearLayout ll_privacypolicy = null;
    ImageButton imgbtn_searchuserprofile = null;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getSupportActionBar().hide();
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));

        InitUI();

        InitializeObjects();


    }

    private void InitUI() {

        ll_passwordchnage = findViewById(R.id.ll_passwordchnage);
        ll_helpandsupport = findViewById(R.id.ll_helpandsupport);
        ll_blockerlist = findViewById(R.id.ll_blockerlist);
        ll_deleteaccount = findViewById(R.id.ll_deleteaccount);
        ll_privacypolicy = findViewById(R.id.ll_privacypolicy);
        imgbtn_searchuserprofile = findViewById(R.id.imgbtn_searchuserprofile);

        ll_passwordchnage.setOnClickListener(SettingsActivity.this);
        ll_helpandsupport.setOnClickListener(SettingsActivity.this);
        ll_blockerlist.setOnClickListener(SettingsActivity.this);
        ll_deleteaccount.setOnClickListener(SettingsActivity.this);
        ll_privacypolicy.setOnClickListener(SettingsActivity.this);
        imgbtn_searchuserprofile.setOnClickListener(SettingsActivity.this);


    }

    private void InitializeObjects() {
        presenter = new SettingsPresenter(SettingsActivity.this, SettingsActivity.this);

    }

    @Override
    public void onSuccess(Object object) {

    }

    @Override
    public void onServerError(String error) {
        dismissProgress();
        Constants.displayLongToast(SettingsActivity.this, error);
    }


    @Override
    public void showProgress() {
        progressDialog = Constants.showProgressDialog(SettingsActivity.this, "");
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
        Constants.displayLongToast(SettingsActivity.this, error.toString());
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ll_passwordchnage:


                startActivity(new Intent(SettingsActivity.this, PasswordChangeActivity.class));

                break;

            case R.id.ll_helpandsupport:

                openHelpandSupport();

                break;

            case R.id.ll_blockerlist:
                openBlockerList();

                break;


            case R.id.ll_deleteaccount:

                showAlertDeleteAccount();
                break;

            case R.id.ll_privacypolicy:

                openPrivacyPolicy();

                break;
            case R.id.imgbtn_searchuserprofile:


                finish();
                break;

        }


    }



    private void openBlockerList() {

        Intent i = new Intent(SettingsActivity.this, CommonListActivity.class);
        i.putExtra("commingfrom", "BlockerList");
        startActivity(i);

    }

    public void showAlertDeleteAccount() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View customLayout = getLayoutInflater().inflate(R.layout.layout_deleteaccount, null);


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

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDialog.dismiss();
            }
        });
        tv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Constants.isNetworkAvailable(SettingsActivity.this))
                {
                    mDialog.dismiss();

                    deleteAccoutn();


                }else {

                    Constants.displayLongToast(SettingsActivity.this,getResources().getString(R.string.check_network));

                }

            }
        });


    }

    private void deleteAccoutn() {


        AccountDeleteRequestBean bean=new AccountDeleteRequestBean();
        bean.setUid(Constants.getUid(SettingsActivity.this));

        presenter.deleteAccount(bean, new ResponseSucessCallback()
        {
            @Override
            public void responseSucess(Object object) {

                AccountDeleteResponseBean bean=(AccountDeleteResponseBean) object;

                Constants.displayLongToast(SettingsActivity.this,bean.getMessage());

                showProgress();

                signOut();

            }
        });

    }


    private void signOut() {
        FirebaseAuth mAuth=null;
        GoogleSignInClient mGoogleSignInClient=null;
        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(SettingsActivity.this, gso);
        mAuth.signOut();
        mGoogleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        AppPreference.getInstance(SettingsActivity.this).put(Constants.loginStatus,false);
                        AppPreference.getInstance(SettingsActivity.this).put(Constants.User_ID,"");

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent i = new Intent(SettingsActivity.this, LoginActivity.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(i);
                                finish();

                                dismissProgress();

                            }
                        },1000);




                    }
                });
    }



    private void openPrivacyPolicy() {

        Intent i = new Intent(SettingsActivity.this, PrivicyPolicyActivity.class);
        i.putExtra("commingfrom", "PrivacyPolicy");
        startActivity(i);

    }


    private void openHelpandSupport() {

        Intent i = new Intent(SettingsActivity.this, PrivicyPolicyActivity.class);
        i.putExtra("commingfrom", "HelpandSupport");
        startActivity(i);

    }
}