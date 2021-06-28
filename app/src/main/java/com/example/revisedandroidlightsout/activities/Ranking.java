package com.example.revisedandroidlightsout.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.revisedandroidlightsout.ApplicationController;
import com.example.revisedandroidlightsout.R;
import com.example.revisedandroidlightsout.adapters.RankingEntryAdapter;
import com.example.revisedandroidlightsout.data.RankingEntryRepository;
import com.example.revisedandroidlightsout.models.RankingEntries;
import com.example.revisedandroidlightsout.models.RankingEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Ranking extends AppCompatActivity {


    //region DECLARATIONS

    private RankingEntryRepository repository = new RankingEntryRepository();

    private ImageView back;
    private ImageView refresh;

    private RecyclerView recyclerView;

    private RankingEntries entries = new RankingEntries();

    private RankingEntryAdapter adapter;


    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        viewsInitialization();
    }



    void viewsInitialization() {
        back = findViewById(R.id.rankingMenuBackButton);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                }
        });

        refresh = findViewById(R.id.rankingMenuRefresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.notifyDataSetChanged();
               }
        });

        recyclerView = findViewById(R.id.rankingMenuRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(ApplicationController.getInstance());
        recyclerView.setLayoutManager(layoutManager);

        entries.initEntries();


        adapter = new RankingEntryAdapter(entries.allRankingEntries);

        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

    }





}