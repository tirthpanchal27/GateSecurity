package com.example.gatesecurutiyapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gatesecurutiyapp.Home.Profile;

public class GuardHome extends AppCompatActivity {

    private EditText name,number,message,address;
    private Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardhome);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        Button guardprofile_btn = (Button) findViewById(R.id.guardprofilebutton);

        guardprofile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuardHome.this,
                        Profile.class);

                startActivity(intent);
            }
        });
        name =findViewById(R.id.visitorname);
        number = findViewById(R.id.visitornumber);
        message = findViewById(R.id.typemsg);
        address = findViewById(R.id.ownerhome);
        send = findViewById(R.id.sendbtn);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                        sendSMS();
                    }
                    else
                    {
                        requestPermissions(new String[]{Manifest.permission.SEND_SMS},1);
                    }
                }
            }
        });
    }
    private void sendSMS()
    {
        String Vname = name.getText().toString().trim();
        String phoneNo = number.getText().toString().trim();
        String SMS = message.getText().toString().trim();
        String addr = address.getText().toString().trim();



        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo,null, Vname ,null, null);
            smsManager.sendTextMessage(phoneNo,null, phoneNo ,null, null);
            smsManager.sendTextMessage(phoneNo,null, SMS ,null, null);
            smsManager.sendTextMessage(phoneNo,null, addr ,null, null);
            Toast.makeText(GuardHome.this, "Message Sent!!", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(GuardHome.this, "Failed to send Message!!", Toast.LENGTH_SHORT).show();
        }



    }
}