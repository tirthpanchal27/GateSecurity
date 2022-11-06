package com.example.gatesecurutiyapp.Home.features;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gatesecurutiyapp.Home.Settings;
import com.example.gatesecurutiyapp.R;
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

public class announcements extends AppCompatActivity {

    EditText editannouncement;
    Button sendann;
    StorageReference storageReference;
    FirebaseAuth auth;
    FirebaseFirestore fStore;
    DocumentReference documentReference;
    String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcements);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        sendann = (Button) findViewById(R.id.sendann);
        editannouncement = (EditText) findViewById(R.id.Announcmentsedit);

        auth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        storageReference = FirebaseStorage.getInstance().getReference();

        FirebaseUser user = auth.getInstance().getCurrentUser();
        userId = user.getUid();
        documentReference = fStore.collection("announcements").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                editannouncement.setText(documentSnapshot.getString("announcements"));
            }
        });

        sendann.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile3();
            }
        });
    }

    private void updateProfile3() {
        String announcements = editannouncement.getText().toString();


        final DocumentReference sDoc = fStore.collection("announcements").document(userId);
        fStore.runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(Transaction transaction) throws FirebaseFirestoreException {

                transaction.update(sDoc, "announcements", announcements);


                return null;
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(announcements.this, "updated", Toast.LENGTH_SHORT).show();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(announcements.this, "added", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}