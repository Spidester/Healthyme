package com.example.mini_project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mini_project.databinding.ActivityPhysical2Binding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class Physical2_activity extends AppCompatActivity {

    DatabaseReference reference;
    RelativeLayout relativeLayout;
    ActivityPhysical2Binding binding;
    String day;
    Button btnInput;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPhysical2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        btnInput = findViewById(R.id.button3);
        btnInput.setOnClickListener(view -> {
            Intent intent3 = new Intent(getApplicationContext(), Profile_activity.class);
            startActivity(intent3);
        });

        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        relativeLayout.setVisibility(View.INVISIBLE);

        Date enter_date = Calendar.getInstance().getTime();
        String date = enter_date.toString();
        char[] ch = new char[date.length()];
        for (int j = 0; j < 3; j++) {
            ch[j] = date.charAt(j);}
        if(ch[0]=='M'){
            day ="Monday";
        }if(ch[0]=='W'){
            day ="Wednesday";
        }if(ch[0]=='F'){
            day ="Friday";
        }if(ch[0]=='T'){
            if(ch[1]=='u'){
                day ="Tuesday";}
            else{
                day ="Thursday";}
        }if(ch[0]=='S'){
            if(ch[1]=='a'){
                day ="Saturday";}
            else{
                day ="Sunday";}
        }

        binding.button13.setOnClickListener(view -> {
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
                    Double tdee = (Double) dataSnapshot.child("Tdee").getValue();
                    Double bmr = (Double) dataSnapshot.child("BMR").getValue();
                    String weight = String.valueOf(dataSnapshot.child("Weight").getValue());
                    double weight2 = Double.parseDouble(weight);
                    double caloriesPerDay = bmr * 1.2;
                    double proteinGramsPerDay = weight2 * 0.36;
                    double carbohydratesGramsPerDay = caloriesPerDay * 0.45 / 4;
                    double fatsGramsPerDay = caloriesPerDay * 0.25 / 9;

                    if(tdee>2500){
                        Double dailycaloricintake = tdee - 500;
                        if(Objects.equals(day, "Monday")) {
                            binding.textView38.setText("1 small bowl of vegetable poha with a side of fresh orange juice");
                            binding.textView40.setText("1 small bowl of vegetable biryani made with basmati rice, with a side of cucumber raita and mixed vegetable salad");
                            binding.textView42.setText("1 small bowl of roasted chana");
                            binding.textView45.setText("1 small bowl of mixed vegetable curry with 1 small whole wheat naan and a small bowl of plain yogurt");
                            binding.textView47.setText("You should consume approximately "+caloriesPerDay+" calories per day. This includes "+proteinGramsPerDay+" grams of protein, "+carbohydratesGramsPerDay+" grams of carbohydrates and "+fatsGramsPerDay+" grams of fat.");
                        }else if(Objects.equals(day, "Tuesday")) {
                            binding.textView38.setText("1 small bowl of vegetable upma with a side of fresh pineapple juice");
                            binding.textView40.setText("1 small bowl of paneer butter masala with 1 small whole wheat roti and mixed vegetable salad");
                            binding.textView42.setText("1 small bowl of mixed fruit salad with a sprinkle of chaat masala");
                            binding.textView45.setText("1 small bowl of vegetable korma with 1 small bowl of brown rice and a small bowl of plain yogurt");
                            binding.textView47.setText("You should consume approximately "+caloriesPerDay+" calories per day. This includes "+proteinGramsPerDay+" grams of protein, "+carbohydratesGramsPerDay+" grams of carbohydrates and "+fatsGramsPerDay+" grams of fat.");
                        }else if(Objects.equals(day, "Wednesday")) {
                            binding.textView38.setText("1 small bowl of mixed vegetable dosa with coconut chutney and a side of fresh mango juice");
                            binding.textView40.setText("1 small bowl of mixed vegetable sambar with 1 small bowl of brown rice and cucumber salad");
                            binding.textView42.setText("1 small bowl of mixed fruit salad with a sprinkle of chaat masala");
                            binding.textView45.setText("1 small bowl of mushroom and green pea curry with 1 small whole wheat naan and a small bowl of plain yogurt");
                            binding.textView47.setText("You should consume approximately "+caloriesPerDay+" calories per day. This includes "+proteinGramsPerDay+" grams of protein, "+carbohydratesGramsPerDay+" grams of carbohydrates and "+fatsGramsPerDay+" grams of fat.");
                        }else if(Objects.equals(day, "Thursday")) {
                            binding.textView38.setText("1 small bowl of vegetable dalia cooked with water and topped with peanuts and fresh coriander, with a side of fresh orange juice");
                            binding.textView40.setText("1 small bowl of chana masala with 1 small bowl of brown rice and mixed vegetable salad");
                            binding.textView42.setText("1 small bowl of roasted makhana");
                            binding.textView45.setText("1 small bowl of paneer tikka masala with 1 small whole wheat roti and a small bowl of plain yogurt");
                            binding.textView47.setText("You should consume approximately "+caloriesPerDay+" calories per day. This includes "+proteinGramsPerDay+" grams of protein, "+carbohydratesGramsPerDay+" grams of carbohydrates and "+fatsGramsPerDay+" grams of fat.");
                        }else if(Objects.equals(day, "Friday")) {
                            binding.textView38.setText("1 small bowl of vegetable uttapam with coconut chutney and a side of fresh pineapple juice");
                            binding.textView40.setText("1 small bowl of vegetable kadhi with 1 small bowl of brown rice and cucumber salad");
                            binding.textView42.setText("1 small bowl of mixed fruit salad with a sprinkle of chaat masala");
                            binding.textView45.setText("1 small bowl of mixed vegetable curry with 1 small whole wheat naan and a small bowl of plain yogurt");
                            binding.textView47.setText("You should consume approximately "+caloriesPerDay+" calories per day. This includes "+proteinGramsPerDay+" grams of protein, "+carbohydratesGramsPerDay+" grams of carbohydrates and "+fatsGramsPerDay+" grams of fat.");
                        }else if(Objects.equals(day, "Saturday")) {
                            binding.textView38.setText("1 small bowl of mixed vegetable idli sambar with coconut chutney and a side of fresh watermelon juice");
                            binding.textView40.setText("1 small bowl of vegetable pulao made with basmati rice, with a side of cucumber raita and mixed vegetable salad");
                            binding.textView42.setText("1 small bowl of mixed fruit salad with a sprinkle of chaat masala");
                            binding.textView45.setText("1 small bowl of mushroom and green pea curry with 1 small whole wheat roti and a small bowl of plain yogurt");
                            binding.textView47.setText("You should consume approximately "+caloriesPerDay+" calories per day. This includes "+proteinGramsPerDay+" grams of protein, "+carbohydratesGramsPerDay+" grams of carbohydrates and "+fatsGramsPerDay+" grams of fat.");
                        }else if(Objects.equals(day, "Sunday")) {
                            binding.textView38.setText("1 small bowl of vegetable oats cooked with water and topped with peanuts and fresh coriander, with a side of fresh orange juice");
                            binding.textView40.setText("1 small bowl of rajma masala with 1 small bowl of brown rice and mixed vegetable salad");
                            binding.textView42.setText("1 small bowl of roasted chana");
                            binding.textView45.setText("1 small bowl of palak paneer with 1 small whole wheat naan and a small bowl of plain yogurt");
                            binding.textView47.setText("You should consume approximately "+caloriesPerDay+" calories per day. This includes "+proteinGramsPerDay+" grams of protein, "+carbohydratesGramsPerDay+" grams of carbohydrates and "+fatsGramsPerDay+" grams of fat.");
                        }
                    }
                    if(tdee<1500){
                        Double dailycaloricintake = tdee+500;
                        if(Objects.equals(day, "Monday")) {
                            binding.textView38.setText("1 small bowl of oats cooked with water and topped with almonds and raisins");
                            binding.textView40.setText("1 small bowl of mixed vegetable dal with 1 small whole wheat roti and cucumber salad");
                            binding.textView42.setText("1 cup of cucumber and carrot sticks with mint yogurt dip");
                            binding.textView45.setText("1 small bowl of vegetable khichdi made with brown rice and moong dal, with a side of roasted papad and a small bowl of plain yogurt");
                            binding.textView47.setText("You should consume approximately "+caloriesPerDay+" calories per day. This includes "+proteinGramsPerDay+" grams of protein, "+carbohydratesGramsPerDay+" grams of carbohydrates and "+fatsGramsPerDay+" grams of fat.");
                        }else if(Objects.equals(day, "Tuesday")) {
                            binding.textView38.setText("1 small bowl of vegetable poha with a side of fresh orange slices");
                            binding.textView40.setText("1 small bowl of rajma curry with 1 small whole wheat roti and mixed vegetable salad");
                            binding.textView42.setText("1 cup of mixed fruit salad with a sprinkle of chaat masala");
                            binding.textView45.setText("1 small bowl of mixed vegetable curry with 1 small whole wheat roti and a small bowl of plain yogurt");
                            binding.textView47.setText("You should consume approximately "+caloriesPerDay+" calories per day. This includes "+proteinGramsPerDay+" grams of protein, "+carbohydratesGramsPerDay+" grams of carbohydrates and "+fatsGramsPerDay+" grams of fat.");
                        }else if(Objects.equals(day, "Wednesday")) {
                            binding.textView38.setText("1 small bowl of masala oats cooked with vegetables and spices, with a side of fresh grapes");
                            binding.textView40.setText("1 small bowl of moong dal khichdi with a side of roasted papad and mixed vegetable salad");
                            binding.textView42.setText("1 small bowl of roasted chana");
                            binding.textView45.setText("1 small bowl of vegetable pulao made with brown rice, with a side of cucumber raita and a small bowl of plain yogurt");
                            binding.textView47.setText("You should consume approximately "+caloriesPerDay+" calories per day. This includes "+proteinGramsPerDay+" grams of protein, "+carbohydratesGramsPerDay+" grams of carbohydrates and "+fatsGramsPerDay+" grams of fat.");
                        }else if(Objects.equals(day, "Thursday")) {
                            binding.textView38.setText("1 small bowl of vegetable upma with a side of fresh papaya");
                            binding.textView40.setText("1 small bowl of mixed vegetable sambar with 1 small whole wheat dosa and coconut chutney");
                            binding.textView42.setText("1 small bowl of sprouts salad with lemon and chaat masala");
                            binding.textView45.setText("1 small bowl of mushroom and green pea curry with 1 small whole wheat roti and a small bowl of plain yogurt");
                            binding.textView47.setText("You should consume approximately "+caloriesPerDay+" calories per day. This includes "+proteinGramsPerDay+" grams of protein, "+carbohydratesGramsPerDay+" grams of carbohydrates and "+fatsGramsPerDay+" grams of fat.");
                        }else if(Objects.equals(day, "Friday")) {
                            binding.textView38.setText("1 small bowl of vegetable dalia cooked with water and topped with peanuts and fresh coriander, with a side of fresh orange slices");
                            binding.textView40.setText("1 small bowl of vegetable kadhi with 1 small whole wheat roti and cucumber salad");
                            binding.textView42.setText("1 small bowl of mixed fruit salad with a sprinkle of chaat masala");
                            binding.textView45.setText("1 small bowl of vegetable biryani made with brown rice, with a side of cucumber raita and a small bowl of plain yogurt");
                            binding.textView47.setText("You should consume approximately "+caloriesPerDay+" calories per day. This includes "+proteinGramsPerDay+" grams of protein, "+carbohydratesGramsPerDay+" grams of carbohydrates and "+fatsGramsPerDay+" grams of fat.");
                        }else if(Objects.equals(day, "Saturday")) {
                            binding.textView38.setText("1 small bowl of mixed vegetable idli sambar with coconut chutney and a side of fresh papaya");
                            binding.textView40.setText("1 small bowl of chana masala with 1 small whole wheat roti and mixed vegetable salad");
                            binding.textView42.setText("1 small bowl of roasted makhana");
                            binding.textView45.setText("1 small bowl of mixed vegetable curry with 1 small whole wheat roti and a small bowl of plain yogurt");
                            binding.textView47.setText("You should consume approximately "+caloriesPerDay+" calories per day. This includes "+proteinGramsPerDay+" grams of protein, "+carbohydratesGramsPerDay+" grams of carbohydrates and "+fatsGramsPerDay+" grams of fat.");
                        }else if(Objects.equals(day, "Sunday")) {
                            binding.textView38.setText("1 small bowl of vegetable uttapam with coconut chutney and a side of fresh grapes");
                            binding.textView40.setText("1 small bowl of vegetable pulao made with broken wheat, with a side of cucumber raita and mixed vegetable salad");
                            binding.textView42.setText("1 small bowl of roasted chana");
                            binding.textView45.setText("1 small bowl of roasted chana");
                            binding.textView47.setText("You should consume approximately "+caloriesPerDay+" calories per day. This includes "+proteinGramsPerDay+" grams of protein, "+carbohydratesGramsPerDay+" grams of carbohydrates and "+fatsGramsPerDay+" grams of fat.");
                        }
                    }
                    if(tdee>1500 && tdee<2500){
                        if(Objects.equals(day, "Monday")) {
                            binding.textView38.setText("1 small bowl of oats cooked with water and topped with almonds and raisins");
                            binding.textView40.setText("1 small bowl of mixed vegetable dal with 1 small whole wheat roti and cucumber salad");
                            binding.textView42.setText("1 cup of cucumber and carrot sticks with mint yogurt dip");
                            binding.textView45.setText("1 small bowl of vegetable khichdi made with brown rice and moong dal, with a side of roasted papad and a small bowl of plain yogurt");
                            binding.textView47.setText("You should consume approximately "+caloriesPerDay+" calories per day. This includes "+proteinGramsPerDay+" grams of protein, "+carbohydratesGramsPerDay+" grams of carbohydrates and "+fatsGramsPerDay+" grams of fat.");
                        }else if(Objects.equals(day, "Tuesday")) {
                            binding.textView38.setText("1 small bowl of vegetable upma with a side of fresh pineapple juice");
                            binding.textView40.setText("1 small bowl of paneer butter masala with 1 small whole wheat roti and mixed vegetable salad");
                            binding.textView42.setText("1 small bowl of mixed fruit salad with a sprinkle of chaat masala");
                            binding.textView45.setText("1 small bowl of vegetable korma with 1 small bowl of brown rice and a small bowl of plain yogurt");
                            binding.textView47.setText("You should consume approximately "+caloriesPerDay+" calories per day. This includes "+proteinGramsPerDay+" grams of protein, "+carbohydratesGramsPerDay+" grams of carbohydrates and "+fatsGramsPerDay+" grams of fat.");
                        }else if(Objects.equals(day, "Wednesday")) {
                            binding.textView38.setText("1 small bowl of masala oats cooked with vegetables and spices, with a side of fresh grapes");
                            binding.textView40.setText("1 small bowl of moong dal khichdi with a side of roasted papad and mixed vegetable salad");
                            binding.textView42.setText("1 small bowl of roasted chana");
                            binding.textView45.setText("1 small bowl of vegetable pulao made with brown rice, with a side of cucumber raita and a small bowl of plain yogurt");
                            binding.textView47.setText("You should consume approximately "+caloriesPerDay+" calories per day. This includes "+proteinGramsPerDay+" grams of protein, "+carbohydratesGramsPerDay+" grams of carbohydrates and "+fatsGramsPerDay+" grams of fat.");
                        }else if(Objects.equals(day, "Thursday")) {
                            binding.textView38.setText("1 small bowl of vegetable dalia cooked with water and topped with peanuts and fresh coriander, with a side of fresh orange juice");
                            binding.textView40.setText("1 small bowl of chana masala with 1 small bowl of brown rice and mixed vegetable salad");
                            binding.textView42.setText("1 small bowl of roasted makhana");
                            binding.textView45.setText("1 small bowl of paneer tikka masala with 1 small whole wheat roti and a small bowl of plain yogurt");
                            binding.textView47.setText("You should consume approximately "+caloriesPerDay+" calories per day. This includes "+proteinGramsPerDay+" grams of protein, "+carbohydratesGramsPerDay+" grams of carbohydrates and "+fatsGramsPerDay+" grams of fat.");
                        }else if(Objects.equals(day, "Friday")) {
                            binding.textView38.setText("1 small bowl of vegetable dalia cooked with water and topped with peanuts and fresh coriander, with a side of fresh orange slices");
                            binding.textView40.setText("1 small bowl of vegetable kadhi with 1 small whole wheat roti and cucumber salad");
                            binding.textView42.setText("1 small bowl of mixed fruit salad with a sprinkle of chaat masala");
                            binding.textView45.setText("1 small bowl of vegetable biryani made with brown rice, with a side of cucumber raita and a small bowl of plain yogurt");
                            binding.textView47.setText("You should consume approximately "+caloriesPerDay+" calories per day. This includes "+proteinGramsPerDay+" grams of protein, "+carbohydratesGramsPerDay+" grams of carbohydrates and "+fatsGramsPerDay+" grams of fat.");
                        }else if(Objects.equals(day, "Saturday")) {
                            binding.textView38.setText("1 small bowl of mixed vegetable idli sambar with coconut chutney and a side of fresh watermelon juice");
                            binding.textView40.setText("1 small bowl of vegetable pulao made with basmati rice, with a side of cucumber raita and mixed vegetable salad");
                            binding.textView42.setText("1 small bowl of mixed fruit salad with a sprinkle of chaat masala");
                            binding.textView45.setText("1 small bowl of mushroom and green pea curry with 1 small whole wheat roti and a small bowl of plain yogurt");
                            binding.textView47.setText("You should consume approximately "+caloriesPerDay+" calories per day. This includes "+proteinGramsPerDay+" grams of protein, "+carbohydratesGramsPerDay+" grams of carbohydrates and "+fatsGramsPerDay+" grams of fat.");
                        }else if(Objects.equals(day, "Sunday")) {
                            binding.textView38.setText("1 small bowl of vegetable uttapam with coconut chutney and a side of fresh grapes");
                            binding.textView40.setText("1 small bowl of vegetable pulao made with broken wheat, with a side of cucumber raita and mixed vegetable salad");
                            binding.textView42.setText("1 small bowl of roasted chana");
                            binding.textView45.setText("1 small bowl of roasted chana");
                            binding.textView47.setText("You should consume approximately "+caloriesPerDay+" calories per day. This includes "+proteinGramsPerDay+" grams of protein, "+carbohydratesGramsPerDay+" grams of carbohydrates and "+fatsGramsPerDay+" grams of fat.");
                        }
                    }
                }
            });
        });
    }
}