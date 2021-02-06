package com.example.snake20;

import android.graphics.Bitmap;
import android.graphics.Rect;

public class Grass {
    private Bitmap bm;
    private int x, y, width, height;
    private Rect r;

    public Rect getR() {
        return new Rect(this.x,this.y, this.x+GameView.sizeOfMap, this.y+GameView.sizeOfMap);
    }

    public void setR(Rect r) {
        this.r = r;
    }

    public Grass(Bitmap bm, int x, int y, int width, int height)
    {
        this.bm=bm;
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
    }
    public Bitmap getBm(){
        return bm;
    }

    public void setBm(Bitmap bm){
        this.bm=bm;
    }

    public int getX(){
        return x;
    }

    public void setX(int x){
        this.x=x;
    }

    public int getY(){
        return y;
    }

    public void setY(int y){
        this.y=y;
    }

    public int getWidth(){
        return width;
    }

    public void setWidth(int width) {
        this.width=width;
    }

    public int getHeight(){
        return height;
    }

}
