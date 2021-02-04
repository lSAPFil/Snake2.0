package com.example.snake20;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;

public class Snake {
    private boolean move_left, move_right, move_top, move_bottom;
    private Bitmap bm, bm_heard_up,bm_heard_down,bm_heard_right, bm_heard_left, bm_body_vertical,
    bm_Body_horisontal,bm_body_top_right, bm_body_top_left, bm_body_buttom_right, bm_body_buttom_left,
    bm_tail_right, bm_tail_left, bm_tail_up, bm_tail_down;
    private static int x, y, lenght;
    private ArrayList<PartSnake> arrPartSnake=new ArrayList<>();

    public Snake(Bitmap bm, int x, int y, int lenght){
        this.bm=bm;
        this.x=x;
        this.y=y;
        this.lenght=lenght;
        bm_body_buttom_left=Bitmap.createBitmap(bm,0,0,GameView.sizeOfMap,GameView.sizeOfMap);
        bm_body_buttom_right=Bitmap.createBitmap(bm,1*GameView.sizeOfMap,0,GameView.sizeOfMap,GameView.sizeOfMap);
        bm_Body_horisontal=Bitmap.createBitmap(bm,2*GameView.sizeOfMap,0,GameView.sizeOfMap,GameView.sizeOfMap);
        bm_body_top_left=Bitmap.createBitmap(bm,3*GameView.sizeOfMap,0,GameView.sizeOfMap,GameView.sizeOfMap);
        bm_body_top_right=Bitmap.createBitmap(bm,4*GameView.sizeOfMap,0,GameView.sizeOfMap,GameView.sizeOfMap);
        bm_body_vertical=Bitmap.createBitmap(bm,5*GameView.sizeOfMap,0,GameView.sizeOfMap,GameView.sizeOfMap);
        bm_heard_down=Bitmap.createBitmap(bm,6*GameView.sizeOfMap,0,GameView.sizeOfMap,GameView.sizeOfMap);
        bm_heard_left=Bitmap.createBitmap(bm,7*GameView.sizeOfMap,0,GameView.sizeOfMap,GameView.sizeOfMap);
        bm_heard_right=Bitmap.createBitmap(bm,8*GameView.sizeOfMap,0,GameView.sizeOfMap,GameView.sizeOfMap);
        bm_heard_up=Bitmap.createBitmap(bm,9*GameView.sizeOfMap,0,GameView.sizeOfMap,GameView.sizeOfMap);
        bm_tail_up=Bitmap.createBitmap(bm,10*GameView.sizeOfMap,0,GameView.sizeOfMap,GameView.sizeOfMap);
        bm_tail_right=Bitmap.createBitmap(bm,11*GameView.sizeOfMap,0,GameView.sizeOfMap,GameView.sizeOfMap);
        bm_tail_left=Bitmap.createBitmap(bm,12*GameView.sizeOfMap,0,GameView.sizeOfMap,GameView.sizeOfMap);
        bm_tail_down=Bitmap.createBitmap(bm,13*GameView.sizeOfMap,0,GameView.sizeOfMap,GameView.sizeOfMap);

        arrPartSnake.add(new PartSnake(bm_heard_right,x,y));
        for(int i=1;i<(lenght-1);i++){
            arrPartSnake.add(new PartSnake(bm_Body_horisontal,arrPartSnake.get(i-1).getX()-GameView.sizeOfMap,y));
        }
        arrPartSnake.add(new PartSnake(bm_tail_right,arrPartSnake.get(lenght-2).getX()-GameView.sizeOfMap,y));
        setMove_right(true);
    }

