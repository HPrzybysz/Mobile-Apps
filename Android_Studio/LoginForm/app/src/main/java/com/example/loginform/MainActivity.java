package com.example.loginform;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText email1, password1, password2;
    private TextView response;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        View mainView = findViewById(R.id.main);

        email1 = findViewById(R.id.emailInput);
        password1 = findViewById(R.id.passwordInput);
        password2 = findViewById(R.id.confirmPasswordInput);
        response = findViewById(R.id.messageDisplay);
        Button button = findViewById(R.id.submitButton);

        response.setText("Autor: HPrzbysz 000000000000");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = email1.getText().toString().trim();
                String password = password1.getText().toString();
                String confirmPassword = password2.getText().toString();

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    response.setText("Nieprawidłowy adres e-mail");
                    response.setTextColor(getColor(R.color.errorColor));
                } else if (!password.equals(confirmPassword)) {
                    response.setText("Hasła się różnią");
                    response.setTextColor(getColor(R.color.errorColor));
                } else if (!isPasswordValid(password)) {
                    response.setText("Hasło musi mieć co najmniej 8 znaków, jedną dużą literę,\njedną małą literę i jedną cyfrę");
                    response.setTextColor(getColor(R.color.errorColor));
                } else {
                    // Przejdź do WelcomeActivity
                    Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                    intent.putExtra("email", email);
                    startActivity(intent);
                    finish();
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*[a-z].*") &&
                password.matches(".*\\d.*");
    }

}