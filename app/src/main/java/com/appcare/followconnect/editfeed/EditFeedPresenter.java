package com.appcare.followconnect.editfeed;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.Network.APIInterface;
import com.appcare.followconnect.Network.APIResponse;
import com.appcare.followconnect.Network.RequestClient;
import com.appcare.followconnect.R;
import com.appcare.followconnect.UploadPost.UploadPostResponse;
import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class EditFeedPresenter {
    Context mcontext;
    APIResponse apiResponse;
    public EditFeedPresenter(EditFeedActivity activity, APIResponse apiResponse) {
        this.mcontext = activity;
        this.apiResponse = apiResponse;
    }



    public void EditFeedPost(String uid, String comment, String isSpolvid, String privicy, String feedId, String[] imagesarray, String[] videoarray) {
        ((Activity) mcontext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                apiResponse.showProgress();
            }
        });

        RequestBody uid_body = RequestBody.create(MediaType.parse("text/plain"), uid);
        RequestBody comment_body  = RequestBody.create(MediaType.parse("text/plain"), comment);
        RequestBody ispoolvid_body  = RequestBody.create(MediaType.parse("text/plain"), isSpolvid);
        RequestBody privicy_body  = RequestBody.create(MediaType.parse("text/plain"), privicy);
        RequestBody feedId_body  = RequestBody.create(MediaType.parse("text/plain"), feedId);


        MultipartBody.Part[] multipartImages = new MultipartBody.Part[imagesarray.length];

        for (int index = 0; index < imagesarray.length; index++) {
            Log.d("Upload request", "requestUploadSurvey: survey image " + index + "  " + imagesarray[index]);
            File file2 = new File(imagesarray[index]);
            RequestBody surveyBody = RequestBody.create(MediaType.parse("image/*"), file2);
            multipartImages[index] = MultipartBody.Part.createFormData("images[]", file2.getPath(), surveyBody);
        }

        MultipartBody.Part videosarray = null;
        for (int i = 0; i < videoarray.length; i++) {
            try {
                File file = new File(String.valueOf(videoarray[i]));
                RequestBody mFile1 = RequestBody.create(MediaType.parse("video/*"), file);
                videosarray = MultipartBody.Part.createFormData("videos[]", file.getName(), mFile1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }






        try {
            if (Constants.isNetworkAvailable(mcontext)) {
                Call<UploadPostResponse> call = RequestClient.getClient().create(APIInterface.class).feeEditdpost(uid_body,comment_body,ispoolvid_body,privicy_body, feedId_body, multipartImages,videosarray);
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
                            apiResponse.onServerError(mcontext.getResources().getString(R.string.server_error)+""+e.getMessage());
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
                        System.out.println("updateprofiledata is 11===  " +t.getMessage());
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
