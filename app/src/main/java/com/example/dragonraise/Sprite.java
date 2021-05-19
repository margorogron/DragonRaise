package com.example.dragonraise;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;

public class Sprite {
    private double x;
    private double y;
    private double w;
    private double h;
    private double vh = 1716;
    private double vw = 1200;
    private Bitmap bitmap;

    public Sprite(double x, double y, Bitmap bitmap, double w, double h) {
        this.x = x;
        this.y = y;
        this.bitmap = bitmap;
        this.w = w;
        this.h = h;
    }
    public void changeBitmap(Bitmap bitmap){
        this.bitmap = bitmap;
    }
    public void draw (Canvas canvas, int viewWidth, int viewHeight) {
    //public void draw (Canvas canvas) {
        Paint p = new Paint();
        Rect source = new Rect(0, 0, (int)(bitmap.getWidth()), (int)(bitmap.getHeight()));
        Rect destination = new Rect((int)(x*vw/viewWidth), (int)(y*vh/viewHeight), (int)((x + w)*vw/viewWidth), (int)((y + h)*vh/viewHeight));
        canvas.drawBitmap(bitmap, source, destination,  p);
    }
    public boolean click(double cx, double cy){
        return (cx>=x)&&(cx<=x+w)&&(cy<=y+h)&&(cy>=y);
    }

}



