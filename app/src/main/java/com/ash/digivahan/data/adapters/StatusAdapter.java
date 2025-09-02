package com.ash.digivahan.data.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ash.digivahan.R;

import java.util.ArrayList;

public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.StatusViewHolder> {

    private ArrayList<String> statusList;
    private int selectedItem;

    public StatusAdapter(ArrayList<String> statusList, int selectedItem) {
        this.statusList = statusList;
        this.selectedItem = selectedItem;
    }

    @NonNull
    @Override
    public StatusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.status_item, parent, false);
        return new StatusViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StatusViewHolder holder, int position) {
        String status = statusList.get(position);
        holder.statusButton.setText(status);

        // Apply style for selected or unselected items
        if (position == selectedItem) {
            holder.statusButton.setBackgroundResource(R.drawable.rounded_button); // Active button style
            holder.statusButton.setTextColor(Color.WHITE);
        } else {
            holder.statusButton.setBackgroundResource(R.drawable.rounded_button_inactive); // Inactive button style
            holder.statusButton.setTextColor(Color.GRAY);
        }
    }

    @Override
    public int getItemCount() {
        return statusList.size();
    }

    public static class StatusViewHolder extends RecyclerView.ViewHolder {
        TextView statusButton;

        public StatusViewHolder(View itemView) {
            super(itemView);
            statusButton = itemView.findViewById(R.id.statusButton);
        }
    }
}

