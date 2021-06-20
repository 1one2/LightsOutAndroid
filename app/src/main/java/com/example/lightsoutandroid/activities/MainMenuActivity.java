package com.example.lightsoutandroid.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lightsoutandroid.ApplicationController;
import com.example.lightsoutandroid.R;
import com.example.lightsoutandroid.interfaces.OnItemClickListener;
import com.example.lightsoutandroid.models.Board;
import com.example.lightsoutandroid.models.Light;

import java.io.Serializable;
import java.util.LinkedHashMap;

public class MainMenuActivity extends AppCompatActivity implements Serializable {

    private Button playButton;
    private Button aboutButton;
    private Button exitButton;

    Intent playIntent;
    Intent aboutIntent;

    private OnItemClickListener clickListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        setUpViews();
        //
    }

    void setUpViews()
    {
         playButton = this.findViewById(R.id.playButton);
         aboutButton = this.findViewById(R.id.aboutButton);
         exitButton = this.findViewById(R.id.exitButton);

        playIntent = new Intent(this, GameActivity.class);

        clickListener = new OnItemClickListener() {
            @Override
            public void OnClick(View view) {
                if (view.getId() == R.id.playButton) {
                    startActivity(playIntent);
                }
            }
        };



        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    clickListener.OnClick(playButton);
            }
        });
    }
}