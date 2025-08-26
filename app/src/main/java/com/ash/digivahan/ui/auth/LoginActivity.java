package com.ash.digivahan.ui.auth;

import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.ash.digivahan.R;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText edtUser, edtPassword;
    private Button btnOldUser, btnNewUser, btnLogin;
    private CheckBox chkLoginOption;
    private TextView txtForgotPassword;
    private LoginViewModel loginViewModel;

    private boolean isOldUser = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUser = findViewById(R.id.edtUser);
        edtPassword = findViewById(R.id.edtPassword);
        btnOldUser = findViewById(R.id.btnOldUser);
        btnNewUser = findViewById(R.id.btnNewUser);
        btnLogin = findViewById(R.id.btnLogin);
        chkLoginOption = findViewById(R.id.chkLoginOption);
        txtForgotPassword = findViewById(R.id.txtForgotPassword);

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        btnOldUser.setOnClickListener(v -> setOldUser());
        btnNewUser.setOnClickListener(v -> setNewUser());

        btnLogin.setOnClickListener(v -> {
            String user = edtUser.getText().toString().trim();
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
            });
        });
    }

    private void setOldUser() {
        isOldUser = true;
//        btnOldUser.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.primary_icon));
//        btnNewUser.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.icon_gray));
        edtUser.setHint("Phone Number");
        edtUser.setInputType(InputType.TYPE_CLASS_PHONE);
    }

    private void setNewUser() {
        isOldUser = false;
//        btnNewUser.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.primary_icon));
//        btnOldUser.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.icon_gray));
        edtUser.setHint("Email Address");
        edtUser.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
    }
}
