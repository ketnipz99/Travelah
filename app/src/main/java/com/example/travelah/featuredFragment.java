package com.example.travelah;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class featuredFragment extends Fragment {

    View view;
    private RecyclerView featuredRecyclerView;
    private List<recyclerItems> listFeaturedCategory;

    public featuredFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_featured, container, false);

        featuredRecyclerView =  (RecyclerView) view.findViewById(R.id.rvFeatured);
        FeaturedViewAdapter myRecyclerViewAdapter = new FeaturedViewAdapter(getContext(), listFeaturedCategory);
        featuredRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        featuredRecyclerView.setAdapter(myRecyclerViewAdapter);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listFeaturedCategory = new ArrayList<>();
        listFeaturedCategory.add(new recyclerItems("Adventure", R.drawable.adventure));
        listFeaturedCategory.add(new recyclerItems("Food", R.drawable.food));
        listFeaturedCategory.add(new recyclerItems("Nature", R.drawable.outdoor));
        listFeaturedCategory.add(new recyclerItems("Family", R.drawable.family));
        listFeaturedCategory.add(new recyclerItems("Healthcare", R.drawable.healthcare));
    }
}
