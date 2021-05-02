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

public class stateFragment extends Fragment {

    View view;
    private RecyclerView stateRecyclerView;
    private List<recyclerItems> listState;

    public stateFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_state, container, false);

        stateRecyclerView =  (RecyclerView) view.findViewById(R.id.rvState);
        StateViewAdapter myRecyclerViewAdapter = new StateViewAdapter(getContext(), listState);
        stateRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        stateRecyclerView.setAdapter(myRecyclerViewAdapter);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listState = new ArrayList<>();

        listState.add(new recyclerItems("Johor", R.drawable.johor));
        listState.add(new recyclerItems("Kedah", R.drawable.kedah));
        listState.add(new recyclerItems("Kelantan", R.drawable.kelantan));
        listState.add(new recyclerItems("Malacca", R.drawable.malacca));
        listState.add(new recyclerItems("Negeri Sembilan", R.drawable.negeri_sembilan));
        listState.add(new recyclerItems("Penang", R.drawable.penang));
        listState.add(new recyclerItems("Pahang", R.drawable.pahang));
        listState.add(new recyclerItems("Perak", R.drawable.perak));
        listState.add(new recyclerItems("Perlis", R.drawable.perlis));
        listState.add(new recyclerItems("Sabah", R.drawable.sabah));
        listState.add(new recyclerItems("Sarawak", R.drawable.sarawak));
        listState.add(new recyclerItems("Selangor", R.drawable.selangor));
        listState.add(new recyclerItems("Terengganu", R.drawable.terengganu));
        listState.add(new recyclerItems("Putrajaya", R.drawable.putrajaya));
    }
}
