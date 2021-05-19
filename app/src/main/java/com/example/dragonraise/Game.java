package com.example.dragonraise;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


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
    Bitmap fonBitmap;
    private Sprite sdrag;
    Bitmap drag;
    Bitmap ndrag;
    Bitmap eedrag;
    private Sprite_spat sspat;
    Bitmap spatt;
    private Sprite_myt myt;
    Bitmap mytt;
    private Sprite_est est;
    Bitmap estt;
    private Sprite_igra igra;
    Bitmap igratt;
    Timer t;

    private int viewWidth;
    private int viewHeight;
    private final int timerInterval = 3;


    public Dragon dragon = new Dragon();


    public GameView(Context context) {

        super(context);

        //Resources resources = context.getResources();
        //DisplayMetrics metrics = resources.getDisplayMetrics();


        fonBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fon_igry);
        drag = BitmapFactory.decodeResource(getResources(), R.drawable.drakon1);
        ndrag = BitmapFactory.decodeResource(getResources(), R.drawable.drakon);
        eedrag = BitmapFactory.decodeResource(getResources(), R.drawable.drakon2);
        spatt = BitmapFactory.decodeResource(getResources(), R.drawable.spat);
        mytt = BitmapFactory.decodeResource(getResources(), R.drawable.myt);
        igratt = BitmapFactory.decodeResource(getResources(), R.drawable.igrat);
        estt = BitmapFactory.decodeResource(getResources(), R.drawable.est);

        int w = drag.getWidth();
        int h = drag.getHeight();

        int we = spatt.getWidth();
        int he = spatt.getHeight();

        int wei = mytt.getWidth();
        int hei = mytt.getHeight();

        int weig = estt.getWidth();
        int heig = estt.getHeight();

        int weigh = igratt.getWidth();
        int heigh = igratt.getHeight();

        Rect firstFrame = new Rect(0, 0, w, h);

        sdrag = new Sprite(450, 900, drag, w, h);
        sspat = new Sprite_spat(100, 1550, spatt, we, he);
        myt = new Sprite_myt(320, 1500, mytt, wei, hei);
        est = new Sprite_est(546, 1500, estt, weig, heig);
        igra = new Sprite_igra(732, 1500, igratt, weigh, heigh);
        t = new Timer();
        t.start();


    }

    protected void update() {

        //счет настроения
        if (dragon.x == 0) {
            dragon.nastroi--;
            dragon.x = 800;
        } else {
            dragon.x--;
        }
        //счет усталости
        if (dragon.l == 0) {
            dragon.son--;
            dragon.l = 850;
        } else {
            dragon.l--;
        }
        //счет аппетита
        if (dragon.k == 0) {
            dragon.eat--;
            dragon.k = 600;
        } else {
            dragon.k--;
        }
        //счет гигиены
        if (dragon.h == 0) {
            dragon.gigiena--;
            dragon.h = 700;
        } else {
            dragon.h--;
        }
        if (dragon.nastroi == 0) {
            //sdrag.changeBitmap(ndrag);
            t.cancel();

        }
        if (dragon.nastroi <= 70 && dragon.eat <= 70 && dragon.son <= 70 && dragon.gigiena <= 70 &&
                dragon.nastroi >= 40 && dragon.eat >= 40 && dragon.son >= 40 && dragon.gigiena >= 40) {
            sdrag.changeBitmap(ndrag);
            //t.wait();
        }
        if (dragon.nastroi <= 30 && dragon.eat <= 30 && dragon.son <= 30 && dragon.gigiena <= 30 ) {
            sdrag.changeBitmap(eedrag);
            //t.wait();
        }
        if (dragon.nastroi > 70 && dragon.eat > 70 && dragon.son > 70 && dragon.gigiena > 70) {
            sdrag.changeBitmap(drag);
            //t.wait();
        }
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
        sspat.draw(canvas);
        myt.draw(canvas);
        est.draw(canvas);
        igra.draw(canvas);

        p.setAntiAlias(true);
        p.setTextSize(45.0f);
        p.setColor(Color.YELLOW);
        int nn = (int) dragon.nastroi;
        canvas.drawText("Настроение:" + nn, 10, 400, p);
        p.setColor(Color.BLUE);
        int sn = (int) dragon.son;
        canvas.drawText("Усталость:" + sn, 10, 100, p);
        p.setColor(Color.RED);
        int en = (int) dragon.eat;
        canvas.drawText("Голод:" + en, 10, 300, p);
        p.setColor(Color.WHITE);
        int gn = (int) dragon.gigiena;
        canvas.drawText("Гигиена:" + gn, 10, 200, p);


        Paint paint = new Paint();
        paint.setColor(Color.WHITE); // установим белый цвет
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.FILL); // заливаем
        paint.setAntiAlias(true);
        final RectF oval = new RectF();
        oval.set(870, 80, 970, 180);
        canvas.drawArc(oval, 0, 360, true, paint);


        paint.setStyle(Paint.Style.STROKE);
        oval.set(840, 50, 1000, 210);
        canvas.drawArc(oval, 0, (int)dragon.lvl_ang, true, paint);

        paint.setStyle(Paint.Style.STROKE);
        p.setColor(Color.BLACK);
        p.setTextSize(65.0f);
        canvas.drawText("" + dragon.level, 900, 150, p);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        viewWidth = w;
        viewHeight = h;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (sdrag.click(event.getX(), event.getY()))
            if (dragon.nastroi < 100) {
                dragon.nastroi += 0.5;
                dragon.lvl_ang += 0.3;
                dragon.checkLv(dragon.lvl_ang);
            }
        if (sspat.click_spat(event.getX(), event.getY())) {
            if (dragon.son < 100) {
                dragon.son += 0.5;
                dragon.lvl_ang += 0.3;
                dragon.checkLv(dragon.lvl_ang);
            }
        }
        if (myt.click_myt(event.getX(), event.getY())) {
            if (dragon.gigiena < 100) {
                dragon.gigiena += 0.5;
                dragon.lvl_ang += 0.3;
                dragon.checkLv(dragon.lvl_ang);
            }
        }
        if (est.click_est(event.getX(), event.getY())) {
            if (dragon.eat < 100) {
                dragon.eat +=0.7;
                dragon.lvl_ang += 0.3;
                dragon.checkLv(dragon.lvl_ang);
            }
        }

        if (igra.click_igra(event.getX(), event.getY())) {
            if (dragon.nastroi < 100) {
                dragon.nastroi += 0.5;
                dragon.lvl_ang += 0.3;
                dragon.checkLv(dragon.lvl_ang);
            }
        }

        return true;
    }
}
