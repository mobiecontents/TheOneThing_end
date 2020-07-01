package com.example.mochon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private LinearLayout noItem, todoLinear;
    private TextView mainTitle, mainDes,main_ym,main_d;

    SharedPreferences sf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sf = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (sf.getStringSet("getTitle", null) == null){
            sf.edit().putStringSet("getTitle" ,new HashSet<String>()).apply();
            sf.edit().putStringSet("getDesc" ,new HashSet<String>()).apply();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        mainTitle = findViewById(R.id.mainTitle);
        mainDes = findViewById(R.id.mainDes);
        main_ym=findViewById(R.id.main_ym);
        main_d=findViewById(R.id.main_d);
        todoLinear = findViewById(R.id.todoLinear);
        noItem = findViewById(R.id.noItem);
        noItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        todoLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivityForResult(intent, 0);
            }
        });


        Date date=new Date();
        Calendar cal = Calendar.getInstance();
        System.out.println(cal);

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        main_ym.setText(year+"."+month);
        main_d.setText(""+day);

        setNoItem();
        setMainItem();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            if(requestCode == 0){
                setNoItem();
            }
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setMainItem();

    }

    public void setNoItem(){
        noItem = findViewById(R.id.noItem);
        todoLinear = findViewById(R.id.todoLinear);
        if (ListActivity.list.isEmpty()) {
            noItem.setVisibility(View.VISIBLE);
            todoLinear.setVisibility(View.INVISIBLE);
        } else {
            noItem.setVisibility(View.GONE);
            todoLinear.setVisibility(View.VISIBLE);
        }
    }
    public void setMainItem(){
        if(!ListActivity.list.isEmpty()){
            mainTitle.setText(ListActivity.list.get(0).getTitle());
            mainDes.setText(ListActivity.list.get(0).getDes());
            Log.d("MAIN_PRINT",ListActivity.list.get(0).getTitle());
            Log.d("MAIN_PRINT2", mainTitle.getText().toString());
            setNoItem();
        }
    }

}
