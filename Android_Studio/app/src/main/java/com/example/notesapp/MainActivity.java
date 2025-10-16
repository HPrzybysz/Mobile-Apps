package com.example.notesapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> EntriesArray;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        initalizeViews();

        initializeArrays();

        setupButtonListener();


        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });




    }

    private void initalizeViews(){
        EditText EntriesInput;
        Button EntriesButton;
        ListView EntriesList;

        EntriesInput = findViewById(R.id.addEntryField);
        EntriesButton = findViewById(R.id.addEntryButton);
        EntriesList = findViewById(R.id.list);
    }

    private void initializeArrays(){
        EntriesArray = new ArrayList<>();

        EntriesArray.add("maslo");
        EntriesArray.add("isc na spacer");
        EntriesArray.add("isc spac");

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                EntriesArray
        );
        EntriesList.setAdapter(adapter);
    }

    private void setupButtonListener(){
        EntriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItemToList();
            }
        });
    }

    private void addItemToList(){
        String inputText = EntriesInput.getText().toString().trim();
        if(!inputText.isEmpty()){
            EntriesArray.add(inputText);
            adapter.notifyDataSetChanged();
            EntriesInput.setText("");
        }else{
            Toast.makeText(this, "Wprowadź dane", Toast.LENGTH_SHORT).show();
        }

    }
}