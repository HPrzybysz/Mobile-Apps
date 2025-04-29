package edu.zsk.Przybysz;

import android.os.Bundle;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase database;
    private EditText emailInput;
    private EditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initDb();

        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(view -> {
            String email = emailInput.getText().toString();
            String password = passwordInput.getText().toString();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(MainActivity.this, "Wypełnij wszystkie pola!", Toast.LENGTH_LONG).show();
                return;
            }

            if (!checkCredentials(email, password)) {
                Toast.makeText(MainActivity.this, "Niepoprawne dane logowania!", Toast.LENGTH_LONG).show();
                passwordInput.setText("");
                return;
            }

            Toast.makeText(MainActivity.this, "Zalogowano!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, LoggedInActivity.class);
            startActivity(intent);
        });
    }

    private void initDb() {
        SQLiteOpenHelper dbHelper = new SQLiteOpenHelper(this, "app_db", null, 1) {
            @Override
            public void onCreate(SQLiteDatabase sqLiteDatabase) {
                sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT, password TEXT)");
            }

            @Override
            public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            }
        };

        database = dbHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT COUNT(*) FROM users", null);
        if (cursor != null) {
            cursor.moveToFirst();
            int count = cursor.getInt(0);
            cursor.close();

            if (count == 0) {
                database.execSQL("INSERT INTO users (email, password) VALUES ('admin@example.com', 'admin')");
                database.execSQL("INSERT INTO users (email, password) VALUES ('user1@example.com', 'user1')");
                database.execSQL("INSERT INTO users (email, password) VALUES ('user2@example.com', 'user2')");
                database.execSQL("INSERT INTO users (email, password) VALUES ('user3@example.com', 'user3')");
            }
        }
    }

    private boolean checkCredentials(String email, String password) {
        Cursor cursor = database.rawQuery("SELECT password FROM users WHERE email = ?", new String[]{email});
        if (cursor != null && cursor.moveToFirst()) {
            String dbPassword = cursor.getString(0);
            cursor.close();
            return dbPassword.equals(password);
        }

        if (cursor != null) {
            cursor.close();
        }
        return false;
    }
}