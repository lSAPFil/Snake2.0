package com.example.snake20;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public String r=readFile();
    public   Path recordPathNew = null;
    public static int valueApple = 0 ;
    public static int valueRecord = 0 ;
    public TextView appleValue;
    public TextView recordValue;
    public TextView newRecordValue;
    public String recordStringOld;


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

        if (Files.exists(Paths.get("C:\\Users\\sapfi\\AndroidStudioProjects\\Snake20\\app\\src\\main\\res\\raw\\value.txt"))) {
            Path recordFile = Paths.get("C:\\Users\\sapfi\\AndroidStudioProjects\\Snake20\\app\\src\\main\\res\\raw\\value.txt");
            List<String> recordslist = null;
            try {
                recordslist = Files.readAllLines(recordFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            recordStringOld = recordslist.get(recordslist.size()-1);
            int recordIntOld = Integer.parseInt(recordStringOld);

            if (valueRecord > recordIntOld) {
                try {
                    Files.delete(recordFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    recordPathNew = Files.createFile(Paths.get("C:\\Users\\sapfi\\AndroidStudioProjects\\Snake20\\app\\src\\main\\res\\raw\\value.txt"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                recordPathNew = Paths.get("C:\\Users\\sapfi\\AndroidStudioProjects\\Snake20\\app\\src\\main\\res\\raw\\value.txt");
                recordStringOld = Integer.toString(valueRecord);
                try {
                    Files.write(recordPathNew, Collections.singleton(recordStringOld));//writeString(recordPathNew,recordStringOld);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        else if (!Files.exists(Paths.get("C:\\Users\\sapfi\\AndroidStudioProjects\\Snake20\\app\\src\\main\\res\\raw\\value.txt"))) {

            try {
                recordPathNew = Files.createFile(Paths.get("C:\\Users\\sapfi\\AndroidStudioProjects\\Snake20\\app\\src\\main\\res\\raw\\value.txt"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            recordPathNew = Paths.get("C:\\Users\\sapfi\\AndroidStudioProjects\\Snake20\\app\\src\\main\\res\\raw\\value.txt");
            recordStringOld = Integer.toString(valueRecord);
            try {
                Files.write(recordPathNew, Collections.singleton(recordStringOld));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        appleValue.setText(Integer.toString(valueApple));
        recordValue.setText(recordStringOld);
        newRecordValue.setText(recordStringOld);

//        File file = new File("C:\\Users\\sapfi\\AndroidStudioProjects\\Snake20\\app\\src\\main\\res\\raw\\value");
//        InputStream in=null;
//        try {
//            in= new BufferedInputStream(new FileInputStream(file));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } finally {
//            if(in!=null){
//                try {
//                    in.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        Scanner scan = new Scanner(in);
//        line= scan.hasNext() ? scan.next():"";



        //        File file= new File("C:\\Users\\sapfi\\AndroidStudioProjects\\Snake20\\app\\src\\main\\res\\raw\\value");
//        try {
//            FileInputStream input = new FileInputStream(file);
//
//            while (input.available()>0){
//                buf=input.read();
//            }
//            input.close();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }


//        String line;
//        recordValue.setText(Integer.toString(buf));


        g.imaga=findViewById(R.id.swipe);
        g.imaga1=findViewById(R.id.bd_dialog);
        g.image1=findViewById(R.id.helpPage1);
        g.image2=findViewById(R.id.helpPage2);
        g.image3=findViewById(R.id.helpPage3);
        g.image4=findViewById(R.id.helpPage4);
        g.main = this;


//        try {
//            Scanner scan = new Scanner(new File("C:\\Users\\sapfi\\AndroidStudioProjects\\Snake20\\app\\src\\main\\res\\text-file\\value"));
//            while (scan.hasNext()){
//                line=scan.next();
//                buf=Integer.valueOf(line);
//                if(buf>valueRecord){
//                    try {
//                        FileWriter file = new FileWriter("C:\\Users\\sapfi\\AndroidStudioProjects\\Snake20\\app\\src\\main\\res\\text-file\\value");
//                        file.write(line);
//                        file.close();
//                    }catch (IOException ex){
//                        System.out.println(ex.getMessage());
//                    }
//                    recordValue.setText(line);
//                    newRecordValue.setText(line);
//                }
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }



        startService(new Intent(this, MyService.class));

    }
    public void onBackPressed() {
        stopService(new Intent(this, MyService.class));
    }
    private String readFile(){
        String myValue="";
        File myFile=new File("C:\\Users\\sapfi\\AndroidStudioProjects\\Snake20\\app\\src\\main\\res\\text-file\\value");
        try{
            FileInputStream fis=new FileInputStream(myFile);
            DataInputStream inp=new DataInputStream(fis);
            BufferedReader buf=new BufferedReader(new InputStreamReader(inp));
            String line;
            while ((line=buf.readLine())!=null){
                myValue=myValue+line;
            }
            buf.close();
            inp.close();
            fis.close();

        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        return myValue;
    }

}

