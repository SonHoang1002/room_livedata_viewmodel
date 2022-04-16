package com.example.room_livedata_viewmodel.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.room_livedata_viewmodel.DAO.WordDao;
import com.example.room_livedata_viewmodel.entity.Word;

// khai báo số lượng bảng trong db, version: phiên bản sử dụng, exportSchema()
@Database(entities = {Word.class}, version = 1, exportSchema = false)

public abstract class MyRoomDatabase extends RoomDatabase {

    // tạo ra 1 đối tượng
    public abstract WordDao wordDao();

    //kiểm tra đối tượng rỗng hay không
    private static MyRoomDatabase mWordRoomDatabase;

    public static MyRoomDatabase getDatabase(final Context context) {
        if (mWordRoomDatabase == null) {
            synchronized (MyRoomDatabase.class) {
                if (mWordRoomDatabase == null) {
                    // Create database here
                    mWordRoomDatabase = Room.databaseBuilder(context.getApplicationContext(),
                            MyRoomDatabase.class, "word_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();

                }
            }
        }
        return mWordRoomDatabase;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new Thread(() -> {
                final WordDao mDao = mWordRoomDatabase.wordDao();
                mDao.deleteAll();
                String[] words = {"dolphin", "crocodile", "cobra"};
                for (int i = 0; i <= words.length - 1; i++) {
                    Word word = new Word(words[i]);
                    mDao.insert(word);
                }
            }).start();
        }
    };


}
