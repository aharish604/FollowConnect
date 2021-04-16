package com.appcare.followconnect.Login;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appcare.followconnect.Common.AppPreference;
import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.Common.Utility;
import com.appcare.followconnect.ForgotPassword.ForgotPasswordActivity;
import com.appcare.followconnect.LoginWithGoogle.Bean.LoginWithGoogleBeanRequest;
import com.appcare.followconnect.LoginWithGoogle.Bean.LoginWithGoogleBeanResponse;
import com.appcare.followconnect.LoginWithGoogle.Bean.LoginWithGoogleResponseBean1;
import com.appcare.followconnect.LoginWithGoogle.ProfileCallback;
import com.appcare.followconnect.Network.APIResponse;
import com.appcare.followconnect.R;
import com.appcare.followconnect.SignUp.SignUpActivity;
import com.appcare.followconnect.Home.HomeActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

import static com.appcare.followconnect.Common.Constants.RC_SIGN_IN;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, APIResponse {
    TextView tv_forgorPassword = null;
    TextView tv_signup = null;
    EditText et_email = null;
    EditText edt_password = null;
    ImageButton img_googlebtn = null;
    ImageView img_emailclear = null;
    ImageView eye_off = null;
    ImageView eye_visable = null;
    private FirebaseAuth mAuth;
    ProgressDialog progressDialog = null;
    private GoogleSignInClient mGoogleSignInClient;
    LoginPresenter presenter = null;
    String dvc_tkn = "";
    Button btn_sigin = null;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.WHITE));
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.WHITE));

        mAuth = FirebaseAuth.getInstance();
        dvc_tkn = FirebaseInstanceId.getInstance().getToken();
        System.out.println("Device Token:-"+dvc_tkn);
        AppPreference.getInstance().put(Constants.Device_Token, dvc_tkn);

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(LoginActivity.this, gso);

        initUI();
        IntializeObjetcs();

    }

    private void IntializeObjetcs() {

        presenter = new LoginPresenter(LoginActivity.this, LoginActivity.this);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void initUI() {

        tv_forgorPassword = findViewById(R.id.forgetpassword_tv);
        tv_signup = findViewById(R.id.tv_signup);
        et_email = findViewById(R.id.et_email);
        edt_password = findViewById(R.id.edt_password);
        img_emailclear = findViewById(R.id.img_emailclear);
        img_googlebtn = findViewById(R.id.img_googlebtn);
        eye_off = findViewById(R.id.eye_off);
        eye_visable = findViewById(R.id.eye_visable);
        btn_sigin = findViewById(R.id.btn_sigin);


        tv_forgorPassword.setOnClickListener(this);
        tv_signup.setOnClickListener(this);
        img_emailclear.setOnClickListener(this);
        img_googlebtn.setOnClickListener(this);
        eye_off.setOnClickListener(this);
        eye_visable.setOnClickListener(this);
        btn_sigin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.forgetpassword_tv:
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
                break;
            case R.id.tv_signup:
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                break;
            case R.id.img_emailclear:
                et_email.setText("");
                break;
            case R.id.img_googlebtn:
                if (Constants.isNetworkAvailable(LoginActivity.this)) {
                    signIn();
                } else {
                    Constants.displayLongToast(LoginActivity.this, LoginActivity.this.getResources().getString(R.string.check_network));
                }
                break;
            case R.id.eye_visable:
                edt_password.setTransformationMethod(null);
                eye_off.setVisibility(View.VISIBLE);
                eye_visable.setVisibility(View.GONE);
                break;
            case R.id.eye_off:
                edt_password.setTransformationMethod(new PasswordTransformationMethod());
                eye_visable.setVisibility(View.VISIBLE);
                eye_off.setVisibility(View.GONE);
                break;
            case R.id.btn_sigin:

                checkValidation();

                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            showProgress();
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                Constants.displayLongToast(LoginActivity.this, e.toString());
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            FirebaseUser user = mAuth.getCurrentUser();
                            LoginWithGoogleBeanRequest bean = new LoginWithGoogleBeanRequest();
                            bean.setEmail(user.getEmail());
                            bean.setDeviceid(user.getUid());
                            bean.setFullname(user.getDisplayName());
                            bean.setSocial_uid(user.getUid());
                            bean.setDevicetoken(dvc_tkn);
                            bean.setDevicetype("Android");

                            presenter.postloginwithemail(bean, new ProfileCallback() {
                                @Override
                                public void getProfileData(Object object) {
                                    LoginWithGoogleResponseBean1 bean = (LoginWithGoogleResponseBean1) object;
                                    ArrayList<LoginWithGoogleBeanResponse> list = bean.getData();

                                    Constants.displayLongToast(LoginActivity.this, bean.getMessage());

                                    if (list.size() != 0) {
                                        AppPreference.getInstance().put(Constants.User_ID, list.get(0).getUser_id());
                                        AppPreference.getInstance().put(Constants.ProfilrURL, list.get(0).getProfile_pic());
                                        AppPreference.getInstance().put(Constants.FullName, list.get(0).getFullname());
                                        AppPreference.getInstance().put(Constants.UserName, list.get(0).getUsername());
                                        AppPreference.getInstance().put(Constants.Email, list.get(0).getEmail());

                                        openHomeActivity();

                                    }


                                }


                            });


                        } else {
                            // If sign in fails, display a message to the use
                            Constants.displayLongToast(LoginActivity.this, task.getException().toString());
                        }
                    }
                });
    }

    @Override
    public void onSuccess(Object object) {
        LoginResponse bean = (LoginResponse) object;
        ArrayList<LoginResponseBean> list = bean.getData();

        Constants.displayLongToast(LoginActivity.this, bean.getMessage());

        if (list.size() != 0) {
            AppPreference.getInstance().put(Constants.User_ID, list.get(0).getUser_id());
            AppPreference.getInstance().put(Constants.ProfilrURL, list.get(0).getProfile_pic());
            AppPreference.getInstance().put(Constants.FullName, list.get(0).getFullname());
            AppPreference.getInstance().put(Constants.UserName, list.get(0).getUsername());
            AppPreference.getInstance().put(Constants.Email, list.get(0).getEmail());

            openHomeActivity();
        }

        openHomeActivity();
    }

    @Override
    public void onServerError(String error) {
        dismissProgress();
        Constants.displayLongToast(LoginActivity.this, error);

    }

    @Override
    public void showProgress() {
        progressDialog = Constants.showProgressDialog(LoginActivity.this, "");
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
        Constants.displayLongToast(LoginActivity.this, error);

    }

    private void checkValidation() {
        String email = et_email.getText().toString().trim();
        String password = edt_password.getText().toString().trim();

        if (email.isEmpty()) {
            Constants.displayLongToast(LoginActivity.this, getString(R.string.alert_entereamil));
        } else if (password.equalsIgnoreCase("")) {
            Constants.displayLongToast(LoginActivity.this, getString(R.string.alert_enterpassword));
        } else {
            LoginBean bean = new LoginBean();
            bean.setUsername(email);
            bean.setPassword(password);
            bean.setAPIKEY("");
            bean.setDevicetype("Android");
            bean.setDevicetoken(dvc_tkn);
            bean.setDeviceid("");
            presenter.doLogin(bean);
        }
    }

    private void openHomeActivity() {

        AppPreference.getInstance().put(Constants.loginStatus, true);

        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
    }
}