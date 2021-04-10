package com.appcare.followconnect.spoolvid;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.appcare.followconnect.Common.Constants;

import com.appcare.followconnect.R;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.util.Util;

public class SpoolvidVideoPLayingActivity extends AppCompatActivity  {

    ExoPlayer simpleExoPlayer=null;
    private BandwidthMeter bandwidthMeter;
    private ExtractorsFactory extractorsFactory;
    private AdaptiveTrackSelection.Factory trackSelectionFactory;
    private TrackSelector trackSelector;
    private PlayerView playerView;
    private int currentWindow = 0;
    private long playbackPosition = 0;
    private boolean currPlayWhenReady = true;
    String videourl=null;
    String toolbarname=null;
    ImageButton imgbtn_backvideoplay=null;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spoolvid_video_p_laying);


        getSupportActionBar().hide();
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));



        playerView=findViewById(R.id.ic_exoplayer);
        imgbtn_backvideoplay=findViewById(R.id.imgbtn_backvideoplay);

        Intent intent=getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
             videourl = (String) bd.get("videourl");
             toolbarname = (String) bd.get(Constants.ToolbarName);
        }

        initializePlayer();

        imgbtn_backvideoplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }



    private void initializePlayer() {
        if (simpleExoPlayer == null) {
            bandwidthMeter = new DefaultBandwidthMeter();
            extractorsFactory = new DefaultExtractorsFactory();
            trackSelectionFactory = new AdaptiveTrackSelection.Factory();
            trackSelector = new DefaultTrackSelector();
            simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(SpoolvidVideoPLayingActivity.this, trackSelector);
            playerView.setPlayer(simpleExoPlayer);
            simpleExoPlayer.setPlayWhenReady(currPlayWhenReady);
            simpleExoPlayer.seekTo(currentWindow, playbackPosition);

            playerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);

            setSource(videourl);

        }
    }



    public void setSource(String source) {
        MediaSource mediaSource = Constants.buildMediaSource(source, null,SpoolvidVideoPLayingActivity.this);
        if (mediaSource != null) {
            if (simpleExoPlayer != null) {
                simpleExoPlayer.prepare(mediaSource, true, true);
            }
        }
    }



    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 24) {
            initializePlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if ((Util.SDK_INT <= 24|| simpleExoPlayer == null)) {
            initializePlayer();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 24) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 24) {
            releasePlayer();
        }
    }

    private void releasePlayer() {
        if (simpleExoPlayer != null) {
            playbackPosition = simpleExoPlayer.getCurrentPosition();
            currentWindow = simpleExoPlayer.getCurrentWindowIndex();
            currPlayWhenReady = simpleExoPlayer.getPlayWhenReady();
            simpleExoPlayer.release();
            simpleExoPlayer = null;
        }
    }


}