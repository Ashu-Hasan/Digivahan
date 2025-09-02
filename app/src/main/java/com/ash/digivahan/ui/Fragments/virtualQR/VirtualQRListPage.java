package com.ash.digivahan.ui.Fragments.virtualQR;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ash.digivahan.R;
import com.ash.digivahan.data.adapters.NotificationItemAdapter;
import com.ash.digivahan.data.adapters.VirtualQRItemAdapter;
import com.ash.digivahan.data.model.NotificationItemModel;
import com.ash.digivahan.data.model.VirtualQRItemModel;
import com.ash.digivahan.databinding.FragmentNotificationPageBinding;
import com.ash.digivahan.databinding.FragmentVirtualQRListPageBinding;

import java.util.ArrayList;


public class VirtualQRListPage extends Fragment {

    String TAG = "VirtualQRListPageData";

    FragmentVirtualQRListPageBinding binding;

    ArrayList<VirtualQRItemModel> notificationItemList = new ArrayList<>();

    VirtualQRItemAdapter notificationItemAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentVirtualQRListPageBinding.inflate(getLayoutInflater(), container, false);

        notificationItemAdapter = new VirtualQRItemAdapter(getContext(), notificationItemList);
        binding.rvVirtualQRList.setAdapter(notificationItemAdapter);

        return binding.getRoot();
    }
}