package com.ash.digivahan.data.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ash.digivahan.data.model.ChatItemModel;
import com.ash.digivahan.databinding.ChatItemDesignBinding;
import com.ash.digivahan.ui.Activities.documentVault.DocumentVaultActivity;

import java.util.ArrayList;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.CLViewHolder> {

    Context context;
    ArrayList<ChatItemModel> list;

    public ChatListAdapter(Context context, ArrayList<ChatItemModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ChatListAdapter.CLViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ChatItemDesignBinding binding = ChatItemDesignBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CLViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatListAdapter.CLViewHolder holder, int position) {
        holder.binding.receiverLayout.setOnClickListener(v -> {
            Intent documentPage = new Intent(context, DocumentVaultActivity.class);
            context.startActivity(documentPage);
        });

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public static class CLViewHolder extends RecyclerView.ViewHolder {
        ChatItemDesignBinding binding;
        public CLViewHolder(ChatItemDesignBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
