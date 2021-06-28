package com.example.revisedandroidlightsout.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.revisedandroidlightsout.R;
import com.example.revisedandroidlightsout.interfaces.OnItemClickListener;

public class MainMenu extends AppCompatActivity {

    //region DECLARATIONS

    //region BUTTONS

    private Button playButton;
    private Button rankingButton;
    private Button aboutButton;
    private Button exitButton;

    //endregion

    //region Intents

    private Intent playIntent;
    private Intent rankingIntent;
    private Intent aboutIntent;

    //endregion

    private OnItemClickListener clickListener;

    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        viewsInitialization();
    }

    void viewsInitialization()
    {
        //region BUTTONS
        playButton = findViewById(R.id.mainMenuPlay);
        rankingButton = findViewById(R.id.mainMenuRanking);
        aboutButton = findViewById(R.id.mainMenuAbout);
        exitButton = findViewById(R.id.mainMenuExit);
        //endregion

        //region INTENTS

        playIntent = new Intent(this,Game.class);
        rankingIntent = new Intent(this,Ranking.class);
        aboutIntent = new Intent(this,About.class);

        //endregion

        //region Click-Listeners

        clickListener = new OnItemClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.mainMenuPlay)
                    startActivity(playIntent);
                else if(view.getId() == R.id.mainMenuRanking)
                    startActivity(rankingIntent);
                else if(view.getId() == R.id.mainMenuAbout)
                    startActivity(aboutIntent);
                else if(view.getId() == R.id.mainMenuExit)
                    finish();
            }
        };

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(playButton);
            }
        });

        rankingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(rankingButton);
            }
        });

        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(aboutButton);
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(exitButton);
            }
        });

        //endregion
    }
}