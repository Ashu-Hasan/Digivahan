package com.ash.digivahan.ui.Activities.deliveryAddress;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ash.digivahan.R;
import com.ash.digivahan.databinding.ActivityAddEditDeliveryAddressBinding;

public class AddEditDeliveryAddress extends AppCompatActivity {
    String TAG = "AddEditDeliveryAddressData";

    ActivityAddEditDeliveryAddressBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddEditDeliveryAddressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}