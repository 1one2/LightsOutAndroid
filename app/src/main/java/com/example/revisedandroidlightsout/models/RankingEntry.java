package com.example.revisedandroidlightsout.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RankingEntry {

    public RankingEntry(String playerName,long time)
    {
        this.playerName = playerName;
        this.time = time;
    }

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "time")
    public long time;

    @ColumnInfo(name = "playerName")
    public String playerName;
}
