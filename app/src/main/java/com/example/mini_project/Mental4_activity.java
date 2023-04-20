package com.example.mini_project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mini_project.databinding.ActivityMental2Binding;
import com.example.mini_project.databinding.ActivityMental4Binding;

public class Mental4_activity extends AppCompatActivity {
Button btnInput;
EditText again_input;
ActivityMental4Binding binding;
char again;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMental4Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        btnInput = findViewById(R.id.button3);
        btnInput.setOnClickListener(view -> {
            Intent intent3 = new Intent(getApplicationContext(), Profile_activity.class);
            startActivity(intent3);
        });

        btnInput = findViewById(R.id.button27);
        btnInput.setOnClickListener(view -> {

            again_input = findViewById(R.id.again_input);
            again = again_input.getText().toString().charAt(0);

            if(again=='Y'){
                Intent intent4 = new Intent(getApplicationContext(), Mental_activity.class);
                startActivity(intent4);
            }else if(again=='N'){
                Intent intent4 = new Intent(getApplicationContext(), Mental3_activity.class);
                startActivity(intent4);
            }else{
                Toast.makeText(getApplicationContext(), "Invalid Input", Toast.LENGTH_SHORT).show();
            }
        });
    }
}