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

public class signUp extends AppCompatActivity {

    private ImageButton back;
    private EditText username, email, password, rePassword;
    private Button signUp;
    private UserDatabaseHelper dbUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        back = findViewById(R.id.ibBack);
        username = findViewById(R.id.etName);
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        rePassword = findViewById(R.id.etRePassword);
        signUp = findViewById(R.id.btnSignup2);
        dbUser = new UserDatabaseHelper(this);


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user_name = username.getText().toString();
                String user_email = email.getText().toString();
                String user_pw = password.getText().toString();
                String user_repw = rePassword.getText().toString();

                if(user_name.equals("") || user_email.equals("") || user_pw.equals("") || user_repw.equals("")) {
                    Toast.makeText(signUp.this, "Please enter the required field", Toast.LENGTH_LONG).show();
                }
                else {
                    if (user_pw.equals(user_repw)) {
                        //Check user exist or not
                        Boolean userRedundancy = dbUser.checkRedundancy(user_email);

                        if(!userRedundancy) {

                            Boolean insert = dbUser.insertData(user_name, user_email, user_pw);

                            if(insert) {
                                //Share the value of email for user profile updates in other activities
                                SharedPreferences.Editor editor = getSharedPreferences(EMAIL, MODE_PRIVATE).edit();
                                editor.putString("email", user_email);
                                editor.apply();

                                Toast.makeText(signUp.this, "Registered successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), fragmentManager.class);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Toast.makeText(signUp.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(signUp.this, "Email already registered. Please log in.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(signUp.this, "Password not matched.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (signUp.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}