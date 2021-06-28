package com.example.revisedandroidlightsout.models;

import com.example.revisedandroidlightsout.data.RankingEntryRepository;

import java.util.ArrayList;
import java.util.List;

public class RankingEntries {

    public ArrayList<RankingEntry> allRankingEntries;
    private RankingEntryRepository repository;

    public RankingEntries()
    {
        repository = new RankingEntryRepository();
        allRankingEntries = new ArrayList<>();
    }

    public void initEntries()
    {
        repository.getAllEntries(new RankingEntryRepository.QueryListener() {
            @Override
            public void onSuccess(List<RankingEntry> allEntries) {

                for (RankingEntry entry : allEntries) {
                    allRankingEntries.add(entry);
                }

            }
        });
    }
}
