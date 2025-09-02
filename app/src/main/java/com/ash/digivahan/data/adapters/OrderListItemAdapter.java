package com.ash.digivahan.data.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ash.digivahan.data.model.OrderItemModel;
import com.ash.digivahan.databinding.OrderItemDesignBinding;
import com.ash.digivahan.ui.Activities.orderDetails.MyOrderDetails;

import java.util.ArrayList;

public class OrderListItemAdapter extends RecyclerView.Adapter<OrderListItemAdapter.OLIViewHolder> {

    Context context;
    ArrayList<OrderItemModel> list;

    public OrderListItemAdapter(Context context, ArrayList<OrderItemModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public OrderListItemAdapter.OLIViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        OrderItemDesignBinding binding = OrderItemDesignBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new OLIViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderListItemAdapter.OLIViewHolder holder, int position) {
        holder.binding.getRoot().setOnClickListener(v -> {
            Intent myQRDetailsPage = new Intent(context, MyOrderDetails.class);
            context.startActivity(myQRDetailsPage);
        });
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public static class OLIViewHolder extends RecyclerView.ViewHolder {
        OrderItemDesignBinding binding;

        public OLIViewHolder(OrderItemDesignBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }
    }
}
