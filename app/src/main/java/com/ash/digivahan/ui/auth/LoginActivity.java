package com.ash.digivahan.ui.auth;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.StyleSpan;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.ash.digivahan.R;
import com.ash.digivahan.data.local.PreferencesManager;
import com.ash.digivahan.databinding.ActivityLoginBinding;
import com.ash.digivahan.ui.MainActivity;
import com.ash.digivahan.ui.verification.VerifyPhoneEmailActivity;

public class LoginActivity extends AppCompatActivity {
    String TAG = "LoginActivityData";
    ActivityLoginBinding binding;

    private LoginViewModel loginViewModel;

    private boolean isOldUser = true;

    boolean isEmailLogin = true;

    PreferencesManager prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initializeData();

        setLoginData();

        setEvents();


    }

    private void setEvents() {
        binding.btnOldUser.setOnClickListener(v -> setOldUser());
        binding.btnNewUser.setOnClickListener(v -> setNewUser());

        binding.btnLogin.setOnClickListener(v -> {
            /*String user = binding.edtUser.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();
            if (user.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter details", Toast.LENGTH_SHORT).show();
                return;
            }
            loginViewModel.login(user, password, isOldUser).observe(this, response -> {
                if (response != null && response.has("success")) {
                    boolean success = response.get("success").getAsBoolean();
                    if (success) {
                        String token = response.has("token") ? response.get("token").getAsString() : "";
                        // do login success handling
                    } else {
                        String msg = response.has("message") ? response.get("message").getAsString() : "Login failed";
                        // show error
                    }
                }
            });*/

            Intent mainPage = new Intent(LoginActivity.this, MainActivity.class);

            if (isOldUser){
                prefs.setBoolean(PreferencesManager.KEY_IS_LOGGED_IN, true);
            }else {
                mainPage = new Intent(LoginActivity.this, VerifyPhoneEmailActivity.class);
                mainPage.putExtra("verificationType", "createAccount");
            }

            startActivity(mainPage);
            finish();
        });

        binding.forgotPassword.setOnClickListener(v -> {
            Intent mainPage = new Intent(LoginActivity.this, VerifyPhoneEmailActivity.class);
            mainPage.putExtra("verificationType", "forgotPassword");
            startActivity(mainPage);
            finish();
        });

        binding.changeLoginType.setOnClickListener(v -> {
            if (isEmailLogin){
                binding.changeLoginType.setText("Login with email");
                binding.changeLoginType.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.email_icon2, 0, 0, 0);
                binding.loginTypeText.setText("Phone Number");
                binding.loginTypeField.etInput.setHint("Enter your phone number");
                binding.loginTypeField.ivIcon.setImageResource(R.drawable.call_icon2);
            }else {
                binding.changeLoginType.setText("Login with number");
                binding.changeLoginType.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.call_icon1, 0, 0, 0);
                binding.loginTypeText.setText("Email Address");
                binding.loginTypeField.etInput.setHint("Enter your email");
                binding.loginTypeField.ivIcon.setImageResource(R.drawable.email_icon1);
            }
            isEmailLogin = !isEmailLogin;
        });
    }

    private void setLoginData() {
        String text = "I Agree to Terms and Condition and privacy policy.";
        SpannableString ss = new SpannableString(text);

// Bold
        ss.setSpan(new StyleSpan(Typeface.BOLD), 10, 15, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // Terms
        ss.setSpan(new StyleSpan(Typeface.BOLD), 20, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // Condition
        ss.setSpan(new StyleSpan(Typeface.BOLD), 35, 49, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // privacy policy

// Clickable
        ClickableSpan termsClick = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                // Open Terms activity / WebView
            }
        };
        ss.setSpan(termsClick, 10, 15, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        ClickableSpan condClick = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                // Open Condition page
            }
        };
        ss.setSpan(condClick, 20, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        ClickableSpan privacyClick = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                // Open Privacy Policy page
            }
        };
        ss.setSpan(privacyClick, 35, 49, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        binding.tvTerms.setText(ss);
        binding.tvTerms.setMovementMethod(LinkMovementMethod.getInstance());

        binding.loginPasswordField.etInput.setHint("Enter your Password");
        binding.loginPasswordField.ivIcon.setImageResource(R.drawable.password_icon1);

        binding.firstNameField.etInput.setHint("Enter your first Name");
        binding.firstNameField.ivIcon.setImageResource(R.drawable.profile_icon1);

        binding.lastNameField.etInput.setHint("Enter your last Name");
        binding.lastNameField.ivIcon.setImageResource(R.drawable.profile_icon1);

        binding.emailField.etInput.setHint("Enter your email");
        binding.emailField.ivIcon.setImageResource(R.drawable.email_icon1);

        binding.phoneField.etInput.setHint("Enter your phone Number");
        binding.phoneField.ivIcon.setImageResource(R.drawable.call_icon2);

        binding.passwordField.etInput.setHint("Enter your password");
        binding.passwordField.ivIcon.setImageResource(R.drawable.password_icon1);

        binding.passwordConfirmField.etInput.setHint("Confirm your password");
        binding.passwordConfirmField.ivIcon.setImageResource(R.drawable.password_icon1);

    }

    private void initializeData() {
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        prefs = new PreferencesManager(this);
    }

    private void setOldUser() {
        isOldUser = true;
        isEmailLogin = true;
//        btnOldUser.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.primary_icon));
//        btnNewUser.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.icon_gray));
        binding.loginLayout.setVisibility(View.VISIBLE);
        binding.termBtnLayout.setVisibility(View.GONE);
        binding.registerLayout.setVisibility(View.GONE);

        binding.btnLogin.setText("Login");
        binding.changeLoginType.setText("Login with number");
        binding.changeLoginType.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.call_icon1, 0, 0, 0);

        binding.loginTypeText.setText("Email Address");
        binding.loginTypeField.etInput.setHint("Enter your email");
        binding.loginTypeField.ivIcon.setImageResource(R.drawable.email_icon1);
    }

    private void setNewUser() {
        isOldUser = false;
//        btnNewUser.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.primary_icon));
//        btnOldUser.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.icon_gray));
        binding.loginLayout.setVisibility(View.GONE);
        binding.termBtnLayout.setVisibility(View.VISIBLE);
        binding.registerLayout.setVisibility(View.VISIBLE);

        binding.btnLogin.setText("Create Account");
    }
}
