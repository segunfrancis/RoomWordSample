package com.example.computer.roomwordsample;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

/* The DAO must be an interface or an abstract class
*  By default, all queries bust be executed on a separate thread
*  Room uses the DAO to create a clean API for your code.
*/

@Dao
public interface WordDao {

    @Insert
    void insert(Word word);

    @Query("SELECT * from word_table LIMIT 1")
    Word[] getAnyWord();

    @Query("DELETE FROM word_table")
    void deleteAll();

    // because the operation deletes a single row, the @Delete annotation is all
    // that is needed to delete the word from the database
    @Delete
    void deleteWord(Word word);

    // LiveData helps in observing changes to data across multiple components of the app
    @Query("SELECT * from word_table ORDER BY id DESC")
    LiveData<List<Word>> getAllWords();

    @Update
    void update(Word... word);
}