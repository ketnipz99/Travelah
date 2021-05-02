package com.example.travelah;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class credits extends AppCompatActivity {

    TextView creditsDetails;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        back = findViewById(R.id.ibBackCredits);
        creditsDetails = findViewById(R.id.tvCreditsDetails);

        creditsDetails.setText(R.string.credits);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}