package com.example.paczki;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText textCity;
    private EditText textPostCode;
    private EditText textStreet;
    private TextView priceText;
    private ImageView image;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button buttonCheckPrice;
    private Button buttonAccept;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        textCity = findViewById(R.id.textCity);
        textStreet = findViewById(R.id.textStreet);
        textPostCode = findViewById(R.id.textPostCode);
        priceText = findViewById(R.id.priceText);
        image = findViewById(R.id.image);
        radioGroup = findViewById(R.id.radioGroup);
        buttonAccept = findViewById(R.id.buttonAccept);
        buttonCheckPrice = findViewById(R.id.buttonCheckPrice);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        buttonCheckPrice.setOnClickListener(view -> {
            checkPrice();
        });


    }

    private void checkPrice(){
        int selectedId = radioGroup.getCheckedRadioButtonId();
        priceText.setText(String.valueOf(selectedId));


    }
}