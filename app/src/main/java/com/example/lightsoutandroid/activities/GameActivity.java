package com.example.lightsoutandroid.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.lightsoutandroid.ApplicationController;
import com.example.lightsoutandroid.R;
import com.example.lightsoutandroid.adapters.MyAdapter;
import com.example.lightsoutandroid.interfaces.LightClickListener;
import com.example.lightsoutandroid.interfaces.OnItemClickListener;
import com.example.lightsoutandroid.models.Board;
import com.example.lightsoutandroid.models.Light;

import java.io.Serializable;

public class GameActivity extends AppCompatActivity implements Serializable {

    private RecyclerView recyclerView;

    private boolean isGamePaused = false;

    private ImageView pauseImage;
    private ImageView startRestartImage;
    private ImageView backImage;

    private ImageButton resumeGame;

    private FrameLayout pauseMenu;

    private Chronometer timer;

    private Chronometer timerPausedMenu = new Chronometer(ApplicationController.getInstance());

    private OnItemClickListener clickListener;

    private Board board = new Board();

    private long elapsedSeconds;
    private long timeSpendPaused = 0;


    private MyAdapter adapter = new MyAdapter(board, new LightClickListener() {
        @Override
        public void onLightClicked(Light light) {
            if(!board.isGameDone() && !isGamePaused) {
                board.switchLights(board.getLights().indexOf(light));
                if (board.isGameDone()) {
                    timer.stop();
                    elapsedSeconds = (SystemClock.elapsedRealtime() - timer.getBase()) / 1000;
                    Toast.makeText(ApplicationController.getInstance(), "You solved the puzzle in " + elapsedSeconds + " seconds.", Toast.LENGTH_LONG).show();

                }
                adapter.notifyDataSetChanged();
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setUp();
    }

    private void setUp()
    {
        timer = findViewById(R.id.timer);

        startRestartImage = findViewById(R.id.playImageView);
        backImage = findViewById(R.id.backImageView);
        pauseImage = findViewById(R.id.pauseImageView);

        pauseMenu = findViewById(R.id.pausedLayout);
        resumeGame = pauseMenu.findViewById(R.id.resumeGamePlay);



        RecyclerView recyclerView = findViewById(R.id.boardGridView);
        GridLayoutManager layout = new GridLayoutManager(getApplicationContext(),5);
        recyclerView.setLayoutManager(layout);

        clickListener = (OnItemClickListener) getIntent().getSerializableExtra("clickListener");

        board.initBoard();


        recyclerView.setAdapter(adapter);

        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        pauseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isGamePaused) {
                    pauseMenu.setVisibility(View.VISIBLE);
                    pauseMenu.bringToFront();

                    isGamePaused = true;


                    timer.stop();

                    timerPausedMenu.setBase(SystemClock.elapsedRealtime());
                    timerPausedMenu.start();
                }
            }
        });

        resumeGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseMenu.setVisibility(View.INVISIBLE);
                isGamePaused = false;

                timerPausedMenu.stop();

                timeSpendPaused = (SystemClock.elapsedRealtime() - timerPausedMenu.getBase());

                timer.setBase(timer.getBase() + timeSpendPaused);

                timer.start();
            }
        });

        startRestartImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isGamePaused) {
                    timer.stop();
                    board.initBoard();
                    adapter.notifyDataSetChanged();
                    timer.setBase(SystemClock.elapsedRealtime());
                    timer.start();
                }
            }
        });

        timer.start();
    }

}