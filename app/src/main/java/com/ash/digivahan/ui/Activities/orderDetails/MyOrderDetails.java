package com.ash.digivahan.ui.Activities.orderDetails;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ash.digivahan.R;
import com.ash.digivahan.databinding.ActivityMyOrderDetailsBinding;
import com.ash.digivahan.ui.Activities.invoice.DownloadInvoice;
import com.ash.digivahan.ui.Activities.qr.ByNewQR;
import com.ash.digivahan.ui.Activities.review.ShareReviewPage;

public class MyOrderDetails extends AppCompatActivity {

    ActivityMyOrderDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyOrderDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.trackOrder.setOnClickListener(v -> {
            Intent trackOrder = new Intent(MyOrderDetails.this, TrackOrderPage.class);
            startActivity(trackOrder);
        });

        binding.byForOther.setOnClickListener(v -> {
            Intent byNewQR = new Intent(MyOrderDetails.this, ByNewQR.class);
            startActivity(byNewQR);
        });

        binding.byForOther.setOnClickListener(v -> {
            Intent byNewQR = new Intent(MyOrderDetails.this, ByNewQR.class);
            startActivity(byNewQR);
        });

        binding.reviewBtn.setOnClickListener(v -> {
            Intent reviewPage = new Intent(MyOrderDetails.this, ShareReviewPage.class);
            startActivity(reviewPage);
        });

        binding.downloadInvoice.setOnClickListener(v -> {
            Intent downloadInvoicePage = new Intent(MyOrderDetails.this, DownloadInvoice.class);
            startActivity(downloadInvoicePage);
        });

    }
}