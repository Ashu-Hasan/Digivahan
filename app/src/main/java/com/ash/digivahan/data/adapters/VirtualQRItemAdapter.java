package com.ash.digivahan.data.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ash.digivahan.data.model.NotificationItemModel;
import com.ash.digivahan.data.model.VirtualQRItemModel;
import com.ash.digivahan.databinding.NotificationItemDesignBinding;
import com.ash.digivahan.ui.Activities.chat.ChatActivity;
import com.ash.digivahan.ui.Activities.qr.VirtualQR;
import com.ash.digivahan.ui.Fragments.virtualQR.VirtualQRListPage;

import java.util.ArrayList;

public class VirtualQRItemAdapter extends RecyclerView.Adapter<VirtualQRItemAdapter.VQRIViewHolder> {

    Context context;
    ArrayList<VirtualQRItemModel> list;

    public VirtualQRItemAdapter(Context context, ArrayList<VirtualQRItemModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public VirtualQRItemAdapter.VQRIViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NotificationItemDesignBinding binding = NotificationItemDesignBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new VirtualQRItemAdapter.VQRIViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VirtualQRItemAdapter.VQRIViewHolder holder, int position) {
        holder.binding.btnChatNow.setOnClickListener(v -> {
            Intent chatPage = new Intent(context, VirtualQR.class);
            context.startActivity(chatPage);
        });
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public static class VQRIViewHolder extends RecyclerView.ViewHolder {
        NotificationItemDesignBinding binding;
        public VQRIViewHolder(NotificationItemDesignBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
