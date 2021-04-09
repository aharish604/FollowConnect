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
import com.appcare.followconnect.Profile.FriendsList.Bean.FollowersRequestBean;
import com.appcare.followconnect.Profile.FriendsList.Bean.FollowersResponseBean;
import com.appcare.followconnect.Profile.FriendsList.Bean.FriendsListRequestBean;
import com.appcare.followconnect.Profile.FriendsList.Bean.FriendsListResponseBean;
import com.appcare.followconnect.R;
import com.appcare.followconnect.Settings.BlockerList.UnblockRequestBean;
import com.appcare.followconnect.Settings.BlockerList.UnblockResponseBean;
import com.appcare.followconnect.Settings.BlockerList.BlockerListRequestBean;
import com.appcare.followconnect.Settings.BlockerList.BlockerListResponseBean;

import retrofit2.Call;
import retrofit2.Callback;

public class Profilepresenter {


    Context mcontext;
    APIResponse apiResponse;
    public Profilepresenter(Context profileActivity, APIResponse apiResponse) {
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


    public void getFriendsList(FriendsListRequestBean bean, ResponseSucessCallback responseSucessCallback) {

        ((Activity) mcontext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                apiResponse.showProgress();
            }
        });

        try {
            if (Constants.isNetworkAvailable(mcontext)) {
                Call<FriendsListResponseBean> call = RequestClient.getClient().create(APIInterface.class).getFreindsList(bean);
                call.enqueue(new Callback<FriendsListResponseBean>() {
                    @Override
                    public void onResponse(Call<FriendsListResponseBean> call, retrofit2.Response<FriendsListResponseBean> response) {
                        try {
                            FriendsListResponseBean friendsListResponseBean = response.body();
                            apiResponse.dismissProgress();
                            if (friendsListResponseBean.getStatus()) {
                                responseSucessCallback.responseSucess(friendsListResponseBean);
                            } else {
                                apiResponse.onServerError(friendsListResponseBean.getMessage());
                            }

                        } catch (Exception e) {
                            apiResponse.dismissProgress();
                            apiResponse.onServerError(mcontext.getResources().getString(R.string.server_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<FriendsListResponseBean> call, Throwable t) {
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

    public void getFollowersList(FollowersRequestBean bean1, ResponseSucessCallback responseSucessCallback) {


        ((Activity) mcontext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                apiResponse.showProgress();
            }
        });

        try {
            if (Constants.isNetworkAvailable(mcontext)) {
                Call<FollowersResponseBean> call = RequestClient.getClient().create(APIInterface.class).getFollowersList(bean1);
                call.enqueue(new Callback<FollowersResponseBean>() {
                    @Override
                    public void onResponse(Call<FollowersResponseBean> call, retrofit2.Response<FollowersResponseBean> response) {
                        try {
                            FollowersResponseBean followersResponseBean = response.body();
                            apiResponse.dismissProgress();
                            if (followersResponseBean.getStatus()) {
                                responseSucessCallback.responseSucess(followersResponseBean);
                            } else {
                                apiResponse.onServerError(followersResponseBean.getMessage());
                            }

                        } catch (Exception e) {
                            apiResponse.dismissProgress();
                            apiResponse.onServerError(mcontext.getResources().getString(R.string.server_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<FollowersResponseBean> call, Throwable t) {
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

    public void getBlockerList(BlockerListRequestBean bean, ResponseSucessCallback responseSucessCallback) {


        ((Activity) mcontext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                apiResponse.showProgress();
            }
        });

        try {
            if (Constants.isNetworkAvailable(mcontext)) {
                Call<BlockerListResponseBean> call = RequestClient.getClient().create(APIInterface.class).getBlockerList(bean);
                call.enqueue(new Callback<BlockerListResponseBean>() {
                    @Override
                    public void onResponse(Call<BlockerListResponseBean> call, retrofit2.Response<BlockerListResponseBean> response) {
                        try {
                            BlockerListResponseBean blockerListResponseBean = response.body();
                            apiResponse.dismissProgress();
                            if (blockerListResponseBean.getStatus()) {
                                responseSucessCallback.responseSucess(blockerListResponseBean);
                            } else {
                                apiResponse.onServerError(blockerListResponseBean.getMessage());
                            }

                        } catch (Exception e) {
                            apiResponse.dismissProgress();
                            apiResponse.onServerError(mcontext.getResources().getString(R.string.server_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<BlockerListResponseBean> call, Throwable t) {
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

    public void unBlockuser(UnblockRequestBean bean, ResponseSucessCallback responseSucessCallback) {


        ((Activity) mcontext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                apiResponse.showProgress();
            }
        });

        try {
            if (Constants.isNetworkAvailable(mcontext)) {
                Call<UnblockResponseBean> call = RequestClient.getClient().create(APIInterface.class).unblockuser(bean);
                call.enqueue(new Callback<UnblockResponseBean>() {
                    @Override
                    public void onResponse(Call<UnblockResponseBean> call, retrofit2.Response<UnblockResponseBean> response) {
                        try {
                            UnblockResponseBean blockerListResponseBean = response.body();
                            apiResponse.dismissProgress();
                            if (blockerListResponseBean.getStatus()) {
                                responseSucessCallback.responseSucess(blockerListResponseBean);
                            } else {
                                apiResponse.onServerError(blockerListResponseBean.getMessage());
                            }

                        } catch (Exception e) {
                            apiResponse.dismissProgress();
                            apiResponse.onServerError(mcontext.getResources().getString(R.string.server_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<UnblockResponseBean> call, Throwable t) {
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
