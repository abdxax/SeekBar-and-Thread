package com.example.abdulrahman.seekbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
SeekBar seekBar;
TextView textView;
int MaxCounter=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar=(SeekBar) findViewById(R.id.seekBar2);
        textView=(TextView) findViewById(R.id.textView);
        seekBar.setMax(MaxCounter);
    }

    public void bustart(View view) {
       isRunning=true;
       // first way
        //myThread thread=new myThread();
       // thread.start();


        // secont way 
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRunning) {
                    if (counter <= MaxCounter) {
                        // call ui
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                seekBar.setProgress(counter);
                                textView.setText(counter + "");
                            }
                        });
                        counter++;
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        thread.start();

    }

    public void bustop(View view) {
        isRunning=false;
    }
boolean isRunning=false;
    int counter=0;
    public class myThread extends Thread{
        @Override
        public void run() {
  while (isRunning){
      if (counter<=MaxCounter){
          // call ui
          runOnUiThread(new Runnable() {
              @Override
              public void run() {
                  seekBar.setProgress(counter);
                  textView.setText(counter+"");
              }
          });
          counter++;
          try {
              Thread.sleep(2000);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
      }
  }
        }
    }
}
