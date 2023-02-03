package com.example.mini_project;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login_activity extends AppCompatActivity {

    EditText editText, editText2;
    String email, pass;
    Button btnInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent intent2 = getIntent();

        btnInput = findViewById(R.id.button);
        btnInput.setOnClickListener(view -> {

            editText = findViewById(R.id.enter_email);
            email = editText.getText().toString();

            editText2 = findViewById(R.id.enter_pass);
            pass = editText2.getText().toString();

            if (email.equals("123@mit.com") && pass.equals("0000")) {
                Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                //toast is used to show message when clicking the button

                // Intent intent = new Intent(getApplicationContext(), HomePage.class);
                //startActivity(intent);
            }
            else {
                Toast.makeText(getApplicationContext(), "Invalid email or password", Toast.LENGTH_SHORT).show();
                //toast is used to show message when clicking the button
            }

        });
    }
}