package com.example.mini_project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mini_project.databinding.ActivityMental3Binding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Mental3_activity extends AppCompatActivity {
    Button btnInput;
    ActivityMental3Binding binding;
    DatabaseReference reference;
    RelativeLayout relativeLayout;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMental3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        btnInput = findViewById(R.id.button3);
        btnInput.setOnClickListener(view -> {
            Intent intent3 = new Intent(getApplicationContext(), Profile_activity.class);
            startActivity(intent3);
        });

        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout2);
        relativeLayout.setVisibility(View.INVISIBLE);

        binding.button16.setOnClickListener(view -> {
            relativeLayout.setVisibility(View.VISIBLE);

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
                    String ans1 = String.valueOf(dataSnapshot.child("Score").getValue());
                    String ans2 = String.valueOf(dataSnapshot.child("Score2").getValue());
//                    String score1 = (String) dataSnapshot.child("Score").getValue();
//                    assert score1 != null;
//                    int scr1 =  Integer.parseInt(score1);
//                    String score2 = (String) dataSnapshot.child("Score2").getValue();
//                    assert score2 != null;
//                    int scr2 =  Integer.parseInt(score2);
                    double anss1 = Double.parseDouble(ans1);
                    double anss2 = Double.parseDouble(ans2);

                    if(anss1==0){
                        binding.textView77.setText("You're perfectly not depressed.");
                    }
                    if(anss1>0 && anss1<=9){
                        binding.textView77.setText("You're feeling a little sad but not depressed.");
                    }
                    if(anss1>9 && anss1<=18){
                        binding.textView77.setText("You're feeling mildly depressed. If this continues you should seek professional help.");
                    }
                    if(anss1>18 && anss1<=27){
                        binding.textView77.setText("You're feeling really depressed. You should seek professional help.");
                    }

                    if(anss2==0){
                        binding.textView78.setText("You're perfectly not anxious.");
                    }
                    if(anss2>0 && anss2<=16){
                        binding.textView78.setText("You're feeling a little distressed but not anxious.");
                    }
                    if(anss2>16 && anss2<=32){
                        binding.textView78.setText("You're feeling mildly anxious. If this continues you should seek professional help.");
                    }
                    if(anss2>32 && anss2<=48){
                        binding.textView78.setText("You're feeling really anxious. You should seek professional help.");
                    }
                }
            });
        });
    }
}