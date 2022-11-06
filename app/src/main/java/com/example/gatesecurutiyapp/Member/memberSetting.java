package com.example.gatesecurutiyapp.Member;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class memberSetting extends AppCompatActivity {
    EditText number, fullname, email, block, hnum, password;
    TextView imgbtn;
    Button changebtn;
    ImageView profileImage;
    StorageReference storageReference;
    FirebaseAuth auth;
    FirebaseFirestore fStore;
    DocumentReference documentReference;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        changebtn = (Button) findViewById(R.id.changebtn);
        fullname = findViewById(R.id.setname);
        email = findViewById(R.id.setemail);
        number = findViewById(R.id.setnumber);
        block = findViewById(R.id.setaddress);
        hnum = findViewById(R.id.setaddress2);
        password = findViewById(R.id.setpassword);
        profileImage = findViewById(R.id.setimage);
        imgbtn = findViewById(R.id.imgbtn);

        auth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference profileRef = storageReference.child("users/" + auth.getCurrentUser().getUid() + "profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profileImage);
            }
        });

        FirebaseUser user = auth.getInstance().getCurrentUser();
        userId = user.getUid();
        documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                number.setText(documentSnapshot.getString("number"));
                fullname.setText(documentSnapshot.getString("name"));
                email.setText(documentSnapshot.getString("email"));
                block.setText(documentSnapshot.getString("Block"));
                hnum.setText(documentSnapshot.getString("Home Number"));
                password.setText(documentSnapshot.getString("password"));

            }
        });

        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent, 1000);
            }
        });

        changebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile();
            }
        });
    }


    private void updateProfile() {
        String name = fullname.getText().toString();
        String num = number.getText().toString();
        String mail = email.getText().toString();
        String blocknum = block.getText().toString();
        String homenum = hnum.getText().toString();
        String pass = password.getText().toString();

        final DocumentReference sDoc = fStore.collection("users").document(userId);
        fStore.runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(Transaction transaction) throws FirebaseFirestoreException {

                transaction.update(sDoc, "name", name);
                transaction.update(sDoc, "email", mail);
                transaction.update(sDoc, "number", num);
                transaction.update(sDoc, "Block", blocknum);
                transaction.update(sDoc, "Home Number", homenum);
                transaction.update(sDoc, "password", pass);

                return null;
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(memberSetting.this, "updated", Toast.LENGTH_SHORT).show();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(memberSetting.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if (resultCode == Activity.RESULT_OK) {
                Uri imageUri = data.getData();
                //profileImage.setImageURI(imageUri);
                uploadImageToFirebase(imageUri);
            }
        }
    }

    private void uploadImageToFirebase(Uri imageUri) {
        StorageReference fileRef = storageReference.child("users/" + auth.getCurrentUser().getUid() + "profile.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(profileImage);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(memberSetting.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}