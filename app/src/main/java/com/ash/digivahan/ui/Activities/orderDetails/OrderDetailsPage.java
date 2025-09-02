package com.ash.digivahan.ui.Activities.orderDetails;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ash.digivahan.R;
import com.ash.digivahan.databinding.ActivityOrderDetailsPageBinding;
import com.ash.digivahan.ui.Activities.auth.LoginActivity;
import com.ash.digivahan.ui.Activities.deliveryAddress.AddEditDeliveryAddress;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class OrderDetailsPage extends AppCompatActivity {

    ActivityOrderDetailsPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderDetailsPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.deliveryAddressLayout.setOnClickListener(v -> {
            showAddressBottomSheet(this);
        });

        binding.continueBtn.setOnClickListener(v -> {
            showPasswordChangedDialog();
        });


    }

    public void showAddressBottomSheet(Context context) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.BottomSheetDialogTheme);
        View sheetView = LayoutInflater.from(context).inflate(R.layout.bottom_sheet_address, null);
        bottomSheetDialog.setContentView(sheetView);

        // Close button
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView ivClose = sheetView.findViewById(R.id.ivClose);
        ivClose.setVisibility(View.VISIBLE);
        ivClose.setOnClickListener(v -> bottomSheetDialog.dismiss());

        // Example: dynamically add address items
        LinearLayout container = sheetView.findViewById(R.id.addressContainer);
        for (int i = 0; i < 3; i++) {
            View item = LayoutInflater.from(context).inflate(R.layout.item_address, container, false);

            TextView tvName = item.findViewById(R.id.tvName);
            TextView tvAddress = item.findViewById(R.id.tvAddress);
            TextView tvPhone = item.findViewById(R.id.tvPhone);
            Button btnDeliver = item.findViewById(R.id.btnDeliver);

            // Sample data
            tvName.setText("Julfikar Tiwari " + (i + 1));
            tvAddress.setText("P - A 3rd floor Cyber Tech, Opposit Kaliyan Jewellers, Dehradun, Uttarakhand, 247667");
            tvPhone.setText("+91 9897000001");

            btnDeliver.setOnClickListener(v -> {
                Intent editDeliveryAddress = new Intent(OrderDetailsPage.this, AddEditDeliveryAddress.class);
                startActivity(editDeliveryAddress);
                bottomSheetDialog.dismiss();
            });

            container.addView(item);
        }

        bottomSheetDialog.show();
    }

    private void showPasswordChangedDialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_password_changed);
        dialog.setCancelable(false);

        // Transparent background
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        TextView btnLogin = dialog.findViewById(R.id.btnLogin);
        TextView tvTitle = dialog.findViewById(R.id.tvTitle);
        TextView tvMessage = dialog.findViewById(R.id.tvMessage);

        btnLogin.setText("Track");
        tvTitle.setText("Order Successful");
        tvMessage.setText("Your QR order is successfully placed. And profile has been created with verified number or email. Login for complete tracking.");

        btnLogin.setOnClickListener(v -> {
            dialog.dismiss();
            // 👉 Redirect to login activity here
            Intent intent = new Intent(this, OrderListPage.class);
            startActivity(intent);
            finish();
        });

        dialog.show();
    }

}