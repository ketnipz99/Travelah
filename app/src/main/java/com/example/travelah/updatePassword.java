package com.example.travelah;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import static android.provider.ContactsContract.Intents.Insert.EMAIL;

public class updatePassword extends AppCompatActivity {

    EditText oldPassword, newPassword, newPasswordConfirmation;
    Button updatePassword;
    ImageButton back;
    UserDatabaseHelper dbUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);

        oldPassword = findViewById(R.id.etPrevPassword);
        newPassword = findViewById(R.id.etNewPassword1);
        newPasswordConfirmation = findViewById(R.id.etNewPassword2);
        updatePassword = findViewById(R.id.btnUpdatePw);
        back = findViewById(R.id.ibBackPassword);
        dbUser = new UserDatabaseHelper(this);

        updatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Read the user's input
                String current_pw = oldPassword.getText().toString();
                String new_pw = newPassword.getText().toString();
                String confirmation_pw = newPasswordConfirmation.getText().toString();

                SharedPreferences preferencesEmail = getSharedPreferences(EMAIL, MODE_PRIVATE);
                String email = preferencesEmail.getString("email", "");


                //Condition check on user's input
                if (current_pw.equals("") || new_pw.equals("") || confirmation_pw.equals("")) {
                    Toast.makeText(updatePassword.this, "All fields are required to be filled up", Toast.LENGTH_SHORT).show();
                }
                    else if (new_pw.equals(current_pw)) {
                        Toast.makeText(updatePassword.this, "New password cannot be similar as current password.", Toast.LENGTH_SHORT).show();
                    }
                        else if (!new_pw.equals(confirmation_pw)) {
                            Toast.makeText(updatePassword.this, "New password does not match with the Confirmation password", Toast.LENGTH_SHORT).show();
                        }
                            else {
                                //Use UserDatabaseHelper class to authenticate user current password
                                Boolean authenticateUser = dbUser.authenticateUser(email, current_pw);

                                if (!authenticateUser) {
                                    Toast.makeText(updatePassword.this, "Invalid current password", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Boolean updatePw = dbUser.updatePassword(email, new_pw);

                                    if (updatePw) {
                                        Toast.makeText(updatePassword.this, "Password is updated", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Toast.makeText(updatePassword.this, "Fail to update password", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
            }
        });

        //gt bug cannot go back
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    onBackPressed();
            }
        });
    }
}