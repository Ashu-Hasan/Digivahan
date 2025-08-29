package com.ash.digivahan.ui.intro;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ash.digivahan.R;
import com.ash.digivahan.data.local.PreferencesManager;
import com.ash.digivahan.data.model.IntroModel;
import com.ash.digivahan.databinding.ActivityIntroBinding;
import com.ash.digivahan.ui.auth.LoginActivity;
import com.ash.digivahan.utils.CommonLogic;

import java.util.Arrays;
import java.util.List;

public class IntroActivity extends AppCompatActivity {
    String TAG = "IntroActivityData";
    private ActivityIntroBinding binding;
    private List<IntroModel> introList;
    private int currentIndex = 0;

    PreferencesManager prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        CommonLogic.hideStatusBar(IntroActivity.this);

        prefs = new PreferencesManager(this);

        // Prepare intro pages
        introList = Arrays.asList(
                new IntroModel(R.drawable.intro_img_1, "Scan & Connect",
                        "Scan the QR code on any vehicle to instantly connect with its owner. Quick, safe, and hassle-free communication."),
                new IntroModel(R.drawable.intro_img_2, "Nearby Essentials",
                        "Find nearby services like mechanics, petrol pumps, and towing – all based on your current location, just one tap away."),
                new IntroModel(R.drawable.intro_img_3, "Vehicle Info & Challan",
                        "Check challans, insurance, and PUC details of any vehicle with ease. All info comes from trusted government sources."),
                new IntroModel(R.drawable.app_icon, "Welcome to Digivahan",
                        "Simplifying the way you connect with vehicles.\u2028From instant owner contact to complete vehicle info – everything is just a tap away.")
        );

        // First page load
        setIntroPage(currentIndex);

        binding.btnNext.setOnClickListener(v -> {
            if (currentIndex < introList.size() - 1) {
                currentIndex++;
                setIntroPage(currentIndex);
                if (currentIndex == introList.size() - 1){
                    binding.btnSkip.setVisibility(View.GONE);
                }
            } else {
                // Finished intro → go to Login/Main
                prefs.setBoolean(PreferencesManager.KEY_FIRST_LAUNCH, false);
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            }
        });

        binding.btnSkip.setOnClickListener(v -> {
            prefs.setBoolean(PreferencesManager.KEY_FIRST_LAUNCH, false);
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

    }

    private void setIntroPage(int index) {
        IntroModel model = introList.get(index);
        binding.setIntroData(model);

        // manually set image
        binding.introImage.setImageResource(model.getImageRes());

        Log.d(TAG, "ImageRes ID: " + model.getImageRes());


        updateIndicators(index);
    }

    private void updateIndicators(int index) {
        binding.indicatorLayout.removeAllViews();
        for (int i = 0; i < introList.size(); i++) {
            View view = new View(this);
            int size = (i == index) ? 16 : 8;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(size, 8);
            params.setMargins(4, 0, 4, 0);
            view.setLayoutParams(params);
            view.setBackgroundResource(i == index ? R.drawable.indicator_active : R.drawable.indicator_inactive);
            binding.indicatorLayout.addView(view);
        }
    }


}