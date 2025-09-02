package com.ash.digivahan.data.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ash.digivahan.data.model.NotificationItemModel;
import com.ash.digivahan.databinding.NotificationItemDesignBinding;
import com.ash.digivahan.ui.Activities.chat.ChatActivity;

import java.util.ArrayList;

public class NotificationItemAdapter extends RecyclerView.Adapter<NotificationItemAdapter.NIViewHolder> {

    Context context;
    ArrayList<NotificationItemModel> list;

    public NotificationItemAdapter(Context context, ArrayList<NotificationItemModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NotificationItemAdapter.NIViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        NotificationItemDesignBinding binding = NotificationItemDesignBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new NIViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationItemAdapter.NIViewHolder holder, int position) {
        holder.binding.btnChatNow.setOnClickListener(v -> {
            Intent chatPage = new Intent(context, ChatActivity.class);
            context.startActivity(chatPage);
        });
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public static class NIViewHolder extends RecyclerView.ViewHolder {
        NotificationItemDesignBinding binding;
        public NIViewHolder(NotificationItemDesignBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
