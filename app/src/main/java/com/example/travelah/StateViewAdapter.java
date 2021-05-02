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

public class StateViewAdapter extends RecyclerView.Adapter<StateViewAdapter.StateViewHolder> {

    Context context;
    List<recyclerItems> stateList;

    //Constructor
    public StateViewAdapter(Context context, List<recyclerItems> stateList) {
        this.context = context;
        this.stateList = stateList;
    }


    @NonNull
    @Override
    public StateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.items_state, parent, false);
        StateViewHolder stateViewHolder = new StateViewHolder(view);


        return stateViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StateViewHolder holder, int position) {

        holder.stateItemName.setText(stateList.get(position).getItemName());
        holder.itemIcon.setImageResource(stateList.get(position).getIcon());

    }

    @Override
    public int getItemCount() {
        return stateList.size();
    }

    public static class StateViewHolder extends RecyclerView.ViewHolder {

        LinearLayout item_stateList;
        TextView stateItemName;
        ImageView itemIcon;
        private final Context context;
        public String chosenState;

        public StateViewHolder(@NonNull View itemView) {
            super(itemView);

            context = itemView.getContext();

            item_stateList = itemView.findViewById(R.id.itemsLayout);
            stateItemName = itemView.findViewById(R.id.tvItemName);
            itemIcon = itemView.findViewById(R.id.ivIcon);

            itemView.setClickable(true);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Intent intent;

                    switch (getAdapterPosition()) {
                        case 0:
                            chosenState = "johor";
                            break;

                        case 1:
                            chosenState = "kedah";
                            break;

                        case 2:
                            chosenState = "kelantan";
                            break;

                        case 3:
                            chosenState = "malacca";
                            break;

                        case 4:
                            chosenState = "negeri sembilan";
                            break;

                        case 5:
                            chosenState = "penang";
                            break;

                        case 6:
                            chosenState = "pahang";
                            break;

                        case 7:
                            chosenState = "perak";
                            break;

                        case 8:
                            chosenState = "perlis";
                            break;

                        case 9:
                            chosenState = "sabah";
                            break;

                        case 10:
                            chosenState = "sarawak";
                            break;

                        case 11:
                            chosenState = "selangor";
                            break;

                        case 12:
                            chosenState = "terengganu";
                            break;

                        case 13:
                            chosenState = "putrajaya";
                            break;

                        default:
                            break;
                    }

                    intent = new Intent (context, displayTouristSpots.class);
                    intent.putExtra("state", chosenState);
                    context.startActivity(intent);
                }
            });
        }
    }


}
