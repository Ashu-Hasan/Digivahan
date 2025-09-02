package com.ash.digivahan.ui.Fragments.notification;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ash.digivahan.data.adapters.NotificationItemAdapter;
import com.ash.digivahan.data.model.NotificationItemModel;
import com.ash.digivahan.databinding.FragmentNotificationPageBinding;


import java.util.ArrayList;


public class NotificationPageFragment extends Fragment {

    String TAG = "NotificationPageFragmentData";

    FragmentNotificationPageBinding binding;

    ArrayList<NotificationItemModel> notificationItemList = new ArrayList<>();

    NotificationItemAdapter notificationItemAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNotificationPageBinding.inflate(getLayoutInflater(), container, false);

        notificationItemAdapter = new NotificationItemAdapter(getContext(), notificationItemList);
        binding.rvNotificationList.setAdapter(notificationItemAdapter);

        return binding.getRoot();
    }
}