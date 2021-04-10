package com.appcare.followconnect.MyviewPostdisplay;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import com.appcare.followconnect.Common.Constants;
import com.appcare.followconnect.R;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class ImageSliderActivity extends AppCompatActivity {

    SliderView sliderView;
    SliderAdapterExample adapter;
    String imageuri;
    String[] imagesarray = null;
    String toolbarname;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_slider);

        getSupportActionBar().hide();
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));


        Intent intent=getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
            imageuri = (String) bd.get("imageurl");
            toolbarname = (String) bd.get(Constants.ToolbarName);
        }


        if(imageuri!=null) {
            if (!imageuri.isEmpty()) {
                imagesarray = imageuri.split(",");

            }
        }





        sliderView = findViewById(R.id.imageSlider);
        adapter = new SliderAdapterExample(this,imagesarray);
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();

    }



}