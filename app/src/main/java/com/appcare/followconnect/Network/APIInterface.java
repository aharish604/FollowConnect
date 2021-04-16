package com.appcare.followconnect.Network;

import com.appcare.followconnect.Chat.Bean.CharBeanRequest;
import com.appcare.followconnect.Chat.Bean.ChatHistoryBeanRequest;
import com.appcare.followconnect.Chat.Bean.ChatHistoryBeanResponse;
import com.appcare.followconnect.Chat.Bean.ChatListBeanResponse;
import com.appcare.followconnect.Chat.Bean.MessageSendRequest;
import com.appcare.followconnect.Chat.Bean.MessageSendResponse;
import com.appcare.followconnect.Comments.model.CommentsListInputs;
import com.appcare.followconnect.Comments.model.CommentsListResponse;
import com.appcare.followconnect.ForgotPassword.ForgotResponse;
import com.appcare.followconnect.Home.fragments.FeedLikeInputs;
import com.appcare.followconnect.InToTo.Bean.IntotoRequestBean;
import com.appcare.followconnect.InToTo.Bean.IntotoResponse;
import com.appcare.followconnect.Login.LoginResponse;
import com.appcare.followconnect.LoginWithGoogle.Bean.LoginWithGoogleBeanRequest;
import com.appcare.followconnect.LoginWithGoogle.Bean.LoginWithGoogleResponseBean1;
import com.appcare.followconnect.MyviewPostdisplay.FeedLikeResponse;
import com.appcare.followconnect.MyviewPostdisplay.bean.BlockResponse;
import com.appcare.followconnect.MyviewPostdisplay.bean.BlockerInputs;
import com.appcare.followconnect.MyviewPostdisplay.bean.DeleteFeedInputs;
import com.appcare.followconnect.MyviewPostdisplay.bean.DeleteResponse;
import com.appcare.followconnect.MyviewPostdisplay.bean.GetPostFeedResponse;
import com.appcare.followconnect.MyviewPostdisplay.bean.GetPostRequestBean;
import com.appcare.followconnect.Notifications.Bean.NotificationRequestBean;
import com.appcare.followconnect.Notifications.Bean.NotificationResponsebean;
import com.appcare.followconnect.Profile.Bean.ProfileBeanRequest;
import com.appcare.followconnect.Profile.Bean.ProfileBeanResponse;
import com.appcare.followconnect.Profile.Bean.UserFeedRequest;
import com.appcare.followconnect.Profile.Bean.UserFeedResponseBean;
import com.appcare.followconnect.Profile.FriendsList.Bean.FollowersRequestBean;
import com.appcare.followconnect.Profile.FriendsList.Bean.FollowersResponseBean;
import com.appcare.followconnect.Profile.FriendsList.Bean.FriendsListRequestBean;
import com.appcare.followconnect.Profile.FriendsList.Bean.FriendsListResponseBean;
import com.appcare.followconnect.ProfileUpdate.ProfileUpdateBeanResponse;
import com.appcare.followconnect.ProfileUpdate.UpdateProfileBean;
import com.appcare.followconnect.ProfileUpdate.UpdateProfilePhotoResponse;
import com.appcare.followconnect.SearchFriends.AddasaFriend.AddfriendRequestBean;
import com.appcare.followconnect.SearchFriends.AddasaFriend.AddfrinedResponseBean;
import com.appcare.followconnect.SearchFriends.Bean.SearchDataInsertBeanRequest;
import com.appcare.followconnect.SearchFriends.Bean.SearchDataInsertBeanResponse;
import com.appcare.followconnect.SearchFriends.Bean.SearchHistoryBeanRequest;
import com.appcare.followconnect.SearchFriends.Bean.SearchHistoryBeanResponse;
import com.appcare.followconnect.SearchFriends.Bean.UserFriendsInuts;
import com.appcare.followconnect.SearchFriends.Bean.UserNameSearchBeanRequest;
import com.appcare.followconnect.SearchFriends.Bean.UserNameSearchBeanResponse;
import com.appcare.followconnect.SearchFriends.UserFriendsFeedResponse;
import com.appcare.followconnect.SearchFriends.followandunfollow.followResponseBean;
import com.appcare.followconnect.SearchFriends.followandunfollow.followrequestbean;
import com.appcare.followconnect.SearchFriends.unfriend.UnfriendRequestBean;
import com.appcare.followconnect.SearchFriends.unfriend.UnfriendResponseBean;
import com.appcare.followconnect.Settings.DeleteAccount.AccountDeleteRequestBean;
import com.appcare.followconnect.Settings.DeleteAccount.AccountDeleteResponseBean;
import com.appcare.followconnect.Settings.BlockerList.UnblockRequestBean;
import com.appcare.followconnect.Settings.BlockerList.UnblockResponseBean;
import com.appcare.followconnect.Settings.BlockerList.BlockerListRequestBean;
import com.appcare.followconnect.Settings.BlockerList.BlockerListResponseBean;
import com.appcare.followconnect.Settings.ChnagePassword.ChangePasswordRequestBean;
import com.appcare.followconnect.Settings.ChnagePassword.ChangePasswordResponseBean;
import com.appcare.followconnect.Settings.ChnagePassword.HelpandSupportResponseBean;
import com.appcare.followconnect.Settings.PrivacyPolicy.PrivacyPolicyResponeseBean;
import com.appcare.followconnect.SignUp.Beans.RandomUsernameBeanResponse;
import com.appcare.followconnect.SignUp.Beans.RandomusernameBeanRequest;
import com.appcare.followconnect.ForgotPassword.ForgotPasswordBean;
import com.appcare.followconnect.Login.LoginBean;
import com.appcare.followconnect.SignUp.Beans.SignUpBeanResponse;
import com.appcare.followconnect.SignUp.Beans.SignupBeanRequest;
import com.appcare.followconnect.UploadPost.UploadPostResponse;
import com.appcare.followconnect.View.ViewBeanRequest;
import com.appcare.followconnect.View.ViewBeanResponse;
import com.appcare.followconnect.spoolvid.Bean.SpoolvidRequestBean;
import com.appcare.followconnect.spoolvid.Bean.SpoolvidResponseBean;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface APIInterface {

    @POST("register/register")
    Call<SignUpBeanResponse> signUpResponse(@Body SignupBeanRequest signupBeanRequest);

    @POST("register/login")
    Call<LoginResponse> loginResponse(@Body LoginBean loginBean);

    @POST("register/Forgot_password")
    Call<ForgotResponse> forgotpasswordResponse(@Body ForgotPasswordBean forgotPasswordBean);

    @POST("register/random_username")
    Call<RandomUsernameBeanResponse> generateRandomUsername(@Body RandomusernameBeanRequest randomuserName);


    @POST("Register/social_login")
    Call<LoginWithGoogleResponseBean1> loginwithResponse(@Body LoginWithGoogleBeanRequest loginWithGoogleBeanRequest);

    @POST("Register/profile_details")
    Call<ProfileBeanResponse> getProfileData(@Body ProfileBeanRequest profilebeanpostbean);

    @POST("Register/profile_update")
    Call<ProfileUpdateBeanResponse> updateprofiledata(@Body UpdateProfileBean updateProfileBean);

    @Multipart
    @POST("Feed/feedPost")
    Call<UploadPostResponse> uploadpostwithvideos(@Part("uid") RequestBody uid,
                                                  @Part("feedtext") RequestBody comment,
                                                  @Part("address") RequestBody address,
                                                  @Part("isspoolvid") RequestBody ispoolvid,
                                                  @Part("privicy") RequestBody privicy,
                                                  @Part MultipartBody.Part[] videosarray);


    @Multipart
    @POST("Feed/feedPost")
    Call<UploadPostResponse> uploadpostwithimages(@Part("uid") RequestBody uid,
                                                  @Part("feedtext") RequestBody comment,
                                                  @Part("address") RequestBody address,
                                                  @Part("isspoolvid") RequestBody ispoolvid,
                                                  @Part("privicy") RequestBody privicy,
                                                  @Part MultipartBody.Part[] multipartImages);


    @Multipart
    @POST("Feed/feedPost")
    Call<UploadPostResponse> uploadpost(@Part("uid") RequestBody uid,
                                        @Part("feedtext") RequestBody comment,
                                        @Part("address") RequestBody address,
                                        @Part("isspoolvid") RequestBody ispoolvid,
                                        @Part("privicy") RequestBody privicy,
                                        @Part MultipartBody.Part[] multipartImages);


    @POST("Feed/getFeed")
    Call<GetPostFeedResponse> getFeedresponse(@Body GetPostRequestBean getPostRequestBean);

    @POST("Networks/getSerachList")
    Call<SearchHistoryBeanResponse> getSearchList(@Body SearchHistoryBeanRequest searchHistoryBeanRequest);

    @POST("Networks/UserNamesearch")
    Call<UserNameSearchBeanResponse> SearchFriends(@Body UserNameSearchBeanRequest searchBeanRequest);

    @POST("Networks/SearchdataInsert")
    Call<SearchDataInsertBeanResponse> insertUserSearch(@Body SearchDataInsertBeanRequest searchDataInsertBeanRequest);

    @POST("Chat/get_friend_list")
    Call<ChatListBeanResponse> getChatList(@Body CharBeanRequest charBeanRequest);


    @Multipart
    @POST("register/profile_photo_update")
    Call<UpdateProfilePhotoResponse> UpdateProfileData(@Part("user_id") RequestBody uid,
                                                       @Part MultipartBody.Part imagearray);


    @POST("Chat/chat_history")
    Call<ChatHistoryBeanResponse> getChatHistory(@Body ChatHistoryBeanRequest charBeanRequest);

    @POST("Chat/send")
    Call<MessageSendResponse> sendmsg(@Body MessageSendRequest messageSendRequest);

    @POST("Feed/getspoolvid")
    Call<SpoolvidResponseBean> getSpoolvid(@Body SpoolvidRequestBean spoolvidRequestBean);

    @POST("Test/getInTOTO")
    Call<IntotoResponse> getIntoto(@Body IntotoRequestBean intotoRequestBean);

    @POST("Networks/notificationList")
    Call<NotificationResponsebean> getNotificationList(@Body NotificationRequestBean notificationRequestBean);

    @POST("Register/change_password")
    Call<ChangePasswordResponseBean> changePassword(@Body ChangePasswordRequestBean bean);


    @Multipart
    @POST("Feed/spoolVideoUpload")
    Call<UploadPostResponse> spoolvideopost(@Part("uid") RequestBody uid,
                                            @Part("feedtext") RequestBody comment,
                                            @Part("address") RequestBody address,
                                            @Part("isspoolvid") RequestBody ispoolvid,
                                            @Part("privicy") RequestBody privicy,
                                            @Part MultipartBody.Part videosarray);


    @POST("Feed/getUserFeed")
    Call<UserFeedResponseBean> getUserFeed(@Body UserFeedRequest userFeedRequest);


    @POST("Feed/getUserFriendStaus")
    Call<UserFriendsFeedResponse> friendsUserFeed(@Body UserFriendsInuts userFriendWrapper);

    @POST("Networks/FriendsList")
    Call<FriendsListResponseBean> getFreindsList(@Body FriendsListRequestBean friendsListRequestBean);

    @POST("Networks/FollowingList")
    Call<FollowersResponseBean> getFollowersList(@Body FollowersRequestBean followersRequestBean);

    @POST("Feed/userFeddactivity")
    Call<FeedLikeResponse> postLikes(@Body FeedLikeInputs inputs);


    @GET("Register/privacy_policy")
    Call<PrivacyPolicyResponeseBean> getPrivacyPolicy();

    @GET("Register/help_support")
    Call<HelpandSupportResponseBean> getHelpandSupport();

    @POST("Register/account_delete")
    Call<AccountDeleteResponseBean> deleteAccount(@Body AccountDeleteRequestBean bean);

    @POST("Networks/blockerList")
    Call<BlockerListResponseBean> getBlockerList(@Body BlockerListRequestBean bean);

    @POST("Chat/unblock_user")
    Call<UnblockResponseBean> unblockuser(@Body UnblockRequestBean bean);


    @POST("Chat/block_user")
    Call<BlockResponse> block(@Body BlockerInputs blockWrapper);

    @POST("Feed/deleteFeed")
    Call<DeleteResponse> deleteFeed(@Body DeleteFeedInputs deleteInputs);

    @POST("Feed/commentsList")
    Call<CommentsListResponse> getCommentsList(@Body CommentsListInputs inputs);

    @POST("Networks/unFriend")
    Call<UnfriendResponseBean> unfriend(@Body UnfriendRequestBean inputs);

    @POST("Networks/sendRequest")
    Call<followResponseBean> sendunfollowrequest(@Body followrequestbean inputs);

    @POST("Feed/feedView")
    Call<ViewBeanResponse> feedview(@Body ViewBeanRequest inputs);


    @Multipart
    @POST("Feed/feedEdit")
    Call<UploadPostResponse> feeEditdpostImages(@Part("uid") RequestBody uid,
                                                @Part("feedtext") RequestBody comment,
                                                @Part("address") RequestBody address,
                                                @Part("isspoolvid") RequestBody ispoolvid,
                                                @Part("privicy") RequestBody privicy,
                                                @Part("feed_id") RequestBody feed_id,
                                                @Part MultipartBody.Part[] multipartImages);


    @Multipart
    @POST("Feed/feedEdit")
    Call<UploadPostResponse> feeEditdpostVideos(@Part("uid") RequestBody uid,
                                                @Part("feedtext") RequestBody comment,
                                                @Part("isspoolvid") RequestBody ispoolvid,
                                                @Part("privicy") RequestBody privicy,
                                                @Part("feed_id") RequestBody feed_id,
                                                @Part MultipartBody.Part[] videosarray);


    @POST("Networks/addFriend")
    Call<AddfrinedResponseBean> addFriend(@Body AddfriendRequestBean inputs);


}
