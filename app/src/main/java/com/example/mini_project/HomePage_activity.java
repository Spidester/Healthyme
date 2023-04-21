package com.example.mini_project;

import static android.content.Intent.ACTION_VIEW;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mini_project.databinding.ActivityHomePageBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomePage_activity extends AppCompatActivity {

    Button btnInput;
    ImageButton imgBtn;
    DatabaseReference reference;
    ActivityHomePageBinding binding;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent2 = getIntent();
        String  a = "none";
        String  b = "done";

        btnInput = findViewById(R.id.button3);
        btnInput.setOnClickListener(view -> {
            Intent intent3 = new Intent(getApplicationContext(), Profile_activity.class);
            startActivity(intent3);
        });



        imgBtn = (ImageButton) findViewById(R.id.imageButton);
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent19 = new Intent(getApplicationContext(), Motivation_activity.class);
                startActivity(intent19);
            }
        });

        imgBtn = (ImageButton) findViewById(R.id.imageButton7);
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent19 = new Intent(getApplicationContext(), Jokes_activity.class);
                startActivity(intent19);
            }
        });

        btnInput = findViewById(R.id.button5);
        btnInput.setOnClickListener(view -> {
            Intent intent10 = new Intent(getApplicationContext(), Nuskhe_activity.class);
            startActivity(intent10);
        });
        binding.button4.setOnClickListener(view -> {
            reference = FirebaseDatabase.getInstance().getReference("users");
            String id = null;
            if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            } else {
                id = FirebaseAuth.getInstance().getCurrentUser().getUid();
            }
            reference.child(id).get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    DataSnapshot dataSnapshot = task.getResult();
                    String height = String.valueOf(dataSnapshot.child("Height").getValue());
                    binding.textView3.setText(height);
                    String weight = String.valueOf(dataSnapshot.child("Weight").getValue());
                    binding.textView4.setText(weight);
                    String bmi = String.valueOf(dataSnapshot.child("BMI").getValue());
                    binding.textView5.setText(bmi);
                    String bmr = String.valueOf(dataSnapshot.child("BMR").getValue());
                    binding.textView6.setText(bmr);
                }
            });
        });

        btnInput = findViewById(R.id.button8);
        btnInput.setOnClickListener(view -> {
            reference = FirebaseDatabase.getInstance().getReference("users");
            String id2 = null;
            if (FirebaseAuth.getInstance().getCurrentUser() == null){
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }else {
                id2 = FirebaseAuth.getInstance().getCurrentUser().getUid();
            }
            reference.child(id2).get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    DataSnapshot dataSnapshot2 = task.getResult();
                    String phy = String.valueOf(dataSnapshot2.child("Phy").getValue());
                    if(phy.equals(a)){
                        Intent intent4 = new Intent(getApplicationContext(), Physical_activity.class);
                        startActivity(intent4);
                    }
                    else if(phy.equals(b)){
                        Intent intent5 = new Intent(getApplicationContext(), Physical2_activity.class);
                        startActivity(intent5);
                    }
                }
            });
        });

        btnInput = findViewById(R.id.button9);
        btnInput.setOnClickListener(view -> {
            reference = FirebaseDatabase.getInstance().getReference("users");
            String id2 = null;
            if (FirebaseAuth.getInstance().getCurrentUser() == null){
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }else {
                id2 = FirebaseAuth.getInstance().getCurrentUser().getUid();
            }
            reference.child(id2).get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    DataSnapshot dataSnapshot2 = task.getResult();
                    String phy = String.valueOf(dataSnapshot2.child("Men").getValue());
                    if(phy.equals(a)){
                        Intent intent4 = new Intent(getApplicationContext(), Mental_activity.class);
                        startActivity(intent4);
                    }
                    else if(phy.equals(b)){
                        Intent intent5 = new Intent(getApplicationContext(), Mental4_activity.class);
                        startActivity(intent5);
                    }
                }
            });
        });
        }
    }
