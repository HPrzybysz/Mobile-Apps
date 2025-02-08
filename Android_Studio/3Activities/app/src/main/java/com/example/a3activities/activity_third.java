package com.example.a3activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class activity_third extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(view -> {
            startActivity(new Intent(activity_third.this, MainActivity.class));
            finish();
        });
    }
}
