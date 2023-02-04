package com.example.quiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.CountDownTimer;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class astronomy extends AppCompatActivity {
    SQLiteDatabase db;
    int points;
    public int counter;
    private RadioGroup r1,r2,r3,r4,r5,r6,r7,r8,r9,r10;
    TextView cl;
    Button button;
    String name;
//    TextView Score = findViewById(R.id.score);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_astronomy);
        countdown();
        button = findViewById(R.id.button2);
        cl=findViewById(R.id.textv);
        r1= findViewById(R.id.radioGroup);
        r2= findViewById(R.id.radioGroup1);
        r3= findViewById(R.id.radioGroup2);
        r4= findViewById(R.id.radioGroup3);
        r5= findViewById(R.id.radioGroup4);
        r6=findViewById(R.id.radioGroup5);
        r7=  findViewById(R.id.radioGroup6);
        r8= findViewById(R.id.radioGroup7);
        r9=findViewById(R.id.radioGroup8);
        r10=findViewById(R.id.radioGroup9);
        r1.setOnCheckedChangeListener((group, checkedId) -> {
            if(checkedId==R.id.questionOneD){
                points+=5;
            }
        });
        r2.setOnCheckedChangeListener((group, checkedId) -> {
            if(checkedId==R.id.questionTwoA){
                points+=5;
            }
        });
        r3.setOnCheckedChangeListener((group, checkedId) -> {
            if(checkedId==R.id.questionThreeD){
                points+=5;
            }
        });
        r4.setOnCheckedChangeListener((group, checkedId) -> {
            if(checkedId==R.id.questionFourA){
                points+=5;
            }
        });
        r5.setOnCheckedChangeListener((group, checkedId) -> {
            if(checkedId==R.id.questionFiveD){
                points+=5;
            }
        });
        r6.setOnCheckedChangeListener((group, checkedId) -> {
            if(checkedId==R.id.questionSixD){
                points+=5;
            }
        });
        r7.setOnCheckedChangeListener((group, checkedId) -> {
            if(checkedId==R.id.questionSevenA){
                points+=5;
            }
        });
        r8.setOnCheckedChangeListener((group, checkedId) -> {
            if(checkedId==R.id.questionEightA){
                points+=5;
            }
        });
        r9.setOnCheckedChangeListener((group, checkedId) -> {
            if(checkedId==R.id.questionNineC){
                points+=5;
            }
        });
        r10.setOnCheckedChangeListener((group, checkedId) -> {
            if(checkedId==R.id.questionTenC){
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
        AlertDialog.Builder builder = new AlertDialog.Builder(astronomy.this);

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
        AlertDialog.Builder builder = new AlertDialog.Builder(astronomy.this);

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

    }
}
