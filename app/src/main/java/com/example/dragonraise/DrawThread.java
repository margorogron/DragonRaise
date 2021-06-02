package com.example.dragonraise;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Компьютер on 04.04.2017.
 */

public class DrawThread extends Thread {

    private final SurfaceHolder surfaceHolder;

    private volatile boolean running = true;//флаг для остановки потока
    private final Paint backgroundPaint = new Paint();
    private final Paint paint = new Paint();

    private int viewWidth;
    private int viewHeight;
    private int towardPointX;
    private int towardPointY;
    private boolean m;
    private com.example.dragonraise.Dots dots;
    Bitmap fon;
    private Sprite knopkaa;
    Bitmap knopka;



    {

        backgroundPaint.setColor(Color.BLUE);
        backgroundPaint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
    }

    public DrawThread(Context context, SurfaceHolder surfaceHolder) {
        knopka= BitmapFactory.decodeResource(context.getResources(), R.drawable.knopka);
        fon = BitmapFactory.decodeResource(context.getResources(), R.drawable.miniigra);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.trening);
        Bitmap dragBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.treningclic);

        dots = new com.example.dragonraise.Dots(10, 10*2, 1000, 1000, bitmap, dragBitmap);
        this.surfaceHolder = surfaceHolder;
    }

    public void requestStop() {
        running = false;
    }

    public void onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            dots.startDrag((int) event.getX(), (int) event.getY());
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            dots.stopDrag();
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            dots.drag((int) event.getX(), (int) event.getY());
        }
    }


    @Override
    public void run() {
        while (running) {
            Canvas canvas = surfaceHolder.lockCanvas();
            if (canvas != null) {
                try {
                    canvas.drawBitmap(fon, new Rect(0, 0, fon.getWidth(), fon.getHeight()),
                            new Rect(0, 0, canvas.getWidth(), canvas.getHeight()), paint);

                    //canvas.drawText(String.valueOf(s.x), 10, 100, paint);
                    //canvas.drawText(String.valueOf(s.dragging), 10, 300, paint);
                    dots.draw(canvas, backgroundPaint);
                    if(!dots.check_intersect()){
                        int w = knopka.getWidth();
                        int h = knopka.getHeight();
                        knopkaa = new Sprite(40, 1250, knopka, w, h);
                        knopkaa.draw(canvas,canvas.getWidth(),canvas.getHeight());
                    }
                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }

            }
        }
    }
}

class Dot {
    public double x;
    public double y;
    public double w;
    public double h;
    public Bitmap bitmap;
    public Bitmap dragBitmap;


    public boolean dragging = false;

    public Dot(double x, double y, double w, double h, Bitmap bitmap, Bitmap dragBitmap) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.bitmap = bitmap;
        this.dragBitmap = dragBitmap;
    }

    public void draw(Canvas canvas, Paint paint) {

        if (dragging)
            canvas.drawBitmap(dragBitmap, new Rect(0, 0, dragBitmap.getWidth(), dragBitmap.getHeight()),
                    new Rect((int) (x - w / 2), (int) (y - h / 2), (int) (x + w / 2), (int) (y + h / 2)), paint);
        else
            canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()),
                    new Rect((int) (x - w / 2), (int) (y - h / 2), (int) (x + w / 2), (int) (y + h / 2)), paint);


        //canvas.drawBitmap(bitmap, (int)(x-w/2), (int)(y-h/2), paint);
    }

    public void drag(double x, double y) {
        //Log.d("action", String.valueOf(x));
        if (this.dragging) {
            this.x = x;
            this.y = y;
        }
    }

    public void startDrag(double x, double y) {
        if (this.x - this.w / 2 <= x && this.x + this.w / 2 >= x && this.y - this.h / 2 <= y && this.y + this.h / 2 >= y)
            this.dragging = true;
    }

    public void stopDrag() {
        this.dragging = false;
    }
}

class Dots {
    final Random random = new Random();
    public ArrayList<Dot> dots = new ArrayList<Dot>();
    public ArrayList<Pair<Integer, Integer>> connect = new ArrayList<>();

    public Dots(int n, int m, int w, int h, Bitmap b, Bitmap d) {
        for (int i = 0; i < n; i++)
            dots.add(new com.example.dragonraise.Dot(random.nextInt(w), random.nextInt(h), 100, 100, b, d));
        for (int i = 0; i < m; i++) {
            int r1 = random.nextInt(n);
            int r2 = random.nextInt(n);
            if (r1 != r2) {
                Pair<Integer, Integer> p = new Pair<>(r1, r2);
                connect.add(p);
            }
        }
    }

    private double area(com.example.dragonraise.Dot a, com.example.dragonraise.Dot b, com.example.dragonraise.Dot c) {
        return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
    }

    private boolean intersect_1(double a, double b, double c, double d) {
        double t;
        if (a > b) {
            t = a;
            a = b;
            b = t;
        }
        if (c > d) {
            t = c;
            c = d;
            d = t;
        }
        return Math.max(a, c) <= Math.min(b, d);
    }

    private boolean intersect(com.example.dragonraise.Dot a, com.example.dragonraise.Dot b, com.example.dragonraise.Dot c, com.example.dragonraise.Dot d) {
        return intersect_1(a.x, b.x, c.x, d.x)
                && intersect_1(a.y, b.y, c.y, d.y)
                && area(a, b, c) * area(a, b, d) <= 0
                && area(c, d, a) * area(c, d, b) <= 0;
    }

    public boolean check_intersect() {
        boolean s = false;
        for(int i=0; i<connect.size(); i++)
            for (int j=i+1; j<connect.size(); j++){
                if(connect.get(i).first == connect.get(j).first ||
                        connect.get(i).first == connect.get(j).second ||
                        connect.get(i).second == connect.get(j).first ||
                        connect.get(i).second == connect.get(j).second)
                    continue;
                s = s || intersect(
                        dots.get(connect.get(i).first),
                        dots.get(connect.get(i).second),
                        dots.get(connect.get(j).first),
                        dots.get(connect.get(j).second));
            }
        return s;
    }

    public void draw(Canvas canvas, Paint paint) {
        for (com.example.dragonraise.Dot d : dots) d.draw(canvas, paint);
        for (Pair<Integer, Integer> p : connect) {
            com.example.dragonraise.Dot d1 = dots.get(p.first);
            com.example.dragonraise.Dot d2 = dots.get(p.second);
            Paint lines = new Paint();
            if (!check_intersect()) {
                lines.setColor(Color.YELLOW);


            }
            else
                lines.setColor(Color.GREEN);
            lines.setStrokeWidth(5);
            lines.setStrokeJoin(Paint.Join.MITER);
            canvas.drawLine((int) d1.x, (int) d1.y, (int) d2.x, (int) d2.y, lines);
        }
    }

    public void drag(int x, int y) {
        for (com.example.dragonraise.Dot d : dots) {
            d.drag(x, y);
        }

    }

    public void startDrag(int x, int y) {
        for (com.example.dragonraise.Dot d : dots) {
            d.startDrag(x, y);
            if (d.dragging) break;
        }
    }

    public void stopDrag() {
        for (com.example.dragonraise.Dot d : dots)
            d.stopDrag();
    }
}
