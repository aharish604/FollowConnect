package com.appcare.followconnect.Settings;

import android.app.Activity;
import android.content.Context;

import com.appcare.followconnect.Chat.ResponseSucessCallback;
import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.Network.APIInterface;
import com.appcare.followconnect.Network.APIResponse;
import com.appcare.followconnect.Network.RequestClient;
import com.appcare.followconnect.Profile.Bean.ProfileBeanResponse;
import com.appcare.followconnect.Profile.ProfileActivity;
import com.appcare.followconnect.R;
import com.appcare.followconnect.Settings.ChnagePassword.ChangePasswordRequestBean;
import com.appcare.followconnect.Settings.ChnagePassword.ChangePasswordResponseBean;
import com.appcare.followconnect.Settings.ChnagePassword.HelpandSupportResponseBean;

import retrofit2.Call;
import retrofit2.Callback;

public class SettingsPresenter {


    Context mcontext;
    APIResponse apiResponse;
    public SettingsPresenter(Context context, APIResponse apiResponse) {
        this.mcontext = context;
        this.apiResponse = apiResponse;
    }


    public void chnagePassword(ChangePasswordRequestBean bean) {


        ((Activity) mcontext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                apiResponse.showProgress();
            }
        });

        try {
            if (Constants.isNetworkAvailable(mcontext)) {
                Call<ChangePasswordResponseBean> call = RequestClient.getClient().create(APIInterface.class).changePassword(bean);
                call.enqueue(new Callback<ChangePasswordResponseBean>() {
                    @Override
                    public void onResponse(Call<ChangePasswordResponseBean> call, retrofit2.Response<ChangePasswordResponseBean> response) {
                        try {
                            ChangePasswordResponseBean changePasswordResponseBean = response.body();
                            apiResponse.dismissProgress();
                            if (changePasswordResponseBean.getStatus()) {
                                apiResponse.onSuccess(changePasswordResponseBean);
                            } else {
                                apiResponse.onServerError(changePasswordResponseBean.getMessage());
                            }

                        } catch (Exception e) {
                            apiResponse.dismissProgress();
                            apiResponse.onServerError(mcontext.getResources().getString(R.string.server_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<ChangePasswordResponseBean> call, Throwable t) {
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

    public void getPrivacyPOlicy(ResponseSucessCallback responseSucessCallback) {



        ((Activity) mcontext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                apiResponse.showProgress();
            }
        });

        try {
            if (Constants.isNetworkAvailable(mcontext)) {
                Call<PrivacyPolicyResponeseBean> call = RequestClient.getClient().create(APIInterface.class).getPrivacyPolicy();
                call.enqueue(new Callback<PrivacyPolicyResponeseBean>() {
                    @Override
                    public void onResponse(Call<PrivacyPolicyResponeseBean> call, retrofit2.Response<PrivacyPolicyResponeseBean> response) {
                        try {
                            PrivacyPolicyResponeseBean policyResponeseBean = response.body();
                            apiResponse.dismissProgress();
                            if (policyResponeseBean.getStatus()) {
                                responseSucessCallback.responseSucess(policyResponeseBean);
                            } else {
                                apiResponse.onServerError(policyResponeseBean.getMessage());
                            }

                        } catch (Exception e) {
                            apiResponse.dismissProgress();
                            apiResponse.onServerError(mcontext.getResources().getString(R.string.server_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<PrivacyPolicyResponeseBean> call, Throwable t) {
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

    public void getHelpandSupport(ResponseSucessCallback responseSucessCallback) {


        ((Activity) mcontext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                apiResponse.showProgress();
            }
        });

        try {
            if (Constants.isNetworkAvailable(mcontext)) {
                Call<HelpandSupportResponseBean> call = RequestClient.getClient().create(APIInterface.class).getHelpandSupport();
                call.enqueue(new Callback<HelpandSupportResponseBean>() {
                    @Override
                    public void onResponse(Call<HelpandSupportResponseBean> call, retrofit2.Response<HelpandSupportResponseBean> response) {
                        try {
                            HelpandSupportResponseBean policyResponeseBean = response.body();
                            apiResponse.dismissProgress();
                            if (policyResponeseBean.getStatus()) {
                                responseSucessCallback.responseSucess(policyResponeseBean);
                            } else {
                                apiResponse.onServerError(policyResponeseBean.getMessage());
                            }

                        } catch (Exception e) {
                            apiResponse.dismissProgress();
                            apiResponse.onServerError(mcontext.getResources().getString(R.string.server_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<HelpandSupportResponseBean> call, Throwable t) {
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

    public void deleteAccount(AccountDeleteRequestBean bean, ResponseSucessCallback responseSucessCallback) {



        ((Activity) mcontext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                apiResponse.showProgress();
            }
        });

        try {
            if (Constants.isNetworkAvailable(mcontext)) {
                Call<AccountDeleteResponseBean> call = RequestClient.getClient().create(APIInterface.class).deleteAccount(bean);
                call.enqueue(new Callback<AccountDeleteResponseBean>() {
                    @Override
                    public void onResponse(Call<AccountDeleteResponseBean> call, retrofit2.Response<AccountDeleteResponseBean> response) {
                        try {
                            AccountDeleteResponseBean accountDeleteResponseBean = response.body();
                            apiResponse.dismissProgress();
                            if (accountDeleteResponseBean.getStatus()) {
                                responseSucessCallback.responseSucess(accountDeleteResponseBean);
                            } else {
                                apiResponse.onServerError(accountDeleteResponseBean.getMessage());
                            }

                        } catch (Exception e) {
                            apiResponse.dismissProgress();
                            apiResponse.onServerError(mcontext.getResources().getString(R.string.server_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<AccountDeleteResponseBean> call, Throwable t) {
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
