package com.appcare.followconnect.Home.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appcare.followconnect.Chat.Bean.ChatHistoryBeanResponse;
import com.appcare.followconnect.Chat.ResponseSucessCallback;
import com.appcare.followconnect.Comments.CommentsActivity;
import com.appcare.followconnect.Common.AppPreference;
import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.Home.Adapter.MyviewAdapter;
import com.appcare.followconnect.MyviewPostdisplay.FeedLikeResponse;
import com.appcare.followconnect.MyviewPostdisplay.MyviewPresenter;
import com.appcare.followconnect.MyviewPostdisplay.bean.BlockResponse;
import com.appcare.followconnect.MyviewPostdisplay.bean.BlockerInputs;
import com.appcare.followconnect.MyviewPostdisplay.bean.DeleteFeedInputs;
import com.appcare.followconnect.MyviewPostdisplay.bean.DeleteResponse;
import com.appcare.followconnect.MyviewPostdisplay.bean.GetPostFeedBean;
import com.appcare.followconnect.MyviewPostdisplay.bean.GetPostFeedResponse;
import com.appcare.followconnect.Network.APIInterface;
import com.appcare.followconnect.Network.APIResponse;
import com.appcare.followconnect.Profile.ProfileActivity;
import com.appcare.followconnect.R;
import com.appcare.followconnect.SignUp.CountrySpinner.Adapterpositioncallback;
import com.appcare.followconnect.SignUp.CountrySpinner.CountryBean;
import com.appcare.followconnect.SignUp.SignUpActivity;
import com.appcare.followconnect.UploadPost.UploadPostActivity;
import com.appcare.followconnect.View.ViewBeanRequest;
import com.appcare.followconnect.View.ViewBeanResponse;
import com.appcare.followconnect.spoolvid.Bean.SpoolvidRequestBean;
import com.appcare.followconnect.spoolvid.Bean.SpoolvidResponseBean;
import com.appcare.followconnect.spoolvid.Bean.SpoolvidResponseBean1;
import com.appcare.followconnect.spoolvid.SpoolvidAdapter;
import com.appcare.followconnect.spoolvid.SpoolvidUploadPostActivity;
import com.appcare.followconnect.spoolvid.SpoolvidVideoPLayingActivity;
import com.appcare.followconnect.spoolvid.spoolvidPresenter;
import com.omega_r.libs.omegaintentbuilder.OmegaIntentBuilder;
import com.omega_r.libs.omegaintentbuilder.downloader.DownloadCallback;
import com.omega_r.libs.omegaintentbuilder.handlers.ContextIntentHandler;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.callback.Callback;

public class SpooLvidFragment extends Fragment implements APIResponse {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String api_TAG="";

    RecyclerView spoolvid_rv = null;
    ImageButton upload_img = null;
    ProgressDialog progressDialog = null;
    SpoolvidAdapter adapter = null;
    spoolvidPresenter presenter = null;
    ArrayList<SpoolvidResponseBean1> data=null;

