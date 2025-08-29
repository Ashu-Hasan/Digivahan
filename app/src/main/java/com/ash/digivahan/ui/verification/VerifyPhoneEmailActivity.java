package com.ash.digivahan.ui.verification;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.ash.digivahan.R;
import com.ash.digivahan.data.local.PreferencesManager;
import com.ash.digivahan.databinding.ActivityForgotPasswordBinding;
import com.ash.digivahan.databinding.ActivityVerifyPhoneEmailBinding;
import com.ash.digivahan.ui.MainActivity;
import com.ash.digivahan.ui.auth.LoginActivity;
import com.ash.digivahan.ui.auth.LoginViewModel;
import com.ash.digivahan.ui.forgotPassword.ForgotPasswordActivity;

public class VerifyPhoneEmailActivity extends AppCompatActivity {

    ActivityVerifyPhoneEmailBinding binding;

    boolean isPhoneSelected = true;

    String verificationType = "";

    PreferencesManager prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerifyPhoneEmailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        try {
            verificationType = getIntent().getStringExtra("verificationType");
        } catch (Exception e) {
//            throw new RuntimeException(e);
        }

        initializeData();

        setData();

        setEvents();
    }

    private void initializeData() {
        prefs = new PreferencesManager(this);
    }

    private void setData() {
        binding.title.setText("Verify Your Number");
        binding.description.setText("Please verify the number associated with your account. And don’t share your OTP with any one.");
        binding.loginTypeField.etInput.setHint("Enter your phone number");
        binding.loginTypeField.ivIcon.setImageResource(R.drawable.call_icon2);
        binding.btnPhone.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.widgetColor));
        binding.btnEmail.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.paragraph));
    }

    private void setEvents() {
        binding.btnPhone.setOnClickListener(v -> {
            binding.title.setText("Verify Your Number");
            binding.description.setText("Please verify the number associated with your account. And don’t share your OTP with any one.");
            binding.loginTypeField.etInput.setHint("Enter your phone number");
            binding.loginTypeField.ivIcon.setImageResource(R.drawable.call_icon2);
            binding.btnPhone.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.widgetColor));
            binding.btnEmail.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.paragraph));
            isPhoneSelected = true;
        });

        binding.btnEmail.setOnClickListener(v -> {
            binding.title.setText("Verify Your Email");
            binding.description.setText("Please verify the email associated with your account. And don’t share your OTP with any one.");
            binding.loginTypeField.etInput.setHint("Enter your email");
            binding.loginTypeField.ivIcon.setImageResource(R.drawable.email_icon1);
            binding.btnPhone.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.paragraph));
            binding.btnEmail.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.widgetColor));
            isPhoneSelected = false;
        });

        binding.btnSendCode.setOnClickListener(v -> {
            if (binding.btnSendCode.getText().toString().equalsIgnoreCase("Verify")) {
                if (verificationType.equalsIgnoreCase("createAccount")) {
                    showPasswordChangedDialog();
                }else {
                    Intent intent = new Intent(this, ForgotPasswordActivity.class);
                    startActivity(intent);
                    finish();
                }
            } else {
                binding.btnSendCode.setText("Verify");
                binding.otpView.setVisibility(View.VISIBLE);
                binding.etPhoneEmailLayout.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (binding.btnSendCode.getText().toString().equalsIgnoreCase("Verify")) {
            binding.btnSendCode.setText("Send Code");
            binding.otpView.setVisibility(View.GONE);
            binding.etPhoneEmailLayout.setVisibility(View.VISIBLE);
        } else {
            super.onBackPressed();
        }
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

        if (verificationType.equalsIgnoreCase("createAccount")) {
            btnLogin.setText("Login");
            tvTitle.setText("Account Created");
            tvMessage.setText("Welcome to Digivahan – your smart companion for all vehicle-related services.\u2028 Please log in to explore features like QR scan connect, nearby essentials, and vehicle challan info.");
        }else {
            btnLogin.setText("Login");
            tvTitle.setText("Password Changed");
            tvMessage.setText("Your password has been changed.\u2028 For your security, please use the new password next time you log in.");
        }

        btnLogin.setOnClickListener(v -> {
            dialog.dismiss();
            // 👉 Redirect to login activity here
            Intent intent = new Intent(this, LoginActivity.class);
            if (verificationType.equalsIgnoreCase("createAccount")) {
                prefs.setBoolean(PreferencesManager.KEY_IS_LOGGED_IN, true);
                intent = new Intent(this, MainActivity.class);
            }
            startActivity(intent);
            finish();
        });

        dialog.show();
    }

}