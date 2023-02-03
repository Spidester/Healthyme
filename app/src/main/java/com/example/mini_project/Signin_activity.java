package com.example.mini_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class Signin_activity extends AppCompatActivity {

    Button btnInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        Intent intent1 = getIntent();

        btnInput = findViewById(R.id.button2);
        btnInput.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_SHORT).show();
            //toast is used to show message when clicking the button

            //Intent intent = new Intent(getApplicationContext(), HomePage.class);
            //startActivity(intent);
        });
    }
}