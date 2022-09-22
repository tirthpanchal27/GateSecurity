package com.example.gatesecurutiyapp.Home.features;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gatesecurutiyapp.GuardHome;
import com.example.gatesecurutiyapp.Home.Home;
import com.example.gatesecurutiyapp.MainActivity;
import com.example.gatesecurutiyapp.R;
import com.example.gatesecurutiyapp.Signup;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class socservices extends AppCompatActivity {
    EditText addplumber, addwater, addelectricity, addhousemaid, addcleaner, addwifiprovider;
    Button addnobtn;

    StorageReference storageReference;
    FirebaseAuth auth;
    FirebaseFirestore fStore;
    DocumentReference documentReference;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socservices);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        addplumber = findViewById(R.id.plumberedit);
        addwater = findViewById(R.id.Watersupplieredit);
        addelectricity = findViewById(R.id.electricityedit);
        addhousemaid = findViewById(R.id.housemaidedit);
        addcleaner = findViewById(R.id.cleaneredit);
        addwifiprovider = findViewById(R.id.Wifiprovideredit);
        addnobtn = findViewById(R.id.addnobtn);

        auth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();
        documentReference = fStore.collection("socservice").document(userId);

        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                addplumber.setText(documentSnapshot.getString("plumber"));
                addwater.setText(documentSnapshot.getString("water supplier"));
                addelectricity.setText(documentSnapshot.getString("electricity"));
                addhousemaid.setText(documentSnapshot.getString("house maid"));
                addcleaner.setText(documentSnapshot.getString("cleaner"));
                addwifiprovider.setText(documentSnapshot.getString("wifi provider"));
            }
        });

        addnobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile();
            }
        });
    }

    private void updateProfile() {
        String plumber = addplumber.getText().toString();
        String water = addwater.getText().toString();
        String electricity = addelectricity.getText().toString();
        String housemaid = addhousemaid.getText().toString();
        String cleaner = addcleaner.getText().toString();
        String wifiprovider = addwifiprovider.getText().toString();

        final DocumentReference sDoc = fStore.collection("socservice").document(userId);
        fStore.runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(Transaction transaction) throws FirebaseFirestoreException {

                transaction.update(sDoc, "plumber", plumber);
                transaction.update(sDoc, "water supplier", water);
                transaction.update(sDoc, "electricity", electricity);
                transaction.update(sDoc, "house maid", housemaid);
                transaction.update(sDoc, "cleaner", cleaner);
                transaction.update(sDoc, "wifi provider", wifiprovider);

                return null;
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(socservices.this, "added", Toast.LENGTH_SHORT).show();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(socservices.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}