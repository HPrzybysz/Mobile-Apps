package edu.zsk.szyfrowanie;

import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private TextView cipheredOut;
    private EditText key;
    private EditText toCipherEditTextField;
    private Button cipherButton;
    private Button saveToFile;

    private String encryptedText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        cipheredOut = findViewById(R.id.CipheredOut);
        key = findViewById(R.id.keyField);
        toCipherEditTextField = findViewById(R.id.toCipherTextField);
        cipherButton = findViewById(R.id.cipherButton);
        saveToFile = findViewById(R.id.saveToFile);


        cipherButton.setOnClickListener(v -> encryptText());
        saveToFile.setOnClickListener(view -> saveToFile());


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void encryptText(){
        String textToEncrypt = toCipherEditTextField.getText().toString();
        String keyText = key.getText().toString();

        int shift;

        try {
            shift = Integer.parseInt(keyText);
        }catch(NumberFormatException e){
            shift = 0;
        }

        encryptedText = caesarCipher(textToEncrypt, shift);
        cipheredOut.setText(encryptedText);
    }

    private String caesarCipher(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isLowerCase(character) ? 'a' : 'A';
                char shiftedChar = (char) ((character - base + shift) % 26 + base);
                result.append(shiftedChar);
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }

    private void saveToFile(){

        String fileName = "cos.txt";

        try {

            File downloadsDir = getExternalFilesDir(android.os.Environment.DIRECTORY_DOWNLOADS);

            if (downloadsDir == null) {
                Toast.makeText(this, "no downloads :(", Toast.LENGTH_SHORT).show();

                return;
            }

            File file = new File(downloadsDir, fileName);

            try (FileOutputStream fos = new FileOutputStream(file)) {

                fos.write(encryptedText.getBytes());
                String message = "Zapisano do: " + file.getAbsolutePath();
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();

            }

        } catch (IOException e) {
            Toast.makeText(this, "Błąd :o !?!?!?!!?: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}