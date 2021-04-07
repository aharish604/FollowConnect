package com.appcare.followconnect.Login;

import android.app.Activity;
import android.content.Context;

import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.LoginWithGoogle.Bean.LoginWithGoogleBeanRequest;
import com.appcare.followconnect.LoginWithGoogle.Bean.LoginWithGoogleResponseBean1;
import com.appcare.followconnect.LoginWithGoogle.ProfileCallback;
import com.appcare.followconnect.Network.APIInterface;
import com.appcare.followconnect.Network.APIResponse;
import com.appcare.followconnect.Network.RequestClient;
import com.appcare.followconnect.R;


import retrofit2.Call;
import retrofit2.Callback;

public class LoginPresenter {

    Context mcontext;
    APIResponse apiResponse;
    public LoginPresenter(LoginActivity signUpActivity, APIResponse apiResponse) {
        this.mcontext = signUpActivity;
        this.apiResponse = apiResponse;
    }

    public void doLogin(LoginBean bean) {

        ((Activity) mcontext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                apiResponse.showProgress();
            }
        });

        try {
            if (Constants.isNetworkAvailable(mcontext)) {
                Call<LoginResponse> call = RequestClient.getClient().create(APIInterface.class).loginResponse(bean);
                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {
                        try {
                            LoginResponse loginResponse = response.body();
                            apiResponse.dismissProgress();
                            if (loginResponse.getStatus()) {
                                apiResponse.onSuccess(loginResponse);
                            } else {
                                apiResponse.onServerError(loginResponse.getMessage());
                            }

                        } catch (Exception e) {
                            apiResponse.dismissProgress();
                            apiResponse.onServerError(mcontext.getResources().getString(R.string.server_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        call.cancel();

                        ((Activity) mcontext).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                if (apiResponse != null) {
                                    apiResponse.dismissProgress();
                                }

                            }
                        });

                        apiResponse.onServerError(mcontext.getResources().getString(R.string.server_error));
                        System.out.println("login is 11===  " + t.getMessage());
                    }
                });
            } else {
                apiResponse.networkError(mcontext.getResources().getString(R.string.check_network));
            }
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse.onServerError(mcontext.getResources().getString(R.string.server_error));
        }


    }

    public void postloginwithemail(LoginWithGoogleBeanRequest bean, ProfileCallback profileCallback) {

        ((Activity) mcontext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
               // apiResponse.showProgress();
            }
        });

        try {
            if (Constants.isNetworkAvailable(mcontext)) {
                Call<LoginWithGoogleResponseBean1> call = RequestClient.getClient().create(APIInterface.class).loginwithResponse(bean);
                call.enqueue(new Callback<LoginWithGoogleResponseBean1>() {
                    @Override
                    public void onResponse(Call<LoginWithGoogleResponseBean1> call, retrofit2.Response<LoginWithGoogleResponseBean1> response) {
                        try {
                            LoginWithGoogleResponseBean1 loginResponse = response.body();
                            apiResponse.dismissProgress();
                            if (loginResponse.getStatus()) {

                                profileCallback.getProfileData(loginResponse);

                            } else {
                                apiResponse.onServerError(loginResponse.getMessage());
                            }

                        } catch (Exception e) {
                            apiResponse.dismissProgress();
                            apiResponse.onServerError(mcontext.getResources().getString(R.string.server_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginWithGoogleResponseBean1> call, Throwable t) {
                        call.cancel();

                        ((Activity) mcontext).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                if (apiResponse != null) {
                                    apiResponse.dismissProgress();
                                }

                            }
                        });

                        apiResponse.onServerError(mcontext.getResources().getString(R.string.server_error));
                        System.out.println("login is 11===  " + t.getMessage());
                    }
                });
            } else {
                apiResponse.networkError(mcontext.getResources().getString(R.string.check_network));
            }
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse.onServerError(mcontext.getResources().getString(R.string.server_error));
        }


    }
}
