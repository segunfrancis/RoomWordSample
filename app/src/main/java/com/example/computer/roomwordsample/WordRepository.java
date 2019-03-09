package com.example.computer.roomwordsample;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

/**
 * A Repository is a class that abstracts access to multiple data sources.
 * The Repository is not part of the Architecture Components libraries,
 * but is a suggested best practice for code separation and architecture.
 * A Repository class handles data operations.
 * It provides a clean API to the rest of the app for app data.
 * <p>
 * A Repository manages query threads and allows you to use multiple backends.
 * In the most common example, the Repository implements the logic for
 * deciding whether to fetch data from a network or use results cached in a
 * local database.
 */

public class WordRepository {
    private WordDao mWordDoa;
    private LiveData<List<Word>> mAllWords;

    WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDoa = db.wordDao();
        mAllWords = mWordDoa.getAllWords();
    }

    // adding wrapper for getAllWords()
    LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    // adding wrapper for insert()
    /**
     * You must call this on a non-UI thread or your app will crash.
     * Room ensures that you don't do any long-running operations
     * on the main thread, blocking the UI.
     */
    public void insert(Word word) {
        new insertAsyncTask(mWordDoa).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
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
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
