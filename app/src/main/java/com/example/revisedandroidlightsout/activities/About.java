package com.example.revisedandroidlightsout.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.revisedandroidlightsout.ApplicationController;
import com.example.revisedandroidlightsout.R;
import com.example.revisedandroidlightsout.adapters.AboutEntryAdapter;
import com.example.revisedandroidlightsout.models.AboutEntry;

import java.util.ArrayList;

public class About extends AppCompatActivity {


    private RecyclerView recyclerView;
    private ImageView back;
    private ArrayList<AboutEntry> allEntries = new ArrayList<>();
    private AboutEntryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        initializeViews();
    }

    void initializeViews()
    {
        back = findViewById(R.id.aboutMenuBackButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView = findViewById(R.id.aboutMenuRecyclerView);

        LinearLayoutManager layout = new LinearLayoutManager(ApplicationController.getInstance());
        recyclerView.setLayoutManager(layout);

        allEntries.add(new AboutEntry(
                "Lights Out is an electronic game released by " +
                        "Tiger Electronics in 1995. The game consists of a 5 by 5 " +
                        "grid of lights. When the game starts, a random number or a stored " +
                        "pattern of these lights is switched on. Pressing any of the lights will toggle it " +
                        "and the adjacent lights. The goal of the puzzle is to switch all the lights off, " +
                        "preferably in as few button presses as possible."
                 ));

        allEntries.add(new AboutEntry(
                "This app has been developed by Matache David Mihai and Tigaiala Maxim"
                + " for the Android Course inside of the faculty of Mathematics"
                + " and Informatics inside of the Transilvania Brasov University"
        ));

        adapter = new AboutEntryAdapter(allEntries);

        recyclerView.setAdapter(adapter);
    }
}