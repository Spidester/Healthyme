package com.example.mini_project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class Login_activity extends AppCompatActivity {

    EditText editText, editText2, enter_email, enter_pass;
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

            editText = findViewById(R.id.enter_email);
            email = editText.getText().toString();

            editText2 = findViewById(R.id.enter_pass);
            pass = editText2.getText().toString();

            enter_email = findViewById(R.id.enter_email);
            enter_pass = findViewById(R.id.enter_pass);

            mAuth = FirebaseAuth.getInstance();

            if(mAuth.getCurrentUser() != null){
                Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                //toast is used to show message when clicking the button

                Intent intent2 = new Intent(getApplicationContext(), HomePage_activity.class);
                startActivity(intent2);
            }
            else if(mAuth.getCurrentUser() == null){
                mAuth.signInWithEmailAndPassword(enter_email.getText().toString(), enter_pass.getText().toString())

                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                                //toast is used to show message when clicking the button

                                Intent intent2 = new Intent(getApplicationContext(), HomePage_activity.class);
                                startActivity(intent2);
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Invalid email or password", Toast.LENGTH_SHORT).show();
                                //toast is used to show message when clicking the button
                            }
                        });
            }

        });
    }
}