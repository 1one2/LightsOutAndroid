package com.example.revisedandroidlightsout.data.tasks;

import android.os.AsyncTask;

import com.example.revisedandroidlightsout.data.RankingDatabase;
import com.example.revisedandroidlightsout.data.RankingEntryRepository;
import com.example.revisedandroidlightsout.models.RankingEntry;

public class InsertNewRankingEntry extends AsyncTask<RankingEntry,Void,Void> {

    private RankingDatabase database;
    private RankingEntryRepository.InsertListener listener;

    public InsertNewRankingEntry(RankingDatabase pDatabase,RankingEntryRepository.InsertListener pListener)
    {
        database = pDatabase;
        listener = pListener;
    }


    @Override
    protected Void doInBackground(RankingEntry... rankingEntries) {

        database.dao().insertRankingEntry(rankingEntries[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid)
    {
        super.onPostExecute(aVoid);
        listener.onSuccess();
    }
}
