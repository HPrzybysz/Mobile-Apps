package com.example.a12022k25;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText name1, email1;
    private TextView txt1, txt2;
    private Button btn1;
    private int clicks = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        name1 = findViewById(R.id.name);
        email1 = findViewById(R.id.email);
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        btn1 = findViewById(R.id.btn1);

        if (savedInstanceState != null) {
            clicks = savedInstanceState.getInt("clicks");
            txt2.setText("Kliknąłeś przycisk " + clicks + " razy");
            txt1.setText(savedInstanceState.getString("welcomeMessage", ""));
        }

        btn1.setOnClickListener(v -> {
            String name = name1.getText().toString().trim();
            String email = email1.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty()) {
                Toast.makeText(MainActivity.this, "Uzupełnij wszystkie dane", Toast.LENGTH_SHORT).show();
            } else {
                clicks++;
                txt2.setText("Kliknałeś przycisk " + clicks + " razy");
                txt1.setText("Witaj " + name + "! Twój adres email to: " + email);
            }
        });





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

//    @Override
//    protected void onsavedInstanceState(@NonNull Bundle outState){
//        super.onSaveInstanceState(outState);
//        outState.putInt("clicks", clicks);
//        outState.putString("welcomeMessage", txt1.getText().toString());
//    }



}