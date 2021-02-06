package com.example.snake20;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static int valueApple = 0 ;
    public static int valueRecord = 0 ;
    public TextView appleValue;
    public TextView recordValue;
    public TextView newRecordValue;
    //public File recordFile=new File("src/main/res/text-file/value");


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
        recordValue=findViewById(R.id.txt_best_score_final);
        newRecordValue=findViewById(R.id.txt_best_score);
        g.imaga=findViewById(R.id.swipe);
        g.imaga1=findViewById(R.id.bd_dialog);
        g.image1=findViewById(R.id.helpPage1);
        g.image2=findViewById(R.id.helpPage2);
        g.image3=findViewById(R.id.helpPage3);
        g.image4=findViewById(R.id.helpPage4);
        g.main = this;
        appleValue.setText(Integer.toString(valueApple));
        recordValue.setText(Integer.toString(valueRecord));
        //newRecordValue.setText(Integer.toString(checkRecord(valueRecord)));
    }

}

