package com.example.mini_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.mini_project.databinding.ActivityRickRollBinding;

public class RickRoll_activity extends AppCompatActivity {
    ImageButton imgBtn;
    Button btnInput;
    ActivityRickRollBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRickRollBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        imgBtn = (ImageButton) findViewById(R.id.imageButton2);
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url ="https://www.youtube.com/watch?v=xvFZjo5PgG0";
                Uri uri = Uri.parse(url);
                Intent launchWeb = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(launchWeb);
            }
        });

        btnInput = findViewById(R.id.button17);
        btnInput.setOnClickListener(view -> {
            Intent intent3 = new Intent(getApplicationContext(), HomePage_activity.class);
            startActivity(intent3);
        });
    }
}