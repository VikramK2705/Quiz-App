package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ScorePage extends AppCompatActivity {
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_page);
        db = openOrCreateDatabase("UserDB", Context.MODE_PRIVATE, null);
        ArrayList<HashMap<String, String>> userList = GetUsers();
        ListView lv = findViewById(R.id.user_list);
        ListAdapter adapter = new SimpleAdapter(ScorePage.this, userList, R.layout.list_row,new String[]{"name","score"}, new int[]{R.id.name, R.id.score});
        lv.setAdapter(adapter);
        Button back = findViewById(R.id.btnBack);
        back.setOnClickListener(v -> {
           Intent intent = new Intent(ScorePage.this,MainActivity.class);
            startActivity(intent);
        });

    }
    public ArrayList<HashMap<String, String>> GetUsers(){

        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT name, score FROM USER";
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("name",cursor.getString(0));
            user.put("score",cursor.getString(1));
            userList.add(user);
        }
        return  userList;
    }
}