package com.example.quiz;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignupPage extends AppCompatActivity {
    SQLiteDatabase db;
    EditText name,user,pwd;
    Button savebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        name = findViewById(R.id.PersonName);
        user= findViewById(R.id.Username);
        pwd = findViewById(R.id.Password);
        savebtn= findViewById(R.id.button);
        savebtn.setOnClickListener(v->{
            try {
                OnClick(v);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        db = openOrCreateDatabase("UserDB", Context.MODE_PRIVATE, null);
    }
    public void OnClick(View view) throws InterruptedException {
        if(name.getText().toString().trim().length()==0||
                user.getText().toString().trim().length()==0||
                pwd.getText().toString().trim().length()==0){
            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            db.execSQL("INSERT INTO user VALUES('"+user.getText()+"','"+pwd.getText()+"','"+name.getText()+"',0);");
            Toast.makeText(getApplicationContext(),"You are signed up",Toast.LENGTH_SHORT).show();
            Thread.sleep(1000);
            Intent intent = new Intent(SignupPage.this, MainActivity.class);
            startActivity(intent);
        }

    }
}