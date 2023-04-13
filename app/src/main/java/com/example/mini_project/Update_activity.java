package com.example.mini_project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mini_project.databinding.ActivityUpdateBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Update_activity extends AppCompatActivity {
    ActivityUpdateBinding binding;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button11.setOnClickListener(view -> {

            String name = binding.enterName2.getText().toString();
            String age1 = binding.enterAge2.getText().toString();
            String height = binding.enterHeight2.getText().toString();
            String weight = binding.enterWeight2.getText().toString();
            int n=10;
            String age;
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