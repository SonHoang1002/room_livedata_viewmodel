package com.example.room_livedata_viewmodel.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.room_livedata_viewmodel.DAO.WordDao;
import com.example.room_livedata_viewmodel.database.MyRoomDatabase;
import com.example.room_livedata_viewmodel.entity.Word;

import java.util.List;

public class WordRepository {
    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;


    public WordRepository(Application application) {
        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        new Thread(() -> mWordDao.insert(word)).start();
    }

    public void update(Word word) {
        new Thread(() -> {
            mWordDao.update(word);
        }).start();
    }

    public void delete(Word w) {
        new Thread(() -> {
            mWordDao.delete(w);
        }).start();
    }
//    public void update(Word word) {
//        new Thread(() -> mWordDao.update(word)).start();
//    }
}
