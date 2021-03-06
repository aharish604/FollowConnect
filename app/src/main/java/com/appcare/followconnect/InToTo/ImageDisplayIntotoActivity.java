package com.appcare.followconnect.InToTo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.appcare.followconnect.InToTo.Bean.intotopublic;
import com.appcare.followconnect.R;
import com.appcare.followconnect.SimpleRecyclerView.AdapterInterface;

import java.util.ArrayList;

public class ImageDisplayIntotoActivity extends AppCompatActivity {


    RecyclerView rv_intotoimgdisplay;
ArrayList<intotopublic> imagelist=null;
ImageButton imgbtn_searchuserprofile=null;
String imagesarray[];
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display_intoto);

        getSupportActionBar().hide();
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));


        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
      //  imagelist = (ArrayList<intotopublic>) args.getSerializable("imagelistintoto");


        imagesarray=args.getStringArray("imagelistintoto");

        InitUI();

    }

    private void InitUI() {

        rv_intotoimgdisplay=findViewById(R.id.rv_intotoimagedisplay);
        imgbtn_searchuserprofile=findViewById(R.id.imgbtn_searchuserprofile);
        rv_intotoimgdisplay.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        rv_intotoimgdisplay.setAdapter(new ImageViewAdapter(ImageDisplayIntotoActivity.this,imagesarray));

        imgbtn_searchuserprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }


}