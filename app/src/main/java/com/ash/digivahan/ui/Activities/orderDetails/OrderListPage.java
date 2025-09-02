package com.ash.digivahan.ui.Activities.orderDetails;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ash.digivahan.data.adapters.OrderListItemAdapter;
import com.ash.digivahan.data.adapters.StatusAdapter;
import com.ash.digivahan.data.model.OrderItemModel;
import com.ash.digivahan.databinding.ActivityOrderListPageBinding;

import java.util.ArrayList;

public class OrderListPage extends AppCompatActivity {
    String TAG = "OrderListPageData";

    ActivityOrderListPageBinding binding;
    private ArrayList<String> statusList = new ArrayList<>();
    ArrayList<OrderItemModel> orderList = new ArrayList<>();
    private int selectedItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderListPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // Initialize status list
        statusList.add("Pending");
        statusList.add("Progress");
        statusList.add("Shipped");
        statusList.add("Completed");


        // Set LayoutManager
        binding.statusRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // Set Adapter
        StatusAdapter adapter = new StatusAdapter(statusList, selectedItem);
        binding.statusRecyclerView.setAdapter(adapter);

        // Add item click listener to change selected item
        binding.statusRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, binding.statusRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // Update selected item
                selectedItem = position;
                adapter.notifyDataSetChanged(); // Refresh adapter
            }

            @Override
            public void onLongItemClick(View view, int position) {
                // No need for long item click here
            }
        }));


        OrderListItemAdapter orderListItemAdapter = new OrderListItemAdapter(OrderListPage.this, orderList);
        binding.orderRecyclerView.setAdapter(orderListItemAdapter);

    }
}