package com.example.mini_project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Signin_activity extends AppCompatActivity {

    EditText enter_name, enter_age, enter_gender, enter_email, enter_pass, enter_height, enter_weight;
    String name, age1, age = "", gender, email, pass, weight, height, bmi;
    Button btnInput;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    double bmr2;
    int n=10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);


        btnInput = findViewById(R.id.button2);
        btnInput.setOnClickListener(view -> {

            enter_name = findViewById(R.id.enter_name);
            name = enter_name.getText().toString();
            enter_age = findViewById(R.id.enter_age);
            age1 = enter_age.getText().toString();
            enter_gender = findViewById(R.id.enter_gender);
            gender = enter_gender.getText().toString();
            enter_email = findViewById(R.id.enter_email);
            email = enter_email.getText().toString();
            enter_pass = findViewById(R.id.enter_pass);
            pass = enter_pass.getText().toString();
            enter_weight = findViewById(R.id.enter_weight);
            weight = enter_weight.getText().toString();
            enter_height = findViewById(R.id.enter_height);
            height = enter_height.getText().toString();

            if (n > 4) {
                age = age1.substring(n - 4);
            } else {
                age = age1;
            }

            int flag=0, flag2=0, flag3=0;
            String a = "MALE";
            String b = "FEMALE";
            String phy = "none";
            String men = "none";
            double age2 = Double.parseDouble(age);
            age2 = 2023-age2;
            double height2 = Double.parseDouble(height);
            double weight2 = Double.parseDouble(weight);
            double bmi2 = weight2 / (height2 / 100 * height2 / 100);
            bmi = String.valueOf(bmi2);

            if(Objects.equals(gender, a)) {
                bmr2 = 66 + (13.7 * weight2) + (5 * height2) - (6.8 * age2);
                flag3=1;
            }
            else if(Objects.equals(gender, b)) {
                bmr2 = 655 + (9.6 * weight2) + (1.8 * height2) - (4.7 *age2);
                flag3=1;
            }

            char[] ch = new char[email.length()];
            for (int j = 0; j < email.length(); j++) {
                ch[j] = email.charAt(j);
            }
            for(int i=0; i<email.length(); i++) {
                if(ch[i]== '@') {
                    flag=1;
                }
                if(ch[i]=='.') {
                    flag2=1;
                }
            }

            age = String.valueOf(age2);

            mAuth = FirebaseAuth.getInstance();

            if(flag==1 && flag2==1 && flag3==1) {
                mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this, task ->
                                mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this, task2 -> {
                                    FirebaseUser user =  FirebaseAuth.getInstance().getCurrentUser();
                                    if (task.isSuccessful()) {
                                            Toast.makeText(getApplicationContext(), "User Already exists, Please Login", Toast.LENGTH_SHORT).show();
                                    } else {
                                            mDatabase = FirebaseDatabase.getInstance().getReference();
                                            mDatabase.child("users").child(Objects.requireNonNull(mAuth.getUid())).child("Name").setValue(name);
                                            mDatabase = FirebaseDatabase.getInstance().getReference();
                                            mDatabase.child("users").child(Objects.requireNonNull(mAuth.getUid())).child("Age").setValue(age);
                                            mDatabase = FirebaseDatabase.getInstance().getReference();
                                            mDatabase.child("users").child(Objects.requireNonNull(mAuth.getUid())).child("Gender").setValue(gender);
                                            mDatabase = FirebaseDatabase.getInstance().getReference();
                                            mDatabase.child("users").child(Objects.requireNonNull(mAuth.getUid())).child("Height").setValue(height);
                                            mDatabase = FirebaseDatabase.getInstance().getReference();
                                            mDatabase.child("users").child(Objects.requireNonNull(mAuth.getUid())).child("Weight").setValue(weight);
                                            mDatabase = FirebaseDatabase.getInstance().getReference();
                                            mDatabase.child("users").child(Objects.requireNonNull(mAuth.getUid())).child("BMI").setValue(bmi);
                                            mDatabase = FirebaseDatabase.getInstance().getReference();
                                            mDatabase.child("users").child(Objects.requireNonNull(mAuth.getUid())).child("BMR").setValue(bmr2);
                                            mDatabase = FirebaseDatabase.getInstance().getReference();
                                            mDatabase.child("users").child(Objects.requireNonNull(mAuth.getUid())).child("Phy").setValue(phy);
                                            mDatabase = FirebaseDatabase.getInstance().getReference();
                                            mDatabase.child("users").child(Objects.requireNonNull(mAuth.getUid())).child("Men").setValue(men);
                                            mDatabase = FirebaseDatabase.getInstance().getReference();

                                            Intent intent2 = new Intent(getApplicationContext(), Login_activity.class);
                                            startActivity(intent2);
                                    }
                                }));
            }
            else if(flag==0 || flag2==0){
                Toast.makeText(getApplicationContext(), "Invalid Email Address", Toast.LENGTH_SHORT).show();
            }
            else if(flag3==0) {
                Toast.makeText(getApplicationContext(), "Input Error in Gender", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getApplicationContext(), "Unknown Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}