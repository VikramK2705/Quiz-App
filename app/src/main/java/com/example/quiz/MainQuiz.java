package com.example.quiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainQuiz extends AppCompatActivity {
    SQLiteDatabase db;
    int counter;
    int points;
    String name;
    private RadioGroup r1,r2,r3,r4,r5;
    Button button;
    TextView cl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_quiz);
        cl=findViewById(R.id.textv);
         button = findViewById(R.id.button2);
        r1= findViewById(R.id.radioGroup);
        r2= findViewById(R.id.radioGroup1);
        r3= findViewById(R.id.radioGroup2);
        r4= findViewById(R.id.radioGroup3);
        r5= findViewById(R.id.radioGroup4);
        countdown();
        r1.setOnCheckedChangeListener((group, checkedId) -> {
            if(checkedId==R.id.questionOneA){
                points+=5;
            }
        });
        r2.setOnCheckedChangeListener((group, checkedId) -> {
            if(checkedId==R.id.questionTwoB){
                points+=5;
            }
        });
        r3.setOnCheckedChangeListener((group, checkedId) -> {
            if(checkedId==R.id.questionThreeB){
                points+=5;
            }
        });
        r4.setOnCheckedChangeListener((group, checkedId) -> {
            if(checkedId==R.id.questionFourB){
                points+=5;
            }
        });
        r5.setOnCheckedChangeListener((group, checkedId) -> {
            if(checkedId==R.id.questionFiveC){
                points+=5;
            }
        });
        button.setOnClickListener(v->{
            onBClick(v);
        });


    }

    public void countdown(){
        new CountDownTimer(21000, 1000){
            public void onTick(long millisUntilFinished){
                cl.setText("Time Remaining: "+(20-counter));
                counter++;
            }
            public  void onFinish(){
//                Intent intent=new Intent(astronomy.this,PlayerScore.class);
//                intent.putExtra("Score",10);
//                startActivity(intent);
                  submit();
            }
        }.start();

    }
        public void onBClick(View v){
            Bundle bundle=getIntent().getExtras();
            String user = bundle.getString("User");
            db = openOrCreateDatabase("UserDB", Context.MODE_PRIVATE, null);

            {
                Cursor c = db.rawQuery("SELECT score,name from USER WHERE username= '" + user + "';", null);
                if (c.moveToFirst()) {
                    if (points > Integer.valueOf(c.getString(0))) {
                        db.execSQL("UPDATE USER SET score='" + points + "' WHERE USERNAME='" + user + "';");
                    }
                    name = c.getString(1);
                }
            }
            // Create the object of AlertDialog Builder class
            AlertDialog.Builder builder = new AlertDialog.Builder(MainQuiz.this);

            builder.setMessage("Well played " + name + "\nYour Score is " + points);

            // Set Alert Title
            builder.setTitle("Quiz Finished");


            builder.setCancelable(false);

            // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
            builder.setPositiveButton("OK", (dialog, which) -> {
                // When the user click yes button then app will close
                finish();
            });

            // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
//        builder.setNegativeButton("No", (dialog, which) -> {
//            // If user click no then dialog box is canceled.
//            dialog.cancel();
//        });

            // Create the Alert dialog
            AlertDialog alertDialog = builder.create();
            // Show the Alert Dialog box
            alertDialog.show();


//            intent.putExtra("Name",c.getString(1));
//            startActivity(intent);
        }

    public void submit() {

        Bundle bundle=getIntent().getExtras();
        String user = bundle.getString("User");
        db = openOrCreateDatabase("UserDB", Context.MODE_PRIVATE, null);

        {
            Cursor c = db.rawQuery("SELECT score,name from USER WHERE username= '" + user + "';", null);
            if (c.moveToFirst()) {
                if (points > Integer.valueOf(c.getString(0))) {
                    db.execSQL("UPDATE USER SET score='" + points + "' WHERE USERNAME='" + user + "';");
                }
                name = c.getString(1);
            }
        }
        // Create the object of AlertDialog Builder class
        AlertDialog.Builder builder = new AlertDialog.Builder(MainQuiz.this);

        builder.setMessage("Well played " + user + "\nYour Score is " + points);

        // Set Alert Title
        builder.setTitle("Quiz Finished");


        builder.setCancelable(false);

        // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setPositiveButton("OK", (dialog, which) -> {
            // When the user click yes button then app will close
            finish();
        });

        // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
//        builder.setNegativeButton("No", (dialog, which) -> {
//            // If user click no then dialog box is canceled.
//            dialog.cancel();
//        });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();
        // Show the Alert Dialog box
        alertDialog.show();

    }
}