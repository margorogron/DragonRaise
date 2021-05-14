package com.example.dragonraise;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;


import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;

public class Game extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new GameView(this));
        //setContentView(R.layout.activity_main);
    }

}
class GameView<context> extends View {
    private Sprite fon;
    Bitmap fonBitmap;
    private int viewWidth;
    private int viewHeight;
    private final int timerInterval = 30;

    public Dragon dragon = new Dragon();

    public GameView(Context context) {

        super(context);
        fonBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fon_igry);

        /*int w = b.getWidth()/5;
        int h = b.getHeight()/3;

        Rect firstFrame = new Rect(0, 0, w, h);

        fon = new Sprite(0,0, b);*/
        Timer t = new Timer();
        t.start();
    }

    protected void update() {
        dragon.n--;

        invalidate();
    }

    class Timer extends CountDownTimer {

        public Timer() {
            super(Integer.MAX_VALUE, timerInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

            update();
        }

        @Override
        public void onFinish() {

        }
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        Paint p = new Paint();
        canvas.drawBitmap(fonBitmap, new Rect(0, 0, fonBitmap.getWidth(), fonBitmap.getHeight()),
                new Rect(0, 0, viewWidth, viewHeight), p);

        p.setAntiAlias(true);
        p.setTextSize(45.0f);
        p.setColor(Color.YELLOW);
        canvas.drawText("Настроение:" + dragon.n, 10, 100, p);
        p.setColor(Color.BLUE);
        canvas.drawText("Усталость:" + dragon.s, 360, 100, p);
        p.setColor(Color.RED);
        canvas.drawText("Голод:" + dragon.e, 685, 100, p);
        p.setColor(Color.WHITE);
        canvas.drawText("Гигиена:" + dragon.gi, 905, 100, p);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        viewWidth = w;
        viewHeight = h;
    }
}