    public void update(){
        for (int i=lenght - 1; i>0; i--){
            arrPartSnake.get(i).setX(arrPartSnake.get(i-1).getX());
            arrPartSnake.get(i).setY(arrPartSnake.get(i-1).getY());
        }
        if(move_right){
            arrPartSnake.get(0).setX(arrPartSnake.get(0).getX()+GameView.sizeOfMap);
            arrPartSnake.get(0).setBm(bm_heard_right);
        }
        else if(move_left){
            arrPartSnake.get(0).setX(arrPartSnake.get(0).getX()-GameView.sizeOfMap);
            arrPartSnake.get(0).setBm(bm_heard_left);
        }
        else if(move_top){
            arrPartSnake.get(0).setY(arrPartSnake.get(0).getY()-GameView.sizeOfMap);
            arrPartSnake.get(0).setBm(bm_heard_up);
        }
        else if(move_bottom){
            arrPartSnake.get(0).setY(arrPartSnake.get(0).getY()+GameView.sizeOfMap);
            arrPartSnake.get(0).setBm(bm_heard_down);
        }
        for(int i=1;i<lenght-1;i++){
            if(arrPartSnake.get(i).getrLeft().intersect(arrPartSnake.get(i+1).getrBody())
                    &&arrPartSnake.get(i).getrButtom().intersect(arrPartSnake.get(i-1).getrBody())
                    ||arrPartSnake.get(i).getrLeft().intersect(arrPartSnake.get(i-1).getrBody())
                    &&arrPartSnake.get(i).getrButtom().intersect(arrPartSnake.get(i+1).getrBody())) {
                arrPartSnake.get(i).setBm(bm_body_buttom_left);

            }else if(arrPartSnake.get(i).getrRight().intersect(arrPartSnake.get(i+1).getrBody())
                    &&arrPartSnake.get(i).getrButtom().intersect(arrPartSnake.get(i-1).getrBody())
                    ||arrPartSnake.get(i).getrRight().intersect(arrPartSnake.get(i-1).getrBody())
                    &&arrPartSnake.get(i).getrButtom().intersect(arrPartSnake.get(i+1).getrBody())){
//                Log.d("ANIMATION", "update: 1");
               arrPartSnake.get(i).setBm(bm_body_buttom_right);

            }else if(arrPartSnake.get(i).getrLeft().intersect(arrPartSnake.get(i+1).getrBody())
                    &&arrPartSnake.get(i).getrTop().intersect(arrPartSnake.get(i-1).getrBody())
                    ||arrPartSnake.get(i).getrLeft().intersect(arrPartSnake.get(i-1).getrBody())
                    &&arrPartSnake.get(i).getrTop().intersect(arrPartSnake.get(i+1).getrBody())){
                  arrPartSnake.get(i).setBm(bm_body_top_left);

            }else if(arrPartSnake.get(i).getrRight().intersect(arrPartSnake.get(i+1).getrBody())
                    &&arrPartSnake.get(i).getrTop().intersect(arrPartSnake.get(i-1).getrBody())
                    ||arrPartSnake.get(i).getrRight().intersect(arrPartSnake.get(i-1).getrBody())
                    &&arrPartSnake.get(i).getrTop().intersect(arrPartSnake.get(i+1).getrBody())){
                arrPartSnake.get(i).setBm(bm_body_top_right);

            }else if(arrPartSnake.get(i).getrTop().intersect(arrPartSnake.get(i+1).getrBody())
                    &&arrPartSnake.get(i).getrButtom().intersect(arrPartSnake.get(i-1).getrBody())
                    ||arrPartSnake.get(i).getrTop().intersect(arrPartSnake.get(i-1).getrBody())
                    &&arrPartSnake.get(i).getrButtom().intersect(arrPartSnake.get(i+1).getrBody())){
                arrPartSnake.get(i).setBm(bm_body_vertical);

            }else if(arrPartSnake.get(i).getrLeft().intersect(arrPartSnake.get(i+1).getrBody())
                    &&arrPartSnake.get(i).getrRight().intersect(arrPartSnake.get(i-1).getrBody())
                    ||arrPartSnake.get(i).getrLeft().intersect(arrPartSnake.get(i-1).getrBody())
                    &&arrPartSnake.get(i).getrRight().intersect(arrPartSnake.get(i+1).getrBody())){
                arrPartSnake.get(i).setBm(bm_Body_horisontal);
            }
        }
        if(arrPartSnake.get(lenght-1).getrRight().intersect(arrPartSnake.get(lenght-2).getrBody())){
              arrPartSnake.get(lenght-1).setBm(bm_tail_right);
        }else if(arrPartSnake.get(lenght-1).getrLeft().intersect(arrPartSnake.get(lenght-2).getrBody())){
              arrPartSnake.get(lenght-1).setBm(bm_tail_left);
        }else if(arrPartSnake.get(lenght-1).getrTop().intersect(arrPartSnake.get(lenght-2).getrBody())){
              arrPartSnake.get(lenght-1).setBm(bm_tail_up);
        }else if(arrPartSnake.get(lenght-1).getrButtom().intersect(arrPartSnake.get(lenght-2).getrBody())){
              arrPartSnake.get(lenght-1).setBm(bm_tail_down);
        }
    }


    public void draw(Canvas canvas){
        for(int i=0; i<lenght;i++){
            canvas.drawBitmap(arrPartSnake.get(i).getBm(),arrPartSnake.get(i).getX(),arrPartSnake.get(i).getY(),null);
        }
    }

    public Bitmap getBm() {
        return bm;
    }

    public void setBm(Bitmap bm) {
        this.bm = bm;
    }

    public Bitmap getBm_heard_up() {
        return bm_heard_up;
    }

    public void setBm_heard_up(Bitmap bm_heard_up) {
        this.bm_heard_up = bm_heard_up;
    }

