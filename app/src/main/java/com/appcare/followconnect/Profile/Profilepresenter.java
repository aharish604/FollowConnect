package com.appcare.followconnect.Profile;

import android.app.Activity;
import android.content.Context;

import com.appcare.followconnect.Chat.ResponseSucessCallback;
import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.Network.APIInterface;
import com.appcare.followconnect.Network.APIResponse;
import com.appcare.followconnect.Network.RequestClient;
import com.appcare.followconnect.Profile.Bean.ProfileBeanRequest;
import com.appcare.followconnect.Profile.Bean.ProfileBeanResponse;
import com.appcare.followconnect.Profile.Bean.UserFeedRequest;
import com.appcare.followconnect.Profile.Bean.UserFeedResponseBean;
import com.appcare.followconnect.R;

import retrofit2.Call;
import retrofit2.Callback;

public class Profilepresenter {


    Context mcontext;
    APIResponse apiResponse;
    public Profilepresenter(ProfileActivity profileActivity, APIResponse apiResponse) {
        this.mcontext = profileActivity;
        this.apiResponse = apiResponse;
    }




    public void getProfileData(String userid) {

        ProfileBeanRequest profilebeanpostbean= new ProfileBeanRequest();
        profilebeanpostbean.setUser_id(userid);


        ((Activity) mcontext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                apiResponse.showProgress();
            }
        });

        try {
            if (Constants.isNetworkAvailable(mcontext)) {
                Call<ProfileBeanResponse> call = RequestClient.getClient().create(APIInterface.class).getProfileData(profilebeanpostbean);
                call.enqueue(new Callback<ProfileBeanResponse>() {
                    @Override
                    public void onResponse(Call<ProfileBeanResponse> call, retrofit2.Response<ProfileBeanResponse> response) {
                        try {
                            ProfileBeanResponse profileBeanResponse = response.body();
                            apiResponse.dismissProgress();
                            if (profileBeanResponse.getStatus()) {
                                apiResponse.onSuccess(profileBeanResponse);
                            } else {
                                apiResponse.onServerError(profileBeanResponse.getMessage());
                            }

                        } catch (Exception e) {
                            apiResponse.dismissProgress();
                            apiResponse.onServerError(mcontext.getResources().getString(R.string.server_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<ProfileBeanResponse> call, Throwable t) {
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
                        System.out.println("profiledata is 11===  " +t.getMessage());
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




    public void getUserFeed(UserFeedRequest bean, ResponseSucessCallback responseSucessCallback) {


        ((Activity) mcontext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                apiResponse.showProgress();
            }
        });

        try {
            if (Constants.isNetworkAvailable(mcontext)) {
                Call<UserFeedResponseBean> call = RequestClient.getClient().create(APIInterface.class).getUserFeed(bean);
                call.enqueue(new Callback<UserFeedResponseBean>() {
                    @Override
                    public void onResponse(Call<UserFeedResponseBean> call, retrofit2.Response<UserFeedResponseBean> response) {
                        try {
                            UserFeedResponseBean profileBeanResponse = response.body();
                            apiResponse.dismissProgress();
                            if (profileBeanResponse.getStatus()) {
                                responseSucessCallback.responseSucess(profileBeanResponse);
                            } else {
                                apiResponse.onServerError(profileBeanResponse.getMessage());
                            }

                        } catch (Exception e) {
                            apiResponse.dismissProgress();
                            apiResponse.onServerError(mcontext.getResources().getString(R.string.server_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<UserFeedResponseBean> call, Throwable t) {
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
                        System.out.println("profiledata is 11===  " +t.getMessage());
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
