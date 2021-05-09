package com.example.vcall;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class DashboardActivity extends AppCompatActivity {

    EditText secretCodeBox;
    Button joinBtn, shareBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        secretCodeBox = findViewById(R.id.codebox);
        joinBtn = findViewById(R.id.joinbtn);
        shareBtn = findViewById(R.id.sharebtn);
     URL serverURL;

     try{
         serverURL = new URL("https://meet.jit.si");
         JitsiMeetConferenceOptions defaultOptions = new JitsiMeetConferenceOptions.Builder()
                 .setServerURL(serverURL)
                 .setWelcomePageEnabled(false)
                 .build();

         JitsiMeet.setDefaultConferenceOptions(defaultOptions);
     } catch (MalformedURLException e){
         e.printStackTrace();
     }


        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                        .setRoom(secretCodeBox.getText().toString())
                        .setWelcomePageEnabled(false)
                        .build();

                JitsiMeetActivity.launch(DashboardActivity.this, options);
            }
        });

    }
}