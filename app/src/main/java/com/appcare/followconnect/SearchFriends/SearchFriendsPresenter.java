package com.appcare.followconnect.SearchFriends;

import android.app.Activity;
import android.content.Context;

import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.Login.LoginActivity;
import com.appcare.followconnect.LoginWithGoogle.Bean.LoginWithGoogleResponseBean1;
import com.appcare.followconnect.Network.APIInterface;
import com.appcare.followconnect.Network.APIResponse;
import com.appcare.followconnect.Network.RequestClient;
import com.appcare.followconnect.R;
import com.appcare.followconnect.SearchFriends.Bean.SearchDataInsertBeanRequest;
import com.appcare.followconnect.SearchFriends.Bean.SearchDataInsertBeanResponse;
import com.appcare.followconnect.SearchFriends.Bean.SearchHistoryBeanRequest;
import com.appcare.followconnect.SearchFriends.Bean.SearchHistoryBeanResponse;
import com.appcare.followconnect.SearchFriends.Bean.UserNameSearchBeanRequest;
import com.appcare.followconnect.SearchFriends.Bean.UserNameSearchBeanResponse;

import retrofit2.Call;
import retrofit2.Callback;

public class SearchFriendsPresenter {

    Context mcontext;
    APIResponse apiResponse;

    public SearchFriendsPresenter(SearchFriendsActivity searchFriendsActivity, APIResponse apiResponse) {
        this.mcontext = searchFriendsActivity;
        this.apiResponse = apiResponse;
    }

    public void getSearchHistory(SearchHistoryBeanRequest bean, GetSearchHistoryCallback getSearchHistoryCallback) {

        ((Activity) mcontext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                apiResponse.showProgress();
            }
        });

        try {
            if (Constants.isNetworkAvailable(mcontext)) {
                Call<SearchHistoryBeanResponse> call = RequestClient.getClient().create(APIInterface.class).getSearchList(bean);
                call.enqueue(new Callback<SearchHistoryBeanResponse>() {
                    @Override
                    public void onResponse(Call<SearchHistoryBeanResponse> call, retrofit2.Response<SearchHistoryBeanResponse> response) {
                        try {
                            SearchHistoryBeanResponse searchHistoryBeanResponse = response.body();
                            apiResponse.dismissProgress();
                            if (searchHistoryBeanResponse.getStatus()) {

                                getSearchHistoryCallback.getSearchHistory(searchHistoryBeanResponse);

                            } else {
                                apiResponse.onServerError(searchHistoryBeanResponse.getMessage());
                            }

                        } catch (Exception e) {
                            apiResponse.dismissProgress();
                            apiResponse.onServerError(mcontext.getResources().getString(R.string.server_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<SearchHistoryBeanResponse> call, Throwable t) {
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



    public void SearchFriend(UserNameSearchBeanRequest bean, GetSearchHistoryCallback getSearchHistoryCallback) {


        ((Activity) mcontext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                apiResponse.showProgress();
            }
        });

        try {
            if (Constants.isNetworkAvailable(mcontext)) {
                Call<UserNameSearchBeanResponse> call = RequestClient.getClient().create(APIInterface.class).SearchFriends(bean);
                call.enqueue(new Callback<UserNameSearchBeanResponse>() {
                    @Override
                    public void onResponse(Call<UserNameSearchBeanResponse> call, retrofit2.Response<UserNameSearchBeanResponse> response) {
                        try {
                            UserNameSearchBeanResponse userNameSearchBeanResponse = response.body();
                            apiResponse.dismissProgress();
                            if (userNameSearchBeanResponse.getStatus()) {

                                getSearchHistoryCallback.getSearchHistory(userNameSearchBeanResponse);

                            } else {
                                apiResponse.onServerError(userNameSearchBeanResponse.getMessage());
                            }

                        } catch (Exception e) {
                            apiResponse.dismissProgress();
                            apiResponse.onServerError(mcontext.getResources().getString(R.string.server_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<UserNameSearchBeanResponse> call, Throwable t) {
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

    public void insertSearchList(SearchDataInsertBeanRequest searchDataInsertBeanRequest) {


        ((Activity) mcontext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                apiResponse.showProgress();
            }
        });

        try {
            if (Constants.isNetworkAvailable(mcontext)) {
                Call<SearchDataInsertBeanResponse> call = RequestClient.getClient().create(APIInterface.class).insertUserSearch(searchDataInsertBeanRequest);
                call.enqueue(new Callback<SearchDataInsertBeanResponse>() {
                    @Override
                    public void onResponse(Call<SearchDataInsertBeanResponse> call, retrofit2.Response<SearchDataInsertBeanResponse> response) {
                        try {
                            SearchDataInsertBeanResponse userNameSearchBeanResponse = response.body();
                            apiResponse.dismissProgress();
                            if (userNameSearchBeanResponse.getStatus()) {
                                //  getSearchHistoryCallback.getSearchHistory(userNameSearchBeanResponse);
                                apiResponse.onSuccess(userNameSearchBeanResponse);
                            } else {
                                apiResponse.onServerError(userNameSearchBeanResponse.getMessage());
                            }

                        } catch (Exception e) {
                            apiResponse.dismissProgress();
                            apiResponse.onServerError(mcontext.getResources().getString(R.string.server_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<SearchDataInsertBeanResponse> call, Throwable t) {
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
