package com.ash.digivahan.ui.Activities.emergencyContacts;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ash.digivahan.R;
import com.ash.digivahan.data.adapters.EmergancyContactListAdapter;
import com.ash.digivahan.data.model.EmergencyContactModel;
import com.ash.digivahan.data.model.OrderItemModel;
import com.ash.digivahan.databinding.ActivityEmergencyContactsBinding;

import java.util.ArrayList;

public class EmergencyContacts extends AppCompatActivity {

    ActivityEmergencyContactsBinding binding;

    ArrayList<EmergencyContactModel> contactList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEmergencyContactsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EmergancyContactListAdapter orderListItemAdapter = new EmergancyContactListAdapter  (EmergencyContacts.this, contactList);
        binding.rvEmergencyContact.setAdapter(orderListItemAdapter);

    }
}