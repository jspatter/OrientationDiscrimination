package com.example.jackie.orientationdiscrimination;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**
 * Created by Jackie on 5/24/2016.
 */
public class Experiment extends AppCompatActivity {

    private RelativeLayout experimentLayout;
    private boolean isInitialized;
    private boolean clickLeft;
    private boolean clickRight;
    private boolean done;
    private ArrayList<Staircase> staircases;
    private int screenHeight;
    private int screenWidth;
    private Handler myHandler;
    private ImageView gratingImageView;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_experiment);

        // Set flag to turn off touch
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        View decorView = getWindow().getDecorView();

        // Hide status and navigation bars
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Work on RelativeLayout, determine whether we've done this before because onCreate can be
        // run multiple times

        // might move this to its own method
        experimentLayout = (RelativeLayout) findViewById(R.id.experiment_layout);

        isInitialized = false;
    }

    private void initGrating() {
        gratingImageView = (ImageView) findViewById(R.id.grating);
        String uri = "@drawable/grating_cropped";  // where myresource (without the extension) is the file

        int imageResource = getResources().getIdentifier(uri, null, getPackageName());

        Drawable res = getResources().getDrawable(imageResource);
        gratingImageView.setImageDrawable(res);
        gratingImageView.setVisibility(View.GONE);
    }

    // Shows what has been clicked on the screen
    private void clickRight() {
        if (!clickRight && !clickLeft) {
            clickRight = true;
            clickLeft = false;
        }
    }

    private void clickLeft() {
        if (!clickRight && !clickLeft) {
            clickLeft = true;
            clickRight = false;
        }
    }

    private void updateStaircase(boolean isCorrect) {
        if (isCorrect) {

        } else {

        }
    }

    private void update() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!done) {
                    int timeElapsed = 0;
                    try {
                        timeElapsed = timeElapsed + AppConstants.ISI_TIME;
                        Thread.sleep(AppConstants.EXP_THREAD);
                        myHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                gratingImageView.setVisibility(View.VISIBLE);
                            }
                        }, timeElapsed);

                        timeElapsed = timeElapsed + AppConstants.PRESENTATION_TIME;

                        myHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                gratingImageView.setVisibility(View.INVISIBLE);
                            }
                        }, timeElapsed);

                        timeElapsed = timeElapsed + AppConstants.ISI_TIME;

                        myHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                gratingImageView.setRotation(45);
                                gratingImageView.setVisibility(View.VISIBLE);
                            }
                        }, timeElapsed);

                        timeElapsed = timeElapsed + AppConstants.PRESENTATION_TIME;

                        myHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                gratingImageView.setVisibility(View.INVISIBLE);
                            }
                        }, timeElapsed);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    done = true; // Need to get rid of this and have the value of done change when the experiment is over
                }
            }
        }).start();
    }

    // Need to initialize staircases

    // Create a touch detector
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        double x = event.getX();
        if (x > screenWidth / 2) {
            clickRight();
        } else {
            clickLeft();
        }
        return super.onTouchEvent(event);
    }

    // Called when window focus has changed (e.g. when coming to the screen for the first time from
    // the menu, if the game is paused), uses isInitialized from onCreate
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (!isInitialized) {
            isInitialized = true;

            // Set it up so that you can receive touch events
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

            // Set display (get size of screen)
            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenWidth = size.x;
            screenHeight = size.y;

            // Initialize the handler
            myHandler = new Handler();
            initGrating();

            done = false;

            // Set the flags
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

            // Initialize ArrayLists
            staircases = new ArrayList<Staircase>();

            // Start up game
            if (hasFocus) {
                update();
            }
            super.onWindowFocusChanged(hasFocus);
        }
    }
}
