package com.example.room_livedata_viewmodel.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.room_livedata_viewmodel.entity.Word;

import java.util.List;

@Dao
public interface WordDao {

    @Insert
    void insert(Word word);

    @Query("DELETE FROM word_table")
    void deleteAll();

    //load lại view mỗi khi db có sự thay đổi.
    @Query("SELECT * FROM word_table ORDER BY word ASC")
    LiveData<List<Word>> getAllWords();

    @Update
    void update(Word word);

    @Delete
    void delete(Word word);
//    void update(Word word);
}
