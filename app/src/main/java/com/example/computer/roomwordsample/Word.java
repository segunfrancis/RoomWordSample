package com.example.computer.roomwordsample;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey(autoGenerate = true) // each entity needs a primary key.
    private int id;

    @NonNull // return value can never be null
    @ColumnInfo(name = "word")
    // specify column name if you want it to be different from member variable
    private String mWord;

    public Word(@NonNull String word) {
        this.mWord = word;
    }

    @Ignore
    public Word(int id, @NonNull String mWord) {
        this.id = id;
        this.mWord = mWord;
    }

    public String getWord() {
        return this.mWord;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
