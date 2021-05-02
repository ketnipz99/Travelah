package com.example.travelah;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class privacyPolicy extends AppCompatActivity {

    TextView privacyPolicyDetails;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        back = findViewById(R.id.ibBackPrivacyPolicy);
        privacyPolicyDetails = findViewById(R.id.tvPrivacyDetails);

        privacyPolicyDetails.setText(R.string.privacy_policy);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}