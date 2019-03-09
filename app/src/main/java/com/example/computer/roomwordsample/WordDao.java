package com.example.computer.roomwordsample;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

/* The DAO must be an interface or an abstract class
*  By default, all queries bust be executed on a separate thread
*  Room uses the DAO to create a clean API for your code.
*/

@Dao
public interface WordDao {

    @Insert
    void insert(Word word);

    @Query("DELETE FROM word_table")
    void deletaAll();

    @Query("SELECT * from word_table ORDER BY word ASC")
    List<Word> getAllWords();
}