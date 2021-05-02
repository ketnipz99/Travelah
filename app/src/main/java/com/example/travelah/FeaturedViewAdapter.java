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

import java.util.List;

public class FeaturedViewAdapter extends RecyclerView.Adapter<FeaturedViewAdapter.FeaturedViewHolder> {

    Context context;
    List<recyclerItems> featuredList;

    //Constructor
    public FeaturedViewAdapter(Context context, List<recyclerItems> featuredList) {

        this.context = context;
        this.featuredList = featuredList;
    }


    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.items_featured, parent, false);
        FeaturedViewHolder featuredViewHolder = new FeaturedViewHolder(view);


        return featuredViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {

        holder.featuredItemName.setText(featuredList.get(position).getItemName());
        holder.itemIcon.setImageResource(featuredList.get(position).getIcon());

    }

    @Override
    public int getItemCount() {
        return featuredList.size();
    }

    public static class FeaturedViewHolder extends RecyclerView.ViewHolder {

        LinearLayout item_featuredList;
        TextView featuredItemName;
        ImageView itemIcon;
        private final Context context;
        public String featuredCategory;

        public FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);

            context = itemView.getContext();

            item_featuredList = itemView.findViewById(R.id.itemsLayout);
            featuredItemName = itemView.findViewById(R.id.tvItemName);
            itemIcon = itemView.findViewById(R.id.ivIcon);

            itemView.setClickable(true);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Intent intent;

                    switch (getAdapterPosition()) {
                        case 0:
                            featuredCategory = "adventure";
                            break;

                        case 1:
                            featuredCategory = "food";
                            break;

                        case 2:
                            featuredCategory = "nature";
                            break;

                        case 3:
                            featuredCategory = "family";
                            break;

                        case 4:
                            featuredCategory = "healthcare";
                            break;

                        default:
                            break;
                    }

                    intent = new Intent (context, displayTouristSpots.class);
                    intent.putExtra("category", featuredCategory);
                    context.startActivity(intent);
                }
            });
        }
    }
}
