package com.example.mini_project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Login_activity extends AppCompatActivity {

    EditText enter_email, enter_pass;
    String email, pass;
    Button btnInput;
    FirebaseAuth mAuth;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnInput = findViewById(R.id.button);
        btnInput.setOnClickListener(view -> {


            enter_email = findViewById(R.id.enter_email);
            email = enter_email.getText().toString();
            enter_pass = findViewById(R.id.enter_pass);
            pass = enter_pass.getText().toString();

            if (TextUtils.isEmpty((email))){
                Toast.makeText(getApplicationContext(), "Invalid Input", Toast.LENGTH_SHORT).show();
                return;
            }if (TextUtils.isEmpty((pass))){
                Toast.makeText(getApplicationContext(), "Invalid Input", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth = FirebaseAuth.getInstance();

            mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Login successfully", Toast.LENGTH_SHORT).show();
                    Intent intent2 = new Intent(getApplicationContext(), HomePage_activity.class);
                    startActivity(intent2);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Invalid Input", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}