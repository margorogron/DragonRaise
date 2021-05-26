package com.example.dragonraise;

import android.content.Context;
import android.content.SharedPreferences;
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
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;

public class Game extends AppCompatActivity {

    public static SharedPreferences settings;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        settings = getSharedPreferences("PreferencesName", MODE_PRIVATE);
        setContentView(new GameView(this));
        //setContentView(R.layout.activity_main);

    }

}

class GameView<context> extends View {
    Bitmap fonBitmap;

    Bitmap oldfonBitmap;

    private Sprite sdrag;
    Bitmap drag;

    Bitmap olddrag;
    Bitmap olddrag1;
    Bitmap olddrag2;
    Bitmap olddrag3;

    Bitmap ndrag;
    Bitmap eedrag;
    Bitmap plachetdrag;
    Bitmap spitdrag;

    private Sprite sspat;
    Bitmap spatt;
    private Sprite myt;
    Bitmap mytt;
    private Sprite est;
    Bitmap estt;
    private Sprite igra;
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
        oldfonBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fon_igry1);
        //sdrag = new Sprite(0.25, 0.25, 0.5, 0.5, BitmapFactory.decodeResource(getResources(), R.drawable.drakon1));
        //sspat = new Sprite(0.75, 0.25, 0.5, 0.5,BitmapFactory.decodeResource(getResources(), R.drawable.spat));
        drag = BitmapFactory.decodeResource(getResources(), R.drawable.drakon1);
        plachetdrag = BitmapFactory.decodeResource(getResources(), R.drawable.drakon_plachet);
        spitdrag = BitmapFactory.decodeResource(getResources(), R.drawable.drakon_spit);
        ndrag = BitmapFactory.decodeResource(getResources(), R.drawable.drakon);
        eedrag = BitmapFactory.decodeResource(getResources(), R.drawable.drakon2);

        spatt = BitmapFactory.decodeResource(getResources(), R.drawable.spat);
        mytt = BitmapFactory.decodeResource(getResources(), R.drawable.myt);
        igratt = BitmapFactory.decodeResource(getResources(), R.drawable.igrat);
        estt = BitmapFactory.decodeResource(getResources(), R.drawable.est);

        olddrag = BitmapFactory.decodeResource(getResources(), R.drawable.olddrakon);
        olddrag1 = BitmapFactory.decodeResource(getResources(), R.drawable.olddrakon1);
        olddrag2 = BitmapFactory.decodeResource(getResources(), R.drawable.olddrakon2);
        olddrag3 = BitmapFactory.decodeResource(getResources(), R.drawable.olddrakonspit);

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

        int weight = igratt.getWidth();
        int height = igratt.getHeight();


        //Rect firstFrame = new Rect(0, 0, w, h);
      // МЕНЯТЬ РАСПЛОЖЕНИЕ ОБЪЕКТОВ
       sdrag = new Sprite(450, 900, drag, w, h);
        sspat = new Sprite(100, 1550, spatt, we, he);
        myt = new Sprite(320, 1500, mytt, wei, hei);
        est = new Sprite(546, 1500, estt, weig, heig);
        igra = new Sprite(732, 1500, igratt, weigh, heigh);

        /*sdrag = new Sprite(250, 900, drag, w, h);
        sspat = new Sprite(10, 1850, spatt, we, he);
        myt = new Sprite(220, 1800, mytt, wei, hei);
        est = new Sprite(500, 1800, estt, weig, heig);
        igra = new Sprite(732, 1800, igratt, weigh, heigh);*/

        //сохранение настроения
        String str_nastroi = Game.settings.getString("nastroi","1");
        dragon.nastroi = Double.valueOf(str_nastroi);
        //сохранение сна
        String str_son = Game.settings.getString("son","1");
        dragon.son = Double.valueOf(str_son);
        //сохранение гигиены
        String str_gigiena = Game.settings.getString("gigiena","1");
        dragon.gigiena = Double.valueOf(str_gigiena);
        //сохранение сытости
        String str_eat = Game.settings.getString("eat","1");
        dragon.eat = Double.valueOf(str_eat);
        //сохранение левела
        String str_lvl_ang = Game.settings.getString("lvl_ang","1");
        dragon.lvl_ang = Double.valueOf(str_lvl_ang);
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
        if(!dragon.sleeping) {
            if (dragon.l == 0) {
                dragon.son--;
                dragon.l = 500;
            } else {
                dragon.l--;
            }
        }
        if(dragon.sleeping) {
            if (dragon.l == 0) {
                if(dragon.son < 100)
                    dragon.son++;
                dragon.l = 500;

            } else {
                dragon.l--;
            }
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
        //остановка счета
        /*if (dragon.nastroi == -1) {
            t.cancel();
        }

        if (dragon.eat == -1) {
            t.cancel();
        }

        if (dragon.gigiena == -1) {
            t.cancel();
        }

        if (dragon.son == -1) {
            t.cancel();
        }
*/

        if (dragon.nastroi <= 70 && dragon.nastroi >= 40 ||
                dragon.eat <= 70 && dragon.eat >= 40 ||
                dragon.son <= 70 && dragon.son >= 40 ||
                dragon.gigiena <= 70 && dragon.gigiena >= 40) {
            sdrag.changeBitmap(ndrag);

        }
        if (dragon.nastroi <= 1 && dragon.eat <= 1 && dragon.son <= 1 && dragon.gigiena <= 1) {
            sdrag.changeBitmap(plachetdrag); // плачет

        }
        if (dragon.nastroi <= 39 && dragon.nastroi >= 2 ||
                dragon.eat <= 39 && dragon.eat >= 2 ||
                dragon.son <= 39 && dragon.son >= 2 ||
                dragon.gigiena <= 39 && dragon.gigiena >= 2){
            sdrag.changeBitmap(eedrag);}
        if (dragon.nastroi > 70 && dragon.eat > 70 && dragon.son > 70 && dragon.gigiena > 70) {
            sdrag.changeBitmap(drag); // счастлив

        }
        if(dragon.sleeping) sdrag.changeBitmap(spitdrag);


        SharedPreferences.Editor prefEditor = Game.settings.edit();
        prefEditor.putString("nastroi", String.valueOf(dragon.nastroi));
        prefEditor.putString("son", String.valueOf(dragon.son));
        prefEditor.putString("eat", String.valueOf(dragon.eat));
        prefEditor.putString("gigiena", String.valueOf(dragon.gigiena));
        prefEditor.putString("lvl_ang", String.valueOf(dragon.lvl_ang));
        prefEditor.apply();

       if(dragon.level >= 2){
           if (dragon.nastroi <= 70 && dragon.nastroi >= 40 ||
                   dragon.eat <= 70 && dragon.eat >= 40 ||
                   dragon.son <= 70 && dragon.son >= 40 ||
                   dragon.gigiena <= 70 && dragon.gigiena >= 40){
                sdrag.changeBitmap(olddrag);

            }
           if (dragon.nastroi <= 1 && dragon.eat <= 1 && dragon.son <= 1 && dragon.gigiena <= 1) {
                sdrag.changeBitmap(olddrag2);

            }
           if (dragon.nastroi <= 39 && dragon.nastroi >= 2 ||
                   dragon.eat <= 39 && dragon.eat >= 2 ||
                   dragon.son <= 39 && dragon.son >= 2 ||
                   dragon.gigiena <= 39 && dragon.gigiena >= 2) {
                sdrag.changeBitmap(olddrag);

            }
            if (dragon.nastroi > 70 && dragon.eat > 70 && dragon.son > 70 && dragon.gigiena > 70) {
                sdrag.changeBitmap(olddrag1);

            }
            if(dragon.sleeping) sdrag.changeBitmap(olddrag3);
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

        if(dragon.level >= 2){
            canvas.drawBitmap(oldfonBitmap, new Rect(0, 0, oldfonBitmap.getWidth(), oldfonBitmap.getHeight()),
                    new Rect(0, 0, viewWidth, viewHeight), p);
        }
        sdrag.draw(canvas,viewWidth,viewHeight);
        sspat.draw(canvas,viewWidth,viewHeight);
        myt.draw(canvas,viewWidth,viewHeight);
        est.draw(canvas,viewWidth,viewHeight);
        igra.draw(canvas,viewWidth,viewHeight);

        p.setAntiAlias(true);
        p.setTextSize(45.0f);
        p.setColor(Color.YELLOW);
        int nn = (int) dragon.nastroi;
        canvas.drawText("Настроение:" + nn, 10, 400, p);
        p.setColor(Color.BLUE);
        int sn = (int) dragon.son;
        canvas.drawText("Бодрость:" + sn, 10, 100, p);
        p.setColor(Color.RED);
        int en = (int) dragon.eat;
        canvas.drawText("Сытость:" + en, 10, 300, p);
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
        Log.d("size", String.valueOf(w));
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (sdrag.click(event.getX(), event.getY())) {
            dragon.sleeping = false;
            if (dragon.nastroi < 100) {
                dragon.nastroi += 0.2;
                dragon.lvl_ang += 0.5;
                dragon.checkLv(dragon.lvl_ang);

            }
        }
        if (sspat.click(event.getX(), event.getY())) {
            if (dragon.son < 100) {
                dragon.son += 0.2;
                dragon.lvl_ang += 0.5;
                dragon.checkLv(dragon.lvl_ang);
                dragon.sleeping = true;
            }
        }
        if (myt.click(event.getX(), event.getY())) {
            dragon.sleeping = false;
            if (dragon.gigiena < 100) {
                dragon.gigiena += 0.2;
                dragon.lvl_ang += 0.5;
                dragon.checkLv(dragon.lvl_ang);

            }
        }
        if (est.click(event.getX(), event.getY())) {
            dragon.sleeping = false;
            if (dragon.eat < 100) {
                dragon.eat +=0.2;
                dragon.lvl_ang += 0.5;
                dragon.checkLv(dragon.lvl_ang);

            }
        }
        if (igra.click(event.getX(), event.getY())) {
            dragon.sleeping = false;
            if (dragon.nastroi < 100) {
                dragon.nastroi += 0.2;
                dragon.lvl_ang += 0.5;
                dragon.checkLv(dragon.lvl_ang);

            }
        }
        return true;
    }
}
