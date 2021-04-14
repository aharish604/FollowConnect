package com.appcare.followconnect.MyviewPostdisplay;

import android.app.Activity;
import android.content.Context;

import com.appcare.followconnect.Chat.ResponseSucessCallback;
import com.appcare.followconnect.Common.Constants;

import com.appcare.followconnect.Home.fragments.FeedLikeInputs;
import com.appcare.followconnect.MyviewPostdisplay.bean.BlockResponse;
import com.appcare.followconnect.MyviewPostdisplay.bean.BlockerInputs;
import com.appcare.followconnect.MyviewPostdisplay.bean.DeleteFeedInputs;
import com.appcare.followconnect.MyviewPostdisplay.bean.DeleteResponse;
import com.appcare.followconnect.MyviewPostdisplay.bean.GetPostFeedResponse;
import com.appcare.followconnect.MyviewPostdisplay.bean.GetPostRequestBean;
import com.appcare.followconnect.Network.APIInterface;
import com.appcare.followconnect.Network.APIResponse;
import com.appcare.followconnect.Network.RequestClient;
import com.appcare.followconnect.R;
import com.appcare.followconnect.SearchFriends.unfriend.UnfriendRequestBean;
import com.appcare.followconnect.SearchFriends.unfriend.UnfriendResponseBean;
import com.appcare.followconnect.View.ViewBeanRequest;
import com.appcare.followconnect.View.ViewBeanResponse;

import retrofit2.Call;
import retrofit2.Callback;

public class MyviewPresenter {

    Context mcontext;
    APIResponse apiResponse;
    public MyviewPresenter(Context mContext, APIResponse apiResponse) {
        this.mcontext = mContext;
        this.apiResponse = apiResponse;
    }

