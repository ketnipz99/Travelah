package com.example.travelah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import static android.provider.ContactsContract.Intents.Insert.EMAIL;

public class logIn extends AppCompatActivity {

    private EditText email, password;
    private Button logIn;
    private ImageButton back;
    private CheckBox rememberMe;
    private UserDatabaseHelper dbUser;
    Boolean checkRememberMe = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        email = findViewById(R.id.etEmailLogIn);
        password = findViewById(R.id.etPasswordLogIn);
        logIn = findViewById(R.id.btnLogIn2);
        back = findViewById(R.id.ibBackLogIn);
        rememberMe = findViewById(R.id.cbRememberMe);
        dbUser = new UserDatabaseHelper(this);


        //Read the value of rememberMe upon user log in
        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String checkboxValue = preferences.getString("remember", "");

        //If checked, user no need to log in again next time unless is logged out
        if(checkboxValue.equals("true")) {
            Intent intent = new Intent(logIn.this, fragmentManager.class);
            startActivity(intent);
            finish();
        }
        else if (checkboxValue.equals("false")) {
            Toast.makeText(this, "Log in with your email and password.", Toast.LENGTH_LONG).show();

        }


        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_email = email.getText().toString();
                String user_password = password.getText().toString();

                SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                if (user_email.equals("") || user_password.equals("")) {
                    Toast.makeText(logIn.this, "Please enter all the fields", Toast.LENGTH_LONG).show();
                } else {
                    Boolean checkUser = dbUser.checkRedundancy(user_email);

                    if(checkUser) {

                        Boolean userAuthentication = dbUser.authenticateUser(user_email, user_password);

                        if (userAuthentication) {
                            //Store the value of user email so that later can be used in other activities

                            if(checkRememberMe.equals(true)) {
                                editor.putString("remember", "true");
                            } else {
                                editor.putString("remember", "false");
                            }
                            editor.apply();

                            SharedPreferences.Editor editorEmail = getSharedPreferences(EMAIL, MODE_PRIVATE).edit();
                            editorEmail.putString("email", user_email);
                            editorEmail.apply();

                            Toast.makeText(logIn.this, "WELCOME", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent (getApplicationContext(), fragmentManager.class);
                            startActivity(intent);
                            finish();

                        } else {
                            Toast.makeText(logIn.this, "Invalid login. Incorrect email or password.", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(logIn.this, "Email not registered. Please sign up", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


        rememberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    checkRememberMe = true;

                } else if (!buttonView.isChecked())
                    checkRememberMe = false;
                }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (logIn.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}