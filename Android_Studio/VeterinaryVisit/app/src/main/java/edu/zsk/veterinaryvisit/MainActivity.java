package edu.zsk.veterinaryvisit;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText ownerNameEditText;
    private ListView speciesListView;
    private TextView ageValueTextView;
    private SeekBar ageSeekBar;
    private EditText visitPurposeEditText;
    private EditText appointmentTimeEditText;
    private Button okButton;
    private TextView resultTextView;

    private static final String SPECIES_DOG = "Pies";
    private static final String SPECIES_CAT = "Kot";
    private static final String SPECIES_GUINEA_PIG = "Świnka morska";

    private static final int MAX_AGE_DOG = 18;
    private static final int MAX_AGE_CAT = 20;
    private static final int MAX_AGE_GUINEA_PIG = 9;

    private String selectedSpecies = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        setupSpeciesList();
        setupSeekBar();
        setupOkButton();
    }

    private void initializeViews() {
        ownerNameEditText = findViewById(R.id.ownerNameEditText);
        speciesListView = findViewById(R.id.speciesListView);
        ageValueTextView = findViewById(R.id.ageValueTextView);
        ageSeekBar = findViewById(R.id.ageSeekBar);
        visitPurposeEditText = findViewById(R.id.visitPurposeEditText);
        appointmentTimeEditText = findViewById(R.id.appointmentTimeEditText);
        okButton = findViewById(R.id.okButton);
        resultTextView = findViewById(R.id.resultTextView);
    }

    private void setupSpeciesList() {
        String[] speciesArray = {SPECIES_DOG, SPECIES_CAT, SPECIES_GUINEA_PIG};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                speciesArray
        );

        speciesListView.setAdapter(adapter);

        speciesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedSpecies = speciesArray[position];
                updateAgeLimitBasedOnSpecies();
            }
        });
    }

    private void updateAgeLimitBasedOnSpecies() {
        switch (selectedSpecies) {
            case SPECIES_DOG:
                ageSeekBar.setMax(MAX_AGE_DOG);
                break;
            case SPECIES_CAT:
                ageSeekBar.setMax(MAX_AGE_CAT);
                break;
            case SPECIES_GUINEA_PIG:
                ageSeekBar.setMax(MAX_AGE_GUINEA_PIG);
                break;
        }

        ageSeekBar.setProgress(0);
        ageValueTextView.setText("0");
    }

    private void setupSeekBar() {
        ageSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ageValueTextView.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private void setupOkButton() {
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collectAndDisplayFormData();
            }
        });
    }

    private void collectAndDisplayFormData() {
        String ownerName = ownerNameEditText.getText().toString().trim();
        String visitPurpose = visitPurposeEditText.getText().toString().trim();
        String appointmentTime = appointmentTimeEditText.getText().toString().trim();
        String age = ageValueTextView.getText().toString().trim();

        if (ownerName.isEmpty() || selectedSpecies.isEmpty() || visitPurpose.isEmpty()) {
            showToast();
            return;
        }

        String result = ownerName + ", " +
                selectedSpecies + ", " +
                age + " lat, " +
                visitPurpose + ", " +
                appointmentTime;

        resultTextView.setText(result);
        resultTextView.setVisibility(View.VISIBLE);

    }

    private void showToast() {
        Toast.makeText(this, "usupelnij pola", Toast.LENGTH_LONG).show();
    }
}