    public void getFeedList(GetPostRequestBean bean) {


        ((Activity) mcontext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                apiResponse.showProgress();
            }
        });

        try {
            if (Constants.isNetworkAvailable(mcontext)) {
                Call<GetPostFeedResponse> call = RequestClient.getClient().create(APIInterface.class).getFeedresponse(bean);
                call.enqueue(new Callback<GetPostFeedResponse>() {
                    @Override
                    public void onResponse(Call<GetPostFeedResponse> call, retrofit2.Response<GetPostFeedResponse> response) {
                        try {
                            GetPostFeedResponse profileBeanResponse = response.body();
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
                    public void onFailure(Call<GetPostFeedResponse> call, Throwable t) {
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


    public void postLikes(FeedLikeInputs bean, ResponseSucessCallback responseSucessCallback) {
        ((Activity) mcontext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                apiResponse.showProgress();
            }
        });

        try {
            if (Constants.isNetworkAvailable(mcontext)) {
                Call<FeedLikeResponse> call = RequestClient.getClient().create(APIInterface.class).postLikes(bean);
                call.enqueue(new Callback<FeedLikeResponse>() {
                    @Override
                    public void onResponse(Call<FeedLikeResponse> call, retrofit2.Response<FeedLikeResponse> response) {
                        try {
                            FeedLikeResponse feedLikeResponse = response.body();
                            apiResponse.dismissProgress();
                            if (feedLikeResponse.isStatus()) {
                                responseSucessCallback.responseSucess(feedLikeResponse);
                            } else {
                                apiResponse.onServerError(feedLikeResponse.getMessage());
                            }

                        } catch (Exception e) {
                            apiResponse.dismissProgress();
                            apiResponse.onServerError(mcontext.getResources().getString(R.string.server_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<FeedLikeResponse> call, Throwable t) {
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

    public void block(BlockerInputs bean, ResponseSucessCallback responseSucessCallback) {
        ((Activity) mcontext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                apiResponse.showProgress();
            }
        });

        try {
            if (Constants.isNetworkAvailable(mcontext)) {
                Call<BlockResponse> call = RequestClient.getClient().create(APIInterface.class).block(bean);
                call.enqueue(new Callback<BlockResponse>() {
                    @Override
                    public void onResponse(Call<BlockResponse> call, retrofit2.Response<BlockResponse> response) {
                        try {
                            BlockResponse blockResponse = response.body();
                            apiResponse.dismissProgress();
                            if (blockResponse.isStatus()) {
                                responseSucessCallback.responseSucess(blockResponse);
                            } else {
                                apiResponse.onServerError(blockResponse.getMessage());
                            }

                        } catch (Exception e) {
                            apiResponse.dismissProgress();
                            apiResponse.onServerError(mcontext.getResources().getString(R.string.server_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<BlockResponse> call, Throwable t) {
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

    public void deleteFeed(DeleteFeedInputs bean, ResponseSucessCallback responseSucessCallback) {
        ((Activity) mcontext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                apiResponse.showProgress();
            }
        });

        try {
            if (Constants.isNetworkAvailable(mcontext)) {
                Call<DeleteResponse> call = RequestClient.getClient().create(APIInterface.class).deleteFeed(bean);
                call.enqueue(new Callback<DeleteResponse>() {
                    @Override
                    public void onResponse(Call<DeleteResponse> call, retrofit2.Response<DeleteResponse> response) {
                        try {
                            DeleteResponse deleteResponse = response.body();
                            apiResponse.dismissProgress();
                            if (deleteResponse.isStatus()) {
                                responseSucessCallback.responseSucess(deleteResponse);
                            } else {
                                apiResponse.onServerError(deleteResponse.getMessage());
                            }

                        } catch (Exception e) {
                            apiResponse.dismissProgress();
                            apiResponse.onServerError(mcontext.getResources().getString(R.string.server_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<DeleteResponse> call, Throwable t) {
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

    public void unfriend(UnfriendRequestBean bean, ResponseSucessCallback responseSucessCallback) {
        ((Activity) mcontext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                apiResponse.showProgress();
            }
        });

        try {
            if (Constants.isNetworkAvailable(mcontext)) {
                Call<UnfriendResponseBean> call = RequestClient.getClient().create(APIInterface.class).unfriend(bean);
                call.enqueue(new Callback<UnfriendResponseBean>() {
                    @Override
                    public void onResponse(Call<UnfriendResponseBean> call, retrofit2.Response<UnfriendResponseBean> response) {
                        try {
                            UnfriendResponseBean unfriendResponseBean = response.body();
                            apiResponse.dismissProgress();
                            if (unfriendResponseBean.getStatus()) {
                                responseSucessCallback.responseSucess(unfriendResponseBean);
                            } else {
                                apiResponse.onServerError(unfriendResponseBean.getMessage());
                            }

                        } catch (Exception e) {
                            apiResponse.dismissProgress();
                            apiResponse.onServerError(mcontext.getResources().getString(R.string.server_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<UnfriendResponseBean> call, Throwable t) {
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


    public void viewpost(ViewBeanRequest bean, ResponseSucessCallback responseSucessCallback) {

        ((Activity) mcontext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                apiResponse.showProgress();
            }
        });

        try {
            if (Constants.isNetworkAvailable(mcontext)) {
                Call<ViewBeanResponse> call = RequestClient.getClient().create(APIInterface.class).feedview(bean);
                call.enqueue(new Callback<ViewBeanResponse>() {
                    @Override
                    public void onResponse(Call<ViewBeanResponse> call, retrofit2.Response<ViewBeanResponse> response) {
                        try {
                            ViewBeanResponse viewBeanResponse = response.body();
                            apiResponse.dismissProgress();
                            if (viewBeanResponse.getStatus()) {
                                responseSucessCallback.responseSucess(viewBeanResponse);
                            } else {
                                apiResponse.onServerError(viewBeanResponse.getMessage());
                            }

                        } catch (Exception e) {
                            apiResponse.dismissProgress();
                            apiResponse.onServerError(mcontext.getResources().getString(R.string.server_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<ViewBeanResponse> call, Throwable t) {
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