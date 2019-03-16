package com.example.computer.roomwordsample;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

/**
 * The ViewModel's role is to provide data to the UI and survive configuration
 * changes. A ViewModel acts as a communication center between the Repository
 * and the UI. You can also use a ViewModel to share data between fragments.
 * The ViewModel is part of the lifecycle library.
 * <p>
 * A ViewModel holds your app's UI data in a lifecycle-conscious way that
 * survives configuration changes. Separating your app's UI data from your
 * Activity and Fragment classes lets you better follow the single
 * responsibility principle: Your activities and fragments are responsible
 * for drawing data to the screen, while your ViewModel can take care of
 * holding and processing all the data needed for the UI.
 */

public class WordViewModel extends AndroidViewModel {
    private WordRepository mRepository;
    private LiveData<List<Word>> mAllWords;

    public WordViewModel(@NonNull Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    // This completely hides the implementation from the UI.
    // this makes the method available to the MainActivity
    LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    // This completely hides the implementation from the UI.
    // this makes the method available to the MainActivity
    public void insert(Word word) {
        mRepository.insert(word);
    }

    public void deleteWord(Word word) {
        mRepository.deleteWord(word);
    }

    // this makes the method available to the MainActivity
    public void deleteAll() {
        mRepository.deleteAll();
    }

    public void update(Word word) {
        mRepository.update(word);
    }
}
/* Warning: Never pass context into ViewModel instances. Do not store
 * Activity, Fragment, or View instances or their Context in the ViewModel.
 * If you need the application context, use AndroidViewModel,
 * as shown in this codelab.
 */