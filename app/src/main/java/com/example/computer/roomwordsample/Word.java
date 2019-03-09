package com.example.computer.roomwordsample;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey // each entity needs a primary key. Each word acts as it's own primary key
    @NonNull // return value can never be null
    @ColumnInfo(name = "word") // specify column name if you wat it to be different from member variable
    private String mWord;

    public Word(@NonNull String word) {
        this.mWord = word;
    }

    public String getmWord() {
        return this.mWord;
    }
}
