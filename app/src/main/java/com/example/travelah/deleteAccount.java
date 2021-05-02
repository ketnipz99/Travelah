package com.example.travelah;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import static android.provider.ContactsContract.Intents.Insert.EMAIL;

public class deleteAccount extends AppCompatActivity {

    EditText userPassword;
    Button submit;
    ImageButton backDelete;
    UserDatabaseHelper dbUser;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_account);

        userPassword = findViewById(R.id.etPasswordDelete);
        submit = findViewById(R.id.btnConfirmDelete);
        backDelete = findViewById(R.id.ibBackDeleteAcc);
        dbUser = new UserDatabaseHelper(this);

        SharedPreferences preferencesEmail = getSharedPreferences(EMAIL, Context.MODE_PRIVATE);
        String email = preferencesEmail.getString("email", "");


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String enteredPassword = userPassword.getText().toString();

                if(userPassword.equals(""))
                {
                    Toast.makeText(deleteAccount.this, "Please enter your current password in order to proceed", Toast.LENGTH_LONG).show();
                } else {
                    Boolean userVerification = dbUser.authenticateUser(email, enteredPassword);

                    if (userVerification) {
                        deleteAccountAlertDialog(email);
                    } else  {
                        Toast.makeText(deleteAccount.this, "Incorrect password. Please try again.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        backDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void deleteAccountAlertDialog(String email) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("All data related to the account will be deleted.\nAre you sure you want to delete your account?");

        alertDialogBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {

                Boolean deleteAccount = dbUser.deleteUser(email);

                if (deleteAccount) {

                    preferences = getSharedPreferences("checkbox", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember", "false");
                    editor.putString("email", "");
                    editor.apply();

                    Toast.makeText(deleteAccount.this, "Account deleted. Please sign up or log in again.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(deleteAccount.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(deleteAccount.this, "Failed to delete your account. Please try again.", Toast.LENGTH_LONG).show();
                }
            }
        });

        alertDialogBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


}