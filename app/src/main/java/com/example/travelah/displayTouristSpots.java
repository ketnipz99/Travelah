package com.example.travelah;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class displayTouristSpots extends AppCompatActivity implements TourismViewAdapter.TourismViewHolder.OnListListener {

    private RecyclerView touristSpotRecyclerView;
    private List<TourismInfo> tourismInfoList;
    private List<TourismInfo> passAdapterList;

    public String chosenFeaturedCat, chosenState;
    int tourismInfoCounter;
    int getTourismInfoCountTotal;
    TourismInfo currentTourismInfo;
    private TravelahDbAccessHelper travelahDbAccessHelper;

    ImageButton back;
    TextView userOption;
    FloatingActionButton backToTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_tourist_spots);

        Intent intent = getIntent();
        chosenFeaturedCat = intent.getStringExtra("category");
        chosenState = intent.getStringExtra("state");

        back = findViewById(R.id.ibBackTouristSpots);
        userOption = findViewById(R.id.tvTouristSpots);

        travelahDbAccessHelper = TravelahDbAccessHelper.getInstance(this);
        travelahDbAccessHelper.openTravelahDb();

        if (chosenFeaturedCat == null) {
            tourismInfoList = travelahDbAccessHelper.getStateTourismInfo(chosenState);
            userOption.setText(chosenState);
        } else {
            tourismInfoList = travelahDbAccessHelper.getFeaturedTourismInfo(chosenFeaturedCat);
            userOption.setText(chosenFeaturedCat);
        }

        userOption.setAllCaps(true);

        getTourismInfoCountTotal = tourismInfoList.size();

        passAdapterList = new ArrayList<>();

        for (tourismInfoCounter = 0; tourismInfoCounter < getTourismInfoCountTotal; tourismInfoCounter++) {
            currentTourismInfo = tourismInfoList.get(tourismInfoCounter);

            passAdapterList.add(new TourismInfo(currentTourismInfo.getId(), currentTourismInfo.getTitle(), null, null, currentTourismInfo.getImage_1(), null, currentTourismInfo.getUrl()));
        }

        travelahDbAccessHelper.closeTravelahDb();

        touristSpotRecyclerView = findViewById(R.id.rvTouristSpots);
        TourismViewAdapter tourismViewAdapter = new TourismViewAdapter(this, passAdapterList, this);
        touristSpotRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        touristSpotRecyclerView.setAdapter(tourismViewAdapter);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    @Override
    public void onListClick(int position) {

        int chosenOption = passAdapterList.get(position).getId();

        Intent intent = new Intent (displayTouristSpots.this, displayTouristSpotsDetails.class);
        intent.putExtra("chosenSpots", chosenOption);
        startActivity(intent);

    }
}