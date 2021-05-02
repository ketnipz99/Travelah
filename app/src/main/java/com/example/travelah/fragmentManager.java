package com.example.travelah;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class fragmentManager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_manager);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        //Show home fragment upon logging in/launching
        getSupportFragmentManager().beginTransaction().replace(
                R.id.fragmentContainer, new homeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment  = null;

                    switch(item.getItemId()) {

                        case R.id.nav_home:
                            selectedFragment = new homeFragment();
                            break;

                        case R.id.nav_featured:
                            selectedFragment = new featuredFragment();
                            break;

                        case R.id.nav_state:
                            selectedFragment = new stateFragment();
                            break;

                        case R.id.nav_settings:
                            selectedFragment = new settingsFragment();
                            break;

                    }
                    getSupportFragmentManager().beginTransaction().replace(
                            R.id.fragmentContainer, selectedFragment).addToBackStack(null).commit();

                    return true;
                }
            };
}