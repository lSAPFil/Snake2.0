package com.example.snake20;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Apple {
    private Bitmap bm;
    private int x,y;
    private Rect r;
    public Apple(Bitmap bm,int x, int y){
        this.bm=bm;
        this.x=x;
        this.y=y;
    }

    public Bitmap getBm() {
        return bm;
    }

    public void setBm(Bitmap bm) {
        this.bm = bm;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rect getR() {
        return new Rect(this.x,this.y, this.x+GameView.sizeOfMap, this.y+GameView.sizeOfMap);
    }

    public void setR(Rect r) {
        this.r = r;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bm,x,y,null);
    }

    public void reset(int nx, int ny) {
        this.x=nx;
        this.y=ny;
    }
}
