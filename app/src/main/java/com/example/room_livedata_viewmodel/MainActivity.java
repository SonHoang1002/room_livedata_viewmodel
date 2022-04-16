package com.example.room_livedata_viewmodel;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.room_livedata_viewmodel.adapter.WordListAdapter;
import com.example.room_livedata_viewmodel.entity.Word;
import com.example.room_livedata_viewmodel.viewmodel.WordViewModel;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private WordViewModel mWordViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWordViewModel = new WordViewModel(getApplication());

        recyclerView = findViewById(R.id.recyclerview);
        final WordListAdapter adapter = new WordListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        mWordViewModel.getAllWords().observe(this, words -> {
            adapter.setWords(words);
        });



    }

    public void addNewWord(View view) {
        startActivity(new Intent(this,WordActivity.class));
    }
}