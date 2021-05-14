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
    private Sprite sdrag;
    Bitmap fonBitmap;
    Bitmap drag;
    private int viewWidth;
    private int viewHeight;
    private final int timerInterval = 3;

    public Dragon dragon = new Dragon();

    public GameView(Context context) {

        super(context);
        fonBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fon_igry);
        drag = BitmapFactory.decodeResource(getResources(), R.drawable.drakon);

        int w = drag.getWidth()/5;
        int h = drag.getHeight()/3;

        Rect firstFrame = new Rect(0, 0, w, h);

        sdrag = new Sprite(450,900, drag, w*5, h*3);
        Timer t = new Timer();
        t.start();
    }

    protected void update() {
       //счет настроения
        if (dragon.x == 0){
            dragon.n--;
            dragon.x = 1000;
        }
        else{
            dragon.x--;
        }
            //счет усталости
        if (dragon.l == 0){
            dragon.s--;
            dragon.l = 850;
        }
            else{
            dragon.l--;
        }
        //счет аппетита
          if (dragon.k == 0){
            dragon.e--;
            dragon.k = 600;
        }
                else{
            dragon.k--;
        }
        //счет гигиены
         if (dragon.h == 0){
            dragon.gi--;
            dragon.h = 700;
        }
                    else{
            dragon.h--;
        }
        /*if(dragon.n == 0){

        }*/
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
        sdrag.draw(canvas);
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
