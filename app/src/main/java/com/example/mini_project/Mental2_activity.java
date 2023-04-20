package com.example.mini_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mini_project.databinding.ActivityMental2Binding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Mental2_activity extends AppCompatActivity {
    FirebaseAuth mAuth;
    DatabaseReference mDatabase, reference;
    EditText input_11, input_12, input_13, input_14, input_15, input_16, input_17, input_18, input_19, input_20, input_21, input_22, input_23, input_24, input_25, input_26;
    char[] inp = new char[16];
    Button btnInput;
    ActivityMental2Binding binding;
    int ans=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMental2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        btnInput = findViewById(R.id.button3);
        btnInput.setOnClickListener(view -> {
            Intent intent3 = new Intent(getApplicationContext(), Profile_activity.class);
            startActivity(intent3);
        });

        String men = "done";

        btnInput = findViewById(R.id.button15);
        btnInput.setOnClickListener(view -> {

            input_11 = findViewById(R.id.input_11);
            inp[0] = input_11.getText().toString().charAt(0);
            input_12 = findViewById(R.id.input_12);
            inp[1] = input_12.getText().toString().charAt(0);
            input_13 = findViewById(R.id.input_13);
            inp[2] = input_13.getText().toString().charAt(0);
            input_14 = findViewById(R.id.input_14);
            inp[3] = input_14.getText().toString().charAt(0);
            input_15 = findViewById(R.id.input_15);
            inp[4] = input_15.getText().toString().charAt(0);
            input_16 = findViewById(R.id.input_16);
            inp[5] = input_16.getText().toString().charAt(0);
            input_17 = findViewById(R.id.input_17);
            inp[6] = input_17.getText().toString().charAt(0);
            input_18 = findViewById(R.id.input_18);
            inp[7] = input_18.getText().toString().charAt(0);
            input_19 = findViewById(R.id.input_19);
            inp[8] = input_19.getText().toString().charAt(0);
            input_20 = findViewById(R.id.input_20);
            inp[9] = input_20.getText().toString().charAt(0);
            input_21 = findViewById(R.id.input_21);
            inp[10] = input_21.getText().toString().charAt(0);
            input_22 = findViewById(R.id.input_22);
            inp[11] = input_22.getText().toString().charAt(0);
            input_23 = findViewById(R.id.input_23);
            inp[12] = input_23.getText().toString().charAt(0);
            input_24 = findViewById(R.id.input_24);
            inp[13] = input_24.getText().toString().charAt(0);
            input_25 = findViewById(R.id.input_25);
            inp[14] = input_25.getText().toString().charAt(0);
            input_26 = findViewById(R.id.input_26);
            inp[15] = input_26.getText().toString().charAt(0);

            for(int i=0; i<16; i++){
                switch (inp[i]) {
                    case 'A':
                        break;
                    case 'B':
                        ans = ans + 1;
                        break;
                    case 'C':
                        ans = ans + 2;
                        break;
                    case 'D':
                        ans = ans + 3;
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "Invalid Input", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
            String ans2 = String.valueOf(ans);
            reference = FirebaseDatabase.getInstance().getReference("users");
            String id = null;
            if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
            else{
                id = FirebaseAuth.getInstance().getCurrentUser().getUid();
            }
            reference.child(id).get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {

                    mAuth = FirebaseAuth.getInstance();
                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    mDatabase.child("users").child(Objects.requireNonNull(mAuth.getUid())).child("Score2").setValue(ans2);
                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    mDatabase.child("users").child(Objects.requireNonNull(mAuth.getUid())).child("Men").setValue(men);
                    mDatabase = FirebaseDatabase.getInstance().getReference();

                    Intent intent10 = new Intent(getApplicationContext(), Mental3_activity.class);
                    startActivity(intent10);

                }
            });
        });
    }
}