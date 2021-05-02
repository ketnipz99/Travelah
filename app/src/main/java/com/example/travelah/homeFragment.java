package com.example.travelah;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.io.IOException;
import java.net.URL;

public class homeFragment extends Fragment {

    ImageView test;
    YouTubePlayerView youTubePlayerView_1, youTubePlayerView_2, youTubePlayerView_3;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        youTubePlayerView_1 = view.findViewById(R.id.ytVideo_1);
        youTubePlayerView_2 = view.findViewById(R.id.ytVideo_2);
        youTubePlayerView_3 = view.findViewById(R.id.ytVideo_3);

        getLifecycle().addObserver(youTubePlayerView_1);
        getLifecycle().addObserver(youTubePlayerView_2);
        getLifecycle().addObserver(youTubePlayerView_3);




        return view;
    }
}
