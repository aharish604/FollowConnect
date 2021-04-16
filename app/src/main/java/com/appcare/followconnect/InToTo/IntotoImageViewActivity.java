package com.appcare.followconnect.InToTo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.appcare.followconnect.R;
import com.bumptech.glide.Glide;
import com.jsibbold.zoomage.ZoomageView;

public class IntotoImageViewActivity extends AppCompatActivity {

    ZoomageView img_intotimageview;
    String uri="";

    ImageButton imgback_intotoImageview=null;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intoto_image_view);

        getSupportActionBar().hide();
       // getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));



        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        if(args!=null)
        {
            uri=args.getString("imageurl");

        }




        img_intotimageview=findViewById(R.id.img_intotimageview);
        imgback_intotoImageview=findViewById(R.id.imgback_intotoImageview);





        Glide.with(IntotoImageViewActivity.this)
                .load(uri)
                .fitCenter()
                .into(img_intotimageview);

        imgback_intotoImageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




    }
}