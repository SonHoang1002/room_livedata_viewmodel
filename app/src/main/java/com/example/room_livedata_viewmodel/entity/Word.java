package com.example.room_livedata_viewmodel.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {


    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int mID;

    @NonNull
    @ColumnInfo(name = "word")
    private String mWord;


    public int getID() {
        return mID;
    }

    public void setID(int mID) {
        this.mID = mID;
    }

    public String getWord() {
        return mWord;
    }

    public void setWord(String mWord) {
        this.mWord = mWord;
    }



    public Word( @NonNull String mWord) {
        this.mWord = mWord;
    }




}
