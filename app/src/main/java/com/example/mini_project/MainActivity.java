package com.example.mini_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnInput,btnInput2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInput = findViewById(R.id.button6);
        btnInput.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Welcome new user, Please Register", Toast.LENGTH_SHORT).show();
            //toast is used to show message when clicking the button

            Intent intent1 = new Intent(getApplicationContext(), Signin_activity.class);
            startActivity(intent1);
        });

        btnInput2 = findViewById(R.id.button7);
        btnInput2.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Welcome back", Toast.LENGTH_SHORT).show();
            //toast is used to show message when clicking the button

            Intent intent2 = new Intent(getApplicationContext(), Login_activity.class);
            startActivity(intent2);
        });
    }
}