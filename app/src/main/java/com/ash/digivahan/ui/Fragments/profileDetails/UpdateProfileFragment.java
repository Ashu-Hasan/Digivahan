package com.ash.digivahan.ui.Fragments.profileDetails;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ash.digivahan.R;
import com.ash.digivahan.databinding.FragmentUpdateProfileBinding;
import com.ash.digivahan.ui.Activities.changPassword.ChangePasswordPage;
import com.ash.digivahan.ui.Activities.deliveryAddress.AddEditDeliveryAddress;
import com.ash.digivahan.ui.Activities.emergencyContacts.EmergencyContacts;
import com.ash.digivahan.ui.Activities.primaryDetails.PrimaryOptionsPage;
import com.ash.digivahan.ui.Activities.profile.UpdateBasicDetails;
import com.ash.digivahan.ui.Activities.profile.UpdateDeliveryAddress;
import com.ash.digivahan.ui.Activities.profile.UpdatePublicDetails;

public class UpdateProfileFragment extends Fragment {

    FragmentUpdateProfileBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUpdateProfileBinding.inflate(getLayoutInflater(), container, false);

        binding.basicDetailBtn.mainLayout.setOnClickListener(v -> {
            Intent basicProfile = new Intent(getContext(), UpdateBasicDetails.class);
            startActivity(basicProfile);
        });

        binding.publicDetailsBtn.mainLayout.setOnClickListener(v -> {
            Intent basicProfile = new Intent(getContext(), UpdatePublicDetails.class);
            startActivity(basicProfile);
        });

        binding.tvAddress.mainLayout.setOnClickListener(v -> {
            Intent basicProfile = new Intent(getContext(), UpdateDeliveryAddress.class);
            startActivity(basicProfile);
        });

        binding.emergencyContact.mainLayout.setOnClickListener(v -> {
            Intent basicProfile = new Intent(getContext(), EmergencyContacts.class);
            startActivity(basicProfile);
        });

        binding.changePasswordBtn.mainLayout.setOnClickListener(v -> {
            Intent basicProfile = new Intent(getContext(), ChangePasswordPage.class);
            startActivity(basicProfile);
        });

        return binding.getRoot();
    }
}