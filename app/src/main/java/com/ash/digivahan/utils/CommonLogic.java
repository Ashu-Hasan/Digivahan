package com.ash.digivahan.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsController;

import androidx.core.view.WindowInsetsControllerCompat;

public interface CommonLogic {


    // Call this from your activity to hide status bar
    @SuppressLint("WrongConstant")
    public static void hideStatusBar(Activity activity) {
        Window window = activity.getWindow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // Android 11 and above
            window.setDecorFitsSystemWindows(false);
            WindowInsetsController controller = window.getInsetsController();
            if (controller != null) {
                controller.hide(WindowInsets.Type.statusBars());
                controller.setSystemBarsBehavior(
                        WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                );
            }
        } else {
            // Below Android 11
            WindowInsetsControllerCompat controller =
                    new WindowInsetsControllerCompat(window, window.getDecorView());
            controller.hide(android.view.WindowInsets.Type.statusBars());
        }
    }


}
