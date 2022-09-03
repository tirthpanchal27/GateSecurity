package com.example.gatesecurutiyapp.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gatesecurutiyapp.Home.features.Features;
import com.example.gatesecurutiyapp.R;
import com.example.gatesecurutiyapp.selecthome;

public class Home extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().setStatusBarColor(ContextCompat.getColor(Home.this, R.color.appblack));
        //ActionBar actionBar = getSupportActionBar();
        //actionBar.hide();
        Button profile_button = (Button) findViewById(R.id.profilebutton);
        Button addhome = findViewById(R.id.addhome);
        Button sharebtn= findViewById(R.id.sharebtn);
        CardView viewall = findViewById(R.id.viewallcv);

        profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,
                        Profile.class);
                startActivity(intent);
            }
        });

        addhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, selecthome.class);
                startActivity(intent);
            }
        });

        sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String Body = "Download this App";
                String Sub = "http://play.google.com";
                intent.putExtra(Intent.EXTRA_TEXT, Body);
                intent.putExtra(Intent.EXTRA_TEXT, Sub);
                startActivity(Intent.createChooser(intent, "Share using"));
            }
        });

        viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Features.class);
                startActivity(intent);
            }
        });

    }
}