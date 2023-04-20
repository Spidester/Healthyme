package com.example.mini_project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.mini_project.databinding.ActivityMentalBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Mental_activity extends AppCompatActivity {
    FirebaseAuth mAuth;
    DatabaseReference mDatabase, reference;
    EditText input_1, input_2, input_3, input_4, input_5, input_6, input_7, input_8, input_9;
    char[] inp = new char[9];
    Button btnInput;
    int ans;
    ActivityMentalBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMentalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        btnInput = findViewById(R.id.button3);
        btnInput.setOnClickListener(view -> {
            Intent intent3 = new Intent(getApplicationContext(), Profile_activity.class);
            startActivity(intent3);
        });

        btnInput = findViewById(R.id.button14);
        btnInput.setOnClickListener(view -> {

            input_1 = findViewById(R.id.input_1);
            inp[0] = input_1.getText().toString().charAt(0);
            input_2 = findViewById(R.id.input_2);
            inp[1] = input_2.getText().toString().charAt(0);
            input_3 = findViewById(R.id.input_3);
            inp[2] = input_3.getText().toString().charAt(0);
            input_4 = findViewById(R.id.input_4);
            inp[3] = input_4.getText().toString().charAt(0);
            input_5 = findViewById(R.id.input_5);
            inp[4] = input_5.getText().toString().charAt(0);
            input_6 = findViewById(R.id.input_6);
            inp[5] = input_6.getText().toString().charAt(0);
            input_7 = findViewById(R.id.input_17);
            inp[6] = input_7.getText().toString().charAt(0);
            input_8 = findViewById(R.id.input_8);
            inp[7] = input_8.getText().toString().charAt(0);
            input_9 = findViewById(R.id.input_9);
            inp[8] = input_9.getText().toString().charAt(0);

            for(int i=0; i<9; i++){
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
            String ans1 = String.valueOf(ans);
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
                mDatabase.child("users").child(Objects.requireNonNull(mAuth.getUid())).child("Score").setValue(ans1);

                Intent intent10 = new Intent(getApplicationContext(), Mental2_activity.class);
                startActivity(intent10);

                }
            });
        });
    }
}