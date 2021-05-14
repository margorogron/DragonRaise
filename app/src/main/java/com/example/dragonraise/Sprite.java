package com.example.dragonraise;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
public class Sprite {
    private double x;
    private double y;
    private Bitmap bitmap;

    public Sprite(double x, double y, Bitmap bitmap) {
        this.x = x;
        this.y = y;
        this.bitmap = bitmap;
    }
    public void draw (Canvas canvas) {
        Paint p = new Paint();
        Rect source = new Rect(0, 0, (int)(bitmap.getWidth()), (int)(bitmap.getHeight()));
        Rect destination = new Rect((int)x, (int)y, (int)(x + bitmap.getWidth()), (int)(y + bitmap.getHeight()));
        canvas.drawBitmap(bitmap, source, destination,  p);
    }
}



