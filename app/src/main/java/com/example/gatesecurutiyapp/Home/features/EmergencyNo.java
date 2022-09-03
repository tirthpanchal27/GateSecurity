package com.example.gatesecurutiyapp.Home.features;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.gatesecurutiyapp.R;

public class EmergencyNo extends AppCompatActivity {

    TextView police;
    TextView firestation;
    TextView ambulance;
    TextView citizen;
    TextView missingchild;
    TextView road;
    TextView railway;
    TextView organ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_no);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        police = findViewById(R.id.police);
        firestation = findViewById(R.id.firestation);
        ambulance = findViewById(R.id.ambulance);
        citizen = findViewById(R.id.citizen);
        missingchild = findViewById(R.id.missingchild);
        road = findViewById(R.id.road);
        railway = findViewById(R.id.railway);
        organ = findViewById(R.id.organ);

        police.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:100"));
                startActivity(intent);
            }
        });

        firestation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:101"));
                startActivity(intent);
            }
        });
        ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:108"));
                startActivity(intent);
            }
        });
        citizen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:1091"));
                startActivity(intent);
            }
        });
        missingchild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:1094"));
                startActivity(intent);
            }
        });
        road.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:1073"));
                startActivity(intent);
            }
        });
        railway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:1072"));
                startActivity(intent);
            }
        });

        organ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:1060"));
                startActivity(intent);
            }
        });
    }

}