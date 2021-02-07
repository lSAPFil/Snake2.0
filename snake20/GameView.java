package com.example.snake20;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;

public class GameView extends View implements OnClickListener {

    private Bitmap bmGrass1,bmGrass2, bmSnake, bmApple,bmWall;
    private int h=21, w=12;
    public static int sizeOfMap=75*Constanta.SCREEN_WIDH/1080;
    private ArrayList<Grass> arrGrass=new ArrayList<>();
    private Snake snake;
    private boolean move=false;
    private float mx,my;
    private Handler handler;
    private Runnable r ;
    public MainActivity main;
    private Apple apple;
    private boolean dead=false;
    public int valueApple=0;
    public boolean guide2=false;
    public RelativeLayout imaga;
    public ImageView image1;
    public ImageView image2;
    public ImageView image3;
    public ImageView image4;
    public RelativeLayout imaga1;
    public long start = System.currentTimeMillis();
    private SoundPool sounds;
    private int die;
    private int eat;
    private int music;


    public GameView(Context context, @Nullable AttributeSet attrs){


        super(context, attrs);

        sounds = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        die = sounds.load(context, R.raw.die, 1);
        eat = sounds.load(context, R.raw.eat, 1);
        music = sounds.load(context, R.raw.music, 1);


        bmGrass1= BitmapFactory.decodeResource(this.getResources(),R.drawable.grass);
        bmGrass1=Bitmap.createScaledBitmap(bmGrass1,sizeOfMap,sizeOfMap,true);
        bmGrass2= BitmapFactory.decodeResource(this.getResources(),R.drawable.grass03);
        bmGrass2=Bitmap.createScaledBitmap(bmGrass2,sizeOfMap,sizeOfMap,true);
        bmSnake= BitmapFactory.decodeResource(this.getResources(),R.drawable.snake1);
        bmSnake=Bitmap.createScaledBitmap(bmSnake,14*sizeOfMap,sizeOfMap,true);
        bmApple= BitmapFactory.decodeResource(this.getResources(),R.drawable.apple);
        bmApple=Bitmap.createScaledBitmap(bmApple,sizeOfMap,sizeOfMap,true);
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
    public void draw(Canvas canvas){

        super.draw(canvas);

        canvas.drawColor(0xFF1B007C);

        for (int i=0; i<arrGrass.size();i++){
            canvas.drawBitmap(arrGrass.get(i).getBm(),arrGrass.get(i).getX(),arrGrass.get(i).getY(), null);
        }

        //sounds.play(music, 1, 1, 0, 10, 0);


        image1.setOnClickListener(this);
        image2.setOnClickListener(this);
        image3.setOnClickListener(this);
        image4.setOnClickListener(this);
        imaga.setOnClickListener(this);

            if(guide2==true){
                snake.update();
                snake.draw(canvas);
                apple.draw(canvas);
            }


            if(snake.getArrPartSnake().get(0).getrBody().intersect(apple.getR())){
                if(dead==false){
                    sounds.play(eat, 1, 1, 0, 0, 0);
                }
                randomApple();
                this.valueApple++;
                apple.reset(arrGrass.get(randomApple()[0]).getX(),arrGrass.get(randomApple()[1]).getY());
                main.appleValue.setText(Integer.toString(this.valueApple));
                snake.addPart();
                //Log.d("start", "Яблоко скушано");
                this.start= System.currentTimeMillis();

            }else{
                long finish = System.currentTimeMillis();
                if(finish-this.start>=2000){
                    apple.reset(arrGrass.get(randomApple()[0]).getX(),arrGrass.get(randomApple()[1]).getY());
                    //Log.d("start", "Рандом яблока");
                    this.start= System.currentTimeMillis();
                }
            }

            handler.postDelayed(r,100);


            for (int i=1; i!=snake.getArrPartSnake().size();i++){
                if (snake.getArrPartSnake().get(0).getrBody().intersect(snake.getArrPartSnake().get(i).getrBody())){/////
                    dead=true;
                }
            }

            for(int i=0;i<=21;i++)
            {
                if(i<=12 &&snake.getArrPartSnake().get(0).getrBody().intersect(arrGrass.get(i).getR())){
                    dead=true;
                    sounds.play(die, 1, 1, 0, 0, 1);

                }
                if(snake.getArrPartSnake().get(0).getrBody().intersect(arrGrass.get(i*13).getR())){
                    dead=true;
                    sounds.play(die, 1, 1, 0, 0, 1);

                }
                if(i>=1 && snake.getArrPartSnake().get(0).getrBody().intersect(arrGrass.get(i*13-1).getR())){
                    dead=true;
                    sounds.play(die, 1, 1, 0, 0, 1);

                }
                if(i<=12 && snake.getArrPartSnake().get(0).getrBody().intersect(arrGrass.get(272+i).getR())){
                    dead=true;
                    sounds.play(die, 1, 1, 0, 0, 1);

                }
                if(dead==true){

                    //Log.d("start", "Змейка умерла");
                    super.draw(canvas);
                    canvas.drawColor(0xFF1B007C);


                    for (int j=0; j<arrGrass.size();j++){
                        canvas.drawBitmap(arrGrass.get(j).getBm(),arrGrass.get(j).getX(),arrGrass.get(j).getY(), null);
                    }

                    main.recordValue.setText(Integer.toString(this.valueApple));
                    imaga1.setVisibility(View.VISIBLE);
                    if(move==true){
                        imaga1.setVisibility(View.INVISIBLE);
                        System.exit(0);
                    }
                }


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


    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.helpPage1:
                image1.setVisibility(View.INVISIBLE);
                image2.setVisibility(View.VISIBLE);
                break;
            case R.id.helpPage2:
                image2.setVisibility(View.INVISIBLE);
                image3.setVisibility(View.VISIBLE);
                break;
            case R.id.helpPage3:
                image3.setVisibility(View.INVISIBLE);
                image4.setVisibility(View.VISIBLE);
                break;
            case R.id.helpPage4:
                image4.setVisibility(View.INVISIBLE);
                imaga.setVisibility(View.VISIBLE);
                break;
            case R.id.swipe:
                imaga.setVisibility(View.INVISIBLE);
                this.guide2 = true;
                break;
        }
    }


}
