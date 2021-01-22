package jp.ac.shohoku.programmer.balloontouchapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {

    private TextView timerText;

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