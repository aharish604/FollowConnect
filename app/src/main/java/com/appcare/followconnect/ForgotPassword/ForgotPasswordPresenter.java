package com.appcare.followconnect.ForgotPassword;

import android.app.Activity;
import android.content.Context;

import com.appcare.followconnect.Chat.ResponseSucessCallback;
import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.Network.APIInterface;
import com.appcare.followconnect.Network.APIResponse;
import com.appcare.followconnect.Network.RequestClient;
import com.appcare.followconnect.R;

import retrofit2.Call;
import retrofit2.Callback;

public class ForgotPasswordPresenter {

    Context mcontext;
    APIResponse apiResponse;

    public ForgotPasswordPresenter(Context forgotPassword, APIResponse apiResponse) {

        this.mcontext = forgotPassword;
        this.apiResponse = apiResponse;
    }


    public void sendForgetpasswordLink(ForgotPasswordBean bean, ResponseSucessCallback responseSucessCallback) {

        ((Activity) mcontext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                apiResponse.showProgress();
            }
        });

        try {
            if (Constants.isNetworkAvailable(mcontext)) {
                Call<ForgotResponse> call = RequestClient.getClient().create(APIInterface.class).forgotpasswordResponse(bean);
                call.enqueue(new Callback<ForgotResponse>() {
                    @Override
                    public void onResponse(Call<ForgotResponse> call, retrofit2.Response<ForgotResponse> response) {
                        try {
                            ForgotResponse forgotResponse = response.body();
                            apiResponse.dismissProgress();
                            if (forgotResponse.getStatus()) {
                                responseSucessCallback.responseSucess(forgotResponse);
                            } else {
                                apiResponse.onServerError(forgotResponse.getMessage());
                            }

                        } catch (Exception e) {
                            apiResponse.dismissProgress();
                            apiResponse.onServerError(mcontext.getResources().getString(R.string.server_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<ForgotResponse> call, Throwable t) {
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
