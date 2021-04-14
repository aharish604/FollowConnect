package com.appcare.followconnect.UploadPost;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.Network.APIInterface;
import com.appcare.followconnect.Network.APIResponse;
import com.appcare.followconnect.Network.RequestClient;
import com.appcare.followconnect.ProfileUpdate.ProfileUpdateBeanResponse;
import com.appcare.followconnect.ProfileUpdate.UpdateProfileActivity;
import com.appcare.followconnect.R;
import com.appcare.followconnect.SignUp.CountrySpinner.CountryBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Part;

public class UploadPostpresenter {


    Context mcontext;
    APIResponse apiResponse;

    public UploadPostpresenter(UploadPostActivity activity, APIResponse apiResponse) {
        this.mcontext = activity;
        this.apiResponse = apiResponse;
    }

    public void uploadPost(UploadPostBean bean) {

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


        MultipartBody.Part[] multipartImages = new MultipartBody.Part[bean.getImages().length];

        for (int index = 0; index < bean.getImages().length; index++) {
            Log.d("Upload request", "requestUploadSurvey: survey image " + index + "  " + bean.getImages()[index]);
            File file2 = new File(bean.getImages()[index]);
            RequestBody surveyBody = RequestBody.create(MediaType.parse("image/*"), file2);
            multipartImages[index] = MultipartBody.Part.createFormData("images[]", file2.getPath() + ".png", surveyBody);
        }


        MultipartBody.Part[] videosarray = new MultipartBody.Part[bean.getVideos().length];
        for (int i = 0; i < bean.getVideos().length; i++) {
            File file = new File(String.valueOf(bean.getVideos()[i]));
            RequestBody mFile1 = RequestBody.create(MediaType.parse("video/*"), file);
            videosarray[i] = MultipartBody.Part.createFormData("videos[]", file.getName() + ".mp4", mFile1);
        }


        if (bean.getVideos().length != 0) {

            try {
                if (Constants.isNetworkAvailable(mcontext)) {
                    Call<UploadPostResponse> call = RequestClient.getClient().create(APIInterface.class).uploadpostwithvideos(uid, comment, address, ispoolvid, privicy, videosarray);
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
                                apiResponse.onServerError(mcontext.getResources().getString(R.string.server_error) + "" + e.getMessage());
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


        } else if (bean.getImages().length != 0) {
            try {
                if (Constants.isNetworkAvailable(mcontext)) {
                    Call<UploadPostResponse> call = RequestClient.getClient().create(APIInterface.class).uploadpostwithimages(uid, comment, address, ispoolvid, privicy, multipartImages);
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
                                apiResponse.onServerError(mcontext.getResources().getString(R.string.server_error) + "" + e.getMessage());
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


        } else {

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
                                apiResponse.onServerError(mcontext.getResources().getString(R.string.server_error) + "" + e.getMessage());
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


}
