package com.example.travelah;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class TourismViewAdapter extends RecyclerView.Adapter<TourismViewAdapter.TourismViewHolder> {

    Context context;
    List<TourismInfo> displayTourismInfoList;
    private TourismViewHolder.OnListListener myOnListListener;

    //Constructor
    public TourismViewAdapter(Context context, List<TourismInfo> displayTourismInfoList, TourismViewHolder.OnListListener onListListener) {

        this.context = context;
        this.displayTourismInfoList = displayTourismInfoList;
        this.myOnListListener = onListListener;
    }

    @NonNull
    @Override
    public TourismViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_tourist_spots, parent, false);
        TourismViewHolder tourismViewHolder = new TourismViewHolder(view, myOnListListener);


        return tourismViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TourismViewHolder holder, int position) {

        holder.touristSpotsTitle.setText(displayTourismInfoList.get(position).getTitle());
        holder.itemImage.setImageBitmap(displayTourismInfoList.get(position).getImage_1());

    }

    @Override
    public int getItemCount() {
        return displayTourismInfoList.size();
    }


    public static class TourismViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        LinearLayout item_touristSpotsList;
        TextView touristSpotsTitle;
        ImageView itemImage;
        OnListListener onListListener;


        public TourismViewHolder(@NonNull View itemView, OnListListener onListListener) {
            super(itemView);

            item_touristSpotsList = itemView.findViewById(R.id.touristSpotsLayout);
            this.touristSpotsTitle = itemView.findViewById(R.id.tvTitleTS);
            this.itemImage = itemView.findViewById(R.id.ivImageTS);
            this.onListListener = onListListener;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onListListener.onListClick(getAdapterPosition());
        }

        public interface OnListListener {
            void onListClick (int position);
        }
    }
}
