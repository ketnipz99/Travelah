package com.example.travelah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import static android.provider.ContactsContract.Intents.Insert.EMAIL;

public class updateUsername extends AppCompatActivity {

    EditText newUsername;
    Button submitUsername;
    ImageButton back;
    private UserDatabaseHelper dbUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_username);

        newUsername = findViewById(R.id.etEditUsername);
        submitUsername = findViewById(R.id.btnSubmitUsername);
        back = findViewById(R.id.ibBackUsername);
        dbUser = new UserDatabaseHelper(this);


        SharedPreferences preferencesEmail = getSharedPreferences(EMAIL, MODE_PRIVATE);
        String email = preferencesEmail.getString("email", "");

        submitUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String new_username = newUsername.getText().toString();
                Boolean updateProfile = dbUser.updateUsername (email, new_username);

                if(updateProfile) {
                    Toast.makeText(updateUsername.this, "Username updated.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(updateUsername.this, "Failed to update username. ", Toast.LENGTH_SHORT).show();
                }

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}