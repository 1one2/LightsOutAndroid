package com.example.revisedandroidlightsout.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Adapter;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.revisedandroidlightsout.ApplicationController;
import com.example.revisedandroidlightsout.R;
import com.example.revisedandroidlightsout.adapters.LightAdapter;
import com.example.revisedandroidlightsout.data.RankingEntryRepository;
import com.example.revisedandroidlightsout.interfaces.LightClickListener;
import com.example.revisedandroidlightsout.models.Board;
import com.example.revisedandroidlightsout.models.Light;
import com.example.revisedandroidlightsout.models.RankingEntry;

public class Game extends AppCompatActivity {

    //region Declarations

    //region Buttons

    private ImageView pause;
    private ImageView restart;
    private ImageView submit;
    private ImageView send;
    private ImageView back;
    private ImageView resume;

    //endregion

    private RecyclerView recyclerView;

    //region Menus

    private LinearLayout submitBar;
    private FrameLayout pausedMenu;

    //endregion

    private Chronometer timer;
    private Chronometer timerPausedMenu = new Chronometer(ApplicationController.getInstance());

    private EditText submitText;

    private Board board = new Board();

    private long elapsedSeconds;
    private long timeSpendPaused;

    private boolean isGamePaused = false;
    private boolean isFirstMove = true;
    private boolean alreadySubmitted = false;

    RankingEntryRepository repository = new RankingEntryRepository();

    LightAdapter adapter = new LightAdapter(board, new LightClickListener() {
        @Override
        public void onLightClicked(Light light) {

            if(!board.isGameDone() && !isGamePaused)
            {
                if(isFirstMove)
                {
                    timer.setBase(SystemClock.elapsedRealtime());
                    timer.start();
                    isFirstMove = false;
                }
                board.switchLights(board.getLights().indexOf(light));

                if(board.isGameDone())
                {
                    timer.stop();

                    elapsedSeconds =(SystemClock.elapsedRealtime() - timer.getBase()) / 1000;
                }

                adapter.notifyDataSetChanged();
            }

        }
    });

    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        viewsInitialization();
    }


    void viewsInitialization()
    {
        //region Buttons

        pause = findViewById(R.id.gamePauseButton);
        submit = findViewById(R.id.gameSubmitButton);
        restart = findViewById(R.id.gameRestartButton);
        resume = findViewById(R.id.gamePausedResumeButton);
        back = findViewById(R.id.gameBackButton);
        send = findViewById(R.id.gameSubmitBarSendButton);

        //endregion

        timer = findViewById(R.id.gameTimer);

        submitText = findViewById(R.id.gameSubmitBarText);


        recyclerView = findViewById(R.id.gameRecyclerView);
        GridLayoutManager layout = new GridLayoutManager(getApplicationContext(),5);
        recyclerView.setLayoutManager(layout);

        submitBar = findViewById(R.id.gameSubmitBar);

        pausedMenu = findViewById(R.id.gamePausedMenu);

        board.initBoard();

        recyclerView.setAdapter(adapter);


        //region Click Listeners

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(board.isGameDone() && !alreadySubmitted)
                submitBar.setVisibility(View.VISIBLE);
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(submitText.getText().toString().isEmpty())
                    Toast.makeText(ApplicationController.getInstance(),"Name can't be empty",Toast.LENGTH_SHORT).show();

                else
                {
                    repository.insertNewRankingEntry(new RankingEntry(submitText.getText().toString(),
                                    elapsedSeconds),
                            new RankingEntryRepository.InsertListener() {
                                @Override
                                public void onSuccess() {
                                    Toast.makeText(ApplicationController.getInstance(),"Submition was successful",Toast.LENGTH_SHORT).show();
                                }
                            });

                    submitBar.setVisibility(View.INVISIBLE);
                    alreadySubmitted = true;
                    submitText.setText("");
                }
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isGamePaused)
                {
                    pausedMenu.setVisibility(View.VISIBLE);
                    pausedMenu.bringToFront();

                    isGamePaused = true;

                    timer.stop();
                    timerPausedMenu.setBase(SystemClock.elapsedRealtime());
                    timerPausedMenu.start();
                }

            }
        });

        resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pausedMenu.setVisibility(View.INVISIBLE);

                isGamePaused = false;

                timerPausedMenu.stop();
                timeSpendPaused = (SystemClock.elapsedRealtime() - timerPausedMenu.getBase());

                timer.setBase(timer.getBase() + timeSpendPaused);
                timer.start();
            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isGamePaused)
                {
                    submitBar.setVisibility(View.INVISIBLE);

                    timer.stop();

                    board.initBoard();
                    adapter.notifyDataSetChanged();

                    timer.setBase(SystemClock.elapsedRealtime());
                    timer.start();
                    alreadySubmitted = false;
                }
            }
        });

        //endregion


    }
}