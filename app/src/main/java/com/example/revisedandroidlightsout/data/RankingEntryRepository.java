package com.example.revisedandroidlightsout.data;

import com.example.revisedandroidlightsout.ApplicationController;
import com.example.revisedandroidlightsout.data.tasks.GetAllRankingEntries;
import com.example.revisedandroidlightsout.data.tasks.InsertNewRankingEntry;
import com.example.revisedandroidlightsout.models.RankingEntry;

import java.util.List;

public class RankingEntryRepository {

    private RankingDatabase database;

    public static interface QueryListener
    {
        void onSuccess(List<RankingEntry> allEntries);
    }

    public static interface InsertListener
    {
        void onSuccess();
    }


    public RankingEntryRepository()
    {
        database = ApplicationController.getDatabase();
    }

    public void insertNewRankingEntry(RankingEntry newEntry, InsertListener listener)
    {
        new InsertNewRankingEntry(database,listener).execute(newEntry);
    }

    public void getAllEntries(QueryListener listener)
    {
        new GetAllRankingEntries(database,listener).execute();
    }
}
