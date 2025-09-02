package com.ash.digivahan.ui.Fragments.my_order;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ash.digivahan.data.adapters.MyOrderItemAdapter;
import com.ash.digivahan.data.model.MyOrderItemModel;
import com.ash.digivahan.databinding.FragmentMyOrderBinding;

import java.util.ArrayList;

public class MyOrderFragment extends Fragment {

    String TAG = "MyOrderFragmentData";

    FragmentMyOrderBinding binding;

    ArrayList<MyOrderItemModel> notificationItemList = new ArrayList<>();

    MyOrderItemAdapter orderItemAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        binding = FragmentMyOrderBinding.inflate(getLayoutInflater(), container, false);

        orderItemAdapter = new MyOrderItemAdapter(getContext(), notificationItemList);
        binding.rvNotificationList.setAdapter(orderItemAdapter);

        return binding.getRoot();
    }
}