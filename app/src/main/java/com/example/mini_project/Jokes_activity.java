package com.example.mini_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Jokes_activity extends AppCompatActivity {
    Button btnInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes);

        btnInput = findViewById(R.id.button3);
        btnInput.setOnClickListener(view -> {
            Intent intent3 = new Intent(getApplicationContext(), Profile_activity.class);
            startActivity(intent3);
        });

        btnInput = findViewById(R.id.button10);
        btnInput.setOnClickListener(view -> {
            Intent intent3 = new Intent(getApplicationContext(), RickRoll_activity.class);
            startActivity(intent3);
        });
    }
}