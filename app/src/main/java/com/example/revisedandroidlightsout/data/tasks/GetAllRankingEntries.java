package com.example.revisedandroidlightsout.data.tasks;

import android.os.AsyncTask;

import androidx.room.PrimaryKey;

import com.example.revisedandroidlightsout.data.RankingDatabase;
import com.example.revisedandroidlightsout.data.RankingEntryRepository;
import com.example.revisedandroidlightsout.models.RankingEntry;

import java.util.ArrayList;
import java.util.List;

public class GetAllRankingEntries extends AsyncTask<Void,Void, List<RankingEntry>> {


    private RankingDatabase database;
    private RankingEntryRepository.QueryListener listener;


    public GetAllRankingEntries(RankingDatabase pDatabase, RankingEntryRepository.QueryListener pListener)
    {
        database = pDatabase;
        listener = pListener;
    }
    @Override
    protected List<RankingEntry> doInBackground(Void... voids) {
        return database.dao().getAllRankingEntries();
    }

    @Override
    protected void onPostExecute(List<RankingEntry> allEntries)
    {
        super.onPostExecute(allEntries);
        listener.onSuccess(allEntries);
    }

}
