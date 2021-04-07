package com.appcare.followconnect.Notifications;

import android.app.Activity;
import android.content.Context;

import com.appcare.followconnect.Chat.ResponseSucessCallback;
import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.Network.APIInterface;
import com.appcare.followconnect.Network.APIResponse;
import com.appcare.followconnect.Network.RequestClient;
import com.appcare.followconnect.Notifications.Bean.NotificationRequestBean;
import com.appcare.followconnect.Notifications.Bean.NotificationResponsebean;
import com.appcare.followconnect.Profile.Bean.ProfileBeanRequest;
import com.appcare.followconnect.Profile.Bean.ProfileBeanResponse;
import com.appcare.followconnect.R;
import com.appcare.followconnect.UploadPost.UploadPostActivity;

import retrofit2.Call;
import retrofit2.Callback;

public class Notificationpresenter {


    Context mcontext;
    APIResponse apiResponse;
    public Notificationpresenter(Activity activity, APIResponse apiResponse) {
        this.mcontext = activity;
        this.apiResponse = apiResponse;
    }

    public void getNotfications(NotificationRequestBean bean, ResponseSucessCallback responseSucessCallback) {


        ((Activity) mcontext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                apiResponse.showProgress();
            }
        });

        try {
            if (Constants.isNetworkAvailable(mcontext)) {
                Call<NotificationResponsebean> call = RequestClient.getClient().create(APIInterface.class).getNotificationList(bean);
                call.enqueue(new Callback<NotificationResponsebean>() {
                    @Override
                    public void onResponse(Call<NotificationResponsebean> call, retrofit2.Response<NotificationResponsebean> response) {
                        try {
                            NotificationResponsebean notificationResponsebean = response.body();
                            apiResponse.dismissProgress();
                            if (notificationResponsebean.getStatus()) {
                                responseSucessCallback.responseSucess(notificationResponsebean);
                            } else {
                                apiResponse.onServerError(notificationResponsebean.getMessage());
                            }

                        } catch (Exception e) {
                            apiResponse.dismissProgress();
                            apiResponse.onServerError(mcontext.getResources().getString(R.string.server_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<NotificationResponsebean> call, Throwable t) {
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
