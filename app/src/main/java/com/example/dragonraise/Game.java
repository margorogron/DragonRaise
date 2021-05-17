package com.example.dragonraise;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
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
    Bitmap fonBitmap;
    private Sprite sdrag;
    Bitmap drag;
    private Sprite_spat sspat;
    Bitmap spatt;
    private Sprite_myt myt;
    Bitmap mytt;
    private Sprite_est est;
    Bitmap estt;
    private Sprite_igra igra;
    Bitmap igratt;

    private int viewWidth;
    private int viewHeight;
    private final int timerInterval = 3;



    public Dragon dragon = new Dragon();



    public GameView(Context context) {

        super(context);
        fonBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fon_igry);
        drag = BitmapFactory.decodeResource(getResources(), R.drawable.drakon);
        spatt = BitmapFactory.decodeResource(getResources(), R.drawable.spat);
        mytt = BitmapFactory.decodeResource(getResources(), R.drawable.myt);
        igratt= BitmapFactory.decodeResource(getResources(), R.drawable.igrat);
        estt = BitmapFactory.decodeResource(getResources(), R.drawable.est);

        int w = drag.getWidth()/5;
        int h = drag.getHeight()/3;

        int we = spatt.getWidth()/5;
        int he = spatt.getHeight()/3;

        int wei = mytt.getWidth()/5;
        int hei = mytt.getHeight()/3;

        int weig = estt.getWidth()/5;
        int heig = estt.getHeight()/3;

        int weigh = igratt.getWidth()/5;
        int heigh = igratt.getHeight()/3;

        Rect firstFrame = new Rect(0, 0, w, h);

        sdrag = new Sprite(450,900, drag, w*5, h*3);
        sspat = new Sprite_spat(100,1550, spatt, we*5, he*3);
        myt = new Sprite_myt(320,1500, mytt, wei*5, hei*3);
        est = new Sprite_est(546,1500, estt, weig*5, heig*3);
        igra = new Sprite_igra(732,1500, igratt, weigh*5, heigh*3);
        Timer t = new Timer();
        t.start();

    }

    protected void update() {

       //счет настроения
        if (dragon.x == 0){
            dragon.nastroi--;
            dragon.x = 1000;
        }
        else{
            dragon.x--;
        }
            //счет усталости
        if (dragon.l == 0){
            dragon.son--;
            dragon.l = 850;
        }
            else{
            dragon.l--;
        }
        //счет аппетита
          if (dragon.k == 0){
            dragon.eat--;
            dragon.k = 600;
        }
                else{
            dragon.k--;
        }
        //счет гигиены
         if (dragon.h == 0){
            dragon.gigiena--;
            dragon.h = 700;
        }
                    else{
            dragon.h--;
        }
                    if(dragon.nastroi == 0) {

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
        canvas.drawText("Настроение:" + nn, 10, 100, p);
        p.setColor(Color.BLUE);
        int sn = (int) dragon.son;
        canvas.drawText("Усталость:" + sn, 10, 200, p);
        p.setColor(Color.RED);
        int en = (int) dragon.eat;
        canvas.drawText("Голод:" + en, 10, 300, p);
        p.setColor(Color.WHITE);
        int gn = (int) dragon.gigiena;
        canvas.drawText("Гигиена:" + gn , 10, 400, p);


        Paint paint = new Paint();
        paint.setColor(Color.WHITE); // установим белый цвет
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.FILL); // заливаем
        paint.setAntiAlias(true);
        final RectF oval = new RectF();
        oval.set(870, 80 , 970,180);
        canvas.drawArc(oval, 0, 360, true, paint);


        paint.setStyle(Paint.Style.STROKE);
        oval.set(830, 50 , 990,210);
        canvas.drawArc(oval, 0, dragon.lvl_ang, true, paint);

        paint.setStyle(Paint.Style.STROKE);
        p.setColor(Color.BLACK);
        p.setTextSize(65.0f);
        canvas.drawText(""+dragon.level,900, 150, p );


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        viewWidth = w;
        viewHeight = h;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (sdrag.click(event.getX(),event.getY()))
        if(dragon.nastroi < 100){
            dragon.nastroi += 0.5;
            dragon.lvl_ang += 1;
            dragon.checkLv(dragon.lvl_ang);
        }
        if(sspat.click_spat(event.getX(),event.getY())){
            if(dragon.son < 100){
                dragon.son += 0.5;
                dragon.lvl_ang += 1;
                dragon.checkLv(dragon.lvl_ang);
            }}
        if(myt.click_myt(event.getX(),event.getY())){
            if(dragon.gigiena < 100){
                dragon.gigiena += 0.5;
                dragon.lvl_ang += 1;
                dragon.checkLv(dragon.lvl_ang);
            }}
        if(est.click_est(event.getX(),event.getY())){
            if(dragon.eat < 100){
                dragon.eat += 0.5;
                dragon.lvl_ang += 1;
                dragon.checkLv(dragon.lvl_ang);
            }}

        if(igra.click_igra(event.getX(),event.getY())){
            if(dragon.nastroi < 100){
                dragon.nastroi += 0.5;
                dragon.lvl_ang += 1;
                dragon.checkLv(dragon.lvl_ang);
            }}

        return true;
    }
}
