package com.example.snake20;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static int valueApple = 0 ;
//    public static int valueRecord = 0 ;
    public TextView appleValue;
//    public TextView recordValue;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constanta.SCREEN_WIDH=dm.widthPixels;
        Constanta.SCREEEN_HEIGHT=dm.heightPixels;
        setContentView(R.layout.activity_main);
        GameView g= findViewById(R.id.gv);
        appleValue=findViewById(R.id.txt_score);
//        recordValue=findViewById(R.id.txt_best_score);
        g.main = this;
//           Log.d("start", "HELLO WORLD");

        appleValue.setText(Integer.toString(valueApple));
//        recordValue.setText(Integer.toString(valueRecord));


    }

}

