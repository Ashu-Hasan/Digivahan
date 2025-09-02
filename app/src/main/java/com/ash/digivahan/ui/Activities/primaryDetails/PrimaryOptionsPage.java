package com.ash.digivahan.ui.Activities.primaryDetails;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ash.digivahan.R;
import com.ash.digivahan.databinding.ActivityPrimaryOptionsPageBinding;
import com.ash.digivahan.ui.Activities.deliveryAddress.SetDefaultAddressPage;

public class PrimaryOptionsPage extends AppCompatActivity {

    ActivityPrimaryOptionsPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPrimaryOptionsPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.primaryDetailBtn.mainLayout.setOnClickListener(v -> {
            Intent basicProfile = new Intent(PrimaryOptionsPage.this, SetPrimaryContactPage.class);
            startActivity(basicProfile);
        });

        binding.defaultAddressBtn.mainLayout.setOnClickListener(v -> {
            Intent basicProfile = new Intent(PrimaryOptionsPage.this, SetDefaultAddressPage.class);
            startActivity(basicProfile);
        });

    }
}