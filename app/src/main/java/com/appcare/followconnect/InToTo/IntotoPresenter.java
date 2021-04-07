package com.appcare.followconnect.InToTo;

import android.app.Activity;
import android.content.Context;

import com.appcare.followconnect.Chat.ResponseSucessCallback;
import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.InToTo.Bean.IntotoRequestBean;
import com.appcare.followconnect.InToTo.Bean.IntotoResponse;
import com.appcare.followconnect.Network.APIInterface;
import com.appcare.followconnect.Network.APIResponse;
import com.appcare.followconnect.Network.RequestClient;
import com.appcare.followconnect.R;

import retrofit2.Call;
import retrofit2.Callback;

public class IntotoPresenter {

    Context mcontext;
    APIResponse apiResponse;

    public IntotoPresenter(Context mContext, APIResponse apiResponse) {
        this.mcontext = mContext;
        this.apiResponse = apiResponse;
    }


    public void getIntotoList(IntotoRequestBean bean, ResponseSucessCallback responseSucessCallback) {


        ((Activity) mcontext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                apiResponse.showProgress();
            }
        });

        try {
            if (Constants.isNetworkAvailable(mcontext)) {
                Call<IntotoResponse> call = RequestClient.getClient().create(APIInterface.class).getIntoto(bean);
                call.enqueue(new Callback<IntotoResponse>() {
                    @Override
                    public void onResponse(Call<IntotoResponse> call, retrofit2.Response<IntotoResponse> response) {
                        try {

                            apiResponse.dismissProgress();
                            IntotoResponse intotoResponse = response.body();
                            if (intotoResponse.getStatus()) {
                                responseSucessCallback.responseSucess(intotoResponse);
                            } else {
                                apiResponse.onServerError(intotoResponse.getMessage());
                            }

                        } catch (Exception e) {
                            apiResponse.dismissProgress();
                            apiResponse.onServerError(mcontext.getResources().getString(R.string.server_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<IntotoResponse> call, Throwable t) {
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
