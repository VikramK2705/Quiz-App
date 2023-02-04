package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    Button sltbtn;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView=findViewById(R.id.textView13);
        Bundle bundle=getIntent().getExtras();
        sltbtn=findViewById(R.id.button5);
        String username=bundle.getString("User");
        textView.setText("Hello "+username+"!!\n Please select any one quiz from below");
        sltbtn.setOnClickListener(v -> {
                    PopupMenu popupMenu = new PopupMenu(MainActivity2.this, sltbtn);
    popupMenu.getMenuInflater().inflate(R.menu.quiztype, popupMenu.getMenu());
    popupMenu.setOnMenuItemClickListener(menuItem -> {
        if (menuItem.getItemId() == R.id.item1) {
            Intent i = new Intent(MainActivity2.this, astronomy.class);
            i.putExtra("User",username);
            startActivity(i);
        } else if (menuItem.getItemId() == R.id.item2) {
            Intent i = new Intent(MainActivity2.this, general2.class);
            i.putExtra("User",username);
            startActivity(i);
        }
        else if(menuItem.getItemId() == R.id.item3){
            Intent i = new Intent(MainActivity2.this, MainQuiz.class);
            i.putExtra("User",username);
            startActivity(i);
        }
        return false;
    });
    popupMenu.show();
        });

    }
}