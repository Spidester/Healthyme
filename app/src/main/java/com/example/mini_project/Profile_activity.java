package com.example.mini_project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mini_project.databinding.ActivityProfileBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Profile_activity extends AppCompatActivity {
    Button btnInput;
    DatabaseReference reference;
    ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

            reference = FirebaseDatabase.getInstance().getReference("users");
            String id = null;
            if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            } else {
                id = FirebaseAuth.getInstance().getCurrentUser().getUid();
            }
        assert id != null;
        reference.child(id).get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    DataSnapshot dataSnapshot = task.getResult();
                    String name = String.valueOf(dataSnapshot.child("Name").getValue());
                    binding.textView14.setText(name);
                    String age = String.valueOf(dataSnapshot.child("Age").getValue());
                    binding.textView15.setText(age);
                    String gender = String.valueOf(dataSnapshot.child("Gender").getValue());
                    binding.textView16.setText(gender);
                    String height = String.valueOf(dataSnapshot.child("Height").getValue());
                    binding.textView17.setText(height);
                    String weight = String.valueOf(dataSnapshot.child("Weight").getValue());
                    binding.textView25.setText(weight);
                }
            });

        binding.btn2.setOnClickListener(view -> {
            Intent intent8 = new Intent(getApplicationContext(), Update_activity.class);
            startActivity(intent8);
        });
        btnInput = findViewById(R.id.button18);
        btnInput.setOnClickListener(view -> {
            Intent intent3 = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent3);
        });
    }
}