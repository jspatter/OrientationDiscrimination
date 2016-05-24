package com.example.jackie.orientationdiscrimination;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainMenu extends AppCompatActivity {

    // Create variable to place Main Menu layout
    private RelativeLayout mainMenuLayout;

    // Create variable to place Start Button ImageView
    private ImageView startButtonImageView;

    // Create variable to place Settings button ImageView
    private ImageView settingsButtonImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        // Find the layout from the main_menu.xml file
        mainMenuLayout = (RelativeLayout) findViewById(R.id.main_menu_layout);

        // Set the screen to full screen mode and hide any action bars, etc.
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Initialize everything in the screen
        initStartButton();
        initSettings();
    }

    // Create initialization methods for the various game modes
    private void initStartButton() {
        startButtonImageView = (ImageView) findViewById(R.id.start_button);
        startButtonImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startButtonIntent = new Intent(MainMenu.this, Experiment.class);
                startButtonIntent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(startButtonIntent);
            }
        });
    }

    private void initSettings() {
        settingsButtonImageView = (ImageView) findViewById(R.id.settings);
        settingsButtonImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsButtonIntent = new Intent(MainMenu.this, Settings.class);
                settingsButtonIntent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(settingsButtonIntent);
            }
        });
    }
}
