package com.example.mini_project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mini_project.databinding.ActivityUpdateBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class Update_activity extends AppCompatActivity {
    ActivityUpdateBinding binding;
    FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    double bmr2;
    String bmi, age1, name, height, weight, age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button11.setOnClickListener(view -> {

            name = binding.enterName2.getText().toString();
            age1 = binding.enterAge2.getText().toString();
            height = binding.enterHeight2.getText().toString();
            weight = binding.enterWeight2.getText().toString();
            String a = "MALE";
            String b = "FEMALE";
            int n=10;
            if (n > 4) {
                age = age1.substring(n - 4);
            } else {
                age = age1;
            }
            double age2 = Double.parseDouble(age);
            age2 = 2023-age2;
            age = String.valueOf(age2);

            HashMap user = new HashMap();
            user.put("Name",name);
            user.put("Age",age);
            user.put("Weight",weight);
            user.put("Height",height);

            databaseReference = FirebaseDatabase.getInstance().getReference("users");
            String id3 = null;
            if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
            else{
                id3 = FirebaseAuth.getInstance().getCurrentUser().getUid();
            }

            databaseReference.child(id3).get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    DataSnapshot dataSnapshot2 = task.getResult();
                    String gen = String.valueOf(dataSnapshot2.child("Gender").getValue());
                    double age3 = Double.parseDouble(age);
                    double height2 = Double.parseDouble(height);
                    double weight2 = Double.parseDouble(weight);
                    double bmi2 = weight2 / (height2 / 100 * height2 / 100);
                    bmi = String.valueOf(bmi2);
                    if(Objects.equals(gen, a)) {
                        bmr2 = 66 + (13.7 * weight2) + (5 * height2) - (6.8 * age3);
                    }
                    else if(Objects.equals(gen, b)) {
                        bmr2 = 655 + (9.6 * weight2) + (1.8 * height2) - (4.7 *age3);
                    }

                    mAuth = FirebaseAuth.getInstance();
                    databaseReference = FirebaseDatabase.getInstance().getReference();
                    databaseReference.child("users").child(Objects.requireNonNull(mAuth.getUid())).child("BMI").setValue(bmi);
                    databaseReference = FirebaseDatabase.getInstance().getReference();
                    databaseReference.child("users").child(Objects.requireNonNull(mAuth.getUid())).child("BMR").setValue(bmr2);
                }
            });

            databaseReference.child(id3).updateChildren(user).addOnCompleteListener(task -> {
                if(task.isSuccessful()){

                    Toast.makeText(getApplicationContext(), "Profile Updated", Toast.LENGTH_SHORT).show();
                    Intent intent9 = new Intent(getApplicationContext(), Profile_activity.class);
                    startActivity(intent9);
                }else{
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}