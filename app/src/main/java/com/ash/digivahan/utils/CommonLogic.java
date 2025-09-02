package com.ash.digivahan.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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

    // to check interNet connected or not
    static boolean Connected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return manager.getActiveNetworkInfo() != null && manager.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    @SuppressLint("ObsoleteSdkInt")
    static String convertHtmlToString(String htmlText) {
        // Check Android version
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // Use the newer fromHtml method for Android Nougat and above
            Spanned spannedText = Html.fromHtml(htmlText, Html.FROM_HTML_MODE_LEGACY);
            return spannedText.toString();
        } else {
            // Use the deprecated fromHtml method for older versions
            Spanned spannedText = Html.fromHtml(htmlText);
            return spannedText.toString();
        }
    }


    public static void checkAppVersion(Activity activity) {

        /*ApiController.getInstance().getapi().getVersion("get_version").enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(@NonNull Call<JsonObject> call, @NonNull Response<JsonObject> response) {
                Gson gson = new Gson();
                JSONObject jsonObject = ApiSet.getResponseData(response);
                Log.d("VersionAPPData", String.valueOf(jsonObject));

                try {
                    if (jsonObject != null) {
                        // Fetch the version number and app link from the response
                        JSONObject msg = jsonObject.getJSONObject("JOBS_APP");
                        String versionNumber = msg.optString("app_update_version_code");
                        String appLink = msg.optString("app_update_link");
                        String app_update_desc = msg.optString("app_update_desc");

                        // Get the current version of the app
                        String currentVersion = getAppVersion(activity);

                        // Compare versions
                        if (!versionNumber.equalsIgnoreCase("0") && !versionNumber.equals(currentVersion)) {
                            // If the versions don't match, redirect to the Play Store
                            Log.e("TAG", "showSecurityDialog: dialog show");
                            final Dialog dialog = new Dialog(activity);
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setContentView(R.layout.block_dialog);
                            Window window = dialog.getWindow();

                            Objects.requireNonNull(window).setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            dialog.setCancelable(false);

                            Button button = dialog.findViewById(R.id.exit_btn);
                            button.setVisibility(View.VISIBLE);
                            button.setText("Update");
                            TextView titleView = dialog.findViewById(R.id.title_text);
                            TextView descView = dialog.findViewById(R.id.desc_text);
                            titleView.setText("Need Update");
                            descView.setText(app_update_desc);
                            button.setOnClickListener(v -> {
                                redirectToPlayStore(activity, appLink);
                            });
                            dialog.show();
                        }
                    }

                } catch (JSONException e) {
                    Log.d("APPVersionError", Objects.requireNonNull(e.getMessage()));
//                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<JsonObject> call, @NonNull Throwable t) {

            }
        });*/
    }

    // Method to get the current version of the app
    public static String getAppVersion(Activity activity) {
        try {
            PackageInfo packageInfo = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "Unknown";
        }
    }

    // Method to redirect the user to the Play Store or a custom app link
    public static void redirectToPlayStore(Activity activity, String appLink) {
        try {
            // Redirect to the Play Store if the app is hosted there
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(appLink));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // If Play Store is unavailable, redirect to a custom link (like a website)
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(appLink));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(intent);
        }
    }


    public static Fragment switchFragment(FragmentManager fm, Fragment targetFragment, Fragment activeFragment, int containerId) {
        final String TAG = "FragmentSwitcher";

        if (fm == null || targetFragment == null) {
            Log.e(TAG, "Invalid params: fm=" + fm + " target=" + targetFragment + " active=" + activeFragment);
            return activeFragment; // Return current if params are invalid
        }

        try {
            FragmentTransaction transaction = fm.beginTransaction();

            // Case 1: No active fragment yet (first load)
            if (activeFragment == null) {
                Log.d(TAG, "No active fragment. Adding: " + targetFragment.getClass().getSimpleName());
                transaction.add(containerId, targetFragment).commitAllowingStateLoss();
                return targetFragment;
            }

            // Case 2: Switching between fragments
            if (activeFragment == targetFragment) {
                Log.d(TAG, "Target fragment is already active: " + targetFragment.getClass().getSimpleName());
                return targetFragment; // Already showing
            }

            if (!targetFragment.isAdded()) {
                Log.d(TAG, "Adding fragment: " + targetFragment.getClass().getSimpleName() +
                        " | Hiding: " + activeFragment.getClass().getSimpleName());
                transaction.add(containerId, targetFragment).hide(activeFragment).commitAllowingStateLoss();
            } else {
                Log.d(TAG, "Showing fragment: " + targetFragment.getClass().getSimpleName() +
                        " | Hiding: " + activeFragment.getClass().getSimpleName());
                transaction.hide(activeFragment).show(targetFragment).commitAllowingStateLoss();
            }

            Log.i(TAG, "Switched to fragment: " + targetFragment.getClass().getSimpleName());
            return targetFragment; // New active fragment
        } catch (IllegalStateException e) {
            Log.e(TAG, "IllegalStateException while switching fragments", e);
            return activeFragment; // Fallback to current fragment
        } catch (Exception e) {
            Log.e(TAG, "Exception while switching fragments", e);
            return activeFragment; // Fallback to current fragment
        }
    }





}
