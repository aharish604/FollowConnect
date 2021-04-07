package com.appcare.followconnect.InToTo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.appcare.followconnect.InToTo.Bean.VideoViewAdapter;
import com.appcare.followconnect.InToTo.Bean.intotopublic;
import com.appcare.followconnect.R;

import java.util.ArrayList;

public class VideodisplayIntotoActivity extends AppCompatActivity {

    ArrayList<intotopublic> videolist=null;
    RecyclerView rv_intotovideodisplay;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videodisplay_intoto);


        getSupportActionBar().hide();
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));


        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        videolist = (ArrayList<intotopublic>) args.getSerializable("imagelistintoto");



        InitUI();

    }

    private void InitUI() {

        rv_intotovideodisplay=findViewById(R.id.rv_intotovideodisplay);
        rv_intotovideodisplay.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        rv_intotovideodisplay.setAdapter(new VideoViewAdapter(VideodisplayIntotoActivity.this,videolist));





    }

}