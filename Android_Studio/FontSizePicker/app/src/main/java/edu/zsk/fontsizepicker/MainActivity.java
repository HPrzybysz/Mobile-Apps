package edu.zsk.fontsizepicker;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView sizeValue;
    private SeekBar fontSizeSlider;
    private TextView quoteText;
    private Button changeQuoteButton;

    private String[] quotes = {"Dzień dobry", "Good morning", "Buenos dias"};
    private int currentQuoteIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        initializeViews();
        setupEventListeners();


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void initializeViews() {
        sizeValue = findViewById(R.id.sizeValue);
        fontSizeSlider = findViewById(R.id.fontSizeSlider);
        quoteText = findViewById(R.id.quoteText);
        changeQuoteButton = findViewById(R.id.changeQuoteButton);

        updateSizeDisplay();
    }

    private void updateSizeDisplay() {
        int currentSize = fontSizeSlider.getProgress();
        sizeValue.setText(String.valueOf(currentSize));
    }

    private void setupEventListeners() {
        fontSizeSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateSizeDisplay();
                updateQuoteFontSize();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        changeQuoteButton.setOnClickListener(v -> changeQuote());
    }



    private void updateQuoteFontSize() {
        int currentSize = fontSizeSlider.getProgress();
        quoteText.setTextSize(currentSize);
    }

    private void changeQuote() {
        currentQuoteIndex = (currentQuoteIndex + 1) % quotes.length;
        quoteText.setText(quotes[currentQuoteIndex]);
    }
}