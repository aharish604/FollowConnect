package com.appcare.followconnect.Chat;

import android.app.Activity;
import android.content.Context;

import com.appcare.followconnect.Chat.Bean.CharBeanRequest;
import com.appcare.followconnect.Chat.Bean.ChatHistoryBeanRequest;
import com.appcare.followconnect.Chat.Bean.ChatHistoryBeanResponse;
import com.appcare.followconnect.Chat.Bean.ChatListBeanResponse;
import com.appcare.followconnect.Chat.Bean.MessageSendRequest;
import com.appcare.followconnect.Chat.Bean.MessageSendResponse;
import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.Network.APIInterface;
import com.appcare.followconnect.Network.APIResponse;
import com.appcare.followconnect.Network.RequestClient;
import com.appcare.followconnect.R;
import com.appcare.followconnect.SearchFriends.Bean.SearchDataInsertBeanResponse;
import com.appcare.followconnect.SignUp.CountrySpinner.CountryBean;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class ChatPresenter {

    Context mcontext;
    APIResponse apiResponse;

    public ChatPresenter(Context activity, APIResponse apiResponse) {
        this.mcontext = activity;
        this.apiResponse = apiResponse;
    }

    public void getChatList(CharBeanRequest charBeanRequest, ResponseSucessCallback responseSucessCallback) {

        ((Activity) mcontext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                apiResponse.showProgress();
            }
        });

        try {
            if (Constants.isNetworkAvailable(mcontext)) {
                Call<ChatListBeanResponse> call = RequestClient.getClient().create(APIInterface.class).getChatList(charBeanRequest);
                call.enqueue(new Callback<ChatListBeanResponse>() {
                    @Override
                    public void onResponse(Call<ChatListBeanResponse> call, retrofit2.Response<ChatListBeanResponse> response) {
                        try {
                            ChatListBeanResponse chatListBeanResponse = response.body();
                            apiResponse.dismissProgress();
                            if (chatListBeanResponse.getStatus()) {
                                responseSucessCallback.responseSucess(chatListBeanResponse);
                            } else {
                                apiResponse.onServerError(chatListBeanResponse.getMessage());
                            }

                        } catch (Exception e) {
                            apiResponse.dismissProgress();
                            apiResponse.onServerError(mcontext.getResources().getString(R.string.server_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<ChatListBeanResponse> call, Throwable t) {
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

    public void getChatHistory(ChatHistoryBeanRequest charBeanRequest,String commingfrom, ResponseSucessCallback responseSucessCallback) {

        ((Activity) mcontext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                apiResponse.showProgress();
            }
        });

        try {
            if (Constants.isNetworkAvailable(mcontext)) {
                Call<ChatHistoryBeanResponse> call = RequestClient.getClient().create(APIInterface.class).getChatHistory(charBeanRequest);
                call.enqueue(new Callback<ChatHistoryBeanResponse>() {
                    @Override
                    public void onResponse(Call<ChatHistoryBeanResponse> call, retrofit2.Response<ChatHistoryBeanResponse> response) {
                        try {
                            ChatHistoryBeanResponse chatListBeanResponse = response.body();
                            if (chatListBeanResponse.getStatus()) {
                                apiResponse.dismissProgress();
                                responseSucessCallback.responseSucess(chatListBeanResponse);
                            } else {
                               // apiResponse.onServerError(chatListBeanResponse.getMessage());
                            }

                        } catch (Exception e) {
                            apiResponse.dismissProgress();
                            apiResponse.onServerError(mcontext.getResources().getString(R.string.server_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<ChatHistoryBeanResponse> call, Throwable t) {
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

    public void sendmsg(MessageSendRequest bean, ResponseSucessCallback responseSucessCallback) {


        ((Activity) mcontext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                apiResponse.showProgress();
            }
        });

        try {
            if (Constants.isNetworkAvailable(mcontext)) {
                Call<MessageSendResponse> call = RequestClient.getClient().create(APIInterface.class).sendmsg(bean);
                call.enqueue(new Callback<MessageSendResponse>() {
                    @Override
                    public void onResponse(Call<MessageSendResponse> call, retrofit2.Response<MessageSendResponse> response) {
                        try {
                            MessageSendResponse messageSendResponse = response.body();
                            apiResponse.dismissProgress();
                            if (messageSendResponse.getStatus()) {
                                responseSucessCallback.responseSucess(messageSendResponse);
                            } else {
                                apiResponse.onServerError(messageSendResponse.getMessage());
                            }

                        } catch (Exception e) {
                            apiResponse.dismissProgress();
                            apiResponse.onServerError(mcontext.getResources().getString(R.string.server_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<MessageSendResponse> call, Throwable t) {
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
