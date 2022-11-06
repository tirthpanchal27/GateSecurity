package com.example.gatesecurutiyapp.Member;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gatesecurutiyapp.Home.Home;
import com.example.gatesecurutiyapp.Home.Profile;
import com.example.gatesecurutiyapp.Home.features.EmergencyNo;
import com.example.gatesecurutiyapp.Home.features.Features;
import com.example.gatesecurutiyapp.Home.features.announcements;
import com.example.gatesecurutiyapp.Home.features.residents;
import com.example.gatesecurutiyapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class memberHome extends AppCompatActivity {

    TextView username;
    FirebaseAuth auth;
    FirebaseFirestore fStore;
    StorageReference storageReference;
    DocumentReference documentReference;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().setStatusBarColor(ContextCompat.getColor(memberHome.this, R.color.appblack));
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        Button profile_button = (Button) findViewById(R.id.profilebutton);
        Button sharebtn= findViewById(R.id.sharebtn);
        CardView viewall = findViewById(R.id.viewallcv);
        CardView residentcv = findViewById(R.id.residentcv);
        CardView emergencycv = findViewById(R.id.emergencycv);
        CardView announcementcv = findViewById(R.id.announcementcv);

        username = findViewById(R.id.username);

        auth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        FirebaseUser user = auth.getInstance().getCurrentUser();
        userId = user.getUid();
        documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                username.setText(documentSnapshot.getString("name"));
            }
        });

        profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(memberHome.this,
                        memberProfile.class);
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
                Intent intent = new Intent(memberHome.this, memberFeatures.class);
                startActivity(intent);
            }
        });

        residentcv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(memberHome.this, residents.class);
                startActivity(intent);
            }
        });

        emergencycv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(memberHome.this, memberEmergencyNo.class);
                startActivity(intent);
            }
        });

        announcementcv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(memberHome.this, memberAnnouncements.class);
                startActivity(intent);
            }
        });
    }
}