package jp.ac.shohoku.programmer.balloontouchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity2 extends AppCompatActivity {

    private TextView timerText;
    private ImageView balloon;

    private int frameWidth;
    private int frameHeight;
    private int screenWidth;
    private int screenHeight;

    private Handler handler = new Handler();
    private Timer timer = new Timer();

    private float balloonX;
    private float balloonY;


    private SimpleDateFormat dataFormat =
            new SimpleDateFormat("mm:ss.SSS", Locale.US);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        long countNumber = 10000;
        long interval = 10;


        timerText = findViewById(R.id.Timer);
        timerText.setText(dataFormat.format(10000));

        final CountDown countDown = new CountDown(countNumber, interval);

        countDown.start();

        balloon = findViewById(R.id.balloon);

        WindowManager wm = getWindowManager();
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        screenWidth = size.x;
        screenHeight = size.y;

        balloon.setX(-80.0f);
        balloon.setY(-80.0f);
    }
    public void changePos(){
        balloonY -= 12;
        if(balloonY < 0){
            balloonY = screenHeight + 2;
            balloonX = (float)Math.floor(Math.random() * (frameWidth - balloon.getWidth()));
        }
        balloon.setY(balloonY);
        balloon.setX(balloonX);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        FrameLayout frame = findViewById(R.id.frame);
        frameHeight = frame.getHeight();

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
        }, 0, 20);
        return true;
    }

    class CountDown extends CountDownTimer {
        CountDown(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
            startActivity(intent);
            timerText.setText(dataFormat.format(0));
        }

        @Override
        public void onTick(long millisUntilFinished) {
            timerText.setText(dataFormat.format(millisUntilFinished));
        }
    }
}