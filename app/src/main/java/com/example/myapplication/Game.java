package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Game extends AppCompatActivity {
    private FrameLayout container;
    private int xDelta, yDelta, currentSponge;
    private ImageView spn, dirt;
    static protected int score = 0;
    private SharedPreferences sPref;
    private int spongeForce;
    private TextView scoreView;
    private ArrayList<Integer> spongesInts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        dirt = (ImageView) findViewById(R.id.plateDirt);
        container = findViewById(R.id.container);
        spn = (ImageView) findViewById(R.id.imageView);
        TextView txtSecond = (TextView) findViewById(R.id.txtSecond);
        scoreView = findViewById(R.id.now_score);
        spongesInts.add(R.drawable.sponge);
        spongesInts.add(R.drawable.spongev2);
        spongesInts.add(R.drawable.spongev3);
        spongesInts.add(R.drawable.spongev4);
        spongesInts.add(R.drawable.spongev5);
        spn.setOnTouchListener(touchListener);

        sPref = getSharedPreferences("WashDish", Context.MODE_PRIVATE);
        currentSponge = sPref.getInt("SPONGE", 0);
        int time = sPref.getInt("TIME", 60);
        spn.setImageResource(spongesInts.get(currentSponge));
        spongeForce = (int) (4000/Math.pow(2,currentSponge));
        CountDownTimer my_timer = new CountDownTimer(time*1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                txtSecond.setText(Long.toString(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {

                Intent intent = new Intent(Game.this, GameEnd.class);
                startActivity(intent);
            }
        };
        scoreView.setText("Деньги: " + score);
        my_timer.start();
    }



    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent event) {


            final int x = (int) event.getRawX();
            final int y = (int) event.getRawY();

            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN: {
                    FrameLayout.LayoutParams lParams = (FrameLayout.LayoutParams) view.getLayoutParams();

                    xDelta = x - lParams.leftMargin;
                    yDelta = y - lParams.topMargin;
                    break;
                }
                case MotionEvent.ACTION_MOVE: {


                    if (x - xDelta + view.getWidth() <= container.getWidth()

                            && y - yDelta + view.getHeight() <= container.getHeight()
                            && x - xDelta >= 0
                            && y - yDelta >= 0) {

                        VelocityTracker velocity = VelocityTracker.obtain();
                        velocity.addMovement(event);

                        FrameLayout.LayoutParams layoutParams =
                                (FrameLayout.LayoutParams) view.getLayoutParams();

                        FrameLayout.LayoutParams DirtParams =
                                (FrameLayout.LayoutParams) dirt.getLayoutParams();

                        layoutParams.leftMargin = x - xDelta;
                        layoutParams.topMargin = y - yDelta;

                        Rect spongeRect = new Rect(layoutParams.leftMargin, layoutParams.topMargin, layoutParams.rightMargin + spn.getWidth(), layoutParams.bottomMargin + spn.getHeight());
                        Rect plateRect = new Rect(DirtParams.leftMargin, DirtParams.topMargin, DirtParams.leftMargin + dirt.getWidth(), DirtParams.bottomMargin + dirt.getHeight());
                        Log.d("Plate", plateRect.toString());
                        if (plateRect.intersect(spongeRect)) {
                            velocity.computeCurrentVelocity(1000);
                            dirt.setImageAlpha((int) (dirt.getImageAlpha() - Math.abs(velocity.getXVelocity() / spongeForce)));
                            Log.d("Velocity", String.valueOf(velocity.getXVelocity()));
                            if (dirt.getImageAlpha() - Math.abs(velocity.getXVelocity() / spongeForce) <= 0) {
                                score++;
                                System.out.println("LolKek" + score);
                                scoreView.setText("Деньги: " + score);
                                dirt.setImageAlpha(255);


                            }
                        }

                        layoutParams.rightMargin = 0;
                        layoutParams.bottomMargin = 0;
                        view.setLayoutParams(layoutParams);

                    }

                    break;
                }
            }
            container.invalidate();
            return true;
        }
    };

}


