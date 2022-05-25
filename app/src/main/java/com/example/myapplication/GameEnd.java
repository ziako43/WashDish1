package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameEnd extends AppCompatActivity {
    MediaPlayer winSound;
    SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_end);
        ((TextView) findViewById(R.id.score)).setText(String.valueOf(Game.score));

        sPref = getSharedPreferences("WashDish",Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putInt("MONEY", Game.score);
        ed.commit();

        System.out.println("LolKek" +Game.score);



        winSound = MediaPlayer.create(this, R.raw.gamemusic);
        winSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                winSound.start();
            }
        });


    }

    public void onClick1(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}