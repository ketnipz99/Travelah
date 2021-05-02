package com.example.travelah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button logIn, signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        logIn = findViewById(R.id.btnLogin);
        signUp = findViewById(R.id.btnSignup);

        //If user logged in with checked RememberMe, skip the welcome page
        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String checkbox = preferences.getString("remember", "");

        //If not logged out, skip welcome page and proceed to home
        if(checkbox.equals("true")) {
            Intent intent = new Intent(MainActivity.this, fragmentManager.class);
            startActivity(intent);
            finish();
        }

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this, logIn.class);
                startActivity(intent);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this, signUp.class);
                startActivity(intent);
            }
        });
    }
}