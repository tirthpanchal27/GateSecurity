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
import com.example.gatesecurutiyapp.MyAdapter2;
import com.example.gatesecurutiyapp.R;
import com.example.gatesecurutiyapp.fetchResident;
import com.example.gatesecurutiyapp.fetchsocservice;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class membersocservices extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<fetchsocservice> fetchsocserviceArrayList;
    MyAdapter2 myAdapter2;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membersocservices);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        fetchsocserviceArrayList = new ArrayList<fetchsocservice>();
        myAdapter2 = new MyAdapter2(membersocservices.this, fetchsocserviceArrayList);

        recyclerView.setAdapter(myAdapter2);

        EventChangeListener();
    }

    private void EventChangeListener()
    {
        db.collection("socservice")
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

                                fetchsocserviceArrayList.add(dc.getDocument().toObject(fetchsocservice.class));
                            }

                            myAdapter2.notifyDataSetChanged();
                        }
                    }
                });
    }
}