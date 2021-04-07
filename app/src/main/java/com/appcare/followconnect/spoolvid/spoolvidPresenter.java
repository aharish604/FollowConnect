package com.appcare.followconnect.spoolvid;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.appcare.followconnect.Chat.Bean.ChatHistoryBeanResponse;
import com.appcare.followconnect.Chat.ResponseSucessCallback;
import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.Network.APIInterface;
import com.appcare.followconnect.Network.APIResponse;
import com.appcare.followconnect.Network.RequestClient;
import com.appcare.followconnect.R;
import com.appcare.followconnect.UploadPost.UploadPostBean;
import com.appcare.followconnect.UploadPost.UploadPostResponse;
import com.appcare.followconnect.spoolvid.Bean.SpoolvidRequestBean;
import com.appcare.followconnect.spoolvid.Bean.SpoolvidResponseBean;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class spoolvidPresenter {

    Context mcontext;
    APIResponse apiResponse;

    public spoolvidPresenter(Context mContext, APIResponse apiResponse) {
        this.mcontext = mContext;
        this.apiResponse = apiResponse;
    }


    public void getspoolvid(SpoolvidRequestBean bean, ResponseSucessCallback responseSucessCallback) {


        ((Activity) mcontext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                apiResponse.showProgress();
            }
        });

        try {
            if (Constants.isNetworkAvailable(mcontext)) {
                Call<SpoolvidResponseBean> call = RequestClient.getClient().create(APIInterface.class).getSpoolvid(bean);
                call.enqueue(new Callback<SpoolvidResponseBean>() {
                    @Override
                    public void onResponse(Call<SpoolvidResponseBean> call, retrofit2.Response<SpoolvidResponseBean> response) {
                        try {
                            SpoolvidResponseBean spoolvidResponseBean = response.body();
                            if (spoolvidResponseBean.getStatus()) {
                                responseSucessCallback.responseSucess(spoolvidResponseBean);
                            } else {
                                apiResponse.onServerError(spoolvidResponseBean.getMessage());
                            }

                        } catch (Exception e) {
                            apiResponse.dismissProgress();
                            apiResponse.onServerError(mcontext.getResources().getString(R.string.server_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<SpoolvidResponseBean> call, Throwable t) {
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

    public void postPoolvid(UploadPostBean bean) {


        ((Activity) mcontext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                apiResponse.showProgress();
            }
        });

        RequestBody uid = RequestBody.create(MediaType.parse("text/plain"), bean.getUid());
        RequestBody comment = RequestBody.create(MediaType.parse("text/plain"), bean.getFeedtext());
        RequestBody address = RequestBody.create(MediaType.parse("text/plain"), bean.getAddress());
        RequestBody privicy = RequestBody.create(MediaType.parse("text/plain"), bean.getPrivicy());
        RequestBody ispoolvid = RequestBody.create(MediaType.parse("text/plain"), bean.getIsspoolvid());


        MultipartBody.Part[] multipartImages = new MultipartBody.Part[0];

       /* for (int index = 0; index < bean.getImages().length; index++) {
            Log.d("Upload request", "requestUploadSurvey: survey image " + index + "  " + bean.getImages()[index]);
            File file2 = new File(bean.getImages()[index]);
            RequestBody surveyBody = RequestBody.create(MediaType.parse("image/*"), file2);
            multipartImages[index] = MultipartBody.Part.createFormData("images[]", file2.getPath(), surveyBody);
        }
*/

        MultipartBody.Part videosarray = null;
        File file2 = new File(bean.getVideopath());
        RequestBody surveyBody = RequestBody.create(MediaType.parse("video/*"), file2);
        videosarray= MultipartBody.Part.createFormData("videos[]", file2.getPath(), surveyBody);




        try {
            if (Constants.isNetworkAvailable(mcontext)) {
                Call<UploadPostResponse> call = RequestClient.getClient().create(APIInterface.class).uploadpost(uid, comment, address, ispoolvid, privicy, multipartImages, videosarray);
                call.enqueue(new Callback<UploadPostResponse>() {
                    @Override
                    public void onResponse(Call<UploadPostResponse> call, retrofit2.Response<UploadPostResponse> response) {
                        try {
                            UploadPostResponse uploadPostResponse = response.body();
                            apiResponse.dismissProgress();
                            if (uploadPostResponse.getStatus()) {
                                apiResponse.onSuccess(uploadPostResponse);
                            } else {
                                apiResponse.onServerError(uploadPostResponse.getMessage());
                            }

                        } catch (Exception e) {
                            apiResponse.dismissProgress();
                            apiResponse.onServerError(mcontext.getResources().getString(R.string.server_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<UploadPostResponse> call, Throwable t) {
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
                        System.out.println("updateprofiledata is 11===  " + t.getMessage());
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
