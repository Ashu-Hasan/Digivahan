package com.ash.digivahan.ui.Activities.forgotPassword;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ash.digivahan.R;
import com.ash.digivahan.databinding.ActivityForgotPasswordBinding;
import com.ash.digivahan.ui.Activities.auth.LoginActivity;

public class ForgotPasswordActivity extends AppCompatActivity {

    ActivityForgotPasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setData();

        setEvents();

    }

    private void setData() {
        binding.passwordField.etInput.setHint("Enter your password");
        binding.passwordField.ivIcon.setImageResource(R.drawable.password_icon1);

        binding.passwordConfirmField.etInput.setHint("Confirm your password");
        binding.passwordConfirmField.ivIcon.setImageResource(R.drawable.password_icon1);
    }

    private void setEvents() {
        binding.btnSave.setOnClickListener(v -> {
            showPasswordChangedDialog();
        });

        binding.btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

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

        btnLogin.setText("Login");
        tvTitle.setText("Password Changed");
        tvMessage.setText("Your password has been changed.\u2028 For your security, please use the new password next time you log in.");

        btnLogin.setOnClickListener(v -> {
            dialog.dismiss();
            // 👉 Redirect to login activity here
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        dialog.show();
    }

}