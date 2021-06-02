package com.example.dragonraise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Puzzle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));
    }
}