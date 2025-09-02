package com.ash.digivahan.data.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ash.digivahan.data.model.ChatItemModel;
import com.ash.digivahan.data.model.EmergencyContactModel;
import com.ash.digivahan.databinding.ChatItemDesignBinding;
import com.ash.digivahan.databinding.EmergencyContactDesignBinding;
import com.ash.digivahan.ui.Activities.documentVault.DocumentVaultActivity;
import com.ash.digivahan.ui.Activities.emergencyContacts.EditEmergencyContacts;

import java.util.ArrayList;

public class EmergancyContactListAdapter extends RecyclerView.Adapter<EmergancyContactListAdapter.CLViewHolder> {

    Context context;
    ArrayList<EmergencyContactModel> list;

    public EmergancyContactListAdapter(Context context, ArrayList<EmergencyContactModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public EmergancyContactListAdapter.CLViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        EmergencyContactDesignBinding binding = EmergencyContactDesignBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CLViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EmergancyContactListAdapter.CLViewHolder holder, int position) {
        holder.binding.receiverLayout.setOnClickListener(v -> {
            Intent documentPage = new Intent(context, EditEmergencyContacts.class);
            context.startActivity(documentPage);
        });

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public static class CLViewHolder extends RecyclerView.ViewHolder {
        EmergencyContactDesignBinding binding;
        public CLViewHolder(EmergencyContactDesignBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
