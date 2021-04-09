package com.appcare.followconnect.Comments.presenter;

import android.app.Activity;
import android.content.Context;

import com.appcare.followconnect.Chat.Bean.CharBeanRequest;
import com.appcare.followconnect.Chat.Bean.ChatHistoryBeanRequest;
import com.appcare.followconnect.Chat.Bean.ChatHistoryBeanResponse;
import com.appcare.followconnect.Chat.Bean.ChatListBeanResponse;
import com.appcare.followconnect.Chat.Bean.MessageSendRequest;
import com.appcare.followconnect.Chat.Bean.MessageSendResponse;
import com.appcare.followconnect.Chat.ResponseSucessCallback;
import com.appcare.followconnect.Comments.model.CommentsListInputs;
import com.appcare.followconnect.Comments.model.CommentsListResponse;
import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.Home.fragments.FeedLikeInputs;
import com.appcare.followconnect.MyviewPostdisplay.FeedLikeResponse;
import com.appcare.followconnect.Network.APIInterface;
import com.appcare.followconnect.Network.APIResponse;
import com.appcare.followconnect.Network.RequestClient;
import com.appcare.followconnect.R;


import retrofit2.Call;
import retrofit2.Callback;

public class CommentsPresenter {
    Context mcontext;
    APIResponse apiResponse;

    public CommentsPresenter(Context activity, APIResponse apiResponse) {
        this.mcontext = activity;
        this.apiResponse = apiResponse;
    }

    public void getCommentsList(CommentsListInputs inputs, ResponseSucessCallback responseSucessCallback) {

        ((Activity) mcontext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                apiResponse.showProgress();
            }
        });

        try {
            if (Constants.isNetworkAvailable(mcontext)) {
                Call<CommentsListResponse> call = RequestClient.getClient().create(APIInterface.class).getCommentsList(inputs);
                call.enqueue(new Callback<CommentsListResponse>() {
                    @Override
                    public void onResponse(Call<CommentsListResponse> call, retrofit2.Response<CommentsListResponse> response) {
                        try {
                            CommentsListResponse commentsListResponse = response.body();
                            apiResponse.dismissProgress();
                            if (commentsListResponse.isStatus()) {
                                responseSucessCallback.responseSucess(commentsListResponse);
                            } else {
                                apiResponse.onServerError(commentsListResponse.getMessage());
                            }

                        } catch (Exception e) {
                            apiResponse.dismissProgress();
                            apiResponse.onServerError(mcontext.getResources().getString(R.string.server_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<CommentsListResponse> call, Throwable t) {
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


    public void postComments(FeedLikeInputs bean, ResponseSucessCallback responseSucessCallback) {
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

}