    public Bitmap getBm_heard_down() {
        return bm_heard_down;
    }

    public void setBm_heard_down(Bitmap bm_heard_down) {
        this.bm_heard_down = bm_heard_down;
    }

    public Bitmap getBm_heard_right() {
        return bm_heard_right;
    }

    public void setBm_heard_right(Bitmap bm_heard_right) {
        this.bm_heard_right = bm_heard_right;
    }

    public Bitmap getBm_heard_left() {
        return bm_heard_left;
    }

    public void setBm_heard_left(Bitmap bm_heard_left) {
        this.bm_heard_left = bm_heard_left;
    }

    public Bitmap getBm_body_vertical() {
        return bm_body_vertical;
    }

    public void setBm_body_vertical(Bitmap bm_body_vertical) {
        this.bm_body_vertical = bm_body_vertical;
    }

    public Bitmap getBm_Body_horisontal() {
        return bm_Body_horisontal;
    }

    public void setBm_Body_horisontal(Bitmap bm_Body_horisontal) {
        this.bm_Body_horisontal = bm_Body_horisontal;
    }

    public Bitmap getBm_body_top_right() {
        return bm_body_top_right;
    }

    public void setBm_body_top_right(Bitmap bm_body_top_right) {
        this.bm_body_top_right = bm_body_top_right;
    }

    public Bitmap getBm_body_top_left() {
        return bm_body_top_left;
    }

    public void setBm_body_top_left(Bitmap bm_body_top_left) {
        this.bm_body_top_left = bm_body_top_left;
    }

    public Bitmap getBm_body_buttom_right() {
        return bm_body_buttom_right;
    }

    public void setBm_body_buttom_right(Bitmap bm_body_buttom_right) {
        this.bm_body_buttom_right = bm_body_buttom_right;
    }

    public Bitmap getBm_body_buttom_left() {
        return bm_body_buttom_left;
    }

    public void setBm_body_buttom_left(Bitmap bm_body_buttom_left) {
        this.bm_body_buttom_left = bm_body_buttom_left;
    }

    public Bitmap getBm_tail_right() {
        return bm_tail_right;
    }

    public void setBm_tail_right(Bitmap bm_tail_right) {
        this.bm_tail_right = bm_tail_right;
    }

    public Bitmap getBm_tail_left() {
        return bm_tail_left;
    }

    public void setBm_tail_left(Bitmap bm_tail_left) {
        this.bm_tail_left = bm_tail_left;
    }

    public Bitmap getBm_tail_up() {
        return bm_tail_up;
    }

    public void setBm_tail_up(Bitmap bm_tail_up) {
        this.bm_tail_up = bm_tail_up;
    }

    public Bitmap getBm_tail_down() {
        return bm_tail_down;
    }

    public void setBm_tail_down(Bitmap bm_tail_down) {
        this.bm_tail_down = bm_tail_down;
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

    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    public ArrayList<PartSnake> getArrPartSnake() {
        return arrPartSnake;
    }

    public void setArrPartSnake(ArrayList<PartSnake> arrPartSnake) {
        this.arrPartSnake = arrPartSnake;
    }



    public boolean isMove_left() {
        return move_left;
    }

    public void setMove_left(boolean move_left) {
        s();
        this.move_left = move_left;
    }

    public boolean isMove_right() {
        return move_right;
    }

    public void setMove_right(boolean move_right) {
        s();
        this.move_right = move_right;
    }

    public boolean isMove_top() {
        return move_top;
    }

    public void setMove_top(boolean move_top) {
        s();
        this.move_top = move_top;
    }

    public boolean isMove_bottom() {
        return move_bottom;
    }

    public void setMove_bottom(boolean move_bottom) {
        s();
        this.move_bottom = move_bottom;
    }

    public void s(){
        this.move_left=false;
        this.move_right=false;
        this.move_bottom=false;
        this.move_top= false;
    }

    public void addPart() {
        PartSnake p = this.arrPartSnake.get(lenght-1);
        this.lenght+=1;
        if(p.getBm()==bm_tail_right){
            this.arrPartSnake.add(new PartSnake(bm_tail_right,p.getX()-GameView.sizeOfMap,p.getY()));
        }else if(p.getBm()==bm_tail_left){
            this.arrPartSnake.add(new PartSnake(bm_tail_left,p.getX()+GameView.sizeOfMap,p.getY()));
        }else if(p.getBm()==bm_tail_up){
            this.arrPartSnake.add(new PartSnake(bm_tail_up,p.getX(),p.getY()+GameView.sizeOfMap));
        }else if(p.getBm()==bm_tail_down){
            this.arrPartSnake.add(new PartSnake(bm_tail_down,p.getX(),p.getY()-GameView.sizeOfMap));
        }
    }
}

