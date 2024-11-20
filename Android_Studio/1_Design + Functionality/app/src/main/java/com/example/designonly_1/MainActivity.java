package com.example.designonly_1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {

    private EditText email1, password1, password2;
    private TextView response;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View mainView = findViewById(R.id.main);

        email1 = findViewById(R.id.emailInput);
        password1 = findViewById(R.id.passwordInput);
        password2 = findViewById(R.id.confirmPasswordInput);
        response = findViewById(R.id.messageDisplay);
        button = findViewById(R.id.submitButton);

        response.setText("Autor: HPrzbysz 000000000000");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!email1.getText().toString().contains("@")){
                    response.setText("Nieprawidłowy adres e-mail");
                }else if(!password1.getText().toString().equals(password2.getText().toString())){
                    response.setText("Hasła się różnią");
                }else{
                    response.setText("Witaj "+email1.getText().toString());
                }
            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
