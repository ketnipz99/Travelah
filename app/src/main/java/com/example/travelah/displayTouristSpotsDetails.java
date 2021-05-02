package com.example.travelah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class displayTouristSpotsDetails extends AppCompatActivity {

    ImageButton back;
    Button visitWebsite;
    TextView title, description_1, description_2;
    ImageView image_1, image_2;
    FloatingActionButton backToTop;
    ScrollView sv;
    TourismInfo chosenInfo;
    TravelahDbAccessHelper travelahDbAccessHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_tourist_spots_details);

        Intent intent = getIntent();
        int chosenId = intent.getIntExtra("chosenSpots", 0);

        back = findViewById(R.id.ibBackDetails);
        visitWebsite = findViewById(R.id.btnVisitWebsite);
        title = findViewById(R.id.tvTitle);
        description_1 = findViewById(R.id.tvDesc_1);
        description_2 = findViewById(R.id.tvDesc_2);
        image_1 = findViewById(R.id.ivImage_1);
        image_2 = findViewById(R.id.ivImage_2);
        backToTop = findViewById(R.id.fabTouristSpotsDetails);
        sv = findViewById(R.id.svDetails);

        travelahDbAccessHelper = new TravelahDbAccessHelper(this);
        travelahDbAccessHelper.openTravelahDb();

        chosenInfo = travelahDbAccessHelper.getChosenSpotsDetails(chosenId);

        showAllDetails();

        travelahDbAccessHelper.closeTravelahDb();


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        visitWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Implicit intent to allow user visit the original website
                String url = chosenInfo.getUrl();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);

            }
        });

        backToTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sv.scrollTo(0, 0);
            }
        });
    }

    public void showAllDetails() {

        title.setText(chosenInfo.getTitle());
        description_1.setText(chosenInfo.getDesc_1());
        description_2.setText(chosenInfo.getDesc_2());
        image_1.setBackground(new BitmapDrawable(getResources(), chosenInfo.getImage_1()));
        image_2.setBackground(new BitmapDrawable(getResources(), chosenInfo.getImage_2()));

        sv.smoothScrollTo(0,0);

    }
}