package com.example.loginform;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    private TextView welcomeText, emailText;
    private Button logoutButton;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        welcomeText = findViewById(R.id.welcomeText);
        emailText = findViewById(R.id.emailDisplay);
        logoutButton = findViewById(R.id.logoutButton);

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");

        welcomeText.setText("Witamy w aplikacji");
        emailText.setText("Zalogowano jako: " + email);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToMain = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(backToMain);
                finish();
            }
        });
    }
}
