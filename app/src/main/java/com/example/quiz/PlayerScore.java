package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PlayerScore extends AppCompatActivity {
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_score);
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null) {
            int points = bundle.getInt("Score");
            String name=bundle.getString("Name");
            TextView textView= findViewById(R.id.scoredisplay);
            TextView tv=findViewById(R.id.textView7);
            tv.setText("Well Played "+name+" !!\n Your Score for this round is:");
            textView.setText(String.valueOf(points));

        }
        back= findViewById(R.id.button3);
        back.setOnClickListener(v -> {
           Intent intent=new Intent(PlayerScore.this,MainActivity.class);
           startActivity(intent);
        });
    }
}