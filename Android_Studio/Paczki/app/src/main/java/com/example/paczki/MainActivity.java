package com.example.paczki;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class
MainActivity extends AppCompatActivity {

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
        image = findViewById(R.id.imageView1);
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

        buttonAccept.setOnClickListener(view -> {
            checkCode();
        });


    }

    private void checkPrice(){
        int selectedId = radioGroup.getCheckedRadioButtonId();
        if(selectedId == 2131231099){
            priceText.setText("Cena: 1 zł");
            image.setImageResource(R.drawable.pocztowka);
        } else if (selectedId == 2131231097) {
            priceText.setText("Cena: 1,5 zł");
            image.setImageResource(R.drawable.list);
        } else if (selectedId == 2131231098) {
            priceText.setText("Cena: 10 zł");
            image.setImageResource(R.drawable.paczka);
        }
    }

    public boolean isNumeric(String str) {
        return str != null && str.matches("\\d+");
    }

    private void checkCode(){
        String code = textPostCode.getText().toString().trim();

        if(code.length() != 5){
            Toast toast = Toast.makeText(this, "Nieprawidłowa liczba cyfr w kodzie pocztowym", Toast.LENGTH_LONG);
            toast.show();
        } else if (!isNumeric(code)) {
            Toast toast = Toast.makeText(this, "Kod pocztowy powinien się składać z samych cyfr", Toast.LENGTH_LONG);
            toast.show();
        }else{
            Toast toast = Toast.makeText(this, "Dane przesyłki zostały wprowafdzone", Toast.LENGTH_LONG);
            toast.show();
        }
    }
}