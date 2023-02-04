package com.example.quiz;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MainActivity extends AppCompatActivity {
    EditText uname, pwd;
    Button SignInBtn, SignUpBtn,ScoreBtn;
    SQLiteDatabase db;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uname = findViewById(R.id.txtName);
        pwd = findViewById(R.id.txtPwd);
        SignInBtn = findViewById(R.id.btnsgnin);
        SignUpBtn = findViewById(R.id.btnsgnup);
        ScoreBtn=findViewById(R.id.button4);
        db = openOrCreateDatabase("UserDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS user(username VARCHAR,password VARCHAR,name VARCHAR,score NUMBER DEFAULT 0);");
        SignInBtn.setOnClickListener(v->{
            try {
                OnSignInClick(v);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        SignUpBtn.setOnClickListener(v->{
            OnSignUpClick(v);
        });
        ScoreBtn.setOnClickListener(v->{
            ViewBoard(v);
        });


    }

    public void OnSignInClick(View view) throws InterruptedException {
        if(view.getId()==SignInBtn.getId()){
            if(uname.getText().toString().trim().length()==0 || pwd.getText().toString().trim().length()==0){
                Toast.makeText(getApplicationContext(),"Please Enter your Details",Toast.LENGTH_SHORT).show();

            }
            else{
                Cursor c=db.rawQuery("SELECT username,password FROM USER WHERE username='"+uname.getText()+"'AND password='"+pwd.getText()+"' ",null);
                if(c.getCount()>=1){
                    Toast.makeText(getApplicationContext(),"You are logged in",Toast.LENGTH_SHORT).show();
                    if(c.moveToFirst()) {
                    Thread.sleep(1000);
                    String user=c.getString(0);
                    intent = new Intent(MainActivity.this, MainActivity2.class);
                    intent.putExtra("User",user);
                    c.close();
                        startActivity(intent);
                    }

                    }

                else{
                    Toast.makeText(getApplicationContext(),"Wrong Info ☹",Toast.LENGTH_SHORT).show();
                }
            }

        }
    }
    public void OnSignUpClick(View view){
        intent=new Intent(MainActivity.this,SignupPage.class);
        startActivity(intent);
    }
    public void ViewBoard(View view){
        intent =new Intent(MainActivity.this,ScorePage.class);
        startActivity(intent);
    }
}