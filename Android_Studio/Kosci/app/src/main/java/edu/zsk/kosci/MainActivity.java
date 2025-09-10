package edu.zsk.kosci;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView dice1, dice2, dice3, dice4, dice5;
    private TextView rollResultLabel, gameResultLabel;
    private Button rollButton, resetButton;

    private int totalGameScore = 0;
    private final Random random = new Random();

    private final int[] diceImages = {
            R.drawable.k1,
            R.drawable.k2,
            R.drawable.k3,
            R.drawable.k4,
            R.drawable.k5,
            R.drawable.k6
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        setupButtonListeners();
    }

    private void initializeViews() {
        dice1 = findViewById(R.id.dice1);
        dice2 = findViewById(R.id.dice2);
        dice3 = findViewById(R.id.dice3);
        dice4 = findViewById(R.id.dice4);
        dice5 = findViewById(R.id.dice5);

        rollResultLabel = findViewById(R.id.rollResultLabel);
        gameResultLabel = findViewById(R.id.gameResultLabel);

        rollButton = findViewById(R.id.rollButton);
        resetButton = findViewById(R.id.resetButton);

        TextView titleTextView = findViewById(R.id.titleTextView);
        titleTextView.setText("Gra w kości. Autor 000000002137");
    }

    private void setupButtonListeners() {
        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDices();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    private void rollDices() {
        int[] diceValues = new int[5];
        diceValues[0] = random.nextInt(6) + 1;
        diceValues[1] = random.nextInt(6) + 1;
        diceValues[2] = random.nextInt(6) + 1;
        diceValues[3] = random.nextInt(6) + 1;
        diceValues[4] = random.nextInt(6) + 1;

        updateDiceImages(diceValues);

        int rollScore = calculateScore(diceValues);

        rollResultLabel.setText("Wynik tego losowania: " + rollScore);

        totalGameScore += rollScore;
        gameResultLabel.setText("Wynik gry: " + totalGameScore);
    }

    private void updateDiceImages(int[] diceValues) {
        dice1.setImageResource(diceImages[diceValues[0] - 1]);
        dice2.setImageResource(diceImages[diceValues[1] - 1]);
        dice3.setImageResource(diceImages[diceValues[2] - 1]);
        dice4.setImageResource(diceImages[diceValues[3] - 1]);
        dice5.setImageResource(diceImages[diceValues[4] - 1]);
    }

    private int calculateScore(int[] diceValues) {
        int[] counts = new int[7];

        for (int value : diceValues) {
            counts[value]++;
        }

        int score = 0;

        for (int i = 1; i <= 6; i++) {
            if (counts[i] >= 2) {
                score += i * counts[i];
            }
        }

        return score;
    }

    private void resetGame() {
        dice1.setImageResource(R.drawable.question);
        dice2.setImageResource(R.drawable.question);
        dice3.setImageResource(R.drawable.question);
        dice4.setImageResource(R.drawable.question);
        dice5.setImageResource(R.drawable.question);

        totalGameScore = 0;
        rollResultLabel.setText("Wynik tego losowania: 0");
        gameResultLabel.setText("Wynik gry: 0");
    }
}