
package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sPref;
    MediaPlayer menuSound, gameSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sPref = getSharedPreferences("WashDish", Context.MODE_PRIVATE);
        Game.score = sPref.getInt("MONEY", 0);

        menuSound = MediaPlayer.create(this, R.raw.menusound);
        menuSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                menuSound.start();
            }

        });
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, Game.class);
        startActivity(intent);

        gameSound = MediaPlayer.create(this, R.raw.gamemusic);
        gameSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                gameSound.start();
            }
        });

    }

    public void onClick2(View view) {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    public void onClick3(View view) {
        Intent intent = new Intent(this, Magazin.class);
        startActivity(intent);
    }
}