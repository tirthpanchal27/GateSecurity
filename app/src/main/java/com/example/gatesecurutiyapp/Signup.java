package com.example.gatesecurutiyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText uName, uNumber, uCity, uEmail, uPass, uCPass;
    CheckBox rGuard, rMember;
    Button signup_btn;

    FirebaseAuth auth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        uName = findViewById(R.id.editTextTextPersonName2);
        uNumber = findViewById(R.id.editTextPhone);
        uCity = findViewById(R.id.editTextTextPersonCity);
        uEmail = findViewById(R.id.editTextTextEmailAddress);
        uPass = findViewById(R.id.editTextTextPassword);
        uCPass = findViewById(R.id.editTextTextPassword3);
        rGuard = findViewById(R.id.radiobutton3);
        rMember = findViewById(R.id.radiobutton4);
        Button signup_btn = (Button) findViewById(R.id.button2);

        auth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = uName.getText().toString();
                String number = uNumber.getText().toString();
                String city = uCity.getText().toString();
                String email = uEmail.getText().toString().trim();
                String pass = uPass.getText().toString().trim();
                String cpass = uCPass.getText().toString();
                String guard = rGuard.getText().toString();
                String membr = rMember.getText().toString();

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

                auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            FirebaseUser fuser = auth.getCurrentUser();
                            fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(Signup.this,"Verification Email has been sent.", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG,"onFailure: Email not sent."+ e.getMessage());
                                }
                            });
                            Toast.makeText(Signup.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            userID = auth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("name",name);
                            user.put("email",email);
                            user.put("number",number);
                            user.put("password",pass);
                            user.put("city",city);

                            if(rGuard.isChecked())
                            {
                                user.put("member",guard);
                            }
                            if(rMember.isChecked())
                            {
                                user.put("member", membr);
                            }
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG, "onSucccess: user profile is created for" +userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG,"onFailure: "+ e.toString());
                                }
                            });
                            startActivity(new Intent(Signup.this, MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(Signup.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}