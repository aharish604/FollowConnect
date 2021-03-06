package com.appcare.followconnect.SearchFriends;

import android.app.Activity;
import android.content.Context;

import com.appcare.followconnect.Chat.ResponseSucessCallback;
import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.Network.APIInterface;
import com.appcare.followconnect.Network.APIResponse;
import com.appcare.followconnect.Network.RequestClient;
import com.appcare.followconnect.R;
import com.appcare.followconnect.SearchFriends.AddasaFriend.AddfriendRequestBean;
import com.appcare.followconnect.SearchFriends.AddasaFriend.AddfrinedResponseBean;
import com.appcare.followconnect.SearchFriends.Bean.UserFriendsInuts;
import com.appcare.followconnect.SearchFriends.followandunfollow.followResponseBean;
import com.appcare.followconnect.SearchFriends.followandunfollow.followrequestbean;

import retrofit2.Call;
import retrofit2.Callback;

public class UserFriendsPresenter {

    Context mcontext;
    APIResponse apiResponse;

    public UserFriendsPresenter(SearchUserProfileActivity searchUserProfileActivity, APIResponse apiResponse) {
        this.mcontext = searchUserProfileActivity;
        this.apiResponse = apiResponse;
    }

    public void getFriendsData(UserFriendsInuts bean, ResponseSucessCallback responseSucessCallback) {

        ((Activity) mcontext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                apiResponse.showProgress();
            }
        });

        try {
            if (Constants.isNetworkAvailable(mcontext)) {
                Call<UserFriendsFeedResponse> call = RequestClient.getClient().create(APIInterface.class).friendsUserFeed(bean);
                call.enqueue(new Callback<UserFriendsFeedResponse>() {
                    @Override
                    public void onResponse(Call<UserFriendsFeedResponse> call, retrofit2.Response<UserFriendsFeedResponse> response) {
                        try {
                            UserFriendsFeedResponse userFriendResponse = response.body();
                            apiResponse.dismissProgress();
                            if (userFriendResponse.getStatus()) {

                                responseSucessCallback.responseSucess(userFriendResponse);

                            } else {
                                apiResponse.onServerError(userFriendResponse.getMessage());
                            }

                        } catch (Exception e) {
                            apiResponse.dismissProgress();
                            apiResponse.onServerError(mcontext.getResources().getString(R.string.server_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<UserFriendsFeedResponse> call, Throwable t) {
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


    public void sendunfollowrequest(followrequestbean bean, ResponseSucessCallback responseSucessCallback) {

        ((Activity) mcontext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                apiResponse.showProgress();
            }
        });

        try {
            if (Constants.isNetworkAvailable(mcontext)) {
                Call<followResponseBean> call = RequestClient.getClient().create(APIInterface.class).sendunfollowrequest(bean);
                call.enqueue(new Callback<followResponseBean>() {
                    @Override
                    public void onResponse(Call<followResponseBean> call, retrofit2.Response<followResponseBean> response) {
                        try {
                            followResponseBean userFriendResponse = response.body();
                            apiResponse.dismissProgress();
                            if (userFriendResponse.getStatus()) {

                                responseSucessCallback.responseSucess(userFriendResponse);

                            } else {
                                apiResponse.onServerError(userFriendResponse.getMessage());
                            }

                        } catch (Exception e) {
                            apiResponse.dismissProgress();
                            apiResponse.onServerError(mcontext.getResources().getString(R.string.server_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<followResponseBean> call, Throwable t) {
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

    public void sendFriendRequest(AddfriendRequestBean bean, ResponseSucessCallback responseSucessCallback) {

        ((Activity) mcontext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                apiResponse.showProgress();
            }
        });

        try {
            if (Constants.isNetworkAvailable(mcontext)) {
                Call<AddfrinedResponseBean> call = RequestClient.getClient().create(APIInterface.class).addFriend(bean);
                call.enqueue(new Callback<AddfrinedResponseBean>() {
                    @Override
                    public void onResponse(Call<AddfrinedResponseBean> call, retrofit2.Response<AddfrinedResponseBean> response) {
                        try {
                            AddfrinedResponseBean addfrinedResponseBean = response.body();
                            apiResponse.dismissProgress();
                            if (addfrinedResponseBean.getStatus()) {

                                responseSucessCallback.responseSucess(addfrinedResponseBean);

                            } else {
                                apiResponse.onServerError(addfrinedResponseBean.getMessage());
                            }

                        } catch (Exception e) {
                            apiResponse.dismissProgress();
                            apiResponse.onServerError(mcontext.getResources().getString(R.string.server_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<AddfrinedResponseBean> call, Throwable t) {
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
