package com.example.computer.roomwordsample;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/* Room class must be abstract and extend RoomDatabase
 *  Room uses the DAO to issue queries to its database.
 */

@Database(entities = {Word.class}, version = 1)
/*When you modify the database schema, you'll need
 * to update the version number and define how to handle migrations.
 */
public abstract class WordRoomDatabase extends RoomDatabase {
    public abstract WordDao wordDao();

    // Making the class a singleton to prevent having multiple instances
    // of the database opened at the same time
    private static volatile WordRoomDatabase INSTANCE;

    static WordRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null) {
                    // create database here...
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "word_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
