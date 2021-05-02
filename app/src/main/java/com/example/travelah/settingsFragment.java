package com.example.travelah;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import static android.provider.ContactsContract.Intents.Insert.EMAIL;

public class settingsFragment extends Fragment {

    TextView username, userEmail;
    Button updateUsername, updatePassword, creditsButton, privacyPolicyButton, logOut, deleteAccount;
    private SharedPreferences preferences;
    UserDatabaseHelper dbUser;
    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        context = view.getContext();

        username = view.findViewById(R.id.tvUsername);
        userEmail = view.findViewById(R.id.tvEmail);
        updateUsername = view.findViewById(R.id.btnUpdateUsername);
        updatePassword = view.findViewById(R.id.btnUpdatePassword);
        creditsButton = view.findViewById(R.id.btnCredit);
        privacyPolicyButton = view.findViewById(R.id.btnPrivacyPolicy);
        logOut = view.findViewById(R.id.btnLogout);
        deleteAccount = view.findViewById(R.id.btnDeleteAcc);
        dbUser = new UserDatabaseHelper(view.getContext());

        SharedPreferences preferencesEmail = getActivity().getSharedPreferences(EMAIL, Context.MODE_PRIVATE);
        String email = preferencesEmail.getString("email", "");
        Cursor cursor = dbUser.fetchUsername(email);


        if (cursor.getCount() >= 1) {
            while (cursor.moveToNext()) {
                //Setting the displayed name and email in setting page
                username.setText(cursor.getString(0));
                userEmail.setText(email);
            }
            cursor.close();
        }

        updateUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), updateUsername.class);
                startActivity(intent);
            }
        });

        updatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), updatePassword.class);
                startActivity(intent);
            }
        });

        creditsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(context, credits.class);
               context.startActivity(intent);
            }
        });

        privacyPolicyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, privacyPolicy.class);
                context.startActivity(intent);
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                preferences = getActivity().getSharedPreferences("checkbox", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember", "false");
                editor.putString("email", "");
                editor.apply();

                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();

            }

        });

        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), deleteAccount.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }

}
