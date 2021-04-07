package com.appcare.followconnect.ProfileUpdate;

import android.app.Activity;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.LoginWithGoogle.ProfileCallback;
import com.appcare.followconnect.Network.APIInterface;
import com.appcare.followconnect.Network.APIResponse;
import com.appcare.followconnect.Network.RequestClient;
import com.appcare.followconnect.R;
import com.appcare.followconnect.SignUp.CountrySpinner.CountryBean;
import com.appcare.followconnect.SignUp.SignupView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class ProfileUpdatePresenter {

    Context mcontext;
    APIResponse apiResponse;
    ArrayList<CountryBean> countrylist = new ArrayList<>();
    ArrayList<CountryBean> countrylist1 = new ArrayList<>();

    public ProfileUpdatePresenter(UpdateProfileActivity activity, APIResponse apiResponse) {
        this.mcontext = activity;
        this.apiResponse = apiResponse;
    }



    public void getCountryes(SignupView signupView) {

        ((Activity) mcontext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (apiResponse != null) {
                    apiResponse.showProgress();
                }
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(mcontext);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                Constants.countryAPI,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            countrylist.clear();
                            for (int i = 0; i < response.length(); i++) {
                                CountryBean bean = new CountryBean();
                                JSONObject countryJSONObject = response.getJSONObject(i);
                                String str_country = countryJSONObject.getString("alpha3Code");
                                String flagUrl = countryJSONObject.getString("flag");
                                String callingCodes = countryJSONObject.getString("callingCodes");
                                String name = countryJSONObject.getString("name");
                                if (!callingCodes.equalsIgnoreCase("")) {
                                    try {
                                        String[] arrOfStr = callingCodes.split(",", 0);
                                        bean.setCallingcode(callingCodes.substring(2, arrOfStr[0].length() - 2));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                }
                                bean.setCountry(str_country);
                                bean.setFlag_url(flagUrl);
                                bean.setName(name);
                                if (!name.equalsIgnoreCase("india")) {
                                    if (!bean.getCallingcode().equalsIgnoreCase("")) {
                                        countrylist.add(bean);
                                    }

                                } else {
                                    countrylist1.add(bean);
                                }

                                System.out.println("data" + str_country + flagUrl + callingCodes);

                            }

                            countrylist1.addAll(countrylist);

                            ((Activity) mcontext).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (apiResponse != null && signupView!=null) {
                                        apiResponse.dismissProgress();
                                        signupView.resultcountryData(countrylist1);
                                    }
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ((Activity) mcontext).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (apiResponse != null) {
                                    apiResponse.dismissProgress();
                                    Constants.displayLongToast(mcontext, "" + error.toString());
                                }
                            }
                        });
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }


    public void updateProfiledata(UpdateProfileBean updateProfileBean) {

        ((Activity) mcontext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                apiResponse.showProgress();
            }
        });

        try {
            if (Constants.isNetworkAvailable(mcontext)) {
                Call<ProfileUpdateBeanResponse> call = RequestClient.getClient().create(APIInterface.class).updateprofiledata(updateProfileBean);
                call.enqueue(new Callback<ProfileUpdateBeanResponse>() {
                    @Override
                    public void onResponse(Call<ProfileUpdateBeanResponse> call, retrofit2.Response<ProfileUpdateBeanResponse> response) {
                        try {
                            ProfileUpdateBeanResponse profileUpdateBeanResponse = response.body();
                            apiResponse.dismissProgress();
                            if (profileUpdateBeanResponse.getStatus()) {
                                apiResponse.onSuccess(profileUpdateBeanResponse);
                            } else {
                                apiResponse.onServerError(profileUpdateBeanResponse.getMessage());
                            }

                        } catch (Exception e) {
                            apiResponse.dismissProgress();
                            apiResponse.onServerError(mcontext.getResources().getString(R.string.server_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<ProfileUpdateBeanResponse> call, Throwable t) {
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

    public void UpdateProfilePicture(String profileImage, String userid, ProfileCallback profileCallback) {

        ((Activity) mcontext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                apiResponse.showProgress();
            }
        });

        RequestBody user_id = RequestBody.create(MediaType.parse("text/plain"),userid );


        MultipartBody.Part prof_img = null;
            try {
                File file = new File(profileImage);
                RequestBody mFile1 = RequestBody.create(MediaType.parse("image/*"), file);
                prof_img = MultipartBody.Part.createFormData("profile_pic", file.getName(), mFile1);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
            if (Constants.isNetworkAvailable(mcontext)) {
                Call<UpdateProfilePhotoResponse> call = RequestClient.getClient().create(APIInterface.class).UpdateProfileData(user_id,prof_img);
                call.enqueue(new Callback<UpdateProfilePhotoResponse>() {
                    @Override
                    public void onResponse(Call<UpdateProfilePhotoResponse> call, retrofit2.Response<UpdateProfilePhotoResponse> response) {
                        try {
                            UpdateProfilePhotoResponse updateProfilePhotoResponse = response.body();
                            apiResponse.dismissProgress();
                            if (updateProfilePhotoResponse.getStatus()) {
                                profileCallback.getProfileData(updateProfilePhotoResponse);
                            } else {
                                apiResponse.onServerError(updateProfilePhotoResponse.getMessage());
                            }

                        } catch (Exception e) {
                            apiResponse.dismissProgress();
                            apiResponse.onServerError(mcontext.getResources().getString(R.string.server_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<UpdateProfilePhotoResponse> call, Throwable t) {
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
