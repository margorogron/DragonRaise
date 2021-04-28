package com.example.dragonraise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void game(View view) {
        startActivity(new Intent(this, Game.class));
    }

    public void user(View view) {
        startActivity(new Intent(this, User.class));
    }

    public void rul(View view) {
        startActivity(new Intent(this, Rules.class));
    }
}
