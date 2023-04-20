package com.example.mini_project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mini_project.databinding.ActivityPhysicalBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Physical_activity extends AppCompatActivity {
    FirebaseAuth mAuth;
    DatabaseReference mDatabase, reference;
    EditText enter_activity, enter_sugar, enter_press;
    String activity;
    Double tdee, sugar, press;
    ActivityPhysicalBinding binding;
    Button btnInput;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPhysicalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        btnInput = findViewById(R.id.button3);
        btnInput.setOnClickListener(view -> {
            Intent intent3 = new Intent(getApplicationContext(), Profile_activity.class);
            startActivity(intent3);
        });

        String phy= "done";
        String a = "sedentary";
        String b = "low";
        String c = "active";
        String d = "very";

        binding.button12.setOnClickListener(view -> {

            enter_activity = findViewById(R.id.enter_activity);
            activity = enter_activity.getText().toString();
            enter_sugar = findViewById(R.id.enter_sugar);
            sugar = Double.parseDouble(enter_sugar.getText().toString());
            enter_press = findViewById(R.id.enter_press);
            press = Double.parseDouble(enter_press.getText().toString());

                reference = FirebaseDatabase.getInstance().getReference("users");
                String id2 = null;
                if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
                else{
                    id2 = FirebaseAuth.getInstance().getCurrentUser().getUid();
                }
                reference.child(id2).get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DataSnapshot dataSnapshot2 = task.getResult();
                        Double bmr2 = (Double) dataSnapshot2.child("BMR").getValue();

                        if (press >= 90 && press <= 120 && sugar <= 100 && sugar >= 70) {
                            if (Objects.equals(activity, a)) {
                                tdee = (bmr2 * 1.2);
                                mAuth = FirebaseAuth.getInstance();
                                mDatabase = FirebaseDatabase.getInstance().getReference();
                                mDatabase.child("users").child(Objects.requireNonNull(mAuth.getUid())).child("Phy").setValue(phy);
                                mDatabase = FirebaseDatabase.getInstance().getReference();
                                mDatabase.child("users").child(Objects.requireNonNull(mAuth.getUid())).child("Tdee").setValue(tdee);

                                Intent intent6 = new Intent(getApplicationContext(), Physical2_activity.class);
                                startActivity(intent6);
                            } else if (Objects.equals(activity, b)) {
                                tdee = (bmr2 * 1.375);
                                mAuth = FirebaseAuth.getInstance();
                                mDatabase = FirebaseDatabase.getInstance().getReference();
                                mDatabase.child("users").child(Objects.requireNonNull(mAuth.getUid())).child("Phy").setValue(phy);
                                mDatabase = FirebaseDatabase.getInstance().getReference();
                                mDatabase.child("users").child(Objects.requireNonNull(mAuth.getUid())).child("Tdee").setValue(tdee);

                                Intent intent6 = new Intent(getApplicationContext(), Physical2_activity.class);
                                startActivity(intent6);
                            } else if (Objects.equals(activity, c)) {
                                tdee = (bmr2 * 1.55);
                                mAuth = FirebaseAuth.getInstance();
                                mDatabase = FirebaseDatabase.getInstance().getReference();
                                mDatabase.child("users").child(Objects.requireNonNull(mAuth.getUid())).child("Phy").setValue(phy);
                                mDatabase = FirebaseDatabase.getInstance().getReference();
                                mDatabase.child("users").child(Objects.requireNonNull(mAuth.getUid())).child("Tdee").setValue(tdee);

                                Intent intent6 = new Intent(getApplicationContext(), Physical2_activity.class);
                                startActivity(intent6);
                            } else if (Objects.equals(activity, d)) {
                                tdee = (bmr2 * 1.725);
                                mAuth = FirebaseAuth.getInstance();
                                mDatabase = FirebaseDatabase.getInstance().getReference();
                                mDatabase.child("users").child(Objects.requireNonNull(mAuth.getUid())).child("Phy").setValue(phy);
                                mDatabase = FirebaseDatabase.getInstance().getReference();
                                mDatabase.child("users").child(Objects.requireNonNull(mAuth.getUid())).child("Tdee").setValue(tdee);

                                Intent intent6 = new Intent(getApplicationContext(), Physical2_activity.class);
                                startActivity(intent6);
                            } else {
                                Toast.makeText(getApplicationContext(), "Invalid Input ", Toast.LENGTH_SHORT).show();
                            }
                        } else if(press>120){
                            Intent intent7 = new Intent(getApplicationContext(), Hpress_activity.class);
                            startActivity(intent7);
                        } else if(press<90){
                            Intent intent7 = new Intent(getApplicationContext(), Lpress_activity.class);
                            startActivity(intent7);
                        } else if(sugar<70){
                            Intent intent7 = new Intent(getApplicationContext(), Lsugar_activity.class);
                            startActivity(intent7);
                        } else if(sugar>100){
                            Intent intent7 = new Intent(getApplicationContext(), Hsugar_activity.class);
                            startActivity(intent7);
                        } else{
                            Toast.makeText(getApplicationContext(), "Invalid Input ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        });
    }
}