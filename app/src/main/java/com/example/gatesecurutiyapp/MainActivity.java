package com.example.gatesecurutiyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gatesecurutiyapp.Home.Home;
import com.example.gatesecurutiyapp.Home.features.Features;
import com.example.gatesecurutiyapp.Member.memberHome;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {


    EditText uEmail, uPass;
    Button uLogin, forgotTextLink;
    TextView uSignup, noacc;
    FirebaseAuth auth;
    FirebaseFirestore fStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        uLogin = (Button) findViewById(R.id.loginbtn);
        uEmail = (EditText) findViewById(R.id.editTextTextEmailAddress);
        uPass = (EditText) findViewById(R.id.editTextTextPassword);
        noacc = findViewById(R.id.noacc);
        uSignup =findViewById(R.id.signupbtn);
        forgotTextLink = findViewById(R.id.forgotbtn);
        auth = FirebaseAuth.getInstance();
        fStore =FirebaseFirestore.getInstance();

        uSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        Signup.class);

                startActivity(intent);
            }
        });




        uLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = uEmail.getText().toString().trim();
                String pass = uPass.getText().toString().trim();



                if (TextUtils.isEmpty(email)) {
                    uEmail.setError("Email is Required");
                    return;
                }

                if (TextUtils.isEmpty(pass)) {
                    uPass.setError("Password is Required");
                    return;
                }

                if (pass.length() < 6) {
                    uPass.setError("Password must be greater than or equal to 6 character");
                }


                //if (valid) {
                auth.signInWithEmailAndPassword(email, pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                            Toast.makeText(MainActivity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            checkUserAccessLevel(authResult.getUser().getUid());
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override

                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

             //   }
             /*   else if (member == FirebaseDatabase.getInstance().getReference("users").orderByChild("member")) {
                    auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), GuardHome.class));
                            } else {
                                Toast.makeText(MainActivity.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                 }*/

            }
        });


        forgotTextLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText resetMail = new EditText(view.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(view.getContext());
                passwordResetDialog.setMessage("Enter your email to receive Reset link.");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String mail = resetMail.getText().toString();
                        auth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(MainActivity.this, "Reset link send to your email.", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this, "Error! Reset link is not sent." + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                    }
                });
                passwordResetDialog.create().show();
            }
        });
    }

    private void checkUserAccessLevel(String uid) {
        DocumentReference documentReference = fStore.collection("users").document(uid);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("TAG","onSuccess" + documentSnapshot.getData());

                if (documentSnapshot.getString("isMember")!=null)
                {
                    Intent intent = new Intent(MainActivity.this, memberHome.class);
                    startActivity(intent);
                    finish();
                }
                if (documentSnapshot.getString("isGuard")!=null)
                {
                    Intent intent = new Intent(MainActivity.this, GuardHome.class);
                    startActivity(intent);
                    finish();
                }
                if (documentSnapshot.getString("isAdmin")!=null)
                {
                    Intent intent = new Intent(MainActivity.this, Home.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}