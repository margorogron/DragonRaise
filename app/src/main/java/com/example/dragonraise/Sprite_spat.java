package com.example.dragonraise;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Sprite_spat  {
        private double x;
        private double y;
        private double w;
        private double h;
        private Bitmap bitmap;

        public Sprite_spat(double x, double y, Bitmap bitmap, double w, double h) {
            this.x = x;
            this.y = y;
            this.bitmap = bitmap;
            this.w = w;
            this.h = h;
        }
        public void draw (Canvas canvas) {
            Paint p = new Paint();
            Rect source = new Rect(0, 0, (int)(bitmap.getWidth()), (int)(bitmap.getHeight()));
            Rect destination = new Rect((int)x, (int)y, (int)(x + w), (int)(y + h));
            canvas.drawBitmap(bitmap, source, destination,  p);
        }
        public boolean click_spat(double cx, double cy){
            return (cx>=x)&&(cx<=x+w)&&(cy<=y+h)&&(cy>=y);
        }
}
