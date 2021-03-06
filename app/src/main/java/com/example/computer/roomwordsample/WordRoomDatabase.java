package com.example.computer.roomwordsample;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * Room class must be abstract and extend RoomDatabase
 * Room uses the DAO to issue queries to its database.
 */

@Database(entities = {Word.class}, version = 2, exportSchema = false)
// When you modify the database schema, you'll need
// to update the version number and define how to handle migrations.

public abstract class WordRoomDatabase extends RoomDatabase {
    public abstract WordDao wordDao();

    // Making the class a singleton to prevent having multiple instances
    // of the database opened at the same time
    private static volatile WordRoomDatabase INSTANCE;

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE 'word_table' ADD COLUMN 'id' INTEGER NOT NULL");
        }
    };

    static WordRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null) {
                    // create database here...
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "word_database")
                            .addCallback(sRoomDatabaseCallback)
                            .addMigrations(MIGRATION_1_2)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };


    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final WordDao mDao;
        Intent intent;

        PopulateDbAsync(WordRoomDatabase db) {
            mDao = db.wordDao();
        }

        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param params The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected Void doInBackground(final Void... params) {
            return null;
        }
    }
}