package com.example.moveimagesgame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Point;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.PointerIcon;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    //Sreen size
    private  int screenWidth;
    private  int screenHeight;

    //Images
    private ImageView arrowUp;
    private ImageView arrowDown;
    private ImageView arrowRight;
    private ImageView arrowLeft;

    //position
    private float arrowUpX;
    private float arrowUpY;
    private float arrowDownX;
    private float arrowDownY;
    private float arrowRightX;
    private float arrowRightY;
    private float arrowLeftX;
    private float arrowLeftY;

    // Initialize Class
    private Handler handler = new Handler();
    private Timer timer = new Timer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrowUp = (ImageView)findViewById(R.id.arrowUp);
        arrowDown = (ImageView)findViewById(R.id.arrowdDown);
        arrowRight= (ImageView) findViewById(R.id.arrowRight);
        arrowLeft=(ImageView)findViewById(R.id.arrowLeft);

        // Get Sreenn size.

        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        //Move to out of screen.
        arrowUp.setX(-80.0f);
        arrowUp.setY(-80.0f);
        arrowDown.setX(-80.0f);
        arrowDown.setY(screenHeight + 80.0f);
        arrowRight.setX(screenWidth+80.0f);
        arrowRight.setY(-80.0f);
        arrowLeft.setX(-80.0f);
        arrowLeft.setY(-80.0f);

        //Start the timer
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        changePos();
                    }
                });
            }
        }, 0,20);

    }

        public  void  changePos(){
        //Up
            arrowUpY -= 10;
            if (arrowUp.getY()+arrowUp.getHeight()<0){
                arrowUpX = (float)Math.floor(Math.random()*(screenWidth-arrowUp.getWidth()));
                arrowUpY = screenHeight + 100.0f;
            }
            arrowUp.setX(arrowUpX);
            arrowUp.setY(arrowUpY);
         
            //Down
            arrowDownY += 10;
            if (arrowDown.getY()> screenHeight){
                arrowDownX = (float)Math.floor(Math.random()*(screenWidth-arrowDown.getWidth()));
                arrowDownY =- 100.0f;
            }
            arrowDown.setX(arrowDownX);
            arrowDown.setY(arrowDownY);

            //Rigth
            arrowRightX +=10;
            if (arrowRight.getX()>screenWidth){
                arrowRightX = -100.0f;
                arrowRightY = (float)Math.floor(Math.random()*(screenHeight-arrowRight.getHeight()));
            }
            arrowRight.setX(arrowRightX);
            arrowRight.setY(arrowRightY);

            //Left

            arrowLeftX -=10;
            if (arrowLeft.getX()+arrowLeft.getWidth()<0){
                arrowLeftX = screenWidth + 100.0f;
                arrowLeftY = (float)Math.floor(Math.random()*(screenHeight-arrowLeft.getHeight()));
            }
            arrowLeft.setX(arrowLeftX);
            arrowLeft.setY(arrowLeftY);
        }

}

