package com.example.jackie.orientationdiscrimination;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
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
import android.widget.Toast;

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
    //    private ImageSwitcher imageSwitcher;
//    private int index;
    private ImageView gratingImageView;
    //    private Bitmap gratingBitmap;
//    private ArrayList<Bitmap> bitmapArrayList;
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
        experimentLayout = (RelativeLayout) findViewById(R.id.experiment_layout);

//        final ImageSwitcher imageSwitcher = (ImageSwitcher) findViewById(R.id.grating);

        isInitialized = false;
    }

    private void initGrating() {
        gratingImageView = (ImageView) findViewById(R.id.grating);
        String uri = "@drawable/grating_cropped";  // where myresource (without the extension) is the file

        int imageResource = getResources().getIdentifier(uri, null, getPackageName());

        Drawable res = getResources().getDrawable(imageResource);
        gratingImageView.setImageDrawable(res);
        gratingImageView.setVisibility(View.GONE);
        // Convert original to bitmap
//        imageSwitcher = (ImageSwitcher) findViewById(R.id.grating);
//        gratingBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.grating_cropped);
    }

    // Shows what has been clicked on the screen
    private void clickRight() {
        if (clickRight == false && clickLeft == false) {
            clickRight = true;
            clickLeft = false;
        }
    }

    private void clickLeft() {
        if (clickRight == false && clickLeft == false) {
            clickLeft = true;
            clickRight = false;
        }
    }

    private void updateStaircase(boolean isCorrect) {
        if (isCorrect) {

        } else {

        }
    }

    public static Bitmap RotateBitmap(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }


    private void update() {
//        Animation inanim  = AnimationUtils.loadAnimation(Experiment.this, R.anim.anim_fade_in);
//        inanim.setDuration(1000);
//        Animation outanim = AnimationUtils.loadAnimation(Experiment.this, R.anim.anim_fade_out);
//        outanim.setDuration(1000);
//
//        inanim.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//
//        outanim.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//        gratingImageView.startAnimation(inanim);
//        gratingImageView.startAnimation(outanim);
//        gratingImageView.startAnimation(inanim);
//        gratingImageView.startAnimation(outanim);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!done) {
//                    try {
//                        Thread.sleep(AppConstants.EXP_THREAD);
//                        index = 0;
//                        // Create rotated bitmap (need to change to getCurrentAngle from Staircase
//                        Bitmap rotatedBitmap = RotateBitmap(gratingBitmap, 45);
//                        // Create bitmap for blank screen
//                        Bitmap blankBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.blank);
//                        // Create ArrayList for images
//                        bitmapArrayList = new ArrayList<Bitmap>();
//                        bitmapArrayList.add(blankBitmap);
//                        bitmapArrayList.add(gratingBitmap);
//                        bitmapArrayList.add(blankBitmap);
//                        bitmapArrayList.add(rotatedBitmap);
//                        bitmapArrayList.add(blankBitmap);
//
//                        imageSwitcher.setFactory((ViewSwitcher.ViewFactory) Experiment.this);
//                        imageSwitcher.setImageDrawable(new BitmapDrawable(bitmapArrayList.get(index)));
//
//                        // Set up handler
////                    final Handler handler = new Handler();
//                        Runnable runnable = new Runnable() {
//                            @Override
//                            public void run() {
//                                index++;
//                                index = index % bitmapArrayList.size();
//                                imageSwitcher.setImageDrawable(new BitmapDrawable(bitmapArrayList.get(index)));
//                                myHandler.postDelayed(this, 1000);
//
//                            }
//                        };
//                        myHandler.postDelayed(runnable, 1000);
//                    } catch (InterruptedException e) {
//                        Thread.currentThread().interrupt();
//                    }

                    try {
                        Thread.sleep(AppConstants.EXP_THREAD);
                        myHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                gratingImageView.setVisibility(View.VISIBLE);
                            }
                        }, 1000);

                        myHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                gratingImageView.setVisibility(View.INVISIBLE);
                            }
                        }, 2000);

                        myHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                gratingImageView.setRotation(45);
                                gratingImageView.setVisibility(View.VISIBLE);
                            }
                        }, 3000);

                        myHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                gratingImageView.setVisibility(View.INVISIBLE);
                            }
                        }, 4000);

                        Thread.sleep(AppConstants.EXP_THREAD);
                        myHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                gratingImageView.setRotation(105);
                                gratingImageView.setVisibility(View.VISIBLE);
                            }
                        }, 8000);

                        myHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                gratingImageView.setVisibility(View.INVISIBLE);
                            }
                        }, 9000);

                        myHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                gratingImageView.setRotation(90);
                                gratingImageView.setVisibility(View.VISIBLE);
                            }
                        }, 10000);

                        myHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                gratingImageView.setVisibility(View.INVISIBLE);
                            }
                        }, 11000);
//                        myHandler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                gratingImageView.postDelayed(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        gratingImageView.setVisibility(View.VISIBLE);
//                                    }
//                                }, 1000);
//
//                                gratingImageView.postDelayed(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        gratingImageView.setVisibility(View.INVISIBLE);
//                                    }
//                                }, 2000);
//
//                                gratingImageView.postDelayed(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        gratingImageView.setRotation(45);
//                                        gratingImageView.setVisibility(View.VISIBLE);
//                                    }
//                                }, 3000);
//
//                                gratingImageView.postDelayed(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        gratingImageView.setVisibility(View.INVISIBLE);
//                                    }
//                                }, 4000);
//                            }
//                        });

                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    done = true;
                }
//            }
            }
        }
        ).

                start();
    }

    // Need to initialize staircases

    // Create a touch detector


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        double x = event.getX();
        if (x > screenWidth / 2) {
            clickRight();
            Toast.makeText(Experiment.this, "Right side tapped", Toast.LENGTH_LONG);
        } else {
            clickLeft();
            Toast.makeText(Experiment.this, "Left side tapped", Toast.LENGTH_LONG);
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
