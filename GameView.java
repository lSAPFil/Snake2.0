package com.example.snake20;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

public class GameView extends View {
    private Bitmap bmGrass1,bmGrass2, bmSnake, bmApple,bmWall;
    private int h=21, w=12;
    public static int sizeOfMap=75*Constanta.SCREEN_WIDH/1080;
    private ArrayList<Grass> arrGrass=new ArrayList<>();
    private Snake snake;
    private boolean move=false;
    private float mx,my;
    private Handler handler;
    private Runnable r;
    public MainActivity main;
    private Apple apple;
    private boolean dead=false;
    public int valueApple=0;
    private Timer timer;

//    public int valueRecords;
    public GameView(Context context, @Nullable AttributeSet attrs){ /////////Данный класс отрисовывается только один раз. Он не повторяется (на случай если ты будешь сомневаться)
        super(context, attrs);
        bmGrass1= BitmapFactory.decodeResource(this.getResources(),R.drawable.grass);
        bmGrass1=Bitmap.createScaledBitmap(bmGrass1,sizeOfMap,sizeOfMap,true);
        bmGrass2= BitmapFactory.decodeResource(this.getResources(),R.drawable.grass03);
        bmGrass2=Bitmap.createScaledBitmap(bmGrass2,sizeOfMap,sizeOfMap,true);
        bmSnake= BitmapFactory.decodeResource(this.getResources(),R.drawable.snake1);
        bmSnake=Bitmap.createScaledBitmap(bmSnake,14*sizeOfMap,sizeOfMap,true);
        bmApple= BitmapFactory.decodeResource(this.getResources(),R.drawable.apple);
        bmApple=Bitmap.createScaledBitmap(bmApple,sizeOfMap,sizeOfMap,true);
        bmWall=BitmapFactory.decodeResource(this.getResources(),R.drawable.wall);
        bmWall=Bitmap.createScaledBitmap(bmWall,sizeOfMap,sizeOfMap,true);
        for(int i=0;i<(h+1);i++)
        {
            for(int j=0;j<(w+1);j++)
            {
                if((i+j)%2!=0){
                    arrGrass.add(new Grass(bmGrass1,j*sizeOfMap+Constanta.SCREEN_WIDH/2-(w/2)*sizeOfMap,
                            i*sizeOfMap+100*Constanta.SCREEEN_HEIGHT/1920, sizeOfMap,sizeOfMap));
                }else{
                    arrGrass.add(new Grass(bmGrass2, j*sizeOfMap+Constanta.SCREEN_WIDH/2-(w/2)*sizeOfMap,
                            i*sizeOfMap+100*Constanta.SCREEEN_HEIGHT/1920, sizeOfMap, sizeOfMap));

                }
            }
        }
        snake=new Snake(bmSnake,arrGrass.get(4).getX(),arrGrass.get(70).getY(),4);///285 285 (границы (на 286 прога вылетает по причине выхода за границы. Значт буду килить змейку на 0 и 285 клетке))
        apple=new Apple(bmApple,arrGrass.get(randomApple()[0]).getX(),arrGrass.get(randomApple()[1]).getX());
        handler = new Handler();
        r=new Runnable() {
            @Override
            public void run() {
                //handler.postDelayed(this,1000);
                invalidate();
            }
        };
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
         int a=event.getActionMasked();
         switch (a){
             case MotionEvent.ACTION_MOVE:{
                 if(move==false){
                     mx=event.getX();
                     my=event.getY();
                     move=true;
                 }else{
                     if(mx-event.getX()>100*Constanta.SCREEN_WIDH/1080&&!snake.isMove_right()){
                         mx=event.getX();
                         my=event.getY();
                         snake.setMove_left(true);
                     }else if(event.getX()-mx>100*Constanta.SCREEN_WIDH/1080&&!snake.isMove_left()){
                         mx=event.getX();
                         my=event.getY();
                         snake.setMove_right(true);
                     }else if(my-event.getY()>100*Constanta.SCREEN_WIDH/1080&&!snake.isMove_bottom()){
                         mx=event.getX();
                         my=event.getY();
                         snake.setMove_top(true);
                     }else if(event.getY()-my>100*Constanta.SCREEN_WIDH/1080&&!snake.isMove_top()){
                         mx=event.getX();
                         my=event.getY();
                         snake.setMove_bottom(true);
                     }

                 }
                 break;
             }
             case MotionEvent.ACTION_UP:{
                 mx=0;
                 my=0;
                 move=false;
                 break;
             }
         }
        return true;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        timer = new Timer();
        canvas.drawColor(0xFF1B007C);
        for (int i=0; i<arrGrass.size();i++){
            canvas.drawBitmap(arrGrass.get(i).getBm(),arrGrass.get(i).getX(),arrGrass.get(i).getY(), null);
        }

        snake.update();
        snake.draw(canvas);
        apple.draw(canvas);

        if(snake.getArrPartSnake().get(0).getrBody().intersect(apple.getR())){
            randomApple();
            this.valueApple++;
            apple.reset(arrGrass.get(randomApple()[0]).getX(),arrGrass.get(randomApple()[1]).getY());
            main.appleValue.setText(Integer.toString(this.valueApple));
            snake.addPart();
            Log.d("start", "Яблоко скушано");

        }else {
            int i=0;
            while (true){
                if(handler.postDelayed(r,2000)){
                    i++;
                    Log.d("start", "Счет передвижений змейки");
                    if(i%5==0){
                        randomApple();
                        apple.reset(arrGrass.get(randomApple()[0]).getX(),arrGrass.get(randomApple()[1]).getY());
                        //handler.postDelayed(r,100);
                        Log.d("start", "Рандом яблока");
                    break;
                   }

                }
            }

        }
        handler.postDelayed(r,2000);



//            {
//            Thread thread = new Thread();
//            thread.start();
//            try {
//                Thread.sleep(1000);
//                apple.reset(arrGrass.get(randomApple()[0]).getX(),arrGrass.get(randomApple()[1]).getY());
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//
//            thread.interrupt();
//
//        }


//            {
//            int i=0;
//            while (handler.postDelayed(r,100)){
//                i++;
//                Log.d("start", "Счет передвижений змейки");
//                if(i%1000==0){
//                    apple.reset(arrGrass.get(randomApple()[0]).getX(),arrGrass.get(randomApple()[1]).getY());
//                    //handler.postDelayed(r,100);
//                    Log.d("start", "Рандом яблока");
//                    break;
//                }
//            }
//        }





//            while(!Thread.currentThread().isInterrupted()) {
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
//                }
//            }


//            for (int i=0;i<=100000000;i++){
//                if(i==100000000){
//                    apple.reset(arrGrass.get(randomApple()[0]).getX(),arrGrass.get(randomApple()[1]).getY());
//                }
//            }

//            timer=new Timer();
//            timer.schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    apple.reset(arrGrass.get(randomApple()[0]).getX(),arrGrass.get(randomApple()[1]).getY());
//                }
//            },500,7000);

//            TimerTask task=new TimerTask() {
//                @Override
//                public void run() {
//                    apple.reset(arrGrass.get(randomApple()[0]).getX(),arrGrass.get(randomApple()[1]).getY());
//                }
//            };
//            timer.schedule(task,0,5000);
//


//            runnable=new Runnable() {
//                @Override
//                public void run() {
//                    invalidate();
//                }
//            };
//            while (true){
//                //Thread.sleep(3000);
//                apple.reset(arrGrass.get(randomApple()[0]).getX(),arrGrass.get(randomApple()[1]).getY());
//                handler.postDelayed(runnable,1000);
//            }



        for (int i=1; i!=snake.getArrPartSnake().size();i++){
            if (snake.getArrPartSnake().get(0).getrBody().intersect(snake.getArrPartSnake().get(i).getrBody())){/////
                dead=true;
                Log.d("start", "Фор на столкновение с телом");
                snake.setMove_top(false);
                snake.setMove_right(false);
                snake.setMove_left(false);
                snake.setMove_bottom(false);
            }
        }

        for(int i=0;i<=21;i++)
        {
            if(i<=12 &&snake.getArrPartSnake().get(0).getrBody().intersect(arrGrass.get(i).getR())){
                dead=true;
                Log.d("start", "Фор на столкновение со стеной ВЕРХ");
                snake.setMove_top(false);
                snake.setMove_right(false);
                snake.setMove_left(false);
                snake.setMove_bottom(false);
            }
            if(snake.getArrPartSnake().get(0).getrBody().intersect(arrGrass.get(i*13).getR())){
                dead=true;
                Log.d("start", "Фор на столкновение со стеной ЛЕВО");/// tupoy idiot
                snake.setMove_top(false);
                snake.setMove_right(false);
                snake.setMove_left(false);
                snake.setMove_bottom(false);
            }
            if(i>=1 && snake.getArrPartSnake().get(0).getrBody().intersect(arrGrass.get(i*13-1).getR())){
                dead=true;
                Log.d("start", "Фор на столкновение со стеной ПРАВО");
                snake.setMove_top(false);
                snake.setMove_right(false);
                snake.setMove_left(false);
                snake.setMove_bottom(false);
            }
            if(i<=12 && snake.getArrPartSnake().get(0).getrBody().intersect(arrGrass.get(272+i).getR())){
                dead=true;
                Log.d("start", "Фор на столкновение со стеной НИЗ");
                snake.setMove_top(false);
                snake.setMove_right(false);
                snake.setMove_left(false);
                snake.setMove_bottom(false);
            }
        }


        if (dead==true) {
            Log.d("start", "Змейка умерла");
            dead=false;////delete (Написала для того, чтобы дебагер потом не спамил мне каждый шаг напоминанием о том, что она уже сдохла)
        }
    }

    public <valueApple> int[] randomApple(){
        int []xy=new int[2];
        Random r = new Random();
        xy[0]=r.nextInt(arrGrass.size()-1);
        xy[1]=r.nextInt(arrGrass.size()-1);
        Rect rect=new Rect(arrGrass.get(xy[0]).getX(), arrGrass.get(xy[1]).getY(),arrGrass.get(xy[0]).getX()+sizeOfMap,arrGrass.get(xy[1]).getY()+sizeOfMap);
        boolean check = true;
        while (check){
            check=false;
            for (int i=0;i<snake.getArrPartSnake().size();i++){
                if(rect.intersect(snake.getArrPartSnake().get(i).getrBody())){
                    check=true;
                    xy[0]=r.nextInt(arrGrass.size()-1);
                    xy[1]=r.nextInt(arrGrass.size()-1);
                    rect=new Rect(arrGrass.get(xy[0]).getX(), arrGrass.get(xy[1]).getY(),arrGrass.get(xy[0]).getX()+sizeOfMap,arrGrass.get(xy[1]).getY()+sizeOfMap);;

                }
            }

        }
        return xy;
    }


}
