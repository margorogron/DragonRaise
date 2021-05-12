package com.example.dragonraise;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;


import androidx.appcompat.app.AppCompatActivity;

public class Game extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new GameView(this));
        //setContentView(R.layout.activity_main);
    }

}
class GameView extends View{
    public Dragon dragon = new Dragon();
    public GameView(Context context) {

        super(context);
    }
    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        canvas.drawARGB(250, 255, 0, 0); // заливаем цветом
        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setTextSize(55.0f);
        p.setColor(Color.WHITE);
        canvas.drawText("настроение "+dragon.n, 100, 100, p);
    }
}
