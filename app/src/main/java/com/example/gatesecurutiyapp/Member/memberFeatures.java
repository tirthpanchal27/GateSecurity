package com.example.gatesecurutiyapp.Member;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gatesecurutiyapp.Home.features.EmergencyNo;
import com.example.gatesecurutiyapp.Home.features.Features;
import com.example.gatesecurutiyapp.Home.features.announcements;
import com.example.gatesecurutiyapp.Home.features.rent_sale;
import com.example.gatesecurutiyapp.Home.features.residents;
import com.example.gatesecurutiyapp.Home.features.socservices;
import com.example.gatesecurutiyapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class memberFeatures extends AppCompatActivity {
    CardView emergencycv;
    CardView announcmentcv;
    CardView socservicecv, residentcv;
    FirebaseAuth auth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(ContextCompat.getColor(memberFeatures.this, R.color.appblack));
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_features);
        emergencycv = (CardView) findViewById(R.id.emergencycv);
        announcmentcv = (CardView) findViewById(R.id.announcementcv);
        socservicecv = (CardView) findViewById(R.id.socservicecv);
        residentcv = (CardView) findViewById(R.id.residentcv);

        auth = FirebaseAuth.getInstance();
        fStore =FirebaseFirestore.getInstance();

        residentcv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(memberFeatures.this,
                        residents.class);

                startActivity(intent);
            }
        });
        emergencycv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(memberFeatures.this,
                        memberEmergencyNo.class);

                startActivity(intent);
            }
        });

        socservicecv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(memberFeatures.this,
                        membersocservices.class);

                startActivity(intent);
            }
        });

        announcmentcv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(memberFeatures.this,
                        memberAnnouncements.class);

                startActivity(intent);
            }
        });
    }
}