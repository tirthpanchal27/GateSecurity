package com.example.gatesecurutiyapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splashscreen extends AppCompatActivity {
    Animation topAnim,bottomAnim;
    ImageView image;
    TextView logo;
    Handler mhandler;
    Runnable mRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        image = findViewById(R.id.imageView);
        logo = findViewById(R.id.tv_note);
        mhandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splashscreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
        mhandler.postDelayed(mRunnable, 2500);

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        image.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);
    }
}