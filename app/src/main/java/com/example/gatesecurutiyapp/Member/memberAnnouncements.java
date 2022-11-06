package com.example.gatesecurutiyapp.Member;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.gatesecurutiyapp.Home.features.residents;
import com.example.gatesecurutiyapp.MyAdapter;
import com.example.gatesecurutiyapp.MyAdapter3;
import com.example.gatesecurutiyapp.R;
import com.example.gatesecurutiyapp.fetchResident;
import com.example.gatesecurutiyapp.fetchannouncements;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class memberAnnouncements extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<fetchannouncements> announcementArrayList;
    MyAdapter3 myAdapter3;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_residents);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        announcementArrayList = new ArrayList<fetchannouncements>();
        myAdapter3 = new MyAdapter3(memberAnnouncements.this, announcementArrayList);

        recyclerView.setAdapter(myAdapter3);

        EventChangeListener();
    }

    private void EventChangeListener() {

        db.collection("announcements")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if(error != null)
                        {

                            Log.e("Error", error.getMessage());
                            return;
                        }

                        for(DocumentChange dc: value.getDocumentChanges())
                        {
                            if(dc.getType() == DocumentChange.Type.ADDED){

                                announcementArrayList.add(dc.getDocument().toObject(fetchannouncements.class));
                            }

                            myAdapter3.notifyDataSetChanged();
                        }
                    }
                });
    }
}