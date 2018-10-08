package com.example.rahul.a04animation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void fade1(View v){
        ImageView im1 = (ImageView)findViewById(R.id.imageViewOne);
        ImageView im2 = (ImageView)findViewById(R.id.imageView2);
        im1.setVisibility(View.VISIBLE);
        im2.animate().alpha(0).setDuration(2000);
        im1.animate().alpha(1).setDuration(2000);
        im2.setVisibility(View.GONE);
    }
    public void fade2(View v){
        ImageView im1 = (ImageView)findViewById(R.id.imageViewOne);
        ImageView im2 = (ImageView)findViewById(R.id.imageView2);
        im2.setVisibility(View.VISIBLE);
        im1.animate().alpha(0).setDuration(2000);
        im2.animate().alpha(1).setDuration(2000);
        im1.setVisibility(View.GONE);
    }

}

