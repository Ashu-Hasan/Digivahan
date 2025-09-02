package com.ash.digivahan.data.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ash.digivahan.data.model.MyOrderItemModel;
import com.ash.digivahan.data.model.NotificationItemModel;
import com.ash.digivahan.databinding.NotificationItemDesignBinding;
import com.ash.digivahan.ui.Activities.chat.ChatActivity;
import com.ash.digivahan.ui.Activities.orderDetails.OrderDetailsPage;

import java.util.ArrayList;

public class MyOrderItemAdapter extends RecyclerView.Adapter<MyOrderItemAdapter.NIViewHolder> {

    Context context;
    ArrayList<MyOrderItemModel> list;

    public MyOrderItemAdapter(Context context, ArrayList<MyOrderItemModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyOrderItemAdapter.NIViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        NotificationItemDesignBinding binding = NotificationItemDesignBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new NIViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrderItemAdapter.NIViewHolder holder, int position) {

        holder.binding.btnChatNow.setOnClickListener(v -> {
            Intent chatPage = new Intent(context, OrderDetailsPage.class);
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
