package com.ash.digivahan.ui.Activities.splash;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ash.digivahan.data.local.PreferencesManager;
import com.ash.digivahan.databinding.ActivitySplashScreenBinding;
import com.ash.digivahan.ui.Activities.MainActivity;
import com.ash.digivahan.ui.Activities.auth.LoginActivity;
import com.ash.digivahan.ui.Activities.intro.IntroActivity;

public class SplashScreen extends AppCompatActivity {

    private ActivitySplashScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Animate logo
        binding.ivLogo.animate()
                .scaleX(1.2f)
                .scaleY(1.2f)
                .setDuration(1500)
                .withEndAction(() -> {
                    // Navigate after delay
                    navigateNext();
                }).start();
    }

    private void navigateNext() {
        PreferencesManager prefs = new PreferencesManager(this);

        Intent intent;
        if (prefs.getBoolean(PreferencesManager.KEY_FIRST_LAUNCH, true)) {
            intent = new Intent(this, IntroActivity.class);
        } else if (prefs.isLoggedIn()) {
            intent = new Intent(this, MainActivity.class);
        } else {
            intent = new Intent(this, LoginActivity.class);
        }

        startActivity(intent);
        finish();
    }

}