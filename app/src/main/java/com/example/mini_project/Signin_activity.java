package com.example.mini_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Signin_activity extends AppCompatActivity {

    EditText enter_name, enter_age, enter_gender, enter_email, enter_pass;
    String name, age, gender, email, pass;
    Button btnInput;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        Intent intent1 = getIntent();

        btnInput = findViewById(R.id.button2);
        btnInput.setOnClickListener(view -> {

            enter_name = findViewById(R.id.enter_name);
            name = enter_name.getText().toString();
            enter_age = findViewById(R.id.enter_age);
            age = enter_age.getText().toString();
            enter_gender = findViewById(R.id.enter_gender);
            gender = enter_gender.getText().toString();
            enter_email = findViewById(R.id.enter_email);
            email = enter_email.getText().toString();
            enter_pass = findViewById(R.id.enter_pass);
            pass = enter_pass.getText().toString();

            mAuth = FirebaseAuth.getInstance();

            mAuth.signInWithEmailAndPassword(email, pass)

                    .addOnCompleteListener(this, task ->
                            mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this, task2 -> {
                        if (task.isSuccessful()){
                            if(task2.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();

                                Toast.makeText(getApplicationContext(), "Welcome, Please Login", Toast.LENGTH_SHORT).show();

                                Intent intent2 = new Intent(getApplicationContext(), Login_activity.class);
                                startActivity(intent2);
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Something went wrong, Try again", Toast.LENGTH_SHORT).show();
                        }
                    }));
        });
    }
}
