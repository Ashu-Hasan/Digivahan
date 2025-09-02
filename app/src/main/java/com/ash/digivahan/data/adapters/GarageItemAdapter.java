package com.ash.digivahan.data.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ash.digivahan.data.model.ChatItemModel;
import com.ash.digivahan.data.model.GarageItemModel;
import com.ash.digivahan.databinding.ChatItemDesignBinding;
import com.ash.digivahan.databinding.ItemGarageCardDesignBinding;
import com.ash.digivahan.ui.Activities.documentVault.DocumentVaultActivity;
import com.ash.digivahan.ui.Activities.garage.VehicleInformation;

import java.util.ArrayList;

public class GarageItemAdapter extends RecyclerView.Adapter<GarageItemAdapter.CLViewHolder> {

    Context context;
    ArrayList<GarageItemModel> list;

    public GarageItemAdapter(Context context, ArrayList<GarageItemModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public GarageItemAdapter.CLViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemGarageCardDesignBinding binding = ItemGarageCardDesignBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CLViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GarageItemAdapter.CLViewHolder holder, int position) {
        holder.binding.receiverLayout.setOnClickListener(v -> {
            Intent documentPage = new Intent(context, VehicleInformation.class);
            context.startActivity(documentPage);
        });

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public static class CLViewHolder extends RecyclerView.ViewHolder {
        ItemGarageCardDesignBinding binding;
        public CLViewHolder(ItemGarageCardDesignBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
