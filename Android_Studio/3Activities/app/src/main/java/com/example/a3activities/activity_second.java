package com.example.a3activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class activity_second extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(view -> {
            startActivity(new Intent(activity_second.this, MainActivity.class));
            finish();
        });
    }
}