    public SpooLvidFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SpooLvidFragment newInstance(String param1, String param2) {
        SpooLvidFragment fragment = new SpooLvidFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_spoolvid, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spoolvid_rv = view.findViewById(R.id.spoolvid_rv);
        upload_img = view.findViewById(R.id.upload_img);
        spoolvid_rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        AppPreference.getInstance(getActivity());

        getSpoolVid();

        upload_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SpoolvidUploadPostActivity.class));

            }
        });


    }

    private void getSpoolVid() {


        presenter = new spoolvidPresenter(getActivity(), this);


        SpoolvidRequestBean bean = new SpoolvidRequestBean();
        bean.setUid(Constants.getUid(getActivity()));

        presenter.getspoolvid(bean, new ResponseSucessCallback() {

            @Override
            public void responseSucess(Object object) {

                dismissProgress();

                SpoolvidResponseBean spoolvidResponseBean = (SpoolvidResponseBean) object;

                 data=spoolvidResponseBean.getData();

                setadapter(data);

            }
        });


    }


    public void getdapterPosition() {
        adapter.adapterPosition(new Adapterpositioncallback() {
            @Override
            public void getadapterposition(Object object, int pos) {
                SpoolvidResponseBean1 bean = (SpoolvidResponseBean1) object;

                Intent intent=new Intent(getActivity(), SpoolvidVideoPLayingActivity.class);
               intent.putExtra("videourl",bean.getVf());
                intent.putExtra(Constants.ToolbarName,"SpoolVid");

                startActivity(intent);

            }
        });

    }


    private void setadapter(ArrayList<SpoolvidResponseBean1> data) {

         adapter=new SpoolvidAdapter(getActivity(),data,this);
        spoolvid_rv.setAdapter(adapter);
        getdapterPosition();


    }

    @Override
    public void onSuccess(Object object) {

    }

    @Override
    public void onServerError(String error) {
        dismissProgress();
        Constants.displayLongToast(getActivity(), error);
    }


    @Override
    public void showProgress() {
        progressDialog = Constants.showProgressDialog(getActivity(), "");
    }

    @Override
    public void dismissProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void networkError(String error) {
        dismissProgress();
        Constants.displayLongToast(getActivity(), error.toString());
    }

    public void whatsAppShare(String fileuri, String sid, String feed) {

        showProgress();

        String[] imagesarray=null;
        ArrayList<String> list=new ArrayList<>();
        list.clear();
        if (!fileuri.equalsIgnoreCase("")) {
            imagesarray = fileuri.split(",");
        }

        for(String ch:imagesarray)
        {

            list.add(ch);
        }

        OmegaIntentBuilder.from(getActivity())
                .share()
                //.emailTo("your_email_here@gmail.com")
                //.subject("Great library")
                .filesUrls()
                .filesUrls(list)
                // .fileUrlWithMimeType("https://avatars1.githubusercontent.com/u/28600571?s=200&v=4", MimeTypes.IMAGE_PNG)
                .download(new DownloadCallback() {
                    @Override
                    public void onDownloaded(boolean success, @NotNull ContextIntentHandler contextIntentHandler) {

                        dismissProgress();
                        contextIntentHandler.startActivity();
                    }
                });

    }


    public void blockuser(String blockerId) {
        BlockerInputs inputs = new BlockerInputs();

        inputs.setBlock_id(blockerId);
        inputs.setUser_id(Constants.getUid(getActivity()));

        block(inputs);
    }

    private void block(BlockerInputs inputs) {

        MyviewPresenter presenter=new MyviewPresenter(getActivity(),this);
        presenter.block(inputs, new ResponseSucessCallback() {
            @Override
            public void responseSucess(Object object) {
                BlockResponse blockResponse =  (BlockResponse) object;
                Constants.displayLongToast(getActivity(),blockResponse.getMessage());
            }
        });
    }


    public void deleteFeed(String feedid, int position) {
        DeleteFeedInputs inputs = new DeleteFeedInputs();

        inputs.setUid(Constants.getUid(getActivity()));
        inputs.setFeed_id(feedid);

        MyviewPresenter presenter=new MyviewPresenter(getActivity(),this);

        presenter.deleteFeed(inputs, new ResponseSucessCallback() {
            @Override
            public void responseSucess(Object object) {
                DeleteResponse deleteResponse =  (DeleteResponse) object;
                //   Toast.makeText(getActivity(), ""+deleteResponse.getMessage(), Toast.LENGTH_SHORT).show();
                Constants.displayLongToast(getActivity(),deleteResponse.getMessage());
                data.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void commentsClick(String feedid, String postid, int count, int position) {
        Intent i = new Intent(getActivity(), CommentsActivity.class);
        i.putExtra("FEEDID", feedid);
        i.putExtra("POSTID", postid);
        startActivity(i);
    }



    public void edit(String feedId,String url,String feed) {
        Intent i = new Intent(getActivity(), UploadPostActivity.class);
        i.putExtra("FEEDID", feedId);
        i.putExtra("commingfrom", "editfeed");
        i.putExtra("feedtext", feed);
        i.putExtra("Imageurls",url);
        startActivity(i);
    }


    public void likes(int position, String feedid, String postid, int count, int likeStatus) {
        FeedLikeInputs inputs = new FeedLikeInputs();
        inputs.setFeed_id(feedid);
        inputs.setPoster_id(postid);
        inputs.setCommenter_id(Constants.getUid(getActivity()));
        inputs.setComment("");
        inputs.setLike("1");
        inputs.setDislike("");
        inputs.setShare("");
        inputs.setView("");
        api_TAG = "Likes";
        int countvalu;
        if (likeStatus == 0) {
            likeStatus = 1;
            countvalu = count + 1;
        } else {
            likeStatus = 0;
            countvalu = count - 1;
        }
        postLikes(inputs, position, api_TAG, countvalu, likeStatus);
    }

    private void postLikes(FeedLikeInputs inputs, int position, String likes, int countvalu, int likeStatus) {
      MyviewPresenter  presenter = new MyviewPresenter(getActivity(), this);
      presenter.postLikes(inputs, new ResponseSucessCallback() {
            @Override
            public void responseSucess(Object object) {
                FeedLikeResponse feedLikeResponse = (FeedLikeResponse) object;
                Toast.makeText(getActivity(), "" + feedLikeResponse.getMessage(), Toast.LENGTH_SHORT).show();
                if (api_TAG.equals("Likes")) {
                    data.get(position).setLikesCount(countvalu);
                    data.get(position).setLikes(likeStatus);
                    adapter.notifyDataSetChanged();
                } else if (api_TAG.equals("DisLikes")) {
                  //  data.get(position).setLikesCount(countvalu);
                    data.get(position).setLikes(likeStatus);
                    data.get(position).setDislikeCount(countvalu);
                    adapter.notifyDataSetChanged();
                }
            }
        });

    }


    public void disLikes(int position, String feedid, String postid, int count, int likeStatus) {
        FeedLikeInputs inputs = new FeedLikeInputs();
        inputs.setFeed_id(feedid);
        inputs.setPoster_id(postid);
        inputs.setCommenter_id(Constants.getUid(getActivity()));
        inputs.setComment("");
        inputs.setLike("0");
        inputs.setDislike("1");
        inputs.setShare("");
        inputs.setView("");
        api_TAG = "DisLikes";

        int countvalu;

        if (likeStatus == 0) {
            likeStatus = 1;
            countvalu = count + 1;
        } else {
            likeStatus = 0;
            countvalu = count - 1;
        }
        postLikes(inputs, position, api_TAG, countvalu, likeStatus);
    }

}
