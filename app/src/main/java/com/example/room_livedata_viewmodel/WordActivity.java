package com.example.room_livedata_viewmodel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.room_livedata_viewmodel.entity.Word;
import com.example.room_livedata_viewmodel.viewmodel.WordViewModel;

public class WordActivity extends AppCompatActivity {

    private EditText mEditWordView;
    private WordViewModel mWordViewModel;
    private String word;
    private  int id ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);
        mEditWordView = findViewById(R.id.edit_word);

        Intent i = getIntent();
        word = i.getStringExtra("word");
        id = i.getIntExtra("id",0);
        if(word !=null){
            mEditWordView.setText(word);
        }

    }


    public void SaveClick(View view) {
        mWordViewModel = new WordViewModel(getApplication());
        Word w = new Word(mEditWordView.getText().toString());
        if(word !=null){
            w.setID(id);
            mWordViewModel.update(w);
        }else{
            mWordViewModel.insert(w);
        }
        finish();
    }

    public void DeleteClick(View view) {
        mWordViewModel = new WordViewModel(getApplication());
        Word w = new Word(mEditWordView.getText().toString());
        w.setID(id);
        mWordViewModel.delete(w);
        finish();
    }
}