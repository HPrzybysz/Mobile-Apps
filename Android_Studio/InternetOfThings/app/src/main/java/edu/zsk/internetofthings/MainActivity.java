package edu.zsk.internetofthings;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //pralka
    private EditText washingMachineNumberInput;
    private Button washingMaschineConfirmButton;
    private TextView washingMaschineStatusText;

    //odkurzacz
    private Button vacuumCleanerToggleButton;
    private TextView vacuumCleanerStatusText;

    private boolean isVaccumCleanerOn = false;
    private int currentWashingProgram = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initalizeViews();
        setupClickListeners();

        EdgeToEdge.enable(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    private void initalizeViews() {

        washingMachineNumberInput = findViewById(R.id.pralkaNumber);
        washingMaschineConfirmButton = findViewById(R.id.pralkaBtn);
        washingMaschineStatusText = findViewById(R.id.pralkaTekst2);

        vacuumCleanerStatusText = findViewById(R.id.odkurzaczTekst2);
        vacuumCleanerToggleButton = findViewById(R.id.odkurzaczBtn);

    }

    private void setupClickListeners() {
        washingMaschineConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmWaschingProgram();
            }
        });

        vacuumCleanerToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleVacuumCleaner();
            }
        });
    }

    private void confirmWaschingProgram(){
        String inputText = washingMachineNumberInput.getText().toString().trim();

        if (inputText.isEmpty()){
            washingMaschineStatusText.setText("Numer prania: nie podano");
            return;
        }

        try {
            int programNumber = Integer.parseInt(inputText);

            if(programNumber >= 1 && programNumber <= 12){
                currentWashingProgram = programNumber;
                washingMaschineStatusText.setText("Numer prania: " + programNumber);
            } else {
                washingMaschineStatusText.setText("Numer prania: nieprawidłowy (1-12)");
            }
        }catch (NumberFormatException e){
            washingMaschineStatusText.setText("Numer prania: nieprawidłowy format");
        }

        washingMachineNumberInput.setText("");
    }

    private void toggleVacuumCleaner(){
        isVaccumCleanerOn = !isVaccumCleanerOn;

        if(isVaccumCleanerOn){
            vacuumCleanerToggleButton.setText("Wyłącz");
            vacuumCleanerStatusText.setText("Odkurzacz włączony");
        }else{
            vacuumCleanerToggleButton.setText("Wyłącz");
            vacuumCleanerStatusText.setText("Odkurzacz wyłączony");
        }
    }

}