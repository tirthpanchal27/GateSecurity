package com.example.gatesecurutiyapp.Home.features;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.gatesecurutiyapp.MyAdapter;
import com.example.gatesecurutiyapp.R;
import com.example.gatesecurutiyapp.fetchResident;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class residents extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<fetchResident> residentArrayList;
    MyAdapter myAdapter;
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
        residentArrayList = new ArrayList<fetchResident>();
        myAdapter = new MyAdapter(residents.this, residentArrayList);

        recyclerView.setAdapter(myAdapter);

        EventChangeListener();
    }

    private void EventChangeListener() {

        db.collection("users").orderBy("name", Query.Direction.ASCENDING)
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

                                residentArrayList.add(dc.getDocument().toObject(fetchResident.class));
                            }

                            myAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }
}