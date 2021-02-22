package com.example.snake20;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MainActivity extends AppCompatActivity {
    public static int valueApple = 0 ;
    public static int valueRecord = 0 ;
    public TextView appleValue;
    public TextView recordValue;
    public TextView newRecordValue;
    public  String contents;

    private SharedPreferences json;

    private static final String PREF_FILE = "RECORDS"; // file name
    private static final String PREF_RECORD = "Value"; // tag in file

    public int record;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        json = getSharedPreferences(PREF_FILE, MODE_PRIVATE); // load file
//        record = json.getInt(PREF_FILE, 0);
//
//        //game stops
//        if (valueRecord > record) {
//            SharedPreferences.Editor edit = json.edit();
//            edit.putInt(PREF_RECORD, valueRecord);
//            edit.apply();
//        }
//
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constanta.SCREEN_WIDH=dm.widthPixels;
        Constanta.SCREEEN_HEIGHT=dm.heightPixels;
        setContentView(R.layout.activity_main);
        GameView g= findViewById(R.id.gv);
        appleValue=findViewById(R.id.txt_score);
         recordValue=findViewById(R.id.txt_best_score_final);
        newRecordValue=findViewById(R.id.txt_best_score);

//        String file="value.txt";
//        try {
//            FileOutputStream fOut = openFileOutput(file, MODE_WORLD_READABLE);
//            fOut.write(Integer.toString(valueRecord).getBytes());
//            fOut.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//


//        try {
//            FileInputStream fin = openFileInput(file);
//            int c;
//            recordString="";
//            while ((recordString=fin.read())!=1){
//                recordString=recordString+Character.toString(recordString);
//            }
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//        if (Files.exists(Paths.get("C:\\Users\\sapfi\\AndroidStudioProjects\\Snake20\\app\\src\\main\\res\\raw\\value.txt"))) {
//            Path recordFile = Paths.get("C:\\Users\\sapfi\\AndroidStudioProjects\\Snake20\\app\\src\\main\\res\\raw\\value.txt");
//
//            List<String> recordslist = null;
//            try {
//                recordslist = Files.readAllLines(recordFile);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            recordStringOld = recordslist.get(recordslist.size()-1);
//            int recordIntOld = Integer.parseInt(recordStringOld);
//            if (valueRecord > recordIntOld) {
//                try {
//                    Files.delete(recordFile);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                try {
//                    recordPathNew = Files.createFile(Paths.get("C:\\Users\\sapfi\\AndroidStudioProjects\\Snake20\\app\\src\\main\\res\\raw\\value.txt"));
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                recordPathNew = Paths.get("C:\\Users\\sapfi\\AndroidStudioProjects\\Snake20\\app\\src\\main\\res\\raw\\value.txt");
//                recordStringOld = Integer.toString(valueRecord);
//                byte[] bytes = recordStringOld.getBytes();
//                try {
//                    Files.write(Paths.get("C:\\Users\\sapfi\\AndroidStudioProjects\\Snake20\\app\\src\\main\\res\\raw\\value.txt"),bytes);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        else if (!Files.exists(Paths.get("C:\\Users\\sapfi\\AndroidStudioProjects\\Snake20\\app\\src\\main\\res\\raw\\value.txt"))) {
//            try {
//                recordPathNew = Files.createFile(Paths.get("C:\\Users\\sapfi\\AndroidStudioProjects\\Snake20\\app\\src\\main\\res\\raw\\value.txt"));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            recordPathNew = Paths.get("C:\\Users\\sapfi\\AndroidStudioProjects\\Snake20\\app\\src\\main\\res\\raw\\value.txt");
//            recordStringOld = Integer.toString(valueRecord);
//            byte[] bytes = recordStringOld.getBytes();
//            try {
//                Files.write(Paths.get("C:\\Users\\sapfi\\AndroidStudioProjects\\Snake20\\app\\src\\main\\res\\raw\\value.txt"),bytes);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }





//        try {
//            contents = readUsingFiles("C:\\Users\\sapfi\\AndroidStudioProjects\\Snake20\\app\\src\\main\\res\\raw\\value.txt");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        File myF = new File("C:\\Users\\sapfi\\AndroidStudioProjects\\Snake20\\app\\src\\main\\res\\raw\\value.txt");
//        try {
//            FileOutputStream foo = new FileOutputStream(myF,false);
//            byte[] myB= "0".getBytes();
//            foo.write(myB);
//            foo.close();
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//



//        if(Integer.valueOf(recordString)<valueRecord){
//            writeFile(this,"value.txt",Integer.toString(valueRecord));
//        }
        appleValue.setText(Integer.toString(valueApple));
        recordValue.setText(Integer.toString(valueRecord));
        newRecordValue.setText(Integer.toString(valueRecord));



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


    private String readFile(Context context){
        String myValue="";
        String fileName="value.txt";
        File dir = context.getFilesDir();
        File file = new File(dir,fileName);
        try{
            FileInputStream fis=new FileInputStream(file);
            BufferedReader buf=new BufferedReader(new InputStreamReader(fis));
            String line;
            while ((line=buf.readLine())!=null){
                myValue=myValue+line;
            }
            buf.close();
            fis.close();

        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        return myValue;
    }

    private void writeFile(Context context, String file, String body){
        File dir = new File(context.getFilesDir(),"mydir");
        if(!dir.exists()){
            dir.mkdir();
        }
        try {
            File gfile=new File(dir,file);
            FileWriter writer = new FileWriter(gfile);
            writer.append(body);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     private static String readUsingFiles(String file) throws IOException{
        return new String(Files.readAllBytes(Paths.get(file)));
     }


}

