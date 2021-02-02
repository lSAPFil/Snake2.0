package com.example.snake20;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    int valueApple=0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constanta.SCREEN_WIDH=dm.widthPixels;
        Constanta.SCREEEN_HEIGHT=dm.heightPixels;
        setContentView(R.layout.activity_main);
        TextView appleValue=findViewById(R.id.txt_score);
    }
    public void onValue(){
        TextView appleValue=findViewById(R.id.txt_score);
        GameView g= new GameView(this, null);
        appleValue.setText(Integer.toString(g.valueApple));
    }
}
