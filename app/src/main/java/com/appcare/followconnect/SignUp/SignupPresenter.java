package com.appcare.followconnect.SignUp;

import android.app.Activity;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.appcare.followconnect.Common.AppPreference;
import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.Network.APIInterface;
import com.appcare.followconnect.Network.APIResponse;
import com.appcare.followconnect.Network.RequestClient;
import com.appcare.followconnect.R;
import com.appcare.followconnect.SignUp.Beans.RandomUsernameBeanResponse;
import com.appcare.followconnect.SignUp.Beans.RandomusernameBeanRequest;
import com.appcare.followconnect.SignUp.Beans.SignUpBeanResponse;
import com.appcare.followconnect.SignUp.Beans.SignupBeanRequest;
import com.appcare.followconnect.SignUp.CountrySpinner.CountryBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class SignupPresenter {

    Context mcontext;
    SignupView signupView;
    APIResponse apiResponse;
    ArrayList<CountryBean> countrylist = new ArrayList<>();
    ArrayList<CountryBean> countrylist1 = new ArrayList<>();

    public SignupPresenter(SignUpActivity signUpActivity, SignupView signupView, APIResponse apiResponse) {
        this.mcontext = signUpActivity;
        this.signupView = signupView;
        this.apiResponse = apiResponse;
    }

    public void getCountryes() {

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
                                    if (signupView != null && apiResponse != null) {
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

    public void postSignUp(SignupBeanRequest signupBeanRequest) {

        ((Activity) mcontext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                apiResponse.showProgress();
            }
        });

        try {
            if (Constants.isNetworkAvailable(mcontext)) {
                Call<SignUpBeanResponse> call = RequestClient.getClient().create(APIInterface.class).signUpResponse(signupBeanRequest);
                call.enqueue(new Callback<SignUpBeanResponse>() {
                    @Override
                    public void onResponse(Call<SignUpBeanResponse> call, retrofit2.Response<SignUpBeanResponse> response) {
                        try {
                            SignUpBeanResponse signUpBeanResponse = response.body();
                            apiResponse.dismissProgress();
                            if (signUpBeanResponse.getStatus()) {


                                apiResponse.onSuccess(signUpBeanResponse);

                            } else {
                                apiResponse.onServerError(signUpBeanResponse.getMessage());
                            }

                        } catch (Exception e) {
                            apiResponse.dismissProgress();
                            apiResponse.onServerError(mcontext.getResources().getString(R.string.server_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<SignUpBeanResponse> call, Throwable t) {
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


    public void getrandomusername(RandomusernameBeanRequest randomuserName, RandonusernameCallbacks randonusernameCallbacks) {


        ((Activity) mcontext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                apiResponse.showProgress();
            }
        });

        try {
            if (Constants.isNetworkAvailable(mcontext)) {
                Call<RandomUsernameBeanResponse> call = RequestClient.getClient().create(APIInterface.class).generateRandomUsername(randomuserName);
                call.enqueue(new Callback<RandomUsernameBeanResponse>() {
                    @Override
                    public void onResponse(Call<RandomUsernameBeanResponse> call, retrofit2.Response<RandomUsernameBeanResponse> response) {
                        try {
                            RandomUsernameBeanResponse randomUsernameResponse = response.body();
                            apiResponse.dismissProgress();
                            if (randomUsernameResponse.getStatus()) {
                                AppPreference.getInstance().put(Constants.UserName, randomUsernameResponse.getData());

                                randonusernameCallbacks.getRandomUsername(randomUsernameResponse.getData());

                            } else {

                                apiResponse.onServerError(randomUsernameResponse.getMessage());
                            }

                        } catch (Exception e) {
                            apiResponse.dismissProgress();
                            apiResponse.onServerError(mcontext.getResources().getString(R.string.server_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<RandomUsernameBeanResponse> call, Throwable t) {
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


