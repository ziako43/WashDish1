package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Settings extends AppCompatActivity {

    private SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

    }

    public void onClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClick1(View view) {
        TextView time = findViewById(R.id.editTimer);
        int timer = Integer.parseInt(time.getText().toString());
        if (timer > 1 && timer < 3600) {
            sPref = getSharedPreferences("WashDish", Context.MODE_PRIVATE);
            SharedPreferences.Editor ed = sPref.edit();
            ed.putInt("TIME", timer);
            ed.commit();
            Toast.makeText(this, "Время изменено", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Недопустимое значение времени", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClick2(View view) {
        sPref = getSharedPreferences("WashDish", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putInt("MONEY", 0);
        ed.putInt("SPONGE", 0);
        ed.putInt("TIME", 60);
        ed.commit();
    }

}