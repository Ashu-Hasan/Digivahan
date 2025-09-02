package com.ash.digivahan.ui.Activities.garage;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ash.digivahan.R;
import com.ash.digivahan.data.adapters.EmergancyContactListAdapter;
import com.ash.digivahan.data.adapters.GarageItemAdapter;
import com.ash.digivahan.data.model.EmergencyContactModel;
import com.ash.digivahan.data.model.GarageItemModel;
import com.ash.digivahan.databinding.ActivityEmergencyContactsBinding;
import com.ash.digivahan.databinding.ActivityMyGarageBinding;
import com.ash.digivahan.ui.Activities.emergencyContacts.EmergencyContacts;

import java.util.ArrayList;

public class MyGarageActivity extends AppCompatActivity {

    ActivityMyGarageBinding binding;

    ArrayList<GarageItemModel> contactList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyGarageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        GarageItemAdapter orderListItemAdapter = new GarageItemAdapter  (MyGarageActivity.this, contactList);
        binding.rvGarageList.setAdapter(orderListItemAdapter);

    }
}