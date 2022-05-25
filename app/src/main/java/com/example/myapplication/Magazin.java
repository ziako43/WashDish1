package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class Magazin extends AppCompatActivity implements SpongeListAdapter.onItemClickListner {

    static protected TextView money;

SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magazin);
        SpongeListAdapter adapter = new SpongeListAdapter(this, makeSponges());
        adapter.setOnItemClickListner(this);
        ListView lv = (ListView) findViewById(R.id.spongeList);
        lv.setAdapter(adapter);
        TextView money = findViewById(R.id.money);
        money.setText("Деньги: " + String.valueOf(Game.score));
    }

    private Sponge[] makeSponges() {
        Sponge[] arr = new Sponge[4];
        arr[0] = new Sponge("BubkaGob", 100);
        arr[1] = new Sponge("Brick", 200);
        arr[2] = new Sponge("Coal", 400);
        arr[3] = new Sponge("Minecraft", 1000);

        return arr;
    }

    public void onClick(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    @Override
    public void Clicuha(int money, int pos) {
        TextView money2 = findViewById(R.id.money);
        money2.setText("Деньги: " + String.valueOf(Game.score));

        sPref = getSharedPreferences("WashDish",Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putInt("MONEY", money);
        ed.putInt("SPONGE", pos+1);
        ed.commit();
    }
}