package com.ash.digivahan.ui.Activities.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ash.digivahan.R;
import com.ash.digivahan.databinding.ActivityUpdateDeliveryAddressBinding;
import com.ash.digivahan.ui.Activities.deliveryAddress.AddEditDeliveryAddress;
import com.ash.digivahan.ui.Activities.orderDetails.OrderDetailsPage;

public class UpdateDeliveryAddress extends AppCompatActivity {

    ActivityUpdateDeliveryAddressBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateDeliveryAddressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // Dynamically add address items
        addSampleAddresses();
    }

    private void addSampleAddresses() {
        for (int i = 0; i < 3; i++) {
            View item = LayoutInflater.from(this).inflate(R.layout.item_address, binding.deliveryAddressLayout.addressContainer, false);

            TextView tvName = item.findViewById(R.id.tvName);
            TextView tvAddress = item.findViewById(R.id.tvAddress);
            TextView tvPhone = item.findViewById(R.id.tvPhone);
            Button btnDeliver = item.findViewById(R.id.btnDeliver);

            // Sample data
            tvName.setText("Julfikar Tiwari " + (i + 1));
            tvAddress.setText("P - A 3rd floor Cyber Tech, Opposite Kaliyan Jewellers, Dehradun, Uttarakhand, 247667");
            tvPhone.setText("+91 989700000" + (i + 1));

            // Button click → open Add/Edit screen
            btnDeliver.setOnClickListener(v -> {
                Intent editDeliveryAddress = new Intent(UpdateDeliveryAddress.this, AddEditDeliveryAddress.class);
                startActivity(editDeliveryAddress);
            });

            // Add item inside container
            binding.deliveryAddressLayout.addressContainer.addView(item);
        }
    }
}