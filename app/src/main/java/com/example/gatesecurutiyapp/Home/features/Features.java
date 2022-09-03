package com.example.gatesecurutiyapp.Home.features;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gatesecurutiyapp.Home.Home;
import com.example.gatesecurutiyapp.MainActivity;
import com.example.gatesecurutiyapp.R;
import com.example.gatesecurutiyapp.Signup;

public class Features extends AppCompatActivity {
    CardView emergencycv;
    CardView announcmentcv;
    CardView socservicecv;
    CardView rentsellcv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(ContextCompat.getColor(Features.this, R.color.appblack));
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_features);
        emergencycv = (CardView) findViewById(R.id.emergencycv);
        announcmentcv = (CardView) findViewById(R.id.announcementcv);
        socservicecv = (CardView) findViewById(R.id.socservicecv);
        rentsellcv = (CardView) findViewById(R.id.rentsellcv);

        emergencycv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Features.this,
                        EmergencyNo.class);

                startActivity(intent);
            }
        });

        socservicecv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Features.this,
                        socservices.class);

                startActivity(intent);
            }
        });

        announcmentcv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Features.this,
                        announcements.class);

                startActivity(intent);
            }
        });

        rentsellcv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Features.this,
                        rent_sale.class);

                startActivity(intent);
            }
        });
    }
}